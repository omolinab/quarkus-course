package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import org.agoncal.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;

import java.sql.SQLException;

@QuarkusTest
class ArtistRepositoryTest {

	@Inject
	ArtistRepository repository;

    @Test
	@TestTransaction
    void shouldCreateAndfindAnArtist() throws SQLException {
	    Artist artist = new Artist("name","bio");

	    repository.persist(artist);
	    assertNotNull(artist.getId());

	    artist = repository.findById(artist.getId());
	    assertEquals("name", artist.getName());
    }

}
