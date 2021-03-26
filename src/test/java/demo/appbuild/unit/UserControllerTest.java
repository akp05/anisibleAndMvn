package demo.appbuild.unit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import demo.appbuild.UserController;
import demo.appbuild.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

//	@Test
//	public void greetingShouldReturnMessageFromService() throws Exception {
//		when(service.getMessage()).thenReturn("Hello, Mock");
//		this.mockMvc.perform(get("/user/")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello, Mock")));
//	}
//	
//	@Test
//	void testGetHomePage() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRespondLoginForm() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testProcessForm() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRegisterNewUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetAllUsers() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetEditUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteUser() {
//		fail("Not yet implemented");
//	}

}
