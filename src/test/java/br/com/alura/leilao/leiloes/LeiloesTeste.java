package br.com.alura.leilao.leiloes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTeste {

    private LeiloesPage paginaDeLeiloes;

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
    }
    
    @Test
    public void deveriaCadastrarLeilao() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioDeLogin("fulano","pass");
        this.paginaDeLeiloes = paginaDeLogin.submeteFormulario();
        
        paginaDeLeiloes.carregarFormulario();
    }
}