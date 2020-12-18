package cn.zhou.codedemo.gui;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class JavaFxDemo extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        /*
        HBox hBox = new HBox();
        hBox.setMaxWidth(500);
        hBox.setMaxHeight(500);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        Scene scene = new Scene( hBox);
        */

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 500, 500);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        new JavaFxDemo().start(args);
        launch(args);
    }
}
