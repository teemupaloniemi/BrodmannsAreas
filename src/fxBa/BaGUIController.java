package fxBa;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;

import ba.Area;
import ba.Ba;
import ba.Function;
import ba.Lf;
import ba.Location;
import ba.TilaException;

/**
 * @author tealjapa
 * @version 24.1.2022
 *
 */
public class BaGUIController implements Initializable {
       
    @FXML private ListView<?> areaText;
    @FXML private ListChooser<Area> chooserAreas;
    @FXML private TextField nameText;
    @FXML private TextField locationText;
    @FXML private ListChooser<Function> chooserFunctions;
    @FXML private MenuItem menuClose;
    @FXML private MenuItem menuDelete;
    @FXML private MenuItem menuHelp;
    @FXML private MenuItem menuNewArea;
    @FXML private MenuItem menuNewFunction;
    @FXML private MenuItem menuPrintArea;
    @FXML private MenuItem menuPrintSearch;
    @FXML private MenuItem menuSave;
    
    @FXML void handleNewArea() {
        newArea();
    }
    
    
    @FXML private void handlePrintArea() {
        PrintViewController.print(kanta.Mallit.TESTI_ALUE);
    } 

    
    @FXML private void handlePrintSearch() {   
        PrintViewController.print(kanta.Mallit.TESTI_HAKU);
    }                                        
   
    
    @FXML private void handleNewFunction() {
        newFunction();
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
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        reset();      
    }

//===============================================================================================
//Muut toteutukset  
    
    private Ba ba;
    
    /**
     * @param ba luotavat aivot
     */
    public void setBa(Ba ba) {
        this.ba = ba; // luodaan uusi 
        write(); // päivitetään käyttöliittymä
    }
    
    
    /**
     * Alustetaan aluevalikko uusilla tiedoilla
     */
    protected void reset() {
        this.chooserAreas.clear(); // tyhjennetään käyttöliittymän alue lista 
        this.chooserAreas.addSelectionListener(e -> write()); // kun valitaan alue, päivitetään käyttöliittymä sen tiedoilla
    }


    /**
     * Näytetään alueen tiedot
     */
    protected void write() {
        Area selectedArea = this.chooserAreas.getSelectedObject(); // otetaan listasta valittu alue
        if (selectedArea == null) return; // jos tyhjä niin häippästään
        try {
            this.nameText.setText(selectedArea.getName()); // Valitun alueen nimi
            this.locationText.setText(ba.getLocation(selectedArea.getLid()).getName()); // Valitun alueen sijainnin nimi 
            var funcs = this.ba.findFunctionIDs(selectedArea.getLid()); // pyydetään etsimään funktiot joita sijainnilla on
            this.chooserFunctions.clear(); // tyhjennetään funktio lista käyttöliittymässä
            for (int i = 0; i < funcs.size(); i++) this.chooserFunctions.add(funcs.get(i).getName(), funcs.get(i)); // Lisätään pyydetyt funktiot käyttöliittymään
        } catch (IndexOutOfBoundsException e) { // jos ongelmia niin näytetään mitä 
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage()); // jos ei löydy
        }
    }
    
    
   /**
    * lisäään uusi alue 
    */
    protected void newArea() {
        Area a = new Area().register().fillAreaInfo(); // TODO: oikeasti käyttäjä antaa tiedot
        try { 
            this.ba.add(a); // pyydetään ba luokkaa lisäämään alue alueistoon
        }
        catch (TilaException e) { // jos ongelmia häippästään
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
            return;
        }
        a.setLid(newLocation().getLid()); // annetaan alueelle sijainti TODO: oikeasti käyttäjä antaa tämän
        setIndex(a.getAid()); // asetetaan aluelistan indeksi alueelle
    }
    
    
    /**
     * lisäään uusi sijainti
     * @return uuden sijainnin viitteen
     */
     protected Location newLocation() {
         Location l = new Location().register().fillLocationInfo(); // TODO: oikeasti käyttäjä antaa tiedot
         try { 
             this.ba.add(l); // pyydetään ba luokkaa lisäämään sijainti sijainti-listaan
         } 
         catch (TilaException e) { // jos ongelmia näyetään mitä
             Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
         }
         return l; // palautetaan luotu sijainti
     }
     
     
     /**
      * Adding new function
      */
     public void newFunction() {
         Function f = new Function().register().fillFunctionInfo(); // TODO: oikeasti käyttäjä antaa tiedot
         this.ba.add(f); // pyydetään ba luokkaa lisäämään tehtävä tehtävä-listaan
         this.ba.add(new Lf(this.chooserAreas.getSelectedObject().getLid(),f.getFid())); //luodaan uusi sijainti-tehtävä pari
         write(); // päivitetään käyttöliittymä
     }
     
    
    /**
     * @param Anum Alueen tunnusnumero
     */
    protected void setIndex(int Anum) {
        this.chooserAreas.clear(); // tyhjennetään alueiden lista käyttöliittymästä
        int index = 0; // alkuindeksi
        for (int i = 0; i < ba.getAreaCount(); i++) {  // käydään alueet läpi
            Area area = ba.getArea(i); // alue jonka kohdalla ollaan käymässä läpi 
            if (area.getAid() == Anum) index = i; // jos täsmää, alustetaan indeksi alueiston indeksin mukaan
            this.chooserAreas.add(area.getName(), area); // päivitetään käyttöliittymä
        }
        this.chooserAreas.setSelectedIndex(index); // ja hypätään listassa äsken tarkistettuun kohtaan
     }
    
    
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