package Praticaoff1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import modelos.Condutor;
import modelos.Veiculo;

public class Cliente {
	Scanner scanner = new Scanner(System.in);
	Protocolo protocolo = new Protocolo("Protocolo Cliente-Servidor iniciado!!");

	public Cliente(String mensagem) {
		System.out.println(mensagem);
	}

	private void acessarQntVeiculos() {
		System.out.println("\nQuantidade de veiculos cadastrados: " + protocolo.acessarQntVeiculos());
	}

	private void removerVeiculo() {
		Veiculo veiculo = buscarVeiculo();
		if (veiculo != null) {
			protocolo.removerVeiculo(veiculo);
			System.out.println("\nVeiculo removido!");
		}

	}

	private void alterarVeiculo() {

		System.out.println("Informe o renavam do veículo: ");
		String renavam = scanner.nextLine();

		Veiculo veiculo = protocolo.buscarVeiculos(renavam);
		if (veiculo != null) {
			int opcao = -1;
			while (opcao != 0) {
				System.out.println("\nSelecione o que deseja alterar: " + veiculo);
				System.out.println("1. Placa");
				System.out.println("2. Nome do condutor");
				System.out.println("3. CPF do condutor");
				System.out.println("4. Modelo");
				System.out.println("5. Ano");
				System.out.println("0. Parar alterações");

				boolean entradaValida2 = false;
				while (!entradaValida2) {
					try {
						opcao = scanner.nextInt();
						entradaValida2 = true;
					} catch (InputMismatchException e) {
						System.out.println("Entrada inválida. Por favor, forneça uma entrada válida.");
						scanner.nextLine(); // Limpar o buffer do scanner
					}
				}
				switch (opcao) {
				case 1:
					System.out.println("Digite a nova placa: ");
					scanner.nextLine();
					veiculo.setPlaca(scanner.nextLine());
					break;
				case 2:
					System.out.println("Digite o novo nome do condutor: ");
					scanner.nextLine();
					veiculo.setCondutor(new Condutor(scanner.nextLine(), veiculo.getCondutor().getCpf()));
					break;
				case 3:
					System.out.println("Digite o CPF do novo condutor: ");
					scanner.nextLine();
					veiculo.setCondutor(new Condutor(veiculo.getCondutor().getNome(), scanner.nextLine()));
					break;
				case 4:
					System.out.println("Digite o novo modelo: ");
					scanner.nextLine();
					veiculo.setModelo(scanner.nextLine());
					break;
				case 5:
					System.out.println("Digite o novo ano: ");
					scanner.nextLine();
					boolean entradaValida3 = false;
					int ano = 0;
					while (!entradaValida3) {
						try {
							ano = scanner.nextInt();
							entradaValida3 = true;
						} catch (InputMismatchException e) {
							System.out.println("Entrada inválida. Por favor, forneça uma entrada válida.");
							scanner.nextLine(); // Limpar o buffer do scanner
						}
					}
					veiculo.setAno(ano);
					break;
				case 0:
					cadastrarVeiculo(veiculo);
					break;
				default:
					System.out.println("Opção desconhecida!!");
					break;
				}

			}
			// cadastrarVeiculo(criarVeiculo(renavam));
			// System.out.println("O veiculo está no banco de dados! " + veiculo);
			// System.out.println(veiculo);
		} else {
			System.out.println("Veículo não encontrado");
		}
	}

	private void listarVeiculos() {
		protocolo.listarVeiculos();
	}

	private Veiculo criarVeiculo() {

		String nomeCondutor = "";
		String cpfCondutor = "";
		String placa = "";
		String renavam = "";
		String modelo = "";
		int ano = 0;

		System.out.println("Nome do condutor: ");
		nomeCondutor = scanner.nextLine();

		System.out.println("CPF do condutor: ");
		cpfCondutor = scanner.nextLine();

		System.out.println("Placa do veículo: ");
		placa = scanner.nextLine();

		System.out.println("Renavam do veículo: ");
		renavam = scanner.nextLine();

		System.out.println("Modelo do veículo: ");
		modelo = scanner.nextLine();

		boolean entradaValida = false;
		while (!entradaValida) {
			try {
				System.out.println("Ano do veículo: ");
				ano = scanner.nextInt();
				entradaValida = true;
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, forneça uma entrada válida.");
				scanner.nextLine(); // Limpar o buffer do scanner
			}
		}
		Condutor condutor = new Condutor(nomeCondutor, cpfCondutor);
		Veiculo veiculo = new Veiculo(placa, renavam, condutor, modelo, ano);

		return veiculo;

	}

	private void cadastrarVeiculo(Veiculo veiculo) {

		boolean req = protocolo.cadastrarVeiculo(veiculo);

		if (req == true) {
			System.out.println("O veiculo foi cadastrado/atualizado");
		} else {
			System.out.println("O veiculo não foi cadastrado/atualizado");
		}
	}

	private Veiculo buscarVeiculo() {
		System.out.println("Informe o renavam do veículo: ");
		String renavam = scanner.nextLine();

		Veiculo veiculo = protocolo.buscarVeiculos(renavam);
		if (veiculo != null) {
			return veiculo;
			// System.out.println(veiculo);
		} else {
			System.out.println("Veículo não encontrado");
			return null;

		}

	}
	
	private String encerrandoConexao() {
		System.out.println("Cliente desconectado.");
		return protocolo.encerrandoConexao();
		
	}


	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = new Cliente("Cliente iniciado.\n");

		cliente.iniciar50();

		int opcao = -1;
		while (opcao != 0) {
			scanner.nextLine();
			System.out.println("\nEscolha uma operação:");
			System.out.println("1. Buscar veículo");
			System.out.println("2. Cadastrar veículo");
			System.out.println("3. Listar veículos");
			System.out.println("4. Alterar veículo");
			System.out.println("5. Remover veículo");
			System.out.println("6. Acessar quantidade de veículos");
			System.out.println("0. Sair\n");

			boolean entradaValida = false;
			while (!entradaValida) {
				try {
					opcao = scanner.nextInt();
					entradaValida = true;
				} catch (InputMismatchException e) {
					System.out.println("Entrada inválida. Por favor, forneça uma entrada válida.");
					scanner.nextLine(); // Limpar o buffer do scanner
				}
			}

			if (opcao == 0) {
				System.out.println(cliente.encerrandoConexao());
				break;
			}

			switch (opcao) {
			case 1:
				Veiculo veiculo = cliente.buscarVeiculo();
				if (veiculo != null) {
					System.out.println(veiculo);
				}
				break;
			case 2:
				System.out.println("Informe os dados a seguir.");
				cliente.cadastrarVeiculo(cliente.criarVeiculo());
				break;
			case 3:
				System.out.println("=============================================================================");
				cliente.listarVeiculos();
				System.out.println("=============================================================================");
				break;
			case 4:
				cliente.alterarVeiculo();
				break;
			case 5:
				cliente.removerVeiculo();
				break;
			case 6:
				cliente.acessarQntVeiculos();
				break;
			default:
				System.out.println("Opção desconhecida!!");
				break;
			}
		}
	}

	
	private void iniciar50() {
		int quantidade = 90000; // Altere a quantidade conforme necessário
		Random random = new Random();

		cadastrarVeiculo(new Veiculo("POG3R32", "12344", new Condutor("Icaro", "000.999.333-77"), "Kwid", 2020));
		cadastrarVeiculo(new Veiculo("FIB1I23", "12333", new Condutor("Brenno", "051.298.043-84"), "Civic", 2023));

		for (int i = 0; i < quantidade; i++) {
			String nomeCondutor = gerarNomeAleatorio(random);
			String cpfCondutor = String.format("%03d.%03d.%03d-%02d", random.nextInt(1000), random.nextInt(1000),
					random.nextInt(1000), random.nextInt(100));

			String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String placa = String.format("%c%c%c%d%c%d%d", letras.charAt(random.nextInt(26)),
					letras.charAt(random.nextInt(26)), letras.charAt(random.nextInt(26)), random.nextInt(10),
					letras.charAt(random.nextInt(26)), random.nextInt(10), random.nextInt(10));

			String renavam = String.format("%d%d%d%d%d%d%d", random.nextInt(10), random.nextInt(10), random.nextInt(10),
					random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10));

			String modelo = "Modelo" + (i + 1);
			int ano = 2000 + random.nextInt(23); // Ano de 2000 a 2022

			Veiculo veiculo = new Veiculo(placa, renavam, new Condutor(nomeCondutor, cpfCondutor), modelo, ano);
			cadastrarVeiculo(veiculo);
		}
		

	}

	private static String gerarNomeAleatorio(Random random) {
		String[] nomes = { "João", "Maria", "Pedro", "Ana", "Carlos", "Julia", "Luiz", "Mariana", "Fernando", "Amanda",
				"Rafael", "Larissa", "Lucas", "Isabela", "André", "Camila", "Matheus", "Natália", "Gustavo", "Manuela",
				"Vitor", "Sophia", "Diego", "Gabriela", "Arthur", "Laura", "Alexandre", "Beatriz", "Paulo", "Ana Clara",
				"Rodrigo", "Leticia", "Leonardo", "Eduarda", "Ricardo", "Alice", "Eduardo", "Helena", "Marcos",
				"Valentina", "Vinicius", "Luiza", "Fábio", "Isadora", "Thiago", "Yasmin", "Guilherme", "Clara"
				// Adicione mais nomes se necessário
		};
		return nomes[random.nextInt(nomes.length)];
	}

}
