package github.johnnysc.githubusers.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hovhannes Asatryqn on 22.07.16.
 */
public class RestManager {
    private GithubService userService;

    public GithubService getUserService(){
        if(userService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            userService = retrofit.create(GithubService.class);
        }
        return userService;
    }
}
