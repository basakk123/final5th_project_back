package shop.mtcoding.final5th.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where user_name = :userName")
    Optional<User> findByUsername(@Param("userName") String userName);

    @Query(value = "insert into user (username, password, email, emailConfirm) values(:username, :password, :email, :emailConfirm)", nativeQuery = true)
    void join(String username, String password, String email, String emailConfirm);

    @Query(value = "select * from user where username = :username and password = :password", nativeQuery = true)
    User mLogin(String username, String password);

    @Query(value = "select * from user where username = :username and emailConfirm = :emailConfirm", nativeQuery = true)
    User mCheck(String username, String emailConfirm);

    @Query(value = "update user set emailConfirm = 'Y' where username = :username", nativeQuery = true)
    void mUpdate(String username);
}
