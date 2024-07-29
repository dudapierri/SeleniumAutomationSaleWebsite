package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PageObject {
    protected WebDriver browser;

    public PageObject(WebDriver browser){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Work\\Documents\\QA\\chromedriver.exe");
        if (browser == null){
          this.browser = new ChromeDriver();
        }else{
            this.browser = browser; //abre a tela
        }

        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS) // Toda vez que o selenium for buscar algo na página ele espera 5 segundos antes de disparar erro
                .pageLoadTimeout(10, TimeUnit.SECONDS); // espera até 10 segundos para a página carregar
    }
    public void fechar(){
        this.browser.quit(); // fecha a janela do navegador que ele abriu
    }


}
