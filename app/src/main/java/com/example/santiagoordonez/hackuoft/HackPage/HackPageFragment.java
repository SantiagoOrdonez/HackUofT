package com.example.santiagoordonez.hackuoft.HackPage;


import android.os.Bundle;
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

    public HackPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment(Use the hack_page_fragment as the UI for this fragment)
        View view = inflater.inflate(R.layout.hack_page_fragment, container, false);
        hackView = (TextView) view.findViewById(R.id.position);
        Bundle bundle = getArguments();
        String position = Integer.toString(bundle.getInt("position"));
        String year = Integer.toString(bundle.getInt("year"));

        //set the text of the screen
        String var = "HackView " + position;
        hackView.setText(var);

        return view;
    }

}