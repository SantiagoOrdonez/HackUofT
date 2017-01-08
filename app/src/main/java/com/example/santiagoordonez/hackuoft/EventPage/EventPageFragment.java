package com.example.santiagoordonez.hackuoft.EventPage;


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
public class EventPageFragment extends Fragment {
    TextView eventView;


    public EventPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment(Use the hack_page_fragment as the UI for this fragment)
        View view = inflater.inflate(R.layout.event_fragment, container, false);
        eventView = (TextView) view.findViewById(R.id.position);
        Bundle bundle = getArguments();

        //get the title
        String title = bundle.getString("title");

        eventView.setText(title);

        return view;
    }

}
