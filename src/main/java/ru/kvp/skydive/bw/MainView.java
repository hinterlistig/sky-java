package ru.kvp.skydive.bw;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import ru.kvp.skydyve.bw.util.StatusLine;

public class MainView {
	private static final Color LABEL_COLOR = Color.color(0.624, 0.624, 0.624);
	private static final Font LABEL_FONT = new Font(18.0);
	private static final Font STATUS_LABEL_FONT = new Font(11.0);

	Scene scene;
	StatusLine status;
	
	public Scene getScene() {

		return scene;
	}

	public MainView() {
		scene = prepareScene();
	}

	private Menu prepareMenuHelp() {
		Menu menuHelp = new Menu("Help", null, new MenuItem("About Application"));
		menuHelp.setMnemonicParsing(false);
		return menuHelp;
	}

	private Menu prepareMenuEdit() {
		Menu menuEdit = new Menu("Edit", null, new MenuItem("Undo"), new MenuItem("Redo"), new SeparatorMenuItem(),
				new MenuItem("Cut"), new MenuItem("Close"), new MenuItem("Paste"), new MenuItem("Delete"),
				new SeparatorMenuItem(), new MenuItem("Select All"), new MenuItem("Unselect All"));
		menuEdit.setMnemonicParsing(false);
		return menuEdit;
	}

	private Menu prepareMenuFile() {
		Menu menuFile = new Menu("File", null, new MenuItem("New"), new MenuItem("Open"), new MenuItem("Open Recent"),
				new SeparatorMenuItem(), new MenuItem("Close"), new MenuItem("Save"), new MenuItem("Save As"),
				new MenuItem("Revert"), new SeparatorMenuItem(), new MenuItem("Preferences"), new SeparatorMenuItem(),
				new MenuItem("Quit"));
		menuFile.setMnemonicParsing(false);
		return menuFile;
	}

	private MenuBar prepareMenuBar() {
		Menu menuFile = prepareMenuFile();
		Menu menuEdit = prepareMenuEdit();
		Menu menuHelp = prepareMenuHelp();

		MenuBar menuBar = new MenuBar(menuFile, menuEdit, menuHelp);
		VBox.setVgrow(menuBar, Priority.NEVER);
		return menuBar;
	}

	private VBox prepareVBox() {

		MenuBar menuBar = prepareMenuBar();

		SplitPane splitPane = prepareSplitPane();
		HBox hBox = prepareHBox();

		VBox vBox = new VBox();
		vBox.setPrefSize(900, 600);
		vBox.getChildren().addAll(menuBar, splitPane, hBox);

		return vBox;
	}

	private HBox prepareHBox() {

		HBox hBox = new HBox(prepareLeftStatus(), prepareStatusDelimiter(), prepareRightStatus());
		hBox.setPadding(new Insets(3.0, 3.0, 3.0, 3.0));
		return hBox;
	}

	private Node prepareLeftStatus() {
		Label label = new Label("LeftStatus");
		label.setMaxSize(1.7976931348623157E308, -1.0);
		HBox.setHgrow(label, Priority.ALWAYS);
		label.setTextFill(LABEL_COLOR);
		label.setFont(STATUS_LABEL_FONT);
		return label;
	}

	private Node prepareStatusDelimiter() {
		Pane pane = new Pane();
		pane.setPrefSize(-1, -1);
		HBox.setHgrow(pane, Priority.ALWAYS);
		return pane;
	}

	private Node prepareRightStatus() {
		Label label = new Label("Right status");
		label.setMaxWidth(-1.0);
		HBox.setHgrow(label, Priority.NEVER);
		label.setTextFill(LABEL_COLOR);
		label.setFont(STATUS_LABEL_FONT);
		return label;
	}

	private SplitPane prepareSplitPane() {
		SplitPane splitPane = new SplitPane();
		splitPane.setDividerPositions(0.2505567928730512, 0.7505567928730512);
		splitPane.setPrefSize(-1.0, -1.0);
		splitPane.setFocusTraversable(true);
		VBox.setVgrow(splitPane, Priority.ALWAYS);
		splitPane.getItems().addAll(prepareLeftPane(), prepareCenterPane(), prepareRightPane());
		return splitPane;
	}

	private Node prepareLeftPane() {
		AnchorPane anchorPane = new AnchorPane(prepareLabelMaster());
		return anchorPane;
	}

	private Node prepareLabelMaster() {
		return prepareAbstractLabel("Master", Pos.CENTER, 14.0, 14.0, 60.0, -1.0, "&#10", TextAlignment.CENTER,
				LABEL_COLOR, false, LABEL_FONT);
	}

	private Label prepareAbstractLabel(String text, Pos pos, double layoutX, double layoutY, double minWidth,
			double prefWidth, String style, TextAlignment textAlignment, Paint textFill, boolean wrapText, Font font) {

		Label label = new Label(text);
		label.setAlignment(pos);
		label.setLayoutX(layoutX);
		label.setLayoutY(layoutY);
		label.setMinWidth(minWidth);
		label.setPrefWidth(prefWidth);
		label.setStyle(style);
		label.setTextAlignment(textAlignment);
		label.setTextFill(textFill);
		label.setWrapText(wrapText);
		label.setFont(font);
		return label;
	}

	private Node prepareCenterAnchor() {
		AnchorPane anchorPane = new AnchorPane(prepareLabelView());
		anchorPane.setId("Content");
		anchorPane.setMinSize(-1.0, -1.0);
		anchorPane.setPrefSize(545.0, 430.0);
		return anchorPane;
	}

	private Node prepareCenterPane() {

		ScrollPane scrollPane = new ScrollPane(prepareCenterAnchor());
		scrollPane.setPrefSize(-1.0, -1.0);

		return scrollPane;
	}

	private Node prepareLabelView() {

		return prepareAbstractLabel("View", Pos.CENTER, 14.0, 14.0, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE,
				"&#10", TextAlignment.CENTER, LABEL_COLOR, false, LABEL_FONT);
	}

	private Node prepareLabelDetails() {

		return prepareAbstractLabel("Details", Pos.CENTER, 14.0, 14.0, Region.USE_COMPUTED_SIZE,
				Region.USE_COMPUTED_SIZE, "&#10", TextAlignment.CENTER, LABEL_COLOR, false, LABEL_FONT);
	}

	private Node prepareRightPane() {
		return new AnchorPane(prepareLabelDetails());
	}

	private Scene prepareScene() {
		VBox vBox = prepareVBox();
		Scene scene = new Scene(vBox);
		return scene;
	}
}
