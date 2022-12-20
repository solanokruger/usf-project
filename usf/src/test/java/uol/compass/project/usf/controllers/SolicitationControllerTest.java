package uol.compass.project.usf.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import uol.compass.project.usf.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.services.SolicitationServiceImpl;
import uol.compass.project.usf.utils.TestUtils;

@WebMvcTest(controllers = SolicitationController.class)
public class SolicitationControllerTest {
    
    public static final String BASE_URL = "/solicitation";
    public static final String ID_URL = BASE_URL + "/1";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SolicitationServiceImpl solicitationService;

    @Test
    void create() throws Exception {
        SolicitationRequestDTO request = getSolicitationRequestDTO();
        SolicitationResponseDTO response = new SolicitationResponseDTO();

        when(solicitationService.create(any())).thenReturn(response);

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

    private SolicitationRequestDTO getSolicitationRequestDTO() {
        return SolicitationRequestDTO.builder()
                .idResource(Long.valueOf(1))
                .idUsf(Long.valueOf(1))
                .necessaryAmount(Long.valueOf(1))
                .build();
    }

}
