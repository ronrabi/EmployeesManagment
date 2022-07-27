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

public class CalculatorRevenuePage implements IView {
	Stage primaryStage;
	IControler iControler;
	VBox vBoxProfit;

	public CalculatorRevenuePage(Stage Pstage) {
		primaryStage = Pstage;
	}

	@Override
	public void Show() throws Exception {
		primaryStage.setTitle("Calculator Revenue");
		BorderPane bpRoot = new BorderPane();
		vBoxProfit = new VBox(10);
		vBoxProfit.setPadding(new Insets(10));

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.setAlignment(Pos.CENTER);

		Label labelTitle = new Label("Calculator Revenue");

		labelTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 50));
		labelTitle.setTextFill(Color.web("#5709E7"));
		vBox.getChildren().addAll(labelTitle);

		Label selectCompanyLabel = new Label("Select Profit to Show :");
		selectCompanyLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));

		ComboBox<String> comboBoxCompany = new ComboBox<String>();

		comboBoxCompany.getItems().addAll("Company", "Departments", "Worker");

		VBox vBoxComboBox = new VBox(10);
		vBoxComboBox.setPadding(new Insets(10));
		vBoxComboBox.getChildren().addAll(selectCompanyLabel, comboBoxCompany);

		HBox hBoxBtn = new HBox();
		hBoxBtn.setPadding(new Insets(10));
		hBoxBtn.setAlignment(Pos.BOTTOM_CENTER);

		Button buttonBeack = new Button("Back");
		buttonBeack
				.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));

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
		hBoxBtn.getChildren().addAll(buttonBeack);

		VBox finalVbox = new VBox(vBoxComboBox, vBoxProfit);
		finalVbox.setSpacing(20);
		finalVbox.getChildren().addAll();
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		borderPane.setTop(vBox);
		borderPane.setBottom(hBoxBtn);
		borderPane.setCenter(finalVbox);
		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(stackPane, 500, 600);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
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
				if (comboBoxCompany.getValue().contains("Company")) {
					vBoxProfit.getChildren().clear();
					iControler.getCompany().updateProfit();

					Label labelTytleProfit = new Label("Company Profit :");
					labelTytleProfit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
					labelTytleProfit.setTextFill(Color.web("#5709E7"));

					Label labelProfit = new Label("" + iControler.getCompany().getProfit());
					labelProfit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
					vBoxProfit.getChildren().addAll(labelTytleProfit, labelProfit);

				} else if (comboBoxCompany.getValue().contains("Departments")) {
					vBoxProfit.getChildren().clear();
					Label labelTytleProfit = new Label("Departments Profit :\n");
					labelTytleProfit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
					labelTytleProfit.setTextFill(Color.web("#5709E7"));

					Label labelProfit = new Label(iControler.printProfitDapartment());
					labelProfit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
					vBoxProfit.getChildren().addAll(labelTytleProfit, labelProfit);

				} else if (comboBoxCompany.getValue().contains("Worker")) {
					vBoxProfit.getChildren().clear();
					Label labelTytleProfit = new Label("Worker Profit :\n");
					labelTytleProfit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
					labelTytleProfit.setTextFill(Color.web("#5709E7"));

					Label labelProfit = new Label(iControler.printProfitWorker());
					labelProfit.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
					vBoxProfit.getChildren().addAll(labelTytleProfit, labelProfit);

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
