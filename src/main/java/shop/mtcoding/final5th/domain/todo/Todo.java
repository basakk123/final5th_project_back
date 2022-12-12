package shop.mtcoding.final5th.domain.todo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "todo")
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String todoTitle;

    @Column(nullable = false)
    private boolean isFinished;

    @Builder
    public Todo(Long todoId, Long userId, String todoTitle, boolean isFinished) {
        this.todoId = todoId;
        this.userId = userId;
        this.todoTitle = todoTitle;
        this.isFinished = isFinished;
    }
}
