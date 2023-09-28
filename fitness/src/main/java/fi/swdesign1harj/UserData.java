package fi.swdesign1harj;

import java.util.ArrayList;

public class UserData {
    private ArrayList<Integer> steps;
    private ArrayList<Integer> burnedCalories;
    private ArrayList<String> time;

    public void setSteps(String steps) {
        String[] elements = steps.replaceAll("\\[|\\]", "").split(", ");        
        ArrayList<Integer> list = new ArrayList<>();

        for (String element : elements) {
            list.add(Integer.parseInt(element));
        }
        this.steps = list;
    }

    public void setBurnedCalories(String burnedCalories) {
        String[] elements = burnedCalories.replaceAll("\\[|\\]", "").split(", ");
        ArrayList<Integer> list = new ArrayList<>();

        for (String element : elements) {
            list.add(Integer.parseInt(element));
        }
        this.burnedCalories = list;
    }

    public void setTime(String time) {
        String[] elements = time.replaceAll("\\[|\\]", "").split(", ");
        ArrayList<String> list = new ArrayList<>();

        for (String element : elements) {
            list.add(element);
        }

        this.time = list;
    }

    public ArrayList<Integer> getSteps() {
        return steps;
    }

    public ArrayList<Integer> getBurnedCalories() {
        return burnedCalories;
    }

    public ArrayList<String> getTime() {
        return time;
    }
}