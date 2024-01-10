package net.javaguides.todomanagement.Service;

import net.javaguides.todomanagement.Dto.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodods();

    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id); //This is for complete Todo

    TodoDto incompleteTodo(Long id); //To mark the completed Todo to incomplete Todo
}
