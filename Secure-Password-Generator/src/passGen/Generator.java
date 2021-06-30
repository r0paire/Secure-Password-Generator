/* 
 * Author: r0paire
 * Date: 29/06/2021
 * Description: Secure Random Password Generator.
 */

package passGen;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Generator implements Initializable {
	
	@FXML
	int passwordLength;
	
	@FXML
	Button closeBtn, clearBtn, generateBtn;
	
	@FXML
	TextArea passwordBox;
	
	@FXML
	Slider lengthSlider;
	
	@FXML
	CheckBox cbNumeric;
	@FXML
	CheckBox cbAlphabetic;
	@FXML
	CheckBox cbSymbolic;

	@FXML
	Label lblPasswordLength;
	
	@FXML
	Stage stage;

	@SuppressWarnings(value = "unchecked")
	@FXML
	public void initialize(URL location, ResourceBundle resources) {
		
		//close button
		closeBtn.setOnAction(new EventHandler() {
			public void handle(Event event) {
				stage.close();
			}
		});
		
		// Listener for Slider adjustments
		lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {lblPasswordLength.setText(Integer.toString(newValue.intValue())); int passwordLength1 = (int)newValue.intValue();});
		
		// slider length
		int sliderLength = (int) lengthSlider.getValue();
		
		// Slider colour
		lengthSlider.styleProperty().bind(Bindings.createStringBinding(() -> {
			double percentage = (lengthSlider.getValue() - lengthSlider.getMin()) / (lengthSlider.getMax() - lengthSlider.getMin()) * 100.0 ;
		    return String.format("-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
		                + "-slider-filled-track-color %f%%, -fx-base %f%%, -fx-base 100%%);", percentage, percentage);
		    }, lengthSlider.valueProperty(), lengthSlider.minProperty(), lengthSlider.maxProperty()));
		
		//clear button - clears password field
		clearBtn.setOnAction(new EventHandler() {
			public void handle(Event event) {
				passwordBox.clear();
			}
			});
		
		// Letters checkbox - enables letters in password generation.
		cbAlphabetic.setOnAction(new EventHandler() {
			public void handle(Event event) {
				
				boolean isSel = cbAlphabetic.isSelected();
				if (isSel == true)
					cbAlphabetic.isSelected();
				else
					cbAlphabetic.setSelected(false);
				}
			});
		
		// Numbers checkbox - enables numbers in password generation.
		cbNumeric.setOnAction(new EventHandler() {
			public void handle(Event event) {
				boolean isSel = cbNumeric.isSelected();
				if (isSel == true)
					cbNumeric.isSelected();
				else
					cbNumeric.setSelected(false);
				}
			});
		
		// Symbols checkbox - enables symbols in password generation
		cbSymbolic.setOnAction(new EventHandler() {
			public void handle(Event event) {
				boolean isSel = cbSymbolic.isSelected();
				if (isSel == true)
					cbSymbolic.isSelected();
				else
					cbSymbolic.setSelected(false);
				}
			});
		
		// Generate button for password generation
		generateBtn.setOnAction(new EventHandler() {
			public void handle(Event event) {
				// Alphabetic
				if (cbAlphabetic.isSelected() && !cbNumeric.isSelected() && !cbSymbolic.isSelected()) {
					passwordBox.setText(Generator.generateAPassword((int) lengthSlider.getValue()));
				    }
				//Numeric
				else if (!cbAlphabetic.isSelected() && cbNumeric.isSelected() && !cbSymbolic.isSelected()) {
					passwordBox.setText(Generator.generateNPassword((int) lengthSlider.getValue()));
				}
				// Symbolic
				else if (!cbAlphabetic.isSelected() && !cbNumeric.isSelected() && cbSymbolic.isSelected()) {
					passwordBox.setText(Generator.generateSPassword((int) lengthSlider.getValue()));
				}
				// Alphanumeric
				else if (cbAlphabetic.isSelected() && cbNumeric.isSelected() && !cbSymbolic.isSelected()) {
					passwordBox.setText(Generator.generateANPassword((int) lengthSlider.getValue()));
				}
				// Alphanumeric & Symbolic
				else if (cbAlphabetic.isSelected() && cbNumeric.isSelected() && cbSymbolic.isSelected()) {
					passwordBox.setText(Generator.generateANSPassword((int) lengthSlider.getValue()));
				}
				// Alphabetic and Symbolic
				else if (cbAlphabetic.isSelected() && !cbNumeric.isSelected() && cbSymbolic.isSelected()) {
					passwordBox.setText(Generator.generateASPassword((int) lengthSlider.getValue()));
				}
				// Numeric & Symbolic
				else if (!cbAlphabetic.isSelected() && cbNumeric.isSelected() && cbSymbolic.isSelected()) {
					passwordBox.setText(Generator.generateNSPassword((int) lengthSlider.getValue()));
				}
				else {
				passwordBox.setText("Password must contain characters.");
				}
			}
		});
	}
	
	// Method for generating an alphabetic password with a specified length
    public static String generateAPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        	{
        	     int randomIndex = random.nextInt(chars.length());
        	     sb.append(chars.charAt(randomIndex));
        	}
        return sb.toString();
	}
	
    // Method for generating a numeric password with a specified length
    public static String generateNPassword(int len)
    {
        final String chars = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        	{
        	     int randomIndex = random.nextInt(chars.length());
        	     sb.append(chars.charAt(randomIndex));
        	}
        return sb.toString();
	}
    
    // Method for generating an symbolic password with a specified length
    public static String generateSPassword(int len)
    {
        final String chars = ".;/$%^&*()[]{}#\\\\<>,?!@:'-_=+~\"";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        	{
        	     int randomIndex = random.nextInt(chars.length());
        	     sb.append(chars.charAt(randomIndex));
        	}
        return sb.toString();
	}
	
	// Method for generating an alphanumeric password with a specified length
    public static String generateANPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        
        for (int i = 0; i < len; i++)
        	{
        		int randomIndex = random.nextInt(chars.length());
        	    sb.append(chars.charAt(randomIndex));
        	}
        return sb.toString();
	}
    
    // Method for generating an alphanumeric and symbolic password with a specified length
    public static String generateANSPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.;/$%^&*()[]{}#\\<>,?!@:'-_=+~\"";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        	{
        		int randomIndex = random.nextInt(chars.length());
        	    sb.append(chars.charAt(randomIndex));
        	}
        return sb.toString();
	}
    
    // Method for generating an alphabetic and symbolic password with a specified length
    public static String generateASPassword(int len)
    {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.;/$%^&*()[]{}#\\\\<>,?!@:'-_=+~\"";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        	{
        	     int randomIndex = random.nextInt(chars.length());
        	     sb.append(chars.charAt(randomIndex));
        	}
        return sb.toString();
	}
    
    // Method for generating an numeric and symbolic password with a specified length
    public static String generateNSPassword(int len)
    {
        final String chars = "0123456789.;/$%^&*()[]{}#\\\\<>,?!@:'-_=+~\"";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < len; i++)
        	{
        	     int randomIndex = random.nextInt(chars.length());
        	     sb.append(chars.charAt(randomIndex));
        	}
        return sb.toString();
	}
	
	@FXML
	// sets necessary data for the password Generator
	//called when loading passGenerator.fxml
	public void setData (Stage stage) {
		this.stage = stage;	
	}
}	