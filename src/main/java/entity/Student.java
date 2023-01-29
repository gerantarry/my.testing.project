package entity;

import lombok.Getter;
import lombok.Setter;

public class Student {

    public enum Gender{
        MALE("Male"),
        FEMALE("Female"),
        OTHER("Other");

        @Getter
        private final String gender;

        Gender(String gender){
            this.gender = gender;
        }
}

    public Student(String fName, String sName, Gender gender, String pNumber){
        firstName = fName;
        secondName = sName;
        setGender(gender);
        phoneNumber = pNumber;
    }

    @Getter
    @Setter
    private String firstName, secondName;

    @Getter
    @Setter
    private String phoneNumber;

    //Allowed values from enum
    @Getter
    private String gender;

    public void setGender(Gender g){
        gender = g.getGender();
    }

}
