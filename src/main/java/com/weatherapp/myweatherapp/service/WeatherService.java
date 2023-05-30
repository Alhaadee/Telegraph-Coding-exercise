package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }

  public String compareDaylight(String city1, String city2) {
    Duration daylightCity1 = calculateDaylightHours(city1);
    Duration daylightCity2 = calculateDaylightHours(city2);

    if (daylightCity1.compareTo(daylightCity2) > 0)
      return city1;
    else if (daylightCity1.compareTo(daylightCity2) < 0)
      return city2;
    else
      return "Both cities have equal daylight hours.";

  }

  public Duration calculateDaylightHours(String city) {
    CityInfo cityInfo = weatherRepo.getByCity(city);


    // Convert sunrise and sunset time from string to LocalTime
    LocalTime sunrise = LocalTime.parse(cityInfo.getCurrentConditions().getSunrise());
    LocalTime sunset = LocalTime.parse((cityInfo.getCurrentConditions().getSunset()));

    return Duration.between(sunrise, sunset);
  }


  public String checkForRain(String city1, String city2) {
    CityInfo cityInfo1 = weatherRepo.getByCity(city1);
    CityInfo cityInfo2 = weatherRepo.getByCity(city2);



    boolean isRainingInCity1 = cityInfo1.getCurrentConditions().getConditions().toLowerCase().contains("rain");
    boolean isRainingInCity2 = cityInfo2.getCurrentConditions().getConditions().toLowerCase().contains("rain");

    if (isRainingInCity1 && isRainingInCity2) {
      return "It's raining in both cities";
    } else if (isRainingInCity1) {
      return city1;
    } else if (isRainingInCity2) {
      return city2;
    } else {
      return "It's not raining in either city";
    }
  }
}
