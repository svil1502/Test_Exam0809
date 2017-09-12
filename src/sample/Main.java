package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import javafx.stage.*;

public class Main extends Application {

    public static Object showPersonEditDialog;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("ProC");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static boolean showPersonEditDialog() {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("subject.fxml"));
            AnchorPane page2 = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Справочник предметов");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Window primaryStage = null;
            dialogStage.initOwner(primaryStage);
            Scene scene2 = new Scene(page2);
            dialogStage.setScene(scene2);

            // Передаём адресата в контроллер.
            // ControllerSubject controller2 = loader.getController();
            // controller2.setDialogStage(dialogStage);
            // controller2.setSubj(subject);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
          //  dialogStage.showAndWait();

            //  return controller2.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
    public static void main(String[] args) {
        launch(args);
    }

   // public static void showPersonEditDialog(void aVoid) {
   // }
}
