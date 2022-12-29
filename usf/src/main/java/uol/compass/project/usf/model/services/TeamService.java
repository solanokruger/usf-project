package uol.compass.project.usf.model.services;

import uol.compass.project.usf.model.dto.request.TeamRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.TeamResponseDTO;
import uol.compass.project.usf.model.entities.TeamEntity;

import java.util.List;

public interface TeamService {

    TeamResponseDTO create(TeamRequestDTO teamRequestDTO);

    List<TeamResponseDTO> findAll();

    TeamResponseDTO findById(Long id);

    TeamEntity findTeamByIdVerication(Long id);

    List<DoctorResponseDTO> findDoctorsInTeam(Long id);

    TeamResponseDTO update(Long id, TeamRequestDTO teamRequestDTO);

    TeamResponseDTO attachTeamToUsf(Long idTeam, Long idUsf);

    TeamResponseDTO disattachTeamFromUsf(Long idTeam, Long idUsf);

    void delete(Long id);
}
