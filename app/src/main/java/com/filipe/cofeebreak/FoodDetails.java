package com.filipe.cofeebreak;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Filipe on 09/12/15.
 */

//this fragment will show details of a given item and allows the user to include the item in the order or cancel the action
public class FoodDetails extends Fragment implements View.OnClickListener{

    public static int foodNo;
    private Button order;
    private Button cancel;

    public FoodDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.item_details, container, false);
    }

    // when it starts, it changes the ImageView source and the TextView texts to the correspondent
    // in the drinks array
    @Override
    public void onStart () {
        super.onStart();
        View view = getView();
        if (view != null) {
            order = (Button) view.findViewById(R.id.order_button);
            order.setOnClickListener(this);

            cancel = (Button) view.findViewById(R.id.cancel_button);
            cancel.setOnClickListener(this);

            Food food = Food.foods[foodNo];

            ImageView photo = (ImageView) view.findViewById(R.id.photo);
            photo.setImageResource(food.getImageResourceId());
            photo.setContentDescription(food.getName());

            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(food.getName());

            TextView description = (TextView) view.findViewById(R.id.description);
            description.setText(food.getDescription());

            TextView price = (TextView) view.findViewById(R.id.price);
            price.setText("Price: £" + food.getPrice());
        }
    }

    public void onClick(View v)
    {
        // a new fragment FoodCategory is created.
        Fragment newFragment = new FoodCategory();

        // if the clicked button is the order button, the fragment is replaced by an OrderActivity fragment
        // and some information is passed to the new fragment.
        if (v == order) {
            OrderActivity.foodNameList.add(Food.foods[foodNo].getName());
            OrderActivity.foodPriceList.add("£" + String.format("%.2f", Food.foods[foodNo].getPrice()));
            OrderActivity.fPrice += Food.foods[foodNo].getPrice();

            newFragment = new OrderActivity();
        }

        // the transaction for the new fragment is then started
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
