/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3_player;

import com.sun.javafx.runtime.VersionInfo;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Duong Vinh
 */
public class Main extends Application {

    private MetadataView metaView;
    private final SongModel songModel;
    private PlayControl playControl;

    public Main() {
        songModel = new SongModel();
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("JavaFX version: " + VersionInfo.getRuntimeVersion());
        songModel.setURL("E:/M&M/nhac hay/Anh Nho Em Nguoi Yeu Cu - Minh Vuong.mp3");
        metaView = new MetadataView(songModel);
        playControl = new PlayControl(songModel);

        final BorderPane root = new BorderPane();
        root.setCenter(metaView.getViewNode());
        root.setBottom(playControl.getViewNode());

        final Scene scene = new Scene(root, 800, 400);
        initSceneDragAndDrop(scene);

        final URL styleSheet = getClass().getResource("media.css");
        scene.getStylesheets().add(styleSheet.toString());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mp3_Player");
        primaryStage.show();

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void initSceneDragAndDrop(Scene scene) {
        scene.setOnDragOver(new EventHandler<DragEvent>() {

            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles() || db.hasUrl()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
            }
        });

    }

}
