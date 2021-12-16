package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CityUtilTest {

    @Autowired
    private ICityRepository cityRepository;

  /* spay and mock */
    List<City> mock = mock(List.class);


    @Test
    public void testMocking() {
        ArrayList<City> arrayListMock =  mock(ArrayList.class);
        System.out.println(arrayListMock.get(0));//null
        System.out.println(arrayListMock.size());//0

        arrayListMock.add(new City("Paris",15));
        arrayListMock.add(new City("Dortmund", 12));
        System.out.println(arrayListMock.size());//0

        when(arrayListMock.size()).thenReturn(5);
        System.out.println(arrayListMock.size());//5
    }

    @Test
    public void testSpying() {
        ArrayList<City> arrayListSpy =  spy(ArrayList.class);
        arrayListSpy.add(new City("Chicago",21));
        System.out.println(arrayListSpy.get(0));//Test0
        System.out.println(arrayListSpy.size());//1

        arrayListSpy.add(new City("Nairobi",27));
        arrayListSpy.add(new City("Dodoma",28));
        System.out.println(arrayListSpy.size());//3

        when(arrayListSpy.size()).thenReturn(5);
        System.out.println(arrayListSpy.size());//5

        arrayListSpy.add(new City("Tripoli",32));
        System.out.println(arrayListSpy.size());//5
    }


    /*Other tests*/
    @Test
    public void weatherShouldBeLessThan40DegreesTest_success(){
        List<City> cities = cityRepository.findAll();
        for(City city: cities){
            assertTrue(city.getWeather() < 40);
        }
    }

    @Test
    public void weatherShouldBeGreaterThan10DegreesTest_success(){
        List<City> cities = cityRepository.findAll();
        for(City city: cities){
            assertTrue(city.getWeather() > 10);
        }
    }

    @Test
    public void cityContainsKigaliAndMusanze_success(){
        List<City> cities = cityRepository.findAll();
        assertTrue(containsCityName("Kigali", cities) && containsCityName("Musanze", cities));
    }

    private Boolean containsCityName(String name, List<City> cities){
        for(City city: cities){
            if(city.getName().equals(name)) return true;
        }
        return false;
    }



}
