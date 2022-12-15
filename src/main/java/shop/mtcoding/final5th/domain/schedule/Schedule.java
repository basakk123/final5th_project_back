package shop.mtcoding.final5th.domain.schedule;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.final5th.domain.category.Category;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "schedule")
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false)
    private Long userId;

    private Long categoryId;

    @Column(nullable = false)
    private String scheduleTitle;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp scheduleCreatedAt;
    private Timestamp scheduleStartAt;
    private Timestamp scheduleFinishAt;

    private String scheduleLocation;

    private String scheduleContent;

    private String scheduleNote;

    private String field;

    @Builder
    public Schedule(Long scheduleId, Long userId, Long categoryId, String scheduleTitle, Timestamp scheduleCreatedAt,
            Timestamp scheduleStartAt, Timestamp scheduleFinishAt, String scheduleLocation, String scheduleContent,
            String scheduleNote, String field) {
        this.scheduleId = scheduleId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleCreatedAt = scheduleCreatedAt;
        this.scheduleStartAt = scheduleStartAt;
        this.scheduleFinishAt = scheduleFinishAt;
        this.scheduleLocation = scheduleLocation;
        this.scheduleContent = scheduleContent;
        this.scheduleNote = scheduleNote;
        this.field = field;
    }
}
