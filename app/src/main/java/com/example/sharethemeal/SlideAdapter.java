package com.example.sharethemeal;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SlideAdapter extends FragmentStatePagerAdapter {
    public SlideAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new ScreenOne();
            case 1:
                return new ScreenTwo();
            case 2:
                return new ScreenThree();
            case 3:
                return new ScreenFour();
            default:
                return new ScreenOne();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
