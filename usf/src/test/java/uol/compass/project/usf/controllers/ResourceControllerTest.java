package uol.compass.project.usf.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import uol.compass.project.usf.model.dto.request.ResourceRequestDTO;
import uol.compass.project.usf.model.dto.response.ResourceResponseDTO;
import uol.compass.project.usf.model.dto.response.ResourceResponseParameters;
import uol.compass.project.usf.services.ResourceServiceImpl;
import uol.compass.project.usf.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ResourceController.class)
@ContextConfiguration(classes = ResourceController.class)
public class ResourceControllerTest {

    public static final String BASE_URL = "/resource";

    public static final String ID_URL = BASE_URL + "/1";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ResourceServiceImpl resourceService;

    @Test
    void create() throws Exception {
        ResourceRequestDTO request = getResourceRequestDTO();
        ResourceResponseDTO responseDTO = new ResourceResponseDTO();

        when(resourceService.createResource(any())).thenReturn(responseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void findAll() throws Exception {
        ResourceResponseParameters resourceResponseParameters = new ResourceResponseParameters();

        Mockito.when(resourceService.getAllResources(any(), any())).thenReturn(resourceResponseParameters);

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
        ResourceResponseDTO resourceResponseDTO = new ResourceResponseDTO();

        when(resourceService.getResourceById(any())).thenReturn(resourceResponseDTO);

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
        ResourceRequestDTO request = getResourceRequestDTO();
        ResourceResponseDTO resourceResponseDTO = new ResourceResponseDTO();

        when(resourceService.update(any(), any())).thenReturn(resourceResponseDTO);

        String input = uol.compass.project.usf.utils.TestUtils.mapToJson(request);

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


    private ResourceRequestDTO getResourceRequestDTO() {
        return ResourceRequestDTO.builder()
                .name("Recurso")
                .category("EQUIPAMENTO")
                .description("Recurso para USF")
                .build();
    }
}
