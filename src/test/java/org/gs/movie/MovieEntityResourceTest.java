package org.gs.movie;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MovieEntityResourceTest {

    @Test
    @Disabled
    public void getAll_success() {
        given()
          .when().get("/movies")
          .then()
             .statusCode(200);
    }

    @Test
    public void getById_success(){
        MovieEntity movieEntity = given().when().get("/movies/1").then().statusCode(200).extract().as(MovieEntity.class);
        Assertions.assertNotNull(movieEntity);
        Assertions.assertNotNull(movieEntity.getDirector());
        Assertions.assertEquals(Long.valueOf(1), movieEntity.getId());
    }

    @Test
    @Disabled
    public void getById_fail(){
        given().when().get("/movies/10000000").then().statusCode(404);
    }
    @Test
    @Disabled
    public void getByTitle_success(){
        MovieEntity movieEntity = given().when().get("/movies/title/Cherry").then().statusCode(200).extract().as(MovieEntity.class);
        Assertions.assertNotNull(movieEntity);
        Assertions.assertEquals(Long.valueOf(1), movieEntity.getId());
        Assertions.assertEquals("Cherry", movieEntity.getTitle());
    }
    @Test
    @Disabled
    public void getByTitle_fail(){
        given().when().get("/movies/title/Apple").then().statusCode(404);
    }

    @Test
    @Disabled
    public void getByCountry_success(){
        MovieEntity[] movieEntityList = given().when().get("/movies/country/NL").then().statusCode(200).extract().as(MovieEntity[].class);
        Assertions.assertNotNull(movieEntityList);
        Assertions.assertEquals(1, movieEntityList.length);

        MovieEntity movieEntity = movieEntityList[0];
        Assertions.assertEquals(Long.valueOf(1), movieEntity.getId());
        Assertions.assertEquals("NL", movieEntity.getCountry());
    }
    @Test
    @Disabled
    public void getByCountry_fail(){
        given().when().get("/movies/country/AAAAA").then().statusCode(204);
    }

    @Test
    @Disabled
    public void update_conflict(){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(4544L);

        given().contentType(ContentType.JSON).body(movieEntity).put("/movies/1").then().statusCode(409);
    }

    @Test
    @Disabled
    public void update_notfound(){
        given().when().put("/movies/4544").then().statusCode(404);
    }

    @Test
    @Disabled
    //@Disabled
    public void insert_update_success(){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle("Titanic");
        movieEntity.setDescription("Description");
        movieEntity.setCountry("NL");

        // Test insert
        MovieEntity entity = given().contentType(ContentType.JSON).body(movieEntity).post("/movies").then().statusCode(201).extract().as(MovieEntity.class);
        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertEquals(movieEntity.getTitle(), entity.getTitle());
        Assertions.assertEquals(movieEntity.getDescription(), entity.getDescription());
        Assertions.assertEquals(movieEntity.getCountry(), entity.getCountry());

        // Test update
        entity.setDescription("Updated description");
        MovieEntity updatedEntity = given().contentType(ContentType.JSON).body(entity).put("/movies/"+entity.getId()).then().statusCode(200).extract().as(MovieEntity.class);

        Assertions.assertNotNull(updatedEntity);
        Assertions.assertEquals(entity.getId(), updatedEntity.getId());
        Assertions.assertEquals(entity.getTitle(), updatedEntity.getTitle());
        Assertions.assertEquals(entity.getDescription(), updatedEntity.getDescription());
        Assertions.assertEquals(entity.getCountry(), updatedEntity.getCountry());
    }

}