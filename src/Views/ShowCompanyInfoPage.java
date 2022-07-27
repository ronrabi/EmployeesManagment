package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ShowCompanyInfoPage implements IView {
	IControler iControler;
	Stage primaryStage;
	int counter = 0;
	VBox vBoxDetels;

	public ShowCompanyInfoPage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void Show() throws Exception {
		primaryStage.setTitle("Show Company Info");

		System.out.println(iControler.getCompany());
		Label labelTitle = new Label("Show Company Info");

		labelTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 30));
		labelTitle.setTextFill(Color.web("#5709E7"));
		Label labelNameComany = new Label("Company name: " + iControler.getCompany().getName());
		labelNameComany.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));
		Label labelNumberOfDapartment = new Label(
				"Number of dapartment: " + iControler.getCompany().getAllDepartments().size());
		labelNumberOfDapartment.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 15));

		VBox vBoxTop = new VBox();
		vBoxTop.setPadding(new Insets(10));
		vBoxTop.setAlignment(Pos.CENTER);
		vBoxTop.getChildren().addAll(labelTitle, labelNameComany, labelNumberOfDapartment);

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

		vBoxDetels = new VBox(10);

		HBox hBoxBtn = new HBox();
		hBoxBtn.setPadding(new Insets(10));
		hBoxBtn.setAlignment(Pos.CENTER);

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

		VBox vBoxfinal = new VBox(10);
		vBoxfinal.getChildren().addAll(vBoxComboBox, vBoxDetels);
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(vBoxTop);
		borderPane.setCenter(vBoxfinal);
		borderPane.setBottom(hBoxBtn);

		StackPane stackPane = new StackPane(borderPane);
		stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(stackPane, 600, 800);
		primaryStage.setScene(scene);
		primaryStage.setResizable(true);
		primaryStage.show();

		comboBoxCompany.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				vBoxDetels.getChildren().clear();
				int index = Integer.parseInt(comboBoxCompany.getValue()) - 1;
				iControler.getAllDepartments().get(index).getRoles().forEach(role -> {
					vBoxDetels.getChildren().addAll(new Label("Role: " + role.getName()));
					vBoxDetels.getChildren().addAll(new Label("Worker :"));
					ListView<String> list = new ListView<String>();
					ObservableList<String> items = FXCollections.observableArrayList();
					role.getWorkerList().forEach(worker -> {
						items.add(worker.toString());

					});
					list.setItems(items);
					list.setMaxSize(450, 200);
					vBoxDetels.setAlignment(Pos.CENTER);
					vBoxDetels.getChildren().addAll(list);

				});
			}
		});

		buttonBeack.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				beackToMenue();
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
