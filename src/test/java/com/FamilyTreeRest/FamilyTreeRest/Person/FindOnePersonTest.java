package com.FamilyTreeRest.FamilyTreeRest.Person;

import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindOnePersonTest {

	//este no pasa

	@Autowired
	public TestRestTemplate testRestTemplate;


	@Test
	public void givenValidTerm_shouldSuccessWith200AndReturnsOneObject(){
		UriComponents url = UriComponentsBuilder.fromUriString("/people/{id}").build();
		PersonModel personModel = new PersonModel();
		ResponseEntity<PersonModel> result =
				testRestTemplate.exchange("/people/{id}", HttpMethod.GET,
										  new HttpEntity<>(new HttpHeaders()),
										  new ParameterizedTypeReference<PersonModel>() {});

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
	}
}
