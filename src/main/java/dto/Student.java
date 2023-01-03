package dto;

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

    @Getter
    @Setter
    private String firstName, secondName;

    @Getter
    private String phoneNumber;

    //Allowed values from enum
    @Getter
    private String gender;

    public void setGender(Gender g){
        gender = g.getGender();
    }

}
