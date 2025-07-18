package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import DAO.MotoristaDAO;
import entity.Motorista;
import entity.Passageiro;
import DAO.PassageiroDAO; 
import views.PassageiroView;   
import entity.Pessoa;   


public class MotoristaView {
        static Scanner scanner = new Scanner(System.in);

        public static void menuMotorista() {
        boolean mostrarMenu = true;    
        Motorista m = new Motorista();               

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
                    menuLoginMotorista();                  
                    break;
                case "2":
                    if (menuCadastroMotorista()) {
                        menuInicialMotorista(m.getDadosMotorista());
                    }
                    mostrarMenu = true;                   
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }
    public static void menuLoginMotorista() {
    MotoristaDAO dao = new MotoristaDAO(); 

    while (true) {
        System.out.println("=== Login Motorista ===");

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

        Motorista loginTemp = dao.buscarPorEmail(emailLogin);

        if (loginTemp != null && loginTemp.getSenha().equals(senhaLogin)) {
            menuInicialMotorista(loginTemp); 
            break;
        } else {
            System.out.println("E-mail ou senha inválidos, tente novamente.");
            esperar(2);
        }
    }
}


public static boolean menuCadastroMotorista(){
        Motorista motorista = new Motorista();

        System.out.print("Insira o seu nome: ");
        motorista.setNome(scanner.nextLine());
        limparTela();

        do{
            try {
                System.out.print("Insira o seu CPF (xxx.xxx.xxx-xx): ");
                motorista.setCpf(scanner.nextLine());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("CPF no formato inválido");
            }
        }while(true);
        limparTela();


        do{
            try {
                System.out.print("Insira o seu email: ");
                motorista.setEmail(scanner.nextLine());
                break;
            }catch (IllegalArgumentException e) {
                System.out.println("Email no formato invalido.");
            }
        }while(true);
        limparTela();
        do {
            
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
        limparTela();
        do{
           
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

        limparTela();

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


    public static void menuInicialMotorista(Motorista m) {
        boolean mostrarMenu = true;
        while (true) {
            System.out.println("\n=== Tela Inicial do Motorista ===");
            System.out.println("1. Editar Cadastro");
            System.out.println("2. Listar Motoristas");
            System.out.println("3. Listar Histórico de Corridas");
            System.out.println("4. Buscar Motorista por email");
            System.out.println("5. Área da Corrida");
            System.out.println("6. Relatório de Faturamento");
            System.out.println("7. Área de Veículo");
            System.out.println("8. Avaliar Passageiro");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                menuEditarCadastroMotorista(m);
                mostrarMenu = true;
                    break;
                case "2":
                Motorista.listarMotoristas();
                mostrarMenu = true;
                    break;
                case "3":
                    break;
                case "4":
                menuBuscarMotorista(m);
                mostrarMenu = true;
                    break;
                case "5":
                    break;
                 case "6":
                    break;
                case "7": 
                    break;
                case "8":
                 menuAvaliaPassageiro();
                    mostrarMenu = true;
                    break;
                case "0":

                    return;
                default:
                    System.out.println("Opção inválida!");
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

     public static void menuRelatorioFaturamento() {
        while (true) {
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

    public static void menuEditarCadastroMotorista(Motorista m) {
        boolean mostrarMenu = true;
        while (mostrarMenu) {
            System.out.println("\n=== Editar Cadastro Motorista ===");
            System.out.println("1. Editar Nome");
            System.out.println("2. Editar Email");
            System.out.println("3. Editar Telefone");
            System.out.println("4. Editar Senha");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                System.out.println("\n=== Editar nome ===");
                    System.out.print("Insira o novo nome: ");
                    String novoNome = scanner.nextLine();
                    if (m.modificarValoresMotorista("nome", novoNome)) {
                        m.setNome(novoNome);
                        System.out.println("Nome alterado com sucesso!");
                    } else {
                        System.out.println("Falha ao alterar nome.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;
                case "2":
                System.out.println("\n=== Editar E-mail ===");
                    System.out.print("Insira o novo e-mail: ");
                    String novoEmail = scanner.nextLine();
                    if (novoEmail.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                        m.modificarValoresMotorista("email", novoEmail);
                        m.setEmail(novoEmail);
                        System.out.println("E-mail alterado com sucesso!");
                    } else {
                        System.out.println("E-mail inválido.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;
                case "3":
                System.out.println("\n=== Editar Telefone ===");
                    System.out.print("Insira o novo telefone ((xx) xxxxx-xxxx): ");
                    String novoTelefone = scanner.nextLine();
                    if (novoTelefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
                        m.modificarValoresMotorista("telefone", novoTelefone);
                        m.setTelefone(novoTelefone);
                        System.out.println("Telefone alterado com sucesso!");
                    } else {
                        System.out.println("Telefone inválido.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;
                case "4":
                System.out.println("\n=== Editar Senha ===");
                    System.out.print("Insira a nova senha: ");
                    String novaSenha = scanner.nextLine();
                    if (m.modificarValoresMotorista("senha", novaSenha)) {
                        m.setSenha(novaSenha);
                        System.out.println("Senha alterada com sucesso!");
                    } else {
                        System.out.println("Falha ao alterar senha.");
                    }
                    esperar(1);
                    mostrarMenu = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    mostrarMenu = false;               
                }
            }
        }
    

        public static void menuBuscarMotorista(Motorista M) {
        System.out.println("\n=== Buscar Motorista ===");
        System.out.print("Insira o email do motorista: ");
        String em = scanner.nextLine();
        Motorista motorista = Motorista.buscarMotorista(em);
        if (motorista != null) {
            System.out.println("Motorista encontrado: " + motorista.getNome());
            System.out.println("Email: " + motorista.getEmail());
            System.out.println("Telefone: " + motorista.getTelefone());
            System.out.println("Gênero: " + motorista.getGenero());
            System.out.println("Idade: " + motorista.getIdade());
            System.out.println("CNH: " + motorista.getNumeroCnh());
        } else {
            System.out.println("Motorista não encontrado.");
        }
    }

    public static void menuAvaliaPassageiro() {
        System.out.println("\n=== Avaliar Passageiro ===");
        System.out.print("Insira o ID do passageiro: ");
        int idPassageiro = scanner.nextInt();
        scanner.nextLine(); 

        PassageiroDAO dao = new PassageiroDAO();
        if (dao.buscarPorId(idPassageiro) != null) {
            Passageiro.avaliarPassageiro(idPassageiro);
            System.out.println("Avaliação enviada com sucesso!");
        } else {
            System.out.println("Passageiro não encontrado.");
        }
    }
}
