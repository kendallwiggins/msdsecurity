package com.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/token")
public class TokenAPI {
	
	JWTUtil jwtUtil = new JWTHelper();
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> getToken(@RequestBody TokenRequestData tokenRequestData) {
		
		String username = tokenRequestData.getUsername();
		String password = tokenRequestData.getPassword();
		//String scopes = tokenRequestData.getScopes();
		System.out.println(username + password);
		
		final String uri = "http://localhost:8080/api/customers/byname" + "username";
		System.out.println(uri);
		
		RestTemplate restTemplate = new RestTemplate();
		boolean result = restTemplate.postForObject(uri, tokenRequestData, boolean.class);
				
		System.out.println(result);

		if (username != null && username.length() > 0 
				&& password != null && password.length() > 0 
				&& Authenticator.checkPassword(username, password)) {
			ResponseEntity<?> response = ResponseEntity.ok(getToken(tokenRequestData));
			return response;			
		}
		
		// if there is a result, create a token
		if(result) {
			Token token = jwtUtil.createToken();
			ResponseEntity<?> response = ResponseEntity.ok(token);
			return response;
		}
		
		// bad request - no result
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
	}
	
	
}