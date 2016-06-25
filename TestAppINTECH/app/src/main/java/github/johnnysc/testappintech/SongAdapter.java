package github.johnnysc.testappintech;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Hovhannes Asatryan on 22.06.16.
 */
public class SongAdapter extends BaseAdapter {
    public static ArrayList<Song> allSongs = null;
    Song mySong;
    Bitmap myBitmap;

    public SongAdapter() {
        allSongs = new ArrayList<>();

        for(int i=0;i<MainActivity.songArrayList.size();i++) {
            allSongs.add(MainActivity.songArrayList.get(i));
        }
    }

    @Override
    public int getCount() {
        return allSongs.size();
    }

    @Override
    public Object getItem(int position) {
        return allSongs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.song_list_item, parent, false);
        }
        Song song = allSongs.get(position);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.albumCover_view);
        mySong = song;
        Thread thread = new Thread(new bitmapRunnable());
        MainActivity.runTheThread(thread);
        imageView.setImageBitmap(myBitmap);

        TextView songName = (TextView)convertView.findViewById(R.id.songName_view);
        songName.setText(song.getTrackName());

        TextView artistName = (TextView)convertView.findViewById(R.id.artistName_view);
        artistName.setText(song.getArtistName());

        return convertView;
    }

    public static Bitmap getImageBitmap(String url) {
        Bitmap bitmap = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

   private class bitmapRunnable implements Runnable{
       public void run(){
        myBitmap=getImageBitmap(mySong.getCoverURL());
       }
   }
}
