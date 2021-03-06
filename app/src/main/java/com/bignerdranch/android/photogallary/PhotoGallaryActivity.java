package com.bignerdranch.android.photogallary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class PhotoGallaryActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context context){
        return new Intent(context, PhotoGallaryActivity.class);
    }
    @Override
    public Fragment createFragment(){
        return PhotoGallaryFragment.newInstance();
    }

}
