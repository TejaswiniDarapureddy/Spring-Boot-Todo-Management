package net.javaguides.todomanagement.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//This class is for transferring data from client-to-server and server-to-client. This helps to keep secure of the
//sensitive information by using dto in returning the response to the client. This will decouple the jpa entity with client
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//The above 4 annotations are from lombok library which automatically creates the getter,setter methods and constructors
public class TodoDto {

    private long id;
    private String title;

    private String description;
    private boolean completed;
}
