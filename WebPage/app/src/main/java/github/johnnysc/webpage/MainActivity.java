package github.johnnysc.webpage;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    public static WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myWebView = (WebView)findViewById(R.id.webview);
        final EditText myEditText = (EditText)findViewById(R.id.editText);
        Button goButton = (Button)findViewById(R.id.goButton);
        if (myWebView != null) {
            myWebView.loadUrl("http://www.google.com");
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAppCacheEnabled(true);
            webSettings.setSupportMultipleWindows(true);
            webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
            myWebView.setWebViewClient(new WebViewClient());
        }

        if (goButton != null) {
            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myWebView != null) {
                        if (myEditText != null) {
                            String urlAddress = myEditText.getText().toString();
                            if(!urlAddress.startsWith("http://www.")) {
                                urlAddress = "http://www." + urlAddress;
                            }else if(urlAddress.startsWith("www.")){
                                urlAddress = "http://"+urlAddress;
                            }
                            myWebView.loadUrl(urlAddress);



                        }
                    }

                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
