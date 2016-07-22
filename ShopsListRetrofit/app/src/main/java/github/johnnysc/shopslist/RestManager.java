package github.johnnysc.shopslist;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class RestManager {
    public final String BASE_URL = "http://app.ecco-shoes.ru/";
    private ShopService shopService;

    public ShopService getShopService(){
        if(shopService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            shopService = retrofit.create(ShopService.class);
        }
        return shopService;
    }
}
