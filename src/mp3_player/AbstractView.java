/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3_player;

import javafx.scene.Node;

/**
 *
 * @author Duong Vinh
 */
public abstract class AbstractView {
    protected final SongModel songModel;
    protected final Node viewNode;
    
    public AbstractView(SongModel songModel){
        this.songModel=songModel;
        this.viewNode= initView();
    }

    public Node getViewNode(){
        return viewNode;
    }
    protected abstract Node initView();
        
    
    
}
