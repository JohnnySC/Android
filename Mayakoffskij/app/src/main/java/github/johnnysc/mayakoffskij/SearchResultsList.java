package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class SearchResultsList extends Activity {
    String searchText;
    FoundPoemsAdapter foundPoemsAdapter;
    public static ArrayList<Integer> indexes;
    public static int foundPosition;
    @BindView(R.id.search_results) ListView searchListView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);

        ButterKnife.bind(this);
        foundPosition = 0;
        Poems allPoems = new Poems();

        searchText = getIntent().getStringExtra("searchText");
        indexes = new ArrayList<>();
        foundPoemsAdapter = new FoundPoemsAdapter();
        searchListView.setAdapter(foundPoemsAdapter);

        for(int i=0;i<allPoems.getSize();i++){
            if(Poems.poems.get(i).toLowerCase().contains(searchText.toLowerCase())){
                FoundPoemsAdapter.addFoundPoem(PoemAdapter.poems.get(i));
                indexes.add(i);
            }
        }
        if(FoundPoemsAdapter.foundPoems.size()==0) {
            Toast.makeText(this, "Нет совпадений", Toast.LENGTH_SHORT).show();
            finish();
        }

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                foundPosition = indexes.get(position);
                Intent intent = new Intent(SearchResultsList.this, FoundPoemView.class);
                startActivity(intent);
            }
        });

    }
}
