package Lance;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LancePage {
    WebDriver browser;

    public LancePage(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Work\\Documents\\QA\\chromedriver.exe");
        this.browser = new ChromeDriver();
        this.browser.navigate().to("http://localhost:8082/login");
    }

    public void navegaPaginaDeLance(String usuario, String senha) {
        this.browser.findElement(By.id("username")).sendKeys(usuario);
        this.browser.findElement(By.id("password")).sendKeys(senha);
        this.browser.findElement(By.id("login-form")).submit();
        this.browser.findElement(By.linkText("dar lance")).click();
    }

    public void fechar(){
        this.browser.quit();
    }

    public void submeterLance(){
        this.browser.findElement(By.id("btnDarLance")).submit();
    }

    public boolean isLanceNulo(){
        return this.browser.getPageSource().contains("não deve ser nulo") &&
               this.browser.getCurrentUrl().equals("http://localhost:8082/lances");
    }

    public void novoLance(String valor){
        this.browser.findElement(By.id("valor")).sendKeys(valor);
    }

    public boolean isLanceValido(){
        return this.browser.getPageSource().contains("Lance adicionado com sucesso!");
    }


    public boolean isLanceInvalido() {
        return this.browser.getPageSource().contains("Lance invalido!");
    }

    public void voltarTelaDeLeiloes() {
        this.browser.findElement(By.linkText("Voltar")).click();
    }

    public boolean isTelaLeiloes() {
        return this.browser.getCurrentUrl().equals("http://localhost:8082/leiloes");
    }
}
