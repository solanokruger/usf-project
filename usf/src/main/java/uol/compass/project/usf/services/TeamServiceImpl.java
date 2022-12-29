package uol.compass.project.usf.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uol.compass.project.usf.model.dto.request.TeamRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.TeamResponseDTO;
import uol.compass.project.usf.model.entities.DoctorEntity;
import uol.compass.project.usf.model.entities.TeamEntity;
import uol.compass.project.usf.model.entities.UsfEntity;
import uol.compass.project.usf.repositories.TeamRepository;
import uol.compass.project.usf.exceptions.TeamNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamServiceImpl {

    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final UsfServiceImpl usfService;

    public TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO) {
        validateTeamColor(teamRequestDTO);
        TeamEntity teamEntity = modelMapper.map(teamRequestDTO, TeamEntity.class);
        TeamEntity createdTeam = teamRepository.save(teamEntity);
        return modelMapper.map(createdTeam, TeamResponseDTO.class);
    }

    public List<TeamResponseDTO> getTeams() {
        List<TeamEntity> allTeams = teamRepository.findAll();
        return modelMapper.map(allTeams, List.class);
    }

    public TeamResponseDTO getTeamById(Long id) {
        TeamEntity team = getTeamByIdVerication(id);
        return modelMapper.map(team, TeamResponseDTO.class);
    }

    public TeamEntity getTeamByIdVerication(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
    }

    public List<DoctorResponseDTO> getDoctorsInTeam(Long id) {
        TeamEntity team = getTeamByIdVerication(id);
        List<DoctorEntity> allDoctors = team.getDoctors();
        return modelMapper.map(allDoctors, List.class);
    }

    public TeamResponseDTO update(Long id, TeamRequestDTO teamRequestDTO) {
        getTeamByIdVerication(id);
        TeamEntity newTeam = modelMapper.map(teamRequestDTO, TeamEntity.class);
        newTeam.setId(id);
        teamRepository.save(newTeam);

        return modelMapper.map(newTeam, TeamResponseDTO.class);
    }

    public TeamResponseDTO attachTeamToUsf(Long idTeam, Long idUsf) {
        TeamEntity teamToAttach = getTeamByIdVerication(idTeam);
        UsfEntity usf = usfService.getUsfEntity(idUsf);

        teamToAttach.setCurrentUSF(usf);
        TeamEntity teamAttached = teamRepository.save(teamToAttach);

        return modelMapper.map(teamAttached, TeamResponseDTO.class);
    }

    public TeamResponseDTO disattachTeamFromUsf(Long idTeam, Long idUsf) {
        TeamEntity teamTodisattach = getTeamByIdVerication(idTeam);
        usfService.getUsfEntity(idUsf);

        teamTodisattach.setCurrentUSF(null);
        TeamEntity teamDisattached = teamRepository.save(teamTodisattach);

        return modelMapper.map(teamDisattached, TeamResponseDTO.class);
    }

    public void delete(Long id) {
        getTeamByIdVerication(id);
        teamRepository.deleteById(id);
    }

    public void validateTeamColor(TeamRequestDTO teamRequestDTO) {
        List<TeamEntity> all = teamRepository.findAll();
        List<String> colors = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            colors.add(all.get(i).getColor());
        }
        if (colors.contains(teamRequestDTO.getColor())) {
            throw new DataIntegrityViolationException("Cor já registrada");
        }
    }

}