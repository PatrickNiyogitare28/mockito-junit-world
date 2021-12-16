package rw.ac.rca.termOneExam.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllWithElementTest_success(){
        ResponseEntity<?> response = restTemplate.getForEntity("/api/cities/all", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getByIdTest_success(){
        ResponseEntity<?> response = restTemplate.getForEntity("/api/cities/id/101", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getByTestIdTest_404(){
        ResponseEntity<?> response = restTemplate.getForEntity("/api/cities/id/530", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void addTest_success(){
        City city = new City("San Francisco", 20);
        ResponseEntity<City> response = restTemplate.postForEntity("/api/cities/add", city, City.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("San Francisco", Objects.requireNonNull(response.getBody()).getName());
    }

    @Test
    public void addTest_existByName(){
        City city = new City("Kigali", 20);
        ResponseEntity<APICustomResponse> response = restTemplate.postForEntity("/api/cities/add", city, APICustomResponse.class);

        assertEquals(false, response.getBody().getStatus());
    }

    @Test
    public void create_400() {
        City city = new City("Musanze", 11);
        ResponseEntity<City> response = restTemplate.postForEntity("/api/cities/add", city, City.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
