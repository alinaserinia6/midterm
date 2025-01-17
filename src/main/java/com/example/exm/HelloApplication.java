package com.example.exm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.nio.file.Paths;

public class HelloApplication extends Application implements Runnable {

	private static Stage stage;

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("a1.fxml"));
		Parent myPane = fxmlLoader.load();
		myPane.setId("pane");
		Scene scene = new Scene(myPane);
		stage.setTitle("Choghok");
		scene.getStylesheets().addAll(this.getClass().getResource("anchor.css").toExternalForm());
		stage.setScene(scene);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				try {
					Client.out.writeObject(new Request(RM.END_PROCESS));
					Client.in.close();
					Client.out.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				} finally {
					System.exit(0);
				}
			}
		});
		this.stage = stage;
		stage.show();
	}


	@Override
	public void run() {
		Stage stage = new Stage();
		try {
			start(stage);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		launch();

	}

	public static void ChangePage(Event e, String fxmlFile) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//		Parent root = FXMLLoader.load(HelloApplication.class.getResource(fxmlFile + ".fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile + ".fxml"));
		Parent root = fxmlLoader.load();
		root.setId(fxmlFile);
		if (fxmlFile.equals("aFocusTweet")) Client.tp = fxmlLoader.getController();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		Application a = new Application() {@Override public void start(Stage stage) throws Exception {}};
		scene.getStylesheets().addAll(a.getClass().getResource("anchor.css").toExternalForm());
		stage.show();
	}

	public static void ChangePage(Event e, String fxmlFile, Object controller) throws IOException {
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile + ".fxml"));
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
		root.setId(fxmlFile);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		Application a = new Application() {@Override public void start(Stage stage) throws Exception {}};
		scene.getStylesheets().addAll(a.getClass().getResource("anchor.css").toExternalForm());
		stage.show();
	}


	public static Stage getStage() {
		return stage;
	}
}