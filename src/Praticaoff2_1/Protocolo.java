package Praticaoff2_1;

import modelos.VeiculoInt;

public class Protocolo {
	Servidor servidor; 
	
	public Protocolo(String mensagem, int tam) {
		servidor = new Servidor("Servidor iniciado.", tam);
		System.out.println(mensagem);
	}
	
	public VeiculoInt buscarVeiculos(int renavam) {
		return servidor.buscarVeiculo(renavam);
	}
	
	public boolean cadastrarVeiculo(VeiculoInt veiculo) {
		return servidor.cadastrarVeiculo(veiculo);
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

	
}
