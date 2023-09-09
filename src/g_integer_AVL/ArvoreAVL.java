package g_integer_AVL;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreAVL<T> {

	No<T> raiz;

	public ArvoreAVL() {
		this.setRaiz(null);
	}

	public No<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(No<T> raiz) {
		this.raiz = raiz;
	}

	// pegar altura de um nó avl

	private Integer altura(No<T> no) {

		if (no == null)
			return -1;

		return no.getAlturaNo();

	}

	private Integer maior(Integer a, Integer b) {

		if (a < b) {

			return b;

		} else {

			return a;

		}

	}

	private Integer obterFB(No<T> no) {

		if (no == null)
			return 0;

		return this.altura(no.getEsq()) - this.altura(no.getDir());
	}

	public void inserir(Integer chave, T valor) {
		this.raiz = this.inserir(getRaiz(), chave, valor);
	}

	private No<T> inserir(No<T> no, Integer chave, T valor) {

		if (no == null) {
			return new No<T>(chave, valor);
		}
		if (chave < no.chave) {
			no.esq = this.inserir(no.esq, chave, valor);
		} else if (chave > no.chave) {
			no.dir = this.inserir(no.dir, chave, valor);
		} else {
			no.setValor(valor);
			return no;
		}
		/* 2. Atualiza altura do ancestral do nó inserido */

		no.alturaNo = 1 + this.maior(this.altura(no.getEsq()), this.altura(no.getDir()));

		/* 3. Obter FB */

		int fb = this.obterFB(no);
		int fbEsq = this.obterFB(no.getEsq());
		int fbDir = this.obterFB(no.getDir());

		if (fb > 1 && fbEsq >= 0)

			return this.rds(no);

		if (fb > 1 && fbEsq < 0) {

			no.esq = this.res(no.esq);
			return rds(no);
		}

		if (fb < -1 && fbDir <= 0)

			return this.res(no);

		if (fb < -1 && fbDir > 0) {

			no.dir = this.rds(no.dir);
			return res(no);
		}

		return no;
	}

	private No<T> res(No<T> x) {

		No<T> y = x.getDir();
		No<T> z = y.getEsq();

		// executa rotação

		y.setEsq(x);
		x.setDir(z);

		x.alturaNo = 1 + this.maior(altura(x.getEsq()), altura(x.getDir()));
		y.alturaNo = 1 + this.maior(altura(y.getEsq()), altura(y.getDir()));

		return y;
	}

	private No<T> rds(No<T> y) {

		No<T> x = y.getEsq();
		No<T> z = x.getDir();

		// executa rotação

		x.setDir(y);
		y.setEsq(z);
		;

		y.alturaNo = 1 + this.maior(altura(y.getEsq()), altura(y.getDir()));
		x.alturaNo = 1 + this.maior(altura(x.getEsq()), altura(x.getDir()));

		return x;
	}

	public void remover(Integer chave, T valor) {
		setRaiz(remover(getRaiz(), chave, valor));
	}

	private No<T> remover(No<T> no, Integer chave, T valor) {
		if (no == null) {
			return no;
		}
		if (chave < no.getChave()) {
			no.setEsq(remover(no.getEsq(), chave, valor));
		}
		if (chave > no.getChave()) {
			no.setDir(remover(no.getDir(), chave, valor));
		}
		if (chave == no.getChave()) {
			// nó sem filhos
			if (no.getEsq() == null && no.getDir() == null) {
				no = null;
			}
			// nó com filhos a direita
			else if (no.getEsq() == null) {//
				No<T> temp = no;
				no = temp.getDir();
				temp = null;
			}
			// nó com filhos a esquerda
			else if (no.getDir() == null) {

				No<T> temp = no;
				no = temp.getEsq();
				temp = null;
			} else {
				// nó com filhos pra ambos lados
				No<T> temp = this.noMenorChave(no.getDir());
				no.setChave(temp.getChave());
				temp.setChave(chave);
				no.setDir(this.remover(no.getDir(), chave, valor));
			}

		}
		if (no == null) {
			return no;
		}
		// Atualiza altura do ancestral
		System.out.println('1');
		no.setAlturaNo(1 + maior(altura(no.getEsq()), altura(no.getDir())));
		System.out.println('2');
		// Fator de balanceamento

		int fb = this.obterFB(no);
		int fbSubEsq = this.obterFB(no.getEsq());
		int fbSubDir = this.obterFB(no.getDir());

		if (fb > 1 && fbSubEsq >= 0) {

			return this.rds(no);
		}
		if (fb > 1 && fbSubEsq < 0) {

			no.esq = this.res(no.esq);
			return rds(no);
		}

		if (fb < -1 && fbSubDir <= 0) {

			return this.res(no);
		}
		if (fb < -1 && fbSubDir > 0) {

			no.dir = this.rds(no.dir);
			return res(no);
		}

		return no;
	}

	private No<T> noMenorChave(No<T> no) {
		No<T> temp = no;
		if (temp == null) {
			return null;
		}
		while (temp.getEsq() != null) {
			temp = temp.getEsq();
		}
		return temp;
	}

	public void preOrdem() {
		preOrdem(raiz);

	}

	private void preOrdem(No<T> r) {
		if (r != null) {
			System.out.println(r.getValor());
			this.preOrdem(r.getEsq());
			this.preOrdem(r.getDir());
		}
	}

	public void emOrdem() {
		emOrdem(raiz);

	}

	private void emOrdem(No<T> no) {
		if (no != null) {
			this.emOrdem(no.getEsq());
			System.out.println(no.getChave() + ": " + no.getValor());
			this.emOrdem(no.getDir());
		}
	}

	public void posOrdem() {
		posOrdem(raiz);

	}

	private void posOrdem(No<T> r) {
		if (r != null) {
			this.posOrdem(r.getEsq());
			this.posOrdem(r.getDir());
			System.out.println(r.getValor());
		}
	}

	public void emLargura() {
		emLargura(raiz);
	}

	private void emLargura(No<T> r) {
		No<T> atual;

		if (r != null) {
			Queue<No<T>> fila = new LinkedList<No<T>>();
			fila.add(r);

			while (fila.isEmpty() == false) {
				atual = fila.remove();
				System.out.println(atual.getValor());

				if (atual.getEsq() != null) {
					fila.add(atual.getEsq());
				}

				if (atual.getDir() != null) {
					fila.add(atual.getDir());
				}

			}
		}
	}

	public int contarNos() {
		return this.contarNos(raiz);
	}

	private int contarNos(No<T> no) {
		if (no == null) {
			return 0;
		} else {
			return 1 + contarNos(no.getEsq()) + contarNos(no.getDir());
		}

	}

	public int contarFolhas() {
		return this.contarFolhas(getRaiz());
	}

	private int contarFolhas(No<T> no) {
		if (no == null) {
			return 0;
		} else if (no.getEsq() == null && no.getDir() == null) {
			return 1;
		}
		return contarFolhas(no.getEsq()) + contarFolhas(no.getDir());

	}
}
