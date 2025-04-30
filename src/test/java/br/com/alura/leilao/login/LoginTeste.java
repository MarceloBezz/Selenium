package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTeste {

    private static final String URL_LOGIN = "http://localhost:8080/login";
    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver.exe");
    }

    @BeforeEach
    public void BeforeEach() {
        this.browser = new ChromeDriver();        
        this.browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach() {
        browser.quit();
    }
    
    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("senha errada");
        browser.findElement(By.id("login-form")).submit();

        Assertions.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error"));
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());

    }
    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");

        Assertions.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
        Assertions.assertFalse(browser.getPageSource().contains("Dados do leilão"));
    }
}
