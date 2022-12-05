package shop.mtcoding.final5th.domain.joined_chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JoinedChatRepository extends JpaRepository<JoinedChat, Long> {

    @Query("select jo from JoinedChat jo where jo.user_id = :userId")
    List<JoinedChat> findJoinedChatListByUserId(@Param("userId") Long userId);
}
