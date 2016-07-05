package github.johnnysc.slidemenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ThirdFragment extends Fragment {
	WebView webview;
	public ThirdFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.webview_fragment, container, false);
        webview = (WebView)rootView.findViewById(R.id.firstWebView);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadUrl("http://johnnyblog.ru/");
         
        return rootView;
    }
}
