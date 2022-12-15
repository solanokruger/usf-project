package uol.compass.project.usf.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import uol.compass.project.usf.dto.request.TeamRequestDTO;
import uol.compass.project.usf.dto.response.TeamResponseDTO;
import uol.compass.project.usf.entities.TeamEntity;
import uol.compass.project.usf.repositories.TeamRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @InjectMocks
    TeamService teamService;
    @Mock
    TeamRepository teamRepository;
    @Mock
    ModelMapper modelMapper;

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
        TeamEntity team = new TeamEntity(1l, "blue");
        TeamResponseDTO teamResponseDTO = new TeamResponseDTO(1l, "blue");

        Mockito.when(teamRepository.findAll()).thenReturn(List.of(team));
        Mockito.when(teamService.getTeams()).thenReturn(List.of(teamResponseDTO));

        List<TeamResponseDTO> response = teamService.getTeams();

        assertNotNull(teamResponseDTO);
        assertEquals(1, response.size());
        assertEquals(TeamResponseDTO.class, response.get(0).getClass());
        assertEquals("blue", response.get(0).getColor());
    }

}
