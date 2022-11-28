package shop.mtcoding.final5th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 날짜 입력 활성화
@SpringBootApplication
public class Final5thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Final5thApplication.class, args);
	}
}
