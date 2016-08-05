package github.johnnysc.watches.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public class RestManager {
    private WatchService watchService;

    public WatchService getWatchService(){
        if(watchService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://watchj.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            watchService = retrofit.create(WatchService.class);
        }
        return watchService;
    }
}
