package Leiloes;

import Login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {
    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastro;

    @BeforeEach
    public void beforeEach(){
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano","pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        this.paginaDeCadastro = paginaDeLeiloes.carregarFormulario();

    }

    @AfterEach
    public void afterEach(){
        this.paginaDeLeiloes.fechar();
    }

    @Test
    public void cadastrarLeilao(){
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));//pega o dia de hoje no padrao DD/MM/AAAA
        String nome = "Leilão do dia " + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
        Assertions.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome,valor,hoje));

    }

    @Test
    public void validarCadastroSemPreenchimentoCampos(){
        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("", "", "");
        Assertions.assertTrue(paginaDeCadastro.isPaginaAtual());
        Assertions.assertTrue(paginaDeCadastro.isCampoNomeErro());
        Assertions.assertTrue(paginaDeCadastro.isValorErro());
        Assertions.assertTrue(paginaDeCadastro.isDataAberturaErro());
    }
}
