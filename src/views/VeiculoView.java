package view;

import java.util.Scanner;
import entity.Veiculo;
import dao.VeiculoDAO;

public class VeiculoView {

    private Scanner scanner;
    private VeiculoDAO veiculoDAO;

    public VeiculoView() {
        scanner = new Scanner(System.in);
        veiculoDAO = new VeiculoDAO();
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU VEÍCULO ---");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Listar Veículos");
            System.out.println("3. Buscar Veículo por Placa");
            System.out.println("4. Remover Veículo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    listarVeiculos();
                    break;
                case 3:
                    buscarPorPlaca();
                    break;
                case 4:
                    removerVeiculo();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarVeiculo() {
        System.out.print("Placa do veículo: ");
        String placa = scanner.nextLine();

        System.out.print("Modelo do veículo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano do veículo: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Veiculo veiculo = new Veiculo(placa, modelo, ano);
        veiculoDAO.adicionarVeiculo(veiculo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    private void listarVeiculos() {
        System.out.println("\n--- LISTA DE VEÍCULOS ---");
        for (Veiculo v : veiculoDAO.getTodosVeiculos()) {
            System.out.println(v);
        }
    }

    private void buscarPorPlaca() {
        System.out.print("Digite a placa do veículo: ");
        String placa = scanner.nextLine();

        Veiculo veiculo = veiculoDAO.buscarPorPlaca(placa);
        if (veiculo != null) {
            System.out.println("Veículo encontrado:");
            System.out.println(veiculo);
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private void removerVeiculo() {
        System.out.print("Digite a placa do veículo a ser removido: ");
        String placa = scanner.nextLine();

        boolean removido = veiculoDAO.removerVeiculo(placa);
        if (removido) {
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
}
