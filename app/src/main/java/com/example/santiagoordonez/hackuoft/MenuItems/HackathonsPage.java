package com.example.santiagoordonez.hackuoft.MenuItems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.example.santiagoordonez.hackuoft.R;


/**
 * Created by jayrebgz on 2017-01-07.
 */

public class HackathonsPage extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hackathons_page_layout, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_UP){
                    //User has clicked the image.
                    Intent intent = new Intent(view.getContext(), com.example.santiagoordonez.hackuoft.HackPage.HackathonsPage.class);
                    startActivity(intent);
                }
                return true;
            }
        });


    }


}
