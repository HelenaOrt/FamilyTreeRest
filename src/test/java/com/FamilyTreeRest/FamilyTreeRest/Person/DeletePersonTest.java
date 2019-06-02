package com.FamilyTreeRest.FamilyTreeRest.Person;

import com.FamilyTreeRest.FamilyTreeRest.constants.properties.JwtProperties;
import com.FamilyTreeRest.FamilyTreeRest.controllers.AuthenticationController;
import com.FamilyTreeRest.FamilyTreeRest.controllers.PersonController;
import com.FamilyTreeRest.FamilyTreeRest.entities.Authority;
import com.FamilyTreeRest.FamilyTreeRest.models.AuthenticationRequest;
import com.FamilyTreeRest.FamilyTreeRest.models.AuthenticationResponse;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.repositories.PersonRepository;
import com.FamilyTreeRest.FamilyTreeRest.repositories.WebUserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeletePersonTest {


	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private TestRestTemplate testRestTemplate;

/*
	@Test
	public void givenValidTermWithoutBeenAuthenticated_shouldFailAndReturn401Unauthorized() {

		UriComponents url = UriComponentsBuilder.newInstance().scheme("/people").path("/1").build();
		ResponseEntity<PersonModel> result =
				testRestTemplate.exchange("/people/1", HttpMethod.DELETE,
										  new HttpEntity<>(new HttpHeaders()),
										  new ParameterizedTypeReference<PersonModel>() {
										  });

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

	@Test
	public void givenValidTermWithRolAdmin_shouldSucessAndReturn200() {
		UriComponents url = UriComponentsBuilder.newInstance().scheme("/people").path("/1").build();

		AuthenticationRequest request = new AuthenticationRequest();
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

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Authorization", response.getToken());

		ResponseEntity<PersonModel> result =
				testRestTemplate.exchange("/people/1", HttpMethod.DELETE,
										  new HttpEntity<>(httpServletResponse),
										  new ParameterizedTypeReference<PersonModel>() {
										  });

		assertEquals(HttpStatus.OK, result.getStatusCode());

		*/

	}

