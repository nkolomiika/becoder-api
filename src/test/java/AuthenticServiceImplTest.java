import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.dto.auth.AuthRequest;
import com.example.becoderapi.model.dto.auth.AuthResponse;
import com.example.becoderapi.model.exceptions.auth.NoSuchAccountException;
import com.example.becoderapi.model.exceptions.auth.UserAlreadyExistsException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.persistance.services.impl.AuthenticServiceImpl;
import com.example.becoderapi.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @Mock
    private BCryptPasswordEncoder bCrypt;

    @InjectMocks
    private AuthenticServiceImpl authenticService;

    @Test
    void register_shouldCreateUserAndReturnToken() {
        // Arrange
        AuthRequest request = new AuthRequest("testUser", "testPassword");
        Account expectedAccount = new Account("testUser", "encodedPassword"); // Assuming 'bCrypt.encode' returns "encodedPassword"
        String expectedToken = "testToken";
        when(bCrypt.encode(request.password())).thenReturn("encodedPassword"); // Mock the BCryptPasswordEncoder
        when(jwtTokenUtil.createToken(any(Account.class))).thenReturn(expectedToken);
        when(accountRepository.findAccountByLogin(request.login())).thenReturn(Optional.empty());

        AuthResponse response = authenticService.register(request);

        assertEquals(expectedToken, response.token());
        verify(accountRepository, times(1))
                .save(argThat(account -> account.getLogin().equals("testUser")
                        && account.getPassword().equals("encodedPassword")));
        verify(jwtTokenUtil, times(1)).createToken(any(Account.class));
    }

    @Test
    void register_shouldThrowUserAlreadyExistsException_whenUserExists() {
        AuthRequest request = new AuthRequest("testUser", "testPassword");
        Account existingAccount = new Account("testUser", "encodedPassword");
        when(accountRepository.findAccountByLogin(request.login())).thenReturn(Optional.of(existingAccount));

        assertThrows(UserAlreadyExistsException.class, () -> authenticService.register(request));
        verify(bCrypt, never()).encode(anyString());
        verify(jwtTokenUtil, never()).createToken(any(Account.class));
    }

    @Test
    void login_shouldReturnToken_whenLoginAndPasswordAreCorrect() throws NoSuchAccountException {
        AuthRequest request = new AuthRequest("testUser", "testPassword");
        Account account = new Account("testUser", "encodedPassword");
        String expectedToken = "testToken";
        when(accountRepository.findAccountByLogin(request.login())).thenReturn(Optional.of(account));
        when(bCrypt.matches(request.password(), account.getPassword())).thenReturn(true);
        when(jwtTokenUtil.createToken(any(Account.class))).thenReturn(expectedToken);

        AuthResponse response = authenticService.login(request);

        assertEquals(expectedToken, response.token());
        verify(jwtTokenUtil, times(1)).createToken(account);
    }

    @Test
    void login_shouldThrowNoSuchAccountException_whenLoginIsIncorrect() throws NoSuchAccountException {
        AuthRequest request = new AuthRequest("testUser", "testPassword");
        when(accountRepository.findAccountByLogin(request.login())).thenReturn(Optional.empty());

        assertThrows(NoSuchAccountException.class, () -> authenticService.login(request));
        verify(bCrypt, never()).matches(anyString(), anyString());
        verify(jwtTokenUtil, never()).createToken(any(Account.class));
    }

    @Test
    void login_shouldThrowNoSuchAccountException_whenPasswordIsIncorrect() throws NoSuchAccountException {
        AuthRequest request = new AuthRequest("testUser", "testPassword");
        Account account = new Account("testUser", "encodedPassword");
        when(accountRepository.findAccountByLogin(request.login())).thenReturn(Optional.of(account));
        when(bCrypt.matches(request.password(), account.getPassword())).thenReturn(false);

        assertThrows(NoSuchAccountException.class, () -> authenticService.login(request));
        verify(jwtTokenUtil, never()).createToken(any(Account.class));
    }
}

