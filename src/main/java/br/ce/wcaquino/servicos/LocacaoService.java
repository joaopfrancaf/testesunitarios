package br.ce.wcaquino.servicos;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;

import static br.ce.wcaquino.utils.DataUtils.*;

public class LocacaoService {
	
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

	public static void main(String[] args) {
		//cenario (pelo que entendi são as variaveis mais ou menos que o mnetodo em questao irá usar)
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("user 1");
		Filme filme = new Filme("filme 1", 2,5.0);

		//acao
		Locacao locacao = service.alugarFilme(usuario,filme); //o metodo em questao para testar

		//verify (coleta o objeto da ação) PELO QUE ENTENDI OS RESULTADOS TEM QUE HAVER COM AS REGRAS DE NEGOCIO  EX.: AQUI TEM QUE SER A DATA DE HOJE, AQUI O VALOR DO PRODUTO (ETC..)
		System.out.println(locacao.getValor() == 5);
		System.out.println(isMesmaData(locacao.getDataLocacao(), new Date())); //A DATA DE LOCACAO TEM QUE SER HOJE (esse metodo tu passa a data de locacao e instacia o new data para fazer a data atual, o resultado tem que ser true para ficar de acordo com as regras de negocio)
		System.out.println(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)));//data de retorno deve ser o dia seguinte (1 dia apenas)

		//ACIMA O CONCEITO DE CENARIO , AÇÃO E VERIFICAÇÃO







		//NORMAS DOS TESTES F.I.R.S.T
		//FAST (TEM QUE RODAR RAPIDO)
		//INDEPENDENT (UM TESTE, NAO PODE DEPENDER DE OUTRO, PODENDO RODAR NA HORA)
		//REPATEABLE (REPETITIVO, PODE SER EXECUTADO A QUALQUER MOMENTO, E TEM QUE DAR O MESMO RESULTADO)
		//SELF-VERIFYING (AUTO VERIFICADO) (O TESTE DEVE SABER O QUANTO A SUA EXECUÇÃO FOI CORRETA, OU QUANTO FALHOU) EXEMPLO A BAIXO

		/*
		System.out.println(locacao.getValor());
		System.out.println(locacao.getDataLocacao());
		System.out.println(locacao.getDataRetorno());

		NOS TESTES A CIMA, COMO DA PARA VER, QUEM VÊ SE ESTA CERTO OU NÃO E O DESENVOLVEDOR,
		O CERTO É IMPLEMENTAR UMA LOGICA PARA RETORNAR TRUE (NORMA SELF-VERIFYING)
		 */
		//TIME oportuno, um teste tem que ser criado em memnto correto


		//o unico problema nesse capitolo, é que falta uma padronização nos testes para saber qual teste falhou e qual condição do erro.

	}
}