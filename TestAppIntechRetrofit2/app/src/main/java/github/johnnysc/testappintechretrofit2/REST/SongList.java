package github.johnnysc.testappintechretrofit2.REST;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public class SongList {
    @SerializedName("results")
    List<Song> songsList;

    public SongList(List<Song> songs){
        this.songsList = songs;
    }

    public List<Song> getSongsList(){
        return songsList;
    }
}
