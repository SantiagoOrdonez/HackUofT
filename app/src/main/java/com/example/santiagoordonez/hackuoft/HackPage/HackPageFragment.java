package com.example.santiagoordonez.hackuoft.HackPage;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.santiagoordonez.hackuoft.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HackPageFragment extends Fragment {
    TextView hackView;
    TextView cityView;
    TextView yearView;
    TextView schoolNameView;

    public HackPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment(Use the hack_page_fragment as the UI for this fragment)
        View view = inflater.inflate(R.layout.hack_page_fragment, container, false);



        hackView = (TextView) view.findViewById(R.id.position);
        //cityView = (TextView) view.findViewById(R.id.city);
        //yearView = (TextView) view.findViewById(R.id.year);
        schoolNameView = (TextView) view.findViewById(R.id.school_name);

        Bundle bundle = getArguments();

        //String position = Integer.toString(bundle.getInt("position"));
        String title = bundle.getString("title");
        //String year = Integer.toString(bundle.getInt("year"));
        //String city = bundle.getString("city");
        String school_name_view = bundle.getString("school_name");

        //set the text of the screen
        hackView.setText(title);
        //cityView.setText(city);
        //yearView.setText(year);
        schoolNameView.setText(school_name_view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
