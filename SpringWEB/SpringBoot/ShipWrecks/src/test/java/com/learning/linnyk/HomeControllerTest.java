package com.learning.linnyk;

import com.learning.linnyk.controllers.HomeController;
import org.junit.Assert;
import org.junit.Test;

public class HomeControllerTest {

    @Test
    public void test() {
        final HomeController homeController = new HomeController();
        final String actual = homeController.home();

        Assert.assertEquals("Das Boot", actual);
    }
}
