package org.gs;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MovieResourceTest {

    @Test
    public void getAll_success() {
        given()
          .when().get("/movies")
          .then()
             .statusCode(200);
    }

    @Test
    public void getById_success(){
        Movie movie = given().when().get("/movies/1").then().statusCode(200).extract().as(Movie.class);
        Assertions.assertNotNull(movie);
        Assertions.assertEquals(Long.valueOf(1), movie.getId());
    }

    @Test
    public void getById_fail(){
        given().when().get("/movies/10000000").then().statusCode(404);
    }
/*
    @Test
    public void getByTitle_success(){
        Movie movie = given().when().get("/movies/title/Cherry").then().statusCode(200).extract().as(Movie.class);
        Assertions.assertNotNull(movie);
        Assertions.assertEquals(Long.valueOf(1), movie.getId());
        Assertions.assertEquals("Cherry", movie.getTitle());
    }
    @Test
    public void getByTitle_fail(){
        given().when().get("/movies/title/Apple").then().statusCode(404);
    }

    @Test
    public void getByCountry_success(){
        Movie[] movieList = given().when().get("/movies/country/NL").then().statusCode(200).extract().as(Movie[].class);
        Assertions.assertNotNull(movieList);
        Assertions.assertEquals(1, movieList.length);

        Movie movie = movieList[0];
        Assertions.assertEquals(Long.valueOf(1), movie.getId());
        Assertions.assertEquals("NL", movie.getCountry());
    }
    @Test
    public void getByCountry_fail(){
//        String uuidCountry = UUID.randomUUID().toString();
*//*        Movie movie = new Movie();
        movie.setTitle("Titanic");
        movie.setDescription("Description");
        movie.setCountry("NL");*//*

*//*        given()
                .pathParam("country", uuidCountry)
                .when().get("/movies/country/{country}")
                .then()
                        .statusCode(204);
        //I did it with Malcolm*//*
        given().when().get("/movies/country/AAAAA").then().statusCode(204);
    }

    @Test
    public void update_conflict(){
        Movie movie = new Movie();
        movie.setId(4544L);

        given().contentType(ContentType.JSON).body(movie).put("/movies/1").then().statusCode(409);
    }

    @Test
    public void update_notfound(){
        given().when().put("/movies/4544").then().statusCode(404);
    }

    @Test
    //@Disabled
    public void insert_update_success(){
        Movie movie = new Movie();
        movie.setTitle("Titanic");
        movie.setDescription("Description");
        movie.setCountry("NL");

        // Test insert
        Movie entity = given().contentType(ContentType.JSON).body(movie).post("/movies").then().statusCode(201).extract().as(Movie.class);
        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertEquals(movie.getTitle(), entity.getTitle());
        Assertions.assertEquals(movie.getDescription(), entity.getDescription());
        Assertions.assertEquals(movie.getCountry(), entity.getCountry());

        // Test update
        entity.setDescription("Updated description");
        Movie updatedEntity = given().contentType(ContentType.JSON).body(entity).put("/movies/"+entity.getId()).then().statusCode(200).extract().as(Movie.class);

        Assertions.assertNotNull(updatedEntity);
        Assertions.assertEquals(entity.getId(), updatedEntity.getId());
        Assertions.assertEquals(entity.getTitle(), updatedEntity.getTitle());
        Assertions.assertEquals(entity.getDescription(), updatedEntity.getDescription());
        Assertions.assertEquals(entity.getCountry(), updatedEntity.getCountry());
    }*/

}