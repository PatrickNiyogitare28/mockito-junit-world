package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

     @Mock
     private ICityRepository iCityRepository;

     @InjectMocks
     private CityService cityService;

     @Test
     public void getAllTest_WithSomeDataSuccess(){
         when(iCityRepository.findAll()).thenReturn(Arrays.asList(
                 new City(1L, "Kigali", 24,75.2),
                 new City(1L, "Musanze", 18,44.4)
         ));
       assertEquals("Musanze", cityService.getAll().get(1).getName());
    }

    @Test
    public void getAllTest_WithEmptySuccess(){
        when(iCityRepository.findAll()).thenReturn(Arrays.asList()
        );
        assertEquals(0, cityService.getAll().size());
    }

    @Test
    public void getByIdTest_success(){
        when(iCityRepository.findById(101L)).thenReturn(Optional.of(new City(1L, "Kigali", 24,75.2)));
        assertEquals("Kigali", cityService.getById(101L).get().getName());
    }

    @Test
    public void getByIdTest_404(){
        assertEquals(false, cityService.getById(101L).isPresent());
    }

    @Test
    public void existsByNameTest_fail(){
        when(iCityRepository.existsByName("Muhanga")).thenReturn(false);
        assertEquals(false, cityService.existsByName("Muhanga"));
    }

    @Test
    public void existByNameTest_success(){
        when(iCityRepository.existsByName("Kigali")).thenReturn(true);
        assertEquals(true, cityService.existsByName("Kigali"));
    }

    @Test
    public void saveTest_success(){
         City newCity = new City(1001, "Kampala",28,82.4);
        CreateCityDTO cityDTO = new CreateCityDTO("Kampala",28);
        when(iCityRepository.save(any(City.class))).thenReturn(newCity);
        assertEquals("Kampala",cityService.save(cityDTO).getName());
    }
}
