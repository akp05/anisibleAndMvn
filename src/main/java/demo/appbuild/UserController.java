package demo.appbuild;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/")
	public ModelAndView getMessage() {		
		return new ModelAndView("welcomepage","welcome",service.getMessage());
	}
	
	@GetMapping("/getwelcomepage")
	public ModelAndView getHomePage() {		
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("message", "### Welcome to User management Home page ###");
		return new ModelAndView("welcomepage",model);
	}
	
	@GetMapping("/getloginregister")
	public ModelAndView respondLoginForm() {
		Map<String,Object> model=new HashMap<String,Object>();
		return new ModelAndView("loginregister",model);
	}
	
	@PostMapping("/doprocesslogin")
	public ModelAndView processForm(@ModelAttribute("user") User user) {		
		Map<String, Object> model = new HashMap<String, Object>();	
		model.put("message", "!!! This Login Process Response !!!");		
		return new ModelAndView("responsepage",model);
	}
	
	@PostMapping("/doregistration")
	public ModelAndView registerNewUser(@ModelAttribute("user") User user) {
		user=service.SaveOrUpdateUser(user);
		Map<String,Object> model=new HashMap<String,Object>();
		List<User> users=service.getAllUsers();
		model.put("message", "### Find registered Users ###");
		model.put("users", users);
		return new ModelAndView("allusers",model);
	}
	
	
	@GetMapping("/getallusers")
	public ModelAndView getAllUsers() {
		Map<String,Object> model=new HashMap<String,Object>();
		List<User> users=service.getAllUsers();
		model.put("message", "### Find registered Users ###");
		model.put("users", users);
		return new ModelAndView("allusers",model);
	}
	
	@GetMapping("/getedituser")
	public ModelAndView getEditUser(int id) {
		System.out.println("### edit id: "+id);
		Map<String,Object> model=new HashMap<String,Object>();
		return new ModelAndView("responsepage",model);
	}
	
	@GetMapping("/deleteuser")
	public ModelAndView deleteUser(int id) {
		System.out.println("### delete id: "+id);
		Map<String,Object> model=new HashMap<String,Object>();
		return new ModelAndView("responsepage",model);
	}

}
