package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.StudentId;

/**
 * Parses input arguments and creates a new DeleteStudentCommand object.
 */
public class DeleteStudentCommandParser implements Parser<DeleteStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStudentCommand
     * and returns a DeleteStudentCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteStudentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentCommand.MESSAGE_USAGE));
        }
        try {
            StudentId studentId = new StudentId(trimmedArgs);
            return new DeleteStudentCommand(studentId);
        } catch (IllegalArgumentException e) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS + "\n" + DeleteStudentCommand.MESSAGE_USAGE);
        }
    }
}
