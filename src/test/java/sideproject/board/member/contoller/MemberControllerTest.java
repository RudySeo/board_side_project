package sideproject.board.member.contoller;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import sideproject.board.member.contoller.requests.CreateMemberRequest;
import sideproject.board.member.contoller.requests.LoginMemberRequest;
import sideproject.board.member.domain.Entity.Member;
import sideproject.board.member.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MemberService memberService;

	@Test
	@DisplayName("회원가입")
	public void signUp() throws Exception {

		//Given
		CreateMemberRequest request = new CreateMemberRequest(1L, "dkseo@naver.com", "1234", "서동권", 20);
		String json = new ObjectMapper().writeValueAsString(request);

		given(memberService.signUp(any(Member.class))).willReturn(request.toEntity());
		//When
		mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
			)
			.andExpect(status().isOk())
			.andDo(print());

		//Then
		verify(memberService).signUp(any(Member.class));
	}

	@Test
	@DisplayName("로그인")
	public void login() throws Exception {
		//Given
		LoginMemberRequest request = new LoginMemberRequest("dddd", "222221");
		String json = new ObjectMapper().writeValueAsString(request);

		given(memberService.login(request)).willReturn(anyString());

		//When
		mockMvc.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
			)
			.andExpect(status().isOk())
			.andDo(print());

		//Then
		verify(memberService).login(request);

	}
}