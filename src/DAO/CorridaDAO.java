package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Types;

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

    public List<Corrida> listarCorridasPorCNH(long cnh) {
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
            LEFT JOIN veiculo v ON c.veiculo_id = v.id_veiculo
            WHERE m.numero_cnh = ?
            ORDER BY c.data_corrida DESC
        """;

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, cnh);
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
            System.out.println("Erro ao buscar corridas por CNH: " + e.getMessage());
        }

        return corridas;
    }

    public List<Corrida> listarCorridasPorCPF(String cpf) {
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
            LEFT JOIN motorista m ON c.motorista_id = m.id_motorista
            LEFT JOIN veiculo v ON c.veiculo_id = v.id_veiculo
            WHERE p.cpf = ? and c.status = 4
            ORDER BY c.data_corrida DESC
        """;

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
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
            System.out.println("Erro ao buscar corridas por CPF: " + e.getMessage());
        }

        return corridas;
    }

    public Corrida buscarCorrida(int idCorrida) {
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
            FROM 
                corrida c
            JOIN 
                passageiro p ON c.passageiro_id = p.id_passageiro -- Toda corrida tem um passageiro, então JOIN está correto.
            LEFT JOIN 
                motorista m ON c.motorista_id = m.id_motorista -- Pode não ter motorista ainda, então LEFT JOIN.
            LEFT JOIN 
                veiculo v ON c.veiculo_id = v.id_veiculo -- Pode não ter veículo ainda, então LEFT JOIN.
            WHERE 
                c.id_corrida = ?
        """;

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCorrida);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Corrida(
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
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar corrida por ID: " + e.getMessage());
        }

        return null;
    }

    public List<Corrida> listarCorridasDisponiveis() {
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
                m.nome AS nome_motorista, -- Este campo será NULL para corridas disponíveis
                v.modelo AS modelo_veiculo, -- Este campo também será NULL
                c.passageiro_id,
                c.motorista_id,
                c.veiculo_id,
                c.status
            FROM
                corrida AS c
            JOIN
                passageiro p ON c.passageiro_id = p.id_passageiro -- Um JOIN aqui está correto, pois toda corrida TEM um passageiro.
            LEFT JOIN
                motorista m ON c.motorista_id = m.id_motorista -- Alterado para LEFT JOIN
            LEFT JOIN
                veiculo v ON c.veiculo_id = v.id_veiculo -- Alterado para LEFT JOIN
            WHERE
                c.status = 1
            ORDER BY
                c.data_corrida DESC;
        """;

        try (Connection conn = Conexao.getConnection();
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
            System.out.println("Erro ao buscar corridas disponíveis: " + e.getMessage());
        }   
        return corridas;
    }

    public boolean alterarOrigem(String novaOrigem, int idCorrida) {
        String sql = "UPDATE corrida SET origem = ? WHERE id_corrida = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novaOrigem);
            ps.setInt(2, idCorrida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar origem: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarDestino(String novoDestino, int idCorrida) {
        String sql = "UPDATE corrida SET destino = ? WHERE id_corrida = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoDestino);
            ps.setInt(2, idCorrida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar destino: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarPreco(float novoPreco, int idCorrida) {
        String sql = "UPDATE corrida SET preco = ? WHERE id_corrida = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, novoPreco);
            ps.setInt(2, idCorrida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar preço: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarFatorTransito(int novoFator, int idCorrida) {
        String sql = "UPDATE corrida SET fator_transito = ? WHERE id_corrida = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, novoFator);
            ps.setInt(2, idCorrida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar fator de trânsito: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarDataCorrida(String novaData, int idCorrida) {
        String sql = "UPDATE corrida SET data_corrida = ? WHERE id_corrida = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novaData);
            ps.setInt(2, idCorrida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar data da corrida: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarMotoristaId(int motoristaId, int idCorrida) {
        String sql = "UPDATE corrida SET motorista_id = ? WHERE id_corrida = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, motoristaId);
            ps.setInt(2, idCorrida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar motorista da corrida: " + e.getMessage());
            return false;
        }
    }

    public boolean alterarStatus(int novoStatus, int idCorrida) {
        String sql = "UPDATE corrida SET status = ? WHERE id_corrida = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, novoStatus);
            ps.setInt(2, idCorrida);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar status da corrida: " + e.getMessage());
            return false;
        }
    }

    public Corrida inserirCorrida(Corrida novaCorrida) {
        String sql = """
            INSERT INTO corrida 
            (origem, destino, fator_transito, preco, data_corrida, passageiro_id, motorista_id, veiculo_id, status) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, novaCorrida.getOrigem());
            ps.setString(2, novaCorrida.getDestino());
            ps.setInt(3, novaCorrida.getFatorTransito());
            ps.setFloat(4, novaCorrida.getPreco());
            ps.setString(5, novaCorrida.getData());
            ps.setInt(6, novaCorrida.getPassageiroId());
            ps.setNull(7, Types.INTEGER);
            ps.setNull(8, Types.INTEGER);
            ps.setInt(9, novaCorrida.getStatus());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        novaCorrida.setIdCorrida(generatedKeys.getInt(1));
                        return novaCorrida;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao inserir corrida: " + e.getMessage());
        }
        
        return null;
    }


    public Corrida buscarCorridaAtivaPorMotorista(int idMotorista) {
        String sql = """
            SELECT 
                c.id_corrida, c.origem, c.destino, c.fator_transito, c.preco, c.data_corrida,
                p.nome AS nome_passageiro, m.nome AS nome_motorista, v.modelo AS modelo_veiculo,
                c.passageiro_id, c.motorista_id, c.veiculo_id, c.status
            FROM corrida c
            JOIN passageiro p ON c.passageiro_id = p.id_passageiro
            LEFT JOIN motorista m ON c.motorista_id = m.id_motorista
            LEFT JOIN veiculo v ON c.veiculo_id = v.id_veiculo
            WHERE c.motorista_id = ? AND c.status IN (2, 3)
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMotorista);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Corrida(
                    rs.getString("origem"), rs.getString("destino"), rs.getInt("id_corrida"),
                    rs.getInt("fator_transito"), rs.getFloat("preco"), rs.getString("data_corrida"),
                    rs.getString("nome_passageiro"), rs.getString("nome_motorista"), rs.getString("modelo_veiculo"),
                    rs.getInt("passageiro_id"), rs.getInt("motorista_id"), rs.getInt("veiculo_id"),
                    rs.getInt("status")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar corrida ativa por motorista: " + e.getMessage());
        }
        return null;
    }
    public List<Corrida> listarCorridasPorMotoristaEPeriodo(int idMotorista, String dataInicio, String dataFim) {
        List<Corrida> corridas = new ArrayList<>();
        String sql = """
            SELECT 
                c.id_corrida, c.origem, c.destino, c.fator_transito, c.preco, c.data_corrida,
                p.nome AS nome_passageiro, m.nome AS nome_motorista, v.modelo AS modelo_veiculo,
                c.passageiro_id, c.motorista_id, c.veiculo_id, c.status
            FROM corrida c
            JOIN passageiro p ON c.passageiro_id = p.id_passageiro
            JOIN motorista m ON c.motorista_id = m.id_motorista
            LEFT JOIN veiculo v ON c.veiculo_id = v.id_veiculo
            WHERE c.motorista_id = ? AND c.status = 4 AND c.data_corrida >= ? AND c.data_corrida < ?
            ORDER BY c.data_corrida DESC
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            LocalDate fim = LocalDate.parse(dataFim).plusDays(1);
            
            ps.setInt(1, idMotorista);
            ps.setString(2, dataInicio);
            ps.setString(3, fim.toString());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Corrida corrida = new Corrida(
                    rs.getString("origem"), rs.getString("destino"), rs.getInt("id_corrida"),
                    rs.getInt("fator_transito"), rs.getFloat("preco"), rs.getString("data_corrida"),
                    rs.getString("nome_passageiro"), rs.getString("nome_motorista"), rs.getString("modelo_veiculo"),
                    rs.getInt("passageiro_id"), rs.getInt("motorista_id"), rs.getInt("veiculo_id"),
                    rs.getInt("status")
                );
                corridas.add(corrida);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar corridas por motorista e período: " + e.getMessage());
        }
        return corridas;
    }
}