package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

  @Autowired
  WeatherService weatherService;

  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {

    CityInfo ci = weatherService.forecastByCity(city);

    return ResponseEntity.ok(ci);
  }

  @GetMapping("/compare")
  public ResponseEntity<String> dayLightHoursComparison(@RequestParam String city1, @RequestParam String city2) {
    String response = weatherService.compareDaylight(city1, city2);
    try {
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error comparing daylight hours.");
    }
  }

  @GetMapping("/checkRain")
  public ResponseEntity<String> checkRain(@RequestParam String city1, @RequestParam String city2) {
    try {
      String rainingCity = weatherService.checkForRain(city1, city2);
      return ResponseEntity.ok(rainingCity);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
  }


  // TODO: given two city names, compare the length of the daylight hours and return the city with the longest day

  // TODO: given two city names, check which city its currently raining in

}
