package uk.ac.tees.live.q5273477.journal;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by q5273477 on 21/02/2017.
 */
public class addEditPageFragment extends Fragment {

    private Spinner catList;
    private Button save, cancel;
    private EditText titleBox, noteBox;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_addedit_page, container, false);
        setupConponents(view);




        return view;
    }

    private void setupConponents(View view){

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
