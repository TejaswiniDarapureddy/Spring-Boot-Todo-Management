package net.javaguides.todomanagement.Security;

import lombok.AllArgsConstructor;
import net.javaguides.todomanagement.Entity.User;
import net.javaguides.todomanagement.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
//This is a customUserDetailsService class that implements the USerDetailService Interface and overrides its loadByUserName method
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //Here we are retrieving the user object from the database by using username or email
        User user = userRepository.findByUserNameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(()-> new UsernameNotFoundException("User Not exists by username or email"));
        //We are converting a set of roles into set of granted authorities as this will be expected by spring security
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        //Created user object that is provided by the spring security and returned that user object
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail, user.getPassword(), authorities
        );
    }
}
