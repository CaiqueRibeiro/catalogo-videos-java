package com.fullcycle.FCCatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VideoTests {

  @Test
  public void throwIllegalArgumentExceptionWhenTitleIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new Video(null, "", 0, false));
  } 

  @Test
  public void throwIllegalArgumentExceptionWhenTitleIsBlank() {
    assertThrows(IllegalArgumentException.class, () -> new Video("", "", 0, false));
  } 

  @Test
  public void throwIllegalArgumentExceptionWhenYearLaunchedIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new Video("Title 1", "", null, false));
  }

  @Test
  public void throwIllegalArgumentExceptionWhenYearLaunchedIsGreaterThanToday() {
    assertThrows(IllegalArgumentException.class, () -> new Video("Title 1", "", 2030, false));
  }

  @Test
  public void throwIllegalArgumentExceptionWhenYearLaunchedIsLessThan1700() {
    assertThrows(IllegalArgumentException.class, () -> new Video("Title 1", "", 1659, false));
  }

  @Test
  public void createVideoWithTitleAndYearLanchedTest() {
    final Video entity = new Video("Video 1", "Description random", 2020, false);

    assertNotNull(entity);
    assertEquals(entity.getTitle(), "Video 1");
    assertEquals(entity.getDescription(), "Description random");
    assertEquals(entity.getYearLaunched(), 2020);
    assertFalse(entity.getOpened());
    assertTrue(entity.isValidUUID(entity.getId().toString()));
  }

  @Test
  public void createVideoWithTitleAndYearLanchedAndRatingAndDurationTest() {
    final Video entity = new Video("Video 1", "Description random", 2020, false, "Rating 10", (Float) 13.0f);

    assertNotNull(entity);
    assertEquals(entity.getTitle(), "Video 1");
    assertEquals(entity.getDescription(), "Description random");
    assertEquals(entity.getYearLaunched(), 2020);
    assertFalse(entity.getOpened());
    assertTrue(entity.isValidUUID(entity.getId().toString()));
  }

  @Test
  public void createVideoWithoutFilesTest() {
    final Category category1 = new Category("Category 1");
    final Category category2 = new Category("Category 2");

    final Genre genre1 = new Genre("Genre 1");
    final Genre genre2 = new Genre("Genre 2");

    final CastMember castMember1 = new CastMember("John Doe");
    final CastMember castMember2 = new CastMember("Mary Jane");

    List<Category> categories = new ArrayList<Category>();
    categories.add(category1);
    categories.add(category2);

    List<Genre> genres = new ArrayList<Genre>();
    genres.add(genre1);
    genres.add(genre2);

    List<CastMember> castMembers = new ArrayList<CastMember>();
    castMembers.add(castMember1);
    castMembers.add(castMember2);

    assertNotNull(categories);
    assertNotNull(genres);
    assertNotNull(castMembers);

    Video entity = new Video();
    assertNotNull(entity);

    Video videoCreated = entity.createVideoWithoutFile("Video 1", "Description random", 2019, (Float) 10.0f, categories, genres, castMembers);

    assertNotNull(videoCreated);
    assertEquals(videoCreated.getTitle(), "Video 1");
    assertEquals(videoCreated.getYearLaunched(), 2019);
    assertNotNull(videoCreated.getCategories());
    assertNotNull(videoCreated.getGenres());
    assertNotNull(videoCreated.getCastMembers());
    assertTrue(videoCreated.isValidUUID(videoCreated.getId().toString()));
  }

  @Test
  public void createVideoWithoutFilesWithCategoriesTest() {
    final Category category1 = new Category("Category 1");
    final Category category2 = new Category("Category 2");

    assertNotNull(category1);
    assertNotNull(category2);

    List<Category> categories = new ArrayList<Category>();
    categories.add(category1);
    categories.add(category2);

    final Video entity = new Video("Video 1", "Description random", 2020, false, "Rating 10", (Float) 13.0f);

    entity.setCategories(categories);
    
    assertNotNull(categories);
    assertNotNull(entity);
    assertEquals(entity.getTitle(), "Video 1");
    assertTrue(entity.isValidUUID(entity.getId().toString()));
    assertEquals(category1.getName(), "Category 1");
    assertEquals(category2.getName(), "Category 2");
    assertTrue(category1.isValidUUID(category1.getId().toString()));
    assertTrue(category2.isValidUUID(category2.getId().toString()));
    assertEquals(2, entity.getCategories().size());
  }

  @Test
  public void createVideoWithoutFilesWithAddCategoriesTest() {
    final Category category1 = new Category("Category 1");
    final Category category2 = new Category("Category 2");

    assertNotNull(category1);
    assertNotNull(category2);

    final Video entity = new Video("Video 1", "Description random", 2020, false, "Rating 10", (Float) 13.0f);

    entity.addCategory(category1);
    entity.addCategory(category2);
    
    assertNotNull(entity);
    assertEquals(entity.getTitle(), "Video 1");
    assertTrue(entity.isValidUUID(entity.getId().toString()));
    assertEquals(category1.getName(), "Category 1");
    assertEquals(category2.getName(), "Category 2");
    assertTrue(category1.isValidUUID(category1.getId().toString()));
    assertTrue(category2.isValidUUID(category2.getId().toString()));
    assertEquals(2, entity.getCategories().size());
  }

  @Test
  public void createVideoWithoutFilesWithAddGenresTest() {
    final Genre genre1 = new Genre("Genre 1");
    final Genre genre2 = new Genre("Genre 2");

    assertNotNull(genre1);
    assertNotNull(genre2);

    final Video entity = new Video("Video 1", "Description random", 2020, false, "Rating 10", (Float) 13.0f);

    entity.addGenre(genre1);
    entity.addGenre(genre2);
    
    assertNotNull(entity);
    assertEquals(entity.getTitle(), "Video 1");
    assertTrue(entity.isValidUUID(entity.getId().toString()));
    assertEquals(genre1.getName(), "Genre 1");
    assertEquals(genre2.getName(), "Genre 2");
    assertTrue(genre1.isValidUUID(genre1.getId().toString()));
    assertTrue(genre2.isValidUUID(genre2.getId().toString()));
    assertEquals(2, entity.getGenres().size());
  }

  @Test
  public void createVideoWithoutFilesWithAddCastMembersTest() {
    final CastMember castMember1 = new CastMember("CastMember 1");
    final CastMember castMember2 = new CastMember("CastMember 2");

    assertNotNull(castMember1);
    assertNotNull(castMember2);

    final Video entity = new Video("Video 1", "Description random", 2020, false, "Rating 10", (Float) 13.0f);

    entity.addCastMember(castMember1);
    entity.addCastMember(castMember2);
    
    assertNotNull(entity);
    assertEquals(entity.getTitle(), "Video 1");
    assertTrue(entity.isValidUUID(entity.getId().toString()));
    assertEquals(castMember1.getName(), "CastMember 1");
    assertEquals(castMember2.getName(), "CastMember 2");
    assertTrue(castMember1.isValidUUID(castMember1.getId().toString()));
    assertTrue(castMember2.isValidUUID(castMember2.getId().toString()));
    assertEquals(2, entity.getCastMembers().size());
  }
}
