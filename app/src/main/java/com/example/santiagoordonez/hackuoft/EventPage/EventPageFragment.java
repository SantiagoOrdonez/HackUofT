package com.example.santiagoordonez.hackuoft.EventPage;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import com.example.santiagoordonez.hackuoft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventPageFragment extends Fragment {
    TextView eventView;
    TextView dateView;
    String id;
    Bundle bundle;
    String pictureUrl;

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
        ImageView eventBackgroundPic = (ImageView) view.findViewById(R.id.event_details_picture);
        bundle = getArguments();

        //get the picture url from bundle
        pictureUrl = bundle.getString("picUrl");

        //get the picture into the image view
        if((pictureUrl != null) && !pictureUrl.equals("")){
            Picasso.with(getContext()).load(pictureUrl).into(eventBackgroundPic);
        }else{
            eventBackgroundPic.setImageResource(R.drawable.event_screen_pic);
        }

        //get the title
        String title = bundle.getString("title");
        //get the date
        String date = bundle.getString("date");
        //get the id
        id = bundle.getString("id");

        //set event title and date
        eventView.setText(title);
        dateView.setText(date);

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        //

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        //Log.i(Constants.APP_TAG, "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 150;
                        final int SWIPE_MAX_OFF_PATH = 350;
                        final int SWIPE_THRESHOLD_VELOCITY = 30;
                        try {
                            if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                                //Log.i(Constants.APP_TAG, "Right to Left");
                                //user "Swiped up"
                                goToDescription();


                            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                                //Log.i(Constants.APP_TAG, "Left to Right");
                                //user "Swiped down"


                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

    }

    public void goToDescription(){
        //pass the already bundled info into new eventDetails page and slide down
        Intent toEvenDetailsIntent = new Intent(getContext(), EventDetails.class);
        toEvenDetailsIntent.putExtras(bundle);
        getActivity().startActivity(toEvenDetailsIntent);
        getActivity().overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
    }
}
