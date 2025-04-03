package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.*;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.StudentId;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserStudentUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_MISSING_FILE_NAME = "File name should not be empty!";


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code id} into a {@code StudentId} and returns it. Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified ID is invalid.
     */
    public static StudentId parseStudentId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (trimmedId.isBlank() || trimmedId.indexOf(' ') >= 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, STUDENT_ID_MESSAGE_CONSTRAINTS));
        }
        if (!StudentId.isValidId(trimmedId)) {
            throw new ParseException(MESSAGE_INVALID_STUDENT_ID);
        }
        return new StudentId(trimmedId);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(NAME_MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(PHONE_MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(ADDRESS_MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(EMAIL_MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code fileName} into an {@code File}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if given {@code fileName} is missing.
     */
    public static File parseFileName(String fileName) throws ParseException {
        requireNonNull(fileName);
        String trimmedFileName = fileName.trim();
        if (trimmedFileName.isEmpty()) {
            throw new ParseException(MESSAGE_MISSING_FILE_NAME);
        }
        // Generate timestamp so files with same name will not be overwritten when exported
        String timeStamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("dd MMM yyyy 'at' HH'h' mm'm' ss's'"));
        return new File(trimmedFileName + " (exported on " + timeStamp + ")" + ".csv");
    }
}
