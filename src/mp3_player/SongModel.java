/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3_player;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.MapChangeListener;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Duong Vinh
 */
public final class SongModel {

    private static final String DEFAULT_IMG_URL = SongModel.class.getResourceAsStream("resources/defaultAlbum.png").toString();
    private static final Image DEFAULT_ALBUM_COVER = new Image(DEFAULT_IMG_URL.toString());

    private final StringProperty album = new SimpleStringProperty(this, "album");
    private final StringProperty artist = new SimpleStringProperty(this, "artist");
    private final StringProperty title = new SimpleStringProperty(this, "title");
    private final StringProperty year = new SimpleStringProperty(this, "year");

    private final ObjectProperty<Image> albumCover = new SimpleObjectProperty<Image>(this, "albumCover");
    private final ReadOnlyObjectWrapper<MediaPlayer> mediaPlayer = new ReadOnlyObjectWrapper<MediaPlayer>(this, "mediaPlayer");

    public SongModel() {
        resetProperties();
    }

    public void setURL(String URL) {
        if (mediaPlayer.get() != null) {
            mediaPlayer.get().stop();
        }
        initilizeMedia(URL);
    }

    public String getAlbum() {
        return album.get();
    }

    public void setAlbum(String value) {
        album.setValue(value);
    }

    public StringProperty albumProperty() {
        return album;
    }

    public String getArtist() {
        return artist.get();
    }

    public void setArtist(String value) {
        artist.setValue(value);
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.setValue(value);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getYear() {
        return year.get();
    }

    public void setYear(String value) {
        year.setValue(value);
    }

    public StringProperty yearProperty() {
        return year;
    }

    public Image getAlbumCover() {
        return albumCover.get();
    }

    public void setAlbumCover(Image value) {
        albumCover.set(value);
    }

    public ObjectProperty<Image> albumCoverProperty() {
        return albumCover;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer.get();
    }

    public ReadOnlyObjectProperty<MediaPlayer> mediaPlayerProperty() {
        return mediaPlayer.getReadOnlyProperty();
    }

    private void resetProperties() {
        setArtist("");
        setTitle("");
        setYear("");
        setAlbum("");
        setAlbumCover(DEFAULT_ALBUM_COVER);
    }

    private void initilizeMedia(String URL) {
        resetProperties();

        try {
            final Media media = new Media(URL);
            media.getMetadata().addListener(new MapChangeListener<String, Object>() {

                @Override
                public void onChanged(MapChangeListener.Change<? extends String, ? extends Object> change) {
                    if (change.wasAdded()) {
                        handleMetadata(change.getKey(), change.getValueAdded());
                    }
                }
            });
            mediaPlayer.setValue(new MediaPlayer(media));
            mediaPlayer.get().setOnError(new Runnable() {

                @Override
                public void run() {
                   String errorMessage=mediaPlayer.get().getError().getMessage();
                    System.err.println("Mediaplayer error :"+errorMessage );
                }
            });
            

        } catch (RuntimeException re) {
            System.err.println("Caught exception: " + re.getMessage());
        }
    }

    private void handleMetadata(String key, Object value) {

        if(key.equals("album")){
            setAlbum(value.toString());
        } else if(key.equals("artist")){
            setArtist(value.toString());
        } else if(key.equals("title")){
            setTitle(value.toString());
        } else if(key.equals("year")){
            setTitle(value.toString());
        } else if(key.equals("image")){
            setAlbumCover((Image) value);
        }
    }

}
