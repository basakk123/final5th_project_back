package shop.mtcoding.final5th.domain.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.final5th.domain.schedule.Schedule;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String categoryColor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Schedule schedule;

    @Builder
    public Category(Long categoryId, String categoryName, Long userId, String categoryColor) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
        this.categoryColor = categoryColor;
    }
}
