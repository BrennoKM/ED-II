package g_string_AVL;

public class No<T> implements Comparable<String> {

	private String chave;
	private T valor;
	private Integer alturaNo;
	private No<T> esq, dir;
	
	public No(String chave, T valor) {
		this.setChave(chave);
		this.setValor(valor);
		this.setAlturaNo(0);
		this.setEsq(null);
		this.setDir(null);
		
	}
	
	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
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

	public int compareTo(String chave) {
	    return this.getChave().compareTo(chave);
	}

}
