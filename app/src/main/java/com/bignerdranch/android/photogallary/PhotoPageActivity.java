package com.bignerdranch.android.photogallary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * Created by cwh on 11/24/15.
 */
public class PhotoPageActivity extends SingleFragmentActivity{
    public static Intent newIntent(Context context, Uri photoPageUri){
        Intent i = new Intent(context, PhotoPageActivity.class);
        i.setData(photoPageUri);
        return i;
    }

    @Override
    protected Fragment createFragment(){
        return PhotoPageFragment.newIntent(getIntent().getData());
    }
}
