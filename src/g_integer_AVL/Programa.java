package g_integer_AVL;

public class Programa {
	public static void main(String[] args) {
	
		ArvoreAVL<Integer> avl = new ArvoreAVL<Integer>();
		
		avl.inserir(21, 1);
		avl.inserir(24, 1);
		avl.inserir(18, 2);
		avl.inserir(19, 2);
		avl.inserir(15, 1);
		//avl.inserir(12, "12");
		
		avl.emOrdem();
		
	}
}
