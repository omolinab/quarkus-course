package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

import org.agoncal.quarkus.jdbc.Artist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.inject.Inject;

@QuarkusTest
class ArtistRepositoryTest {

	@Inject
	ArtistRepository repository;

    @Test
	@TestTransaction
    void shouldCreateAndfindAnArtist() {

		long count = repository.count();
		int listAll = repository.listAll().size();
		assertEquals(count, listAll);

	    Artist artist = new Artist("name","bio");

	    repository.persist(artist);
	    assertNotNull(artist.getId());

		assertEquals(count + 1, repository.count());

	    artist = repository.findById(artist.getId());
	    assertEquals("name", artist.getName());

		repository.deleteById(artist.getId());

		assertEquals(count, repository.count());
	}

}
