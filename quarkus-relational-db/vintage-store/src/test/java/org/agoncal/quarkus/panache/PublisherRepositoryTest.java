package org.agoncal.quarkus.panache;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import io.quarkus.test.TestTransaction;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

@QuarkusTest
class PublisherRepositoryTest {

    @Test
    @TestTransaction
    public void shouldCreateAndfindAPublisher() throws SQLException {
	    Publisher publisher = new Publisher("name");

	    Publisher.persist(publisher);
	    assertNotNull(publisher.id);

	    publisher = Publisher.findById(publisher.id);
	    assertEquals("name",publisher.name);
    }

}
