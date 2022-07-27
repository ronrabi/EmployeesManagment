package Views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Optional;

import IMVC.IControler;
import IMVC.IModel;
import IMVC.IView;

public class RevenueCalculatorPage implements IView {
	Stage stage;
	IControler iControler;

	public RevenueCalculatorPage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void Show() throws Exception {

		stage.setTitle("Revenue Calculator");

		Label revenueCalculatorLabel = new Label("Revenue Calculator");
		revenueCalculatorLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
		revenueCalculatorLabel.setTextFill(Color.web("#5709E7"));
		VBox vBoxRevenueCalculatorLabel = new VBox();
		vBoxRevenueCalculatorLabel.setAlignment(Pos.CENTER);
		vBoxRevenueCalculatorLabel.setPadding(new Insets(10));
		vBoxRevenueCalculatorLabel.getChildren().add(revenueCalculatorLabel);

		Label createNewLabel = new Label("Create new :");
		createNewLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		VBox createNewLabelVbox = new VBox();
		createNewLabelVbox.setPadding(new Insets(10));
		createNewLabelVbox.setAlignment(Pos.CENTER);
		createNewLabelVbox.getChildren().add(createNewLabel);

		Button dapartmentBtn = new Button("Department");
		Button roleBtn = new Button("Role");
		Button employeeBtn = new Button("Worker");
		HBox createNewBtnHbox = new HBox();
		createNewBtnHbox.setSpacing(10);
		createNewBtnHbox.setAlignment(Pos.CENTER);
		createNewBtnHbox.setPadding(new Insets(10));
		createNewBtnHbox.getChildren().addAll(dapartmentBtn, roleBtn, employeeBtn);

		Label changeWorkingLabel = new Label("Change working method:");
		changeWorkingLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		VBox changeWorkingLabelVbox = new VBox();
		changeWorkingLabelVbox.setPadding(new Insets(10));
		changeWorkingLabelVbox.setAlignment(Pos.CENTER);
		changeWorkingLabelVbox.getChildren().add(changeWorkingLabel);

		Button byRoleBtn = new Button("By Role");
		Button bydepartmentBtn = new Button("By Department");
		HBox changeWorkingBtn = new HBox();
		changeWorkingBtn.setSpacing(10);
		changeWorkingBtn.setAlignment(Pos.CENTER);
		changeWorkingBtn.setPadding(new Insets(10));
		changeWorkingBtn.getChildren().addAll(byRoleBtn, bydepartmentBtn);

		Label moreLabel = new Label("More:");
		moreLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
		VBox moreLbelVbox = new VBox();
		moreLbelVbox.setPadding(new Insets(10));
		moreLbelVbox.setAlignment(Pos.CENTER);
		moreLbelVbox.getChildren().add(moreLabel);

		Button showCompanysBtn = new Button("Show company's info");
		Button calculatorRevenueBtn = new Button("Calculator Revenue");
		VBox moreBtnVbox = new VBox();
		moreBtnVbox.setSpacing(10);
		moreBtnVbox.setPadding(new Insets(10));
		moreBtnVbox.setAlignment(Pos.CENTER);
		moreBtnVbox.getChildren().addAll(showCompanysBtn, calculatorRevenueBtn);

		Button exitBtn = new Button("EXIT");
		exitBtn.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));

		VBox exitBtnVbox = new VBox();
		exitBtnVbox.setAlignment(Pos.CENTER);
		exitBtnVbox.setPadding(new Insets(20));
		exitBtnVbox.getChildren().add(exitBtn);

		VBox finalVbox = new VBox();
		finalVbox.setSpacing(10);
		finalVbox.getChildren().addAll(createNewLabelVbox, createNewBtnHbox, changeWorkingLabelVbox, changeWorkingBtn,
				moreLbelVbox, moreBtnVbox);

		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(vBoxRevenueCalculatorLabel);
		borderPane.setBottom(exitBtnVbox);
		borderPane.setCenter(finalVbox);

		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(stackPane, 500, 600);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

		exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				exitBtn.setScaleX(1.2);
				exitBtn.setScaleY(1.2);
			}
		});
		exitBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				exitBtn.setScaleX(1);
				exitBtn.setScaleY(1);
			}
		});

		dapartmentBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					IView v = new CreateDepartmentPage(stage);
					IModel m = iControler.getiModel();

					iControler.setModel(m);
					iControler.setView(v);
					v.setControler(iControler);
					m.setControler(iControler);
					v.Show();
				} catch (Exception e) {

				}
			}
		});

		exitBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Alert alert = new Alert(Alert.AlertType.NONE);
				alert.setContentText("Do you want to Save the file?");
				ButtonType buttonTypeOne = new ButtonType("yes");
				ButtonType buttonTypeTwo = new ButtonType("no");
				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne) {
					try {
						iControler.saveToFile(iControler.getCompany());
						stage.close();

					} catch (Exception e) {
						createMassegeAlert("error!! the file not save successfully!");
						stage.close();
					}

				} else {

					stage.close();
				}
			}
		});

		roleBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					IView v = new CreateNewRolePage(stage);
					IModel m = iControler.getiModel();

					iControler.setModel(m);
					iControler.setView(v);
					v.setControler(iControler);
					m.setControler(iControler);
					v.Show();

				} catch (Exception e) {
				}
			}
		});

		employeeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					IView v = new CreateNewEmployeePage(stage);
					IModel m = iControler.getiModel();

					iControler.setModel(m);
					iControler.setView(v);
					v.setControler(iControler);
					m.setControler(iControler);

					v.Show();

				} catch (Exception e) {
				}

			}
		});

		showCompanysBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					IView v = new ShowCompanyInfoPage(stage);
					IModel m = iControler.getiModel();

					iControler.setModel(m);
					iControler.setView(v);
					v.setControler(iControler);
					m.setControler(iControler);

					v.Show();

				} catch (Exception e) {
				}

			}
		});

		byRoleBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					IView v = new WorkingMetudByRolePage(stage);
					IModel m = iControler.getiModel();

					iControler.setModel(m);
					iControler.setView(v);
					v.setControler(iControler);
					m.setControler(iControler);

					v.Show();

				} catch (Exception e) {
				}

			}
		});

		bydepartmentBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					IView v = new WorkingMetudByDapartmentPage(stage);
					IModel m = iControler.getiModel();

					iControler.setModel(m);
					iControler.setView(v);
					v.setControler(iControler);
					m.setControler(iControler);

					v.Show();

				} catch (Exception e) {
				}

			}
		});
		calculatorRevenueBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {

					IView v = new CalculatorRevenuePage(stage);
					IModel m = iControler.getiModel();

					iControler.setModel(m);
					iControler.setView(v);
					v.setControler(iControler);
					m.setControler(iControler);

					v.Show();

				} catch (Exception e) {
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

	}
}
