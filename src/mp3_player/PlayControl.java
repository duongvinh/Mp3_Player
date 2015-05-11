/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3_player;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        
        
        
    }
    
    
}
