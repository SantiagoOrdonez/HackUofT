package com.example.santiagoordonez.hackuoft.EventPage;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.santiagoordonez.hackuoft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventPageFragment extends Fragment {
    TextView eventView;
    TextView dateView;
    String id;

    public EventPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment(Use the hack_page_fragment as the UI for this fragment)
        View view = inflater.inflate(R.layout.event_fragment, container, false);
        eventView = (TextView) view.findViewById(R.id.title);
        dateView = (TextView) view.findViewById(R.id.date);
        Bundle bundle = getArguments();

        //get the title
        String title = bundle.getString("title");
        //get the date
        String date = bundle.getString("date");
        //get the id
        id = bundle.getString("id");

        eventView.setText(title);
        dateView.setText(date);

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_UP){
                    //go to the link url
                    String url_final = "https://www.facebook.com/" + id;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_final));
                    startActivity(browserIntent);
                }
                return true;
            }
        });


    }
}
