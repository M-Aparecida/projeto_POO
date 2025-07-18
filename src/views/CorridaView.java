package views;

import java.util.Scanner;
import entity.Corrida;
import entity.Motorista;
import entity.Passageiro;

public class CorridaView {
    private static Scanner scanner = new Scanner(System.in);

    public static void menuAreaCorrida(Motorista motoristaLogado) {
        if (motoristaLogado == null) {
            System.out.println("Erro: Nenhum motorista logado. Voltando ao menu principal.");
            esperar(2);
            return;
        }

        while (true) {
            limparTela();
            System.out.println("\n=== Área da Corrida ===");
            System.out.println("Motorista: " + motoristaLogado.getNome());
            System.out.println("Status: " + (motoristaLogado.isDisponivel() ? "Disponível" : "Em corrida"));
            System.out.println("---------------------------------");
            System.out.println("1. Ver Corridas Disponíveis");
            System.out.println("2. Aceitar uma Corrida");
            System.out.println("3. Finalizar Corrida Atual");
            System.out.println("4. Ver Meu Histórico de Corridas");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    visualizarCorridasDisponiveis();
                    break;
                case "2":
                    aceitarCorrida(motoristaLogado);
                    break;
                case "3":
                    finalizarCorrida(motoristaLogado);
                    break;
                case "4":
                    visualizarHistoricoMotorista(motoristaLogado);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
                    esperar(1);
            }
        }
    }

    private static void visualizarCorridasDisponiveis() {
        limparTela();
        System.out.println("--- Corridas Disponíveis ---");
        Corrida.listarCorridasDisponiveis();
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    private static void aceitarCorrida(Motorista motorista) {
        if (!motorista.isDisponivel()) {
            System.out.println("Você já está em uma corrida e não pode aceitar outra.");
            esperar(2);
            return;
        }

        limparTela();
        System.out.println("--- Aceitar Corrida ---");
        Corrida.listarCorridasDisponiveis();

        System.out.print("\nDigite o ID da corrida que deseja aceitar (ou 0 para cancelar): ");
        int idCorrida = scanner.nextInt();
        scanner.nextLine();

        if (idCorrida == 0) return;

        Corrida corrida = Corrida.buscarCorrida(idCorrida);

        if (corrida == null || corrida.getStatus() != 1) {
            System.out.println("Não foi possível aceitar a corrida. Ela pode já ter sido pega ou o ID é inválido.");
            esperar(2);
            return;
        }

        boolean sucessoMotorista = corrida.modificarValoresCorrida("motorista_id", String.valueOf(motorista.getIdMotorista()));
        boolean sucessoStatus = corrida.modificarValoresCorrida("status", "2");

        if (sucessoMotorista && sucessoStatus) {
            motorista.setDisponivel(false);
            System.out.println("Corrida " + idCorrida + " aceita com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao atualizar os dados da corrida.");
        }
        esperar(2);
    }

    private static void finalizarCorrida(Motorista motorista) {
        limparTela();
        System.out.println("--- Finalizar Corrida ---");
        System.out.print("Digite o ID da corrida que deseja finalizar: ");
        
        int idCorrida = scanner.nextInt();
        scanner.nextLine();

        Corrida corrida = Corrida.buscarCorrida(idCorrida);

        if (corrida == null || corrida.getMotoristaId() != motorista.getIdMotorista() || corrida.getStatus() != 2) { 
            System.out.println("Corrida inválida, não pertence a você ou não está com o status 'Aceita'.");
            esperar(3);
            return;
        }

        boolean sucessoFinalizar = corrida.modificarValoresCorrida("status", "4");

        if (sucessoFinalizar) {
            motorista.setDisponivel(true);
            System.out.println("Corrida " + idCorrida + " finalizada com sucesso!");
            esperar(2);
            
            System.out.print("Deseja avaliar o passageiro desta corrida? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                Passageiro.avaliarPassageiro(corrida.getPassageiroId());
            }
        } else {
            System.out.println("Falha ao finalizar a corrida.");
            esperar(2);
        }
    }

    private static void visualizarHistoricoMotorista(Motorista motorista) {
        limparTela();
        System.out.println("--- Meu Histórico de Corridas ---");
        Corrida.historicoDeCorridas(motorista.getNumeroCnh());
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    public static void solicitarNovaCorrida(Passageiro passageiroLogado) {
        limparTela();
        System.out.println("--- Solicitar Nova Corrida ---");
        
        System.out.print("Digite o endereço de ORIGEM: ");
        String origem = scanner.nextLine();

        System.out.print("Digite o endereço de DESTINO: ");
        String destino = scanner.nextLine();

        System.out.print("Confirmar a solicitação de '" + origem + "' para '" + destino + "'? (s/n): ");
        
        if (!scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Solicitação cancelada.");
            esperar(2);
            return;
        }
        
        Corrida corridaCriada = Corrida.realizarCorrida(origem, destino, passageiroLogado);

        if (corridaCriada != null) {
            System.out.println("Corrida solicitada com sucesso!");
            System.out.println("O ID da sua corrida é: " + corridaCriada.getIdCorrida());
        } else {
            System.out.println("Houve um erro ao solicitar a corrida. Tente novamente.");
        }
        esperar(3);
    }

    private static void limparTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    private static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}