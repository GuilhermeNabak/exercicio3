package app;

import java.util.List;
import java.util.Scanner;

import dao.VeiculosDAO;
import model.Veiculos;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		VeiculosDAO veiculosDAO = new VeiculosDAO();
		veiculosDAO.conectar();
		Scanner scanner = new Scanner(System.in);
		int opcao = 0;
		
		while (opcao != 5) {
			System.out.println("\n==== MENU DE OPÇÕES ====");
			System.out.println("1- Inserir Veículo");
			System.out.println("2- Listar Veículos");
			System.out.println("3- Atualizar Veículo");
			System.out.println("4- Excluir Veículo");
			System.out.println("5- Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); 

			if (opcao == 1) { 
				System.out.println("\n==== Inserir veículo ===");
				System.out.print("Digite o tipo do veículo: ");
				String tipo = scanner.nextLine();
				System.out.print("Digite o combustível: ");
				String combustivel = scanner.nextLine();
				System.out.print("Digite a capacidade: ");
				int capacidade = scanner.nextInt();
				System.out.print("Digite a velocidade máxima: ");
				int velocidadeMax = scanner.nextInt();

				Veiculos novoVeiculo = new Veiculos(tipo, combustivel, capacidade, velocidadeMax);
				if (veiculosDAO.insert(novoVeiculo)) {
					System.out.println("Veículo inserido com sucesso: " + novoVeiculo.toString());
				} else {
					System.out.println("Erro ao inserir veículo.");
				}
			} 
			else if (opcao == 2) { 
				System.out.println("\n==== Listar veículos ===");
				List<Veiculos> veiculos = veiculosDAO.getAll();
				for (Veiculos v : veiculos) {
					System.out.println(v.toString());
				}
			} 
			else if (opcao == 3) { 
				System.out.println("\n==== Atualizar veículo ===");
				System.out.print("Digite o tipo do veículo a ser atualizado: ");
				String tipoAtualizar = scanner.nextLine();
				Veiculos veiculo = veiculosDAO.getByTipo(tipoAtualizar);

				if (veiculo != null) {
					System.out.print("Novo tipo: ");
					veiculo.setTipo(scanner.nextLine());
					System.out.print("Novo combustível: ");
					veiculo.setCombustivel(scanner.nextLine());
					System.out.print("Nova capacidade: ");
					veiculo.setCapacidade(scanner.nextInt());
					System.out.print("Nova velocidade máxima: ");
					veiculo.setVelocidadeMax(scanner.nextInt());

					if (veiculosDAO.update(veiculo)) {
						System.out.println("Veículo atualizado com sucesso: " + veiculo.toString());
					} else {
						System.out.println("Erro ao atualizar o veículo.");
					}
				} else {
					System.out.println("Veículo não encontrado.");
				}
			} 
			else if (opcao == 4) { 
			    System.out.println("\n==== Excluir veículo ===");
			    System.out.print("Digite o tipo do veículo a ser excluído: ");
			    String tipoExcluir = scanner.nextLine();

			    if (veiculosDAO.delete(tipoExcluir)) {
			        System.out.println("Veículo excluído com sucesso.");
			    } else {
			        System.out.println("Erro ao excluir o veículo.");
			    }
			}
			else if (opcao == 5) { 
				System.out.println("Saindo...");
			} 
			else {
				System.out.println("Opção inválida! Tente novamente.");
			}
		}

		scanner.close(); 
	}
}
