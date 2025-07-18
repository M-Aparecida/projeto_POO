package views;

import java.util.List;
import java.util.Scanner;

import entity.Veiculo;

public class VeiculoView {
    static Scanner scanner = new Scanner(System.in);

    public static void menuVeiculo() {
        boolean mostrarMenu = true;

        while (true) {
            if (mostrarMenu) {
                limparTela();
                System.out.println("\n=== Menu Veículo ===");
                System.out.println("1. Listar Veículos Disponíveis");
                System.out.println("2. Buscar Veículo por Placa");
                System.out.println("3. Buscar Veículo por Modelo e Ano");
                System.out.println("4. Modificar Veículo");
                System.out.println("5. Deletar Veículo");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
            }

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    listarVeiculos();
                    mostrarMenu = true;
                    break;
                case "2":
                    buscarVeiculoPorPlaca();
                    mostrarMenu = true;
                    break;
                case "3":
                    buscarVeiculoPorModeloAno();
                    mostrarMenu = true;
                    break;
                case "4":
                    modificarVeiculo();
                    mostrarMenu = true;
                    break;
                case "5":
                    deletarVeiculo();
                    mostrarMenu = true;
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    mostrarMenu = false;
            }
        }
    }

    private static void listarVeiculos() {
        limparTela();
        System.out.println("=== Lista de Veículos Disponíveis ===");
        List<Veiculo> veiculos = Veiculo.listarVeiculos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo disponível no momento.");
        } else {
            for (Veiculo v : veiculos) {
                System.out.printf("ID: %d | Placa: %s | Modelo: %s | Ano: %d | Capacidade: %d | ID Motorista: %d\n",
                        v.getIdVeiculo(), v.getPlaca(), v.getModelo(), v.getAno(), v.getCapacidade(), v.getIdVeiculo());
            }
        }
        esperar();
    }

    private static void buscarVeiculoPorPlaca() {
        limparTela();
        System.out.print("Informe a placa do veículo: ");
        String placa = scanner.nextLine();

        Veiculo v = Veiculo.buscar(placa);
        if (v != null) {
            System.out.println("Veículo encontrado:");
            System.out.printf("ID: %d | Placa: %s | Modelo: %s | Ano: %d | Capacidade: %d | ID Motorista: %d\n",
                    v.getIdVeiculo(), v.getPlaca(), v.getModelo(), v.getAno(), v.getCapacidade(), v.getIdVeiculo());
        } else {
            System.out.println("Nenhum veículo disponível com essa placa.");
        }
        esperar();
    }

    private static void buscarVeiculoPorModeloAno() {
        limparTela();
        System.out.print("Informe o modelo do veículo: ");
        String modelo = scanner.nextLine();

        int ano = 0;
        while (true) {
            System.out.print("Informe o ano do veículo: ");
            try {
                ano = Integer.parseInt(scanner.nextLine());
                if (ano > 0) break;
                else System.out.println("Ano inválido, tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Ano inválido, digite um número.");
            }
        }

        Veiculo v = Veiculo.buscar(modelo, ano);
        if (v != null) {
            System.out.println("Veículo encontrado:");
            System.out.printf("ID: %d | Placa: %s | Modelo: %s | Ano: %d | Capacidade: %d | ID Motorista: %d\n",
                    v.getIdVeiculo(), v.getPlaca(), v.getModelo(), v.getAno(), v.getCapacidade(), v.getIdVeiculo());
        } else {
            System.out.println("Nenhum veículo disponível com esse modelo e ano.");
        }
        esperar();
    }

    private static void modificarVeiculo() {
        limparTela();
        System.out.print("Informe a placa do veículo que deseja modificar: ");
        String placa = scanner.nextLine();

        Veiculo v = Veiculo.buscar(placa);
        if (v == null) {
            System.out.println("Veículo não encontrado ou indisponível.");
            esperar();
            return;
        }

        System.out.print("Informe o novo modelo: ");
        String novoModelo = scanner.nextLine();

        int novaCapacidade = 0;
        while (true) {
            System.out.print("Informe a nova capacidade: ");
            try {
                novaCapacidade = Integer.parseInt(scanner.nextLine());
                if (novaCapacidade > 0) break;
                else System.out.println("Capacidade inválida, tente novamente.");
            } catch (NumberFormatException e) {
                System.out.println("Capacidade inválida, digite um número.");
            }
        }

        boolean sucesso = v.modificarValoresVeiculo(novoModelo, novaCapacidade);
        if (sucesso) {
            System.out.println("Veículo modificado com sucesso!");
        } else {
            System.out.println("Falha ao modificar veículo.");
        }
        esperar();
    }

    private static void deletarVeiculo() {
        limparTela();
        System.out.print("Informe a placa do veículo que deseja deletar: ");
        String placa = scanner.nextLine();

        Veiculo v = Veiculo.buscar(placa);
        if (v == null) {
            System.out.println("Veículo não encontrado ou indisponível.");
            esperar();
            return;
        }

        boolean sucesso = v.deletarVeiculo();
        if (sucesso) {
            System.out.println("Veículo deletado com sucesso!");
        } else {
            System.out.println("Falha ao deletar veículo.");
        }
        esperar();
    }

    private static void limparTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private static void esperar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        menuVeiculo();
        System.out.println("Encerrando teste da ViewVeiculo.");
    }
}
