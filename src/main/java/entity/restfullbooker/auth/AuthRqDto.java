package entity.restfullbooker.auth;

import lombok.Data;

@Data
public class AuthRqDto {
    private String username;
    private String password;


    public String toJsonString(){
        return "{\"username\": \"" + username + "\"," +
                "\"password\": \"" + password + "\"}";
    }
}
