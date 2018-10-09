package com.learning.linnyk;

import com.learning.linnyk.controllers.ShipwreckControllerV2;
import com.learning.linnyk.model.Shipwreck;
import com.learning.linnyk.repository.ShipwreckRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class ShipwreckControllerTest {

    @Mock
    private ShipwreckRepository shipwreckRepository;

    @InjectMocks
    private ShipwreckControllerV2 shipwreckController;

    @Before
    public void setUp(){
        final Shipwreck shipwreck = new Shipwreck();
        shipwreck.setId(1L);

        Mockito.when(shipwreckRepository.findOne(anyLong())).thenReturn(shipwreck);
    }

    @Test
    public void testShipwreckGet() {
        final Shipwreck shipwreck = shipwreckController.get(1L);

        Assert.assertEquals(1L, shipwreck.getId().longValue());
        Assert.assertThat(shipwreck.getId(), is(1L));

        Mockito.verify(shipwreckRepository).findOne(1L);
    }
}
