package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HomePageControllerTest {
    @InjectMocks
    private HomepageController homepageController;

    @Test
    void testHomePage() {
        String viewName = homepageController.homePage();

        assertEquals("homePage", viewName);
    }
}
