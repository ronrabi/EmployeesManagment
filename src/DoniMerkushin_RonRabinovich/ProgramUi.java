package DoniMerkushin_RonRabinovich;

import IMVC.IControler;
import IMVC.IModel;
import IMVC.IView;
import Model.Model;
import Views.RevenueCalculatorPage;
import Views.SetCompanyNamePage;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Optional;

import Controler.Controler;

public class ProgramUi extends Application implements Serializable {
	@Override
	public void start(Stage primaryStage) throws Exception {
		IView v = new SetCompanyNamePage(primaryStage);
		IModel m = new Model();
		IControler iControler = new Controler();
		iControler.setModel(m);
		iControler.setView(v);
		v.setControler(iControler);
		m.setControler(iControler);

		Alert alert = new Alert(Alert.AlertType.NONE);
		alert.setContentText("do you want to read the file?");
		ButtonType buttonTypeOne = new ButtonType("yes");
		ButtonType buttonTypeTwo = new ButtonType("no");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			try {
				iControler.readFromFile();
				createMassegeAlert("read from file successfully ");
				v = new RevenueCalculatorPage(primaryStage);
				iControler.setModel(m);
				iControler.setView(v);
				v.setControler(iControler);
				m.setControler(iControler);
				v.Show();

			} catch (Exception e) {

				createMassegeAlert("not existing Saved file, start new Company");

				v = new SetCompanyNamePage(primaryStage);
				iControler.setModel(m);
				iControler.setView(v);
				v.setControler(iControler);
				m.setControler(iControler);
				v.Show();

			}
		} else {
			v = new SetCompanyNamePage(primaryStage);
			iControler.setModel(m);
			iControler.setView(v);
			v.setControler(iControler);
			m.setControler(iControler);

			v.Show();
		}
	}

	public void createMassegeAlert(String s) {
		Alert alertEroro = new Alert(Alert.AlertType.INFORMATION);
		alertEroro.setHeaderText(null);
		alertEroro.setContentText(s);
		alertEroro.showAndWait();
	}

}
