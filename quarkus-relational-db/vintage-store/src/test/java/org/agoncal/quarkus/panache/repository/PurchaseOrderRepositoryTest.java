package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.junit.QuarkusTest;

import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Language;
import org.agoncal.quarkus.panache.model.Publisher;
import org.junit.jupiter.api.Test;
import io.quarkus.test.TestTransaction;

import java.sql.SQLException;

@QuarkusTest
class PurchaseOrderRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndfindAPurchaseOrder() throws SQLException {

		//Creates an Artist
		Artist artist = new Artist("artist name", "artist bio");

		//Creates a Publisher
		Publisher publisher = new Publisher("publisher name");

		//Creates a Book
		Book book = new Book();
		book.title = "title of the book";
		book.nbOfPages = 500;
		book.language = Language.ENGLISH;
		//Sets the relationships
		book.publisher = publisher;
		book.artist = artist;
		//Persists the book
		Book.persist(book);
    }

}
