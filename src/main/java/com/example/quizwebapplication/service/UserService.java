package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dao.UserDao;
import com.example.quizwebapplication.domain.User;
import com.example.quizwebapplication.dto.common.ResponseStatus;
import com.example.quizwebapplication.dto.user.UserDTO;
import com.example.quizwebapplication.dto.user.UserResponse;
import com.example.quizwebapplication.exception.DuplicateResourceException;
import com.example.quizwebapplication.security.AuthUserDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public List<UserDTO> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public UserDTO getUserDtoById(int id) {
        return userDao.getUserDtoById(id);
    }

    @Transactional
    public void addUser(User user) {
        Optional<User> userOptional = userDao.loadUserByUsername(user.getEmail());

        if (userOptional.isPresent()){
            throw new DuplicateResourceException("Email " + user.getEmail() + " taken");
        }
        userDao.addUser(user);
    }

    @Transactional
    public void deleteUserById(int id) {
        userDao.deleteUser(id);
    }

    @Transactional
    public void activeOrSuspendUserById(int id, boolean isActive){
        userDao.activeOrSuspendUserById(id, isActive);
    }

    @Override
    //why I need transactional here?
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Optional<User> userOptional = userDao.loadUserByUsername(username);

        if (!userOptional.isPresent()){
            throw new BadCredentialsException("Incorrect credentials, please try again.");
        }

        User user = userOptional.get(); // database user
        System.out.println("User "  + user);

        return AuthUserDetail.builder() // spring security's userDetail
                .id(user.getId())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .username(user.getEmail())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .authorities(getAuthoritiesFromUser(user))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }

    private List<GrantedAuthority> getAuthoritiesFromUser(User user){
        List<GrantedAuthority> userAuthorities = new ArrayList<>();

        if(user.isAdmin()){
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }else{
            userAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return userAuthorities;
    }

    public UserResponse checkInput(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(error -> System.out.println(error.getObjectName() + ": " + error.getDefaultMessage()));
            return UserResponse.builder()
                    .status(
                            ResponseStatus.builder()
                                    .success(false)
                                    .message("Oops, please check your input")
                                    .build()
                    )
                    .user(null)
                    .build();
        }else{
            return null;
        }
    }
}
