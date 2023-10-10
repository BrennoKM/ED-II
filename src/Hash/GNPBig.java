package Hash;

import java.math.BigInteger;

public class GNPBig {

	public static void main(String[] args) {
		BigInteger numeroAtual = new BigInteger("2"); // Iniciamos a busca a partir do número 2, o menor número primo
		GNPBig gnp = new GNPBig();

		System.out.println("Números primos:");

		BigInteger limite = new BigInteger("2147403633");

		while (numeroAtual.compareTo(limite) <= 0) {
			if (gnp.ePrimo(numeroAtual)) {
				System.out.println(numeroAtual);
			}
			numeroAtual = numeroAtual.add(BigInteger.ONE);
		}
	}

	// Método para verificar se um número é primo
	public boolean ePrimo(BigInteger numero) {
		if (numero.compareTo(BigInteger.ONE) <= 0) {
			return false;
		}
		if (numero.compareTo(new BigInteger("3")) <= 0) {
			return true;
		}
		if (numero.mod(new BigInteger("2")).equals(BigInteger.ZERO)
				|| numero.mod(new BigInteger("3")).equals(BigInteger.ZERO)) {
			return false;
		}

		BigInteger i = new BigInteger("5");
		while (i.multiply(i).compareTo(numero) <= 0) {
			if (numero.mod(i).equals(BigInteger.ZERO)
					|| numero.mod(i.add(new BigInteger("2"))).equals(BigInteger.ZERO)) {
				return false;
			}
			i = i.add(new BigInteger("6"));
		}

		return true;
	}

	public BigInteger encontrarProximoPrimo(BigInteger numero) {
		numero = numero.add(BigInteger.ONE);
		while (true) {
			if (ePrimo(numero)) {
				return numero;
			}
			numero = numero.add(BigInteger.ONE);
		}
	}
}