package com.example.quizwebapplication.dao;


import com.example.quizwebapplication.domain.User;
import com.example.quizwebapplication.dto.user.UserDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao extends AbstractHibernateDao<User>{
    public UserDao() {
        setClazz(User.class);
    }

    public Optional<User> loadUserByUsername(String email) {
        System.out.println("In Dao loadUserByUsername");
        System.out.println("email " + email);

        Session session = this.getCurrentSession();
        String sql = "FROM User WHERE email = :email";
        Query<User> query = session.createQuery(sql, User.class);
        query.setParameter("email", email);
        User user = query.uniqueResult();

        System.out.println("User @loadUserByUsername in UserDao " + user);
        if(user != null && !user.isActive()) {
            System.out.println("User is not active");
            throw new RuntimeException("User is not active");
        }
        return Optional.ofNullable(user);
    }
    public User getUserById(int id) {
        return this.findById(id);
    }

    public void addUser(User user) {
        this.add(user);
    }

    public List<UserDTO> getAllUsers() {
        Session session = this.getCurrentSession();
        String sql = "SELECT new com.example.quizwebapplication.dto.user.UserDTO(u.id, u.email, u.firstName, u.lastName, u.isActive, u.isAdmin) FROM User u";
        Query<UserDTO> res = session.createQuery(sql, UserDTO.class);
        return res.getResultList();
    }

    public void deleteUser(int id) {
        this.deleteById(id);
    }

    public void activeOrSuspendUserById(int id, boolean isActive){
        User user = this.getUserById(id);
        user.setActive(isActive);
        this.update(user);
    }

    public UserDTO getUserDtoById(int id) {
        Session session = this.getCurrentSession();
        String sql = "SELECT new com.example.quizwebapplication.dto.user.UserDTO(u.id, u.email, u.firstName, u.lastName, u.isActive, u.isAdmin) FROM User u WHERE id = :id";
        Query<UserDTO> query = session.createQuery(sql, UserDTO.class);
        query.setParameter("id", id);
        return query.uniqueResult();
    }
}
