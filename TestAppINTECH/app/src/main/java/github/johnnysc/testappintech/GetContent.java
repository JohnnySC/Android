package github.johnnysc.testappintech;

import java.util.ArrayList;

/**
 * Created by johnny on 23.06.16.
 */
public class GetContent {
    public static ArrayList<String> getArrayOfString(String keyWord){
        ArrayList<String> artistName = new ArrayList<>();
        ArrayList<String> trackName = new ArrayList<>();
        ArrayList<String> coverURL = new ArrayList<>();
        ArrayList<String> songURL= new ArrayList<>();
        ArrayList<String> result;
        ArrayList<Integer> first;
        ArrayList<Integer> second;
        String wholeText = MainActivity.text;

        if(keyWord=="artistName"){
            first = getIndexes("artistName");
            second = getIndexes("collectionName");
            for(int i=0;i<first.size()-1;i++){
                artistName.add(wholeText.substring(first.get(i)+13,second.get(i)-4));
            }
            result = artistName;
        } else if(keyWord=="trackName"){
            first = getIndexes("trackName");
            second = getIndexes("collectionCensoredName");
            for(int i=0;i<first.size()-1;i++){
                trackName.add(wholeText.substring(first.get(i)+12,second.get(i)-4));
            }
            result = trackName;
        }else if(keyWord=="coverURL"){
            first = getIndexes("artworkUrl100");
            second = getIndexes("collectionPrice");
            for(int i=0;i<first.size()-1;i++){
                coverURL.add(wholeText.substring(first.get(i)+16,second.get(i)-4));
            }
            result = coverURL;
        }else{
            first = getIndexes("previewUrl");
            second = getIndexes("artworkUrl30");
            for(int i=0;i<first.size()-1;i++){
                songURL.add(wholeText.substring(first.get(i)+13,second.get(i)-4));
            }
            result = songURL;
        }

        return result;
    }

    public static ArrayList<Integer> getIndexes(String word){
        ArrayList<Integer> indexes = new ArrayList<>();
        try {
            String wholeText = MainActivity.text;
            for (int i = 0; i < wholeText.length() - word.length(); i++) {
                int k = 0;
                int b = 0;
                while (k < word.length() && b==0) {
                    if (word.charAt(k) == wholeText.charAt(i + k))
                        k++;
                    else
                        b++;
                }
                if (k == word.length())
                    indexes.add(i);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return indexes;
    }

}
