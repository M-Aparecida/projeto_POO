package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Passageiro;

public class PassageiroView {
    static Scanner scanner = new Scanner(System.in);

    public static void menuPassageiroLoginCadastro() {
        Passageiro p = new Passageiro();
        boolean mostrarMenu = true;
        while (true) {
            if (mostrarMenu) {
                limparTela();
                System.out.println("\n=== Menu Passageiro ===");
                System.out.println("1. Fazer Login");
                System.out.println("2. Cadastrar-se");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
            }

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    menuLoginPassageiro();
                    mostrarMenu = true;
                    break;
                case "2":
                    if (menuCadastroPassageiro()) {
                        menuInicialPassageiro(p.getDadosPassageiro());
                    }
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

    public static void menuLoginPassageiro() {
        while (true) {
            limparTela();
            System.out.println("=== Login Passageiro ===");

            String emailLogin;
            System.out.print("E-mail: ");
            do {
                emailLogin = scanner.nextLine();
                if (emailLogin.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    break;
                } else {
                    System.out.print("Formato do email inválido, tente novamente: ");
                }
            } while (true);

            System.out.print("Senha: ");
            String senhaLogin = scanner.nextLine();

            Passageiro loginTemp = Passageiro.buscarPassageiro(emailLogin);

            if (loginTemp != null && loginTemp.login(emailLogin, senhaLogin)) {
                menuInicialPassageiro(loginTemp.getDadosPassageiro());
                break;
            } else {
                System.out.println("E-mail ou senha inválidos, tente novamente.");
                esperar(2);
            }
        }
    }

    public static boolean menuCadastroPassageiro() {
        Passageiro p = new Passageiro();
        limparTela();

        System.out.print("Insira o seu nome: ");
        p.setNome(scanner.nextLine());

        do {
            limparTela();
            try {
                System.out.print("Insira o seu CPF (xxx.xxx.xxx-xx): ");
                p.setCpf(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("CPF no formato inválido, insira novamente!");
                esperar(2);
            }
        } while (true);

        do {
            limparTela();
            try {
                System.out.print("Insira o seu email: ");
                p.setEmail(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Email no formato inválido, insira novamente!");
                esperar(2);
            }
        } while (true);

        do {
            limparTela();
            try {
                System.out.print("Insira o seu telefone ((xx) xxxxx-xxxx): ");
                p.setTelefone(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Telefone no formato inválido");
                esperar(2);
            }
        } while (true);

        limparTela();
        System.out.print("Insira a sua senha: ");
        p.setSenha(scanner.nextLine());

        do {
            limparTela();
            try {
                System.out.print("Insira a sua idade: ");
                p.setIdade(scanner.nextInt());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Idade deve ser um número inteiro.");
                scanner.nextLine();
                esperar(2);
            } catch (IllegalArgumentException e) {
                System.out.println("Insira uma idade válida (1-120)");
                scanner.nextLine();
                esperar(2);
            }
        } while (true);

        limparTela();
        System.out.print("Insira o seu gênero: ");
        p.setGenero(scanner.nextLine());

        return p.cadastroPassageiro(
            p.getNome(), p.getEmail(), p.getCpf(), p.getSenha(),
            p.getCartaoDados(), p.getIdade(), p.getGenero(), p.getTelefone()
        );
    }

    public static void menuInicialPassageiro(Passageiro p) {
        boolean mostrarMenu = true;
        while (true) {
            if (mostrarMenu) {
                limparTela();
                System.out.println("\n=== Tela Inicial do Passageiro ===");
                System.out.println("1. Editar Cadastro");
                System.out.println("2. Solicitar Corrida");
                System.out.println("3. Listar Histórico de Corridas");
                System.out.println("4. Área da Corrida");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("Escolha uma opção: ");
            }

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    menuEditarCadastroPassageiro(p);
                    mostrarMenu = true;
                    break;
                case "2":
                    CorridaView.solicitarNovaCorrida(p);
                    mostrarMenu = true;
                    break;
                case "3":
                    mostrarMenu = true;
                    break;
                case "4":
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

    public static void menuEditarCadastroPassageiro(Passageiro p) {
        boolean mostrarMenu = true;
        while (true) {
            if (mostrarMenu) {
                limparTela();
                System.out.println("\n=== Editar Cadastro ===");
                System.out.println("1. Editar Nome");
                System.out.println("2. Editar CPF");
                System.out.println("3. Editar E-mail");
                System.out.println("4. Editar Telefone");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opção: ");
            }

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    System.out.println("\n=== Editar nome ===");
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    if (p.modificarValoresPassageiro("nome", novoNome)) {
                        p.setNome(novoNome);
                        System.out.println("Nome alterado com sucesso!");
                    } else {
                        System.out.println("Falha ao alterar nome.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;

                case "2":
                    System.out.println("\n=== Editar CPF ===");
                    System.out.print("Novo CPF (xxx.xxx.xxx-xx): ");
                    String novoCpf = scanner.nextLine();
                    if (novoCpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
                        p.modificarValoresPassageiro("cpf", novoCpf);
                        p.setCpf(novoCpf);
                        System.out.println("CPF alterado com sucesso!");
                    } else {
                        System.out.println("CPF inválido.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;

                case "3":
                    System.out.println("\n=== Editar E-mail ===");
                    System.out.print("Novo e-mail: ");
                    String novoEmail = scanner.nextLine();
                    if (novoEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                        p.modificarValoresPassageiro("email", novoEmail);
                        p.setEmail(novoEmail);
                        System.out.println("E-mail alterado com sucesso!");
                    } else {
                        System.out.println("E-mail inválido.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;

                case "4":
                    System.out.println("\n=== Editar Telefone ===");
                    System.out.print("Novo telefone ((xx) xxxxx-xxxx): ");
                    String novoTelefone = scanner.nextLine();
                    if (novoTelefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
                        p.modificarValoresPassageiro("telefone", novoTelefone);
                        p.setTelefone(novoTelefone);
                        System.out.println("Telefone alterado com sucesso!");
                    } else {
                        System.out.println("Telefone inválido.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;

                case "0":
                    return;

                default:
                    System.out.print("Opção inválida, tente novamente: ");
                    mostrarMenu = false;
            }
        }
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Operação interrompida.");
        }
    }
}