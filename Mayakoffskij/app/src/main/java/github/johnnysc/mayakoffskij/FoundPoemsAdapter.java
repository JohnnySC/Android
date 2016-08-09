package github.johnnysc.mayakoffskij;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FoundPoemsAdapter extends BaseAdapter {
    public static ArrayList<PoemRecord> foundPoems;
    @BindView(R.id.name_view) TextView nameTextView;
    @BindView(R.id.poem_view) TextView poemTextView;

    public FoundPoemsAdapter(){
        foundPoems = new ArrayList<>();
    }

    public static void addFoundPoem(PoemRecord foundPoem){
        foundPoems.add(foundPoem);
    }

    @Override
    public int getCount() {
        return foundPoems.size();
    }

    @Override
    public Object getItem(int position) {
        return foundPoems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.poem_list_item, parent, false);
        }

        PoemRecord poem = foundPoems.get(position);

        ButterKnife.bind(this,convertView);
        nameTextView.setText(poem.getName());
        poemTextView.setText(poem.getPoem());

        return convertView;
    }
}
