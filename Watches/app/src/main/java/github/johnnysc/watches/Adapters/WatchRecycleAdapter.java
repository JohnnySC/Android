package github.johnnysc.watches.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.johnnysc.watches.R;
import github.johnnysc.watches.Rest.Watch;

/**
 * Created by Hovhannes Asatryan on 06.08.16.
 */
public class WatchRecycleAdapter extends RecyclerView.Adapter<WatchRecycleAdapter.Holder>{
    private List<Watch> watchList;

    public WatchRecycleAdapter(List<Watch> watchList) {
        this.watchList = watchList;
    }

    @Override
    public WatchRecycleAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.watch_item,parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(WatchRecycleAdapter.Holder holder, int position) {
        Watch currentWatch = watchList.get(position);
        Picasso.with(holder.itemView.getContext())
                .load(currentWatch.getImg())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(holder.watchImage);
        Log.d("myLog","Picasso made the imageView watch!!!");
        holder.watchName.setText(currentWatch.getName());
        holder.watchBrand.setText(currentWatch.getBrand());
        holder.watchCollection.setText(currentWatch.getCollection());
        holder.watchRef.setText(currentWatch.getRef());
        String text = "USD: " + currentWatch.getUsd();
        holder.watchUSD.setText(text);
    }

    @Override
    public int getItemCount() {
        return watchList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        @BindView(R.id.watchImageView) ImageView watchImage;
        @BindView(R.id.watchName) TextView watchName;
        @BindView(R.id.watchBrand) TextView watchBrand;
        @BindView(R.id.watchCollection) TextView watchCollection;
        @BindView(R.id.watchRef) TextView watchRef;
        @BindView(R.id.watchUSD) TextView watchUSD;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
