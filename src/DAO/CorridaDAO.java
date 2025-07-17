package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Corrida;

public class CorridaDAO {
    public List<Corrida> listarTodasCorridas(){
        List<Corrida> corridas = new ArrayList<>();
        String sql = """
            SELECT 
                c.id_corrida,
                c.origem,
                c.destino,
                c.fator_transito,
                c.preco,
                c.data_corrida,
                p.nome AS nome_passageiro,
                m.nome AS nome_motorista,
                v.modelo AS modelo_veiculo,
                c.passageiro_id,
                c.motorista_id,
                c.veiculo_id,
                c.status
            FROM corrida c
            JOIN passageiro p ON c.passageiro_id = p.id_passageiro
            JOIN motorista m ON c.motorista_id = m.id_motorista
            JOIN veiculo v ON c.veiculo_id = v.id_veiculo
        """;

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Corrida c = new Corrida(
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getInt("id_corrida"),
                    rs.getInt("fator_transito"),
                    rs.getFloat("preco"),
                    rs.getString("data_corrida"),
                    rs.getString("nome_passageiro"),
                    rs.getString("nome_motorista"),
                    rs.getString("modelo_veiculo"),
                    rs.getInt("passageiro_id"),
                    rs.getInt("motorista_id"),
                    rs.getInt("veiculo_id"),
                    rs.getInt("status")
                );
                corridas.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar corridas: " + e.getMessage());
        }

        return corridas;
    }

    public List<Corrida> listarCorridasPorPeriodo(String data1, String data2) {
        List<Corrida> corridas = new ArrayList<>();
        String sql = """
            SELECT 
                c.id_corrida,
                c.origem,
                c.destino,
                c.fator_transito,
                c.preco,
                c.data_corrida,
                p.nome AS nome_passageiro,
                m.nome AS nome_motorista,
                v.modelo AS modelo_veiculo,
                c.passageiro_id,
                c.motorista_id,
                c.veiculo_id,
                c.status
            FROM corrida c
            JOIN passageiro p ON c.passageiro_id = p.id_passageiro
            JOIN motorista m ON c.motorista_id = m.id_motorista
            JOIN veiculo v ON c.veiculo_id = v.id_veiculo
            WHERE c.data_corrida >= ? AND c.data_corrida < ?
        """;

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            LocalDate fim = LocalDate.parse(data2).plusDays(1);
            ps.setString(1, data1);
            ps.setString(2, fim.toString());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Corrida c = new Corrida(
                    rs.getString("origem"),
                    rs.getString("destino"),
                    rs.getInt("id_corrida"),
                    rs.getInt("fator_transito"),
                    rs.getFloat("preco"),
                    rs.getString("data_corrida"),
                    rs.getString("nome_passageiro"),
                    rs.getString("nome_motorista"),
                    rs.getString("modelo_veiculo"),
                    rs.getInt("passageiro_id"),
                    rs.getInt("motorista_id"),
                    rs.getInt("veiculo_id"),
                    rs.getInt("status")
                );
                corridas.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar corridas por período: " + e.getMessage());
        }

        return corridas;
    }


}
