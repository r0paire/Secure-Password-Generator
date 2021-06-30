/* 
 * Author: r0paire
 * Date: 29/06/2021
 * Description: Secure Random Password Generator.
 */

package passGen;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PasswordGenerator extends Application {
	Button closeBtn;
	Slider lengthSlider;
	
	public void start(Stage stage) throws IOException {
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PassGenerator.fxml"));
		Parent root = loader.load();
		Generator gc = loader.<Generator>getController();
		gc.setData(stage);
		
		Scene scene = new Scene(root);
		stage.setTitle("Secure Password Generator");
		stage.getIcons().add(new Image(PasswordGenerator.class.getResourceAsStream("lock.png")));
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		launch(args);
	}	
}