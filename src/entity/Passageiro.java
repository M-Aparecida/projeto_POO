package entity;
import java.util.List;

import DAO.PassageiroDAO;

public class Passageiro extends Pessoa {
    private int idPassageiro;
    private String cartaoDados;

    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero,
            int idPassageiro, String cartaoDados, int quantidadeCorridas, float avaliacaoMedia) {
        super(nome, cpf, email, telefone, senha, genero);
        this.idPassageiro = idPassageiro;
        this.cartaoDados = cartaoDados;
        super.setAvaliacaoMedia(avaliacaoMedia);
        super.setQuantidadeCorridas(quantidadeCorridas);
    }

    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero){
        super(nome, cpf, email, telefone, senha, genero);
        cartaoDados = null;
    }

    public boolean realizarPagamento(float valor, String metodoPagamento){
        if (valor > 0.0F) return true;
        return false;
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
                    passageiro.getGenero(),
                    passageiro.getQuantidadeCorridas(),
                    passageiro.getAvaliacaoMedia());
        }
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public String getCartaoDados() {
        return cartaoDados;
    }
}
