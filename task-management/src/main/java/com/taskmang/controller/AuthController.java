package com.taskmang.controller;

//import org.apache.catalina.User;
import com.taskmang.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.taskmang.service.CustomerUserServiceImplemention;
import com.taskmang.config.JwtProvider;
import com.taskmang.repository.UserRepository;
import com.taskmang.request.LoginRequest;
import com.taskmang.response.Authresponse;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	@Autowired
	private UserRepository repo;
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private CustomerUserServiceImplemention customUserDetails;
	
	@PostMapping("/signup")
	public ResponseEntity<Authresponse> createUserHandler(
			@RequestBody User user) throws Exception{
		String email=user.getEmail();
		String password=user.getPassword();
		String fullname=user.getFullname();
		String role=user.getRole();
		
		User isEmailExist=repo.findByEmail(email);
		if(isEmailExist!=null) {
			throw new Exception("Email is already used with another account");
			
		}
//		Create new user
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setFullname(fullname);
		createdUser.setRole(role);
		createdUser.setPassword(passwordencoder.encode(password));
		
		repo.save(createdUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(email,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token=JwtProvider.generateToken(authentication);
		
		Authresponse authResponse=new Authresponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Register Success");
		authResponse.setStatus(true);
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
	}
	

	@PostMapping("/signin")

	public ResponseEntity<Authresponse> signin (@RequestBody LoginRequest loginRequest) {
		
	String username = loginRequest.getEmail();
	String password = loginRequest.getPassword();
	System.out.println(username+"-----"+ password);
	
	Authentication authentication = authenticate(username, password);
	SecurityContextHolder.getContext().setAuthentication(authentication);

	String token = JwtProvider.generateToken(authentication);
	Authresponse authResponse = new Authresponse();

	authResponse.setMessage("Login Success"); 
	authResponse.setJwt(token);
	authResponse.setStatus(true);
	
	return new ResponseEntity<>(authResponse,HttpStatus.OK);
	}
	
	private Authentication authenticate(String username,String password) {
		UserDetails userDetails=customUserDetails.loadUserByUsername(username);
		
		System.out.println("sign in userDetails - "+userDetails);
		
		if(userDetails==null) {
			System.out.println("sign in userDetails - null "+userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		if(!passwordencoder.matches(password,userDetails.getPassword())) {
			System.out.println("sign in userDetails - password not match "+userDetails);
			throw new BadCredentialsException("Invalid username or password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		
	}
	
}
