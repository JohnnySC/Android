package github.johnnysc.shopslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class MainActivity extends AppCompatActivity {
    ListView listView;
    ShopAdapter shopAdapter;
    RestManager restManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.shopsListView);
        shopAdapter = new ShopAdapter();
        restManager = new RestManager();
        fillShopList();
        listView.setAdapter(shopAdapter);
    }

    private void fillShopList(){
        Call<Shops> shopsCall = restManager.getShopService().getAllShops();
        shopsCall.enqueue(new Callback<Shops>() {
            @Override
            public void onResponse(Call<Shops> call, Response<Shops> response) {
                if(response.isSuccessful()){
                    Shops shopsList = response.body();
                    List<ShopItem> list = shopsList.getShopsList();
                    for(ShopItem item: list)
                        shopAdapter.add(item);
                }else
                    Toast.makeText(getApplicationContext(),"Response code is "+response.code(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Shops> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failure! "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

