package arvBin;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
	private No raiz;

	public ArvoreBinaria() {

	}

	public ArvoreBinaria(No raiz) {
		setRaiz(raiz);
	}

	public No getRaiz() {
		return raiz;
	}

	public void setRaiz(No raiz) {
		this.raiz = raiz;
	}

	public void preOrdem() {
		preOrdem(raiz);

	}
	private void preOrdem(No r) {
		if (r != null) {
			System.out.println(r.getValue());
			this.preOrdem(r.getLeft());
			this.preOrdem(r.getRight());
		}
	}

	public void emOrdem() {
		emOrdem(raiz);

	}

	private void emOrdem(No r) {
		if (r != null) {
			this.emOrdem(r.getLeft());
			System.out.println(r.getValue());
			this.emOrdem(r.getRight());
		}
	}

	public void posOrdem() {
		posOrdem(raiz);

	}

	private void posOrdem(No r) {
		if (r != null) {
			this.posOrdem(r.getLeft());
			this.posOrdem(r.getRight());
			System.out.println(r.getValue());
		}
	}

	public void emLargura() {
		emLargura(raiz);
	}

	private void emLargura(No r) {
		No atual;

		if (r != null) {
			Queue<No> fila = new LinkedList<No>();
			fila.add(r);

			while (fila.isEmpty() == false) {
				atual = fila.remove();
				System.out.println(atual.getValue());

				if (atual.getLeft() != null) {
					fila.add(atual.getLeft());
				}

				if (atual.getRight() != null) {
					fila.add(atual.getRight());
				}

			}
		}
	}

	public int countNode() {
		return this.countNode(raiz);
	}

	private int countNode(No r) {
		if (r == null) {
			return 0;
		} else {
			return 1 + countNode(r.getLeft()) + countNode(r.getRight());
		}

	}

	public int countLeaf() {
		return this.countLeaf(raiz);
	}

	private int countLeaf(No r) {
		if (r == null) {
			return 0;
		} else if (r.getLeft() == null && r.getRight() == null) {
			return 1;
		}
		return countLeaf(r.getLeft()) + countLeaf(r.getRight());

	}

	public void inserir(int chave) {
		this.setRaiz(this.inserir(getRaiz(), chave));
		// this.raiz = inserir(raiz, chave);
	}

	private No inserir(No no, int chave) {
		if (no == null) {
			no = new No(chave);
		}
		if (chave < no.getValue()) {
			no.setLeft(this.inserir(no.getLeft(), chave));
			// no.left = this.inserir(no.getLeft(), chave);
		}
		if (chave > no.getValue()) {
			no.setRight(this.inserir(no.getRight(), chave));
			// no.right = this.inserir(no.getRight(), chave);
		}
		return no;
	}

	public static No buscarNo(ArvoreBinaria ab, int chave) {
		No resultado = ab.buscar(chave);
		if (resultado == null) {
			System.out.println(chave + " Não está na arvore");
		} else {
			System.out.println(chave + " Está na arvore");

		}
		return resultado;
	}
	
	public No buscar(int chave) {
		return this.buscar(this.getRaiz(), chave);
	}

	private No buscar(No no, int chave) {
		if (no == null) {
			return null;
		} else if (chave < no.getValue()) {
			return this.buscar(no.getLeft(), chave);
		} else if (chave > no.getValue()) {
			return this.buscar(no.getRight(), chave);
		} else {
			return no;
		}
	}

	public void remover(int chave) {
		this.raiz = this.remover(raiz, chave);
	}

	private No remover(No no, int chave) {
		if (no == null) {
			return null;
		} else if (chave < no.getValue()) {
			no.setLeft(this.remover(no.getLeft(), chave));
		} else if (chave > no.getValue()) {
			no.setRight(this.remover(no.getRight(), chave));
		} else {
			// achou o nó

			// caso 1 - nó sem filhos (é folha)
			if (no.getLeft() == null && no.getRight() == null) {
				no = null;
			} else if (no.getLeft() == null) { // caso 2 - filhos a direita
				No temp = no;
				no = temp.getRight();
				temp = null;
			} else if (no.getRight() == null) { // caso 2 - filhos a esquerda
				No temp = no;
				no = temp.getLeft();
				temp = null;
			} else { // caso 3 - filhos em ambos lados

				No temp = no.getLeft();
				while (temp.getRight() != null) {
					temp = temp.getRight();
				}

				no.setValue(temp.getValue());
				temp.setValue(chave);
				no.setLeft(this.remover(no.getLeft(), temp.getValue()));
				//No n = buscarNo(new ArvoreBinaria(no.getLeft()), chave);
				//System.out.println("aawddawdawd" + temp.getValue());
			}
		}
		return no;
	}

}