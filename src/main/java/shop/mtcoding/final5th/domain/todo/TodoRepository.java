package shop.mtcoding.final5th.domain.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select td from Todo td where td.userId = :userId")
    List<Todo> findTodoListByUserId(@Param("userId") Long userId);
}
