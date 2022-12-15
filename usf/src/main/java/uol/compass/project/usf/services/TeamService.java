package uol.compass.project.usf.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;
import uol.compass.project.usf.dto.request.TeamRequestDTO;
import uol.compass.project.usf.dto.response.TeamResponseDTO;
import uol.compass.project.usf.entities.TeamEntity;
import uol.compass.project.usf.repositories.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    public TeamResponseDTO createTeam(TeamRequestDTO teamRequestDTO){
        validateTeamColor(teamRequestDTO);

        TeamEntity teamEntity = modelMapper.map(teamRequestDTO, TeamEntity.class);
        TeamEntity createdTeam = teamRepository.save(teamEntity);
        return modelMapper.map(createdTeam, TeamResponseDTO.class);
    }

    public List<TeamResponseDTO> getTeams(){
        List<TeamEntity> allTeams = teamRepository.findAll();
        return modelMapper.map(allTeams, List.class);
    }

    public void validateTeamColor(TeamRequestDTO teamRequestDTO){
        List<TeamEntity> all = teamRepository.findAll();
        List<String> colors = new ArrayList();

        for (int i = 0; i < all.size(); i++) {
            colors.add(all.get(i).getColor());
        }
        if (colors.contains(teamRequestDTO.getColor())){
            throw new DataIntegrityViolationException("Color already registered!");
        }
    }

}