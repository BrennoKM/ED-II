package Hash;

import java.text.DecimalFormat;
import modelos.VeiculoInt;

public class HashExtAjustavel {
	public class No {
		Integer chave;
		No proximo;
		VeiculoInt veiculo;

		public No(Integer k, No p, VeiculoInt veiculo) {
			chave = k;
			this.veiculo = veiculo;
			proximo = p;
		}

		public VeiculoInt getValor() {
			return veiculo;
		}
	}

	Integer m;
	Integer elementos;
	No tabela[];
	double fatorCargaLimite = 2.0;

	public HashExtAjustavel(Integer tam) {
		elementos = 0;
		this.m = tam;
		tabela = new No[this.m];
	}

	public Integer hash(Integer k) {
		return k % this.m;
	}

	public double getFatorCarga() {
		double fatorCarga = (double) elementos / m;

		DecimalFormat df = new DecimalFormat("#.############");
		String formatted = df.format(fatorCarga).replace(',', '.'); // Substitui a vírgula pela ponto
		return Double.parseDouble(formatted);
	}

	public int calcularHashRenavam(int r, int tamanhoTabela) {
	    String renavam = String.valueOf(r);

	    if (renavam.length() < 8) {
	        int zerosFaltantes = 8 - renavam.length();
	        StringBuilder renavamComZeros = new StringBuilder();

	        for (int i = 0; i < zerosFaltantes; i++) {
	            renavamComZeros.append("0");
	        }

	        renavamComZeros.append(renavam);
	        renavam = renavamComZeros.toString();
	    } else if (renavam.length() > 8) {
	        renavam = renavam.substring(renavam.length() - 8); // Pegar os últimos 8 dígitos
	    }

	    String digitosIntermediarios = renavam.substring(1, 8); // Excluir o primeiro dígito

	    int[] pesos = { 2, 9, 4, 5, 6, 7 };

	    int valorHash = 0;

	    for (int i = 0; i < digitosIntermediarios.length() && i < pesos.length; i++) {
	        int digito = Character.getNumericValue(digitosIntermediarios.charAt(i));
	        valorHash += digito * pesos[i];
	    }


	    valorHash = valorHash % tamanhoTabela;

	    return valorHash;
	}


	public void inserir(int k, VeiculoInt veiculo) {
		if (getFatorCarga() >= fatorCargaLimite) {
			redimensionarTabela();
		}
		Integer c;
		//Integer c = calcularHashRenavam(k, tabela.length);
		c = hash(k);
		No no = this.tabela[c];

		while (no != null) {
			if (no.chave == k) {
				no.veiculo = veiculo;
				break;
			}
			no = no.proximo;
		}
		if (no == null) {
			no = new No(k, tabela[c], veiculo);
			tabela[c] = no;
			elementos++;
		}
	}

	public void inserirFinal(int k, VeiculoInt veiculo) {
		if (getFatorCarga() >= fatorCargaLimite) {
			redimensionarTabela();
		}

		Integer h = hash(k);

		No atual = tabela[h];
		No anterior = null;

		if (atual == null) {
			tabela[h] = new No(k, null, veiculo);
			elementos++;
		} else {
			while (atual != null) {
				if (atual.chave == k) {
					break;
				}
				anterior = atual;
				atual = atual.proximo;
			}
			if (atual == null) {
				No novo = new No(k, null, veiculo);
				anterior.proximo = novo;
				elementos++;
			}
		}
	}

	private void redimensionarTabela() {
		int novoTamanho = (int) (m * 2.0); // Redimensiona
		GeradorNumerosPrimos gnp = new GeradorNumerosPrimos();
		novoTamanho = gnp.encontrarProximoPrimo(novoTamanho);
		
		No novaTabela[] = new No[novoTamanho];

		for (int i = 0; i < m; i++) {
			No no = tabela[i];

			while (no != null) {
				int chave = no.chave;
				int novaPosicao = chave % novoTamanho;

				while (novaTabela[novaPosicao] != null) {
					novaPosicao = (novaPosicao + 1) % novoTamanho;
				}

				novaTabela[novaPosicao] = no;
				No proximoNo = no.proximo;
				no.proximo = null; // Remove o vínculo com o próximo nó
				no = proximoNo; // Kevyn gostoso
			}
		}

		tabela = novaTabela;
		m = novoTamanho;
	}

	public VeiculoInt remover(int k) {
		Integer h = hash(k);
		No no = tabela[h];
		No anterior = null;
		VeiculoInt v = null;
		while (no != null) {
			if (no.chave == k) {
				if (anterior == null) {
					v = tabela[h].getValor();
					// O elemento a ser removido é o primeiro na lista
					tabela[h] = no.proximo;
				} else {
					v = anterior.proximo.getValor();
					anterior.proximo = no.proximo;
				}
				elementos--;
				return v; // Elemento removido, saia do método
			}
			anterior = no;
			no = no.proximo;
		}
		return null;
	}

	public No buscar(int k) {
	    Integer h = hash(k);
	    No no = tabela[h];
	    No anterior = null;

	    while (no != null) {
	        if (no.chave == k) {
	            if (anterior != null) {
	                anterior.proximo = no.proximo;
	                no.proximo = tabela[h];
	                tabela[h] = no;
	            }
	            return no;
	        }
	        anterior = no;
	        no = no.proximo;
	    }
	    return null;
	}

	public void imprimir() {
		No no;

		for (int i = 0; i < m; i++) {
			no = tabela[i];
			System.out.print(i + "	= ");
			while (no != null) {
				System.out.print(no.getValor() + "	-> ");
				no = no.proximo;
			}
			System.out.println();
		}
	}

	public int getElementos() {
		return this.elementos;
	}

	public static void main(String[] args) {
		int[] chave = { 210, 213, 211, 233, 209, 300, 999, 1, 2, 3, 4, 5, 123456, 12, 0, 9, 13 };
		HashExtAjustavel tabelaHash = new HashExtAjustavel(2);

		
		
		for (int i = 1; i < chave.length; i++) {
			tabelaHash.inserir(chave[i]+10050000, null);
			//System.out.println(tabelaHash.getFatorCarga());
		}
		System.out.println(tabelaHash.calcularHashRenavam(1234698893, tabelaHash.m));
		//tabelaHash.remover(1);
		tabelaHash.imprimir();
	}

}
