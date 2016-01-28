package com.filipe.cofeebreak;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

// this Activity starts the actionBar and the Drawer in the app and then call the fragment TopActivity to its fragment_container
public class MainActivity extends ActionBarActivity {

    //private variables for the array lists
    private String[] titles;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    public int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null)
                return;

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new TopActivity()).commit();
        }

        //get reference from strings.xml titles array
        //use arrayadapter to populate list view
        titles = getResources().getStringArray(R.array.titles);
        drawerList = (ListView)findViewById(R.id.drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, titles));
        //add a new instance of our onItemClickListener to the drawer's ListView.
        drawerList.setOnItemClickListener(new DrawerItemClickListener());

        //if activity is newly created use default selectItem method to display TopFragment
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("position");
            setActionBarTitle(currentPosition);
        }

        else
            selectItem(0);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // if the see_order_action button in the action bar be clicked by the user, it redirects to th Order Activity fragment
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_see_order:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new OrderActivity())
                        .addToBackStack(null).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //when user click on an item in the navigation drawer the onitemclick method gets called and call the
    //selectItem function with the respective position clicked by the user
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
            //call the selection method when an item gets clicked
            selectItem(position);
        }
    };

    // depending on the position that was clicked on the drawer, a given fragment is called
    private void selectItem(int position) {
        currentPosition = position;
        Fragment fragment;

        switch (position) {
            case 1:
                fragment = new DrinkCategory();
                break;
            case 2:
                fragment = new FoodCategory();
                break;
            case 3:
                fragment = new StoreDetails();
                break;
            case 4:
                fragment = new OrderActivity();
                break;
            default:
                fragment = new TopActivity();
        }

        // Begin the transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

        //call the setactionbartitle method and pass the position of the clicked item
        setActionBarTitle(position);

        //get reference to drawer and close it
        drawerLayout.closeDrawer(drawerList);
    }

    //set actionbartitle according to position
    public void setActionBarTitle (int position) {
        currentPosition = position;
        String title;
        if (position == 0)
            title = getResources().getString(R.string.app_name);
        else
            title = titles[position];

       getSupportActionBar().setTitle(title);

    }

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", currentPosition);
    }





}
