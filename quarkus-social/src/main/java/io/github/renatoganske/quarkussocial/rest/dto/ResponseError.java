package io.github.renatoganske.quarkussocial.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ResponseError {

    public static final int UNPROCESSED_ENTITY_STATUS = 422;
    private String message;
    private Collection<FieldError> errors;

    public static <T> ResponseError createFromValidation(
            Set<ConstraintViolation<T>> violations){
        List<FieldError> errors = violations
                .stream()
                .map(cv -> new FieldError(cv.getPropertyPath().toString(), cv.getMessage()))
                .collect(Collectors.toList());

        String message = "Validation Error";

        var responseError = new ResponseError(message, errors);
        return responseError;
    }

    public Response withStatusCode(int code){

        return Response.status(code).entity(this).build();
    }
}
