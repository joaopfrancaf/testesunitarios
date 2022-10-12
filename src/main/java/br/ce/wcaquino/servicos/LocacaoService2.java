package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.*;

public class LocacaoService2 {

    public Locacao alugarFilme(Usuario usuario, Filme filme) {
        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }

    //junit conceitos
    //testrunner (quem vai coletar os testes e ser executado)
    //testfixture(tambem testcontest) (pre condições necessarias ao teste)
    //testsuites (que é aonde posso alencar os testes que devem ser executados)
    //testresultformatter (padronizar os resultados do testes)
    //assertions ferifica o comportamento do teste, (geramente usa padrao logico)





    //ao inves de colocar o metodo main para testar o codigo, usar essa anotação do junit
    @Test
    public void teste() {
        //cenario (pelo que entendi são as variaveis mais ou menos que o mnetodo em questao irá usar)
        LocacaoService service = new LocacaoService();
        Usuario usuario = new Usuario("user 1");
        Filme filme = new Filme("filme 1", 2, 5.0);

        //acao
        Locacao locacao = service.alugarFilme(usuario, filme); //o metodo em questao para testar

        //verify (coleta o objeto da ação) PELO QUE ENTENDI OS RESULTADOS TEM QUE HAVER COM AS REGRAS DE NEGOCIO  EX.: AQUI TEM QUE SER A DATA DE HOJE, AQUI O VALOR DO PRODUTO (ETC..)
        //usando apenas o printout do java, tem que usar os metodos do junit (Assert.assertTrue) (dai o framework funciona)
        Assert.assertTrue(locacao.getValor() == 5);
        Assert.assertTrue(isMesmaData(locacao.getDataLocacao(), new Date())); //A DATA DE LOCACAO TEM QUE SER HOJE (esse metodo tu passa a data de locacao e instacia o new data para fazer a data atual, o resultado tem que ser true para ficar de acordo com as regras de negocio)
        Assert.assertTrue(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));
    }
}