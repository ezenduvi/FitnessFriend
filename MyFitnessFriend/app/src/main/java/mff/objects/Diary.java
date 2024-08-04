package mff.objects;

public class Diary {
    private String reflection;
    private final int MAX_REFLECTION_LENGTH = 128;

    /**
     * Diary
     * creates a new Diary object based off of passed parameters
     * @param newReflection - The name of the reflection
     * @throws IllegalArgumentException - if reflection is null or of length 0
     */

    public Diary(String newReflection){
        if (newReflection == null || newReflection.length() > MAX_REFLECTION_LENGTH) {
            throw new IllegalArgumentException("Reflection must be a string with length between 1 and " + MAX_REFLECTION_LENGTH);
        }
        else
            reflection = newReflection;
    }
    public Diary(){
        reflection = "";
    }

    /**
     * getReflection
     * returns the reflection string
     * @return - the reflection string
     */

    public String getReflection() {
        return reflection;
    }

    public String toString(){return reflection;}

}
