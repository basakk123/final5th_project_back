package shop.mtcoding.final5th.domain.sub_schedule;

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
@Table(name = "sub_schedule")
@Entity
public class SubSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subScheduleId;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(nullable = false, length = 255)
    private String subScheduleTitle;

    @Column(nullable = false)
    private Timestamp subScheduleCreatedAt;
    private Timestamp subScheduleStartAt;
    private Timestamp subScheduleFinishAt;

    @Column(length = 255)
    private String subScheduleLocation;

    @Column(length = 255)
    private String subScheduleContent;
}
