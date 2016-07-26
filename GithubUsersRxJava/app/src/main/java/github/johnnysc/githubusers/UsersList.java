package github.johnnysc.githubusers;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class UsersList {
    @SerializedName("items")
    List<GithubUser> userList;

    public UsersList(List<GithubUser> userList) {
        this.userList = userList;
    }

    public List<GithubUser> getUserList() {
        return userList;
    }
}
