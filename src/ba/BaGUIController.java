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
    
    @FXML void handleNewArea() {
        ModalController.showModal(BaGUIController.class.getResource("EditView.fxml"), "New Area", null, "");
    }

    // Nämä ovat malli tekstit tulostuksille, lopullisessa toteutuksessa haetaan tiedostoista
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
   
    
    @FXML void handleSave() {
        save();
    }


    @FXML void handleClose() {
        close();
    }


    @FXML void handleDelete() {
        delete();
    }
    

    @FXML void handleConditionF() {
        conditionF();
    }

    
    @FXML void handleConditionL() {
        conditionL();
    }

    
    @FXML void handleSearch() {
        search();
    }
    
    
    @FXML void handleEdit() {
        edit();
    }
   

    @FXML void handleHelp() {
        help();
    }
//===============================================================================================
 //Muut toteutukset  
    
    /**
     * tuhotaan valittu alue
     */
    public void delete() {
        huomautus("poistetaan valittu alue, Ei toimi vielä!");
    }
    
    
    /**
     * valitaan hakuehdoksi funktio
     */
    public void conditionF() {
        huomautus("En osaa vielä päättää!");
    }
    
    
    /**
     * valitaan hakuehdoksi sijainti
     */
    public void conditionL() {
        huomautus("En osaa vielä päättää!");
    }
    
    
    /**
     * haetaan kauehdon ja hakuentän tiedoilla sopivat tiedot
     */
    public void search() {
        huomautus("Hakukone ei toimi vielä!");
    }
    
    
    /**
     * muokataan valittua aluetta
     */
    public void edit() {
        ModalController.showModal(BaGUIController.class.getResource("EditView.fxml"), "Edit Area", null, "");
    }
    
    
    /**
     * Näytetään ohjelman suunnitelma selaimessa.
     */
    public void help() {
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
     * tallennetaan ja poistutaan sovelluksesta
     */
    public void close() {
        huomautus("Tallennetaan ja poistutaan, ei toimi vielä!");
    }
    

    /**
     * tallennetaan kaikki tallennettavissa olevat tiedot,
     * muokattava tai lisättävä alue hyppää listaan
     */
    public void save() {
        huomautus("Tallennetaan, ei toimi vielä!");
    }
    
    
    /**
     * Tulostetaan huomautus-dialogi halutun tekstin kanssa
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