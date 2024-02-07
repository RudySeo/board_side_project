package sideproject.board.batch;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import sideproject.board.member.domain.Entity.Member;

@Configuration
@RequiredArgsConstructor
public class JopConfig {
	private final int SIZE = 5;

	private final JobBuilderFactory jobBuilderFactory;

	private final StepBuilderFactory stepBuilderFactory;

	private final EntityManagerFactory entityManagerFactory;


	@Bean
	public Job testJob() {
		return this.jobBuilderFactory.get("testJob")
			.start(testStep())
			.build();
	}

	@Bean
	public Step testStep() {
		return this.stepBuilderFactory.get("testStep")
			.<Member, Member>chunk(SIZE)
			.reader(testItem())
			.processor(testProcess())
			.writer(testWriter())
			.build();
	}

	@Bean
	@StepScope
	public JpaCursorItemReader<Member> testItem() {
		return new JpaCursorItemReaderBuilder<Member>()
			.name("testItem")
			.entityManagerFactory(entityManagerFactory)
			.queryString("SELECT m FROM MEMBER m WHERE m.age = :age")
			.parameterValues(Map.of("age", 12))
			.build();

	}

	@Bean
	public ItemProcessor<Member, Member> testProcess() {
		return member -> {
			//값을 업데이트 하는 역할을 한다
			member.addMoney(1000);
			return member;
		};
	}

	@Bean
	public JpaItemWriter<Member> testWriter() {
		return new JpaItemWriterBuilder<Member>()
			.entityManagerFactory(entityManagerFactory)
			.build();
	}
}
