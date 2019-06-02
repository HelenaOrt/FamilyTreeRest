package com.FamilyTreeRest.FamilyTreeRest.Person;

import com.FamilyTreeRest.FamilyTreeRest.controllers.PersonController;
import com.FamilyTreeRest.FamilyTreeRest.entities.Person;
import com.FamilyTreeRest.FamilyTreeRest.exceptions.EntityNotFoundException;
import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import com.FamilyTreeRest.FamilyTreeRest.repositories.PersonRepository;
import com.oracle.webservices.internal.api.message.ContentType;
import com.sun.deploy.net.HttpResponse;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindOnePersonTest {

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
	public void givenValidTerm_shouldSuccessWith200AndReturnAObjectPerson(){

		UriComponents url = UriComponentsBuilder.newInstance().scheme("/people").path("/1").build();
		ResponseEntity<PersonModel> result =
				testRestTemplate.exchange("/people/1", HttpMethod.GET,
										  new HttpEntity<>(new HttpHeaders()),
										  new ParameterizedTypeReference<PersonModel>() {});

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertNotNull(result.getBody());
	}


	/*

	@Test
	public void givenValidId_shouldReturnExpectedPersonBody() throws JSONException {

			HttpEntity<String> entity = new HttpEntity<String>(new HttpHeaders());
			ResponseEntity<String> response = testRestTemplate.exchange("/people/{id]",
					HttpMethod.GET, entity, String.class);

			String expected = "{id: 1 ," +
							  "name:Helena," +
							  "lastName:Ortiz, " +
							  "age:22, " +
							  "country: Spain, " +
							  "sonsSet: []}";

			JSONAssert.assertEquals(expected, response.getBody(), false);
		}

		*/

	}



