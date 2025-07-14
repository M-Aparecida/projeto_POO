package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Passageiro;

public class PassageiroDAO {
    public List<Passageiro> listarPassageiros(){
        String sql = "SELECT * FROM passageiro";

        List<Passageiro> passageiros = new ArrayList<>();
         
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Passageiro p = new Passageiro(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("telefone"), rs.getString("senha"), rs.getString("genero"), rs.getInt("id_passageiro"), rs.getString("cartao_dados"), rs.getInt("qtd_corridas"), rs.getInt("avaliacao_media"), rs.getInt("idade"));

                    passageiros.add(p);
                }
        }catch (SQLException e) {
            System.out.println("erro ao buscar passageiros" + e.getMessage());
        }

        return passageiros;
    }
    
    public boolean cadastraPassageiro(Passageiro passageiro){
        String sql = "INSERT INTO passageiro (nome, cpf, email, telefone, senha, idade, genero, qtd_corridas, avaliacao_media, cartao_dados) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, passageiro.getNome());
            ps.setString(2, passageiro.getCpf());
            ps.setString(3, passageiro.getEmail());
            ps.setString(4, passageiro.getTelefone());
            ps.setString(5, passageiro.getSenha());
            ps.setInt(6, passageiro.getIdade());
            ps.setString(7, passageiro.getGenero());
            ps.setInt(8, passageiro.getQuantidadeCorridas());
            ps.setFloat(9, passageiro.getAvaliacaoMedia());
            ps.setString(10, passageiro.getCartaoDados());

            ps.executeUpdate();

            System.out.println("Passageiro cadastrado com sucesso.");
            return true;
        }catch (SQLException e) {
            System.out.println("erro ao inserir passageiros " + e.getMessage());
            return false;
        }
    }
}
