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
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwh on 11/13/15.
 */
public class PhotoGallaryFragment extends Fragment{
    private static final String TAG = "PhotoGallaryfragment";
    private RecyclerView mPhotoRecyclerView;
    private List<GallaryItem> mItems = new ArrayList<>();

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

    private void setupAdapter(){
        if(isAdded()){
            mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems));
        }
    }

    private class PhotoHolder extends RecyclerView.ViewHolder{
        private TextView mTitleTextView;

        public PhotoHolder(View itemView){
            super(itemView);

            mTitleTextView = (TextView)itemView;
        }

        public void bindGallaryItem(GallaryItem item){
            mTitleTextView.setText(item.toString());
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder>{
        private List<GallaryItem> mGallaryItems;

        public PhotoAdapter(List<GallaryItem> gallaryItems){
            mGallaryItems = gallaryItems;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
            TextView textView = new TextView(getActivity());
            return new PhotoHolder(textView);
        }

        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position){
            GallaryItem gallaryItem = mGallaryItems.get(position);
            photoHolder.bindGallaryItem(gallaryItem);
        }

        @Override
        public int getItemCount(){
            return mGallaryItems.size();
        }
    }

    private class FetchItemTask extends AsyncTask<Void, Void, List<GallaryItem>>{
        @Override
        protected List<GallaryItem> doInBackground(Void...params){
            return new FlickrFechr().fetchItems();
        }

        @Override
        protected void onPostExecute(List<GallaryItem> items){
            mItems = items;
            setupAdapter();
        }
    }
}
