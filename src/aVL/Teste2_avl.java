package aVL;

public class Teste2_avl {
	public static void main(String[] args) {
		ArvoreAVL av = new ArvoreAVL();
		
		av.inserir(12, "12 Brenno");
		av.inserir(23, "23 Brennok");
		av.inserir(34, "34 Brennokk");
		av.inserir(45, "45 Brennokkk");
		av.inserir(56, "56 Brennokkkk");
		av.inserir(27, "27 Brenno");
		av.inserir(5, "5 Brenno");
		//System.out.println(av.getRaiz().toString());
		av.remover(5, "5 Brenno");
		av.inserir(5, "5 tttBrenno");
		av.inserir(5, "5 wawwBrenno");
		av.inserir(13, "13 Brenno");
		av.inserir(17, "17 Brenno");
		av.inserir(18, "18 Brenno");
		av.inserir(35, "35 Brenno");
		av.inserir(41, "41 Brenno");
		av.inserir(41, "41 wwBrenno");
		av.inserir(38, "38 Brenno");
		
		av.emOrdem();
	}

}

