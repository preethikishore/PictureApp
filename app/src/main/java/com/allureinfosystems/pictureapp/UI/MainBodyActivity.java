package com.allureinfosystems.pictureapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.allureinfosystems.pictureapp.R;
import com.allureinfosystems.pictureapp.data.AndroidImageAssets;

public class MainBodyActivity extends AppCompatActivity implements MasterListFragment.onImageClickListner {

    private int bodyIndex;
    private int legIndex;
    private int headIndex;
    private Button next;
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_body);
        next  = (Button) findViewById(R.id.button_next);
        if(findViewById(R.id.android_me_linear_layout) != null) {
            // This LinearLayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            // Change the GridView to space out the images more on tablet
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            // Getting rid of the "Next" button that appears on phones for launching a separate activity

            next.setVisibility(View.GONE);

            if(savedInstanceState == null) {
                // In two-pane mode, add initial BodyPartFragments to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Creating a new head fragment
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setmImageId(AndroidImageAssets.getHeads());
                // Add the fragment to its container using a transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();

                // New body fragment
                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setmImageId(AndroidImageAssets.getBodies());
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyFragment)
                        .commit();

                // New leg fragment
                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setmImageId(AndroidImageAssets.getLegs());
                fragmentManager.beginTransaction()
                        .add(R.id.leg_conatiner, legFragment)
                        .commit();
            }
        } else {
            // We're in single-pane mode and displaying fragments on a phone in separate activities
            mTwoPane = false;
        }


    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this,String.valueOf(position),Toast.LENGTH_LONG).show();
        int bodyPartNumber = position /12;
        int listIndex = position - 12 * bodyPartNumber ;
        if (mTwoPane) {
            // Create two=pane interaction

            BodyPartFragment newFragment = new BodyPartFragment();

            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    // A head image has been clicked
                    // Give the correct image resources to the new fragment
                    newFragment.setmImageId(AndroidImageAssets.getHeads());
                    newFragment.setmIndex(listIndex);
                    // Replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setmImageId(AndroidImageAssets.getBodies());
                    newFragment.setmIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setmImageId(AndroidImageAssets.getLegs());
                    newFragment.setmIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_conatiner, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {

            // Handle the single-pane phone case by passing information in a Bundle attached to an Intent

            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }
        }
        final Bundle b = new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("legIndex",legIndex);
        b.putInt("bodyIndex",bodyIndex);
        final Intent intent = new Intent(this,MainActivity.class);
        intent.putExtras(b);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(intent);
            }
        });

    }
}