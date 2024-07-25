package sideproject.board.batch;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sideproject.board.member.domain.Entity.MemberRepository;

@SpringBatchTest
@SpringBootTest
class JopConfigTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	@DisplayName("배치 테스트코드")
	public void testJob() throws Exception {


		JobExecution jobExecution = jobLauncherTestUtils.launchJob();


	}

}