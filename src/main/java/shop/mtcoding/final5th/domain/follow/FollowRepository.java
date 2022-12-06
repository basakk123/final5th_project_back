package shop.mtcoding.final5th.domain.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query(value = "select * from follow fo inner join users on fo.user_id = users.user_id where fo.following_user_id = :followingUserId", nativeQuery = true)
    List<Follow> findFollowListByFollowingUserId(@Param("followingUserId") Long followingUserId);
}
