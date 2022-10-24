package io.github.renatoganske.quarkussocial.rest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter

public class CreateUserRequest {
    @NotBlank(message = "Nome is required.")
    private String name;
    @NotNull(message = "Age is required.")
    private Integer age;

}
