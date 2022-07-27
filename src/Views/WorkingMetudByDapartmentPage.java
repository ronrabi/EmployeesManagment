package Views;

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
import IMVC.IControler;
import IMVC.IModel;
import IMVC.IView;

public class WorkingMetudByDapartmentPage implements IView {
	Stage primaryStage;
	IControler iControler;

	public WorkingMetudByDapartmentPage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void Show() throws Exception {
		primaryStage.setTitle("Method By Darpatment");

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setAlignment(Pos.CENTER);
		Label labelTitle = new Label("Method By Darpatment");
		labelTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
		labelTitle.setTextFill(Color.web("#5709E7"));

		Label selectCompanyLabel = new Label("Select Department :");
		selectCompanyLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));

		ComboBox<String> comboBoxCompany = new ComboBox<String>();
		int nuberOfDepartment = iControler.getAllDepartments().size();
		for (int i = 0; i < nuberOfDepartment; i++) {
			comboBoxCompany.getItems().addAll(Integer.toString(i + 1));
		}

		VBox vBoxComboBox = new VBox(10);
		vBoxComboBox.setPadding(new Insets(10));
		vBoxComboBox.getChildren().addAll(selectCompanyLabel, comboBoxCompany);
		Label peferencesLabel = new Label("select peferences :");
		peferencesLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		ToggleGroup tglOption = new ToggleGroup();
		RadioButton rdoEarlier = new RadioButton("EARLIER");
		RadioButton rdoLater = new RadioButton("LATER");
		RadioButton rdoDeafult = new RadioButton("DEFAULT");
		RadioButton rdoFreelancer = new RadioButton("FREELANCER");
		rdoEarlier.setSelected(true);
		rdoEarlier.setToggleGroup(tglOption);
		rdoLater.setToggleGroup(tglOption);
		rdoDeafult.setToggleGroup(tglOption);
		rdoFreelancer.setToggleGroup(tglOption);
		VBox vBoxpeferences = new VBox(10);
		vBoxpeferences.setPadding(new Insets(10));
		vBoxpeferences.getChildren().addAll(peferencesLabel, rdoEarlier, rdoLater, rdoDeafult, rdoFreelancer);
		HBox hBoxBtn = new HBox();
		hBoxBtn.setPadding(new Insets(100));
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

		Label moveShiftLabel = new Label("Number of hour to move shift :");
		moveShiftLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField textFieldNuberHour = new TextField();

		textFieldNuberHour.setPromptText("must be a number"); // to set the hint text

		HBox hBoxEmployeeName = new HBox(10);
		hBoxEmployeeName.setPadding(new Insets(10));
		hBoxEmployeeName.getChildren().addAll(moveShiftLabel, textFieldNuberHour);

		VBox vBox1finalCenter = new VBox(10);
		vBox1finalCenter.getChildren().addAll(vBoxComboBox, vBoxpeferences, hBoxEmployeeName);
		vBox.getChildren().addAll(labelTitle);
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(vBox);
		borderPane.setCenter(vBox1finalCenter);
		borderPane.setBottom(hBoxBtn);

		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(stackPane, 600, 600);
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
				try {
					if (tglOption.getSelectedToggle().toString().contains("EARLIER")) {
						int indexDapatment = Integer.parseInt(comboBoxCompany.getValue()) - 1;
						int hour = Integer.parseInt(textFieldNuberHour.getText());
						if (hour > 24) {
							createMassegeAlert("Hour to shift Must by under 24");
						} else {
							iControler.getCompany().changeDepHours(indexDapatment, 3, hour);
							beackToMenue();
						}
					} else if (tglOption.getSelectedToggle().toString().contains("LATER")) {
						int indexDapatment = Integer.parseInt(comboBoxCompany.getValue()) - 1;
						int hour = Integer.parseInt(textFieldNuberHour.getText());
						if (hour > 24) {
							createMassegeAlert("Hour to shift Must by under 24");
						} else {
							iControler.getCompany().changeDepHours(indexDapatment, 3, hour);
							beackToMenue();
						}
					} else if (tglOption.getSelectedToggle().toString().contains("DEFAULT")) {
						int indexDapatment = Integer.parseInt(comboBoxCompany.getValue()) - 1;
						int hour = Integer.parseInt(textFieldNuberHour.getText());
						if (hour > 24) {
							createMassegeAlert("Hour to shift Must by under 24");
						} else {
							iControler.getCompany().changeDepHours(indexDapatment, 3, hour);
							beackToMenue();
						}
					} else if (tglOption.getSelectedToggle().toString().contains("FREELANCER")) {
						int indexDapatment = Integer.parseInt(comboBoxCompany.getValue()) - 1;
						int hour = Integer.parseInt(textFieldNuberHour.getText());
						if (hour > 24) {
							createMassegeAlert("Hour to shift Must by under 24");
						} else {
							iControler.getCompany().changeDepHours(indexDapatment, 3, hour);
							beackToMenue();
						}
					}

				} catch (Exception e) {
					createMassegeAlert("One of the field is empty or hour is not ligal");
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
