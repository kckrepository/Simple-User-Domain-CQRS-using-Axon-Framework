package org.dani.command.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class UserDto {
    @NotBlank(message = "first_name has to be present")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "last_name has to be present")
    @JsonProperty("last_name")
    private String lastName;

    @Past
    @JsonProperty("birth")
    private Date birth;

    @JsonIgnore
    private Date created;

}
