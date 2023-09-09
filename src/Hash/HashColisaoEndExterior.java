package Hash;

import java.text.DecimalFormat;

import modelos.VeiculoInt;

public class HashColisaoEndExterior {
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

    public HashColisaoEndExterior(Integer tam) {
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

    public void inserir(int k, VeiculoInt veiculo) {
        if (getFatorCarga() >= fatorCargaLimite) {
            redimensionarTabela();
        }

        Integer c = this.hash(k);
        No no = this.tabela[c];

        while (no != null) {
            if (no.chave == k) {
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
                no = proximoNo;
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

        while (no != null) {
            if (no.chave == k) {
                return no;
            }
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
        HashColisaoEndExterior tabelaHash = new HashColisaoEndExterior(2);

        for (int i = 0; i < chave.length; i++) {
            tabelaHash.inserir(chave[i], null);
            System.out.println(tabelaHash.getFatorCarga());
        }

        tabelaHash.remover(1);
        tabelaHash.imprimir();
    }

	
}
