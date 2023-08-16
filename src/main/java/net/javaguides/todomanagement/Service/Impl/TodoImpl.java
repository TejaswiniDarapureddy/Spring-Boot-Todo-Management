package net.javaguides.todomanagement.Service.Impl;

import lombok.AllArgsConstructor;
import net.javaguides.todomanagement.Dto.TodoDto;
import net.javaguides.todomanagement.Entity.Todo;
import net.javaguides.todomanagement.Exception.ResourceNotFoundException;
import net.javaguides.todomanagement.Repository.TodoRepository;
import net.javaguides.todomanagement.Service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

@Service //This annotation makes the class as service class
@AllArgsConstructor //This annotation will create the parameterized constructor
public class TodoImpl implements TodoService {

    private TodoRepository todoRepository; //injecting the TodoRepository dependency

    private ModelMapper modelMapper; //Injecting the ModelMapper dependency

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //Convert TodoDto into Todo JPA entity

//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription((todoDto.getDescription()));
//        todo.setCompleted(todoDto.isCompleted());

        //Converting the TodoDto into JPA entity using ModelMapper map method
        Todo todo = modelMapper.map(todoDto,Todo.class);


        //Save Todo JPA entity to database
       Todo savedTodo = todoRepository.save(todo);

       //convert savedTodo(JPA entity object) to TodoDto

//        TodoDto todoDto1 = new TodoDto();
//        todoDto1.setId(savedTodo.getId());
//        todoDto1.setTitle(savedTodo.getTitle());
//        todoDto1.setDescription(savedTodo.getDescription());
//        todoDto1.setCompleted(savedTodo.isCompleted());

        //Converting the JPA entity object into TodoDTO using ModelMapper map method
        TodoDto todoDto1 = modelMapper.map(savedTodo,TodoDto.class);

        //return the todoDto1 object
        return todoDto1;
    }

    @Override
    public TodoDto getTodo(Long id) {
        //This orElseThrow() method takes supplier functional interface and it takes lambda expression
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " +id)); //This will throw the message whenever the id is not found

        //Converts the Todo JPA entity object into TodoDto and return the object
        TodoDto todoDto = modelMapper.map(todo,TodoDto.class);
        return  todoDto;
    }

    @Override
    public List<TodoDto> getAllTodods() {
        List<Todo> todos = todoRepository.findAll();
        //This will convert a list of Todo JPA entities to list of TodoDto's
        return todos.stream().map((todo) -> modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        //We will get the existing todo object from the databse table
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id:" +id));
        //retrieve all the information from TodoDto which is sent by the client and set to the todo object
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todo.isCompleted());

        Todo updatedTodo = todoRepository.save(todo); //This save method will perform the update operation if the JPA entity object contains the primary key otherwise it performs insert operation
        return modelMapper.map(updatedTodo,TodoDto.class); //arguments in map method are source and destination
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo Not found with id: " +id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        //Retrieve the existing todo object from the database table
        Todo todo =todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Tod not found with id: "+id));
        todo.setCompleted(Boolean.TRUE); //This will mark the existing Todo object as completed
        //Now save this todo JPA entity object into database
        Todo todo1 = todoRepository.save(todo);
        return modelMapper.map(todo1,TodoDto.class); //convert the todo object into ToDto
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        //Retrieve the existing todo object from the database table
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id: "+id));
        todo.setCompleted(Boolean.FALSE);
        Todo todo1 = todoRepository.save(todo);
        return modelMapper.map(todo1,TodoDto.class);
    }
}
