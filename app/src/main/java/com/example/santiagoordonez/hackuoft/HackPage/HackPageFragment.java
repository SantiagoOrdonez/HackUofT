package com.example.santiagoordonez.hackuoft.HackPage;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import com.example.santiagoordonez.hackuoft.R;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class HackPageFragment extends Fragment {
    Bundle bundle;
    String title;
    String school_name;
    String cost;
    String facebook_url;
    String picture_url;

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
        picture_url = bundle.getString("picture_url", picture_url);

//        new DownloadImageTask(hack_background)
//                .execute(picture_url);
//        try {
//            URL url = new URL(picture_url);
//            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            hack_background.setImageBitmap(bmp);
//        } catch (MalformedURLException e) {
//            System.out.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //set the text of the screen
        hackView.setText(title);
        schoolNameView.setText(school_name);

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView cool_pic = (ImageView) getActivity().findViewById(R.id.imageView);

        ImageView hack_background = (ImageView) getActivity().findViewById(R.id.hack_fragment_image);
        if(picture_url != null && !picture_url.equals("")){
            Picasso.with(getContext()).load(picture_url).into(hack_background);
        }else{
            hack_background.setImageResource(R.drawable.hack_screen_pic);
        }

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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



}
