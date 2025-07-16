package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Veiculo;

public class VeiculoDAO {
    public List<Veiculo> listarVeiculos(){
        String sql = "SELECT * FROM veiculo";

        List<Veiculo> veiculos = new ArrayList<>();
         
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Veiculo v = new Veiculo(rs.getInt("id_veiculo"), rs.getString("placa"), rs.getString("modelo"), rs.getInt("ano"), rs.getInt("capacidade"));

                    veiculos.add(v);
                }
        }catch (SQLException e) {
            System.out.println("erro ao buscar veículos" + e.getMessage());
        }

        return veiculos;
    }

    public int qtd_veiculos(){
        String sql = "SELECT COUNT(*) FROM veiculo";
        int qtd_veiculos = 0;

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                rs.next();
                qtd_veiculos = rs.getInt("count");
                return qtd_veiculos;

        }catch (SQLException e) {
            System.out.println("erro ao contar veículos" + e.getMessage());
            return -1;
        }

    }
}

