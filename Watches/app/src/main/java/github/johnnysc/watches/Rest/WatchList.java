package github.johnnysc.watches.Rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public class WatchList {
    @SerializedName("data")
    List<Watch> watchesList;

    public WatchList(List<Watch> watches){
        this.watchesList = watches;
    }

    public List<Watch> getWatchesList(){
        return watchesList;
    }

}
