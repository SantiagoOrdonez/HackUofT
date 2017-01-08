package com.example.santiagoordonez.hackuoft;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jayrebgz on 2017-01-07.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {

        switch (arg0) {
            case 0:
                return new HackathonsPage();
            case 1:
                return new EventsPage();
            case 2:
                return new NewsPage();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
