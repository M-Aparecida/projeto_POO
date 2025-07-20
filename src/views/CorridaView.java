package views;

import java.util.Scanner;
import DAO.CorridaDAO;       
import DAO.MotoristaDAO;    
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
            CorridaDAO corridaDAO = new CorridaDAO();
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            Corrida corridaAtual = corridaDAO.buscarCorridaAtivaPorMotorista(motoristaLogado.getIdMotorista());
            motoristaLogado.setDisponivel(corridaAtual == null);

            limparTela();
            System.out.println("\n=== Área da Corrida ===");
            System.out.println("Motorista: " + motoristaLogado.getNome());
            System.out.println("Status: " + (motoristaLogado.isDisponivel() ? "Disponível" : "Em corrida"));
            System.out.println("---------------------------------");
            
            if (corridaAtual != null) {
                System.out.println("Você está em uma corrida (ID: " + corridaAtual.getIdCorrida() + ")");
                System.out.println("De: " + corridaAtual.getOrigem());
                System.out.println("Para: " + corridaAtual.getDestino());
                System.out.println("---------------------------------");
                System.out.println("1. Finalizar Corrida");
                System.out.println("2. Cancelar Corrida");
                System.out.println("3. Ver Meu Histórico de Corridas");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");

                String opcao = scanner.nextLine();
                switch (opcao) {
                    case "1":
                        finalizarCorrida(motoristaLogado, corridaAtual); 
                        break;
                    case "2":
                        cancelarCorrida(motoristaLogado, corridaAtual); 
                        break;
                    case "3":
                        visualizarHistoricoMotorista(motoristaLogado);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        esperar(1);
                }
            } else {
                System.out.println("1. Ver Corridas Disponíveis");
                System.out.println("2. Aceitar uma Corrida");
                System.out.println("3. Ver Meu Histórico de Corridas");
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
        int idCorrida = Integer.parseInt(scanner.nextLine());

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
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            motoristaDAO.alterarDisponibilidade(false, motorista.getIdMotorista());
            motorista.setDisponivel(false);
            System.out.println("Corrida " + idCorrida + " aceita com sucesso!");
        } else {
            System.out.println("Ocorreu um erro ao atualizar os dados da corrida.");
        }
        esperar(2);
    }

    private static void finalizarCorrida(Motorista motorista, Corrida corrida) {
        if (motorista.isDisponivel()) {
            System.out.println("Você não está em nenhuma corrida para finalizar.");
            esperar(2);
            return;
        }

        boolean sucessoFinalizar = corrida.terminarCorrida(); 

        if (sucessoFinalizar) {
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            motoristaDAO.alterarDisponibilidade(true, motorista.getIdMotorista());
            motorista.setDisponivel(true);
            System.out.println("Corrida " + corrida.getIdCorrida() + " finalizada com sucesso!");
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
    private static void cancelarCorrida(Motorista motorista, Corrida corrida) {
        System.out.print("Tem certeza que deseja cancelar a corrida " + corrida.getIdCorrida() + "? (s/n): ");
        if (!scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Operação cancelada.");
            esperar(2);
            return;
        }
        
        boolean sucessoCancelar = corrida.cancelarCorrida();
        
        if (sucessoCancelar) {
            MotoristaDAO motoristaDAO = new MotoristaDAO();
            motoristaDAO.alterarDisponibilidade(true, motorista.getIdMotorista());
            motorista.setDisponivel(true);
            System.out.println("Corrida cancelada com sucesso. Você está disponível para novas corridas.");
        } else {
            System.out.println("Falha ao cancelar a corrida.");
        }
        esperar(2);
    }

    public static void visualizarHistoricoMotorista(Motorista motorista) {
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