import entity.Motorista;

import java.util.InputMismatchException;
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
                menuMotoristaLoginCadastro();
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

    public static void menuMotoristaLoginCadastro() {
        while (true) {
            limparTela();
            System.out.println("\n=== Menu Motorista ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar-se");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                case "2":
                    if (menuCadastroMotorista()) {
                        menuInicialMotorista();
                    }else{
                        menuCadastroMotorista();
                    }                    
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void menuInicialMotorista() {
        while (true) {
            limparTela();
            System.out.println("\n=== Tela Inicial do Motorista ===");
            System.out.println("1. Editar Cadastro");
            System.out.println("2. Listar Histórico de Corridas");
            System.out.println("3. Área da Corrida");
            System.out.println("4. Relatório de Faturamento");
            System.out.println("5. Área de Veículo");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    break;
                case "2":
                    menuHistoricoCorridas();
                    break;
                case "3":
                    menuAreaCorrida();
                    break;
                case "4":
                    menuRelatorioFaturamento();
                    break;
                case "5":
                    menuAreaVeiculo();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void menuRelatorioFaturamento() {
        while (true) {
            limparTela();
            System.out.println("\n=== Relatório de Faturamento ===");
            System.out.println("1. Todo o período");
            System.out.println("2. Período específico");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            if (opcao.equals("0")) return;
            System.out.println("Função de relatório não implementada ainda.");
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
    public static boolean menuCadastroMotorista(){
        Motorista motorista = new Motorista();

        limparTela();
        System.out.print("Insira o seu nome: ");
        motorista.setNome(scanner.nextLine());

        do{
            limparTela();
            try {
                System.out.print("Insira o seu CPF (xxx.xxx.xxx-xx): ");
                motorista.setCpf(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("CPF no formato inválido");
            }
        }while(true);

        do{
            limparTela();
            try {
                System.out.print("Insira o seu email: ");
                motorista.setEmail(scanner.nextLine());
                break;
            }catch (IllegalArgumentException e) {
                System.out.println("Email no formato invalido.");
            }
        }while(true);
        
        do {
            limparTela();
            try {
                System.out.print("Insira o seu telefone ((xx) xxxxx-xxxx): ");
                motorista.setTelefone(scanner.nextLine());  
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Telefone no formato invalido");
            }
        } while (true);

        limparTela();
        System.out.print("Insira a sua senha: ");
        motorista.setSenha(scanner.nextLine());

         limparTela();
        System.out.print("Insira o seu genero: ");
        motorista.setGenero(scanner.nextLine());

        do{
            limparTela();
            try {
                System.out.print("Insira a sua idade: ");
                motorista.setIdade(scanner.nextInt());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Idade deve ser um inteiro.");
                scanner.nextLine();
            }catch(IllegalArgumentException e){
                System.out.println("Insira uma idade válida 1-120");
                scanner.nextLine();
            }
        }while(true);

       do {
            
            try {
                System.out.print("Insira o numero da sua CNH (11 dígitos): ");
                String entrada = scanner.nextLine(); 
                motorista.setNumeroCnh(Long.parseLong(entrada));
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("A CNH deve conter apenas números.");
            } catch (IllegalArgumentException e) {
                System.out.println("CNH inválida. Ela deve conter exatamente 11 dígitos.");
            }
        } while (true);


        return Motorista.cadastroMotorista( motorista.getNome(),motorista.getCpf(),motorista.getEmail(),motorista.getTelefone(),
            motorista.getSenha(),motorista.getGenero(), motorista.getIdade(), motorista.getNumeroCnh());
    }

}

