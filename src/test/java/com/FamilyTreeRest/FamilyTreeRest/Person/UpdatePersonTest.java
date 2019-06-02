package com.FamilyTreeRest.FamilyTreeRest.Person;

import com.FamilyTreeRest.FamilyTreeRest.constants.properties.JwtProperties;
import com.FamilyTreeRest.FamilyTreeRest.models.AuthenticationRequest;
import com.FamilyTreeRest.FamilyTreeRest.models.AuthenticationResponse;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdatePersonTest {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void givenValidTermWithoutBeenAuthenticated_shouldFailAndReturn401Unauthorized(){

		/*AuthenticationRequest request = new AuthenticationRequest();
		request.setUsername("Elena");
		request.setPassword("1234");


		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(),
														request.getPassword()));


		String token = Jwts.builder()
						   .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
						   .setIssuer(jwtProperties.getIssuer())
						   .setSubject(request.getUsername())
						   .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationInMillis()))
						   .claim(jwtProperties.getAuthoritiesClaim(), "ADMIN")
						   .compact();

		AuthenticationResponse response = new AuthenticationResponse(token);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", response.getToken());

		HttpEntity<String> entity = new HttpEntity<String>("\"id\": 1, \"name\": \"New Name\"" +
														   ",\"lastName\": \"Ortiz\",\"age\": 22,\"country\": \"Spain\"," +
														   "\"fatherId\": 2\n",headers);


		String result = testRestTemplate.postForObject("/people", entity, String.class);

		UriComponents url = UriComponentsBuilder.newInstance().scheme("/people").path("/1").build();
		ResponseEntity<PersonModel> responseEntity =
				testRestTemplate.exchange("/people/1", HttpMethod.PUT,
										  new HttpEntity<>(new HttpHeaders()),
										  new ParameterizedTypeReference<PersonModel>() {});
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
*/
	}
}
