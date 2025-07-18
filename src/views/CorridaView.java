package view;

import DAO.CorridaDAO;
import entity.Corrida;

import java.util.List;
import java.util.Scanner;

public class CorridaView {

    private CorridaDAO corridaDAO;
    private Scanner scanner;

    public CorridaView() {
        this.corridaDAO = new CorridaDAO();
        this.scanner = new Scanner(System.in);
    }

    public void menuCorridas() {
        int opcao;
        do {
            System.out.println("\n=== Menu Corridas ===");
            System.out.println("1. Cadastrar nova corrida");
            System.out.println("2. Listar todas as corridas");
            System.out.println("3. Buscar corrida por ID");
            System.out.println("4. Cancelar corrida");
            System.out.println("5. Finalizar corrida");
            System.out.println("6. Avaliar corrida");
            System.out.println("7. Listar corridas disponíveis");
            System.out.println("8. Listar corridas entre datas");
            System.out.println("9. Histórico por CPF do passageiro");
            System.out.println("10. Histórico por CNH do motorista");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCorrida();
                case 2 -> listarTodas();
                case 3 -> buscarPorId();
                case 4 -> cancelarCorrida();
                case 5 -> finalizarCorrida();
                case 6 -> avaliarCorrida();
                case 7 -> listarDisponiveis();
                case 8 -> listarEntreDatas();
                case 9 -> historicoPorPassageiro();
                case 10 -> historicoPorMotorista();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarCorrida() {
        System.out.println("\n--- Cadastrar Nova Corrida ---");
        System.out.print("ID da corrida: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Origem: ");
        String origem = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Motorista (CNH): ");
        int motoristaId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Passageiro (CPF): ");
        String passageiro = scanner.nextLine();
        System.out.print("Data (AAAA-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Valor da corrida: ");
        double valor = scanner.nextDouble(); scanner.nextLine();

        Corrida nova = new Corrida(id, origem, destino, motoristaId, null, passageiro, data, valor, 0);
        if (corridaDAO.adicionarCorrida(nova)) {
            System.out.println("Corrida cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar corrida.");
        }
    }

    private void listarTodas() {
        List<Corrida> corridas = corridaDAO.listarTodasCorridas();
        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida cadastrada.");
        } else {
            for (Corrida c : corridas) {
                System.out.println(c);
            }
        }
    }

    private void buscarPorId() {
        System.out.print("ID da corrida: ");
        int id = scanner.nextInt();
        Corrida c = corridaDAO.buscarCorrida(id);
        System.out.println(c != null ? c : "Corrida não encontrada.");
    }

    private void cancelarCorrida() {
        System.out.print("ID da corrida a cancelar: ");
        int id = scanner.nextInt();
        Corrida c = corridaDAO.buscarCorrida(id);
        if (c != null) {
            c.cancelarCorrida();
            corridaDAO.atualizarCorrida(c);
            System.out.println("Corrida cancelada.");
        } else {
            System.out.println("Corrida não encontrada.");
        }
    }

    private void finalizarCorrida() {
        System.out.print("ID da corrida a finalizar: ");
        int id = scanner.nextInt();
        Corrida c = corridaDAO.buscarCorrida(id);
        if (c != null) {
            c.terminarCorrida();
            corridaDAO.atualizarCorrida(c);
            System.out.println("Corrida finalizada.");
        } else {
            System.out.println("Corrida não encontrada.");
        }
    }

    private void avaliarCorrida() {
        System.out.print("ID da corrida a avaliar: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Nota (0 a 5): ");
        int nota = scanner.nextInt(); scanner.nextLine();
        Corrida c = corridaDAO.buscarCorrida(id);
        if (c != null) {
            c.avaliarCorrida(id, nota);
        } else {
            System.out.println("Corrida não encontrada.");
        }
    }

    private void listarDisponiveis() {
        List<Corrida> corridas = corridaDAO.listarCorridasDisponiveis();
        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida disponível.");
        } else {
            for (Corrida c : corridas) {
                System.out.println(c);
            }
        }
    }

    private void listarEntreDatas() {
        System.out.print("Data inicial (AAAA-MM-DD): ");
        String d1 = scanner.nextLine();
        System.out.print("Data final (AAAA-MM-DD): ");
        String d2 = scanner.nextLine();

        List<Corrida> corridas = corridaDAO.listarCorridasPorData(d1, d2);
        for (Corrida c : corridas) {
            System.out.println(c);
        }
    }

    private void historicoPorPassageiro() {
        System.out.print("CPF do passageiro: ");
        String cpf = scanner.nextLine();
        List<Corrida> corridas = corridaDAO.historicoDeCorridasPorPassageiro(cpf);
        for (Corrida c : corridas) {
            System.out.println(c);
        }
    }

    private void historicoPorMotorista() {
        System.out.print("CNH do motorista: ");
        int cnh = scanner.nextInt();
        List<Corrida> corridas = corridaDAO.historicoDeCorridasPorMotorista(cnh);
        for (Corrida c : corridas) {
            System.out.println(c);
        }
    }
}
