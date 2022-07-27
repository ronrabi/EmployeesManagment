package Views;

import DoniMerkushin_RonRabinovich.*;
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

public class CreateNewEmployeePage implements IView {
	Stage primaryStage;
	IControler iControler;

	public CreateNewEmployeePage(Stage Pstage) {
		primaryStage = Pstage;
	}

	@Override
	public void Show() throws Exception {
		primaryStage.setTitle("Create new Worker");
		Label createNewEmployeeLabal = new Label("Create new Worker");
		createNewEmployeeLabal.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
		createNewEmployeeLabal.setTextFill(Color.web("#5709E7"));
		VBox vBoxCreateNewEmployeeLabal = new VBox();
		vBoxCreateNewEmployeeLabal.setPadding(new Insets(10));
		vBoxCreateNewEmployeeLabal.setAlignment(Pos.CENTER);
		vBoxCreateNewEmployeeLabal.getChildren().addAll(createNewEmployeeLabal);

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

		Label employeeNameLabel = new Label("Worker Name :");
		employeeNameLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField textFieldName = new TextField();
		HBox hBoxEmployeeName = new HBox(10);
		hBoxEmployeeName.setPadding(new Insets(10));
		hBoxEmployeeName.getChildren().addAll(employeeNameLabel, textFieldName);

		Label employeeTypeLabel = new Label("Worker Type :");
		employeeTypeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		ToggleGroup tglOption = new ToggleGroup();
		RadioButton rdoBase = new RadioButton("Base");
		RadioButton rdoBaseBonus = new RadioButton("Base Bonus");
		RadioButton rdoHourly = new RadioButton("Hourly");
		rdoBase.setSelected(true);
		rdoBase.setToggleGroup(tglOption);
		rdoBaseBonus.setToggleGroup(tglOption);
		rdoHourly.setToggleGroup(tglOption);
		VBox vBoxEmployeeType = new VBox(10);
		vBoxEmployeeType.setPadding(new Insets(10));
		vBoxEmployeeType.getChildren().addAll(employeeTypeLabel, rdoBase, rdoBaseBonus, rdoHourly);

		Label beginHourLabel = new Label("Begin Hour :");
		beginHourLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField textFieldBeginHour = new TextField();
		textFieldBeginHour.setPromptText("must be a number");

		Label endHourLabel = new Label("End Hour :");
		endHourLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		TextField textFieldEndHour = new TextField();
		textFieldEndHour.setPromptText("Must be 9 hours Differnces");
		HBox hBoxBeginHour = new HBox(10);
		hBoxBeginHour.setPadding(new Insets(10));
		hBoxBeginHour.getChildren().addAll(beginHourLabel, textFieldBeginHour);
		HBox hBoxEndHour = new HBox(10);
		hBoxEndHour.setPadding(new Insets(10));
		hBoxEndHour.getChildren().addAll(endHourLabel, textFieldEndHour);
		VBox vBoxBeginEnd = new VBox();
		vBoxBeginEnd.setPadding(new Insets(10));
		vBoxBeginEnd.getChildren().addAll(hBoxBeginHour, hBoxEndHour);

		Label selectRoleLabel = new Label("Select Role :");
		selectRoleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
		ComboBox<String> comboBoxRole = new ComboBox<String>();

		VBox vBoxComboBoxRole = new VBox(10);
		vBoxComboBoxRole.setPadding(new Insets(10));
		vBoxComboBoxRole.getChildren().addAll(selectRoleLabel, comboBoxRole);

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

		VBox vBoxFinalCenter = new VBox(10);
		vBoxFinalCenter.setPadding(new Insets(10));
		vBoxFinalCenter.getChildren().addAll(vBoxComboBox, hBoxEmployeeName, vBoxEmployeeType, vBoxBeginEnd,
				vBoxComboBoxRole);

		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(vBoxCreateNewEmployeeLabal);
		borderPane.setCenter(vBoxFinalCenter);
		borderPane.setBottom(hBoxBtn);

		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene = new Scene(stackPane, 500, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		buttonBeack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				beackToMenue();
			}
		});

		comboBoxCompany.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (comboBoxCompany.getValue() != "") {
						comboBoxRole.getItems().clear();
						int index = Integer.parseInt(comboBoxCompany.getValue()) - 1;
						iControler.getAllDepartments().get(index).getRoles().forEach(role -> {
							comboBoxRole.getItems().addAll(role.getName());
						});

					}
				} catch (Exception e) {
				}
			}
		});

		buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				String option = tglOption.getSelectedToggle().toString();
				if (option.contains("Bonus")) {
					try {
						if (validHours(Integer.parseInt(textFieldBeginHour.getText()),
								Integer.parseInt(textFieldEndHour.getText()))) {
							Worker worker = new WorkerBaseBonus(textFieldName.getText(),
									Integer.parseInt(textFieldBeginHour.getText()),
									Integer.parseInt(textFieldEndHour.getText()), comboBoxRole.getValue());
							iControler.addWorker(worker, Integer.parseInt(comboBoxCompany.getValue()));

							createMassegeAlert("Worker Successfully add");

							beackToMenue();
						} else {
							createMassegeAlert("begin/end hour not ligal ");
						}

					} catch (Exception e) {
						createMassegeAlert("One of the field is empty or begin/end hours no ligal");
						System.out.println(e);

					}
				} else if (option.contains("Base")) {
					try {
						if (validHours(Integer.parseInt(textFieldBeginHour.getText()),
								Integer.parseInt(textFieldEndHour.getText()))) {

							Worker worker = new WorkerBase(textFieldName.getText(),
									Integer.parseInt(textFieldBeginHour.getText()),
									Integer.parseInt(textFieldEndHour.getText()), comboBoxRole.getValue());
							iControler.addWorker(worker, Integer.parseInt(comboBoxCompany.getValue()));
							createMassegeAlert("Worker Successfully add");
							beackToMenue();
						} else {
							createMassegeAlert("begin/end hour not ligal ");
						}

					} catch (Exception e) {
						createMassegeAlert("One of the fild is empty or begin/end hours not ligal");
						System.out.println(e);

					}

				} else {
					try {
						if (validHours(Integer.parseInt(textFieldBeginHour.getText()),
								Integer.parseInt(textFieldEndHour.getText()))) {

							Worker worker = new WorkerHourly(textFieldName.getText(),
									Integer.parseInt(textFieldBeginHour.getText()),
									Integer.parseInt(textFieldEndHour.getText()), comboBoxRole.getValue());
							iControler.addWorker(worker, Integer.parseInt(comboBoxCompany.getValue()));
							createMassegeAlert("Worker Successfully add");
							beackToMenue();
						} else {
							createMassegeAlert("begin/end hour not ligal ");
						}
					} catch (Exception e) {
						createMassegeAlert("One of the fild is empty or begin/end hours not ligal");
						System.out.println(e);

					}
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

	private static boolean validHours(int begin, int end) {
		if (begin >= 0 && begin <= 23 && end >= 0 && end <= 23) {
			if ((end - begin == 9) || (end + 24 - begin == 9))
				return true;
		}
		return false;

	}
}
