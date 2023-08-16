package net.javaguides.todomanagement.Controller;

import lombok.AllArgsConstructor;
import net.javaguides.todomanagement.Dto.TodoDto;
import net.javaguides.todomanagement.Entity.Todo;
import net.javaguides.todomanagement.Service.TodoService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //This annotation makes this class spring mvc rest controller. In this class, we can define REST APIs
@RequestMapping("api/todos")//This annotation is used to define the base url for all the REST APIs in the Todo Controller
@AllArgsConstructor
public class TodoController {
    private TodoService todoService; //Injecting the todoService object

    //Build Add Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping //To map incoming HTTP POST request to this particular method
    //@RequestBody annotation is used to extract the json object from the HTTP request and converts it into TodoDto java class object
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){ //ResponseEntity is a generic class. This is used to construct the response of the rest api
        TodoDto savedTodo = todoService.addTodo(todoDto); //calling the todoService addTodo() method
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //Build Get Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER)")
    @GetMapping("{id}")//To map incoming HTTP GET request to this particular method and id needs to be passed in the url
    //@PathVariable annotation is used to bind the id from url to the object
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId); //calling the todoService getTodo() method
        return new ResponseEntity<>(todoDto,HttpStatus.OK); //Returns the 200 OK status along with the todoDto object
    }


    //Build GetAll Todo's REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todoDtos = todoService.getAllTodods(); //This method will retrieve all the Todo's from the database
        return new ResponseEntity<>(todoDtos,HttpStatus.OK);
    }

    //Build Update Todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}") //To map incoming HTTP PUT request to this particular method
    //@RequestBody annotation extracts the updated JSON from the HTTP PUT request convert the JSON to TOdoDTo Java Object
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long id){
        TodoDto todoDto1 = todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(todoDto1); //This is also similar to the HTTPStatus.OK
    }

    //Build Delete Tod REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}") //To map incoming HTTP DELETE request to this particular method
    public ResponseEntity<String> deleteTod(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todd Deleted Successfully");
    }

    //Build Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete") //To map incoming HTTP PATCH request to this particular method
    //We use PATCH to update the existing record partially (not the whole Todo object, just a single field or two filed but not all the fields)
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    //Build InComplete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete") //To map incoming HTTP PATCH request to this particular method
    //We use PATCH to update the existing record partially (not the whole Todo object, just a single field or two filed but not all the fields)
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.incompleteTodo(id);
        return ResponseEntity.ok(todoDto);
    }
}
