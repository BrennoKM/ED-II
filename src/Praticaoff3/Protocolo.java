package Praticaoff3;

import Huffman.Huffman;
import modelos.Condutor;
import modelos.VeiculoInt;

public class Protocolo {
	Servidor servidor;

	public Protocolo(String mensagem, int tam) {
		servidor = new Servidor("Servidor iniciado.", tam);
		System.out.println(mensagem);
	}

	public String buscarVeiculos(int renavam) {
		return comprimir(servidor.buscarVeiculo(renavam));
	}

	public boolean cadastrarVeiculo(VeiculoInt veiculo) {
		return servidor.cadastrarVeiculo(comprimir(veiculo));
	}

	public void removerVeiculo(VeiculoInt veiculo) {
		servidor.removerVeiculo(veiculo);
	}

	public void listarVeiculos() {
		servidor.listarVeiculos();
	}

	public int acessarQntVeiculos() {
		return servidor.acessarQntVeiculos();
	}

	public String encerrandoConexao() {
		System.out.println("Protocolo encerrado.");
		return servidor.encerrandoConexao();
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
