package github.johnnysc.mayakoffskij;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FoundPoemsAdapter extends BaseAdapter {
    public static ArrayList<PoemRecord> foundPoems;

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

        TextView nameTextView = (TextView)convertView.findViewById(R.id.name_view);
        nameTextView.setText(poem.getName());

        TextView poemTextView = (TextView)convertView.findViewById(R.id.poem_view);
        poemTextView.setText(poem.getPoem());
        return convertView;

    }

}
