package com.example.exm;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import io.github.palexdev.materialfx.controls.MFXScrollPane;

public class TimeLineController {
	@FXML
	private MFXScrollPane sp;
	@FXML
	private ImageView avatar;
	private static boolean shutdown;
	private static Thread thread;
	private final int MAX_READ = 5 + 1;

	public void initialize() {
		System.out.println("initialize timeLine");
		shutdown = false;
		Label l = new Label();
		l.setText("In the name of GOD");
		Client.timeline.getChildren().add(0,l);
        sp.setContent(Client.timeline);
		if (thread != null) return;
		thread = new Thread(() -> {
			while (!shutdown) {
				try {
					Client.out.writeObject(new Request(RM.GET_TWEETS));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				System.out.println("in while");
				getTweets();
				try {
					Thread.sleep(4000); // time of sleep between get tweet
				} catch (InterruptedException e) {
					System.err.println("\t\t\t{interrupted}");
					break;
				}
			}
			thread = null;
			System.out.println("finish thread");
		});
		thread.start();
		System.err.println("hey");
	}

	private void getTweets() {
		System.out.println("\t".repeat(7) + "{getTweet}");
		for (int t = 0; t < MAX_READ; t++) {
			try {
				Tweet i = (Tweet) Client.getObject();
				Client.tweets.put(i.getId(), i);
				if (i.getId() == -1) {
					System.err.println("\t".repeat(7) + "{Finish}");
					return;
				}
				GridPane pane = i.toShow();
				Platform.runLater(() -> Client.timeline.getChildren().add(0, pane));
				Client.out.writeObject(new Request(RM.LAST_SEEN_TIME, i.getUsername(), i.getDt()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void addTweet(MouseEvent e) throws IOException {
		stop();
		HelloApplication.ChangePage(e, "a6");
	}

	@FXML
	void setting(MouseEvent e) throws IOException {
		stop();
		HelloApplication.ChangePage(e, "a7");
	}

	@FXML
	void searchButton(MouseEvent e) throws IOException {
		stop();
		HelloApplication.ChangePage(e, "a8");
	}

	@FXML
	void notificationButton(MouseEvent e) throws IOException{
		stop();
		HelloApplication.ChangePage(e, "aNotification");
	}

	@FXML
	void directButton(MouseEvent e) throws IOException {
		stop();
		HelloApplication.ChangePage(e, "aDirect");
	}

	public static void stop() { // TODO DELETE STATIC
		shutdown = true;
		if (thread != null) thread.interrupt();
	}

}
