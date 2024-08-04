package mff.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mff.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    ArrayList<String> foodNames;
    ArrayList<String> foodServing;
    ArrayList<String> calories;
    Context context;

    /**
     * RecyclerViewAdapter
     * sets up list to show, allows it to be filtered based on what user is typing
     * @param ct - context
     * @param names - names of foods to display
     * @param servings - serving sizes
     * @param cals - calories
     */
    public RecyclerViewAdapter(Context ct, ArrayList<String> names,  ArrayList<String> servings, ArrayList<String> cals ){
        this.context = ct;
        this.foodNames = names;
        this.foodServing = servings;
        this.calories = cals;
    }

    @Override
    /**
     * onCreateViewHolder
     * sets up list to show, allows it to be filtered based on what user is typing
     * @param parent - parent object
     * @param viewType - type of view
     */
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meal_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    /**
     * onBindViewHolder
     * sets text into view holder
     * @param holder - view holder
     * @param position - current item
     */
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.foodName.setText(foodNames.get(position));
        holder.foodServings.setText(foodServing.get(position));
        holder.foodCalories.setText(calories.get(position));
    }

    @Override
    /**
     * getItemCount
     * returns size of foodNames list
     */
    public int getItemCount() {
        return foodNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView foodName;
        TextView foodServings;
        TextView foodCalories;

        /**
         * MyViewHolder
         * set up list to show correct name, servings and calories
         * @param view - current view
         */
        public MyViewHolder(View view){
            super(view);
            foodName = view.findViewById(R.id.itemFoodTitle);
            foodServings = view.findViewById(R.id.itemFoodServings);
            foodCalories = view.findViewById(R.id.itemFoodCalories);
        }

    }

}
