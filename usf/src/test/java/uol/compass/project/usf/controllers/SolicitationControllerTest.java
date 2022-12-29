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

import uol.compass.project.usf.model.controllers.SolicitationController;
import uol.compass.project.usf.model.dto.request.SolicitationRequestDTO;
import uol.compass.project.usf.model.dto.request.SolicitationUpdateRequestDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseDTO;
import uol.compass.project.usf.model.dto.response.SolicitationResponseParameters;
import uol.compass.project.usf.model.services.SolicitationServiceImpl;
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

    @Test
    void findAll() throws Exception {
        SolicitationResponseParameters solicitationResponseParameters = new SolicitationResponseParameters();

        when(solicitationService.findAll(any())).thenReturn(solicitationResponseParameters);

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
        SolicitationResponseDTO solicitationResponseDTO = new SolicitationResponseDTO();

        when(solicitationService.findById(any())).thenReturn(solicitationResponseDTO);

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
        SolicitationUpdateRequestDTO request = getSolicitationUpdateRequestDTO();
        SolicitationResponseDTO solicitationResponseDTO = new SolicitationResponseDTO();

        when(solicitationService.update(any(), any())).thenReturn(solicitationResponseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.patch(ID_URL)
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

    private SolicitationUpdateRequestDTO getSolicitationUpdateRequestDTO() {
        return SolicitationUpdateRequestDTO.builder()
                .necessaryAmount(Long.valueOf(1))
                .build();
    }

    private SolicitationRequestDTO getSolicitationRequestDTO() {
        return SolicitationRequestDTO.builder()
                .idResource(Long.valueOf(1))
                .idUsf(Long.valueOf(1))
                .necessaryAmount(Long.valueOf(1))
                .build();
    }

}
