package entity;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.PassageiroDAO;

/**
 * Representa um Passageiro no sistema, estendendo a classe base Pessoa.
 * Esta classe armazena informações específicas do passageiro, como dados de pagamento,
 * e fornece métodos para interagir com o sistema, como listar passageiros e modificar dados.
 *
 * @see Pessoa
 * @see PassageiroDAO
 */
public class Passageiro extends Pessoa {
    private int idPassageiro;
    private String cartaoDados;

    /**
     * Construtor completo para criar um objeto Passageiro a partir de dados do banco de dados.
     *
     * @param nome                 O nome do passageiro.
     * @param cpf                  O CPF do passageiro.
     * @param email                O email do passageiro.
     * @param telefone             O telefone do passageiro.
     * @param senha                A senha de acesso.
     * @param genero               O gênero do passageiro.
     * @param idPassageiro         O ID único do passageiro.
     * @param cartaoDados          Os dados do cartão de pagamento.
     * @param quantidadeCorridas   O total de corridas concluídas.
     * @param avaliacaoMedia       A avaliação média do passageiro.
     * @param idade                A idade do passageiro.
     */
    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero,
            int idPassageiro, String cartaoDados, int quantidadeCorridas, float avaliacaoMedia, int idade) {
        super(nome, cpf, email, telefone, idade, senha, genero);
        this.idPassageiro = idPassageiro;
        this.cartaoDados = cartaoDados;
        super.setAvaliacaoMedia(avaliacaoMedia);
        super.setQuantidadeCorridas(quantidadeCorridas);
    }

    /**
     * Construtor para criar um novo Passageiro durante o cadastro.
     * Inicializa os dados de cartão como nulos.
     *
     * @param nome     O nome do passageiro.
     * @param cpf      O CPF do passageiro.
     * @param email    O email do passageiro.
     * @param telefone O telefone do passageiro.
     * @param senha    A senha de acesso.
     * @param genero   O gênero do passageiro.
     * @param idade    A idade do passageiro.
     */
    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero, int idade){
        super(nome, cpf, email, telefone,idade, senha, genero);
        cartaoDados = null;
    }

     /**
     * Construtor padrão (sem argumentos).
     * Inicializa um objeto Passageiro vazio com dados de cartão nulos.
     */
    public Passageiro(){
        cartaoDados = null;
    }

    /**
     * Simula a realização de um pagamento.
     *
     * @param valor           O valor a ser pago.
     * @param metodoPagamento O método de pagamento (ex: "cartão de crédito").
     * @return true se o valor for válido, false caso contrário.
     */
    public boolean realizarPagamento(float valor, String metodoPagamento){
        if (valor > 0) {
            System.out.println("Pagamento de R$" + valor + " no " + metodoPagamento + "efetuado.");
            return true;    
        }else{
            System.out.println("valor inválido.");
            return false;
        }
    }

    /**
     * Busca todos os passageiros no banco de dados e os exibe em uma tabela no console.
     */
    public static void listarPassageiros(){
        PassageiroDAO dao = new PassageiroDAO();
        List<Passageiro> passageiros = dao.listarPassageiros();
        System.out.printf("| %-3s | %-30s | %-30s | %-16s | %-10s | %-22s | %-9s |\n",
        "ID", "Nome", "Email", "Telefone", "Gênero", "Quantidade de Corridas", "Avaliação");
        System.out.println("|-----|--------------------------------|--------------------------------|------------------|------------|------------------------|-----------|");
        for (Passageiro passageiro : passageiros) {
            System.out.printf("| %-3d | %-30s | %-30s | %-16s | %-10s | %-22d | %-9.2f |\n",
                    passageiro.getIdPassageiro(),
                    passageiro.getNome(),
                    passageiro.getEmail(),
                    passageiro.getTelefone(),
                    passageiro.getGenero().toUpperCase(),
                    passageiro.getQuantidadeCorridas(),
                    passageiro.getAvaliacaoMedia());
        }
    }

    /**
     * Cria e cadastra um novo passageiro no sistema.
     *
     * @return true se o cadastro for bem-sucedido, false caso contrário.
     */
    public boolean cadastroPassageiro(String nome, String email, String cpf, String senha, String cartaoDados, int idade, String genero, String telefone){
            Passageiro p = new Passageiro(nome, cpf, email, telefone, senha, genero, idade);

            PassageiroDAO dao = new PassageiroDAO();
            if(dao.cadastraPassageiro(p)){
                return true;
            }else{
                return false;
            }
    }

    /**
     * Busca os dados completos e atualizados desta instância de passageiro no banco de dados.
     *
     * @return Um novo objeto Passageiro com os dados do banco, ou null se o ID da instância atual for 0.
     */
    public Passageiro getDadosPassageiro() {
        if (this.idPassageiro == 0) {
            System.out.println("Passageiro sem ID definido.");
            return null;
        }

        PassageiroDAO dao = new PassageiroDAO();
        return dao.buscarPorId(this.idPassageiro);
    }

    /**
     * Modifica um campo específico desta instância de passageiro no banco de dados.
     *
     * @param campo      O campo a ser alterado (ex: "nome", "cpf", "email", "telefone").
     * @param novoValor  O novo valor para o campo.
     * @return true se a modificação for bem-sucedida, false caso contrário.
     */
    public boolean modificarValoresPassageiro(String campo, String novoValor) {
    PassageiroDAO dao = new PassageiroDAO();
    switch (campo.toLowerCase()) {
        case "nome":
            return dao.alterarNome(novoValor, this.getIdPassageiro());
        case "cpf":
            return dao.alterarCpf(novoValor, this.getIdPassageiro());
        case "email":
            return dao.alterarEmail(novoValor, this.getIdPassageiro());
        case "telefone":
                return dao.alterarTelefone(novoValor, this.getIdPassageiro());
        default:
            System.out.println("Campo inválido.");
            return false;
        }
    }

    /**
     * Busca um passageiro no banco de dados pelo seu e-mail.
     *
     * @param email O e-mail do passageiro a ser buscado.
     * @return O objeto Passageiro se encontrado, caso contrário, retorna null.
     */
    public static Passageiro buscarPassageiro(String email){
        PassageiroDAO dao = new PassageiroDAO();
        Passageiro p = dao.buscarPorEmail(email);
        return p;
    }

    /**
     * Método interativo para coletar uma avaliação para um passageiro via console e persistí-la no banco.
     *
     * @param idPassageiro O ID do passageiro a ser avaliado.
     */
    public static void avaliarPassageiro(int idPassageiro){
        Scanner ent = new Scanner(System.in);
        float avaliacao = 0;
        PassageiroDAO dao = new PassageiroDAO();
        
        do{
            try {
                System.out.print("Insira sua nota para o passageiro de 0-5: ");
                avaliacao = ent.nextFloat();
                if(avaliacao >= 0 && avaliacao <= 5){
                    dao.updateAvaliacao(avaliacao, idPassageiro); 
                    break;  
                }else{
                    System.out.println("insira um valor de 0-5");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("O valor deve ser um inteiro");   
                ent.nextLine();
            }
        }while(true);

    }

    // --- GETTERS ---
    public int getIdPassageiro() {
        return idPassageiro;
    }

    public String getCartaoDados() {
        return cartaoDados;
    }
}
