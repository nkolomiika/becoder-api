import com.example.becoderapi.model.data.Account;
import com.example.becoderapi.model.data.Transaction;
import com.example.becoderapi.model.dto.basic.Response;
import com.example.becoderapi.model.dto.transaction.TransactionRequest;
import com.example.becoderapi.model.dto.transaction.TransactionResponse;
import com.example.becoderapi.model.exceptions.abstracts.TransactionRuntimeException;
import com.example.becoderapi.persistance.repository.AccountRepository;
import com.example.becoderapi.persistance.repository.TransactionRepository;
import com.example.becoderapi.persistance.services.impl.TransactionServiceImpl;
import com.example.becoderapi.utils.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class TransactionServiceImplTest {

    @Mock
    private AccountRepository accountRepositoryMock;

    @Mock
    private TransactionRepository transactionRepositoryMock;

    @Mock
    private JwtTokenUtil jwtTokenUtilMock;


    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl(accountRepositoryMock, transactionRepositoryMock, jwtTokenUtilMock);
    }

    // Тест успешного выполнения метода makeContract
    @Test
    void makeContractSuccess() throws Exception {
        Account buyer = new Account("buyer", "qwerty");
        Account seller = new Account("seller", "qwerty");

        accountRepositoryMock.save(buyer);
        accountRepositoryMock.save(seller);

        String buyerId = buyer.getId();
        String sellerId = seller.getId();

        String jwt = "valid-jwt-token";
        TransactionRequest request = new TransactionRequest(sellerId, 100);
        Transaction transaction = new Transaction(buyerId, sellerId, request.sum());

        when(accountRepositoryMock.findAccountById(buyerId)).thenReturn(Optional.of(buyer));
        when(accountRepositoryMock.findAccountById(sellerId)).thenReturn(Optional.of(seller));
        when(jwtTokenUtilMock.extractTokenFromJwt(jwt)).thenReturn("valid-token");
        when(jwtTokenUtilMock.getId("valid-token")).thenReturn(buyerId);
        when(transactionRepositoryMock.updateBalance(buyerId, 9900)).thenReturn(true);
        when(transactionRepositoryMock.updateBalance(sellerId, 10100)).thenReturn(true);
        when(transactionRepositoryMock.save(any(Transaction.class))).thenReturn(transaction);


        // Выполнение тестируемого метода
        TransactionResponse result = transactionService.makeContract(jwt, request);

        // Проверки
        assertNotNull(result);
        assertEquals("Success transaction!", result.message());
        assertNotNull(result.transaction());
        assertEquals(buyerId, result.transaction().getBuyerId());
        assertEquals(sellerId, result.transaction().getSellerId());
        assertEquals(100, result.transaction().getCost());

        assertEquals(buyer.getBalance(), 9900);
        assertEquals(seller.getBalance(), 10100);
    }

    // Тест исключения TransactionRuntimeException в методе makeContract
    @Test
    void makeContractTransactionRuntimeException() {
        // Данные для теста
        String jwt = "valid-jwt-token";
        TransactionRequest request = new TransactionRequest("seller-id", 100);

        // Настройка моков
        when(accountRepositoryMock.findAccountById("buyer-id")).thenReturn(Optional.of(new Account("buyer-id", "qwerty")));
        when(accountRepositoryMock.findAccountById("seller-id")).thenReturn(Optional.of(new Account("seller-id", "qwerty")));
        when(jwtTokenUtilMock.extractTokenFromJwt(jwt)).thenReturn("valid-token");
        when(jwtTokenUtilMock.getId("valid-token")).thenReturn("buyer-id");
        when(transactionRepositoryMock.updateBalance("buyer-id", 900)).thenReturn(false);

        // Выполнение тестируемого метода
        assertThrows(TransactionRuntimeException.class, () -> transactionService.makeContract(jwt, request));
    }

    // Тест исключения JwtException в методе makeContract
    @Test
    void makeContractJwtException() {
        // Данные для теста
        String jwt = "invalid-jwt-token";
        TransactionRequest request = new TransactionRequest("seller-id", 100);

        // Настройка моков
        when(jwtTokenUtilMock.extractTokenFromJwt(jwt)).thenThrow(new JwtException("Invalid JWT token"));

        // Выполнение тестируемого метода
        assertThrows(JwtException.class, () -> transactionService.makeContract(jwt, request));
    }

    // Тест успешного выполнения метода updateBalance
    @Test
    void updateBalanceSuccess() {
        // Данные для теста
        TransactionRequest request = new TransactionRequest("seller-id", 100);
        // Настройка моков
        when(accountRepositoryMock.findAccountById("seller-id")).thenReturn(Optional.of(new Account("seller-id", "qwerty")));

        // Выполнение тестируемого метода
        Response result = transactionService.updateBalance(request);

        // Проверки
        assertNotNull(result);
        assertEquals("Balance successfully updated with 100.0", result.message());
    }

    // Тест исключения TransactionRuntimeException в методе updateBalance
    @Test
    void updateBalanceTransactionRuntimeException() {
        // Данные для теста
        TransactionRequest request = new TransactionRequest("seller-id", 100);

        // Настройка моков
        when(accountRepositoryMock.findAccountById("seller-id")).thenReturn(Optional.empty());

        // Выполнение тестируемого метода
        assertThrows(TransactionRuntimeException.class, () -> transactionService.updateBalance(request));
    }
}

