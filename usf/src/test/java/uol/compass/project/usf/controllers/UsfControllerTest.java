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

import com.fasterxml.jackson.core.JsonProcessingException;

import uol.compass.project.usf.dto.request.UsfRequestDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.services.UsfServiceImpl;
import uol.compass.project.utils.TestUtils;

@WebMvcTest(controllers = UsfController.class)
public class UsfControllerTest {
    
    public static final String BASE_URL = "/usf";

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

    private UsfRequestDTO getUsfRequestDTO() {
        return UsfRequestDTO.builder()
            .name("Test")
            .idCurrentTeam(Long.valueOf(1))
            .address("AddressTest")
            .build();
    }

}
