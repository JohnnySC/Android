package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FileIO {
    public static String file_name = "favorite_indexes.txt";

    public static void readData(Activity activity, Context context, FileInputStream inputStream){
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
            Crouton.makeText(activity,"Список избранных загружен", Style.INFO).show();
        } catch (IOException e) {
            Crouton.makeText(activity,"Ошибка при загрузке",Style.ALERT).show();
        }
    }

    public static void writeData(Activity activity, Context context, FileOutputStream outputStream){
        String data = "";
        for(int i=0; i<FavoritePoems.favIndexes.size();i++){
            data= data + Integer.toString(FavoritePoems.favIndexes.get(i))+"\n";
        }
        try{
            outputStream.write(data.getBytes());
            outputStream.close();
            Crouton.makeText(activity,"Список избранных сохранен",Style.INFO).show();
        } catch (IOException e) {
            Crouton.makeText(activity,"Ошибка при сохранении",Style.ALERT).show();
        }

    }
}
