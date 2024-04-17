# World Temperature Analysis

The World Temperature Analysis project provides a framework for analyzing temperature data for various cities around the world. It includes components for fetching weather data from an external API, filtering cities based on population thresholds, aggregating temperature data, and identifying top cities by aggregated temperature.

## Features

- Fetch temperature data for cities from an external API.
- Filter cities based on population thresholds.
- Aggregate temperature data using various aggregation methods.
- Identify top cities by aggregated temperature.
- Utilize caching for improved performance.
- Asynchronously fetch temperature data to maximize efficiency.

## Prerequisites

Before running this project, ensure you have the following prerequisites installed:

- Java Development Kit (JDK) version 17
- Maven build tool

## Usage

To use the World Temperature Analysis project, follow these steps:

1. Configure the project properties by editing the `application.properties` file.
2. Run the 'Main' application class to start the analysis process. Comment/Uncomment desired modules for running.


## Configuration

You can customize the behavior of the application by editing the `application.properties` file. This file contains configuration properties such as population thresholds, number of top cities. The non-business configurations are hardcoded: thread pool size, cache size, and cache TTL.

## Using Cache

The application utilizes caching to improve performance by storing fetched temperature data for a certain period. When fetching temperature data for a city, the application first checks if the data is available in the cache. If found, it retrieves the data from the cache instead of making an API call, thus reducing latency and API usage.

## Asynchronous Fetching

Temperature data fetching is performed asynchronously to maximize efficiency and utilize system resources effectively. Asynchronous tasks are submitted to fetch temperature data from the WeatherAPI for each city. This allows the application to fetch data for multiple cities concurrently, speeding up the overall process.



