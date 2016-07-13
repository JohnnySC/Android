package github.johnnysc.privatecontacts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import github.johnnysc.privatecontacts.Contacts.ContactContent;

public class FileIO {

    public static final String FILE_NAME = "myMap.txt";

    public static void saveFile(FileOutputStream fileOutputStream){
        try {
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(ContactContent.myMap);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile(FileInputStream fileInputStream){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ContactContent.myMap = (HashMap) objectInputStream.readObject();

            ContactContent.ITEMS = new ArrayList<>();
            ContactContent.ITEM_MAP = new HashMap<>();
            for(HashMap.Entry<String,String> entry : ContactContent.myMap.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                ContactContent.ContactItem item = new ContactContent.ContactItem(value,key);
                ContactContent.ITEMS.add(item);
                ContactContent.ITEM_MAP.put(item.number,item);
            }

            objectInputStream.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
