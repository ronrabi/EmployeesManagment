package Views;

import DoniMerkushin_RonRabinovich.Company;
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

public class SetCompanyNamePage implements IView {
	Stage primaryStage;
	IControler iControler;

	public SetCompanyNamePage(Stage Pstage) {
		primaryStage = Pstage;
	}

	@Override
	public void Show() throws Exception {
		primaryStage.setTitle("Company Name");
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setAlignment(Pos.CENTER);

		Label labelTitle = new Label("Company Name");

		labelTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
		labelTitle.setTextFill(Color.web("#5709E7"));

		vBox.getChildren().addAll(labelTitle);

		Label employeeNameLabel = new Label("Company Name :");
		employeeNameLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField textFieldName = new TextField();
		HBox hBoxEmployeeName = new HBox(10);
		hBoxEmployeeName.setPadding(new Insets(10));
		hBoxEmployeeName.getChildren().addAll(employeeNameLabel, textFieldName);

		HBox hBoxBtn = new HBox();
		hBoxBtn.setPadding(new Insets(10));
		hBoxBtn.setAlignment(Pos.BOTTOM_CENTER);
		hBoxBtn.setSpacing(10);

		Button buttonCreate = new Button("Start");

		Button buttonBeack = new Button("Exit");

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

		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(vBox);
		borderPane.setBottom(hBoxBtn);
		borderPane.setCenter(hBoxEmployeeName);

		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(stackPane, 500, 200);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		buttonBeack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();

			}
		});
		buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (textFieldName.getText().length() == 0) {
					createMassegeAlert("field is empty");
				} else {
					iControler.CreateSetCompanyName(textFieldName.getText());
					iControler.createHardCoded();
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
