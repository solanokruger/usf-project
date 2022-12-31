package uol.compass.project.usf.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
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

import uol.compass.project.usf.model.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseParameters;
import uol.compass.project.usf.services.DoctorServiceImpl;
import uol.compass.project.usf.utils.TestUtils;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = DoctorController.class)
@ContextConfiguration(classes = DoctorController.class)
public class DoctorControllerTest {
    
    public static final String BASE_URL = "/doctor";

    public static final String ID_URL = BASE_URL + "/1";

    public static final String ID_DOCTOR_TEAM_URL = ID_URL + "/team/1";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DoctorServiceImpl doctorService;

    @Test
    void create() throws Exception {
        DoctorRequestDTO request = getDoctorRequestDTO();
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        when(doctorService.create(any())).thenReturn(doctorResponseDTO);

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
        DoctorResponseParameters doctorResponseParameters = new DoctorResponseParameters();

        when(doctorService.findAll(any())).thenReturn(doctorResponseParameters);

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
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        when(doctorService.findById(any())).thenReturn(doctorResponseDTO);

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
        DoctorRequestDTO request = getDoctorRequestDTO();
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        when(doctorService.update(any(), any())).thenReturn(doctorResponseDTO);

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

    @Test
    void attachDoctorToTeam() throws Exception {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        when(doctorService.attachDoctorToTeam(any(), any())).thenReturn(doctorResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(ID_DOCTOR_TEAM_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void disattachDoctorFromTeam() throws Exception {
        DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();

        when(doctorService.disattachDoctorFromTeam(any(), any())).thenReturn(doctorResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_DOCTOR_TEAM_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private DoctorRequestDTO getDoctorRequestDTO() {
        return DoctorRequestDTO.builder()
                .name("Carlos")
                .specialization("oftalmologista")
                .build();
    }

}
