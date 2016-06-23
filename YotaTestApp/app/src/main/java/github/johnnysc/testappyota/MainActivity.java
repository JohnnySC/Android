package github.johnnysc.testappyota;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/* Test App for Yota
* App gets url address and shows the html code of it.
* Made by Hovhannes Asatryan on 21.06.16
*/
public class MainActivity extends Activity{
    public static EditText editText;
    public static Button button;
    public static TextView textView;
    public static StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new HtmlRunnable());
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textView.setText(sb.toString());
                sb.setLength(0);
            }
        });
    }

public class HtmlRunnable implements Runnable {
    public void run(){
        try {
            URL pageURL = new URL(editText.getText().toString());
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
