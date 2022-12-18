package uol.compass.project.usf.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import uol.compass.project.usf.dto.request.TeamRequestDTO;
import uol.compass.project.usf.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.dto.response.TeamResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.entities.DoctorEntity;
import uol.compass.project.usf.entities.TeamEntity;
import uol.compass.project.usf.entities.UsfEntity;
import uol.compass.project.usf.exceptions.TeamNotFoundException;
import uol.compass.project.usf.exceptions.UsfNotFoundException;
import uol.compass.project.usf.repositories.DoctorRepository;
import uol.compass.project.usf.repositories.TeamRepository;
import uol.compass.project.usf.repositories.UsfRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;
    private final DoctorRepository doctorRepository;
    private final UsfRepository usfRepository;

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

    public TeamResponseDTO getTeamById(Long id){
        TeamEntity team = getTeamByIdVerication(id);
        return modelMapper.map(team, TeamResponseDTO.class);
    }

    private TeamEntity getTeamByIdVerication(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(TeamNotFoundException::new);
    }

    public List<DoctorResponseDTO> getDoctorsByTeam(Long id) {
        getTeamByIdVerication(id);
        List<DoctorEntity> allDoctors = doctorRepository.findByIdTeam(id);
        return modelMapper.map(allDoctors, List.class);
    }

    public TeamResponseDTO update(Long id, TeamRequestDTO teamRequestDTO) {
        getTeamByIdVerication(id);
        TeamEntity newTeam = modelMapper.map(teamRequestDTO, TeamEntity.class);
        newTeam.setId(id);
        TeamEntity updatedTeam = teamRepository.save(newTeam);

        return modelMapper.map(updatedTeam, TeamResponseDTO.class);
    }

    private UsfEntity getUsfEntity(Long id) {
        return usfRepository.findById(id)
                .orElseThrow(UsfNotFoundException::new);
    }

    public UsfResponseDTO setUsfTeam(Long idTeam, Long idUsf){
        getTeamByIdVerication(idTeam);
        UsfEntity usf = getUsfEntity(idUsf);

        usf.setId(idUsf);
        usf.setIdCurrentTeam(idTeam);
        usfRepository.save(usf);

        return modelMapper.map(usf, UsfResponseDTO.class);
    }

    public UsfResponseDTO deleteTeamFromUsf(Long idTeam, Long idUsf) {
        getTeamByIdVerication(idTeam);
        UsfEntity usf = getUsfEntity(idUsf);

        usf.setId(idUsf);
        usf.setIdCurrentTeam(null);
        UsfEntity newUsf = usfRepository.save(usf);

        return modelMapper.map(newUsf, UsfResponseDTO.class);
    }


    public void validateTeamColor(TeamRequestDTO teamRequestDTO){
        List<TeamEntity> all = teamRepository.findAll();
        List<String> colors = new ArrayList();
        for (int i = 0; i < all.size(); i++) {
            colors.add(all.get(i).getColor());
        }
        if (colors.contains(teamRequestDTO.getColor())){
            throw new DataIntegrityViolationException("Cor já registrada");
        }
    }



}