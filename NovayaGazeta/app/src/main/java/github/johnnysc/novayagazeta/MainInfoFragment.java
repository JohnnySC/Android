package github.johnnysc.novayagazeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;

import java.util.List;


/**
 * Created by Hovhannes Asatryan on 27.09.16.
 */
public class MainInfoFragment extends Fragment implements RecycleAdapter.ArticleClickListener {
    RecycleAdapter adapter;
    RecyclerView recyclerView;

    public MainInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        List<Article> articlesList = GetINFO.getArticles(MainActivity.link);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecycleAdapter(articlesList,this);
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onClick(int position) {
        Article currentArticle = adapter.getSelectedArticle(position);
        Intent intent = new Intent(MainActivity.context, ArticleActivity.class);
        intent.putExtra("article", Parcels.wrap(currentArticle));
        startActivity(intent);
    }
}
