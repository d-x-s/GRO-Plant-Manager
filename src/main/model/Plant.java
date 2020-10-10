package model;

/** A data abstraction for plants, abstracting their names and days of watering **/

public class Plant implements AddableScheduleEntry {

    String name;
    String dayOfAction;

    // EFFECTS: Default constructor, necessary for Jackson
    public Plant() {

    }

    // EFFECTS: Construct a Plant object with name, and day of watering
    public Plant(String commonName, String dayOfAction) {
        this.name = commonName;
        this.dayOfAction = dayOfAction;

    }

    // Getter: return the common name of a plant
    public String getName() {
        return this.name;
    }

    // Setter: return the day of action (watering) for a plant
    public String getDayOfAction() {
        return this.dayOfAction;
    }

}

