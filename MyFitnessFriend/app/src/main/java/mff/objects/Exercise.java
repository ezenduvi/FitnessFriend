package mff.objects;

public class Exercise {
    private final String name;
    private int duration;
    private final int caloriesPerHour;

    /**
     * Exercise
     * creates a new exercise object based off of passed parameters
     * @param name - The name of the exercise
     * @param duration - the duration of the exercise
     * @param caloriesPerHour - the calories burned for doing the exercise for an hour
     * @throws IllegalArgumentException - if name is null or of length 0
     * @throws IllegalArgumentException - if duration is not a positive number
     * @throws IllegalArgumentException - if caloriesPerHour is not a positive number
     */
    public Exercise (String name, int duration, int caloriesPerHour) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name must be a string with length greater than one");
        } else if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive integer");
        } else if (caloriesPerHour <= 0) { System.out.println(caloriesPerHour + "============================");
            throw new IllegalArgumentException("caloriesPerHour must be a positive integer");
        } else {
            this.name = name;
            this.duration = duration;
            this.caloriesPerHour = caloriesPerHour;
        }
    }

    /**
     * getName
     * returns the name of the exercise
     * @return - the name of the exercise
     */
    public String getName() { return name; }

    /**
     * getDuration
     * returns the duration of the exercise
     * @return - the duration of the exercise
     */
    public int getDuration() { return duration; }

    /**
     * getCaloriesPerHour
     * returns the calories burned for doing the exercise for an hour
     * @return - the calories burned for doing the exercise for an hour
     */
    public int getCaloriesPerHour() { return caloriesPerHour; }

    /**
     * setDuration
     * sets the duration of the exercise to the passed parameter
     * @param duration - the new duration for the exercise
     * @throws IllegalArgumentException - if duration is not a positive number
     */
    public void setDuration (int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be a positive integer");
        } else {
            this.duration = duration;
        }
    }
}
