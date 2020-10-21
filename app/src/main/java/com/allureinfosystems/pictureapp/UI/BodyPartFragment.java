package com.allureinfosystems.pictureapp.UI;

import android.app.PendingIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allureinfosystems.pictureapp.R;
import com.allureinfosystems.pictureapp.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    private List<Integer> mImageId;
    private int mIndex;
    private static final String IMAGE_LIST ="mImageId";
    private static final String IMAGE_INDEX = "mIndex";


    public BodyPartFragment()
    {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
       View rootView = inflater.inflate(R.layout.fragment_body_part,container,false);
       if(savedInstanceState != null)
       {
           mImageId = savedInstanceState.getIntegerArrayList(IMAGE_LIST);
           mIndex = savedInstanceState.getInt(IMAGE_INDEX);
       }
       final ImageView image = rootView.findViewById(R.id.body_part_image_view);
       if(!(mImageId == null)) {
           image.setImageResource(mImageId.get(mIndex));
           image.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(mIndex < mImageId.size()-1)
                   {
                       mIndex++;
                       Log.i("midex",String.valueOf(mIndex));
                   }else
                   {
                       mIndex = 0;
                   }
                   image.setImageResource(mImageId.get(mIndex));
               }
           });

       }else
       {
           Log.i("Info","List Is Empty");
       }
       return rootView;
}

    public void setmImageId(List<Integer> mImageId) {
        this.mImageId = mImageId;
    }

    public void setmIndex(int mIndex) {
        this.mIndex = mIndex;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
       outState.putIntegerArrayList(IMAGE_LIST, (ArrayList<Integer>)mImageId);
       outState.putInt(IMAGE_INDEX,mIndex);
    }


}
