package com.example.santiagoordonez.hackuoft.EventPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.santiagoordonez.hackuoft.dto.EventDTO;

import java.util.ArrayList;

/**
 * Created by Ose on 1/8/2017.
 */

public class EventSwipeAdapter extends FragmentPagerAdapter {

    ArrayList<EventDTO> eventDTOs;

    //TODO: This constructor will have to accept an array of Event classes that it will iterate through as swipe objects.
    public EventSwipeAdapter(FragmentManager fm ) {
        super(fm);
    }

    //set global events
    public void setEventsArrayList(ArrayList<EventDTO> events){
        this.eventDTOs = events;
    }

    @Override
    //TODO: This method will give us the current Event object using a position as it's index in the list.
    public Fragment getItem(int position) {
        EventPageFragment fragment = new EventPageFragment();

        //get Event that we are using
        EventDTO current_event = eventDTOs.get(position);

        //get the title
        String name = current_event.getName();

        //This will allow us to pass variables from the HackathonEvent Objects to the fragments
        Bundle bundle = new Bundle();

        //Right now we are passing a variable called "position" that gives the position of an object
        bundle.putString("title", name);

        //add bundle as an argument
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    //TODO: This method will get the total number of elements in the Event List.
    public int getCount() {

        // we will leave this as static for now
        return 5;
    }
}
