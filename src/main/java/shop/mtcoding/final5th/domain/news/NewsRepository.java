package shop.mtcoding.final5th.domain.news;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select ne from News ne where ne.targetUserId = :targetUserId")
    List<News> findNewsListByTargetUserId(@Param("targetUserId") Long targetUserId);
}
