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
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.entities.TeamEntity;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.repositories.TeamRepository;
import uol.compass.project.usf.repositories.UsfRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @InjectMocks
    TeamService teamService;
    @Mock
    TeamRepository teamRepository;
    @Mock
    UsfRepository usfRepository;
    @Spy
    ModelMapper modelMapper;

    private final long ID_TEAM = 1l;
    private final long ID_USF = 1l;

    @Test
    public void shouldCreateTeamTest_success(){
        TeamEntity team = new TeamEntity();
        TeamResponseDTO response = new TeamResponseDTO();
        TeamRequestDTO request = new TeamRequestDTO();

        Mockito.when(usfRepository.save(any())).thenReturn(team);

        TeamResponseDTO teamResponseDTO = teamService.createTeam(request);

        assertEquals(response.getId(), teamResponseDTO.getId());
        verify(usfRepository).save(any());
    }

    @Test
    public void shouldFindAllTeamsTest_success(){
        TeamEntity team = new TeamEntity(ID_TEAM, "blue");

        Mockito.when(teamRepository.findAll()).thenReturn(List.of(team));

        List<TeamResponseDTO> response = teamService.getTeams();

        assertNotNull(response);
        assertEquals(response.get(0), team);
    }

    @Test
    public void shouldFindTeamByIdTest_success(){
        TeamEntity team = new TeamEntity(ID_TEAM, "blue");

        Mockito.when(teamRepository.findById(ID_TEAM)).thenReturn(Optional.of(team));

        TeamResponseDTO response = teamService.getTeamById(ID_TEAM);

        assertNotNull(response);
        assertEquals(response.getId(), ID_TEAM);
        assertEquals(response.getColor(), "blue");
    }

    @Test
    public void shouldUpdateTeam_success(){
        TeamEntity team = new TeamEntity(ID_TEAM, "blue");
        TeamResponseDTO response = new TeamResponseDTO(ID_TEAM, "blue");
        TeamRequestDTO request = new TeamRequestDTO();
        request.setColor("black");

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        Mockito.when(teamRepository.save(any())).thenReturn(team);

        TeamResponseDTO teamResponseDTO = teamService.update(ID_TEAM, request);

        assertEquals(response.getId(), teamResponseDTO.getId());
        verify(teamRepository, times(1)).save(any());
    }

    @Test
    public void shouldSetUsfTeamTest() {
        TeamEntity team = new TeamEntity(ID_TEAM, "blue");
        UsfEntity usf = new UsfEntity(ID_USF, "USF_TESTE", ID_TEAM, "Rua X");
        UsfResponseDTO usfResponseDTO = new UsfResponseDTO();

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        Mockito.when(usfRepository.findById(any())).thenReturn(Optional.of(usf));
        Mockito.when(usfRepository.save(any())).thenReturn(usf);

        UsfResponseDTO usfEntity = teamService.setUsfTeam(team.getId(), usf.getId());

        assertNotNull(usfEntity);
        assertEquals(usf.getIdCurrentTeam(), usfEntity.getIdCurrentTeam());
    }

    @Test
    public void shouldDeleteUsfTeamTest() {
        TeamEntity team = new TeamEntity(ID_TEAM, "blue");
        UsfEntity usf = new UsfEntity(ID_USF, "USF_TESTE", null, "Rua X");
        UsfResponseDTO usfResponseDTO = new UsfResponseDTO();

        Mockito.when(teamRepository.findById(any())).thenReturn(Optional.of(team));
        Mockito.when(usfRepository.findById(any())).thenReturn(Optional.of(usf));
        Mockito.when(usfRepository.save(any())).thenReturn(usf);

        UsfResponseDTO usfEntity = teamService.deleteTeamFromUsf(team.getId(), usf.getId());

        assertNotNull(usfEntity);
        assertEquals(usf.getIdCurrentTeam(), usfEntity.getIdCurrentTeam());
    }


}
