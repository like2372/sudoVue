package like.controller;

import java.util.HashMap;

import like.entity.User;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration  
public class UserController {
	
	 @RequestMapping("/")  
	    String home() {  
	        return "Hello World!";  
	    }  
	 
	 @RequestMapping("/user")  
	    User getUser() {  
		 	User user=new User();
		 	user.setId(new Long(123));
		 	user.setName("like");
	        return user;  
	    }  
	 
	 @RequestMapping("/hello/{myName}")  
	    String index(@PathVariable String myName) {  
	        return "Hello "+myName+"!!!";  
	    } 
	 
	 @RequestMapping("/hash")  
	    HashMap<String,Object> hash(@PathVariable String myName) {  
		 	HashMap<String,Object> ha=new HashMap<String,Object>();
		 	ha.put("1", "1");
		 	/*User user=new User();
		 	user.setId(new Long(123));
		 	user.setName("like");
		 	ha.put("2",user);*/
		 	ha.put("3", 5);
	        return ha;  
	    } 
}