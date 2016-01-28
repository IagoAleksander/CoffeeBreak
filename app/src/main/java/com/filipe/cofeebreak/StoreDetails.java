package com.filipe.cofeebreak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by alks_ander on 10/12/15.
 */

// this fragments displays information about the store
public class StoreDetails extends Fragment implements View.OnClickListener{

    private Button cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //This layout contains your list view
        return inflater.inflate(R.layout.activity_store, container, false);
    }



    @Override
    public void onStart () {
        super.onStart();
        View view = getView();

        // When the fragment is started, it changes the text showed in the action bar
        ((MainActivity) getActivity()).setActionBarTitle(4);

        cancel = (Button) view.findViewById(R.id.back_button) ;
        cancel.setOnClickListener(this);

    }

    //this function is activated when the user clicks on the Go Back button. It will redirect the user to the main screen
    public void onClick(View v)
    {
        Fragment newFragment = new TopActivity();

        // Begin transaction
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        return;

    }
}
