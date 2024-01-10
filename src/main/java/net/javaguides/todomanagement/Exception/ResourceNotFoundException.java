package net.javaguides.todomanagement.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//This class is a custom exception class to handle the exceptions

//This annotation is typically used on custom exceptions to indicate that the response status code should
// be set to 404 (Not Found) when that specific exception is thrown.
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
