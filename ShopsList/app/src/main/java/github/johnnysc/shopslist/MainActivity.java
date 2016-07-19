package github.johnnysc.shopslist;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Hovhannes Asatryan on 14.07.16.
 */
public class MainActivity extends AppCompatActivity {
    StringBuilder sb;
    String jsonText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = new StringBuilder();
        listView = (ListView)findViewById(R.id.shopsListView);
        ShopAdapter shopAdapter = new ShopAdapter();

        Thread thread = new Thread(new HtmlRunnable());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jsonText = sb.toString();
        fillShopList();
        sortItems();
        listView.setAdapter(shopAdapter);
    }

    private void fillShopList(){
        try {
            JSONObject jsonRootObject = new JSONObject(jsonText);
            JSONArray jsonArray = jsonRootObject.optJSONArray("data");

            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String address = jsonObject.optString("address");
                String town = jsonObject.optString("town");
                if(town.equals(""))
                    town = "Москва";
                String phone = jsonObject.optString("phone");

                try {
                    double latitude = Double.parseDouble(jsonObject.optString("latitude"));
                    double longitude = Double.parseDouble(jsonObject.optString("longtitude"));
                    double distance = getDistance(latitude, longitude);
                    ShopAdapter.shopItemArrayList.add(new ShopItem(address,town,phone,distance));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();}
    }

    private void sortItems(){
        Collections.sort(ShopAdapter.shopItemArrayList, new Comparator<ShopItem>() {
            @Override
            public int compare(ShopItem lhs, ShopItem rhs) {
                return Double.compare(lhs.getDistance(),rhs.getDistance());
            }
        });
    }

    class HtmlRunnable implements Runnable {
        public void run(){
            try {
                URL pageURL = new URL("http://app.ecco-shoes.ru/shops/list");
                URLConnection uc = pageURL.openConnection();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                uc.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private double getDistance(double latitude, double longitude) {
        double distance = 0;
        Location locationA = new Location("Moscow");
        locationA.setLatitude(55.751244);
        locationA.setLongitude(37.618423);
        Location locationB = new Location("B");
        locationB.setLatitude(latitude);
        locationB.setLongitude(longitude);
        distance = locationA.distanceTo(locationB);
        return distance;

    }
}

