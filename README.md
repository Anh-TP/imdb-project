# IMDB project:

This is a web application which allows users to view and search movies from the open IMDB dataset.
The programme imports the dataset from an open resource provided by IMDB and renders it in a web browser, enabling view and search functionality of the database.

## How to use:
- Download the dataset 'title.basics.tsv.gz' from https://datasets.imdbws.com/
- Save the dataset into the file directory: src/main/resources
- To run the programme from command line use: mvn spring-boot:run
- Open browser on http://localhost:8080/ to interact with the UI

## Programme:
- The programme is built in Java using the Spring framework, with Spring boot, web, database and thymeleaf dependencies.

### Version 1.0
- This version is a MVP which contains a simple UI and search functionality.
- The programme currently limits the dataset to 300000 movies
- Currently search is limited to searching by movie title only and returns the searched movie title to show it exists.

## The next development phase will implement enhanced features including:
- Increased data limits from the database
- Genre and release date search functionalities
- Links to the movie in IMDB
- Enhanced UI
