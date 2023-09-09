package Hash;

import java.text.DecimalFormat;

import modelos.VeiculoInt;

public class HashColisaoEndAberto {
	public class No{
		int chave;
		VeiculoInt veiculo;
		
		public No (VeiculoInt veiculo) {
			this.veiculo = veiculo;
		}
		
		public VeiculoInt getValor() {
			return veiculo;
		}
	}
	int m;
	int elementos;
	No tabela[];
	double fatorCargaLimite = 0.9;
	
	public HashColisaoEndAberto(int tam){
		elementos=0;
		this.m = tam;
		tabela = new No[m];
	}
	
	public int hash(int ch) {
		return ch%m;
	}
	
	public double getFatorCarga() {
        double fatorCarga = (double) elementos / m;
        
        DecimalFormat df = new DecimalFormat("#.############");
        String formatted = df.format(fatorCarga).replace(',', '.'); // Substitui a vírgula pela ponto
        return Double.parseDouble(formatted);
    }
	
	public void inserir (int v, VeiculoInt veiculo) {
		if (getFatorCarga() >= fatorCargaLimite) {
            redimensionarTabela();
        }
		
		int h = hash(v);
		
		while (tabela[h] != null) {
			if(tabela[h].chave ==  v) {
				break;
			}
			h = (h+1) % m;
		}
		if(tabela[h] == null) {
			tabela[h] = new No(veiculo);
			tabela[h].chave=v;
			elementos++;
		}
	}
	
	private void redimensionarTabela() {
		GeradorNumerosPrimos gnp = new GeradorNumerosPrimos();
	    int novoTamanho = gnp.encontrarProximoPrimo(m * 2); // Redimensiona para um tamanho que seja pelo menos duas vezes maior
	    No novaTabela[] = new No[novoTamanho];

	    for (int i = 0; i < m; i++) {
	        if (tabela[i] != null) {
	            int chave = tabela[i].chave;
	            int novaPosicao = chave % novoTamanho;

	            while (novaTabela[novaPosicao] != null) {
	                novaPosicao = (novaPosicao + 1) % novoTamanho;
	            }

	            novaTabela[novaPosicao] = tabela[i];
	        }
	    }

	    tabela = novaTabela;
	    m = novoTamanho;
	}
	
	public VeiculoInt remover(int chave) {
        int h = hash(chave);

        while (tabela[h] != null) {
            if (tabela[h].chave == chave) {
            	VeiculoInt v = tabela[h].getValor();
                tabela[h] = null; 
                elementos--;
                return v;
            }
            h = (h + 1) % m;
        }
        return null;
    }
	
	public No buscar (int v) {
		int h = hash(v);
		while (tabela[h] != null) {
			if(tabela[h].chave ==  v) {
				return tabela[h];
			}
			h = (h+1) % m;
		}
		return null;
	}
	
	public void imprimir() {
		for(int i = 0; i < m; i++) {
			if(tabela[i] != null) {
				System.out.println(i + "	-> " + tabela[i].getValor());
			} else {
				System.out.println(i);
			}
		}
	}
	
	public int getElementos() {
		return elementos;
	}
	
	public static void main(String[] args) {
		//int[] chave = { 210, 213, 211, 233, 209, 300, 999, 1, 2, 3, 4, 5, 123456, 12, 0, 9, 13};
		int[] chave = {46, 46, 49, 68, 71, 97, 26, 72, 27};
		
		HashColisaoEndAberto tabelaHash = new HashColisaoEndAberto(23);
		
		for (int i = 0; i < chave.length; i++) {
			tabelaHash.inserir(chave[i], null);
		}
		tabelaHash.remover(46);
		tabelaHash.imprimir();
	}

	
}
