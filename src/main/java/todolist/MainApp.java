package todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class MainApp extends Application{
    @Override
    public void start(Stage stage){
         
         try {
            try {
                
                AppUIController.Loadtask();

            } catch (Exception e) {
                // todo: handle exception
            }
            Parent root = FXMLLoader.load(getClass().getResource("AppUI.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("OnTrack");
            stage.setScene(scene);
            stage.show();
         } catch (Exception e) {
            //System.err.println("im here");  
            e.printStackTrace();
         }
    }

    public static void main(String[] args) {
        launch(args);
    }
} 