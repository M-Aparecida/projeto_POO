package entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import conexao.Conexao;

public class Passageiro extends Pessoa {
    private int idPassageiro;
    private String cartaoDados;

    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero,
            int idPassageiro, String cartaoDados) {
        super(nome, cpf, email, telefone, senha, genero);
        this.idPassageiro = idPassageiro;
        this.cartaoDados = cartaoDados;
    }

    public boolean realizarPagamento(float valor, String metodoPagamento){
        if (valor > 0.0F) return true;
        return false;
    }

    public static void listarPassageiros(){
        String sql = "SELECT * FROM passageiro";

        Collection<Passageiro> passageiros = new ArrayList<>();
         
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Passageiro p = new Passageiro(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("telefone"), rs.getString("senha"), rs.getString("genero"), rs.getInt("idpassageiro"), rs.getString("cartaodados"));

                    passageiros.add(p);
                }
        }catch (SQLException e) {
            System.out.println("erro ao buscar passageiros" + e.getMessage());
        }

        for (Passageiro passageiro : passageiros) {
            System.out.println(passageiro);
        }
    }

    public static void main(String[] args) {
        Passageiro.listarPassageiros();
    }
}
