/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3_player;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 *
 * @author Duong Vinh
 */
public class PlayControl extends AbstractView{
    
    private Image pauseImg;
    private Image playImg;
    private ImageView playpauseIcon;
    
    private StatusListener statusListener;
    private CurrentTimeListener currentTimeListener;
    
    private Node controlPanel;
    private Label statusLabel;
    private Label currentTimeLabel;
    private Label totalDurationLabel;
    private Slider volumeSlider;
    private Slider positionSlider;
    
    public  PlayControl(SongModel songModel){
        super(songModel);
        
        songModel.mediaPlayerProperty().addListener(new MediaPlayerListener());
        statusListener= new StatusListener();
        currentTimeListener= new CurrentTimeListener();
        addListenersAndBindings(songModel.getMediaPlayer());
        
             
        
        
        
    }

    @Override
    protected Node initView() {
        final Button openButton= createOpenButton();
        controlPanel= createControlPanel();
        volumeSlider= createSlider("volumeSlider");
        statusLabel=createLabel("Buffering","statusDisplay");
        positionSlider= createSlider("positionSlider");
        totalDurationLabel=createLable("00:00","mediaText");
        currentTimeLabel= createLabel("00:00","mediaText");
        
        positionSlider.valueChangingProperty().addListener(new PositionListener());
        
        final ImageView volLow= new ImageView();
        volLow.setId("volumeLow");
        
        final ImageView volHigh= new ImageView();
        volHigh.setId("volumeHigh");
        
        final GridPane gp= new GridPane();
        gp.setHgap(1);
        gp.setVgap(1);
        gp.setPadding(new Insets(10));
        
        final ColumnConstraints buttonCol= new ColumnConstraints(100);
        final ColumnConstraints spacerCol= new ColumnConstraints(40, 80, 80);
        final ColumnConstraints midleCol= new ColumnConstraints();
        midleCol.setHgrow(Priority.ALWAYS);
        
        gp.getColumnConstraints().addAll(buttonCol,spacerCol,midleCol);
        
        GridPane.setValignment(openButton, VPos.BOTTOM);
        GridPane.setHalignment(volHigh, HPos.RIGHT);
        GridPane.setValignment(volumeSlider, VPos.TOP);
        GridPane.setHalignment(statusLabel, HPos.RIGHT);
        GridPane.setValignment(statusLabel, VPos.TOP);
        GridPane.setHalignment(currentTimeLabel, HPos.RIGHT);
        
        gp.add(openButton, 0, 0,1,3);
        gp.add(volLow, 1, 0);
        gp.add(volHigh, 1, 0);
        gp.add(volumeSlider, 1, 1);
        gp.add(controlPanel, 2, 0, 1, 2);
        gp.add(statusLabel, 3, 1);
        gp.add(currentTimeLabel, 1, 2);
        gp.add(positionSlider, 2, 2);
        gp.add(totalDurationLabel, 3, 2);
        
        return gp;
        
                
        
        
        
    }

    private Button createOpenButton() {
       
    }
    
    
}
