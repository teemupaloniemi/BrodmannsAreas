package ba;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Teemu
 * @version 18.2.2022
 *
 */
public class AddFunctionController implements ModalControllerInterface<String> {
    
    @FXML private TextField newFunctionText;
    
    
    @FXML private void handleOK() {
        newFunction();
        ModalController.closeStage(newFunctionText);
    }
    
    
    @FXML private void handleCancel() {
        ModalController.closeStage(newFunctionText);
    }

    
    @FXML private void print() {
        Dialogs.showMessageDialog("Ei osata tallentaa tietoja!");
    }

    
    @Override
    public String getResult() {
        return null;
    } 

    
    @Override
    public void setDefault(String oletus) {
        //
    }
    
    
    @Override
    public void handleShown() {
        //
    }

    
    /**
     * Funktion lisäys dialogi
     */
    public static void addFunction() {
        ModalController.showModal(AddFunctionController.class.getResource("AddFunctionView.fxml"),
                "Function", null, "");
    }
    

    /**
     * Lisätään uusi funktio tiedostoon 
     */
    @FXML public static void newFunction() {
        ba.BaGUIController.huomautus("Ei osata lisätä vielä!");
    }

}
