package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    // TODO: 12/05/2023 write unit tests

    @Mock
    VisualcrossingRepository weatherRepo;

    @InjectMocks
    WeatherService weatherService;

    @Test
    public void compareDaylightHours() {
        // Create mocked CityInfo and CurrentConditions
        CityInfo cityInfo1 = mock(CityInfo.class);
        CityInfo.CurrentConditions conditions1 = mock(CityInfo.CurrentConditions.class);

        // mocking response for getSunrise and getSunset
        when(conditions1.getSunrise()).thenReturn("06:00");
        when(conditions1.getSunset()).thenReturn("20:00");
        when(cityInfo1.getCurrentConditions()).thenReturn(conditions1);


        CityInfo cityInfo2 = mock(CityInfo.class);
        CityInfo.CurrentConditions conditions2 = mock(CityInfo.CurrentConditions.class);
        when(conditions2.getSunrise()).thenReturn("07:00");
        when(conditions2.getSunset()).thenReturn("18:00");
        when(cityInfo2.getCurrentConditions()).thenReturn(conditions2);

        // Setup the mock responses
        when(weatherRepo.getByCity("City1")).thenReturn(cityInfo1);
        when(weatherRepo.getByCity("City2")).thenReturn(cityInfo2);

        // Execute the method
        String cityWithLongestDay = weatherService.compareDaylight("City1", "City2");

        // Verify the result
        assertEquals("City1", cityWithLongestDay);
    }

    @Test
    public void checkForRain() {
        // Create mocked CityInfo and CurrentConditions
        CityInfo cityInfo1 = mock(CityInfo.class);
        CityInfo.CurrentConditions conditions1 = mock(CityInfo.CurrentConditions.class);
        //mocking response to getCondition and getCurrentCondition method
        when(conditions1.getConditions()).thenReturn("it is Raining");
        when(cityInfo1.getCurrentConditions()).thenReturn(conditions1);

        CityInfo cityInfo2 = mock(CityInfo.class);
        CityInfo.CurrentConditions conditions2 = mock(CityInfo.CurrentConditions.class);
        when(conditions2.getConditions()).thenReturn("Sunny");
        when(cityInfo2.getCurrentConditions()).thenReturn(conditions2);

        // Setup the mock responses
        when(weatherRepo.getByCity("City1")).thenReturn(cityInfo1);
        when(weatherRepo.getByCity("City2")).thenReturn(cityInfo2);

        // Execute the method
        String rainingCity = weatherService.checkForRain("City1", "City2");

        // Verify the result
        assertEquals("City1", rainingCity);
    }


}