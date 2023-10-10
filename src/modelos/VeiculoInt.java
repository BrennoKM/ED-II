package modelos;

public class VeiculoInt {
    private String placa;
    private Integer renavam;
    private Condutor condutor;
    private String modelo;
    private int ano;

    public VeiculoInt(String placa, Integer renavam, Condutor condutor, String modelo, int ano) {
        this.placa = placa;
        this.renavam = renavam;
        this.condutor = condutor;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public Integer getRenavam() {
        return renavam;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public String getModelo() {
        return modelo;
    }

    public int getDataFabricacao() {
        return ano;
    }
    
    public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public void setRenavam(Integer renavam) {
		this.renavam = renavam;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String toString(int i) {
		return placa +"#"+renavam+"#"+condutor.getNome()+"#"+condutor.getCpf()+"#"+modelo+"#"+ano;
	}

	public String toString() {
        return "Veiculo{" +
               "placa='" + placa + '\'' +
               ", renavam='" + renavam + '\'' +
               ", condutor{" + condutor +
               "}, modelo='" + modelo + '\'' +
               ", ano=" + ano +
               '}';
    }
}

