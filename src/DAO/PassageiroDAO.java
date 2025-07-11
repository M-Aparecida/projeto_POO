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
                    Passageiro p = new Passageiro(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("telefone"), rs.getString("senha"), rs.getString("genero"), rs.getInt("id_passageiro"), rs.getString("cartao_dados"), rs.getInt("qtd_corridas"), rs.getInt("avaliacao_media"));

                    passageiros.add(p);
                }
        }catch (SQLException e) {
            System.out.println("erro ao buscar passageiros" + e.getMessage());
        }

        return passageiros;
    }

    
}
