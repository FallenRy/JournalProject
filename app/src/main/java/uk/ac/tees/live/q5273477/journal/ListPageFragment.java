package uk.ac.tees.live.q5273477.journal;

import android.icu.text.UnicodeSet;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by q5273477 on 21/02/2017.
 */
public class ListPageFragment extends Fragment {

    ListView listView;
    TextView _studnet_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_page, container, false);

        listView = (ListView) view.findViewById(R.id.entry_list);






        return view;
    }

    public void getList(View view){

        EntryCRUD entryCRUD = new EntryCRUD(view.getContext());

        ArrayList<HashMap<String,String>> entryList = entryCRUD.getEntryList();

        if(entryList.size() != 0){
            listView.setOnItemClickListener( new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parnet, View view, int pos, long id){





                }
                });
        }

    }

}
