package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class LeiloesPage {

    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
    private WebDriver browser;

    public LeiloesPage(WebDriver browser) {
        this.browser = browser;
    }

    public void fechar() {
        browser.quit();
    }

    public void carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
    }
}
