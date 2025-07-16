package views;

import java.util.InputMismatchException;
import java.util.Scanner;

import DAO.MotoristaDAO;
import entity.Motorista;
import entity.Passageiro;

public class MotoristaView {
        static Scanner scanner = new Scanner(System.in);

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
                limparTela();
                    System.out.println("=== Login Motorista ===");
                    System.out.print("E-mail: ");
                    String emailLogin = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaLogin = scanner.nextLine();

                    MotoristaDAO daoLogin = new MotoristaDAO();
                    Motorista loginTemp = daoLogin.buscarMotoristaPorNome(emailLogin);

                    if (loginTemp != null && loginTemp.login(emailLogin, senhaLogin)) {
                        menuInicialMotorista(loginTemp.getDadosMotorista());
                    } else {
                        System.out.println("E-mail ou senha inválidos.");
                    }
                    break;
                case "2":
                    if (menuCadastroMotorista()) {
                        menuMotoristaLoginCadastro();
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
    public static void menuLoginMotorista() {
        while (true) {
            limparTela();
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

            Motorista loginTemp = Motorista.buscarMotorista(emailLogin);

            if (loginTemp != null && loginTemp.login(emailLogin, senhaLogin)) {
                menuInicialMotorista(loginTemp.getDadosMotorista());
                break;
            } else {
                System.out.println("E-mail ou senha inválidos, tente novamente.");
                esperar(2);
            }
        }
    }

public static boolean menuCadastroMotorista(){
        Motorista motorista = new Motorista();

        limparTela();
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
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
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
}
