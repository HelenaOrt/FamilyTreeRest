package com.FamilyTreeRest.FamilyTreeRest.Person;

import com.FamilyTreeRest.FamilyTreeRest.models.PersonModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdatePersonTest {

	@Autowired
	public TestRestTemplate testRestTemplate;

	@Test
	public void givenValidTermWithoutBeenAuthenticated_shouldFailAndReturn401Unauthorized(){

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+ "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjcmlzd" +
									 "GlhbiIsInN1YiI6IkVsZW5hIiwiZXhwIjoxNTY4MDk5ODU2LCJhdX" +
									 "Rob3JpdGllcyI6WyJST0xFX0FETUlOIl19.q4zgyxReib_CM1K8AbG-" +
									 "rZVXmUv9doqzBfKAcPLiZc2HTKamYW-9eKQivwTyfCSjQ_lQ4P6qjgMePd-jT9iGGA");

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

	}
}
