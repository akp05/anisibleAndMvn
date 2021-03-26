package demo.appbuild;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserRepository dao;
	
	public String getMessage() {
		return "Hello, This is message from service for user/";
	}
	
	public User getUserByID(int id) {		
		return dao.findById(id).get();
	}
	
	public User getUserByName(String name) {		
		return dao.findByName(name);
	}
	
	public List<User> getAllUsers() {
		return dao.findAll();
	}
		
	public User SaveOrUpdateUser(User user) {		
		return dao.save(user);
	}
		
	public void removeUser(int id) {
		dao.deleteById(id);
	}

}
