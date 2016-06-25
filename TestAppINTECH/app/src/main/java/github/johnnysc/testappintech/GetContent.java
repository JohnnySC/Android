package github.johnnysc.testappintech;

import java.util.ArrayList;

/**
 * Created by Hovhannes Asatryan on 23.06.16.
 */
public class GetContent {
    public static ArrayList<String> getArrayOfString(String keyWord){
        ArrayList<String> result = new ArrayList<>();
        String[] values = {"artistName","trackName","artworkUrl100","previewUrl"};

        switch (keyWord){
            case "artistName":
                result = getValues(values[0]);
                break;
            case "trackName":
                result = getValues(values[1]);
                break;
            case "coverURL":
                result = getValues(values[2]);
                break;
            case "songURL":
                result = getValues(values[3]);
                break;
        }

        return result;
    }

    public static ArrayList<String> getValues(String keyword){
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<Integer> first;
        String wholeText = MainActivity.text;

        first = getIndexes(wholeText,keyword);
        for(int i=0;i<first.size();i++){
            String current = wholeText.substring(first.get(i),first.get(i)+300);
            ArrayList<Integer> commas = getIndexes(current,",");
            arrayList.add(current.substring(keyword.length()+3,commas.get(0)-1));
        }
        return arrayList;

    }

    public static ArrayList<Integer> getIndexes(String wholeText, String word){
        ArrayList<Integer> indexes = new ArrayList<>();

        for(int i=0;i<wholeText.length()-word.length();i++)
        {
            int k=0;
            boolean b=true;
            while(k<word.length() && b){
                if(word.charAt(k)==wholeText.charAt(i+k))
                    k++;
                else
                    b=false;
            }
            if(k==word.length())
                indexes.add(i);
        }
        return indexes;
    }
}
