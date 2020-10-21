package com.allureinfosystems.pictureapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.allureinfosystems.pictureapp.R;
import com.allureinfosystems.pictureapp.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null) {
            BodyPartFragment headManager = new BodyPartFragment();
            headManager.setmImageId(AndroidImageAssets.getHeads());
            int  headIndex = getIntent().getIntExtra("headIndex",0);
            headManager.setmIndex(headIndex);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.head_container, headManager).commit();

            //////////////////////////////////////////////////
            BodyPartFragment bodymanager = new BodyPartFragment();
            bodymanager.setmImageId(AndroidImageAssets.getBodies());
            int bodyIndex = getIntent().getIntExtra("bodyIndex",0);
            bodymanager.setmIndex(bodyIndex);
            fragmentManager.beginTransaction().add(R.id.body_container, bodymanager).commit();
            /////////////////////
            BodyPartFragment legmanager = new BodyPartFragment();
            legmanager.setmImageId(AndroidImageAssets.getLegs());
            int legIndex = getIntent().getIntExtra("legIndex",0);
            legmanager.setmIndex(legIndex);
            fragmentManager.beginTransaction().add(R.id.leg_conatiner, legmanager).commit();
        }

    }


}
