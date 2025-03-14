package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Student ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS =
            "Student ID should be 4 characters beginning with A followed by 2 digits and ending with an alphabet";

    /*
     * The first character of the id must be A,
     * followed by 2 digits and any alphabet.
     */
    public static final String VALIDATION_REGEX = "A[0-9]{2}[A-Z]";

    public final String studentId;

    /**
     * Constructs a {@code StudentId}.
     *
     * @param id A valid id.
     */
    public StudentId(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        studentId = id;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return studentId;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherId = (StudentId) other;
        return studentId.equals(otherId.studentId);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }

}
