package com.bignerdranch.android.photogallary;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by cwh on 11/13/15.
 */
public class PhotoGallaryFragment extends Fragment{
    private static final String TAG = "PhotoGallaryfragment";
    private RecyclerView mPhotoRecyclerView;

    public static PhotoGallaryFragment newInstance(){
        return new PhotoGallaryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        new FetchItemTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_photo_gallary, container, false);

        mPhotoRecyclerView = (RecyclerView)v
                .findViewById(R.id.fragment_photo_gallary_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return v;
    }

    private class FetchItemTask extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void...params){
            new FlickrFechr().fetchItems();
            return null;
        }
    }
}
