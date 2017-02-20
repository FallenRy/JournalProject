package uk.ac.tees.live.q5273477.journal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Theph on 20/02/2017.
 */
public class Adapter extends ArrayAdapter<ListItem> {


    Context context;
    int layoutId;
    ListItem list[] = null;


    public Adapter(Context contextIn, int idIn, ListItem[] listIn){
        super(contextIn, idIn, listIn);
        this.context = contextIn;
        this.layoutId = idIn;
        this.list = listIn;
    }

    @Override
    public View getView(int pos, View view , ViewGroup parent){
        View drawerItem = view;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        drawerItem = inflater.inflate(layoutId, parent, false);

        ImageView itemIcon = (ImageView) drawerItem.findViewById(R.id.itemIcon);
        TextView  itemName = (TextView) drawerItem.findViewById(R.id.itemText);

        ListItem holder = list[pos];

        itemIcon.setImageResource(holder.itemIcon);
        itemName.setText(holder.itemName);

        return drawerItem;

    }



}
