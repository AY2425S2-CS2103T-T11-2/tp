---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

## CareBook User Guide

Welcome to CareBook! **Your classroom, organized.**

Designed with daycare teachers in mind, **CareBook** simplifies **daily roster management and parent communication** so you can focus on what matters the most - caring for children.

<div markdown="block" class="alert alert-info">

**:information_source: Notes about daycare teachers:**<br>
* We assume that the daycare teachers managing daily rosters of 30 to 40 children
* We assume that the daycare teachers using this product are tech-savvy and fast typers
</div>

With CareBook, you can:
- Instantly access **students’ information**
- Streamline **daily attendance tracking** with simple commands
- Easily maintain **student records** for smooth communication

This user guide will walk you through:
- Setting up your CareBook application
- Navigating the Graphical User Interface (GUI) and Command Line Interface (CLI)
- Learning essential commands to help you manage contacts and attendance

By the end of this guide, you’ll be a **CareBook pro**, managing your daycare with ease and precision.

***
## Table of Contents
1. Quick start
   - [Installing CareBook](#installing-carebook)
   - [Introducing GUI](#introducing-gui)
   - [CLI Tutorial](#cli-tutorial)
1. Features
   - [Adding a student:](#adding-a-student) `add`
   - [Clearing CareBook:](#clearing-carebook) `clear`
   - [Deleting a student:](#deleting-a-student) `delete`
   - [Editing a student:](#editing-a-student) `edit`
   - [Exiting the program:](#exiting-the-program) `exit`
   - [Exporting attendance summary:](#exporting-attendance-summary) `export`
   - [Finding a student’s contact:](#finding-a-student-s-contact)`find`
   - [Viewing help:](#viewing-help) `help`
   - [Listing all students:](#listing-all-students) `list`
   - [Marking a student’s attendance:](#marking-a-student-s-attendance) `mark`
   - [Marking all students’ attendance:](#marking-all-students-attendance) `markall`
   - [Unmarking a student’s attendance:](#unmarking-a-student-s-attendance) `unmark`
   - [Unmarking all students’ attendance:](#unmarking-all-students-attendance) `unmarkall`
1. [Known Issues](#known-issues)
1. [FAQ](#faq)
1. [Command Summary](#command-summary)
***

## Quick Start

#### Installing CareBook
1. Ensure you have Java `17` or above installed in your computer.
   <br>**Mac Users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
   <panel header="How to check your java version">
   <markdown>
   In your command terminal, type `java -version`
   </markdown>
   </panel>

2. Download your `carebook.jar` file with the latest release [here](https://github.com/AY2425S2-CS2103T-T11-2/tp/releases/tag/v1.6).
Scroll down all the way until you see **Assets** and click on `carebook.jar` to install.

3. Move the `.jar` file to the folder you want to use as the home folder for your CareBook.

4. Open a command terminal, `cd` into the folder you placed your .jar file and type `java -jar carebook.jar` and press enter to run CareBook application.
Here are detailed information as to how to change directory in [Windows](https://www.youtube.com/watch?v=BfXh11ryBJg) and [MacOS](https://www.youtube.com/watch?v=VRFcEMPES7U).
   <panel header="Changing directory">
   <markdown>
   If your folder is under `/home/user/CareBook`, simply type `cd /home/user/CareBook` and hit enter
   </markdown>
   </panel>

#### Introducing GUI
After successfully running CareBook application, a GUI similar to below should be displayed.

<img src="images/Ui.png" width="500">

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;
margin-top: 20px">
  💡 <strong>Tip:</strong> CareBook provides sample data in the first run.
</div>

#### CLI Tutorial
1. **Type Command:** Enter your command into the command box, highlighted with a yellow circle in the image below. <br>
<img src="images/clitutorial.png" width="500">

2. **Execute Command:** Press `Enter` to run your command.

3. **View Results:** After executing the command, you should see the student you just added as well as sample data
in the image below. <br>
   <img src="images/clitutorial1.png" width="500">

**Try These Example Commands**
* `help`: Opens the help window.
* `delete A00F`: Deletes the student you just added in step 1 of **type command**.
* `markall`: Marks all the students (including the sample data) as present.

For more details, see the [features section](#features).

<p>&nbsp;</p>

***

## Features

<div style="background-color: #f8d7da; padding: 10px; border-radius: 5px; border-left: 5px solid #dc3545; margin-bottom: 20px;">
  ❗<strong>Warning:</strong> Please be careful when copying commands from a PDF version of this user guide.
If a command is split across lines, spaces could be lost, which might cause errors when you paste
it into the application.
</div>

<div style="background-color: #f8d7da; padding: 10px; border-radius: 5px; border-left: 5px solid #dc3545; margin-bottom: 20px;">
  ❗<strong>Warning:</strong> Only edit the saved data file, carebook.json, if you are very sure what you are doing is correct and it will not cause errors (due to invalid names, dates, email id etc). Any error detected when reading data from the file will imply that the file has been corrupted, and all of its contents will be overwritten <strong>PERMANENTLY</strong>. You will NOT be able to recover lost data if your saved file is corrupted.
</div>

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
  ℹ   <strong> Information:</strong> <code>StudentId</code> used in all features is case-insensitive (e.g. "a10a" will match "A10A"). Both <code>Phone</code> and <code>Email</code> refer to the parent's.
</div>

#### Adding a student
You can add a student to CareBook.

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>add &lt;sn/StudentName&gt; &lt;id/StudentId&gt; &lt;pn/ParentName&gt; &lt;p/Phone&gt; &lt;e/Email&gt; &lt;a/Address&gt;</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>Parameters can be added in <em>any order</em>, but all are compulsory.</li>
    <li>For <code>StudentName</code> and <code>ParentName</code>, instead of "S/O" and "D/O" please use SO or DO.</li>
    <li><code>Name</code> should not have @</li>
    <li>No restrictions to Address.</li>
    <li><code>StudentId</code> must be <strong>unique</strong></li>
    <li><code>StudentId</code> is <strong>case-insensitive</strong></li>
    <li><code>StudentId</code> should be <strong>4 characters</strong> beginning with A followed by 2 digits and ending with an alphabet.</li>
    <li><code>Phone</code> is restricted to Singapore numbers so they <strong>must</strong> be exactly 8 digits and begin with 6, 8 or 9.</li>
    <li><code>Email</code> should have an @ domain.</li>
  </ul>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #ffc107; margin-bottom: 20px;">
  <strong>Examples:</strong>
  <ul>
    <li><code>add sn/Tom Hank id/A10A pn/Thomas Hank p/98122012 e/thomashank@hotmail.com a/Bukit View 9</code></li>
    <li><code>add pn/John Cena p/91029322 e/janeCena@hotmail.com a/Canberra Drive 9 sn/Jane Cena id/A99Z</code></li>
  </ul>
</div>

**Input:** <br>
<img src="images/addcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/addcommandoutput.png" width="500">

<p>&nbsp;</p>

#### Clearing CareBook
You can clear all student records in CareBook.

<div style="background-color: #f8d7da; padding: 10px; border-radius: 5px; border-left: 5px solid #dc3545; margin-bottom: 20px;">
  ❗<strong>Warning:</strong> You cannot retrieve any cleared data. This action is <strong>IRREVERSIBLE</strong>.
</div>

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
  ℹ   <strong> Information:</strong> This command ignores any additional parameters received.
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>clear</code>
</div>

**Input:** <br>
<img src="images/clearcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/clearcommandoutput.png" width="500">

<p>&nbsp;</p>

#### Deleting a student
You can delete a student from CareBook.

<div style="background-color: #f8d7da; padding: 10px; border-radius: 5px; border-left: 5px solid #dc3545; margin-bottom: 20px;">
  ❗<strong>Warning:</strong> You cannot retrieve any deleted student data. This action is <strong>IRREVERSIBLE</strong>.
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>delete &lt;StudentId&gt;</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>Student ID should be one that already exists in CareBook.</li>
  </ul>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #ffc107; margin-bottom: 20px;">
  <strong>Examples:</strong>
  <ul>
    <li><code>delete A10A</code></li>
    <li><code>delete A09B</code></li>
  </ul>
</div>

**Input:** <br>
<img src="images/deletecommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/deletecommandoutput.png" width="500">

<p>&nbsp;</p>

#### Editing a student
You can edit a student's details in CareBook.

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>edit &lt;StudentId&gt; &lt;sn/StudentName&gt; &lt;id/StudentId&gt; &lt;pn/ParentName&gt; &lt;p/Phone&gt; &lt;e/Email&gt; &lt;a/Address&gt;</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>Parameters can be edited in <em>any order</em>.</li>
    <li>For <code>StudentName</code> and <code>ParentName</code>, instead of "S/O" and "D/O" please use SO or DO.</li>
    <li><code>Name</code> should not have @</li>
    <li>There are no restrictions to Address.</li>
    <li><code>StudentId</code> must be <strong>unique</strong></li>
    <li><code>StudentId</code> is <strong>case-insensitive</strong></li>
    <li><code>StudentId</code> should be <strong>4 characters</strong> beginning with A followed by 2 digits and ending with an alphabet.</li>
    <li>Edited <code>StudentId</code> should be a new <code>StudentId</code> that has not been assigned to any students.</li>
    <li><code>Phone</code> is restricted to Singapore numbers so they <strong>must</strong> be exactly 8 digits and begin with 6, 8 or 9.</li>
    <li><code>Email</code> should have an @ domain.</li>
    <li><strong>At least one field</strong> is required in addition to providing the student ID of the student you want to edit.</li>
  </ul>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #ffc107; margin-bottom: 20px;">
  <strong>Examples:</strong>
  <ul>
    <li><code>edit A10A sn/Tom Hank p/98122012 e/thomashank@hotmail.com a/Bukit View 9</code></li>
    <li><code>edit A99Z p/91029322 e/johnnyCena@gmail.com</code></li>
    <li><code>edit A99Z id/A88V</code></li>
  </ul>
</div>


**Input:** <br>
<img src="images/editcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/editcommandoutput.png" width="500">
<p>&nbsp;</p>

#### Exiting the program
You can exit the program.

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
    <strong>ℹ  Information:</strong> This command ignores any additional parameters received.
</div>

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
  💡 <strong>Tip:</strong> Upon exiting, any data you have updated will automatically be saved.
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>exit</code>
</div>

<p>&nbsp;</p>

#### Exporting attendance summary
You can export an attendance summary.

<div style="background-color: #f8d7da; padding: 10px; border-radius: 5px; border-left: 5px solid #dc3545; margin-bottom: 20px;">
  ❗<strong>Warning:</strong> Student records are meant to be exported to a .csv file and viewed in that format. If you copy and paste records in that exported .csv file to other file types like Excel,
there may be a slight formatting issue.
</div>

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
  <strong>ℹ  Information: </strong> The exported file will be automatically saved in .csv format, with a timestamp next
        to the file name to help identify when it was created. It includes student ID, student name, parent name,
parent's email, parent's phone number, and students' attendance history from CareBook.
</div>

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
  💡 <strong>Tip:</strong> You can copy the parent's email address from the exported .csv file and paste it directly into the "To" field when composing an email in Outlook
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>export &lt;FileName&gt;</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>File name should only consist of alphanumeric characters and underscores.</li>
    <li>Special characters including but not limited to * . / ‘ are <strong>invalid</strong>.</li>
     <li><code>FileName</code> should not exceed <strong>100 characters</strong>.</li>
  </ul>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #ffc107; margin-bottom: 20px;">
  <strong>Examples:</strong>
  <ul>
    <li><code>export attendance_sheet</code></li>
    <li><code>export student_records</code></li>
  </ul>
</div>


**Input:** <br>
<img src="images/exportcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/exportcommandoutput.png" width="500">

<p>&nbsp;</p>

#### Finding a student’s contact
You can find a specific student with matching Student ID.

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
    <strong>ℹ  Information:</strong> A find window will open upon executing this command. Full contact information (from top to bottom order:
student name, student ID, parent name, parent's phone number, address, parent's email) of the student with a matching ID will be shown.
</div>

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
  💡 <strong>Tip:</strong> You <strong>must</strong> close the find window by either pressing ESC or cross button before you can execute any commands in the main window
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>find &lt;StudentId&gt;</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>Student ID must be one that already exists in CareBook.</li>
  </ul>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #ffc107; margin-bottom: 20px;">
  <strong>Examples:</strong>
  <ul>
    <li><code>find A03A</code></li>
    <li><code>find A99Z</code></li>
  </ul>
</div>

**Input:** <br>
<img src="images/findcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/findcommandoutput.png" width="500">

<p>&nbsp;</p>

#### Viewing help
you can open a help window with a link to CareBook user guide website.

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
    <strong>ℹ  Information:</strong> This command ignores any additional parameters received.
</div>

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
  💡 <strong>Tip:</strong> The help window also displays a summary of commands below the guide link.
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>help</code>
</div>

**Input:** <br>
<img src="images/helpcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/helpcommandoutput.png" width="500"> <br>

A [command summary](#command-summary) will also be displayed below the guide link.

<p>&nbsp;</p>

#### Listing all students
You can list all students in CareBook.

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
    <strong>ℹ  Information:</strong> This command ignores any additional parameters received.
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>list</code>
</div>

**Input:** <br>
<img src="images/listcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/listcommandoutput.png" width="500">



<p>&nbsp;</p>

#### Marking a student’s attendance
You can mark a student in CareBook as present.

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>mark &lt;StudentId&gt;</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>Student ID must be one that already exists in CareBook.</li>
  </ul>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #ffc107; margin-bottom: 20px;">
  <strong>Examples:</strong>
  <ul>
    <li><code>mark A10A</code></li>
    <li><code>mark A99Z</code></li>
  </ul>
</div>

**Input:** <br>
<img src="images/markcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/markcommandoutput.png" width="500">

<p>&nbsp;</p>

#### Marking all students’ attendance
You can mark all students in CareBook as present.

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
    <strong>ℹ  Information:</strong> This command ignores any additional parameters received.
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>markall</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>The command only works if there is at least one recorded student in CareBook.</li>
    <li>Running the command when all students are already marked will still return a success message. This is expected behaviour and not an error.</li>
  </ul>
</div>

**Input:** <br>
<img src="images/markallcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/markallcommandoutput.png" width="500">

<p>&nbsp;</p>

#### Unmarking a student’s attendance
You can mark a student in CareBook as absent.

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>unmark &lt;StudentId&gt;</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>Student ID must be one that already exists in CareBook.</li>
  </ul>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #ffc107; margin-bottom: 20px;">
  <strong>Examples:</strong>
  <ul>
    <li><code>unmark A10A</code></li>
    <li><code>unmark A99Z</code></li>
  </ul>
</div>

**Input:** <br>
<img src="images/unmarkcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/unmarkcommandoutput.png" width="500">

<p>&nbsp;</p>

#### Unmarking all students’ attendance
You can mark all students in CareBook as absent.

<div style="background-color: #e7f3ff; padding: 10px; border-radius: 5px; border-left: 5px solid #5b9bd5; margin-bottom: 20px;">
    <strong>ℹ  Information:</strong> This command ignores any additional parameters received.
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #6c757d; margin-bottom: 20px;">
  <strong>Command Format:</strong> <code>unmarkall</code>
</div>

<div style="padding: 10px; border-radius: 5px; border-left: 5px solid #34c759; margin-bottom: 20px;">
  <strong>Remarks:</strong>
  <ul>
    <li>The command only works if there is at least one recorded student in CareBook.</li>
    <li>Running the command when all students are already unmarked will still return a success message. This is expected behaviour and not an error.</li>
  </ul>
</div>

**Input:** <br>
<img src="images/unmarkallcommandinput.png" width="500">

**Expected Output:** <br>
<img src="images/unmarkallcommandoutput.png" width="500">

<p>&nbsp;</p>

***

## Known Issues

1. **Find window** cannot be resized. However, since it only displays 1 student, you will still be able to view all details needed.
2. When **help window is minimized** and you run `help` again, the original window remains minimized. Simply restore the minimized window to view it again.
***

## FAQ

**Q:** Is CareBook and Java 17 free to download? <br>
**A:** Yes, they are both free to download!

**Q:** Do I need an internet connection to use CareBook? <br>
**A:** No, CareBook works **fully offline**. All data is stored locally on your device.

**Q:** Is my data automatically saved? <br>
**A:** Yes, CareBook **automatically saves** all changes after each command. No need to manually save!

**Q:** How do I start using this application if I am a novice user? <br>
**A:** Execute `help` command to check the commands available. For more information about the command usage
of each command, see the [features section](#features).

**Q:** Is there a way to recover deleted or cleared student records? <br>
**A:** No, `clear` and `delete <StudentId>` are **IRREVERSIBLE**.

***

## Command Summary
| Action        | Command Format/Examples                                                                                                                                                               |
|---------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**       | `add <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>`<br/> e.g. add sn/Tom Hanks id/A10A pn/Henry Hanks p/99019232 e/henry@hotmail.com a/Bukit View 9 |
| **Clear**     | `clear`                                                                                                                                                                               |
| **Delete**    | `delete <StudentId>` <br/> e.g. delete A10A                                                                                                                                           |
| **Edit**      | `edit <StudentId> <sn/StudentName> <id/StudentId> <pn/ParentName> <p/Phone> <e/Email> <a/Address>` <br/> e.g. edit A10A p/91092222 e/henryhanks@hotmail.com                           |
| **Exit**      | `exit`                                                                                                                                                                                |
| **Export** | `export <FileName>`<br/> e.g. export attendance_sheet                                                                                                                                 |
| **Find**      | `find <StudentId>` <br/> e.g. find A10A                                                                                                                                               |
| **Help**      | `help`                                                                                                                                                                                |
| **List**      | `list`                                                                                                                                                                                |
| **Mark**      | `mark <StudentId>` <br/> e.g. mark A10A                                                                                                                                               |
| **Markall**   | `markall`                                                                                                                                                                             |
| **Unmark**    | `unmark <StudentId>` <br/> e.g. mark A10A                                                                                                                                             |
| **Unmarkall** | `unmarkall`                                                                                                                                                                           |
