package uol.compass.project.usf.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import uol.compass.project.usf.model.controllers.UsfController;
import uol.compass.project.usf.model.dto.request.UsfRequestDTO;
import uol.compass.project.usf.model.dto.response.UsfResponseDTO;
import uol.compass.project.usf.model.dto.response.UsfResponseParameters;
import uol.compass.project.usf.model.services.UsfServiceImpl;
import uol.compass.project.usf.utils.TestUtils;

@WebMvcTest(controllers = UsfController.class)
public class UsfControllerTest {
    
    public static final String BASE_URL = "/usf";
    public static final String ID_URL = BASE_URL + "/1";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsfServiceImpl usfService;

    @Test
    void create() throws Exception {
        UsfRequestDTO request = getUsfRequestDTO();
        UsfResponseDTO response = new UsfResponseDTO();

        when(usfService.create(any())).thenReturn(response);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(input)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse servletResponse = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), servletResponse.getStatus());
    }

    @Test
    void findAll() throws Exception {
        UsfResponseParameters usfResponseParameters = new UsfResponseParameters();

        when(usfService.findAll(any())).thenReturn(usfResponseParameters);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(BASE_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        UsfResponseDTO usfResponseDTO = new UsfResponseDTO();

        when(usfService.findById(any())).thenReturn(usfResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        UsfRequestDTO request = getUsfRequestDTO();
        UsfResponseDTO usfResponseDTO = new UsfResponseDTO();

        when(usfService.update(any(), any())).thenReturn(usfResponseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(input)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    private UsfRequestDTO getUsfRequestDTO() {
        return UsfRequestDTO.builder()
            .name("Test")
            .address("AddressTest")
            .build();
    }

}
