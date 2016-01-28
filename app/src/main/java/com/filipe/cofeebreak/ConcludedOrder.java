package com.filipe.cofeebreak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by alks_ander on 10/12/15.
 */
public class ConcludedOrder extends Fragment implements View.OnClickListener{

    private Button goBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //This layout contains your list view
        return inflater.inflate(R.layout.order_conclusion, container, false);
    }

    // When the fragment starts, it will display a message confirming the previous action of the user
    @Override
    public void onStart () {
        super.onStart();
        View view = getView();

        // When the fragment is started, it changes the text showed in the action bar
        ((MainActivity) getActivity()).setActionBarTitle(0);

        TextView message = (TextView) view.findViewById(R.id.message);
        message.setText(OrderActivity.message);

        goBack = (Button) view.findViewById(R.id.goBack) ;
        goBack.setOnClickListener(this);

    }

    // When the user clicks the button, the information referent to the previous order will be cleared
    // and the program will go back to the main screnn
    public void onClick(View v)
    {
        Fragment newFragment = new TopActivity();

        OrderActivity.drinkNameList.clear();
        OrderActivity.drinkPriceList.clear();
        OrderActivity.fPrice = 0.00;
        OrderActivity.foodNameList.clear();
        OrderActivity.foodPriceList.clear();

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
