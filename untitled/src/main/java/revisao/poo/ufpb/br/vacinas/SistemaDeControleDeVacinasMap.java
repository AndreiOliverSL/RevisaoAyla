package revisao.poo.ufpb.br.vacinas;


import java.util.HashMap;
import java.util.Map;


public class SistemaDeControleDeVacinasMap implements SistemaDeControleDeVacinas  {


    private Map<String, Pessoa> pessoas;


    public SistemaDeControleDeVacinasMap() {
        this.pessoas = new HashMap<>();
    }


    @Override
    public void cadastraPessoa(Pessoa pessoa) throws PessoaJaExisteException {
        if (this.pessoas.containsKey(pessoa.getCpf())) {
            throw new PessoaJaExisteException("Já existe pessoa com o CPF " + pessoa.getCpf());
        } else {
            this.pessoas.put(pessoa.getCpf(), pessoa);
        }
    }
    @Override
    public boolean existePessoaComCpf(String cpf) {
        return this.pessoas.containsKey(cpf);
    }


    @Override
    public int pesquisaQuantidadeDeVacinadosComVacinaDoTipo(String tipoVacina){
        int cont = 0;


        for (Pessoa pessoa : pessoas.values()){
            if((pessoa.getTipoVacinaPrimeiraDose().equalsIgnoreCase(tipoVacina) && pessoa.recebeuPrimeiraDose()) ||
                    (pessoa.getTipoVacinaSegundaDose().equalsIgnoreCase(tipoVacina) && !pessoa.getDataSegundaDose().isEmpty())){
                cont++;
            }
        }
        return cont;
    }


    @Override
    public boolean cadastraPrimeiraDoseDePessoa(String cpf, String tipoVacinaPrimeiraDose, String dataVacinaPrimeiraDose) throws PessoaNaoExisteException {
        if (!existePessoaComCpf(cpf)) {
            throw new PessoaNaoExisteException("Pessoa com CPF " + cpf + " não encontrada.");
        }


        Pessoa pessoa = pessoas.get(cpf);


        if (pessoa.recebeuPrimeiraDose()) {
            return false;
        } else {
            pessoa.setDataPrimeiraDose(dataVacinaPrimeiraDose);
            pessoa.setTipoVacinaPrimeiraDose(tipoVacinaPrimeiraDose);
            return true;
        }
    }


    @Override
    public boolean cadastraSegundaDoseDePessoa(String cpf, String tipoVacinaSegundaDose, String dataVacinaSegundaDose) throws PessoaNaoExisteException {
        if (!existePessoaComCpf(cpf)) {
            throw new PessoaNaoExisteException("Pessoa com CPF " + cpf + " não encontrada.");
        }


        Pessoa pessoa = pessoas.get(cpf);


        if (pessoa.recebeuSegundaDose()) {
            return false;
        } else {
            pessoa.setDataPrimeiraDose(dataVacinaSegundaDose);
            pessoa.setTipoVacinaPrimeiraDose(tipoVacinaSegundaDose);
            return true;
        }
    }


    @Override
    public int pesquisaQuantidadeDeVacinadosComPrimeiraDose() {
        int cont = 0;


        for (Pessoa pessoa : pessoas.values()){
            if(pessoa.recebeuPrimeiraDose()){
                cont++;
            }
        }
        return cont;
    }


    @Override
    public int pesquisaQuantidadeDeVacinadosComSegundaDose() {
        int cont = 0;
        for (Pessoa pessoa : pessoas.values()){
            if(pessoa.recebeuSegundaDose()){
                cont++;
            }
        }
        return cont;
    }
}


