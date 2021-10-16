package com.example.loginapp.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.loginapp.fragments.ContentFragment;

import com.example.loginapp.R;

/**
 * CategoryAdapter provide the layout for
 * each list item based on a data source which is a list of Word objects.
 */

public class CategoryAdapter extends FragmentPagerAdapter  {
    /** Context of the app */
    private final Context mContext;
    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ContentFragment("number");
        } else if (position == 1) {
            return new ContentFragment("family");
        } else if (position == 2) {
            return new ContentFragment("color");
        } else {
            return new ContentFragment("phrases");
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {

        return 4;
    }

//  //  @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return false;
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_family);
        } else if (position == 2) {
            return mContext.getString(R.string.category_colors);
        } else {
            return mContext.getString(R.string.category_phrases);
        }
    }
}

