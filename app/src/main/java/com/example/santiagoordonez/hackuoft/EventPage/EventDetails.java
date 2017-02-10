package com.example.santiagoordonez.hackuoft.EventPage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.santiagoordonez.hackuoft.R;
import com.example.santiagoordonez.hackuoft.TouchListerner.OnSwipeTouchListener;

import static java.security.AccessController.getContext;

public class EventDetails extends AppCompatActivity {

    Bundle myBundle;
    TextView title;
    TextView description;
    TextView date;
    TextView location;
    String id;
    ImageButton facbook_bt;
    AppCompatActivity activity;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // initialize ui components
        title = (TextView) findViewById(R.id.even_details_title);
        description = (TextView) findViewById(R.id.even_details_description);
        date = (TextView) findViewById(R.id.even_details_date);
        facbook_bt = (ImageButton) findViewById(R.id.facebook_link);
        location = (TextView) findViewById(R.id.even_details_location);

        // get bundle from previous activity
        myBundle = getIntent().getExtras();

        //set all ui components to the relevant data
        id = myBundle.getString("id");
        title.setText(myBundle.getString("title"));
        description.setText(myBundle.getString("description"));
        date.setText(myBundle.getString("date"));
        location.setText(myBundle.getString("location"));

        activity = EventDetails.this;
        view = findViewById(android.R.id.content);

        // set listener
        facbook_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to facebook page
                String url_final = "https://www.facebook.com/" + id;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url_final));
                startActivity(browserIntent);
            }
        });

        description.setOnTouchListener(new OnSwipeTouchListener(EventDetails.this) {
            public void onSwipeTop() {
                Toast.makeText(EventDetails.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                //Toast.makeText(EventDetails.this, "right", Toast.LENGTH_SHORT).show();

                //pass the already bundled info into new events page and slide left
                Intent toEvenDetailsIntent = new Intent( activity, EventsPage.class);
                Bundle newBundle = new Bundle();
                newBundle.putInt("move", -1);
                newBundle.putInt("position",myBundle.getInt("position"));
                toEvenDetailsIntent.putExtras(newBundle);
                startActivity(toEvenDetailsIntent);
                //overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
            }
            public void onSwipeLeft() {
                //Toast.makeText(EventDetails.this, "left", Toast.LENGTH_SHORT).show();

                //pass the already bundled info into new events page and slide left
                Intent toEvenDetailsIntent = new Intent( activity, EventsPage.class);
                Bundle newBundle = new Bundle();
                newBundle.putInt("move", 1);
                newBundle.putInt("position",myBundle.getInt("position"));
                toEvenDetailsIntent.putExtras(newBundle);
                startActivity(toEvenDetailsIntent);
                //overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
            public void onSwipeBottom() {
                Toast.makeText(EventDetails.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        /**
        //set swipe listener
        final GestureDetector gesture = new GestureDetector( activity,
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        //Log.i(Constants.APP_TAG, "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 70;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 70;
                        try {
                            if (Math.abs(e1.getX() - e2.getX()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                                //Log.i(Constants.APP_TAG, "Right to Left");
                                //user "Swiped left"

                                //pass the already bundled info into new events page and slide left
                                Intent toEvenDetailsIntent = new Intent( activity, EventsPage.class);
                                Bundle newBundle = new Bundle();
                                newBundle.putInt("move", 1);
                                toEvenDetailsIntent.putExtras(newBundle);
                                startActivity(toEvenDetailsIntent);
                                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);


                            } else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                                //Log.i(Constants.APP_TAG, "Left to Right");
                                //user "Swiped right"

                                //pass the already bundled info into new events page and slide left
                                Intent toEvenDetailsIntent = new Intent( activity, EventsPage.class);
                                Bundle newBundle = new Bundle();
                                newBundle.putInt("move", -1);
                                toEvenDetailsIntent.putExtras(newBundle);
                                startActivity(toEvenDetailsIntent);
                                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

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
         **/
    }
}
