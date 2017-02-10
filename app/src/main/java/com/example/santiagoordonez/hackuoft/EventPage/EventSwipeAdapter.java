package com.example.santiagoordonez.hackuoft.EventPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.santiagoordonez.hackuoft.dto.EventDTO;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Ose on 1/8/2017.
 */

public class EventSwipeAdapter extends FragmentPagerAdapter {

    private ArrayList<EventDTO> eventDTOs;

    //This constructor accepts an array of Event classes that it will iterate through as swipe objects.
    public EventSwipeAdapter(FragmentManager fm, ArrayList<EventDTO> events) {
        super(fm);
        eventDTOs = events;
    }

    @Override
    //This method gives us the current Event object using a position as it's index in the list.
    public Fragment getItem(int position) {
        EventPageFragment fragment = new EventPageFragment();

        //get Event that we are using
        EventDTO current_event = eventDTOs.get(position);

        //get the title
        String name = current_event.getName();
        //Get Date
        String date = current_event.getStartTime();
        //get the id
        String id = current_event.getEventId();
        //get the description
        String description = current_event.getDescription();
        //pass in the location
        String location = current_event.getLocation();
        //get picture
        String picture = current_event.getPictureUrl();


        //This will allow us to pass variables from the HackathonEvent Objects to the fragments
        Bundle bundle = new Bundle();


        //Right now we are passing all the needed variables to the position fragment.
        bundle.putString("title", name);
        bundle.putString("date", date);
        bundle.putString("id", id);
        bundle.putString("description", description);
        bundle.putString("location", location);
        bundle.putInt("position", position);
        bundle.putString("picUrl", picture);

        //add bundle as an argument
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    //This method gets the total number of elements in the Event List.
    public int getCount() {

        return eventDTOs.size();
    }
}
