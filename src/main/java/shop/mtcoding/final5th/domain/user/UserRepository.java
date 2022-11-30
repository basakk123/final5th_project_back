package shop.mtcoding.final5th.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where user_name = :userName")
    Optional<User> findByUsername(@Param("userName") String userName);
}
