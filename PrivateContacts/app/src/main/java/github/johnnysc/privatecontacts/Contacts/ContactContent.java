package github.johnnysc.privatecontacts.Contacts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ContactContent {

    public static List<ContactItem> ITEMS;
    public static HashMap<String, String> myMap;
    public static Map<String,ContactItem> ITEM_MAP;

    public static void addItem(ContactItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.number, item);
        myMap.put(item.number,item.name);
    }

    public static void removeItem(ContactItem item){
        Iterator<ContactItem> iterator = ITEMS.iterator();
        while(iterator.hasNext()){
            ContactItem contactItem = iterator.next();
            if(item.number.equals(contactItem.number)){
                iterator.remove();
            }
        }

        for(Iterator<Map.Entry<String, ContactItem>> it = ITEM_MAP.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, ContactItem> entry = it.next();
            if(entry.getKey().equals(item.number)) {
                it.remove();
            }
        }

        for(Iterator<Map.Entry<String, String>> it = myMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            if(entry.getKey().equals(item.number)) {
                it.remove();
            }
        }
    }

    public static class ContactItem {
        public final String name;
        public final String number;

        public ContactItem(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public String toString() {
            return number;
        }
    }
}
