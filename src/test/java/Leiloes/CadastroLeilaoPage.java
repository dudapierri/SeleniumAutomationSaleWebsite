package Leiloes;

import PageObject.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage extends PageObject {
    //private WebDriver browser;

    public CadastroLeilaoPage (WebDriver browser){
        //this.browser = browser;
        super(browser);
    }

    /*public void fechar() {
        this.browser.quit();
    }*/

    public LeiloesPage cadastrarLeilao(String nome, String valor, String data){
        this.browser.findElement(By.id("nome")).sendKeys(nome);
        this.browser.findElement(By.id("valorInicial")).sendKeys(valor);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(data);
        this.browser.findElement(By.id("button-submit")).submit();

        return new LeiloesPage(browser);
    }


    public boolean isPaginaAtual() {
        return this.browser.getCurrentUrl().equals("http://localhost:8082/leiloes");
    }

    public boolean isCampoNomeErro() {
        String pageSource = this.browser.getPageSource();
        return pageSource.contains("não deve estar em branco")
                && pageSource.contains("minimo 3 caracteres");

    }

    public boolean isValorErro(){
        String pageSource = this.browser.getPageSource();
        return pageSource.contains("deve ser um valor maior de 0.1");
    }

    public boolean isDataAberturaErro(){
        String pageSource = this.browser.getPageSource();
        return pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
