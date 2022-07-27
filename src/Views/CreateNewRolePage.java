package Views;

import DoniMerkushin_RonRabinovich.Role;
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

public class CreateNewRolePage implements IView {
	Stage primaryStage;
	IControler iControler;

	public CreateNewRolePage(Stage Pstage) {
		primaryStage = Pstage;
	}

	@Override
	public void Show() throws Exception {
		primaryStage.setTitle("Create New Role");
		Label createRoleLabel = new Label("Create new Role");
		createRoleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
		createRoleLabel.setTextFill(Color.web("#5709E7"));
		VBox createRoleLabelVbox = new VBox();
		createRoleLabelVbox.setAlignment(Pos.CENTER);
		createRoleLabelVbox.setPadding(new Insets(10));
		createRoleLabelVbox.getChildren().add(createRoleLabel);

		Label nameRoleLabel = new Label("Role Name:");
		nameRoleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
		TextField textFieldRoleName = new TextField();
		HBox hBoxRoleName = new HBox(10);
		hBoxRoleName.setPadding(new Insets(10));
		// hBoxRoleName.setAlignment(Pos.CENTER);
		hBoxRoleName.getChildren().addAll(nameRoleLabel, textFieldRoleName);

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

		VBox finalVboxCenter = new VBox(10);
		finalVboxCenter.setPadding(new Insets(10));
		finalVboxCenter.getChildren().addAll(hBoxRoleName, vBoxisItChagable);

		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(createRoleLabelVbox);
		borderPane.setCenter(finalVboxCenter);
		borderPane.setBottom(hBoxBtn);

		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene = new Scene(stackPane, 500, 600);
		primaryStage.setScene(scene);
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
				if (iControler.cheakNameRoleExist(textFieldRoleName.getText())) {
					createMassegeAlert("Role name is exist");

				} else {
					if (textFieldRoleName.getText().isEmpty()) {
						createMassegeAlert("One of the field is empty");
					} else {
						if (tglOption.getSelectedToggle().toString().contains("Yes")) {
							Role role = new Role(textFieldRoleName.getText(), true);
							iControler.addRole(role);
							createMassegeAlert("Role Successfully add");
							beackToMenue();
						} else {
							Role role = new Role(textFieldRoleName.getText(), false);
							iControler.addRole(role);
							createMassegeAlert("Role Successfully add");
							beackToMenue();

						}
					}
				}
			}
		});

	}

	@Override
	public void setControler(IControler C) {
		this.iControler = C;
	}

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
