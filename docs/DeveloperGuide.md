---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# CareBook Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

Some of CareBook features are adapted from the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
As such, CareBook contains some of the code and documentation from AddressBook-Level3. 

The list of third-party libraries used are: [JavaFX](https://openjfx.io/), [Jackson](https://github.com/FasterXML/jackson), [JUnit5](https://github.com/junit-team/junit5)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete A10A`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete A10A")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete A10A` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteStudentCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteStudentCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteStudentCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the CareBook data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both CareBook data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how an undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how an undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Daycare teacher managing daily rosters of 30 to 40 students (aged 1 to 12)
* Prefers desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Is reasonably comfortable using CLI apps

**Value proposition**: CareBook helps daycare teachers manage classroom and parent communication by providing instant CLI access to students’, parents’, colleagues’ contacts and streamlining repetitive tasks like daily attendance and maintaining student records.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                              | I want to …​                                                                               | So that I can…​                                           |
|----------|--------------------------------------|--------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| `* * *`  | potential user exploring the product | see the instruction guide                                                                  | easily know how the app works                             |
| `* * *`  | daycare teacher                      | view the students' and parents' contacts                                                   | disseminate information to them                           |
| `* * *`  | daycare teacher                      | add student details to the system                                                          | keep records up to date as children enrol or leave        |
| `* * *`  | daycare teacher                      | remove student details from the system                                                     | keep records up to date as children enrol or leave        |
| `* * *`  | forgetful teacher                    | search for contacts by phone number                                                        | quickly identify who is calling/who to call               |
| `* * *`  | daycare teacher                      | mark students as present                                                                   | efficiently take attendance during busy mornings          |
| `* *`    | daycare teacher                      | quickly search for a child's emergency contact details                                     | immediately inform parents in case of emergencies         |
| `* *`    | daycare teacher                      | generate a summary of daily/monthly attendance                                             | track attendance accurately                               |
| `* *`    | new user                             | copy-paste the parents' contacts from the app                                              | easily send an email to all parents                       |
| `* *`    | forgetful user                       | filter the students in my class by subjects                                                | plan the class for the day better                         |
| `* *`    | daycare teacher                      | view all classes scheduled for the day                                                     | prepare for my daily routine                              |
| `* *`    | daycare teacher                      | make a note of my student (e.g., behaviour, meals, incidents)                              | keep track of important details                           |
| `* *`    | daycare teacher                      | edit student details from the system                                                       | keep records up to date as children enrol or leave        |
| `* *`    | daycare teacher                      | export student details                                                                     | transfer student details to other systems                 |
| `* *`    | daycare teacher                      | export parent details                                                                      | transfer parent details to other systems                  |
| `* *`    | daycare teacher                      | filter students by age group                                                               | access information for age-based activities               |
| `* *`    | daycare teacher                      | create a custom group for students                                                         | organise activities for special occasions                 |
| `* *`    | daycare teacher                      | flag important information (e.g. medical conditions/allergies) about a student             | quickly access important details in times of crisis       |
| `* *`    | daycare teacher                      | access an audit log of student information updates                                         | track when and what information was modified              |
| `* *`    | daycare teacher                      | record students' absence with their reasons                                                | maintain accurate records and organise makeup lessons     |
| `* *`    | daycare teacher                      | see which students share the same emergency contact                                        | coordinate communication for siblings in the class        |
| `* *`    | daycare teacher                      | view basic student information (age, name, class) without accessing their complete records | quickly identify students                                 |
| `* *`    | daycare teacher                      | receive alerts when a student's information is updated                                     | stay informed about important changes                     |
| `* *`    | daycare teacher                      | undo previous commands                                                                     | revert any errors                                         |
| `*`      | daycare teacher                      | schedule and send pre-set messages to parents                                              | remind them about upcoming events or tasks effortlessly   |
| `*`      | daycare teacher                      | receive automated alerts if a child is absent for multiple days                            | follow up with parents if needed                          |
| `*`      | daycare teacher                      | set reminders for student-specific events (e.g., medication time)                          | avoid missing important occasions                         |
| `*`      | expert user                          | sort students' records by various criteria (e.g., name)                                    | find information more efficiently                         |
| `*`      | forgetful user                       | set a preferred name for each student                                                      | remember their names and address them properly            |
| `*`      | daycare teacher                      | see attendance trends over a certain period                                                | identify students who have been missing classes           |
| `*`      | daycare teacher                      | print a list of students with their emergency contacts as a PDF                            | have a copy for quick reference in case of a power outage |

### Use cases
(For all use cases below, the **System** is the `CareBook` and the **Actor** is the `Daycare Teacher`, unless specified otherwise.)<br>

#### Use case: UC1 - Add a New Student 
**MSS**
1. User requests to add a student. 
2. CareBook validates student details (student name, parent name, student ID, phone number, email address and address). 
3. CareBook adds the new student. 
4. CareBook confirms successful addition.
    
    Use case ends.

**Extensions**
* 2a. CareBook detects an invalid  student name, parent name, student ID, phone number, email address, or address.
    * 2a1. CareBook displays an error message.
  
      Use case resumes from step 1.

* 2b. CareBook detects a duplicate student ID.
    * 2b1. CareBook displays an error message.
    
      Use case resumes from step 1.

* 2c. CareBook detects an incorrect command format or unknown command.
  * 2c1. CareBook displays an error message.
    
    Use case resumes from step 1.

#### Use case: UC2 - Edit a Student 
**MSS**
1. User requests to edit a student with new details by using student ID.
2. CareBook verifies that the student exists.
3. CareBook updates the student with the new details.
4. CareBook confirms successful edit.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC3 - Delete a Student 

**MSS**

1. User requests to delete a student by student ID.
2. CareBook verifies that the student exists.
3. CareBook removes the student.
4. CareBook confirms successful removal.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC4 - View All Students

**MSS**

1. User requests to list all students.
2. CareBook displays a list of all students.

   Use case ends.

**Extensions**

* 2a. CareBook detects an empty list of students.

    * 2a1. CareBook displays an error message.

      Use case ends.

#### Use case: UC5 - Find a Student by Student ID

**MSS**

1. User requests to find a student by student ID.
2. CareBook verifies that the student exists.
3. CareBook retrieves the student’s details.
4. CareBook displays the student’s information.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC6 - Mark a Student Attendance as Present

**MSS**

1. User requests to mark a student as present by student ID.
2. CareBook verifies that the student exists.
3. CareBook updates the attendance record for that student.
4. CareBook confirms that the student has been marked present.

    Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC7 - Mark a Student Attendance as Absent

**MSS**

1. User requests to mark a student as absent by student ID.
2. CareBook verifies that the student exists.
3. CareBook updates the attendance record for that student.
4. CareBook confirms that the student has been marked absent.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid student ID or is unable to find student ID.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an incorrect command format or unknown command.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC8 - Clear All Students

**MSS**

1. User requests to clear all students.
2. CareBook clears all students.

   Use case ends.

#### Use case: UC9 - View the Instruction Guide

**MSS**

1. User requests for help.
2. CareBook displays a list of all commands, their respective parameters, and an example of usage.

   Use case ends.

#### Use case: UC10 - Mark All Students Attendance as Present

**MSS**

1. User requests to mark all students as present.
2. CareBook updates the attendance record for every student as present.
3. CareBook confirms that all students have been marked present.

   Use case ends.

**Extensions**

* 2a. CareBook detects an empty list of students.

    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC11 - Mark All Students Attendance as Absent

**MSS**

1. User requests to mark all students as absent.

2. CareBook updates the attendance record for every student as absent.

3. CareBook confirms that all students have been marked absent.

   Use case ends.

**Extensions**

* 2a. CareBook detects an empty list of students.

    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

#### Use case: UC12 - Export All Students Information

**MSS**

1. User requests to export all students information with a filename.

2. CareBook saves all students information into the directory where the CareBook application is located.

   Use case ends.

**Extensions**

* 2a. CareBook detects an invalid filename.
    * 2a1. CareBook displays an error message.

      Use case resumes at step 1.

* 2b. CareBook detects an empty list of students.
    * 2b1. CareBook displays an error message.

      Use case resumes at step 1.

### Non-Functional Requirements
1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 50 students without a noticeable sluggishness in performance for typical usage.
3.  System does not take more than 1s to load all data from files when launching.
4.  All commands execute in less than 1s, including saving of data to files.
5.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
6.  Commands should be intuitive and easy to remember.
7.  Data should be saved after each change-causing command.
8.  Error messages should offer support to user (help user get the right commands).
9.  The app should not require complex installation steps.
10. It should be a lightweight application (<100MB) and not require additional dependencies beyond Java.

### Glossary
* **Command**: An instruction entered by the Daycare Teacher into the CareBook system to perform a specific action (e.g., "add", "delete", "find", "list", "mark", "help").
* **Command Format**: The required structure and syntax for a command, including any necessary parameters (e.g., "add <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>" for adding a student).
* **Daycare Teacher**: The primary user of the CareBook application, responsible for managing student records and attendance.
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Student Record**: A set of data representing a student's personal information, including details like student ID, name, parent contact, and attendance.
* **Validation**: The system’s process of checking that entered data (e.g., Student ID, ParentPhoneNumber) meets predefined rules before accepting it.
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

#### Launch and shutdown

1. **Initial launch**
   1. Download the `CareBook.jar` file and copy into an empty folder
   2. Ensure you have `Java 17` or above installed in your computer. 
   3. Open a command terminal, `cd` into the folder you placed your .jar file and type `java -jar CareBook.jar` and press enter to run CareBook application.<br>
      **Expected:** 
      * Shows the GUI with a set of sample students. 
      * The window size may not be optimum.
    
2. **Saving window preferences**
   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   1. Re-launch the app by typing `java -jar CareBook.jar` and pressing enter.<br>
      **Expected**: 
      * The most recent window size and location is retained.
    
3. **Verifying Logs during launch**
   1. Launch the app by typing `java -jar CareBook.jar` and pressing enter.
   2. Observe the logs printed in the terminal during startup.<br>
      **Expected**: 
      * Logs should be displayed with appropriate timestamps.
      * Warnings about JavaFX configuration may appear but should not affect functionality. 

#### Deleting a student

1. **Deleting a student while all students are being shown**
   1. Prerequisites: List all students using the `list` command. Multiple students in the list.
   1. Test case: `delete A10A`<br>
      **Expected**: 
      * Student with ID A10A is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.
   1. Test case: `delete A99Z`<br>
      **Expected:**
      * No student is deleted. Error details shown in the status message. Status bar remains the same.
   1. Other incorrect delete commands to try: `delete`, `delete a10a`, `...` (where student ID is not capitalised)<br>
      **Expected:**
      * Similar to previous.

2.  **Deleting a Student When No Students Are Displayed**
    1. Prerequisites: Clear the list using the `clear` command.
    2. Test case: `delete A99Z`<br>
       **Expected:**
       * No student is deleted. Error details shown in the status message. Status bar remains the same.


#### Saving data

1. **Dealing with missing data files**
   1. Navigate to the application's `data` directory.
   2. Delete or rename the data file (e.g., addressbook.json). 
   3. Launch the app.
      **Expected:**
       * The application should create a new default data file if one does not exist.
       * The list of students should contain default sample student data.
       * A warning message (e.g., “Creating a new data file data\addressbook.json populated with a sample AddressBook.”) should be logged.
   
2. **Dealing with corrupted data files**
    1. Navigate to the application's `data` directory and open `addressbook.json` in a text editor.
    2. Modify the content to be invalid JSON (e.g., remove a closing brace or change a key name to an invalid format).
    3. Save the file and launch the app.
       **Expected:**
        * The application should detect the corruption and handle it.
        * A warning message (e.g., "WARNING: Error reading from jsonFile file data\addressbook.json: ...") should be logged.
        * The app will launch with an empty student list.
        * The application should not crash and should remain functional.

3. **Ensuring data is saved after adding/deleting a student**
    1. Launch the app and modify the data by adding/deleting a student.
    2. Close the application and re-open it.
       **Expected:**
        * The added/deleted student should appear/not appear in the list.

## **Appendix: Effort**
1. **Difficulty Level and Challenges Faced**

   Our project builds on AddressBook Level 3 (AB3) but required significant refactoring to better fit our target users' needs. As a result, the complexity of data management, validation, and UI representation increased.<br>
    **Key challenges included:**
    * Extensive refactoring: From `person` model to `student` model.
    * Editing and updating test cases: Ensuring existing test cases remained valid after structural changes while writing new ones for added functionalities.
    * Maintaining code consistency: Ensuring that refactored components integrated smoothly without introducing regressions.
    
2. **Effort Required**

   The project effort was distributed across key areas:<br>
   * Feature Development (45%) – Modifying existing commands and implementing new commands (e.g. mark, markall, export).
   * Testing & Debugging (30%) – Updating test cases, refactoring test structure, and ensuring correctness after modifications.
   * Data Management & Storage (25%) – Modifying JSON storage to handle new fields in `Student` model.
    
3. **Achievements**
    * Successfully modified AB3 to align with our target users' needs while maintaining its core functionality.
    * Refactored the codebase effectively, improving maintainability and extensibility.

## **Appendix: Planned Enhancements**
1. **Clearing attendance records**

   Currently, attendance records accumulate over time, which can make it difficult for users to manage large amounts of data. We plan to introduce a bulk clearance feature that allows users to clear all the attendance records in CareBook.

_{ more enhancement features to be added …​ }_
