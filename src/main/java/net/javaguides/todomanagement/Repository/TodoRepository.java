package net.javaguides.todomanagement.Repository;

import net.javaguides.todomanagement.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


//This repository extends the JPARepository which in turn extends crudRepository which has all DB related methods
//All these JpaRepository and CrudRepository methods will be implemented in the SimpleJpaRepository class
//JpaRepository is generic, so it takes two parameters, which are entity class name and the data type of primary key
//Now the TodoRepository gets all the CRUD related operations to perform
public interface TodoRepository extends JpaRepository<Todo,Long> {
}
