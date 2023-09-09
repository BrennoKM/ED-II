package Praticaoff2_1;

import java.io.FileWriter;
import java.io.IOException;

import Hash.HashColisaoEndAberto;
import Hash.HashColisaoEndAberto.No;
import modelos.VeiculoInt;

public class Servidor {
	private HashColisaoEndAberto hash;
	private static String log;

	public Servidor(String mensagem, int tam) {
		log = "";
		hash = new HashColisaoEndAberto(tam);
		System.out.println(mensagem);
	}

	public VeiculoInt buscarVeiculo(int renavam) {
		No no = hash.buscar(renavam);
		if (no != null) {
			VeiculoInt veiculo = no.getValor();
			return veiculo;
		} else {
			return null;
		}
	}

	public boolean cadastrarVeiculo(VeiculoInt veiculo) {
		hash.inserir(veiculo.getRenavam(), veiculo);
		VeiculoInt v = buscarVeiculo(veiculo.getRenavam());
		if (v != null) {
			atualizarLog();
			return true;
		} else {
			return false;
		}
	}

	public void removerVeiculo(VeiculoInt veiculo) {
		System.out.println(hash.remover(veiculo.getRenavam()));
		atualizarLog();
	}

	public void listarVeiculos() {
		hash.imprimir();
	}

	public double getFatorCarga() {
		return hash.getFatorCarga();
	}

	public int acessarQntVeiculos() {
		return hash.getElementos();
	}

	private void atualizarLog() {
		//arvore.houveRotacao();
		log = log + "\nServidor:	qntVei=" + acessarQntVeiculos() + "	fatorCarga=" + getFatorCarga();
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
