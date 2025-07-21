import java.util.Scanner;

import views.*;

/**
 * Classe principal que serve como ponto de entrada para a aplicação "XCX UBER".
 * Sua responsabilidade é iniciar o programa e exibir o menu inicial de boas-vindas,
 * permitindo que o usuário escolha entre os módulos de Passageiro ou Motorista.
 */
public class App {

    static Scanner scanner = new Scanner(System.in);
    /**
     * O método principal que inicia a execução da aplicação.
     *
     * @param args Argumentos de linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
        telaBoasVindas();    
    }
    /**
     * Exibe a tela de boas-vindas e o menu principal da aplicação.
     * Este método contém o loop principal que direciona o usuário para as
     * áreas de Passageiro ou Motorista, ou encerra o programa.
     */
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

    /**
     * Limpa a tela do console imprimindo múltiplas linhas novas.
     * Utilizado para melhorar a experiência do usuário entre as telas.
     */
    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

}

