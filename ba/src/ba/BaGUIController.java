package ba;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
/**
 * @author tealjapa
 * @version 24.1.2022
 *
 */
public class BaGUIController {
       
    @FXML private ListView<?> areaText;
    
    @FXML private TextArea functionText;
    
    @FXML void ehtoEka() {
        huomautus("En osaa vielä päättää!");
    }


    @FXML void ehtoToka() {
        huomautus("En osaa vielä päättää!");
    }

    
    @FXML void haku() {
        huomautus("Hakukone ei toimi vielä!");
    }

    
    @FXML void muokkaa() {
        huomautus("Muokataan, ei toimi vielä!");
    }
 //===============================================================================================
 //Omat toteutukset  
    
    private void huomautus(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Huomautus");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}