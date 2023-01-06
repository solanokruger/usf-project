package uol.compass.project.usf.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import uol.compass.project.usf.model.dto.request.DoctorRequestDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseDTO;
import uol.compass.project.usf.model.dto.response.DoctorResponseParameters;
import uol.compass.project.usf.model.entities.DoctorEntity;
import uol.compass.project.usf.model.entities.TeamEntity;
import uol.compass.project.usf.repositories.DoctorRepository;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {
    
    public static final Long ID = 1L;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private TeamServiceImpl teamService;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void shouldCreateDoctor_sucess() {
        DoctorEntity doctor = new DoctorEntity();
        DoctorResponseDTO response = new DoctorResponseDTO();
        DoctorRequestDTO request = new DoctorRequestDTO();

        Mockito.when(doctorRepository.save(any())).thenReturn(doctor);

        DoctorResponseDTO doctorResponseDTO = doctorService.create(request);

        assertEquals(response, doctorResponseDTO);
        verify(doctorRepository).save(any());
    }

    @Test
    void shouldFindAllDoctors_sucess() {
        DoctorEntity doctor = new DoctorEntity();
        Page<DoctorEntity> page = new PageImpl<>(List.of(doctor));
        DoctorResponseParameters expectedDoctorResponseParameters = getDoctorResponseParameters();

        Mockito.when(doctorRepository.findAll((Pageable) any())).thenReturn(page);

        DoctorResponseParameters doctorResponseParameters = doctorService.findAll(null, any(Pageable.class));

        assertEquals(expectedDoctorResponseParameters, doctorResponseParameters);
    }

    @Test
    void shouldFindDoctorById_sucess() {
        DoctorEntity doctor = new DoctorEntity();
        DoctorResponseDTO doctorResponseDto = new DoctorResponseDTO();

        Mockito.when(doctorRepository.findById(any())).thenReturn(Optional.of(doctor));

        DoctorResponseDTO response = doctorService.findById(ID);

        assertEquals(response, doctorResponseDto);
    }

    @Test
    void shouldAttachDoctorToTeam() {
        TeamEntity team = new TeamEntity();

        DoctorResponseDTO doctorResponseDto = new DoctorResponseDTO();
        DoctorEntity doctor = new DoctorEntity();

        doctorResponseDto.setTeam(team);

        Mockito.when(teamService.findTeamByIdVerication(any())).thenReturn(team);

        Mockito.when(doctorRepository.findById(any())).thenReturn(Optional.of(doctor));

        Mockito.when(doctorRepository.save(any())).thenReturn(doctor);

        DoctorResponseDTO response = doctorService.attachDoctorToTeam(ID, ID);

        assertEquals(response, doctorResponseDto);
    }

    @Test
    void shouldDisattachDoctorToTeam() {
        TeamEntity team = new TeamEntity();

        DoctorResponseDTO doctorResponseDto = new DoctorResponseDTO();
        DoctorEntity doctor = new DoctorEntity();

        Mockito.when(teamService.findTeamByIdVerication(any())).thenReturn(team);

        Mockito.when(doctorRepository.findById(any())).thenReturn(Optional.of(doctor));

        Mockito.when(doctorRepository.save(any())).thenReturn(doctor);

        DoctorResponseDTO response = doctorService.disattachDoctorFromTeam(ID, ID);

        assertEquals(response, doctorResponseDto);
    }

    @Test
    void shouldUpdateDoctor_sucess() {
        DoctorEntity doctor = new DoctorEntity();
        DoctorResponseDTO doctorResponseDto = new DoctorResponseDTO();
        DoctorRequestDTO request = new DoctorRequestDTO();

        Mockito.when(doctorRepository.findById(any())).thenReturn(Optional.of(doctor));
        Mockito.when(doctorRepository.save(any())).thenReturn(doctor);

        DoctorResponseDTO response = doctorService.update(ID, request);

        assertEquals(response, doctorResponseDto);
    }

    @Test
    void shouldDeleteUsf_sucess() {
        DoctorEntity usf = new DoctorEntity();

        Mockito.when(doctorRepository.findById(any())).thenReturn(Optional.of(usf));

        doctorService.delete(ID);

        verify(doctorRepository).deleteById(any());
    }

    private DoctorResponseParameters getDoctorResponseParameters() {
        return DoctorResponseParameters.builder()
                .numberOfElements(1)
                .totalElements(1L)
                .totalPages(1)
                .doctor(List.of(new DoctorResponseDTO()))
                .build();
    }

}
