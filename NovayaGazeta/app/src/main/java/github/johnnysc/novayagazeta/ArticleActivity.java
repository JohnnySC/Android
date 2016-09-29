package github.johnnysc.novayagazeta;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Article article = Parcels.unwrap(getIntent().getParcelableExtra("article"));
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(article.getHeading());

        final LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        final LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutParams paramsLeft =  new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,10,10,10);
        paramsLeft.setMargins(10,10,10,10);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        paramsLeft.gravity = Gravity.START;
        linearLayout.setLayoutParams(layoutParams);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(layoutParams);
        scrollView.setBackgroundColor(Color.WHITE);
        scrollView.addView(linearLayout);

        try{
        Document doc = Jsoup.connect(article.getLink()).get();
            TextView textView = new TextView(this);
            String text = doc.select("h1.b-title-name").text()+"\n"+doc.select("p.b-title-description").text()+"\n"+doc.select("span.b-title-meta-date").text();
            textView.setText(text);
            textView.setTypeface(Typeface.SERIF,Typeface.BOLD);
            textView.setTextSize(20);
            textView.setLayoutParams(paramsLeft);
            linearLayout.addView(textView);

       Elements elements = doc.select("div.b-article > *");
        for(final Element element : elements){
            if(element.tagName().equals("p") && element.select("img").size()>0){
                final Button button = new Button(this);
                button.setText("Загрузить изображение");
                button.setTextSize(14);
                button.setBackgroundColor(Color.argb(47,48,211,211));
                button.setTypeface(Typeface.SERIF);
                button.setLayoutParams(params);
                final ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(params);
                linearLayout.addView(imageView);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Picasso.with(MainActivity.context)
                                .load(element.select("img").first().absUrl("src"))
                                .placeholder(R.drawable.progress_animation)
                                .error(R.drawable.ic_error)
                                .into(imageView);
                        button.setVisibility(View.INVISIBLE);
                    }
                });
                linearLayout.addView(button);
            }else if(element.tagName().equals("p") && element.select("iframe").size()>0){
                Button button = new Button(this);
                button.setText("Ссылка на видео");
                button.setTextSize(18);
                button.setTypeface(Typeface.SERIF);
                button.setBackgroundColor(Color.argb(47,48,211,211));
                final String url = element.select("iframe").first().absUrl("src");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        try{
                            startActivity(intent);
                        }catch(ActivityNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(MainActivity.context,"Браузер не найден",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                button.setLayoutParams(params);
                linearLayout.addView(button);
            }
            else{
                TextView txtView = new TextView(this);
                txtView.setText(element.text());
                txtView.setTypeface(Typeface.SERIF);
                txtView.setTextSize(16);
                txtView.setLayoutParams(paramsLeft);
                linearLayout.addView(txtView);
            }
        }

    }catch(Exception e){
        e.printStackTrace();
    }
        setContentView(scrollView);
    }
}
