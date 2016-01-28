package com.filipe.cofeebreak;

/**
 * Created by Filipe on 09/12/15.
 */
// class Food -> will add some new objects to the string foods -> Every new food item in the menu must be added here
public class Food {

    private String name;
    private String description;
    private int imageResourceId;
    private double price;

    public static final Food[] foods = {

            new Food ("Bread", "Slices of fresh bread...", R.drawable.food9, 2.49),
            new Food ("Cereal", "Perfect with milk...",R.drawable.food2, 4.19),
            new Food ("Bagel", "The Polish taste...",R.drawable.food4, 3.39),
            new Food ("Pretzel", "The German taste...",R.drawable.food5, 3.69),
            new Food ("Oatmeal", "Ready to eat...",R.drawable.food1, 4.39),
            new Food ("Popcorn", "In a mood for a movie?...",R.drawable.food3, 3.59),
            new Food ("Chips", "Crispy and salty chips...",R.drawable.food8, 6.39),
            new Food ("Rice", "Try it...",R.drawable.food7, 7.29),
            new Food ("Spaghetty", "The best of London...",R.drawable.food6, 9.39),
            new Food ("Ravioli", "In a mood for some Italian pasta?...",R.drawable.food10, 10.19)
    };

    public Food(String name, String description, int imageResourceId, double price){
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
