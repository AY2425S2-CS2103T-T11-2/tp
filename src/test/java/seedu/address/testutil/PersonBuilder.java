package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_STUDENT_NAME = "Amy Cena";
    public static final String DEFAULT_PARENT_NAME = "Peter Lee";
    public static final String DEFAULT_STUDENT_ID = "A98Z";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name studentName;
    private Name parentName;
    private StudentId studentId;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        studentName = new Name(DEFAULT_STUDENT_NAME);
        parentName = new Name(DEFAULT_PARENT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        studentName = personToCopy.getStudentName();
        parentName = personToCopy.getParentName();
        studentId = personToCopy.getStudentId();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Student Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withSName(String name) {
        this.studentName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Parent Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withPName(String name) {
        this.parentName = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Parent Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withSId(String id) {
        this.studentId = new StudentId(id);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(studentName, parentName, studentId, phone, email, address, tags);
    }

}
