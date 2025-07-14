import entity.Passageiro;

import java.util.InputMismatchException;
import java.util.Scanner;

import DAO.PassageiroDAO;


public class App {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        telaBoasVindas();
    }

    public static void telaBoasVindas() {
        while (true) {
            limparTela();
            System.out.println("=== Bem-vindo(a) ao XCX UBER ===");
            System.out.println("1. Entrar como Passageiro");
            System.out.println("2. Entrar como Motorista");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    menuPassageiroLoginCadastro();
                    break;
                case "2":
                    menuMotoristaLoginCadastro();
                    break;
                case "0":
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void menuPassageiroLoginCadastro() {
        while (true) {
            limparTela();
            Passageiro p = new Passageiro();
            System.out.println("\n=== Menu Passageiro ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar-se");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    limparTela();
                    System.out.println("=== Login Passageiro ===");
                    System.out.print("E-mail: ");
                    String emailLogin = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaLogin = scanner.nextLine();

                    PassageiroDAO daoLogin = new PassageiroDAO();
                    Passageiro loginTemp = daoLogin.buscarPorEmail(emailLogin);

                    if (loginTemp != null && loginTemp.login(emailLogin, senhaLogin)) {
                        menuInicialPassageiro(loginTemp.getDadosPassageiro());
                    } else {
                        System.out.println("E-mail ou senha inválidos.");
                    }
                    break;
                    
                case "2":
                    if (menuCadastroPassageiro()) {
                        menuInicialPassageiro(p.getDadosPassageiro());
                    }else{
                        menuCadastroPassageiro();
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static boolean menuCadastroPassageiro(){
        Passageiro p = new Passageiro();

        limparTela();
        System.out.print("Insira o seu nome: ");
        p.setNome(scanner.nextLine());

        do{
            limparTela();
            try {
                System.out.print("Insira o seu CPF (xxx.xxx.xxx-xx): ");
                p.setCpf(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("CPF no formato inválido");
            }
        }while(true);

        do{
            limparTela();
            try {
                System.out.print("Insira o seu email: ");
                p.setEmail(scanner.nextLine());
                break;
            }catch (IllegalArgumentException e) {
                System.out.println("Email no formato invalido.");
            }
        }while(true);
        
        do {
            limparTela();
            try {
                System.out.print("Insira o seu telefone ((xx) xxxxx-xxxx): ");
                p.setTelefone(scanner.nextLine());  
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Telefone no formato invalido");
            }
        } while (true);

        limparTela();
        System.out.print("Insira a sua senha: ");
        p.setSenha(scanner.nextLine());

        do{
            limparTela();
            try {
                System.out.print("Insira a sua idade: ");
                p.setIdade(scanner.nextInt());
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

        limparTela();
        System.out.print("Insira o seu genero: ");
        p.setGenero(scanner.nextLine());

        return p.cadastroPassageiro(p.getNome(), p.getEmail(), p.getCpf(), p.getSenha(), p.getCartaoDados(), p.getIdade(), p.getGenero(), p.getTelefone());
    }

    public static void menuInicialPassageiro(Passageiro p) {
        while (true) {
            limparTela();
            System.out.println("\n=== Tela Inicial do Passageiro ===");
            System.out.println("1. Editar Cadastro");
            System.out.println("2. Procurar Corrida");
            System.out.println("3. Listar Histórico de Corridas");
            System.out.println("4. Área da Corrida");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    menuEditarCadastroPassageiro(p);
                    break;
                case "2":
                    break;
                case "3":
                    menuHistoricoCorridas();
                    break;
                case "4":
                    menuAreaCorrida();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void menuEditarCadastroPassageiro(Passageiro p) {
        while (true) {
            limparTela();
            System.out.println("\n=== Editar Cadastro ===");
            System.out.println("1. Editar Nome");
            System.out.println("2. Editar CPF");
            System.out.println("3. Editar E-mail");
            System.out.println("4. Editar Telefone");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("Insira o novo nome:");
                    String novoNome = scanner.nextLine();
                    if (p.modificarValoresPassageiro("nome", novoNome)) {
                        p.setNome(novoNome);
                        System.out.println("Nome alterado com sucesso!");
                    } else {
                        System.out.println("Falha ao alterar nome.");
                    }
                    break;

                case "2":
                    System.out.println("Insira o novo CPF (xxx.xxx.xxx-xx):");
                    String novoCpf = scanner.nextLine();
                    if (p.modificarValoresPassageiro("cpf", novoCpf)) {
                        p.setCpf(novoCpf);
                        System.out.println("CPF alterado com sucesso!");
                    } else {
                        System.out.println("Falha ao alterar CPF.");
                    }
                    break;

                case "3":
                    System.out.println("Insira o novo e-mail:");
                    String novoEmail = scanner.nextLine();
                    if (p.modificarValoresPassageiro("email", novoEmail)) {
                        p.setEmail(novoEmail);
                        System.out.println("E-mail alterado com sucesso!");
                    } else {
                        System.out.println("Falha ao alterar e-mail.");
                    }
                    break;

                case "4": 
                    System.out.println("Insira o novo telefone ((xx) xxxxx-xxxx):");
                    String novoTelefone = scanner.nextLine();
                    if (p.modificarValoresPassageiro("telefone", novoTelefone)) {
                        p.setTelefone(novoTelefone);
                        System.out.println("Telefone alterado com sucesso!");
                    } else {
                        System.out.println("Falha ao alterar telefone.");
                    }
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Opção inválida.");
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
                    menuInicialMotorista();
                    return;
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

}

