package uol.compass.project.usf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uol.compass.project.usf.model.dto.request.TeamRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.TeamResponseDTO;
import uol.compass.project.usf.services.TeamServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamServiceImpl teamService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Transactional
    public ResponseEntity<Object> createTeam(@RequestBody @Valid TeamRequestDTO teamRequestDTO) {
        TeamResponseDTO teamResponseDTO = teamService.create(teamRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamResponseDTO);
    }

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @PostMapping("/{idTeam}/usf/{idUsf}")
    @Transactional
    public ResponseEntity<TeamResponseDTO> linkTeamToUsf(@PathVariable Long idTeam, @PathVariable Long idUsf) {
        TeamResponseDTO teamResponseDTO = teamService.attachTeamToUsf(idTeam, idUsf);
        return ResponseEntity.status(HttpStatus.OK).body(teamResponseDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USF_OPERATOR')")
    @GetMapping
    public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
        List<TeamResponseDTO> teams = teamService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USF_OPERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable Long id) {
        TeamResponseDTO teamResponseDTO = teamService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(teamResponseDTO);
    }

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @GetMapping("/{id}/doctor")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctorsByTeam(@PathVariable Long id) {
        List<DoctorResponseDTO> doctorsByTeam = teamService.findDoctorsInTeam(id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorsByTeam);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TeamResponseDTO> updateTeam(@PathVariable Long id,
            @RequestBody TeamRequestDTO request) {
        TeamResponseDTO response = teamService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('USF_OPERATOR')")
    @DeleteMapping("/{idTeam}/usf/{idUsf}")
    public ResponseEntity<TeamResponseDTO> deleteTeamFromUsf(@PathVariable Long idTeam, @PathVariable Long idUsf) {
        TeamResponseDTO teamResponseDTO = teamService.disattachTeamFromUsf(idTeam, idUsf);
        return ResponseEntity.status(HttpStatus.OK).body(teamResponseDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
