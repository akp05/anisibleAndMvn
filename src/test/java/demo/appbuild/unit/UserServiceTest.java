package demo.appbuild.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import demo.appbuild.User;
import demo.appbuild.UserRepository;
import demo.appbuild.UserService;

//Note: use of static imports from hamcrest for junit5

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	private static User p1;
	private static User p2;
	private static User p3;
	private static User p4;
	private static User p5;

	@Mock
	private UserRepository repo;

	@InjectMocks // auto inject UserRepository
	private UserService service;// = new UserService();

	@BeforeAll
	public static void init() {
		p1 = new User("itname1", "itemail1", "itpwad1");
		p2 = new User("itname2", "itemail2", "itpwad2");
		p3 = new User("itname3", "itemail3", "itpwad3");
		p4 = new User("itname4", "itemail4", "itpwad4");
		p5 = new User("itname5", "itemail5", "itpwad5");	
	}


	@DisplayName("test1 when empty")
	@Test
	void testA1ReadAllUsers_WhenEmpty() {
		Mockito.when(repo.findAll()).thenReturn(Arrays.asList());
		assertEquals(0, service.getAllUsers().size());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}
	

	@DisplayName("test2 fetch all Products")
	@Test
	void testA2ReadAllUsers() {
		Mockito.when(repo.findAll()).thenReturn(Arrays.asList(p1, p2, p3));
		assertEquals(3,service.getAllUsers().size());
		assertEquals(p1,service.getAllUsers().get(0));
		assertEquals(p2,service.getAllUsers().get(1));
		assertEquals(p3,service.getAllUsers().get(2));
		Mockito.verify(repo, Mockito.times(4)).findAll();
	}
	
	@DisplayName("test3 get Product by Id")
	@Test
	void testGetUserByID() {
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(p1));
		assertThat(service.getUserByID(1), is(p1));
		Mockito.verify(repo, Mockito.times(1)).findById(1);

	}
	

	@Test
	void testA4SaveOrUpdateUser_save() {
		Mockito.when(repo.save(p1)).thenReturn(p1);
		assertThat(service.SaveOrUpdateUser(p1), is(p1));
		Mockito.verify(repo, Mockito.times(1)).save(p1);	
		
		Mockito.when(repo.save(p2)).thenReturn(p2);
		assertThat(service.SaveOrUpdateUser(p2).getName(), is("itname2"));
		Mockito.verify(repo, Mockito.times(1)).save(p2);
	}
	
	@Test
	void testA5SaveOrUpdateUser_Update() {
		Mockito.when(repo.save(p1)).thenReturn(p1);
		assertThat(service.SaveOrUpdateUser(p1), is(p1));
		Mockito.verify(repo, Mockito.times(1)).save(p1);

		Mockito.when(repo.save(p2)).thenReturn(p2);
		assertThat(service.SaveOrUpdateUser(p2).getName(), is("itname2"));
		Mockito.verify(repo, Mockito.times(1)).save(p2);
	}

	@Test
	void testA6FindByName() {
		Mockito.when(repo.findByName("sony")).thenReturn(p1);
		assertThat(service.getUserByName(("sony")), is(p1));
		Mockito.verify(repo, Mockito.times(1)).findByName("sony");	
	}

	@Test
	void testA8RemoveUser() {
		service.removeUser(1);
		Mockito.verify(repo, Mockito.times(1)).deleteById(1);
	}

}
