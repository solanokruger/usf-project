package uol.compass.project.usf.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.project.usf.dto.request.TeamRequestDTO;
import uol.compass.project.usf.dto.response.TeamResponseDTO;
import uol.compass.project.usf.entities.TeamEntity;
import uol.compass.project.usf.repositories.TeamRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @InjectMocks
    TeamService teamService;
    @Mock
    TeamRepository teamRepository;
    @Spy
    ModelMapper modelMapper;

    private final long ID = 1l;

    @Test
    public void shouldCreateTeamTest_success(){
        TeamEntity team = new TeamEntity();
        TeamResponseDTO responseDTO = new TeamResponseDTO();
        TeamRequestDTO teamRequestDTO = new TeamRequestDTO();

        Mockito.when(modelMapper.map(any(), eq(TeamEntity.class))).thenReturn(team);
        Mockito.when(teamRepository.save(any())).thenReturn(team);
        Mockito.when(modelMapper.map(any(), eq(TeamResponseDTO.class))).thenReturn(responseDTO);

        TeamResponseDTO teamResponseDTO = teamService.createTeam(teamRequestDTO);

        assertEquals(responseDTO, teamResponseDTO);
        verify(teamRepository).save(any());
    }

    @Test
    public void shouldFindAllTeamsTest_success(){
        TeamEntity team = new TeamEntity(ID, "blue");

        Mockito.when(teamRepository.findAll()).thenReturn(List.of(team));

        List<TeamResponseDTO> response = teamService.getTeams();

        assertNotNull(response);
        assertEquals(response.get(0), team);
    }

    @Test
    public void shouldFindTeamByIdTest_success(){
        TeamEntity team = new TeamEntity(ID, "blue");

        Mockito.when(teamRepository.findById(ID)).thenReturn(Optional.of(team));

        TeamResponseDTO response = teamService.getTeamById(ID);

        assertNotNull(response);
        assertEquals(response.getId(), ID);
        assertEquals(response.getColor(), "blue");
    }

    @Test
    public void shouldUpdateTeam_success(){
        TeamEntity team = new TeamEntity(ID, "blue");
        TeamResponseDTO response = new TeamResponseDTO(ID, "blue");
        TeamRequestDTO request = new TeamRequestDTO();
        request.setColor("black");

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        Mockito.when(teamRepository.save(any())).thenReturn(team);

        TeamResponseDTO teamResponseDTO = teamService.update(ID, request);

        assertEquals(response.getId(), teamResponseDTO.getId());
        verify(teamRepository, times(1)).save(any());
    }

}
