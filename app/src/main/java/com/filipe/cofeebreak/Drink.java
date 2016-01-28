package com.filipe.cofeebreak;

/**
 * Created by Filipe on 05/12/drink15.
 */

// class Drink -> will add some new objects to the string drinks -> Every new drink item in the menu must be added here
public class Drink {

    private String name;
    private String description;
    private int imageResourceId;
    private double price;

    public static final Drink[] drinks = {

        new Drink ("Black", "A couple of espresso...", R.drawable.drink2, 2.69),
            new Drink ("Latte", "A couple of espresso...",R.drawable.drink7, 3.19),
            new Drink ("Frozen", "A freezing glass of frozen coffee...",R.drawable.drink1, 4.39),
            new Drink ("Tea", "A lovely cup of tea...",R.drawable.drink3, 3.99),
            new Drink ("Juice", "A tasteful glass of juice...",R.drawable.drink4, 5.39),
            new Drink ("Soda", "A refreshing glass of soda...",R.drawable.drink5, 3.69),
            new Drink ("Milkshake", "A delicious desert...",R.drawable.drink6, 5.09),
            new Drink ("Milk", "A glass of integral milk...",R.drawable.drink9, 2.89),
            new Drink ("Water", "Zero calories...",R.drawable.drink8, 1.99),
            new Drink ("Beer", "For the happy people...",R.drawable.drink10, 4.79)
    };

    public Drink(String name, String description, int imageResourceId, double price){
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return this.name;
    }

}
