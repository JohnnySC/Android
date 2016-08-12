package github.johnnysc.testappintechretrofit2.REST;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */

@Parcel
public class Song {
    @SerializedName("artistName")
    String artistName;

    @SerializedName("trackName")
    String trackName;

    @SerializedName("artworkUrl100")
    String artWorkUrl100;

    @SerializedName("previewUrl")
    String previewUrl;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getArtWorkUrl100() {
        return artWorkUrl100;
    }

    public void setArtWorkUrl100(String artWorkUrl100) {
        this.artWorkUrl100 = artWorkUrl100;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
