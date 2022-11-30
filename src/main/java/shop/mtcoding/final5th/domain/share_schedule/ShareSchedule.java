package shop.mtcoding.final5th.domain.share_schedule;

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
@Table(name = "share_schedule")
@Entity
public class ShareSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long key;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long scheduleId;
}
