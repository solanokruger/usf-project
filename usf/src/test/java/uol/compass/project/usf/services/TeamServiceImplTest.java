package uol.compass.project.usf.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.project.usf.model.dto.request.TeamRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.TeamResponseDTO;
import uol.compass.project.usf.model.entities.DoctorEntity;
import uol.compass.project.usf.model.entities.TeamEntity;
import uol.compass.project.usf.model.entities.UsfEntity;
import uol.compass.project.usf.repositories.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceImplTest {

    public static final Long ID = 1L;

    @InjectMocks
    TeamServiceImpl teamService;
    @Mock
    TeamRepository teamRepository;
    @Mock
    UsfServiceImpl usfService;
    @Spy
    ModelMapper modelMapper;

    @Test
    public void shouldCreateTeamTest_success() {
        TeamEntity team = new TeamEntity();
        TeamRequestDTO teamRequestDTO = new TeamRequestDTO();
        TeamResponseDTO responseDTO = new TeamResponseDTO();

        Mockito.when(teamRepository.save(any())).thenReturn(team);

        TeamResponseDTO teamResponseDTO = teamService.create(teamRequestDTO);

        assertEquals(teamResponseDTO.getId(), responseDTO.getId());

        verify(teamRepository).save(any());
    }

    @Test
    public void shouldFindAllTeamsTest_success() {
        TeamEntity team = new TeamEntity();

        Mockito.when(teamRepository.findAll()).thenReturn(List.of(team));

        List<TeamResponseDTO> response = teamService.findAll();

        assertEquals(response.get(0), team);
    }

    @Test
    public void shouldFindTeamByIdTest_success() {
        TeamEntity team = new TeamEntity();
        TeamResponseDTO responseDTO = new TeamResponseDTO();

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));

        TeamResponseDTO response = teamService.findById(ID);

        assertEquals(response, responseDTO);
    }

    @Test
    public void shouldFindDoctorsByTeam_success() {
        TeamEntity team = new TeamEntity(1L, "blue", null, new ArrayList<>());
        
        team = setDoctorsToTeam(team);

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));

        List<DoctorResponseDTO> response = teamService.findDoctorsInTeam(ID);

        assertEquals(ID, response.get(0).getId());
        assertTrue(response instanceof List);
        assertEquals(DoctorResponseDTO.class, response.get(0).getClass());
        assertNotNull(response);
        assertEquals(2, response.size());
    }

    private TeamEntity setDoctorsToTeam(TeamEntity team) {
        DoctorEntity doctor1 = new DoctorEntity(1L, "João", "Oftalmologista", null);
        DoctorEntity doctor2 = new DoctorEntity(2L, "Alberto", "Cardiologista", null);

        team.getDoctors().add(doctor1);
        team.getDoctors().add(doctor2);

        return team;
    }

    @Test
    public void shouldUpdateTeam_success() {
        TeamEntity team = new TeamEntity();
        TeamResponseDTO response = new TeamResponseDTO();
        TeamRequestDTO request = new TeamRequestDTO();

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        Mockito.when(teamRepository.save(any())).thenReturn(team);

        response.setId(ID);

        TeamResponseDTO teamResponseDTO = teamService.update(ID, request);

        assertEquals(teamResponseDTO, response);
    }

    @Test
    public void shouldSetUsfTeamTest() {
        TeamEntity team = new TeamEntity();
        UsfEntity usf = new UsfEntity();
        TeamResponseDTO response = new TeamResponseDTO();

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        Mockito.when(usfService.getUsfEntity(any())).thenReturn(usf);

        response.setCurrentUSF(usf);

        Mockito.when(teamRepository.save(any())).thenReturn(team);

        TeamResponseDTO responseDTO = teamService.attachTeamToUsf(ID, ID);

        assertEquals(responseDTO, response);
    }

    @Test
    public void shouldDeleteUsfTeamTest() {
        TeamEntity team = new TeamEntity();
        UsfEntity usf = new UsfEntity();
        TeamResponseDTO response = new TeamResponseDTO();

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        Mockito.when(usfService.getUsfEntity(any())).thenReturn(usf);
        Mockito.when(teamRepository.save(any())).thenReturn(team);

        TeamResponseDTO responseDTO = teamService.disattachTeamFromUsf(ID, ID);

        assertEquals(responseDTO, response);
    }

    @Test
    void shouldDeleteTeam_sucess() {
        TeamEntity team = new TeamEntity();

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));

        teamService.delete(ID);

        verify(teamRepository).deleteById(any());
    }

}
