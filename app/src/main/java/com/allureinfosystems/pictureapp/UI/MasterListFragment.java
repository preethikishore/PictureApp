package com.allureinfosystems.pictureapp.UI;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allureinfosystems.pictureapp.R;
import com.allureinfosystems.pictureapp.data.AndroidImageAssets;

import java.util.ArrayList;

public class MasterListFragment extends Fragment {

    MasterListAdapter masterListAdapter;
    onImageClickListner mCallback;

    public interface onImageClickListner
    {
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (onImageClickListner) context;

        }catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()+"Must Implement The Listener");
        }
    }

    public MasterListFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_list,container,false);
        GridView grid = rootView.findViewById(R.id.images_grid_view);
        masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        grid.setAdapter(masterListAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.onImageSelected(i);

            }
        });
       return rootView;
    }


}
