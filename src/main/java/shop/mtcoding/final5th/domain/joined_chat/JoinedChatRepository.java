package shop.mtcoding.final5th.domain.joined_chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JoinedChatRepository extends JpaRepository<JoinedChat, Long> {

    @Query(value = "select jo from JoinedChat jo where jo.user_id = :userId", nativeQuery = true)
    List<JoinedChat> findJoinedChatListByUserId(@Param("userId") Long userId);
}
