package com.ming.web.controller;

import com.ming.web.domain.Spittle;
import com.ming.web.config.RootConfig;
import com.ming.web.config.WebConfig;
import com.ming.web.controller.SpittleController;
import com.ming.web.dao.SpittleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, RootConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpittleControllerTest {

    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        // Set up a mock MVC tester based on the web application context
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void shouldShowRecentSpittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(20);
        SpittleRepository mockRespository = mock(SpittleRepository.class);
        when(mockRespository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRespository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/view/spittles.jsp"))
                .build();
        mockMvc.perform(get("/spittles"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
    }

    @Test
    public void shouldShowPageSpittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(20);
        SpittleRepository mockRespository = mock(SpittleRepository.class);
        when(mockRespository.findSpittles(20, 20)).thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRespository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/view/spittles.jsp"))
                .build();
        mockMvc.perform(get("/spittles?max=20&count=20"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("spittleList"))
                .andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
    }

    @Test
    public void showSpittle() throws Exception {
        Spittle expectedSpittle = new Spittle(1L, "spittle1", new Date());
        SpittleRepository mockRespository = mock(SpittleRepository.class);
        when(mockRespository.findOne(1)).thenReturn(expectedSpittle);

        SpittleController controller = new SpittleController(mockRespository);
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/spittles/1"))
                .andExpect(view().name("spittle"))
                .andExpect(model().attributeExists("spittle"))
                .andExpect(model().attribute("spittle", expectedSpittle));
    }

    @Test
    public void shouleShowRegistrationForm() throws Exception {
        SpittleController controller = new SpittleController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/spittles/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void showProcessRegistration() throws Exception {
        Spittle unsave = new Spittle("he", "ming", "heming", "123456");
        Spittle save = new Spittle("he", "ming", "heming", "123456");
        SpittleRepository spittleRepository = mock(SpittleRepository.class);
        when(spittleRepository.save(unsave)).thenReturn(save);

        //SpittleController controller = new SpittleController(spittleRepository);
        //MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/spittles/register")
                //.param("firstName", "")
                //.param("lastName", "ming")
                //.param("username", "heming")
                .param("password", "123456"))
                .andExpect(redirectedUrl("/spittles/heming"));

        //verify(spittleRepository, atLeastOnce()).save(unsave);
    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle(Long.valueOf(i), "Spitter" + i, new Date()));
        }
        return spittles;
    }
}
