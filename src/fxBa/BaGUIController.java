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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kanta.CheckArea;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;

import ba.Area;
import ba.Ba;
import ba.Function;
import ba.Lf;
import ba.Location;
import ba.Neighbour;
import ba.TilaException;

/**
 * @author tealjapa
 * @version 24.1.2022
 *
 */
public class BaGUIController implements Initializable {
    
    // Nämä vain apuna jotta näkee mitä tietoja olemassa
    @FXML private TextArea functionDat;
    @FXML private TextArea lfDat;
    @FXML private TextArea areaDat;
    @FXML private TextArea locationDat;
    @FXML private TextArea neighbourDat;
    //========================================//
       
    @FXML private ListView<?> areaText;
    @FXML private ListChooser<Area> chooserAreas;
    @FXML private ListChooser<Function> chooserFunctions;
    @FXML private ListChooser<Area> chooserNeighbours;
    @FXML private ListChooser<Function> chooserFunctionsN;
    @FXML private TextField nameText;
    @FXML private TextField locationText;
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
    
    
    @FXML void handleNewNeighbour() {
        newNeighbour();
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
        search(0);
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
     * @param ba luodaan Brodmannin alueet kanta
     */
    public void setBa(Ba ba) {
        this.ba = ba; // luodaan uusi 
        this.readFile(); // haetaan tiedot 
        this.taytaApukentat();
        this.search(0);
        this.write(); // päivitetään käyttöliittymä
    }

    
    private void clearAll() {
        this.areaDat.clear();
        this.locationDat.clear();
        this.functionDat.clear();
        this.lfDat.clear();
        this.neighbourDat.clear();
    }
    
    private void taytaApukentat() {
        this.clearAll();
        
        StringBuilder sb = new StringBuilder();
        String s = System.lineSeparator();
        
        for (int i = 0; i < this.ba.getAreaCount(); i++) 
            sb.append(this.ba.getArea(i).toString() + s);
        this.areaDat.setText(sb.toString());
        sb.setLength(0);
        
        for (int i = 0; i < this.ba.getLocationCount(); i++) 
            sb.append(this.ba.getLocation(i).toString() + s);
        this.locationDat.setText(sb.toString());
        sb.setLength(0);
        
        for (int i = 0; i < this.ba.getFunctionCount(); i++) 
            sb.append(this.ba.getFunction(i).toString() + s);
        this.functionDat.setText(sb.toString());
        sb.setLength(0);
        
        for (int i = 0; i < this.ba.getLfCount(); i++) 
            sb.append(this.ba.getLf(i).toString() + s);
        this.lfDat.setText(sb.toString());
        sb.setLength(0);
        
        for (int i = 0; i < this.ba.getNeighbourCount(); i++) 
            sb.append(this.ba.getNeighbour(i).toString() + s);
        this.neighbourDat.setText(sb.toString());
        sb.setLength(0);
        
        System.gc();
    }
    
    
    private void readFile() {
        try {
            this.ba.readFile();
        } catch (TilaException e) {
            Dialogs.showMessageDialog(e.getMessage());
        }
    }

    
    /**
     * Alustetaan aluevalikko uusilla tiedoilla
     */
    protected void reset() {
     // tyhjennetään käyttöliittymän lista 
        this.chooserAreas.clear(); 
        this.chooserAreas.addSelectionListener(e -> write()); // kun valitaan alue, päivitetään käyttöliittymä sen tiedoilla
    }


    /**
     * Näytetään alueen tiedot
     */
    protected void write() {
        Area selectedArea = this.chooserAreas.getSelectedObject(); // otetaan listasta valittu alue
        if (selectedArea == null) return; // jos tyhjä niin häippästään
        try {
            // Valitun alueen nimi
            this.nameText.setText(selectedArea.getName());
            // Valitun alueen sijainnin nimi
            this.locationText.setText(ba.getLocation(selectedArea.getLid()).getName()); 
            // hoidellaan tehtävät
            var funcs = this.ba.findFunctions(selectedArea.getLid()); // pyydetään etsimään funktiot joita sijainnilla on
            this.chooserFunctions.clear(); // tyhjennetään funktio lista käyttöliittymässä
            for (int i = 0; i < funcs.size(); i++) this.chooserFunctions.add(funcs.get(i).getName(), funcs.get(i)); // Lisätään pyydetyt funktiot käyttöliittymään
            // hoidellaan naapurit
            var neighbours = this.ba.findNeighbours(selectedArea.getAid()); // pyydetään etsimään funktiot joita sijainnilla on
            this.chooserNeighbours.clear(); // tyhjennetään naapuri lista käyttöliittymässä
            for (int i = 0; i < neighbours.size(); i++) this.chooserNeighbours.add(neighbours.get(i).getName(), neighbours.get(i)); // Lisätään pyydetyt funktiot käyttöliittymään
            // Naaupurin tehtävät
            Area n = this.chooserNeighbours.getSelectedObject();
            if (n != null) { // jos on olemassa naapuri
                var funcsN = this.ba.findFunctions(n.getLid()); // pyydetään etsimään funktiot joita sijainnilla on
                this.chooserFunctionsN.clear(); // tyhjennetään funktio lista käyttöliittymässä
                for (int i = 0; i < funcsN.size(); i++) this.chooserFunctionsN.add(funcsN.get(i).getName(), funcsN.get(i)); // Lisätään pyydetyt funktiot käyttöliittymään
            }
        } catch (IndexOutOfBoundsException e) { // jos ongelmia niin näytetään mitä 
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage()); // jos ei löydy
        }
    }
    
    
   /**
    * lisäään uusi alue 
    */
    protected void newArea() {
        newLocation();
        Area a = new Area().register().fillAreaInfo().setLid(CheckArea.rand(0, this.ba.getLocationCount()-1)); // TODO: oikeasti käyttäjä antaa tiedot
        this.ba.add(a); // pyydetään ba luokkaa lisäämään alue alueistoon
        setIndexForAreas(a.getAid()); // asetetaan aluelistan indeksi alueelle
    }
    
    
    /**
     * lisäään uusi sijainti
     * @return uuden sijainnin viitteen
     */
     protected Location newLocation() {
         Location l = new Location().register().fillLocationInfo(); // TODO: oikeasti käyttäjä antaa tiedot
         this.ba.add(l); // pyydetään ba luokkaa lisäämään sijainti sijainti-listaan 
         return l; // palautetaan luotu sijainti
     }
     
     
     /**
      * Adding new function
      */
     public void newFunction() {
         if (this.chooserAreas.getSelectedObject() == null) { // jos yritetään lisätä ennen kuin alueita on olemassa tai niitä ei ole valittu
             huomautus("Lisää tai valitse alue johon tehtävä voidaan lisätä!"); // näytetään huomautus dialigi jossa teksti
             return; // poistutaan 
         }
         Function f = new Function().register().fillFunctionInfo(); // TODO: oikeasti käyttäjä antaa tiedot
         this.ba.add(f); // pyydetään ba luokkaa lisäämään tehtävä tehtävä-listaan
         try {
            this.ba.add(new Lf(this.chooserAreas.getSelectedObject().getLid(),f.getFid()));
        } catch (TilaException e) {
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage()); // jos olemassa oleva pari
            return;
        } //luodaan uusi sijainti-tehtävä pari
         this.chooserFunctions.add(f.getName(), f);
     }
     
     
     
     /**
      * Adding new Neighbour
      */
     public void newNeighbour() {
         Area selectedArea = this.chooserAreas.getSelectedObject();
         if (selectedArea == null) { // jos yritetään lisätä ennen kuin alueita on olemassa tai niitä ei ole valittu
             huomautus("Lisää tai valitse alue jolle naapuri voidaan lisätä!"); // näytetään huomautus dialigi jossa teksti
             return; // poistutaan 
         }
         Area newNeighbour = ba.getArea(CheckArea.rand(0, ba.getAreaCount()-1)); // TODO: oikesti käyttäjä lisää tämän
         try {
            this.ba.add(new Neighbour(selectedArea.getAid(), newNeighbour.getAid())); // luodaan uusi naapuri pari
        } catch (TilaException e) {
            Dialogs.showMessageDialog("Ongelmia: " + e.getMessage()); // jos samat tai jo olemassa 
            return;
        } 
        this.chooserNeighbours.add(newNeighbour.getName(), newNeighbour); // päivitetään käyttöliittymä
     }
     
    
    /**
     * @param Anum Alueen tunnusnumero
     */
    protected void setIndexForAreas(int Anum) {
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
     * tallennetaan kaikki tallennettavissa olevat tiedot,
     * muokattava tai lisättävä alue hyppää listaan
     * @return jos ongelmia palautetaan mikä meni pieleen
     */
    public String save() {
        try {
            this.ba.saveAll("tiedostot");
            this.taytaApukentat();
            return null;
        } catch (TilaException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
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
     * @param searchID id hakunumero
     */
    public void search(int searchID) {        
        this.chooserAreas.clear();
        int index = 0;
        for (int i = 0; i < this.ba.getAreaCount(); i++) {
            Area area = this.ba.getArea(i);
            if (area.getAid() == searchID) index = i;
            this.chooserAreas.add(area.getName(), area);
        }
        this.chooserAreas.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää jäsenen
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