package fmrcrawler.fmrgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FMRGUIApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FMRGUIApplication.class.getResource("FMRGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1270, 1000);
        stage.setTitle("FMR Report Display");
        stage.setScene(scene);
        stage.show();
    }
}
