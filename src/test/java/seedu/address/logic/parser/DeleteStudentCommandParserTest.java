package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.model.student.StudentId;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteStudentCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteStudentCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteStudentCommandParserTest {

    private DeleteStudentCommandParser parser = new DeleteStudentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteStudentCommand() {
        String validStudentId = "A10A";
        DeleteStudentCommand expectedCommand = new DeleteStudentCommand(new StudentId(validStudentId));
        assertParseSuccess(parser, validStudentId, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                StudentId.MESSAGE_CONSTRAINTS + "\n" + DeleteStudentCommand.MESSAGE_USAGE);
    }
}
