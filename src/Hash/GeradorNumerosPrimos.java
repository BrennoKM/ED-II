package Hash;


import java.math.BigInteger;
public class GeradorNumerosPrimos {

    public static void main(String[] args) {
        long numeroAtual = 2; // Iniciamos a busca a partir do número 2, o menor número primo
        GeradorNumerosPrimos gnp = new GeradorNumerosPrimos();
        
        System.out.println("Números primos:");

        numeroAtual = 2147403633;
        
        while (true) {
            if (gnp.ePrimo(numeroAtual)) {
                System.out.print(numeroAtual + "\n");
            }
            numeroAtual++;
        }
    }

    // Método para verificar se um número é primo
    public boolean ePrimo(long numero) {
        if (numero <= 1) {
            return false;
        }
        if (numero <= 3) {
            return true;
        }
        if (numero % 2 == 0 || numero % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= numero; i += 6) {
            if (numero % i == 0 || numero % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }
    
    public int encontrarProximoPrimo(int numero) {
        int proximoPrimo = numero + 1;
        while (true) {
            if (ePrimo(proximoPrimo)) {
                return proximoPrimo;
            }
            proximoPrimo++;
        }
    }
}