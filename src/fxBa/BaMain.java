package fxBa;

import ba.Ba;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author tealjapa
 * @version 24.1.2022
 *
 */
public class BaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("BaGUIView.fxml"));
            final Pane root = ldr.load();
            final BaGUIController baCtrl = (BaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            
            //scene.getStylesheets().add(getClass().getResource("fxBa.css").toExternalForm());
            
            primaryStage.setScene(scene);
            primaryStage.setTitle("ba");
            
            Ba ba = new Ba();
            baCtrl.setBa(ba);
            
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

   
    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}