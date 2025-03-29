package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;

import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object
 */
public class ExportCommandParser implements Parser<ExportCommand> {
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]*$";
    public static final String MESSAGE_INVALID_FILE_NAME = "File name should only consist of alphanumeric"
            + " characters and underscores (_). " + "\nSpecial characters like ?, /, ., * are invalid.";

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.isEmpty()) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExportCommand.MESSAGE_USAGE)));
        }
        if (!args.trim().matches(VALIDATION_REGEX)) {
            throw new ParseException(MESSAGE_INVALID_FILE_NAME);
        }

        File exportedFile = ParserStudentUtil.parseFileName(args);
        return new ExportCommand(exportedFile);
    }
}
