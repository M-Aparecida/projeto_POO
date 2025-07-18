import entity.Motorista;

import java.util.InputMismatchException;
import java.util.Scanner;

import DAO.MotoristaDAO;
import entity.Motorista;
import DAO.PassageiroDAO;

import views.*;


public class App {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        telaBoasVindas();    
    }

    public static void telaBoasVindas() {
    boolean mostrarMenu = true;

    while (true) {
        if (mostrarMenu) {
            limparTela();
            System.out.println("\n=== Bem-vindo(a) ao XCX UBER ===");
            System.out.println("1. Entrar como Passageiro");
            System.out.println("2. Entrar como Motorista");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
        }

        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1":
                PassageiroView.menuPassageiroLoginCadastro();
                mostrarMenu = true; 
                break;
            case "2":
                MotoristaView.menuMotorista();
                mostrarMenu = true;
                break;
            case "0":
                System.out.println("Encerrando o programa...");
                return;
            default:
                System.out.print("Opção inválida, escolha uma válida: ");
                mostrarMenu = false; 
        }
    }
}

    public static void menuHistoricoCorridas() {
        while (true) {
            limparTela();
            System.out.println("\n=== Histórico de Corridas ===");
            System.out.println("1. Ver todo o período");
            System.out.println("2. Ver período específico");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) return;
            System.out.println("Função de histórico não implementada ainda.");
        }
    }

    public static void menuAreaCorrida() {
        while (true) {
            limparTela();
            System.out.println("\n=== Área da Corrida ===");
            System.out.println("Status da corrida: [aqui mostraria o status atual]");
            System.out.println("1. Cancelar Corrida");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) return;
            System.out.println("Função de cancelamento não implementada ainda.");
        }
    }
   
    public static void menuAreaVeiculo() {
        while (true) {
            limparTela();
            System.out.println("\n=== Área de Veículo ===");
            System.out.println("1. Cadastrar novo veículo");
            System.out.println("2. Apagar veículo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) return;
            System.out.println("Função de veículo não implementada ainda.");
        }
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    

}

