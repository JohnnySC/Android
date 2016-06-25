package github.johnnysc.testappintech;

/**
 * Created by Hovhannes Asatryan on 22.06.16.
 */
public class Song {
    private String artistName;
    private String trackName;
    private String coverURL;
    private String songURL;

    public Song(String artistName, String trackName, String coverURL, String songURL) {
        this.artistName = artistName;
        this.trackName = trackName;
        this.coverURL = coverURL;
        this.songURL = songURL;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }
}
