package Huffman;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import modelos.Condutor;
import modelos.VeiculoInt;

class HuffmanNo implements Comparable<HuffmanNo> {
    char caractere;
    int freq;
    HuffmanNo esq;
    HuffmanNo dir;

    public HuffmanNo(char caractere, int frequencia) {
        this.caractere = caractere;
        this.freq = frequencia;
    }

    public int compareTo(HuffmanNo outro) {
        return this.freq - outro.freq;
    }
}

public class Huffman {
    Map<Character, String> mapaCodificacao = new HashMap<>();
    Map<String, Character> mapaDecodificacao = new HashMap<>();

    public String comprimir(String entrada) {
        HashMap<Character, Integer> mapaFrequencia = new HashMap<>();
        PriorityQueue<HuffmanNo> minHeap = new PriorityQueue<>();

        for (char c : entrada.toCharArray()) {
            mapaFrequencia.put(c, mapaFrequencia.getOrDefault(c, 0) + 1);
        }

        for (char c : mapaFrequencia.keySet()) {
            minHeap.add(new HuffmanNo(c, mapaFrequencia.get(c)));
        }

        HuffmanNo raiz = construirArvoreHuffman(minHeap);

        gerarCodigosHuffman(raiz, "");

        StringBuilder comprimida = new StringBuilder();
        for (char c : entrada.toCharArray()) {
            comprimida.append(mapaCodificacao.get(c));
        }

        return serializarArvore(raiz) + "|" + comprimida.toString();
    }

    private HuffmanNo construirArvoreHuffman(PriorityQueue<HuffmanNo> minHeap) {
        while (minHeap.size() > 1) {
            HuffmanNo esquerda = minHeap.poll();
            HuffmanNo direita = minHeap.poll();

            HuffmanNo nodoMergulhado = new HuffmanNo('\0', esquerda.freq + direita.freq);
            nodoMergulhado.esq = esquerda;
            nodoMergulhado.dir = direita;

            minHeap.add(nodoMergulhado);
        }
        return minHeap.poll();
    }

    private void gerarCodigosHuffman(HuffmanNo nodo, String codigo) {
        if (nodo == null) {
            return;
        }

        if (nodo.caractere != '\0') {
            mapaCodificacao.put(nodo.caractere, codigo);
            mapaDecodificacao.put(codigo, nodo.caractere);
        }

        gerarCodigosHuffman(nodo.esq, codigo + "0");
        gerarCodigosHuffman(nodo.dir, codigo + "1");
    }

    public String descomprimir(String comprimida) {
    	String descomprimida = null;
    	if(comprimida!=null) {
        int separadorArvore = comprimida.indexOf('|');
        String arvoreSerializada = comprimida.substring(0, separadorArvore);
        String mensagemComprimida = comprimida.substring(separadorArvore + 1);
    	
        
        HuffmanNo raiz = desserializarArvore(arvoreSerializada);

        descomprimida = descomprimirUsandoArvore(raiz, mensagemComprimida);
    	}
        return descomprimida;
    }

    private String descomprimirUsandoArvore(HuffmanNo raiz, String mensagemComprimida) {
        StringBuilder descomprimida = new StringBuilder();
        HuffmanNo noAtual = raiz;

        for (char bit : mensagemComprimida.toCharArray()) {
            if (bit == '0' && noAtual != null) {
                noAtual = noAtual.esq;
            } else if (bit == '1' && noAtual != null) {
                noAtual = noAtual.dir;
            }

            if (noAtual != null && noAtual.caractere != '\0') {
                descomprimida.append(noAtual.caractere);
                noAtual = raiz;
            }
        }

        return descomprimida.toString();
    }


    public String serializarArvore(HuffmanNo raiz) {
        StringBuilder arvoreSerializada = new StringBuilder();
        serializarArvoreAux(raiz, arvoreSerializada);
        return arvoreSerializada.toString();
    }

    private void serializarArvoreAux(HuffmanNo no, StringBuilder arvoreSerializada) {
        if (no == null) {
            arvoreSerializada.append("N"); // Usar "N" para representar um nó nulo
            return;
        }

        arvoreSerializada.append(no.caractere); // Adicionar caractere do nó
        serializarArvoreAux(no.esq, arvoreSerializada); // Recursivamente serializar a subárvore esquerda
        serializarArvoreAux(no.dir, arvoreSerializada); // Recursivamente serializar a subárvore direita
    }

    public HuffmanNo desserializarArvore(String arvoreSerializada) {
        Queue<Character> fila = new LinkedList<>();
        for (char c : arvoreSerializada.toCharArray()) {
            fila.offer(c);
        }
        return desserializarArvoreAux(fila);
    }

    private HuffmanNo desserializarArvoreAux(Queue<Character> fila) {
        char c = fila.poll();

        if (c == 'N') {
            return null; // Nó nulo
        }

        HuffmanNo no = new HuffmanNo(c, 0);
        no.esq = desserializarArvoreAux(fila);
        no.dir = desserializarArvoreAux(fila);

        return no;
    }

    public static void main(String[] args) {
        Huffman h = new Huffman();
        Huffman m = new Huffman();
        
        VeiculoInt v = new VeiculoInt("POG3R32", 12344, new Condutor("Icaro", "000.999.333-77"), "Kwid", 2020);
        String entrada = "UVF7U88#37818126#Arthur#529.666.940-66#Modelo21#2000";
        entrada = v.toString(0);
        
        //entrada = "37818126";
        System.out.println("String de entrada: " + entrada);

        String comprimida = h.comprimir(entrada);
        System.out.println("String comprimida: " + comprimida);

        String descomprimida = h.descomprimir(comprimida);
        System.out.println("String descomprimida: " + descomprimida);

        comprimida = h.comprimir(entrada);
        System.out.println("\nString comprimida: " + comprimida);

        System.out.println("String descomprimida: " + m.descomprimir(comprimida));
        
        System.out.println("\n\n");
        String[] substrings = descomprimida.split(Character.toString('#'));
        
        for (String substring : substrings) {
            System.out.println(substring);
        }
    }
}
