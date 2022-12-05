package shop.mtcoding.final5th.domain.schedule;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private String scheduleTitle;

    @Column(nullable = false)
    private Timestamp scheduleCreatedAt;
    private Timestamp scheduleStartAt;
    private Timestamp scheduleFinishAt;

    private String scheduleLocation;

    private String scheduleContent;

    private String scheduleNote;

    private String field;
}
