package uol.compass.project.usf.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import uol.compass.project.usf.model.dto.request.UserRequestDTO;
import uol.compass.project.usf.model.dto.request.UserRequestUpdateDTO;
import uol.compass.project.usf.model.dto.response.UserResponseDTO;
import uol.compass.project.usf.model.entities.UserEntity;
import uol.compass.project.usf.repositories.UserRepository;
import uol.compass.project.usf.services.RoleUserService;
import uol.compass.project.usf.services.UserServiceImpl;
import uol.compass.project.usf.utils.TestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = UserController.class)
public class UserControllerTest {

    public static final String CREATE_URL = "/user/create";
    public static final String ID = "/1";
    public static final String UPDATE_URL = "/user/update" + ID;

    public static final String BASE_LOGIN = "login";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @MockBean
    private RoleUserService roleUserService;

    @Test
    void create() throws Exception {
        UserEntity user = new UserEntity();
        user.setLogin("teste");
        user.setPassword("12345");

        UserRequestDTO request = getUserRequestDTO();

        UserResponseDTO userResponseDTO = new UserResponseDTO();

        when(userRepository.findByLogin(BASE_LOGIN)).thenReturn(user);
        when(userService.create(any())).thenReturn(userResponseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.post(CREATE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        UserRequestUpdateDTO request = getUserRequestUpdateDTO();
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        when(userService.update(any(), any())).thenReturn(userResponseDTO);

        String input = TestUtils.mapToJson(request);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.patch(UPDATE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private UserRequestDTO getUserRequestDTO() {
        return UserRequestDTO.builder()
                .name("teste")
                .login("testeLogin")
                .password("12345")
                .build();
    }

    private UserRequestUpdateDTO getUserRequestUpdateDTO() {
        return UserRequestUpdateDTO.builder()
                .oldPassword("12345")
                .name("teste")
                .newPassword("54321")
                .build();
    }
}
