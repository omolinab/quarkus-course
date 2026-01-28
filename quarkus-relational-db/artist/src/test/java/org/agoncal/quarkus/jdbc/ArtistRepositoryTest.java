package org.agoncal.quarkus.jdbc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;

import java.sql.SQLException;

@QuarkusTest
class ArtistRepositoryTest {

	@Inject
	ArtistRepository repository;

    @Test
    void shouldCreateAndfindAnArtist() throws SQLException {
	    Artist artist = new Artist("name","bio");

	    repository.persist(artist);
	    assertNotNull(artist.getId());

	    artist = repository.findById(artist.getId());
	    assertEquals("name", artist.getName());
    }

}
