package uk.ac.tees.live.q5273477.journal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by q5273477 on 21/02/2017.
 */
public class addEditPageFragment extends Fragment {

    private Spinner catList;
    private Button save, cancel;
    private EditText titleBox, noteBox;
    private int _entry_Id  = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_addedit_page, container, false);
        setupComponents(view);
        setupOnClicks(view);

        _entry_Id = 0;
        Intent intent = getActivity().getIntent();
        _entry_Id = intent.getIntExtra("entry_id", 0);
        EntryCRUD crud = new EntryCRUD(view.getContext());
        Entry entry;
        entry = crud.getEntryByID(_entry_Id);
        titleBox.setText(getTitle(entry.text));
        noteBox.setText(getNote(entry.text));
       catList.setSelection(getCatPos(entry.categoty));





        return view;
    }

    public String getTitle(String text){
        String title = "";

        if (text != null && !text.equals("")){

            int breakText = text.indexOf('#');

            title = text.substring(0,breakText);
            return title;
        }


        return title;
    }

    public String getNote(String text){
        String note = "";

        if (text != null && !text.equals("")){

            int breakText = text.indexOf('#');

            note = text.substring(breakText, text.length());
            return note;
        }


        return note;
    }

    public int getCatPos(String cat){
        if(cat == "Work"){
            return 0;
        }
        return 1;
    }

    private void setupOnClicks(View view) {

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EntryCRUD entryCRUD = new EntryCRUD(view.getContext());
                Entry entry = new Entry();

                entry.text = titleBox.getText().toString() + "#b#" +noteBox.getText().toString();
                entry.categoty = catList.getSelectedItem().toString();
                entry.date_time = System.currentTimeMillis();


                if(_entry_Id == 0){
                    _entry_Id = entryCRUD.insert(entry);
                    Toast.makeText(view.getContext(), "New entry saved", Toast.LENGTH_SHORT).show();
                }else{
                    entryCRUD.update(entry);
                    Toast.makeText(view.getContext(), "Entry updated", Toast.LENGTH_SHORT).show();

                }

                ((MainActivity)getActivity()).changeFragment(1);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).changeFragment(1);

            }
        });



    }

    private void setupComponents(View view){

        titleBox = (EditText) view.findViewById(R.id.title_input_text_box);
        noteBox = (EditText) view.findViewById(R.id.entry_input_note);

        save = (Button) view.findViewById(R.id.Edit_button);
        cancel = (Button) view.findViewById(R.id.cancel_button);

        catList = (Spinner) view.findViewById(R.id.cat_dropdown);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cat_spinner_list));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catList.setAdapter(adapter);



    }

}
