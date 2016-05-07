package github.johnnysc.mayakoffskij;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FileIO {
    public static String file_name = "favorite_indexes.txt";

    public static void readData(Context context, FileInputStream inputStream){
        try {
            String value;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            FavoritePoems.favIndexes = new ArrayList<>();
            FavoritePoemsAdapter.favoritePoems = new ArrayList<>();
            while((value=bufferedReader.readLine())!=null){
                stringBuffer.append(value).append("\n");
                FavoritePoems.favIndexes.add(Integer.parseInt(value));
                FavoritePoemsAdapter.favoritePoems.add(PoemAdapter.poems.get(Integer.parseInt(value)));
            }
            Toast.makeText(context,"Список избранных загружен",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData(Context context, FileOutputStream outputStream){
        String data = "";
        for(int i=0; i<FavoritePoems.favIndexes.size();i++){
            data= data + Integer.toString(FavoritePoems.favIndexes.get(i))+"\n";
        }
        try{
            outputStream.write(data.getBytes());
            outputStream.close();
            Toast.makeText(context,"Список избранных сохранен",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
