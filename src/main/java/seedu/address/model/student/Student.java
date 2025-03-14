package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name studentName;
    private final StudentId studentId;
    private final Name parentName;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;

    /**
     * Every field must be present and not null.
     */
    public Student(Name studentName, StudentId studentId, Name parentName, Phone phone, Email email, Address address) {
        requireAllNonNull(studentName, studentId, parentName, phone, email, address);
        this.studentName = studentName;
        this.studentId = studentId;
        this.parentName = parentName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Name getStudentName() {
        return studentName;
    }

    public StudentId getStudentId() {
        return studentId;
    }
    public Name getParentName() {
        return parentName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Student otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getStudentId().equals(getStudentId());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherPerson = (Student) other;
        return studentName.equals(otherPerson.studentName)
                && studentId.equals(otherPerson.studentId)
                && parentName.equals(otherPerson.parentName)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(studentName, studentId, parentName, phone, email, address);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student name", studentName)
                .add("student ID", studentId)
                .add("parent name", parentName)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .toString();
    }

}
