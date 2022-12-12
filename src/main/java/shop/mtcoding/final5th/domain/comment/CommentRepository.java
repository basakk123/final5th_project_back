package shop.mtcoding.final5th.domain.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select com from Comment com where com.scheduleId = :scheduleId")
    List<Comment> findCommentListByScheduleId(@Param("scheduleId") Long scheduleId);
}
