import java.util.Scanner;

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

    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}

