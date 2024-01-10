package net.javaguides.todomanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//The above 4 annotations are from lombok library which automatically creates the getter,setter methods and constructors
@Entity //This annotation is used to specify the todo class as jpa entity
@Table(name = "todos") //This annotation is used to specify the table details. This name attribute is used to
//specify the table name. Basically this @table annotation maps this table to jpa entity
public class Todo {

    @Id//This is used to specify the primary key for the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This helps to automatically increment the value for te primary key
    private long id;

    //If we don't specify the column name with the column annotation then the jpa will automatically create the column name
    //as the name of the field in the entity class
    //This column annotation is used for column mapping
    @Column(name = "title",nullable = false) //This nullable attribute makes the column not null or not
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    private boolean completed;

}
