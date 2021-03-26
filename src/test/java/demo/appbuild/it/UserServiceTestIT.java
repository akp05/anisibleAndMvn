package demo.appbuild.it;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;

import demo.appbuild.User;
import demo.appbuild.UserRepository;

@SpringBootTest // (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
//@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class UserServiceTestIT {

	private static User p1;
	private static User p2;
	private static User p3;
	private static User p4;
	private static User p5;

	@BeforeAll
	public static void init() {
		p1 = new User("itname1", "itemail1", "itpwad1");
		p2 = new User("itname2", "itemail2", "itpwad2");
		p3 = new User("itname3", "itemail3", "itpwad3");
		p4 = new User("itname4", "itemail4", "itpwad4");
		p5 = new User("itname5", "itemail5", "itpwad5");
	}

//	@LocalServerPort
//    private static int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;

	@Autowired
	UserRepository repo;

	@Test
	@Order(1)
	void testGetUserByID_WhenNotPresent() {
		System.out.println("#### order 1 ###");
		Optional<User> foundUser = repo.findById(101);
		assertFalse(foundUser.isPresent());
	}

	@Test
	@Order(2)
	void testGetUserByID_WhenPresent() {
		System.out.println("#### order 2 ###");
		Optional<User> foundUser = repo.findById(1);
		System.out.println(foundUser);
		assertTrue(foundUser.isPresent());
	}

	@Test
	@Order(3)
	void testGetAllUsers() {
		System.out.println("#### order 3 ###");
		List<User> foundUsers = repo.findAll();
		assertEquals(4, foundUsers.size());

		List<User> refUsers = new ArrayList<User>();
		refUsers.add(new User(1, "testname1", "testemail1", "testpwd1"));
		refUsers.add(new User(2, "testname2", "testemail2", "testpwd2"));
		refUsers.add(new User(3, "testname3", "testemail3", "testpwd3"));
		refUsers.add(new User(4, "testname4", "testemail4", "testpwd4"));

		//assertIterableEquals(refUsers, foundUsers);
		assertEquals(refUsers.get(0).getName(), foundUsers.get(0).getName());
		// assertSame(E,A) or assertNotSame(E,A) validates for object references
	}

	@Test
	@Order(4)
	void testSaveOrUpdateUser_forSave() {
		System.out.println("#### order 4 ###");
		User createdUser = repo.save(new User(5, "testname5", "testemail5", "testpwd5"));
		System.out.println(createdUser + " #######");
		List<User> products = repo.findAll();
		assertEquals(5, products.size());
	}


}
