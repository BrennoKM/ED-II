package arvBin;

public class Main {
	public static void main(String[] args) {
		No no7 = new No(7);
		No no10 = new No(10);
		No no16 = new No(16);
		
		No no4 = new No(4);
		No no9 = new No(9, no7, no10);
		No no17 = new No(17, no16, null);
		
		No no6 = new No(6, no4, no9);
		No no13 = new No(13, null, no17);
		
		No no11 = new No(11, no6, no13);
		
		ArvoreBinaria ab = new ArvoreBinaria();
		//ab.setRaiz(no11);
		
		ab.inserir(60);
		ab.inserir(50);
		ab.inserir(70);
		ab.inserir(69);
		ab.inserir(80);
		ab.inserir(12);
		ab.inserir(13);
		ab.inserir(1);
		ab.inserir(-1090);
		
		No nobusca = ArvoreBinaria.buscarNo(ab, 666);
		System.out.println("teste");
		ab.emOrdem();
		ab.remover(60);
		System.out.println("teste2");
		ab.emOrdem();
		
		
		System.out.println("=== Percurso em pre-ordem ===");
		ab.preOrdem();
		System.out.println();
				
		System.out.println("=== Percurso em ordem ===");
		ab.emOrdem();
		System.out.println();
		
		System.out.println("=== Percurso em pos-ordem ===");
		ab.posOrdem();
		System.out.println();
		
		System.out.println("=== Percurso em largura ===");
		ab.emLargura();
		System.out.println();
		
		System.out.println("=== Contando Nós ===");
		System.out.println("Número de nós: " + ab.countNode());
		System.out.println();
		
		System.out.println("=== Contando Folhas ===");
		System.out.println("Número de folhas: " + ab.countLeaf());
		System.out.println();
	}
}