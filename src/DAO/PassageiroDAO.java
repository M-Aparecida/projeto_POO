package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import entity.Passageiro;

/**
 * Data Access Object (DAO) para a entidade Passageiro.
 * Esta classe centraliza todas as operações de persistência de dados
 * (Criar, Ler, Atualizar) relacionadas à tabela 'passageiro'.
 */
public class PassageiroDAO {
     /**
     * Busca e retorna uma lista com todos os passageiros cadastrados no sistema.
     *
     * @return Uma lista de objetos Passageiro. Retorna uma lista vazia se não houver nenhum.
     */
    public List<Passageiro> listarPassageiros(){
        String sql = "SELECT * FROM passageiro";

        List<Passageiro> passageiros = new ArrayList<>();
         
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Passageiro p = new Passageiro(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"), rs.getString("telefone"), rs.getString("senha"), rs.getString("genero"), rs.getInt("id_passageiro"), rs.getString("cartao_dados"), rs.getInt("qtd_corridas_concluidas"), rs.getFloat("avaliacao_media"), rs.getInt("idade"));

                    passageiros.add(p);
                }
        }catch (SQLException e) {
            System.out.println("erro ao buscar passageiros" + e.getMessage());
        }

        return passageiros;
    }
    
    /**
     * Insere um novo passageiro no banco de dados.
     *
     * @param passageiro O objeto Passageiro com os dados a serem cadastrados.
     * @return true se o cadastro for bem-sucedido, false em caso de erro.
     */
    public boolean cadastraPassageiro(Passageiro passageiro){
        String sql = "INSERT INTO passageiro (nome, cpf, email, telefone, senha, idade, genero, qtd_corridas_concluidas, avaliacao_media, cartao_dados) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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

    /**
     * Busca um passageiro específico pelo seu ID único.
     *
     * @param id O ID do passageiro a ser buscado.
     * @return Um objeto Passageiro se encontrado, caso contrário, retorna null.
     */
    public Passageiro buscarPorId(int id) {
        String sql = "SELECT * FROM passageiro WHERE id_passageiro = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Passageiro(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("senha"),
                    rs.getString("genero"),
                    rs.getInt("id_passageiro"),
                    rs.getString("cartao_dados"),
                    rs.getInt("qtd_corridas_concluidas"),
                    rs.getInt("avaliacao_media"),
                    rs.getInt("idade")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar passageiro por ID: " + e.getMessage());
        }

        return null;
    }

    /**
     * Altera o nome de um passageiro específico.
     *
     * @param novoNome O novo nome a ser atribuído.
     * @param id_passageiro O ID do passageiro a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarNome(String novoNome, int id_passageiro){
        String sql = "UPDATE passageiro SET nome = ? where id_passageiro = ?";
         
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, novoNome);
                ps.setInt(2, id_passageiro);
                ps.executeUpdate();
                return true;
        }catch (SQLException e) {
            System.out.println("Erro ao alterar nome" + e.getMessage());
            return false;
        }
    }

    /**
     * Altera o CPF de um passageiro específico.
     *
     * @param novoCpf O novo CPF a ser atribuído.
     * @param id_passageiro O ID do passageiro a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarCpf(String novoCpf, int id_passageiro) {
        String sql = "UPDATE passageiro SET cpf = ? WHERE id_passageiro = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoCpf);
            ps.setInt(2, id_passageiro);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar CPF: " + e.getMessage());
            return false;
        }
    }

    /**
     * Altera o email de um passageiro específico.
     *
     * @param novoEmail     O novo email a ser atribuído.
     * @param id_passageiro O ID do passageiro a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarEmail(String novoEmail, int id_passageiro) {
        String sql = "UPDATE passageiro SET email = ? WHERE id_passageiro = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoEmail);
            ps.setInt(2, id_passageiro);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar e-mail: " + e.getMessage());
            return false;
        }
    }

    /**
     * Altera o telefone de um passageiro específico.
     *
     * @param novoTelefone  O novo telefone a ser atribuído.
     * @param id_passageiro O ID do passageiro a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarTelefone(String novoTelefone, int id_passageiro) {
        String sql = "UPDATE passageiro SET telefone = ? WHERE id_passageiro = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoTelefone);
            ps.setInt(2, id_passageiro);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar telefone: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca um passageiro pelo seu endereço de e-mail.
     * A busca requer uma correspondência exata e é sensível a maiúsculas/minúsculas.
     *
     * @param email O e-mail exato do passageiro a ser buscado.
     * @return Um objeto Passageiro se encontrado, caso contrário, retorna null.
     */
    public Passageiro buscarPorEmail(String email) {
        String sql = "SELECT * FROM passageiro WHERE email = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Passageiro(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("telefone"),
                    rs.getString("senha"),
                    rs.getString("genero"),
                    rs.getInt("id_passageiro"),
                    rs.getString("cartao_dados"),
                    rs.getInt("qtd_corridas_concluidas"),
                    rs.getInt("avaliacao_media"),
                    rs.getInt("idade")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar passageiro por e-mail: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Atualiza a avaliação média e a quantidade de corridas de um passageiro
     * após a conclusão de uma nova corrida.
     *
     * @param avaliacao      A nota da nova corrida a ser adicionada à média.
     * @param id_passageiro  O ID do passageiro que será avaliado.
     */
    public void updateAvaliacao(float avaliacao, int id_passageiro){
        String sqlQtdCorridas = "SELECT qtd_corridas_concluidas, avaliacao_media FROM passageiro WHERE id_passageiro = ?";
        int qtdCorridas = 0;
        float avaliacaoMedia = 0;
        float novaAvalicaoMedia = 0;
        
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQtdCorridas)) {
            ps.setInt(1, id_passageiro);
            ResultSet rs = ps.executeQuery();
            rs.next();
            qtdCorridas = rs.getInt("qtd_corridas_concluidas");
            avaliacaoMedia = rs.getFloat("avaliacao_media");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar qtd_corridas_concluidas e avaliacao_media: " + e.getMessage());
        }
        
        novaAvalicaoMedia = ((avaliacaoMedia * qtdCorridas) + avaliacao)/ (qtdCorridas+1);
    
        String sql = "UPDATE passageiro SET avaliacao_media = ?, qtd_corridas_concluidas = qtd_corridas_concluidas+1 WHERE id_passageiro = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, novaAvalicaoMedia);
            ps.setInt(2, id_passageiro);
            ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Erro ao alterar a avalição média: " + e.getMessage());
        }
    }
}
