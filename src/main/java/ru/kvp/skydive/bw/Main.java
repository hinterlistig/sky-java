package ru.kvp.skydive.bw;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		MainController mainController = new MainController();

		prepareStage(stage, mainController.getView());

	}

	private void prepareStage(Stage stage, MainView mainView) {
		stage.setScene(mainView.getScene());
		stage.setTitle("Hello");
		stage.show();
	}
}
