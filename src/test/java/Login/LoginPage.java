package Login;

import Leiloes.LeiloesPage;
import PageObject.PageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends PageObject {
    private static final String URL_LOGIN = "http://localhost:8082/login";
    //private WebDriver browser;

    public LoginPage() {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Work\\Documents\\QA\\chromedriver.exe");
       // this.browser = new ChromeDriver(); //abre a tela
        super(null);
        this.browser.navigate().to(URL_LOGIN);// navega para a página desejada
    }
    /*public void fechar(){
        this.browser.quit(); // fecha a janela do navegador que ele abriu
    }*/

    public void preencheFormularioDeLogin(String usuario, String senha){
        browser.findElement(By.id("username")).sendKeys(usuario);
        browser.findElement(By.id("password")).sendKeys(senha);
    }

    public LeiloesPage efetuaLogin(){
        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isPaginaDeLogin(){
        return browser.getCurrentUrl().equals(URL_LOGIN);        //verifica se eu não estou mais na tela de login
    }

    public String getNomeUsuarioLogado(){
        try {
            return browser.findElement(By.id("user-name")).getText();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public boolean isPaginaDeLoginError (){
        return browser.getCurrentUrl().equals("http://localhost:8082/login?error");
    }

    public boolean contemTexto(String texto){
        return browser.getPageSource().contains(texto);
    }

    public void navegaParaPagina (String pagina){
        this.browser.navigate().to(pagina);
    }

}
