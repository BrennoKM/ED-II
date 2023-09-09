package Praticaoff1;

import java.io.FileWriter;
import java.io.IOException;

import g_string_AVL.ArvoreAVL;
import g_string_AVL.No;
import modelos.Veiculo;

public class Servidor {
	private ArvoreAVL<Veiculo> arvore = new ArvoreAVL<Veiculo>();
	private static String log;

	public Servidor(String mensagem) {
		log = "";
		System.out.println(mensagem);
	}

	public Veiculo buscarVeiculo(String renavam) {
		No<Veiculo> no = arvore.buscar(renavam);
		if (no != null) {
			Veiculo veiculo = no.getValor();
			return veiculo;
		} else {
			return null;
		}
	}

	public No<Veiculo> buscarNoVeiculo(String renavam) {
		return arvore.buscar(renavam);
	}

	public boolean cadastrarVeiculo(Veiculo veiculo) {
		arvore.inserir(veiculo.getRenavam(), veiculo);
		No<Veiculo> no = buscarNoVeiculo(veiculo.getRenavam());
		if (no != null) {
			atualizarLog();
			// System.out.println(chave + " Não está na arvore");
			return true;
		} else {
			return false;
		}
	}

	public void removerVeiculo(Veiculo veiculo) {
		arvore.remover(veiculo.getRenavam(), veiculo);
		atualizarLog();
	}

	public void listarVeiculos() {
		arvore.emOrdem();
	}

	public int obterAlturaArvore() {
		return arvore.getAltura();
	}

	public int acessarQntVeiculos() {
		return arvore.contarNos();
	}

	private void atualizarLog() {
		//arvore.houveRotacao();
		log = log + "\nServidor:	qntVei=" + acessarQntVeiculos() + "	altura=" + arvore.getAltura() + "	rot="
				+ arvore.getHouveRotacao() + "	qntRot=" + arvore.getQntRot();
	}

	public String encerrandoConexao() {
		System.out.println("Servidor desconectado.");
		String path = "src/log.txt";
		FileWriter writer;
		try {
			writer = new FileWriter(path, false);
			writer.write(log);
			writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return log;
	}

}
