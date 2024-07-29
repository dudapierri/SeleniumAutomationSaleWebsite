package Leiloes;

import PageObject.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage extends PageObject {

    private static final String URL_CADASTRO_LEILOES = "http://localhost:8082/leiloes/new";
    //private WebDriver browser;

    public LeiloesPage(WebDriver browser) {
        //this.browser = browser;
        super(browser);
    }

    //public void fechar(){
    //    this.browser.quit();
  //  }

    public CadastroLeilaoPage carregarFormulario(){
        this.browser.navigate().to(URL_CADASTRO_LEILOES);
        return new CadastroLeilaoPage(browser);

    }

    /*public boolean isLeilaoCadastrado(String nome, String valor, String data){
        if(this.browser.getPageSource().contains("Leilão salvo com sucesso") &&
           this.browser.getPageSource().contains(nome) &&
           this.browser.getPageSource().contains(valor) &&
           this.browser.getPageSource().contains(data)
        ){
            return true;

        } else{
            return false;
        }


    }*/

    public boolean isLeilaoCadastrado(String nome, String valor, String data){
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("body > div.container > table > tbody > tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaValor = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));
        WebElement colunaData = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));

        return colunaNome.getText().equals(nome)
                && colunaValor.getText().equals(valor)
                && colunaData.getText().equals(data);
    }


}
