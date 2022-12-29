package uol.compass.project.usf.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uol.compass.project.usf.model.dto.request.InventoryRequestDTO;
import uol.compass.project.usf.model.dto.response.InventoryResponseDTO;
import uol.compass.project.usf.model.dto.response.InventoryResponseParameters;
import uol.compass.project.usf.services.InventoryServiceImpl;
import uol.compass.project.usf.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = InventoryController.class)
public class InventoryControllerTest {
    public static final String BASE_URL = "/inventory";

    public static final String ID_URL = BASE_URL + "/1";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InventoryServiceImpl inventoryService;

    @Test
    void create() throws Exception {
        InventoryRequestDTO request = getInventoryRequestDTO();
        InventoryResponseDTO responseDTO = new InventoryResponseDTO();

        when(inventoryService.create(any())).thenReturn(responseDTO);

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
        InventoryResponseParameters inventoryResponseParameters = new InventoryResponseParameters();

        Mockito.when(inventoryService.findAll(any())).thenReturn(inventoryResponseParameters);

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
        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();

        when(inventoryService.findById(any())).thenReturn(inventoryResponseDTO);

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
        InventoryRequestDTO request = getInventoryRequestDTO();
        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();

        when(inventoryService.update(any(), any())).thenReturn(inventoryResponseDTO);

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


    private InventoryRequestDTO getInventoryRequestDTO() {
        return InventoryRequestDTO.builder()
                .idUsf(1l)
                .idResource(1l)
                .amount(5)
                .build();
    }


}
