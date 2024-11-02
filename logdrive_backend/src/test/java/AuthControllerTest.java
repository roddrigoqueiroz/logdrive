import logdrive.model.Driver;
import logdrive.service.DriverService;
import logdrive.controller.AuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private DriverService driverService;

    @InjectMocks
    private AuthController driverController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_whenDriverIsNotRegistered_shouldReturnSuccessMessage() {
        // Mockando o Driver
        Driver newDriver = new Driver();
        newDriver.setEmail("email@example.com");
        newDriver.setUsername("Test User");
        newDriver.setCpf("123456789");
        newDriver.setPassword("password");

        // Definindo comportamento simulado para driverService
        when(driverService.checkIfExists(newDriver.getEmail())).thenReturn(false);

        // Chamando o método do controlador
        ResponseEntity<String> response = driverController.signup(newDriver);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário cadastrado com sucesso", response.getBody());
        verify(driverService, times(1)).saveDriver(any(Driver.class));
    }

    @Test
    void register_whenDriverAlreadyExists_shouldReturnErrorMessage() {
        // Mockando o Driver com email já registrado
        Driver existingDriver = new Driver();
        existingDriver.setEmail("existing@example.com");

        // Simulando que o email já existe no driverService
        when(driverService.checkIfExists(existingDriver.getEmail())).thenReturn(true);

        // Chamando o método do controlador
        ResponseEntity<String> response = driverController.signup(existingDriver);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro ao cadastrar usuário: Usuário já registrado", response.getBody());
        verify(driverService, never()).saveDriver(any(Driver.class));
    }

    @Test
    void register_whenExceptionOccurs_shouldReturnErrorMessage() {
        // Mockando o Driver que causa uma exceção
        Driver newDriver = new Driver();
        newDriver.setEmail("error@example.com");

        // Simulando uma exceção ao tentar salvar o driver
        when(driverService.checkIfExists(newDriver.getEmail())).thenReturn(false);
        doThrow(new RuntimeException("Database error")).when(driverService).saveDriver(any(Driver.class));

        // Chamando o método do controlador
        ResponseEntity<String> response = driverController.signup(newDriver);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro ao cadastrar usuário: Database error", response.getBody());
    }

    @Test
    void login_whenDriverIsNotRegistered_shouldReturnErrorMessage() {
        Driver newDriver = new Driver();
        newDriver.setEmail("email@example.com");
        newDriver.setUsername("Test User");
        newDriver.setCpf("123456789");
        newDriver.setPassword("password");
        when(driverService.checkIfExists(newDriver.getEmail())).thenReturn(false);
        ResponseEntity<String> response = driverController.login(newDriver);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void login_whenDriverAlreadyExists_shouldReturnErrorMessage() {
        Driver newDriver = new Driver();
        newDriver.setEmail("email@example.com");
        newDriver.setUsername("Test User");
        newDriver.setCpf("123456789");
        newDriver.setPassword("password");
        when(driverService.checkIfExists(newDriver.getEmail())).thenReturn(true);
        ResponseEntity<String> response = driverController.login(newDriver);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void login_whenExceptionOccurs_shouldReturnErrorMessage() {
        Driver newDriver = new Driver();
        newDriver.setEmail("email@example.com");
        newDriver.setUsername("Test User");
        newDriver.setCpf("123456789");
        newDriver.setPassword("password");
        doThrow(new RuntimeException("Database error")).when(driverService).saveDriver(any(Driver.class));
        ResponseEntity<String> response = driverController.login(newDriver);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void login_successful() {
        Driver newDriver = new Driver();
        newDriver.setEmail("email@example.com");
        newDriver.setUsername("Test User");
        newDriver.setCpf("123456789");
        newDriver.setPassword("password");
        when(driverService.checkIfExists(newDriver.getEmail())).thenReturn(true);
        ResponseEntity<String> response = driverController.login(newDriver);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
