package com.filipe.cofeebreak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Filipe on 05/12/drink15.
 */

// the topActivity class displays an image and the main menu and allows the user to choose an option to be redirected
public class TopActivity extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //This layout contains your list view
        return inflater.inflate(R.layout.activity_top, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {

                Fragment newFragment;

                switch (position) {
                    case 0:
                        newFragment = new DrinkCategory();
                        break;
                    case 1:
                        newFragment = new FoodCategory();
                        break;
                    case 2:
                        newFragment = new StoreDetails();
                        break;
                    default:
                        newFragment = new TopActivity();
                }

                    // Begin the transaction
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack if needed
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();

            }
        };

        ListView listview = (ListView) view.findViewById(R.id.list_options);
        listview.setOnItemClickListener(itemClickListener);
    }


}
