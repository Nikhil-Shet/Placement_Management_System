package com.user;
import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repo;

    // Get all users
    public List<UserEntity> listAll() {
        return repo.findAll();
    }

    // Save a new or existing user
    public UserEntity save(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("User entity cannot be null");
        }
        return repo.save(user);
    }

    // Get a user by ID
    public Optional<UserEntity> get(Integer id) {
        return repo.findById(id);
    }

    // Delete a user by ID
    public void delete(Integer id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }
}
