package github.johnnysc.watches.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import github.johnnysc.watches.R;
import github.johnnysc.watches.Rest.Watch;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public class WatchAdapter extends BaseAdapter{
    ArrayList<Watch> watches;
    Context mContext;

    public WatchAdapter(Context context,ArrayList<Watch> watches) {
        this.mContext = context;
        this.watches = watches;
    }

    @Override
    public int getCount() {
        return watches.size();
    }

    @Override
    public Object getItem(int i) {
        return watches.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.watch_item, viewGroup,false);
        }
        Watch watch = watches.get(i);

        ImageView imageView = (ImageView)view.findViewById(R.id.watchImageView);
        Picasso.with(mContext)
                .load(watch.getImg())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(imageView);
        Log.d("myLog","Picasso made the imageView watch!!!");

        TextView watchName = (TextView)view.findViewById(R.id.watchName);
        watchName.setText(watch.getName());

        TextView watchBrand = (TextView)view.findViewById(R.id.watchBrand);
        watchBrand.setText(watch.getBrand());

        TextView watchCollection = (TextView)view.findViewById(R.id.watchCollection);
        watchCollection.setText(watch.getCollection());

        TextView watchRef = (TextView)view.findViewById(R.id.watchRef);
        watchRef.setText(watch.getRef());

        TextView watchUSD = (TextView)view.findViewById(R.id.watchUSD);
        String text = "USD: "+watch.getUsd();
        watchUSD.setText(text);

    return view;
    }
}
