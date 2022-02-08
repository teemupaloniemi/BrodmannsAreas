package ba;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

/**
 * @author tealjapa
 * @version 24.1.2022
 *
 */
public class BaGUIController {
       
    @FXML private ListView<?> areaText;
    
    @FXML private ListChooser<?> functionText;
    
    @FXML private MenuItem menuClose;

    @FXML private MenuItem menuDelete;

    @FXML private MenuItem menuHelp;
   
    @FXML private MenuItem menuNewArea;

    @FXML private MenuItem menuPrintArea;

    @FXML private MenuItem menuPrintSearch;

    @FXML private MenuItem menuSave;
    
    @FXML void newArea() {
        ModalController.showModal(BaGUIController.class.getResource("EditView.fxml"), "New Area", null, "");
    }

    // Alla testi tekstit tulostuksille, lopullisessa toteutuksessa totetutetaan haun avulla
    final String testiAlue = ("Brodmann's area 2                          \r\n"
                            + "    Location: Primary somatosensory cortex \r\n"
                            + "    Neighbours: 1,4,5,7,40                 \r\n"
                            + "    Functions: Localize pain               \r\n"
                            + "               Localize touch and vibration\r\n"
                            + "               Localize temperature        \r\n"
                            + "               Sense of fingers            \r\n"
                            + "               Sense of body               \r\n"
                            + "               Move hands                  \r\n"
                            + "               Move mouth and tongue       \r\n"
                            + "               Swallowing                  \r\n"
                            + "               Anticipate pain             \r\n"
                            + "               Anticipate tickling         \r\n"
                            + "               Mirror neurons                  ");

    final String testiHaku = ("Brodmann's area 1                          \r\n"  
                            + "    Location: Primary somatosensory cortex \r\n"  
                            + "    Neighbours: 2,3,5                      \r\n"  
                            + "    Functions: Localize pain               \r\n"  
                            + "               Localize touch and vibration\r\n"  
                            + "               Localize temperature        \r\n"  
                            + "               Sense of fingers            \r\n"  
                            + "               Sense of body               \r\n"  
                            + "               Move hands                  \r\n"  
                            + "               Move mouth and tongue       \r\n"  
                            + "               Swallowing                  \r\n"  
                            + "               Anticipate pain             \r\n"  
                            + "               Anticipate tickling         \r\n"  
                            + "               Mirror neurons              \r\n"
                            + "-------------------------------------------\r\n"
                            + "Brodmann's area 2                          \r\n"
                            + "    Location: Primary somatosensory cortex \r\n"
                            + "    Neighbours: 1,4,5,7,40                 \r\n"
                            + "    Functions: Localize pain               \r\n"
                            + "               Localize touch and vibration\r\n"
                            + "               Localize temperature        \r\n"
                            + "               Sense of fingers            \r\n"
                            + "               Sense of body               \r\n"
                            + "               Move hands                  \r\n"
                            + "               Move mouth and tongue       \r\n"
                            + "               Swallowing                  \r\n"
                            + "               Anticipate pain             \r\n"
                            + "               Anticipate tickling         \r\n"
                            + "               Mirror neurons              \r\n"
                            + "-------------------------------------------\r\n"
                            + "Brodmann's area 3                          \r\n"
                            + "    Location: Primary somatosensory cortex \r\n"
                            + "    Neighbours: 1,4,5                      \r\n"
                            + "    Functions: Localize pain               \r\n"
                            + "               Localize touch and vibration\r\n"
                            + "               Localize temperature        \r\n"
                            + "               Sense of fingers            \r\n"
                            + "               Sense of body               \r\n"
                            + "               Move hands                  \r\n"
                            + "               Move mouth and tongue       \r\n"
                            + "               Swallowing                  \r\n"
                            + "               Anticipate pain             \r\n"
                            + "               Anticipate tickling         \r\n"
                            + "               Mirror neurons                  ");
    
    
    //Tämä vain testinä että tulostus toimii TODO: ohjelma joka hakee tarvittavat tiedot tulostusta varten
    @FXML private void handlePrintArea() {
        PrintViewController.print(testiAlue);
    } 

    @FXML private void handlePrintSearch() {   
        PrintViewController.print(testiHaku);
    }                                        
    
    @FXML void save() {
        huomautus("Tallennetaan, ei toimi vielä!");
    }


    @FXML void close() {
        huomautus("Tallennetaan ja poistutaan, ei toimi vielä!");
    }

    @FXML void delete() {
        huomautus("poistetaan valittu alue, Ei toimi vielä!");
    }
    
    @FXML void conditionF() {
        huomautus("En osaa vielä päättää!");
    }


    @FXML void conditionL() {
        huomautus("En osaa vielä päättää!");
    }

    
    @FXML void search() {
        huomautus("Hakukone ei toimi vielä!");
    }
    
    
    @FXML void edit() {
        ModalController.showModal(BaGUIController.class.getResource("EditView.fxml"), "Edit Area", null, "");
    }
    
    @FXML void help() {
        avustus();
    }
 //===============================================================================================
 //Muut toteutukset  
    
    /**
     * Näytetään ohjelman suunnitelma erillisessä selaimessa.
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2022k/ht/tealjapa");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    
    /**
     * Tulostetaan huomautus dialogi tekstin kanssa
     * @param text tulostettava teksti
     */
    public static void huomautus(String text) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Huomautus");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}