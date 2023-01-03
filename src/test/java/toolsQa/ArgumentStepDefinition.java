package toolsQa;

import dto.Student;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;

import java.util.List;
import java.util.Map;

public class ArgumentStepDefinition {

    public static List<Student> STUDENTS;

    @DataTableType
    public Student studentDefinition(Map<String, String> entry) {
        Student.Gender gender = defineGender(entry.get("gender").trim());
        return new Student(
                entry.get("firstName")
                        .trim(),
                entry.get("secondName")
                        .trim(),
                gender,
                entry.get("phoneNumber")
                        .trim()
        );
    }

    private Student.Gender defineGender(String g) {
        switch (g) {
            case "Male":
                return Student.Gender.MALE;
            case "Female":
                return Student.Gender.FEMALE;
            default:
                return Student.Gender.OTHER;
        }
    }

    @And("His information is")
    public void heHas(List<Student> students) {
        STUDENTS = students;
    }

}
