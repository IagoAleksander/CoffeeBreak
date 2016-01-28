package com.filipe.cofeebreak;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipe on 07/12/drink15.
 */

// the orderActivity class create an order with the products ordered by the user
// and displays the final price, allowing the user to confirm or cancel the order
public class OrderActivity extends Fragment implements View.OnClickListener{

    static List<String> foodNameList = new ArrayList<>();
    static List<String> foodPriceList = new ArrayList<>();

    static List<String> drinkNameList = new ArrayList<>();
    static List<String> drinkPriceList = new ArrayList<>();

    static Double fPrice = 0.00;
    private Button confirm;
    private Button cancel;

    static String message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        //This layout contains your list view
        return inflater.inflate(R.layout.activity_order, container, false);
        }



    @Override
    public void onStart () {
        super.onStart();
        View view = getView();

        // the information showed in the actionbar is changed
        ((MainActivity) getActivity()).setActionBarTitle(4);

        // 4 ListViews are created to be populated with the respective items. The display
        // the ordered foods and drinks, their respective names and prices
        ListView foodName;
        ListView foodPrice;
        ListView drinkName;
        ListView drinkPrice;

        foodName = (ListView) view.findViewById(R.id.foodName);
        foodPrice = (ListView) view.findViewById(R.id.foodPrice);
        drinkName = (ListView) view.findViewById(R.id.drinkName);
        drinkPrice = (ListView) view.findViewById(R.id.drinkPrice);

        confirm = (Button) view.findViewById(R.id.confirm_order) ;
        confirm.setOnClickListener(this);

        cancel = (Button) view.findViewById(R.id.cancel_order) ;
        cancel.setOnClickListener(this);

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                foodNameList );

        foodName.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                foodPriceList );

        foodPrice.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                drinkNameList );

        drinkName.setAdapter(arrayAdapter);

        arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                drinkPriceList );

        drinkPrice.setAdapter(arrayAdapter);

        // A TextView finalPrice displays the total price for the order
        TextView finalPrice = (TextView) view.findViewById(R.id.finalPrice);
        finalPrice.setText("£" + String.format("%.2f" , fPrice));
    }

    // depending on the button that the user clicked, a different message to be displayed
    // is sent to the ConcludeOrder fragment that is, then, called
    public void onClick(View v)
    {
        if (v == confirm)
            message = "Your order was confirmed!!!";
        else if (v == cancel)
            message = "Your order was canceled!!!";

        Fragment newFragment = new ConcludedOrder();

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

