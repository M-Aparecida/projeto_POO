package views;

import java.util.Scanner;
import DAO.CorridaDAO;       
import DAO.MotoristaDAO;    
import entity.Corrida;
import entity.Motorista;
import entity.Passageiro;

/**
 * Classe estática responsável por renderizar todas as telas (menus, prompts, etc.)
 * relacionadas às operações de Corrida no console.
 * Gerencia a interface do usuário para motoristas e passageiros no contexto de uma corrida.
 * Esta classe não deve ser instanciada.
 *
 */
public class CorridaView {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Exibe o menu principal da área de corridas para um motorista logado.
     * O menu é dinâmico, mostrando opções diferentes se o motorista está em uma corrida ou disponível.
     *
     * @param motoristaLogado O objeto do motorista que está com a sessão ativa.
     */
    public static void menuAreaCorrida(Motorista motoristaLogado) {
        if (motoristaLogado == null) {
            System.out.println("Erro: Nenhum motorista logado. Voltando ao menu principal.");
            esperar(2);
            return;
        }

        while (true) {
            CorridaDAO corridaDAO = new CorridaDAO();
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

    /**
     * Exibe a lista de corridas que estão com status 'disponível' para serem aceitas.
     */
    private static void visualizarCorridasDisponiveis() {
        limparTela();
        System.out.println("--- Corridas Disponíveis ---");
        Corrida.listarCorridasDisponiveis();
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Gerencia o fluxo para um motorista aceitar uma corrida. Pede o ID da corrida,
     * valida e atualiza o status da corrida e a disponibilidade do motorista.
     *
     * @param motorista O motorista que está aceitando a corrida.
     */
    private static void aceitarCorrida(Motorista motorista) {
        if (!motorista.isDisponivel()) {
            System.out.println("Você já está em uma corrida e não pode aceitar outra.");
            esperar(2);
            return;
        }

        limparTela();
        System.out.println("--- Aceitar Corrida ---");
        int idCorrida = 0;
        Corrida.listarCorridasDisponiveis();

        System.out.print("\nDigite o ID da corrida que deseja aceitar (ou 0 para cancelar): ");
        do {
            try{
                idCorrida = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("O valor deve ser inteiro, tente novamente: ");
                continue;
            }
        } while (true);

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

    /**
     * Gerencia o fluxo para um motorista finalizar uma corrida em andamento.
     * Após finalizar, o motorista avalia o passageiro.
     *
     * @param motorista O motorista que está finalizando a corrida.
     * @param corrida   A corrida que está sendo finalizada.
     */
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

            System.out.print("--------------------------------------------: ");
            System.out.println("Avaliar Pasasgeiro: ");
            Passageiro.avaliarPassageiro(corrida.getPassageiroId());
        } else {
            System.out.println("Falha ao finalizar a corrida.");
            esperar(2);
        }
    }
    
    /**
     * Gerencia o fluxo para um motorista cancelar uma corrida em andamento.
     * Pede confirmação antes de realizar a ação.
     *
     * @param motorista O motorista que está cancelando a corrida.
     * @param corrida   A corrida que está sendo cancelada.
     */
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

    /**
     * Exibe o histórico de corridas de um motorista específico em formato de tabela.
     *
     * @param motorista O motorista cujo histórico será visualizado.
     */
    public static void visualizarHistoricoMotorista(Motorista motorista) {
        limparTela();
        System.out.println("--- Meu Histórico de Corridas ---");
        Corrida.historicoDeCorridas(motorista.getNumeroCnh());
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Conduz o fluxo para que um passageiro solicite uma nova corrida, capturando origem e destino.
     *
     * @param passageiroLogado O objeto do passageiro que está solicitando a corrida.
     */
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

    /**
     * Exibe o menu de corridas para um passageiro logado, permitindo solicitar corridas ou avaliar motoristas.
     *
     * @param p O objeto do passageiro que está com a sessão ativa.
     */
    public static void menuCorridasPassageiro(Passageiro p){
        boolean mostrarMenu = true;
        while (true) {
            if (mostrarMenu) {
                limparTela();
                System.out.println("\n=== Menu de Corrida de Passageiro ===");
                System.out.println("1. Solicitar Corrida");
                System.out.println("2. Avaliar Motorista");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
            }
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    solicitarNovaCorrida(p);
                    mostrarMenu = true;
                    break;
                case "2":
                    PassageiroView.menuHistoricoEAvaliacao(p);
                    mostrarMenu = true;
                    break;
                case "0":
                    return;
                default:
                    System.out.print("Opção inválida, escolha uma válida: ");
                    mostrarMenu = false;
            }
        }
    }

    /**
     * Limpa a tela do console imprimindo múltiplas linhas novas.
     * É um método simples para melhorar a legibilidade da interface no terminal.
     */
    private static void limparTela() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }
    /**
     * Pausa a execução do programa por um determinado número de segundos.
     * Útil para exibir mensagens ao usuário antes de limpar a tela ou mudar de menu.
     *
     * @param segundos O número de segundos que o programa deve esperar.
     */
    private static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}