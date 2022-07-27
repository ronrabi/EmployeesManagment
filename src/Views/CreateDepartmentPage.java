package Views;

import DoniMerkushin_RonRabinovich.Department;
import IMVC.IControler;
import IMVC.IModel;
import IMVC.IView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import Controler.Controler;

public class CreateDepartmentPage implements IView {
	Stage primaryStage;
	IControler iControler;

	public CreateDepartmentPage(Stage Pstage) {
		primaryStage = Pstage;
	}

	@Override
	public void Show() throws Exception {
		primaryStage.setTitle("Create New Department");
		BorderPane bpRoot = new BorderPane();

		Label isItChangeableLabel = new Label("Is it Changeable?");
		isItChangeableLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		ToggleGroup tglOption = new ToggleGroup();
		RadioButton rdoYes = new RadioButton("Yes");
		RadioButton rdoNo = new RadioButton("No");
		rdoYes.setSelected(true);
		rdoYes.setToggleGroup(tglOption);
		rdoNo.setToggleGroup(tglOption);
		VBox vBoxisItChagable = new VBox(10);
		vBoxisItChagable.setPadding(new Insets(10));
		vBoxisItChagable.getChildren().addAll(isItChangeableLabel, rdoYes, rdoNo);

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setAlignment(Pos.CENTER);

		Label labelTitle = new Label("Create Department");

		labelTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
		labelTitle.setTextFill(Color.web("#5709E7"));

		vBox.getChildren().addAll(labelTitle);

		HBox hBoxBtn = new HBox();
		hBoxBtn.setPadding(new Insets(10));
		hBoxBtn.setAlignment(Pos.BOTTOM_CENTER);
		hBoxBtn.setSpacing(10);

		Button buttonCreate = new Button("Create");

		Button buttonBeack = new Button("Back");

		buttonBeack
				.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
		buttonCreate.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

		buttonBeack.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				buttonBeack.setScaleX(1.2);
				buttonBeack.setScaleY(1.2);
			}
		});
		buttonBeack.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				buttonBeack.setScaleX(1);
				buttonBeack.setScaleY(1);
			}
		});
		buttonCreate.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				buttonCreate.setScaleX(1.2);
				buttonCreate.setScaleY(1.2);
			}
		});
		buttonCreate.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				buttonCreate.setScaleX(1);
				buttonCreate.setScaleY(1);
			}
		});

		hBoxBtn.getChildren().addAll(buttonCreate, buttonBeack);

		VBox finalVbox = new VBox();
		finalVbox.setSpacing(20);

		finalVbox.getChildren().addAll(vBoxisItChagable);
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(vBox);
		borderPane.setBottom(hBoxBtn);
		borderPane.setCenter(finalVbox);

		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(stackPane, 500, 600);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		buttonBeack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				beackToMenue();
			}
		});
		buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (tglOption.getSelectedToggle().toString().contains("Yes")) {
					Department department = new Department(true);
					iControler.addDepartment(department);
					createMassegeAlert("Department Successfully add");
					beackToMenue();

				} else {
					Department department = new Department(false);
					iControler.addDepartment(department);
					createMassegeAlert("Department Successfully add");
					beackToMenue();

				}
			}
		});

	}

	@Override
	public void setControler(IControler C) {
		this.iControler = C;

	}

	@Override
	public void createMassegeAlert(String s) {
		Alert alertEroro = new Alert(Alert.AlertType.INFORMATION);
		alertEroro.setHeaderText(null);
		alertEroro.setContentText(s);
		alertEroro.showAndWait();
	}

	@Override
	public void beackToMenue() {
		try {
			IView v = new RevenueCalculatorPage(primaryStage);
			IModel m = iControler.getiModel();
			IControler c = new Controler();
			iControler.setModel(m);
			iControler.setView(v);
			v.setControler(iControler);
			m.setControler(iControler);
			v.Show();
		} catch (Exception e) {
		}
	}

}
