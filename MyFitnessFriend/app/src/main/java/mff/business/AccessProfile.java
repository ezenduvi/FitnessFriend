package mff.business;

import mff.application.Main;
import mff.application.Services;
import mff.objects.User;
import mff.persistence.Database;

public class AccessProfile {
    private Database dataAccess;

    /**
     * AccessProfile
     * creates the database access
     */
    public AccessProfile() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    /**
     * getUserName
     * @return - the user name for the user
     */
    public String getUserName() {
        return dataAccess.getUser().getUserName();
    }

    /**
     * getUserEmail
     * @return - the email for the user
     */
    public String getUserEmail() {
        return dataAccess.getUser().getUserEmail();
    }

    /**
     * getUserWaterGoal
     * @return - the water goal for the user
     */
    public String getUserWaterGoal() {
        return dataAccess.getUser().getWaterGoal() + " cups";
    }

    /**
     * getCalorieGoal
     * @return - the calorie goal for the user with the unit calories
     */
    public String getCalorieGoal() {
        return dataAccess.getUser().getCalorieGoal() + " calories";
    }

    /**
     * setCalorieGoal
     * @param goal - the new goal for the user, must be a positive number
     * @throws IllegalArgumentException - if passed goal is negative
     */
    public void setCalorieGoal(int goal) {
        if (goal < 0) {
            throw new IllegalArgumentException("Can not set negative calorie goal");
        }

        dataAccess.setUserCalorieGoal(goal);
    }

    /**
     * getUser
     * @return user
     */
    public User getUser(){
        return dataAccess.getUser();
    }

    /**
     * setWaterGoal
     * @param goal - the new goal for the user, must be a positive number
     * @throws IllegalArgumentException - if passed goal is negative
     */
    public void setWaterGoal(int goal) {
        if (goal < 0) {
            throw new IllegalArgumentException("Can not set negative water goal");
        }
        dataAccess.setUserWaterGoal(goal);
    }
}
