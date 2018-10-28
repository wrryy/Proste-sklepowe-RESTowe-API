package pl.wrydz.sklep.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.entity.User;
import pl.wrydz.sklep.repository.BasketRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

    @MockBean
    private BasketRepo basketRepo;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getBasket() throws Exception{
        User user = new User();
        user.setId(1L);
        Basket basket = new Basket();
        basket.setUser(user);
        basket.setBasketPrice(2.0);
        Mockito.when(basketRepo.findBasketByUser(1L)).thenReturn(basket);
        mockMvc.perform(get("/baskets").sessionAttr("userId", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.basketPrice", is(2.0)))
                .andExpect(jsonPath("$.user.id", is(1)));
    }

}