package com.ming.web;

import com.ming.domain.SpittleBuilder;
import com.ming.web.config.RootCofig;
import com.ming.web.config.WebConfig;
import com.ming.web.dao.SpittleRepository;
import com.ming.web.domain.Spittle;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.ming.domain.SpittleBuilder.*;
import static com.ming.web.aop.AppWideExceptionHandler.VIEW_NOT_FOUND_SPITTLE;
import static com.ming.web.controller.SpittleController.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, RootCofig.class})
@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(MockitoJUnitRunner.class)
public class SpittleControllerMockitoTest {

    @Mock
    private SpittleRepository spittleRepository;

    /*@InjectMocks
    private SpittleController controller = new SpittleController();*/

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void shouldShowRegistrationForm() throws Exception {
        mockMvc.perform(get(CONTROLLER_URL + REGISTER_CONTROLLER_URL))
                .andExpect(view().name(VIEW_REGISTER_FORM));
    }

    @Test
    public void showProcessRegistration() throws Exception {
        Spittle unSave = SpittleBuilder.aSpittle().build();
        Spittle save = SpittleBuilder.aSpittle().build();
        when(spittleRepository.save(unSave)).thenReturn(save);

        mockMvc.perform(post(CONTROLLER_URL + REGISTER_CONTROLLER_URL)
                .param("firstName", DEFAULT_FIRSTNAME)
                .param("lastName", DEFAULT_LASTNAME)
                .param("username", DEFAULT_USERNAME)
                .param("password", DEFAULT_PASSWORD ))
                .andExpect(redirectedUrl("/spittles/heming"));

        verify(spittleRepository, atLeastOnce()).save(unSave);
    }

    @Test
    public void showSpittle() throws Exception {
        Spittle spittle = SpittleBuilder.aSpittle().build();
        when(spittleRepository.findOne(20)).thenReturn(spittle);

        mockMvc.perform(get(CONTROLLER_URL + CONTROLLER_SEPARATOR + DEFAULT_ID))
                .andExpect(view().name(VIEW_SPITTLE));
    }

    @Test
    public void showSpittleOfException() throws Exception {
        when(spittleRepository.findOne(DEFAULT_ID)).thenReturn(null);

        mockMvc.perform(get(CONTROLLER_URL + CONTROLLER_SEPARATOR + DEFAULT_ID))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Spittle Not Found"));
    }

    @Test
    public void testHandleNotFoundSpittle() throws Exception {
        when(spittleRepository.findOne(DEFAULT_ID)).thenReturn(null);

        mockMvc.perform(get(CONTROLLER_URL + CONTROLLER_SEPARATOR + DEFAULT_ID))
                .andExpect(view().name(VIEW_NOT_FOUND_SPITTLE));
    }
}
