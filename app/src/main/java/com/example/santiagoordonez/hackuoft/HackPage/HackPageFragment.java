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
    Bundle bundle;
    String title;
    String school_name;
    String cost;
    String facebook_url;

    TextView hackView;
    TextView schoolNameView;


    public HackPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment(Use the hack_page_fragment as the UI for this fragment)

        View view = inflater.inflate(R.layout.hack_page_fragment, container, false);
        bundle = getArguments();

        hackView = (TextView) view.findViewById(R.id.position);
        schoolNameView = (TextView) view.findViewById(R.id.school_name);

        title = bundle.getString("title");
        school_name = bundle.getString("school_name");
        cost = bundle.getString("cost");
        facebook_url = bundle.getString("facebook_url");

        //set the text of the screen
        hackView.setText(title);
        schoolNameView.setText(school_name);

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_UP){
                    //User has clicked the image.
                    Intent intent = new Intent(view.getContext(), HackPageDetails.class);

                    intent.putExtra("title",title);
                    intent.putExtra("school_name", school_name);
                    intent.putExtra("cost", cost);
                    intent.putExtra("facebook_url", facebook_url);

                    startActivity(intent);
                }
                return true;
            }
        });


    }



}
