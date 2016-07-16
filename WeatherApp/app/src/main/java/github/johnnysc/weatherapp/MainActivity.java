package github.johnnysc.weatherapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity {
    String url;
    StringBuilder sb;
    String text=null;
    TextView txtView;
    EditText editText;
    Button button;
    ImageView iconImageView;
    String icon;
    Drawable drawableImage;
    WebView mapWebView;
    String longitude,latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.etCity);
        button = (Button)findViewById(R.id.getWeatherButton);
        txtView = (TextView)findViewById(R.id.infoTxtView);
        iconImageView = (ImageView)findViewById(R.id.iconImageView);

        mapWebView = (WebView)findViewById(R.id.mapWebView);
        mapWebView.getSettings().setJavaScriptEnabled(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sb = new StringBuilder();
                if(editText.getText().toString().length()>0)
                getURL(editText.getText().toString());
                method();
                mapWebView.loadUrl("https://www.google.ru/maps/place//@"+latitude + "," + longitude+",12z/data=!4m5!3m4!1s0x0:0x0!8m2!3d55.744063!4d37.614619");
            }
        });
    }

    private void method(){
        Thread thread = new Thread(new HtmlRunnable());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        text = "";
        text = sb.toString();
        getData();
        Thread thread2 = new Thread(new DrawableRunnable());
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iconImageView.setImageDrawable(drawableImage);
        txtView.setText(text);
    }

    private void getURL(String city){
        url= "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=aea3d600dac059de4a27a254928c74b7&mode=json&units=metric";
    }

    private void getData(){
        try {
            JSONObject jsonRootObject = new JSONObject(text);
            JSONObject jsonObject = jsonRootObject.optJSONObject("main");

                String temperature = jsonObject.optString("temp");
                String temp_min = jsonObject.optString("temp_min");
                String temp_max = jsonObject.optString("temp_max");
                double pressureMM = jsonObject.optInt("pressure")*0.75;
                String humidity = jsonObject.optString("humidity");
                text=" Температура: "+temperature+" °C"+
                        "\n Минимальная: "+temp_min+" °C"+
                        "\n Максимальная: "+temp_max+" °C"+
                        "\n Давление: "+pressureMM+" мм.рт.ст"+
                        "\n Влажность: "+humidity+" %";

            jsonObject = jsonRootObject.optJSONObject("wind");
            String windSpeed = jsonObject.optString("speed");
            text+="\n Скорость ветра: "+windSpeed+" м/с";

            jsonObject = jsonRootObject.optJSONObject("coord");
            longitude = jsonObject.optString("lon");
            latitude = jsonObject.optString("lat");

            JSONArray jsonArray = jsonRootObject.getJSONArray("weather");
            jsonObject = jsonArray.optJSONObject(0);
            icon = jsonObject.getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();}
    }

    class DrawableRunnable implements Runnable{
        public void run(){
            setImage(icon);
        }
    }

    private void setImage(String icon){
        String iconURL = "http://openweathermap.org/img/w/"+icon+".png";
        try {
            InputStream is = (InputStream) new URL(iconURL).getContent();
            drawableImage = Drawable.createFromStream(is, "src name");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class HtmlRunnable implements Runnable {
        public void run(){
            try {
                URL pageURL = new URL(url);
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
}
