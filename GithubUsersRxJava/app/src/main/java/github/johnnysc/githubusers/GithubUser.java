package github.johnnysc.githubusers;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class GithubUser {
    @SerializedName("login")
    private String login;

    @SerializedName("html_url")
    private String html_url;

    @SerializedName("avatar_url")
    private String avatar_url;


    public GithubUser(String login, String avatar_url, String html_url) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
    }

    public String getLogin() {
        return login;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
