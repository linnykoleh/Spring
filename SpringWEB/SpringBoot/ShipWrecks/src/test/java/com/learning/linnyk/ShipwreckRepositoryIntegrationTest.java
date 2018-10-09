package com.learning.linnyk;

import com.learning.linnyk.model.Shipwreck;
import com.learning.linnyk.repository.ShipwreckRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class) // Similar to the standard @ContextConfiguration but uses Spring Boot's
public class ShipwreckRepositoryIntegrationTest {

    @Autowired
    private ShipwreckRepository shipwreckRepository;

    @Test
    public void testFindAll(){
        final List<Shipwreck> shipwrecks = shipwreckRepository.findAll();
        assertThat(shipwrecks.size(), is(greaterThanOrEqualTo(0)));
    }

}
