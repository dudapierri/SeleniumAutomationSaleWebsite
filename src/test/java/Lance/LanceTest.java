package Lance;

import Login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LanceTest {
    private LancePage paginaDeLance;

    @BeforeEach
    public void beforeEach(){
        this.paginaDeLance = new LancePage();
        this.paginaDeLance.navegaPaginaDeLance("fulano","pass");
    }

    @AfterEach
    public void afterEach(){
        paginaDeLance.fechar();
    }

    @Test
    public void lanceVazio(){
        paginaDeLance.submeterLance();
        Assertions.assertTrue(paginaDeLance.isLanceNulo());

    }

    @Test
    public void lanceValido(){
        paginaDeLance.novoLance("500.00");
        paginaDeLance.submeterLance();
        Assertions.assertTrue(paginaDeLance.isLanceValido());
    }

    @Test
    public void validaUsuarioDoisLancesConsecutivos(){
        paginaDeLance.novoLance("900.00");
        paginaDeLance.submeterLance();
        paginaDeLance.novoLance("1000.00");
        paginaDeLance.submeterLance();
        Assertions.assertTrue(paginaDeLance.isLanceInvalido());
    }

    @Test
    public void validaBotaoVoltar(){
        paginaDeLance.voltarTelaDeLeiloes();
        Assertions.assertTrue(paginaDeLance.isTelaLeiloes());
    }

}
