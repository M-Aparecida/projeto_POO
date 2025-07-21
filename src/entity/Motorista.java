package entity;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DAO.MotoristaDAO;

/**
 * Representa um Motorista no sistema, estendendo a classe base Pessoa.
 * Esta classe contém atributos e métodos específicos de um motorista, como o número da CNH e status de disponibilidade.
 * Também inclui métodos de serviço estáticos e de instância que interagem com a camada DAO.
 * 
 * @see Pessoa
 * @see MotoristaDAO
 */
public class Motorista extends Pessoa {
    private long numeroCnh;
    private int idMotorista;
    private boolean disponivel;

     /**
     * Construtor padrão.
     * Inicializa um novo motorista com valores padrão: disponível (true), 0 corridas e avaliação 0.0.
     */
    public Motorista(){
        this.disponivel = true;
        setQuantidadeCorridas(0);
        setAvaliacaoMedia(0.0f);
    }

   /**
     * Construtor para criar um objeto Motorista a partir de dados existentes (ex: do banco de dados).
     *
     * @param idMotorista          O ID único do motorista.
     * @param nome                 O nome do motorista.
     * @param cpf                  O CPF do motorista.
     * @param email                O email do motorista.
     * @param telefone             O telefone do motorista.
     * @param senha                A senha do motorista.
     * @param genero               O gênero do motorista.
     * @param idade                A idade do motorista.
     * @param numeroCnh            O número da CNH do motorista.
     * @param disponivel           O status de disponibilidade do motorista.
     * @param quantidadeCorridas   O total de corridas concluídas.
     * @param avaliacaoMedia       A avaliação média do motorista.
     */
    public Motorista( int idMotorista,String nome, String cpf, String email, String telefone, String senha, String genero,
                 int idade, long numeroCnh, boolean disponivel, int quantidadeCorridas, float avaliacaoMedia) {
    super(nome, cpf, email, telefone, idade, senha, genero);
    this.idMotorista = idMotorista;
    this.numeroCnh = numeroCnh;
    this.disponivel = disponivel;
    this.setQuantidadeCorridas(quantidadeCorridas);
    this.setAvaliacaoMedia(avaliacaoMedia);
}


    /**
     * Construtor para criar uma nova instância de Motorista antes de cadastrar no banco.
     *
     * @param nome        O nome do motorista.
     * @param cpf         O CPF do motorista.
     * @param email       O email do motorista.
     * @param telefone    O telefone do motorista.
     * @param senha       A senha do motorista.
     * @param genero      O gênero do motorista.
     * @param idade       A idade do motorista.
     * @param numeroCnh   O número da CNH do motorista.
     */
    public Motorista(String nome, String cpf, String email, String telefone, String senha, String genero, int idade,
            long numeroCnh) {
        super(nome, cpf, email, telefone, idade, senha, genero);
        this.numeroCnh = numeroCnh;
        this.disponivel = true; 
        this.setQuantidadeCorridas(0);
        this.setAvaliacaoMedia(0.0f); 
    }
    
    /**
     * Busca e retorna os dados completos desta instância de motorista a partir do banco de dados.
     *
     * @return Um novo objeto Motorista com todos os dados atualizados do banco, ou null se o ID não estiver definido.
     */
    public Motorista getDadosMotorista() {
        if (this.idMotorista == 0) {
            System.out.println("Passageiro sem ID definido.");
            return null;
        }

        MotoristaDAO dao = new MotoristaDAO();
        return dao.buscarPorId(this.idMotorista);
    }
    
    /**
     * Método estático de fábrica para criar e cadastrar um novo motorista.
     *
     * @return true se o motorista foi cadastrado com sucesso no banco, false caso contrário.
     */
    public static boolean cadastroMotorista( String nome, String cpf, String email, String telefone, String senha, String genero, int idade, long numeroCnh){
        Motorista motorista = new Motorista(nome, cpf, email, telefone, senha, genero, idade, numeroCnh);

        MotoristaDAO dao = new MotoristaDAO();
        if(dao.cadastrarMotorista(motorista)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Busca todos os motoristas no banco de dados e os exibe em uma tabela no console.
     */
     public static void listarMotoristas(){
        MotoristaDAO dao = new MotoristaDAO();
        List<Motorista> motoristas = dao.listarMotoristas();
        System.out.printf("| %-3s | %-30s | %-30s | %-16s | %-10s | %-22s | %-9s |\n",
        "ID", "Nome", "Email", "Telefone", "Gênero", "Quantidade de Corridas", "Avaliação");
        System.out.println("|-----|--------------------------------|--------------------------------|------------------|------------|------------------------|-----------|");
        for (Motorista motorista : motoristas) {
            System.out.printf("| %-3d | %-30s | %-30s | %-16s | %-10s | %-22d | %-9.2f |\n",
                    motorista.getIdMotorista(),
                    motorista.getNome(),
                    motorista.getEmail(),
                    motorista.getTelefone(),
                    motorista.getGenero(),
                    motorista.getQuantidadeCorridas(),
                    motorista.getAvaliacaoMedia());
        }
    }

    /**
     * Atualiza o status local do motorista para indisponível.
     * Nota: Esta alteração ocorre apenas em memória e não é persistida no banco de dados por este método.
     *
     * @param corridaID O ID da corrida que está sendo aceita (atualmente não utilizado no método).
     * @return true se o motorista estava disponível, false caso contrário.
     */
    public boolean aceitarCorrida(int corridaID) {
        if (!disponivel) {
            return false;
        }
        this.disponivel = false;
        return true;
    }
    
    /**
     * Busca um motorista no banco de dados pelo seu e-mail.
     *
     * @param email O e-mail do motorista a ser buscado.
     * @return O objeto Motorista se encontrado, caso contrário, retorna null.
     */
    public static Motorista buscarMotorista(String email){
        MotoristaDAO dao = new MotoristaDAO();
        Motorista m = dao.buscarPorEmail(email);
        return m;
    }
    
    /**
     * Modifica um campo específico desta instância de motorista no banco de dados.
     *
     * @param op             O campo a ser alterado (ex: "nome", "email", "telefone", "senha").
     * @param novaInformacao O novo valor para o campo.
     * @return true se a modificação for bem-sucedida, false caso contrário.
     */
    public boolean modificarValoresMotorista(String op, String novaInformacao){
        MotoristaDAO dao = new MotoristaDAO();
        switch (op.toLowerCase()) {
            case "nome":
                return dao.alterarNome(novaInformacao, this.getIdMotorista());
            case "email":
                return dao.alterarEmail(novaInformacao, this.getIdMotorista());
            case "telefone":
                return dao.alterarTelefone(novaInformacao, this.getIdMotorista());
            case "senha":
                return dao.alterarSenha(novaInformacao, this.getIdMotorista());
            default:
                System.out.println("Opção inválida.");
                return false;
        }
    }

    /**
     * Método interativo para coletar uma avaliação para um motorista via console e persistí-la.
     *
     * @param idMotorista O ID do motorista a ser avaliado.
     */
    public static void avaliarMotorista(int idMotorista){
        Scanner ent = new Scanner(System.in);
        float avaliacao = 0;
        MotoristaDAO dao = new MotoristaDAO();
        
        do{
            try {
                System.out.print("Insira sua nota para o motorista de 0-5: ");
                avaliacao = ent.nextFloat();
                if(avaliacao >= 0 && avaliacao <= 5){
                    dao.updateAvaliacao(avaliacao, idMotorista); 
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

    /**
     * Gera um relatório de faturamento total para este motorista.
     *
     * @return Um Map contendo as estatísticas de faturamento.
     */
    public Map<String, Number> relatorioFaturamento(){
        MotoristaDAO dao = new MotoristaDAO();
        Map<String, Number> relatorio = dao.gerarRelatorioFaturamento(this.getIdMotorista());
        return relatorio;
    }

    /**
     * Gera um relatório de faturamento para este motorista dentro de um período específico.
     *
     * @param dataInicio A data de início do período.
     * @param dataFim    A data de fim do período.
     * @return Um Map contendo as estatísticas de faturamento para o período.
     */
    public Map<String, Number> relatorioFaturamento( String dataInicio, String dataFim){
        MotoristaDAO dao = new MotoristaDAO();
        Map<String, Number> relatorio = dao.gerarRelatorioFaturamentoPorPeriodo(this.getIdMotorista(), dataInicio, dataFim);;
        return relatorio;   
    }

    // --- GETTERS E SETTERS ---
    public long getNumeroCnh() {
        return numeroCnh;   
    }

    public void setNumeroCnh(long numeroCnh) {
        String cnhStr = String.valueOf(numeroCnh);

            if (numeroCnh <= 0 || cnhStr.length() != 11) {
                throw new IllegalArgumentException("O número da CNH deve conter exatamente 11 dígitos e ser positivo.");
            }

            this.numeroCnh = numeroCnh;    
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
