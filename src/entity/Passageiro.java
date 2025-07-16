package entity;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.PassageiroDAO;

public class Passageiro extends Pessoa {
    private int idPassageiro;
    private String cartaoDados;

    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero,
            int idPassageiro, String cartaoDados, int quantidadeCorridas, float avaliacaoMedia, int idade) {
        super(nome, cpf, email, telefone, idade, senha, genero);
        this.idPassageiro = idPassageiro;
        this.cartaoDados = cartaoDados;
        super.setAvaliacaoMedia(avaliacaoMedia);
        super.setQuantidadeCorridas(quantidadeCorridas);
    }

    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero, int idade){
        super(nome, cpf, email, telefone,idade, senha, genero);
        cartaoDados = null;
    }

    public Passageiro(){
        cartaoDados = null;
    }

    public boolean realizarPagamento(float valor, String metodoPagamento){
        if (valor > 0) {
            System.out.println("Pagamento de R$" + valor + " no " + metodoPagamento + "efetuado.");
            return true;    
        }else{
            System.out.println("valor inválido.");
            return false;
        }
    }

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

    public boolean cadastroPassageiro(String nome, String email, String cpf, String senha, String cartaoDados, int idade, String genero, String telefone){
            Passageiro p = new Passageiro(nome, cpf, email, telefone, senha, genero, idade);

            PassageiroDAO dao = new PassageiroDAO();
            if(dao.cadastraPassageiro(p)){
                return true;
            }else{
                return false;
            }
    }

    public Passageiro getDadosPassageiro() {
        if (this.idPassageiro == 0) {
            System.out.println("Passageiro sem ID definido.");
            return null;
        }

        PassageiroDAO dao = new PassageiroDAO();
        return dao.buscarPorId(this.idPassageiro);
    }


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

    public Passageiro buscarPassageiro(String email){
        PassageiroDAO dao = new PassageiroDAO();
        Passageiro p = dao.buscarPorEmail(email);
        return p;
    }

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
        ent.close();
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public String getCartaoDados() {
        return cartaoDados;
    }
}
