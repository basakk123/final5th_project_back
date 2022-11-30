package shop.mtcoding.final5th.domain.schedule_image;

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
@Table(name = "schedule_image")
@Entity
public class ScheduleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleImageId;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(nullable = false)
    private String shceduleImageUrl;

    @Column(nullable = false, length = 255)
    private String scheduleImageType;

    @Column(nullable = false, length = 255)
    private String scheduleImageName;

    @Column(nullable = false, length = 255)
    private String scheduleImageUuid;

    @Column(nullable = false)
    private Timestamp shceduleImageCreatedAt;
}
