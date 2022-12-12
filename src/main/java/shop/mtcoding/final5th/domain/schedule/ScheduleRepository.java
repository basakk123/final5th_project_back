package shop.mtcoding.final5th.domain.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("select sch from Schedule sch where sch.userId = :userId")
    List<Schedule> findScheduleListByUserId(@Param("userId") Long userId);
}
