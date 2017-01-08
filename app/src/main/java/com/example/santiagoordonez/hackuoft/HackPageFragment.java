package com.example.santiagoordonez.hackuoft;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

        //set the text of the screen
        hackView.setText("HackView "+ position);

        return inflater.inflate(R.layout.hack_page_fragment, container, false);
    }

}
