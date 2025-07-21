package views;

import java.util.List;
import java.util.Scanner;

import entity.Veiculo;

/**
 * Classe estática responsável por renderizar todas as telas (menus, prompts, etc.)
 * relacionadas à gestão de Veículos no console.
 * Fornece uma interface de usuário para listar, buscar, modificar e deletar veículos.
 */
public class VeiculoView {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Exibe o menu principal de gerenciamento de veículos.
     * Atua como o controlador principal para todas as operações de veículos,
     * chamando outros métodos privados com base na escolha do usuário.
     */
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

    /**
     * Busca e exibe uma lista formatada de todos os veículos disponíveis no sistema.
     * Informa ao usuário se nenhum veículo for encontrado.
     */
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

    /**
     * Conduz o fluxo de interface para buscar um veículo por sua placa e exibir seus detalhes.
     */
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

    /**
     * Conduz o fluxo de interface para buscar um veículo por seu modelo e ano, e exibir seus detalhes.
     * Inclui validação para a entrada do ano.
     */
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

    /**
     * Gerencia o processo interativo de modificação dos dados de um veículo.
     * O usuário informa a placa e, se o veículo for encontrado, insere os novos dados.
     */
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

    /**
     * Gerencia o processo interativo de exclusão de um veículo.
     * O usuário informa a placa e o veículo correspondente é deletado.
     */
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

    /**
     * Limpa a tela do console imprimindo múltiplas linhas novas para melhorar a legibilidade.
     */
    private static void limparTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    /**
     * Pausa a execução do programa e aguarda o usuário pressionar a tecla ENTER para continuar.
     */
    private static void esperar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

}
