package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Veiculo;

/**
 * Data Access Object (DAO) para a entidade Veiculo.
 * Esta classe gerencia todas as operações de banco de dados
 * (CRUD - Criar, Ler, Atualizar, Deletar) para a tabela 'veiculo'.
 */
public class VeiculoDAO {
    /**
     * Busca e retorna uma lista com todos os veículos cadastrados no sistema.
     *
     * @return Uma lista de objetos Veiculo. Retorna uma lista vazia se não houver nenhum.
     */
    public List<Veiculo> listarVeiculos(){
        String sql = "SELECT * FROM veiculo";

        List<Veiculo> veiculos = new ArrayList<>();
         
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Veiculo v = new Veiculo(rs.getInt("id_veiculo"), rs.getString("placa"), rs.getString("modelo"), rs.getInt("ano"), rs.getInt("capacidade"), rs.getInt("id_veiculo"));
                    veiculos.add(v);
                }
        }catch (SQLException e) {
            System.out.println("erro ao buscar veículos: " + e.getMessage());
        }

        return veiculos;
    }

     /**
     * Conta o número total de veículos cadastrados no banco de dados.
     *
     * @return O número total de veículos como um inteiro, ou -1 em caso de erro.
     */
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
            System.out.println("erro ao contar veículos: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Modifica o modelo e a capacidade de um veículo específico, identificado pela placa e modelo atuais.
     *
     * @param placa       A placa do veículo a ser modificado.
     * @param modelo      O modelo atual do veículo.
     * @param novoModelo  O novo modelo a ser atribuído.
     * @param capacidade  A nova capacidade de passageiros do veículo.
     * @return true se a atualização for bem-sucedida (pelo menos uma linha afetada), false caso contrário.
     */
    public boolean modificarValoresVeiculo(String placa, String modelo, String novoModelo, int capacidade){
        String sql = "UPDATE veiculo SET modelo = ?, capacidade = ? WHERE placa = ? AND modelo = ?";
        
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, novoModelo);
            ps.setInt(2, capacidade);
            ps.setString(3, placa);
            ps.setString(4, modelo);

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("erro ao modificar veículo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deleta um veículo do banco de dados com base na sua placa.
     *
     * @param placa A placa do veículo a ser deletado.
     * @return true se a exclusão for bem-sucedida (pelo menos uma linha afetada), false caso contrário.
     */
    public boolean deletarVeiculo(String placa){
        String sql = "DELETE FROM veiculo WHERE placa = ?";
        
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, placa);
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("erro ao deletar veículo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca um veículo específico pela sua placa.
     *
     * @param placa A placa do veículo a ser buscado.
     * @return Um objeto Veiculo se encontrado, caso contrário, retorna null.
     */
    public Veiculo buscarPorPlaca(String placa){
        String sql = "SELECT * FROM veiculo WHERE placa = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Veiculo(rs.getInt("id_veiculo"), rs.getString("placa"), rs.getString("modelo"), rs.getInt("ano"), rs.getInt("capacidade"), rs.getInt("id_motorista"));
            }

        } catch (SQLException e) {
            System.out.println("erro ao buscar veículo por placa: " + e.getMessage());
        }

        return null;
    }

     /**
     * Busca um veículo pelo seu modelo e ano.
     * Nota: Se houver múltiplos veículos com o mesmo modelo e ano, este método retornará apenas o primeiro encontrado.
     *
     * @param modelo O modelo do veículo.
     * @param ano    O ano de fabricação do veículo.
     * @return Um objeto Veiculo correspondente ao primeiro resultado da busca, ou null se nenhum for encontrado.
     */
    public Veiculo buscarPorModeloEAno(String modelo, int ano){
        String sql = "SELECT * FROM veiculo WHERE modelo = ? AND ano = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, modelo);
            ps.setInt(2, ano);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Veiculo(rs.getInt("id_veiculo"), rs.getString("placa"), rs.getString("modelo"), rs.getInt("ano"), rs.getInt("capacidade"), rs.getInt("id_motorista"));
            }

        } catch (SQLException e) {
            System.out.println("erro ao buscar veículo por modelo e ano: " + e.getMessage());
        }

        return null;
    }

    /**
     * Busca os dados de um veículo pelo seu ID único.
     *
     * @param id O ID do veículo a ser buscado.
     * @return Um objeto Veiculo se encontrado, caso contrário, retorna null.
     */
    public Veiculo getDadosVeiculo(int id){
        String sql = "SELECT * FROM veiculo WHERE id_veiculo = ?";

        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Veiculo(rs.getInt("id_veiculo"), rs.getString("placa"), rs.getString("modelo"), rs.getInt("ano"), rs.getInt("capacidade"), rs.getInt("id_motorista"));
            }

        } catch (SQLException e) {
            System.out.println("erro ao obter dados do veículo: " + e.getMessage());
        }

        return null;
    }
}