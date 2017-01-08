package com.example.santiagoordonez.hackuoft.HackPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Ose on 1/7/2017.
 */

public class HackSwipeAdapter extends FragmentPagerAdapter {
    //TODO: This constructor will have to accept an array of HackathonEvent classes that it will iterate through as swipe objects.
    public HackSwipeAdapter(FragmentManager fm /*, Array<HackathonEvent>*/) {
        super(fm);
    }

    @Override
    //TODO: This method will give us the current HackathonEvent object using a position as it's index in the list.
    public Fragment getItem(int position) {
        HackPageFragment fragment = new HackPageFragment();

        //This will allow us to pass variables from the HackathonEvent Objects to the fragments
        Bundle bundle = new Bundle();

        //Right now we are passing a variable called "position" that gives the position of an object
        bundle.putInt("position", position);

        //add bundle as an argument
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    //TODO: This method will get the total number of elements in the HackathonEvent List.
    public int getCount() {

        // we will leave this as static for now
        return 5;
    }
}
