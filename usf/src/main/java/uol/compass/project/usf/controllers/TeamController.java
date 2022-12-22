package uol.compass.project.usf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uol.compass.project.usf.dto.request.TeamRequestDTO;
import uol.compass.project.usf.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.dto.response.TeamResponseDTO;
import uol.compass.project.usf.dto.response.UsfResponseDTO;
import uol.compass.project.usf.services.TeamService;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> registerTeam(@RequestBody TeamRequestDTO teamRequestDTO){
        try {
            validateTeamParameter(teamRequestDTO);
            TeamResponseDTO teamResponseDTO = teamService.createTeam(teamRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(teamResponseDTO);
        }catch (IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/{idTeam}/usf/{idUsf}")
    @Transactional
    public ResponseEntity<UsfResponseDTO> linkTeamToUsf(@PathVariable Long idTeam, @PathVariable Long idUsf){
        UsfResponseDTO usfResponseDTO = teamService.setUsfTeam(idTeam, idUsf);
        return ResponseEntity.status(HttpStatus.OK).body(usfResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<TeamResponseDTO> teams = teamService.getTeams();
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable Long id) {
        TeamResponseDTO teamResponseDTO = teamService.getTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(teamResponseDTO);
    }

    @GetMapping("/{id}/doctor")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctorsByTeam(@PathVariable Long id) {
        List<DoctorResponseDTO> doctorsByTeam = teamService.getDoctorsByTeam(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorsByTeam);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable Long id,
                                                            @RequestBody TeamRequestDTO request) {
        TeamResponseDTO response = teamService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{idTeam}/usf/{idUsf}")
    public ResponseEntity<UsfResponseDTO> deleteTeamFromUsf(@PathVariable Long idTeam, @PathVariable Long idUsf){
        UsfResponseDTO usfResponseDTO = teamService.deleteTeamFromUsf(idTeam, idUsf);
        return ResponseEntity.status(HttpStatus.OK).body(usfResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }


    private void validateTeamParameter(TeamRequestDTO teamRequestDTO) {
        char[] chars = teamRequestDTO.getColor().toCharArray();
        for (char c:chars) {
            if (!Character.isAlphabetic(c)) {
                throw new IllegalStateException();
            }
        }
    }

}
