package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Exports the student ID, student name, parent email and attendance history
 * of students currently recorded in Care Book into a .csv file whose file name is specified by user
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Exports the student ID, student name, parent email and attendance history of students "
            + "\nto the current directory of where this app is saved as a .csv file.\n"
            + "Parameters: fileName\n"
            + "Example: " + COMMAND_WORD + " attendance_summary";
    public static final String MESSAGE_SUCCESS = "Exported attendance summary to \n";
    public static final String MESSAGE_NO_STUDENT_FOUND = "No student found";
    public static final String FILE_WRITE_ERROR = "Unable to export attendance summary due to I/O error";

    private final File fileName;

    public ExportCommand(File fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(fileName);
        ObservableList<Student> studentList = model.getFilteredStudentList();

        if (studentList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_STUDENT_FOUND);
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Student ID, Student Name, Parent's Email, Attendance History (Present Dates)\n");
            for (Student student : studentList) {
                String studentId = student.getStudentId().toString();
                String studentName = student.getStudentName().toString();
                String parentEmail = student.getEmail().toString();
                Set<LocalDate> attendanceDates = student.getAttendance().getAttendance();
                String attendanceToString = attendanceDates.stream()
                        .map(LocalDate::toString)
                        .collect(Collectors.joining("\n"));

                fileWriter.write(String.format("\"%s\",\"%s\",\"%s\",\"%s\"\n",
                        studentId, studentName, parentEmail, attendanceToString));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new CommandException(FILE_WRITE_ERROR);
        }
        return new CommandResult(MESSAGE_SUCCESS + fileName.getAbsolutePath());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return fileName.equals(otherExportCommand.fileName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fileName", fileName)
                .toString();
    }
}
