package org.agoncal.quarkus.panache.repository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.agoncal.quarkus.jdbc.Artist;
import org.agoncal.quarkus.jpa.Customer;
import org.agoncal.quarkus.panache.model.Book;
import org.agoncal.quarkus.panache.model.Language;
import org.agoncal.quarkus.panache.model.OrderLine;
import org.agoncal.quarkus.panache.model.Publisher;
import org.agoncal.quarkus.panache.model.PurchaseOrder;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;

import java.math.BigDecimal;
import java.sql.SQLException;

@QuarkusTest
class PurchaseOrderRepositoryTest {

	@Inject
	CustomerRepository customerRepository;

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
		book.price = new BigDecimal(10);
		book.isbn = "isbn";
		//Sets the relationships
		book.publisher = publisher;
		book.artist = artist;
		//Persists the book
		Book.persist(book);

		//Creates a Customer
		Customer customer = new Customer("customer first name", "customer last name", "customer email");
		customerRepository.persist(customer);

		//Creates and order line
		OrderLine orderLine = new OrderLine();
		orderLine.item = book;
		orderLine.quantity = 2;

		//Creates a purchase order
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.customer = customer;
		purchaseOrder.addOrderLine(orderLine);

		//Persists the purchase order
		PurchaseOrder.persist(purchaseOrder);
    }

}
