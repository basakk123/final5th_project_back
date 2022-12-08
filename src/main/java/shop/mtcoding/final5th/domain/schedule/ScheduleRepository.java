package shop.mtcoding.final5th.domain.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "select sch from Schedule sch where sch.user_id = :userId", nativeQuery = true)
    List<Schedule> findScheduleListByUserId(@Param("userId") Long userId);

    @Query(value = "select sch from Schedule sch left join sch.category ca where sch.user_id = :userId", nativeQuery = true)
    List<Schedule> findScheduleListAndCategoryByUserId(@Param("userId") Long userId);
}
