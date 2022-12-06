package shop.mtcoding.final5th.domain.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select fo from Follow fo left join fo.users us on fo.user_id = us.user_id where fo.following_user_id = :followingUserId")
    List<Follow> findFollowListByFollowingUserId(@Param("followingUserId") Long followingUserId);
}
