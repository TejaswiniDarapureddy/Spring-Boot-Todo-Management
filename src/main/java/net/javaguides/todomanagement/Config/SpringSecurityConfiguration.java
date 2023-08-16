package net.javaguides.todomanagement.Config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration // This annotation makes the class a spring java based configuration in which we can define the spring beans
@EnableMethodSecurity //This annotation will enable the class with access control rules for controlling who can execute them based on roles, permissions etc.
@AllArgsConstructor
public class SpringSecurityConfiguration {

    private UserDetailsService userDetailsService;

    //This method is used to encode the password. In order to make object as single object we used static. PasswordEncoder is an interface
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //this will internally use the Bcrypt algorithm which hash the password
    }
    //To configure the spring security we need to create a spring bean for securityFilterChain which is an interface
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //All the incoming HTTp requests are authenticated by using anyRequest().authenticated() method
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
//            //This will match the http post request that has the url which has api and has role Admin can only perform addTodo operation
//            authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
//                    //This will match the http delete request that has the url which has api and has role Admin can only perform deleteTodo operation
//            authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
//                    //This will match the http put request that has the url which has api and has role Admin can only perform updateTodo operation
//            authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
//                    //This will match the http get request that has the url which has api and has roles Admin,user can only perform getTodo and getAllTodo operations
//            authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
//                    //This will match the http post patch that has the url which has api and has roles Admin,user can only perform completeTodo and incompleteTodo operations
//            authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");
//            //This will permit all users to access the http get request
//            //authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
            authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());
        return http.build(); //This build method returns the DefaultSecurityFilterChain class object

    }
    @Bean
    //This AuthenticationManager will authenticate the respective provider and by using the that provider will connect to database
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    //This UserDetailService is used to provide the user-related information to the authentication process. It used to define the roles for the users.
    //This is In-Memory Authentication
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails teja = User.builder()
//                .username("teja")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(teja,admin); //store the user objects in in-memory object
//    }
}
