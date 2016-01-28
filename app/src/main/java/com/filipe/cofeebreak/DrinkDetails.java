package com.filipe.cofeebreak;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

//this fragment will show details of a given item and allows the user to include the item in the order or cancel the action
public class DrinkDetails extends Fragment implements View.OnClickListener {

    public static int drinkNo;
    private Button order;
    private Button cancel;

    public DrinkDetails() {
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
    public void onStart (){
        super.onStart();
        View view = getView();
        if (view != null)
        {
            order = (Button) view.findViewById(R.id.order_button) ;
            order.setOnClickListener(this);

            cancel = (Button) view.findViewById(R.id.cancel_button) ;
            cancel.setOnClickListener(this);

            Drink drink = Drink.drinks[drinkNo];

            ImageView photo = (ImageView) view.findViewById(R.id.photo);
            photo.setImageResource(drink.getImageResourceId());
            photo.setContentDescription(drink.getName());

            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(drink.getName());

            TextView description = (TextView) view.findViewById(R.id.description);
            description.setText(drink.getDescription());

            TextView price = (TextView) view.findViewById(R.id.price);
            price.setText("Price: £" + drink.getPrice());
        }

    }

    // this function is activated only if the user clicks on a button.
    public void onClick(View v)
    {
        // a new fragment DrinkCategory is created.
        Fragment newFragment = new DrinkCategory();

        // if the clicked button is the order button, the fragment is replaced by an OrderActivity fragment,
        // some information is passed to the new fragment and the item is included in the order.
        if (v == order) {
            OrderActivity.drinkNameList.add(Drink.drinks[drinkNo].getName());
            OrderActivity.drinkPriceList.add("£" + String.format("%.2f" , Drink.drinks[drinkNo].getPrice()));
            OrderActivity.fPrice += Drink.drinks[drinkNo].getPrice();

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
