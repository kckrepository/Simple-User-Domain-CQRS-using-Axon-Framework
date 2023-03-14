package org.dani.command.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeactivateUserDto {
    @NotBlank(message = "id has to be present")
    @JsonProperty("id")
    private String id;
}
