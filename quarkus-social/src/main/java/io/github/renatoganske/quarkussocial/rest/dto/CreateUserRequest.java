package io.github.renatoganske.quarkussocial.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String name;
    private Integer age;

}
