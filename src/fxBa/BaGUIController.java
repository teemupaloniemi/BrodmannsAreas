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
        this.ba = ba;
        write();
    }
    
    
    /**
     * Alustetaan aluevalikko uusilla tiedoilla
     */
    protected void reset() {
        this.chooserAreas.clear();
        this.chooserAreas.addSelectionListener(e -> write());
    }


    /**
     * Näytetään alueen tiedot
     */
    protected void write() {
        Area selectedArea = this.chooserAreas.getSelectedObject();
        if (selectedArea == null) return;
        try {
            this.nameText.setText(selectedArea.getName());
            this.locationText.setText(ba.getLocation(selectedArea.getLid()).getName());
            var funcs = this.ba.findFunctionIDs(selectedArea.getLid());
            this.chooserFunctions.clear();
            for (int i = 0; i < funcs.size(); i++) this.chooserFunctions.add(funcs.get(i).getName(), funcs.get(i));
        } catch (IndexOutOfBoundsException e) {
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
        }
    }
    
    
   /**
    * lisäään uusi alue 
    */
    protected void newArea() {
        Area a = new Area().register().fillAreaInfo();
        try { 
            this.ba.add(a); 
        }
        catch (TilaException e) {
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
            return;
        }
        a.setLid(newLocation().getLid());
        setIndex(a.getAid());
    }
    
    
    /**
     * lisäään uusi sijainti
     * @return uuden sijainnin viitteen
     */
     protected Location newLocation() {
         Location l = new Location().register().fillLocationInfo();
         try { 
             this.ba.add(l);
         } 
         catch (TilaException e) { 
             Dialogs.showMessageDialog("Ongelmia: " + e.getMessage());
         }
         return l;
     }
     
     
     /**
      * Adding new function
      */
     public void newFunction() {
         Function f = new Function().register().fillFunctionInfo();
         this.ba.add(f);
         this.ba.add(new Lf(this.chooserAreas.getSelectedObject().getLid(),f.getFid()));
         write();
     }
     
    
    /**
     * @param Anum Alueen tunnusnumero
     */
    protected void setIndex(int Anum) {
        this.chooserAreas.clear();
        int index = 0;
        for (int i = 0; i < ba.getAreaCount(); i++) {
            Area area = ba.getArea(i);
            if (area.getAid() == Anum) index = i;
            this.chooserAreas.add(area.getName(), area);
        }
        this.chooserAreas.setSelectedIndex(index);
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