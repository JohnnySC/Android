package github.johnnysc.testappintechretrofit2.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.johnnysc.testappintechretrofit2.R;
import github.johnnysc.testappintechretrofit2.REST.Song;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public class SongRecycleAdapter extends RecyclerView.Adapter<SongRecycleAdapter.Holder>{
    private List<Song> songList;
    private final SongClickListener songClickListener;

    public SongRecycleAdapter(List<Song> songList, SongClickListener songClickListener) {
        this.songList = songList;
        this.songClickListener = songClickListener;
    }

    @Override
    public SongRecycleAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item,parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(SongRecycleAdapter.Holder holder, int position) {
        Song currentSong = songList.get(position);
        Picasso.with(holder.itemView.getContext())
                .load(currentSong.getArtWorkUrl100())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(holder.imageView);
        holder.songName.setText(currentSong.getTrackName());
        holder.artistName.setText(currentSong.getArtistName());
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public Song getSelectedSong(int position){
        return songList.get(position);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.albumCover_view) ImageView imageView;
        @BindView(R.id.songName_view) TextView songName;
        @BindView(R.id.artistName_view) TextView artistName;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            songClickListener.onClick(getLayoutPosition());
        }
    }

    public interface SongClickListener{
        void onClick(int position);
    }
}
