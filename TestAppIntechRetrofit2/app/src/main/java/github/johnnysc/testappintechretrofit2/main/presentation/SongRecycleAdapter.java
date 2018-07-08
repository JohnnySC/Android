package github.johnnysc.testappintechretrofit2.main.presentation;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import github.johnnysc.testappintechretrofit2.R;
import github.johnnysc.testappintechretrofit2.main.data.bean.Song;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public class SongRecycleAdapter extends RecyclerView.Adapter<SongRecycleAdapter.Holder> {

    private final SongClickListener mSongClickListener;
    private List<Song> mSongList;

    public SongRecycleAdapter(SongClickListener songClickListener) {
        mSongClickListener = songClickListener;
    }

    @NonNull
    @Override
    public SongRecycleAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongRecycleAdapter.Holder holder, int position) {
        Song currentSong = mSongList.get(position);
        Picasso.with(holder.itemView.getContext())
                .load(currentSong.getCoverUrl())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(holder.imageView);
        holder.songName.setText(currentSong.getTrackName());
        holder.artistName.setText(currentSong.getArtistName());
    }

    @Override
    public int getItemCount() {
        return mSongList == null ? 0 : mSongList.size();
    }

    public Song getSelectedSong(int position) {
        return mSongList.get(position);
    }

    public void setData(@NonNull List<Song> data) {
        mSongList = data;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView songName;
        TextView artistName;

        Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.cover_image_view);
            songName = itemView.findViewById(R.id.track_text_view);
            artistName = itemView.findViewById(R.id.artist_text_view);
        }

        @Override
        public void onClick(View view) {
            mSongClickListener.onClick(getLayoutPosition());
        }
    }

    public interface SongClickListener {
        void onClick(int position);
    }
}
