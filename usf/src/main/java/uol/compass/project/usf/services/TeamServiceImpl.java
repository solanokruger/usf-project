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
public class TeamServiceImpl implements TeamService{

    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final UsfServiceImpl usfService;

    public TeamResponseDTO create(TeamRequestDTO teamRequestDTO) {
        validateTeamColor(teamRequestDTO);
        TeamEntity teamEntity = modelMapper.map(teamRequestDTO, TeamEntity.class);
        TeamEntity createdTeam = teamRepository.save(teamEntity);
        return modelMapper.map(createdTeam, TeamResponseDTO.class);
    }

    public List<TeamResponseDTO> findAll() {
        List<TeamEntity> allTeams = teamRepository.findAll();
        return modelMapper.map(allTeams, List.class);
    }

    public TeamResponseDTO findById(Long id) {
        TeamEntity team = findTeamByIdVerication(id);
        return modelMapper.map(team, TeamResponseDTO.class);
    }

    public List<DoctorResponseDTO> findDoctorsInTeam(Long id) {
        TeamEntity team = findTeamByIdVerication(id);
        List<DoctorEntity> allDoctors = team.getDoctors();
        return modelMapper.map(allDoctors, List.class);
    }

    public TeamResponseDTO update(Long id, TeamRequestDTO teamRequestDTO) {
        findTeamByIdVerication(id);
        TeamEntity newTeam = modelMapper.map(teamRequestDTO, TeamEntity.class);
        newTeam.setId(id);
        teamRepository.save(newTeam);

        return modelMapper.map(newTeam, TeamResponseDTO.class);
    }

    public TeamEntity findTeamByIdVerication(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
    }

    public TeamResponseDTO attachTeamToUsf(Long idTeam, Long idUsf) {
        TeamEntity teamToAttach = findTeamByIdVerication(idTeam);
        UsfEntity usf = usfService.getUsfEntity(idUsf);

        teamToAttach.setCurrentUSF(usf);
        TeamEntity teamAttached = teamRepository.save(teamToAttach);

        return modelMapper.map(teamAttached, TeamResponseDTO.class);
    }

    public TeamResponseDTO disattachTeamFromUsf(Long idTeam, Long idUsf) {
        TeamEntity teamToDisattach = findTeamByIdVerication(idTeam);
        usfService.getUsfEntity(idUsf);

        teamToDisattach.setCurrentUSF(null);
        TeamEntity teamDisattached = teamRepository.save(teamToDisattach);

        return modelMapper.map(teamDisattached, TeamResponseDTO.class);
    }

    public void delete(Long id) {
        findTeamByIdVerication(id);
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