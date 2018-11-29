package com.demo.musicapp.musicApp;

import java.util.Arrays;

import com.demo.musicapp.musicApp.dao.TagRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(MusicAppRestController.class)

public class MusicAppRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagRepo tagRepo;

    @MockBean
    private MusicAppRestController restController;

    @Test
    public void getTrieOccTest() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/trieOcc/t").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"name\":\"Soft Rock\"},{\"name\":\"Alternative\"},{\"name\":\"Electronic\"}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


    @Test
    public void getTrieOccTestB() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/trieOcc/s").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"name\":\"Blues\"},{\"name\":\"Classical\"},{\"name\":\"Progressive\"}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

}
