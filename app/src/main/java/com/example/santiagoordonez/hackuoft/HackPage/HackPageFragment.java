package com.example.santiagoordonez.hackuoft.HackPage;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class HackPageFragment extends Fragment {

    TextView hackView;
    TextView schoolNameView;

    Bundle bundle = getArguments();
    String title = bundle.getString("title");
    String school_name_view = bundle.getString("school_name");
    String cost = bundle.getString("cost");
    String facebook_url = bundle.getString("facebook_url");

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

        //set the text of the screen
        hackView.setText(title);
        schoolNameView.setText(school_name_view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_BUTTON_PRESS){
                    //User has clicked the image.
                    Intent intent = new Intent(getView().getContext(), HackPageDetails.class);
                    intent.putExtra("title",title);
                    intent.putExtra("school_name", school_name_view);
                    intent.putExtra("cost", cost);
                    intent.putExtra("facebook_url", facebook_url);

                    startActivity(intent);
                }
                return true;
            }
        });


    }



}
