package com.FamilyTreeRest.FamilyTreeRest.Person;

import com.FamilyTreeRest.FamilyTreeRest.controllers.PersonController;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.repositories.PersonRepository;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeletePersonTest {

	@InjectMocks
	private PersonController personController;

	@Mock
	private PersonRepository personRepository;

	@Autowired
	public TestRestTemplate testRestTemplate;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void givenValidTermWithoutBeenAuthenticated_shouldFailAndReturn401Unauthorized(){

		UriComponents url = UriComponentsBuilder.newInstance().scheme("/people").path("/1").build();
		ResponseEntity<PersonModel> result =
				testRestTemplate.exchange("/people/1", HttpMethod.DELETE,
										  new HttpEntity<>(new HttpHeaders()),
										  new ParameterizedTypeReference<PersonModel>() {});

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}
}
