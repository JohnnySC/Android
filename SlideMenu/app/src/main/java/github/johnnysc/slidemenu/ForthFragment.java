package github.johnnysc.slidemenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ForthFragment extends Fragment {
    private static EditText editText;
    public static Button button;
    private static TextView textView;
    private static StringBuilder sb = new StringBuilder();

	public ForthFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.third_fragment, container, false);

        editText = (EditText)rootView.findViewById(R.id.editText);
        button = (Button)rootView.findViewById(R.id.button);
        textView = (TextView)rootView.findViewById(R.id.textView);

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

        return rootView;
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

