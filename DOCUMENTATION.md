
# Weather App - Documentation

## Overview

This application uses the Spring Framework and Visual Crossing's Weather API to provide users with weather-based features. The features introduced include:

1. **Daylight Hours Comparison:** Given two city names, the application compares the length of the daylight hours between the two and returns the city with the longest day.

2. **Rain Check:** Given two city names, the application checks which city it is currently raining in.

## Implementation Details


The WeatherController class handles incoming HTTP requests to the application. It provides endpoints for each of the implemented features whereas the business logic is written in the WeatherService Class. The VisualCrossingRepository is used to fetch data from the Visual Crossing Weather API.



**Daylight Hours Comparison:**

To calculate the city with the most Daylight hours, I created a private method which called the existing forecastByCity method.

I then used get methods to access the date of the sunrise and sunset in LocalTime class. Using Java's built in Duration class I calculated the time between sunrise and sunset for each city. 

I then returned the city which had the longer daylight hours which was sent with a response status of Ok


**Rain Check:**

To determine which has rainfall, I checked whether the conditions property contained the word "rain", indicating that the city is currently raining and stored the value in a boolean. 

I then used an If-else statement to return when either none,one or both cities currently have rainy conditions.


## How To Use

To compare the daylight hours of two cities, make a GET request to `/compare?city1={firstCity}&city2={secondCity}/`, replacing `{firstCity}` and `{secondCity}` with the names of the cities you want to compare.


## Request Paths:

This table shows all possible requests as well as an example of their path and parameters

| HTTP Request Paths | Request Type | Parameters|Description |
|:---:|:---:|:---:|:---:|
| .../forecast/{city} | GET | n/a| returns the current forecast of specified {city} |
| .../compare?city1=London&city2=Manchester | GET |city1,city2 |returns the city with the longer daylight hours |
| .../checkRain?city1=London&city2=Manchester | GET | city1,city2| returns one or both cities if they are forecasted for rain|
