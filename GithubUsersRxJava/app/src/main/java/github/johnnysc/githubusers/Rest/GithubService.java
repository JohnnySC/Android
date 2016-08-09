package github.johnnysc.githubusers.Rest;

import github.johnnysc.githubusers.Model.UsersList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public interface GithubService {
    @GET("search/users")
    Call<UsersList> getUsersList(@Query("q") String username);
}