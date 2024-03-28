package java12.repository;

import java12.entities.User;
import java12.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);

    default User getByEmail(String email) {
        return findByEmail(email).orElseThrow(() ->
                new NotFoundException("User with email: " + email + " not found!"));
    }
}
