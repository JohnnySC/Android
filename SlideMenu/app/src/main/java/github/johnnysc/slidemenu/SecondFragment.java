package github.johnnysc.slidemenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class SecondFragment extends Fragment {
	WebView secondWebView;
	public SecondFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.webview_fragment, container, false);
        secondWebView = (WebView)rootView.findViewById(R.id.firstWebView);
        WebSettings webSettings = secondWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        secondWebView.loadUrl("https://github.com/JohnnySC");
         
        return rootView;
    }
}
