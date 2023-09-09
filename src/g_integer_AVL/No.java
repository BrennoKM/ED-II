package g_integer_AVL;

public class No<T> implements Comparable<Integer> {

	Integer chave;
	T valor;
	Integer alturaNo;
	No<T> esq, dir;
	
	public No(Integer chave, T valor) {
		
		this.setChave(chave);
		this.setValor(valor);
		this.setAlturaNo(0);
		this.setEsq(null);
		this.setDir(null);
		
	}
	
	public Integer getChave() {
		return chave;
	}

	public void setChave(Integer chave) {
		this.chave = chave;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public Integer getAlturaNo() {
		return alturaNo;
	}

	public void setAlturaNo(Integer alturaNo) {
		this.alturaNo = alturaNo;
	}

	public No<T> getEsq() {
		return esq;
	}

	public void setEsq(No<T> esq) {
		this.esq = esq;
	}

	public No<T> getDir() {
		return dir;
	}

	public void setDir(No<T> dir) {
		this.dir = dir;
	}

	@Override
	public int compareTo(Integer chave) {

		if(this.getChave() < chave)
			return -1;
		if(this.getChave() > chave)
			return 1;
		
		return 0;
	}

}
