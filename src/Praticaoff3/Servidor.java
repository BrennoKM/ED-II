package Praticaoff3;

import java.io.FileWriter;
import java.io.IOException;

import Hash.HashExtAjustavel;
import Hash.HashExtAjustavel.No;
import Huffman.Huffman;
import modelos.Condutor;
import modelos.VeiculoInt;

public class Servidor {
	private HashExtAjustavel hash;
	private static String log;

	public Servidor(String mensagem, int tam) {
		log = "";
		hash = new HashExtAjustavel(tam);
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

	public boolean cadastrarVeiculo(String comprimida) {
		String v = descomprimir(comprimida);
		VeiculoInt veiculo = montarVeiculo(v);
		
		if (veiculo != null) {
			hash.inserir(veiculo.getRenavam(), veiculo);
			veiculo = buscarVeiculo(veiculo.getRenavam());
		}

		if (veiculo != null) {
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
		// arvore.houveRotacao();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return log;
	}

	public String comprimir(VeiculoInt v) {
		try {
			Huffman hm = new Huffman();
			return hm.comprimir(v.toString(0));
		} catch (Exception e) {
			System.err.println("Erro ao comprimir veículo: " + e.getMessage());
			return null;
		}
	}

	public String descomprimir(String v) {
		try {
			Huffman hm = new Huffman();
			String descomprimida = hm.descomprimir(v);
			if(descomprimida == "") {
				
			}
			return descomprimida;
		} catch (Exception e) {
			System.err.println("Erro ao descomprimir veículo: " + e.getMessage());
			return null;
		}
	}

	private VeiculoInt montarVeiculo(String descomprimida) {
		try {
			String[] substrings = descomprimida.split(Character.toString('#'));

			// Verifique se há pelo menos dois elementos em substrings antes de acessar
			// substrings[1]
			if (substrings.length >= 6) {
				return new VeiculoInt(substrings[0], Integer.parseInt(substrings[1]),
						new Condutor(substrings[2], substrings[3]), substrings[4], Integer.parseInt(substrings[5]));
			} else {
				System.err.println("Falha na descompressão.");
				return null;
			}
		} catch (Exception e) {
			System.err.println("Erro ao montar veículo: " + e.getMessage());
			return null;
		}
	}

}
