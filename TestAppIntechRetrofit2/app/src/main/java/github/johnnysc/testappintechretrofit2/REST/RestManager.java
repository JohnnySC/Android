package github.johnnysc.testappintechretrofit2.REST;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public class RestManager {
    private SongService songService;

    public SongService getSongService() {
        if (songService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            songService = retrofit.create(SongService.class);
        }
        return songService;
    }
}
