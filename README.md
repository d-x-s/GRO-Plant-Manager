# GRO - Houseplant Management System 🌿
## *GRO is a program that makes houseplant care easy!* 

### **Summary of Functionalities:**
- Schedule watering reminders 
- Calculate fertilizer dosing
- Access a database of plant information
- Profile system to save your plant collection

### **Project Proposal:**
The GRO app is a program created to aid **houseplant owners** who seek the ability to organize and manage their 
collection of plants. It features several useful tools. The first is the ability to *schedule watering times*, allowing
a user to receive notifications when it is time to water. A user is free to customize their watering schedule, or allow
the program to make a suggestion on when to water. Fertilizing is a confusing aspect for many plant owners. 
This program will a feature a calculator to *estimate fertilizer dosing ratios and amounts*. 

This project interests me because I have keeping plants is a casual hobby of mine. It will immediately benefit me, as I
can utilize it to create my own watering schedules! Since it is directly related to my own interests, it should 
facilitate my interest and enthusiasm in creating something genuinely useful.  

### **User Stories:**

*Phase 1: Watering Schedule*
1) I always forget to water, and I want to be able to schedule a watering time! 
2) I want to be able to add a plant to the schedule and set a day to remind me! 
3) I want to be able to remove a plant from my schedule! 
4) I want to be able to view my schedule! 

*Phase 2: Data Persistence*
1) I want my schedule to save when I modify it!
2) I want to be able to load the same schedule when I open the program again!
3) I want the program to remember my name!
4) I want the program to check if I am a new user, and give me a special welcome if I am a new user! 

*Phase 3: GUI / Instructions for Grader*
1) **You can generate the first required event by...** <br />
   You will be greeted by a window asking for your name, please enter it. It will save your username.
   The schedule window will open. Click the "Add Plant" button to add a plant. Refresh to see the changes.
   
2) **You can generate the second required event by...** <br />
   Click the "Remove Plant" button to remove a plant from the schedule. Refresh to see the changes.
   
3) **Visual Component** <br />
   The GRO logo is present as an icon on every window you open.
   
4) **Audio Component (Headphone warning: Can be LOUD!)** <br />
   If you are a new user, saving your username will play a success sound.
   If you need to revisit this window, simply use the "Reset Program" button.
   
5) **Saving the state of the application from file** <br />
   The program will automatically save the changes to file after a user makes a change to the schedule.
   
6) **Loading the state of the application from file** <br />
   Click the "Load Saved Program" button to load up your stored schedule.
   
*Phase 4: Design* <br />
**TASK ONE:** <br />
Phase 3 Code has already been committed. 

**TASK TWO:** <br /> 
1) **Test and design a class that is robust** <br />
   - see "dayToIndex" method in the "Schedule" class
   - "dayToIndex" throws a checked exception "InvalidDayException", and is tested in ScheduleTest

2) **Test and design a class that is robust** <br />
   - see "writeNewEntryToJson" and "findAndRemoveEntryFromJson" methods in the "ManagerJson" class
   - both methods throw IO exceptions that are caught in the same method
   - is tested within ManagerJson test

**TASK THREE: Refactoring** <br />
1) **Code Duplication/Low Cohesion**
   - Problem: In class "ScheduleFrame", I separately instantiated 7 JTextAreas, each representing a day of the week.
              I copy pasted the code for each of these days, resulting in 7 identical code blocks, excluding the 
              difference in names of days. If I wanted to format or change one of the JTextArea, I would have to do
              it 7 times. This was an example of **low class cohesion**. <br />
              
   - Solution: I created an Enum "WeekDaysName" to abstract away the names of days. Then, I extracted the code from
               the 7 identical code blocks, and put it into a separate method in class "ScheduleFrame", called 
               "createWeekDay". This method utilizes a for loop to instantiate 7 JTextAreas, calling upon the enum 
               "WeekDaysName" for information about a day's name. Now, there is a **single point of control**, and
               the code has been refactored successfully. 
               
   - Note: This refactoring was done in Phase 3. This is acceptable as per Piazza post #695.
           I have left a comment above "createWeekDay" method, containing the old code, if you would like to view it. 
   
2) **High Coupling**
   - Problem: Generally, the program is centered around the "Plant" object. That is, many classes are bound to the
              "Plant" object. What if I were to create a new type of object that I wished to add to my weekly schedule?
              It would be difficult, as I would have to change the code in many places. This is an example of **tight
              coupling**. Many classes are dependent upon the "Plant" class. 
              
   - Solution: Although I implemented the "AddableScheduleEntry" interface in Phase 1 of my project, I failed to use it 
               meaningfully. In this refactoring, I will make full use of its potential by enabling the "ManagerJson"
               class to accept any object that implements "Addable". This makes the "ManagerJson" class more independent 
               by removing the bound to "Plant", and therefore reducing tight coupling. Please see the method
               "findAndRemoveEntryFromJson" for an example of this refactoring. I utilized a wildcard. Now, future 
               developers will have an easier time reusing the code from "ManagerJson" if they wish to create new
               types of objects to add to the weekly scheduler. 
               
3) **UML Class Diagram**
    - Please see the PNG file I have added to root. I have also added a PDF as backup. 
               
   
   


