package shop.mtcoding.final5th.domain.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select cat from Category cat where cat.user_id = :userId")
    List<Category> findCategoryListByUserId(@Param("userId") Long userId);
}
