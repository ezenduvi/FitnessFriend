package mff.objects;

public class User {

    private String userName;
    private String userEmail;
    private int calorieGoal;
    private int waterGoal;

    /**
     * User
     * creates a User using the values passed.
     * Doesn't allow negative values for calorieGoal and waterGoal.
     * @param name - the name of the user
     * @param email - the email address of the user
     * @param newCalorieGoal - the maximum daily calories the user wants to consume
     * @param newWaterGoal - the target number of cups of water the user wants to consume
     */
    public User(String name, String email, int newCalorieGoal, int newWaterGoal) {
        if(name != null) {
            this.userName = name;
        } else {
            this.userName = "no-name";
        }

        if(email != null && isValid(email)) {
            this.userEmail = email;
        } else {
            this.userEmail = "no-email";
        }

        if(newCalorieGoal >= 0) {
            this.calorieGoal = newCalorieGoal;
        }
        else {
            this.calorieGoal = 0;
        }

        if(newWaterGoal >= 0) {
            this.waterGoal = newWaterGoal;
        }
        else {
            this.waterGoal = 0;
        }
    }
    /**
     * User
     * creates a User using the values passed.
     * Sets calorieGoal and waterGoal to zero since they are not passed as parameters.
     * @param name - the name of the user
     * @param email - the email address of the user
     */
    public User(String name, String email) {
        this.calorieGoal = 0;
        this.waterGoal = 0;

        if(name != null) {
            this.userName = name;
        } else {
            this.userName = "no-name";
        }

        if(email != null && isValid(email)) {
            this.userEmail = email;
        } else {
            this.userEmail = "no-email";
        }
    }
    /**
     * getUserName
     * @return - the user's name
     */
    public String getUserName() {return (userName); }

    /**
     * getUserEmail
     * @return - the user's email address
     */
    public String getUserEmail() {return (userEmail); }

    /**
     * getCalorieGoal
     * @return - the maximum daily calories the user wants to consume
     */
    public int getCalorieGoal() {return (calorieGoal); }

    /**
     * getWaterGoal
     * @return - the target number of cups of water the user wants to consume
     */
    public int getWaterGoal() {return (waterGoal); }

    /**
     * setCalorieGoal
     * @param newCalorieGoal - the new maximum daily calories the user wants to consume
     */
    public void setCalorieGoal(int newCalorieGoal) {
        if(newCalorieGoal >= 0 ) {
            this.calorieGoal = newCalorieGoal;
        } else {
            this.calorieGoal = 0;
        }
    }

    /**
     * setWaterGoal
     * @param newWaterGoal - the new target number of cups of water the user wants to consume
     */
    public void setWaterGoal(int newWaterGoal) {
        if(newWaterGoal >= 0) {
            this.waterGoal = newWaterGoal;
        } else {
            this.waterGoal = 0;
        }
    }

    public void changeEmail(String newEmail) {
        if(newEmail != null && isValid(newEmail)) {
            this.userEmail = newEmail;
        }
    }

    /**
     * isValid
     * code taken from https://www.tutorialspoint.com/validate-email-address-in-java
     * @param email - the email to validate
     */
    private boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);

    }

}
