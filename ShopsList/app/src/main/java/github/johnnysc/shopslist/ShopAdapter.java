package github.johnnysc.shopslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hovhannes Asatryan on 14.07.16.
 */
public class ShopAdapter extends BaseAdapter {
    public static ArrayList<ShopItem> shopItemArrayList = null;

    public ShopAdapter(){
        shopItemArrayList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return shopItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopItemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.shop_list_item,parent,false);
        }

        ShopItem shopItem = shopItemArrayList.get(position);

        TextView addressTextView = (TextView)convertView.findViewById(R.id.address_view);
        addressTextView.setText(shopItem.getAddress());

        TextView townTextView = (TextView)convertView.findViewById(R.id.town_view);
        townTextView.setText(shopItem.getTown());

        TextView phoneTextView = (TextView)convertView.findViewById(R.id.phone_view);
        phoneTextView.setText(shopItem.getPhone());

        return convertView;
    }
}
