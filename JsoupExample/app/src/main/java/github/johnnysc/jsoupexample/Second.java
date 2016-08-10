package github.johnnysc.jsoupexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Second extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        webView = (WebView)findViewById(R.id.mWebView);

        String link = getIntent().getStringExtra("link");

        webView.loadUrl(link);
    }
}
