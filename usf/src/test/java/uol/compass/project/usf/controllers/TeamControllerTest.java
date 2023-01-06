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

import uol.compass.project.usf.model.dto.request.TeamRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.TeamResponseDTO;
import uol.compass.project.usf.services.TeamServiceImpl;
import uol.compass.project.usf.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = TeamController.class)
@ContextConfiguration(classes = TeamController.class)
public class TeamControllerTest {

    public static final String BASE_URL = "/team";

    public static final String ID_URL = BASE_URL + "/1";

    public static final String ID_TEAM_USF_URL = ID_URL + "/usf/1";

    public static final String ID_DOCTOR = ID_URL + "/doctor";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamServiceImpl teamService;

    @Test
    void create() throws Exception {
        TeamRequestDTO request = getTeamRequestDTO();
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();

        when(teamService.create(any())).thenReturn(teamResponseDTO);

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
    void linkTeamToUsf() throws Exception {
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();

        when(teamService.attachTeamToUsf(any(), any())).thenReturn(teamResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(ID_TEAM_USF_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findAll() throws Exception {
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();

        Mockito.when(teamService.findAll()).thenReturn(List.of(teamResponseDTO));

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
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();

        when(teamService.findById(any())).thenReturn(teamResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findDoctorsById() throws Exception {
        List<DoctorResponseDTO> doctorResponseDTO = new ArrayList<>();

        when(teamService.findDoctorsInTeam(any())).thenReturn(doctorResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get(ID_DOCTOR)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        TeamRequestDTO request = getTeamRequestDTO();
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();

        when(teamService.update(any(), any())).thenReturn(teamResponseDTO);

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
    void deleteTeamFromUsf() throws Exception {
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO();

        when(teamService.disattachTeamFromUsf(any(), any())).thenReturn(teamResponseDTO);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.delete(ID_TEAM_USF_URL)
                        .accept(MediaType.APPLICATION_JSON)
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

    private TeamRequestDTO getTeamRequestDTO() {
        return TeamRequestDTO.builder()
                .color("Blue")
                .build();
    }

}
