package ru.kvp.skydive.bw;

public class MainController {
	MainView mainView;
	DiveModel model;

	public MainController() {
		mainView = new MainView();
		model = new DiveModel();
	}

	public MainView getView() {
		return mainView;
	}

}
