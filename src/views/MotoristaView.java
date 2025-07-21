package views;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import DAO.MotoristaDAO;
import DAO.CorridaDAO;
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
           emailLogin = scanner.nextLine().trim(); 
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

        System.out.println("=== Cadastro do Motorista ===");

         do {
        try {
            System.out.print("Insira o seu nome: ");
            motorista.setNome(scanner.nextLine());
            break; 
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    } while (true);
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
                System.out.println("Insira uma idade válida 18-120");
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
            System.out.println("3. Buscar Motorista por email");
            System.out.println("4. Área da Corrida");
            System.out.println("5. Relatório de Faturamento");
            System.out.println("6. Área de Veículo");
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
                    menuBuscarMotorista(m);
                    mostrarMenu = true;
                    break;
                case "4":
                    CorridaView.menuAreaCorrida(m);
                    break;
                case "5":
                    menuRelatorioFaturamento(m);
                    mostrarMenu = true;
                    break;
                case "6":
                    VeiculoView.menuVeiculo();
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

    private static void menuRelatorioFaturamento(Motorista m) {
        while (true) {
            limparTela();
            System.out.println("\n=== Relatório de Faturamento ===");
            System.out.println("Motorista: " + m.getNome());
            System.out.println("---------------------------------");
            System.out.println("1. Relatório de todo o período");
            System.out.println("2. Relatório por período específico");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();
            Map<String, Number> relatorio = null;

            switch (opcao) {
                case "1":
                    relatorio = m.relatorioFaturamento();
                    limparTela();
                    System.out.println("--- Relatório de Faturamento (Todo o Período) ---");
                    exibirRelatorio(relatorio);
                    esperar(5);
                    break;
                case "2":
                    limparTela();
                    String dataInicio, dataFim;
                    System.out.println("--- Relatório por Período Específico ---");
                    System.out.print("Digite a data de início (AAAA-MM-DD): ");
                    do {
                        dataInicio = scanner.nextLine();
                        if (!dataInicio.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            System.out.print("insira uma data válida (AAAA-MM-DD): ");    
                            continue;
                        }else{
                            break;
                        }
                    } while (true);
                    System.out.print("Digite a data de fim (AAAA-MM-DD): ");
                    do {
                        dataFim = scanner.nextLine();
                        if (!dataFim.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            System.out.print("insira uma data válida (AAAA-MM-DD): ");    
                            continue;
                        }else{
                            break;
                        }
                    } while (true);
                    
                    relatorio = m.relatorioFaturamento(dataInicio, dataFim);
                    limparTela();
                    System.out.println("--- Relatório de Faturamento (" + dataInicio + " a " + dataFim + ") ---");
                    exibirRelatorio(relatorio);
                    esperar(5);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida!");
                    esperar(1);
            }
        }
    }

    private static void exibirRelatorio(Map<String, Number> relatorio) {
        if (relatorio != null) {
            System.out.println("---------------------------------");
            System.out.printf("Corridas Finalizadas: %d\n", relatorio.get("corridasFinalizadas").intValue());
            System.out.printf("Corridas Canceladas:  %d\n", relatorio.get("corridasCanceladas").intValue());
            System.out.printf("Faturamento Total:    R$ %.2f\n", relatorio.get("faturamentoTotal").doubleValue());
            System.out.println("---------------------------------");
        } else {
            System.out.println("Não foi possível gerar o relatório ou não há dados para o período.");
        }
    }

    public static void menuEditarCadastroMotorista(Motorista m) {
        boolean mostrarMenu = true;
        while (mostrarMenu) {
            System.out.println("\n=== Editar Cadastro Motorista ===");
            System.out.println("1. Editar nome");
            System.out.println("2. Editar email");
            System.out.println("3. Editar numero da cnh");
            System.out.println("4. Editar senha");
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
                System.out.println("\n=== Editar numero da cnh ===");
                System.out.println("Insira o novo numero da cnh: ");
                long novoNumeroCnh = scanner.nextLong();
                scanner.nextLine();
               if (m.modificarValoresMotorista("numero cnh", String.valueOf(novoNumeroCnh))) { 
                    m.setNumeroCnh(novoNumeroCnh); 
                    System.out.println("Número da CNH alterado com sucesso!");
                } else {
                    System.out.println("Falha ao alterar o número da CNH.");
                }
                mostrarMenu = true;
                esperar(2);
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
                case "0":

                    return;
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
        int idPassageiro = 0;
        System.out.println("\n=== Avaliar Passageiro ===");
        System.out.print("Insira o ID do passageiro: ");
        do {
            try {
                idPassageiro = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("O valor deve ser do tipo inteiro: ");
                continue;
            }
        } while (true);
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