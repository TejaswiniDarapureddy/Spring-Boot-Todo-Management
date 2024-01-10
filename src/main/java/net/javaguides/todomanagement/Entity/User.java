package net.javaguides.todomanagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //This will have many- to-many relationship between user and roles
    //FetchType.EAGER means whenever we load user along with it also loads the roles
    //Cascade.Type.ALL means whenever we perform the operations on parent then the related operations will be saved in its child too
    //Here user is parent and role is child
    //This will be the third table in many-many relationship where primary keys in the first and second table becomes foreign keys in the third table which is the join table of first and second tables
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles;
}
