package com.rainier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rainier.config.JwtProvider;
import com.rainier.exception.UserException;
import com.rainier.model.User;
import com.rainier.repository.UserRepository;
import com.rainier.request.LoginRequest;
import com.rainier.response.AuthResponse;
import com.rainier.service.CustomUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private PasswordEncoder passwordEncode;

	@Autowired
	private CustomUserServiceImplementation customUserService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		User isEmailExist = userRepo.findByEmail(email);

		if (isEmailExist != null) {
			throw new UserException("This Email is already used with another account");
		}

		User createdUser = new User();

		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncode.encode(password));
		createdUser.setFirstName(firstName);
		createdUser.setFirstName(firstName);
		createdUser.setLastName(lastName);

		User savedUser = userRepo.save(createdUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getEmail());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String generateToken = jwtProvider.generateToken(authentication);
		

		AuthResponse response  = new AuthResponse();
		response.setJwt(generateToken);
		response.setMessage("SignUp Success...");

		return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);
	}
    @PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) {

		String userName = loginRequest.getEmail();

		String password = loginRequest.getPassword();

		Authentication authentication = authenticate(userName, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String generateToken = jwtProvider.generateToken(authentication);

		AuthResponse response  = new AuthResponse();
		response.setJwt(generateToken);
		response.setMessage("SighIn Success...");
		

		return new ResponseEntity<AuthResponse>(response, HttpStatus.CREATED);
	}
    
   
	private Authentication authenticate(String userName, String password) {

		UserDetails userDetails = customUserService.loadUserByUsername(userName);

		if (userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}

		if (!passwordEncode.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
}
