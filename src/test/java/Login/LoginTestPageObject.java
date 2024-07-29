package Login;

import Login.LoginPage;
import org.junit.jupiter.api.*;

public class LoginTestPageObject {
    private LoginPage paginaDeLogin;


    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
       this.paginaDeLogin.fechar();
    }

    @Test
    public void successfulLogin(){
        paginaDeLogin.preencheFormularioDeLogin("fulano","pass");
        paginaDeLogin.efetuaLogin();
        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());//verifica se eu não estou mais na tela de login
        Assertions.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado()); //Verificar se o texto é igual ao usuário esperado
    }
    @Test
    public void invalidLogin (){
        paginaDeLogin.preencheFormularioDeLogin("invalido","123123");
        paginaDeLogin.efetuaLogin();

        Assertions.assertTrue(paginaDeLogin.isPaginaDeLoginError());//verifica se eu não estou mais na tela de login
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos.")); //getPageSource() retorna o código-fonte da página // busca dentro do código fonte o texto de erro e valida com assertTrue
        Assertions.assertNull(paginaDeLogin.getNomeUsuarioLogado());//valida que a funçlão vai retornar null

    }

    @Test
    public void notAccessTheRestrictedAreaWithoutBeingLoggedIn(){
        paginaDeLogin.navegaParaPagina("http://localhost:8082/leiloes/2");
        Assertions.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));



    }
}
