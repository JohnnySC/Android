package github.johnnysc.jsoupexample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textView) TextView textView;
    String text="";
    ArrayList<String> urls = new ArrayList<>();
    final String URL = "http://xn--80aejjdrc2b4d1c.xn--p1ai/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(URL).get();
                    text+= doc.title() + "\n \n";
                    Elements ul = doc.select("div#center > ul");
                    Elements links = doc.select("footer > h4 > a[href]");
                    for(int i=0;i<links.size();i++){
                        urls.add(links.get(i).select("a").attr("href"));
                    }
                    Elements h3 = ul.select("h3");
                    Elements li = ul.select("li");
                    text+=h3.text()+":\n";
                    for(Element element : li){
                        text+="• "+ element.text() + "\n";
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textView.setText(text);

    }

    private void startActivity(int i){
        Intent intent = new Intent(MainActivity.this, Second.class);
        intent.putExtra("link", urls.get(i));
        startActivity(intent);
    }

    @OnClick({
            R.id.fbButton,
            R.id.vkButton,
            R.id.instagramButton
    })
    void onClick(Button button) {
        switch (button.getId()){
            case R.id.fbButton:
                startActivity(0);
                break;
            case R.id.vkButton:
                startActivity(1);
                break;
            case R.id.instagramButton:
                startActivity(2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Выход из приложения")
                .setMessage("Уверены, что хотите выйти из приложения?")
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();
    }
}
