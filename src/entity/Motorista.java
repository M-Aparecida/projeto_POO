package entity;

import java.util.List;

import DAO.MotoristaDAO;

public class Motorista extends Pessoa {
    private long numeroCnh;
    private int idMotorista;
    private boolean disponivel;

    public Motorista(){
        this.disponivel = true;
        setQuantidadeCorridas(0);
        setAvaliacaoMedia(0.0f);
    }

    //construtor p exibir
   public Motorista( int idMotorista,String nome, String cpf, String email, String telefone, String senha, String genero,
                 int idade, long numeroCnh, boolean disponivel, int quantidadeCorridas, float avaliacaoMedia) {
    super(nome, cpf, email, telefone, idade, senha, genero);
    this.idMotorista = idMotorista;
    this.numeroCnh = numeroCnh;
    this.disponivel = disponivel;
    this.setQuantidadeCorridas(quantidadeCorridas);
    this.setAvaliacaoMedia(avaliacaoMedia);
}


    //construtor p inserir
    public Motorista(String nome, String cpf, String email, String telefone, String senha, String genero, int idade,
            long numeroCnh) {
        super(nome, cpf, email, telefone, idade, senha, genero);
        this.numeroCnh = numeroCnh;
        this.disponivel = true; 
        this.setQuantidadeCorridas(0); // começa com zero corridas e avaliação tbm eh igual a 0
        this.setAvaliacaoMedia(0.0f); 
    }
    
    public Motorista getDadosMotorista() {
        if (this.idMotorista == 0) {
            System.out.println("Passageiro sem ID definido.");
            return null;
        }

        MotoristaDAO dao = new MotoristaDAO();
        return dao.buscarPorId(this.idMotorista);
    }
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
    

    public static boolean cadastroMotorista( String nome, String cpf, String email, String telefone, String senha, String genero, int idade, long numeroCnh){
        Motorista motorista = new Motorista(nome, cpf, email, telefone, senha, genero, idade, numeroCnh);

        MotoristaDAO dao = new MotoristaDAO();
        if(dao.cadastrarMotorista(motorista)){
            return true;
        }else{
            return false;
        }
    }

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

   // public boolean modificarValoresMotorista(){

   // }


    public boolean aceitarCorrida(int corridaID) {
        if (!disponivel) {
            return false;
        }
        this.disponivel = false;
        return true;
    }
public static Motorista buscarMotorista(String nome){
        MotoristaDAO dao = new MotoristaDAO();
        Motorista m = dao.buscarPorEmail(nome);
        return m;
    }
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


    
}
