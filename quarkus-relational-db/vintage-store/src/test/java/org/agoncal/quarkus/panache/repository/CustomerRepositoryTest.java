package org.agoncal.quarkus.panache.repository;

import jakarta.inject.Inject;
import java.sql.SQLException;

import io.quarkus.test.junit.QuarkusTest;

import org.agoncal.quarkus.jpa.Customer;
import org.junit.jupiter.api.Test;
import io.quarkus.test.TestTransaction;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CustomerRepositoryTest {

	@Inject
	CustomerRepository repository;

    @Test
    @TestTransaction
    void shouldCreateAndfindACustomer() throws SQLException {
	    Customer customer = new Customer("first name","last name","email");

	    repository.persist(customer);
	    assertNotNull(customer.getId());

	    customer = repository.findById(customer.getId());
	    assertEquals("first name", customer.getFirstName());
    }


}
