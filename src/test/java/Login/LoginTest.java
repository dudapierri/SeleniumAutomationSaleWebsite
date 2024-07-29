package Login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private static final String URL_LOGIN = "http://localhost:8082/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Work\\Documents\\QA\\chromedriver.exe");

    }
    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver(); //abre a tela
        this.browser.navigate().to(URL_LOGIN);// navega para a página desejada
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit(); // fecha a janela do navegador que ele abriu
    }

    @Test
    public void successfulLogin(){
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));//verifica se eu não estou mais na tela de login
        Assertions.assertEquals("fulano", browser.findElement(By.id("user-name")).getText()); //Verificar se o texto é igual ao usuário esperado
    }
    @Test
    public void invalidLogin (){
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123123");
        browser.findElement(By.id("login-form")).submit();

        //WebDriverWait wait = new WebDriverWait(browser, 5);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert alert-danger")));

        Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8082/login?error"));//verifica se eu não estou mais na tela de login
        //Assertions.assertEquals("Usuário e senha inválidos.", browser.findElement(By.xpath("/html/body/div[2]/div[2]")).getText()); //Verificar se o texto é igual ao usuário esperado
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos.")); //getPageSource() retorna o código-fonte da página // busca dentro do código fonte o texto de erro e valida com assertTrue
        Assertions.assertThrows(NoSuchElementException.class, () ->browser.findElement(By.id("user-name")));//valida exception // Validar que nesse caso o nome do usuário não aparece <span> nem vai existir nesse caso

    }

    @Test
    public void notAccessTheRestrictedAreaWithoutBeingLoggedIn(){
        this.browser.navigate().to("http://localhost:8082/leiloes/2");
        Assertions.assertTrue(browser.getCurrentUrl().equals("http://localhost:8082/login"));
        Assertions.assertFalse(browser.getPageSource().contains("Dados do Leilão"));



    }

}
