package github.johnnysc.githubusers;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class MainActivity extends AppCompatActivity implements UserAdapter.UserClickListener {
    RecyclerView recyclerView;
    RestManager restManager;
    UserAdapter userAdapter;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editText = (EditText)findViewById(R.id.etUserName);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);
        restManager = new RestManager();
        getData();
    }

    private void getData(){
        Observable<CharSequence> observable = RxTextView.textChanges(editText);
        observable.subscribe(new Subscriber<CharSequence>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CharSequence charSequence) {
                Call<UsersList> listCall = restManager.getUserService().getUsersList(charSequence.toString());
                userAdapter.initialize();
                listCall.enqueue(new Callback<UsersList>(){

                    @Override
                    public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                        if(response.isSuccessful()){
                            UsersList usersList = response.body();
                            List<GithubUser> list = usersList.getUserList();
                            for(GithubUser user: list)
                                userAdapter.addUser(user);
                        }else
                            Log.d("myLog","HERE IS THE RESPONSE CODE "+response.code());
                    }

                    @Override
                    public void onFailure(Call<UsersList> call, Throwable t) {
                        Log.d("myLog","THIS is a FAILURE ! "+t.getMessage());
                    }

                });
            }
        });
    }

    @Override
    public void onClick(int position) {
        GithubUser selectedUser = userAdapter.getSelectedUser(position);
        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
        intent.putExtra("GITHUB_USER",selectedUser.getHtml_url());
        startActivity(intent);
    }
}
