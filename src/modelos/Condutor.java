package modelos;

public class Condutor {
    private String nome;
    private String cpf;

    public Condutor(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
    
    public String toString() {
        return "nome='" + nome + "', cpf='" + cpf + "'";
    }
}
