package com.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/token")
public class TokenAPI {
	
	JWTUtil jwtUtil = new JWTHelper();
	
	@GetMapping
	public String getDummyToken() {
		return "testtoekn";
	}
	
	@PostMapping(consumes = "application/json")
	public Token getToken(@RequestBody TokenRequestData tokenRequestData) throws URISyntaxException {
		
		String name = tokenRequestData.getName();
		String password = tokenRequestData.getPassword();
		String scopes = tokenRequestData.getScopes();
		System.out.println(name + password);
		
		
		final URI uri = new URI("http://localhost:8080/api/customers/byname/" + name);
		System.out.println(uri);
		
		RestTemplate restTemplate = new RestTemplate();
		//Customer result = restTemplate.postForObject(uri, tokenRequestData, Customer.class);
		boolean result = restTemplate.postForObject(uri, tokenRequestData, boolean.class);
		
		System.out.println(result);

		if (name != null && name.length() > 0 
				&& password != null && password.length() > 0 
				&& result) {
			Token token = jwtUtil.createToken();
			//ResponseEntity<?> response = ResponseEntity.ok(getToken(tokenRequestData));
			System.out.println(token);
			return token;
		}
	// bad request - no result
		
		//return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return new Token("No user, no token");
		
	}
	
	
}