package uk.ac.tees.live.q5273477.journal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Theph on 20/02/2017.
 */
public class HomePageFragment extends Fragment {

    private Button list, add, search, settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        setupButtons(view);
        setupButtonsOnClick();


        return view;
    }

    private void setupButtons(View view) {
        list = (Button) view.findViewById(R.id.list_button);
        add = (Button) view.findViewById(R.id.add_button);
        search = (Button) view.findViewById(R.id.button_search);
        settings = (Button) view.findViewById(R.id.button_settings);
    }

    private void setupButtonsOnClick(){

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "List button", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).changeFragment(1);

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Add button", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).changeFragment(2);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Search button", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).changeFragment(99);

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Settings button", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).changeFragment(99);
            }
        });

    }
}
