package github.johnnysc.slidemenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class FirstFragment extends Fragment {

    WebView firstWebView;

    public FirstFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.webview_fragment, container, false);

        firstWebView = (WebView) rootView.findViewById(R.id.firstWebView);
        WebSettings webSettings = firstWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        firstWebView.loadUrl("https://career.ru/resume/fd9a07aaff02f8c0130039ed1f58456d793236");

        return rootView;
    }
}
