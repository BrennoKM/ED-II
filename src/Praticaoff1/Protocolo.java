package Praticaoff1;

import modelos.Veiculo;

public class Protocolo {
	Servidor servidor = new Servidor("Servidor iniciado.");
	
	public Protocolo(String mensagem) {
		System.out.println(mensagem);
	}
	
	public Veiculo buscarVeiculos(String renavam) {
		return servidor.buscarVeiculo(renavam);
	}
	
	public boolean cadastrarVeiculo(Veiculo veiculo) {
		return servidor.cadastrarVeiculo(veiculo);
	}

	public void removerVeiculo(Veiculo veiculo) {
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

	
}
