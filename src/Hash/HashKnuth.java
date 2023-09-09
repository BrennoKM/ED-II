package Hash;

import java.util.Iterator;

public class HashKnuth {
	public static void main(String[]args) {
		int[] chave = {210, 213, 211, 233, 209, 300, 999, 1, 2, 3, 4 ,5, 123456};
		
		for (int i = 0; i < chave.length; i++) {
			System.out.println(chave[i] + "	->	" + hashMultiplicacao(chave[i], 1024));
		}
	}
	static int hashMultiplicacao(int k, int m) {
		// h(k) = [m((k*a) mod 1 )]
		
		double a = (Math.sqrt(5)-1)/2;
		double produto = k * a;
		produto = produto % 1;
		return (int) (produto*m);
	}
}
