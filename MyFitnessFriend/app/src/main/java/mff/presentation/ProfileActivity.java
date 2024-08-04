package mff.presentation;

import mff.R;
import mff.application.Main;
import mff.business.AccessProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;


public class ProfileActivity extends Activity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private static final AccessProfile profile = new AccessProfile();

    /**
     * onCreate
     * called when this page is created to manage its creation
     * @param savedInstanceState - the instance state to be passed on to the super constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_ui);

        // replace text on the page with proper variables
        fillFields();
    }

    /**
     * onDestroy
     * called when this page is destroyed to manage its destruction
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    /**
     * backArrowOnClick
     * switches the view back to the main page when the back arrow in the top left is clicked
     * @param v - the current view
     */
    public void backArrowOnClick(View v) {
        Intent home = new Intent(ProfileActivity.this, HomeActivity.class);
        home.putExtra("calorieGoal", profile.getCalorieGoal());
        ProfileActivity.this.startActivity(home);
    }

    /**
     * changeCalorieGoalOnClick
     * creates the calorie goal popup and its event listeners
     * @param v - the current view
     */
    public void changeCalorieGoalOnClick(View v) {
        LayoutInflater inflater;
        View popupView;
        PopupWindow popupWindow;
        Button okButton;
        Button cancelButton;

        // create and display the popup view
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.change_calories_goal, null);
        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // find the popups ok and cancel buttons
        okButton = (Button) popupView.findViewById(R.id.okButton);
        cancelButton = (Button) popupView.findViewById(R.id.cancelButton);

        // set an onclick listener to the ok button to submit data entered in the popup
        okButton.setOnClickListener( new View.OnClickListener() {
            /**
             * onClick
             * submits valid data to the db
             * @param v - the current view
             */
            @Override
            public void onClick(View v) {
                int newGoal;

                setContentView(R.layout.profile_ui);

                try {
                    // sets the goal text and parses it into an int
                    newGoal = Integer.parseInt(((EditText)(v.getParent().focusSearch(v, View.FOCUS_UP))).getText().toString());
                    if (newGoal >= 0) {
                        // if it is a positive value, update the goal, close the popup and update the screen
                        profile.setCalorieGoal(newGoal);
                        popupWindow.dismiss();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    fillFields();
                }
            }
        });

        // add an onclick listener to the cancel button to close the popup
        cancelButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onCLick
             * closes the popup when clicked
             * @param v - the current view
             */
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public void changeWaterGoalOnClick(View v) {
        LayoutInflater inflater;
        View popupView;
        PopupWindow popupWindow;
        Button okButton;
        Button cancelButton;

        // create and display the popup view
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = inflater.inflate(R.layout.change_water_goal, null);
        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

        // find the popups ok and cancel buttons
        okButton = (Button) popupView.findViewById(R.id.okButton);
        cancelButton = (Button) popupView.findViewById(R.id.cancelButton);

        // set an onclick listener to the ok button to submit data entered in the popup
        okButton.setOnClickListener( new View.OnClickListener() {
            /**
             * onClick
             * submits valid data to the db
             * @param v - the current view
             */
            @Override
            public void onClick(View v) {
                int newGoal;

                setContentView(R.layout.profile_ui);

                try {
                    // sets the goal text and parses it into an int
                    newGoal = Integer.parseInt(((EditText)(v.getParent().focusSearch(v, View.FOCUS_UP))).getText().toString());
                    if (newGoal >= 0) {
                        // if it is a positive value, update the goal, close the popup and update the screen
                        profile.setWaterGoal(newGoal);
                        popupWindow.dismiss();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } finally {
                    fillFields();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            /**
             * onCLick
             * closes the popup when clicked
             * @param v - the current view
             */
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * fillFields
     * fills in the text fields of the profile page with values from appropriate variables
     */
    private void fillFields() {
        ((TextView)findViewById(R.id.calorieGoalAmount)).setText(profile.getCalorieGoal());
        ((TextView)findViewById(R.id.waterGoalAmount)).setText(profile.getUserWaterGoal());
        ((TextView)findViewById(R.id.profileEmail)).setText(profile.getUserEmail());
        ((TextView)findViewById(R.id.profileName)).setText(profile.getUserName());
    }
}
