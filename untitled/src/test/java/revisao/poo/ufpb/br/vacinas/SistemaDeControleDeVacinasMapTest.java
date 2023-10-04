package revisao.poo.ufpb.br.vacinas;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaDeControleDeVacinasMapTest {


    @Test
    public void testCadastraPessoa() {
        SistemaDeControleDeVacinasMap sistema = new SistemaDeControleDeVacinasMap();
        try {
            sistema.cadastraPessoa(new Pessoa("Maria José","222.222.222-22"));
            assertTrue(sistema.existePessoaComCpf("222.222.222-22"));
        } catch (PessoaJaExisteException e) {
            fail("Não deveria lançar exceção");
        }
    }


    @Test
    public void testPesquisaQuantidadeDeVacinadosComVacinaDoTipo () {
      SistemaDeControleDeVacinasMap sistema = new SistemaDeControleDeVacinasMap();
      try{
          sistema.cadastraPessoa(new Pessoa("Maria Paula Nunes", "333.333.333-33"));
          sistema.cadastraPrimeiraDoseDePessoa("333.333.333-33", "Pfizer", "30/05/2021");
          sistema.cadastraSegundaDoseDePessoa("333.333.333-33", "Pfizer", "28/08/2021");
          int quantidade = sistema.pesquisaQuantidadeDeVacinadosComVacinaDoTipo("Pfizer");
          assertEquals(1, quantidade);
      }catch  (PessoaJaExisteException | PessoaNaoExisteException e) {
          e.printStackTrace();

    }

    }
}

