package model;
/** An 'Addable' interface is useful if future developers wish to refactor the program to allow for other
 objects besides plants to be added to the scheduler.
 The 'Addable' behaviour abstracts any type of data that can be added to a scheduler. **/

public interface AddableScheduleEntry {

    //any object added to the schedule must have a name that can be retrieved
    String getName();

    //any object added to the schedule must be assigned a day that can be retrieved
    String getDayOfAction();

}
