package net.javaguides.springboot.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//This Schema annotation is used to provide the information about the schemas present in the swagger documentation
@Schema(
        description = " User DTO model information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long id;
    //Using this schema annotation, we can even provide the description for the fields
    @Schema(
            description = " User first name"
    )
    @NotEmpty(message = "User first name must be null or empty")
    private String firstName;
    @Schema(
            description = " User last name"
    )
    @NotEmpty(message = "User last name must be null or empty")
    private String lastName;
    @Schema(
            description = " User email address"
    )
    @NotEmpty(message = "User email must not be null or empty")
    @Email(message = "User email should be a valid email address")
    private String email;
}
