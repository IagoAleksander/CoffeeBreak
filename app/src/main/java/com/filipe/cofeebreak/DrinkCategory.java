package com.filipe.cofeebreak;

import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
// here, the information necessary to populate the adapted ListView referent to drinks is collected and prepared
public class DrinkCategory extends ListFragment {


    // Array of strings storing country names
    List<String> drinkName = new ArrayList<String>();

    // Array of integers points to drinkImage stored in /res/drawable/
    List<Integer> drinkImage = new ArrayList<Integer>();

    // Array of strings to store currencies
    List <Double> drinkPrice = new ArrayList<Double>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // the information showed in the actionbar is changed
        ((MainActivity) getActivity()).setActionBarTitle(1);

        //populate lists
        for (int i = 0; i < 10; i++)
        {
            drinkName.add(Drink.drinks[i].getName());
            drinkImage.add(Drink.drinks[i].getImageResourceId());
            drinkPrice.add(Drink.drinks[i].getPrice());
        }

        // Each row in the list stores item name, price and image
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("product", drinkName.get(i));
            hm.put("price", "£" + drinkPrice.get(i));
            hm.put("image", Integer.toString(drinkImage.get(i)) );
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "image","product","price" };

        // Ids of views in listview_layout
        int[] to = { R.id.image,R.id.txt,R.id.price};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.listview_layout, from, to);

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // when a given item in the list is clicked by the user, the DrinkDetails fragment correspondent is opened
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        // Defines which information will be showed
        DrinkDetails.drinkNo = position;

        Fragment newFragment = new DrinkDetails();
        // Begin transaction
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }

}
