package shop.mtcoding.final5th.domain.category;

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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    private Long scheduleId;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String categoryColor;

    @Builder
    public Category(Long categoryId, Long scheduleId, String categoryName, Long userId, String categoryColor) {
        this.categoryId = categoryId;
        this.scheduleId = scheduleId;
        this.categoryName = categoryName;
        this.userId = userId;
        this.categoryColor = categoryColor;
    }
}
