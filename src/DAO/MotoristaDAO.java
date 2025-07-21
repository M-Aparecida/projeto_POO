package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Statement;
import java.time.LocalDate;

import conexao.Conexao;
import entity.Motorista;
/**
 * Data Access Object (DAO) para a entidade Motorista.
 * Esta classe é responsável por todas as interações com o banco de dados
 * relacionadas à tabela 'motorista', incluindo cadastro, busca e atualização.
 */
public class MotoristaDAO{
    /**
     * Cadastra um novo motorista no banco de dados.
     * Após a inserção, o ID gerado pelo banco é atribuído ao objeto motorista.
     *
     * @param motorista O objeto Motorista contendo todos os dados a serem inseridos.
     * @return true se o cadastro for bem-sucedido, false caso contrário.
     */
    public boolean cadastrarMotorista(Motorista motorista) {
        String sql = "INSERT INTO motorista(nome, cpf, email, telefone, senha, idade, genero, qtd_corridas_concluidas, avaliacao_media, numero_cnh, disponivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, motorista.getNome());
            ps.setString(2, motorista.getCpf());
            ps.setString(3, motorista.getEmail());
            ps.setString(4, motorista.getTelefone());
            ps.setString(5, motorista.getSenha());
            ps.setInt(6, motorista.getIdade());
            ps.setString(7, motorista.getGenero());
            ps.setInt(8, motorista.getQuantidadeCorridas());
            ps.setFloat(9, motorista.getAvaliacaoMedia());
            ps.setLong(10, motorista.getNumeroCnh());
            ps.setBoolean(11, motorista.isDisponivel());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    motorista.setIdMotorista(rs.getInt(1));
                }
            }

            System.out.println("Motorista '" + motorista.getNome() + "' cadastrado com sucesso! ID: " + motorista.getIdMotorista());
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar motorista: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Lista todos os motoristas cadastrados no banco de dados.
     *
     * @return Uma lista de objetos Motorista. Retorna uma lista vazia se não houver nenhum.
     */ 
    public List<Motorista> listarMotoristas() {
        String sql = "SELECT * FROM motorista ORDER BY id_motorista";
        List<Motorista> motoristas = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Motorista m = new Motorista(
                    rs.getInt("id_motorista"),
                    rs.getString("nome"), 
                    rs.getString("cpf"), 
                    rs.getString("email"), 
                    rs.getString("telefone"), 
                    rs.getString("senha"), 
                    rs.getString("genero"),      
                    rs.getInt("idade"),
                    rs.getLong("numero_cnh"), 
                    rs.getBoolean("disponivel"), 
                    rs.getInt("qtd_corridas_concluidas"), 
                    rs.getFloat("avaliacao_media") 
                );
                motoristas.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar motoristas: " + e.getMessage());
        }

        return motoristas;
    }

    /**
     * Busca um motorista pelo nome. A busca não diferencia maiúsculas de minúsculas e
     * encontra nomes que contenham o termo pesquisado.
     *
     * @param nome O nome ou parte do nome do motorista a ser buscado.
     * @return Um objeto Motorista se encontrado, caso contrário, retorna null.
     */
    public Motorista buscarMotoristaPorNome(String nome) {
        String sql = "SELECT * FROM motorista WHERE nome ILIKE ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);

            ps.setString(1, "%" + nome + "%"); 

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Motorista(
                rs.getInt("id_motorista"),
                rs.getString("nome"), 
                rs.getString("cpf"), 
                rs.getString("email"), 
                rs.getString("telefone"), 
                rs.getString("senha"), 
                rs.getString("genero"),      
                rs.getInt("idade"),
                rs.getLong("numero_cnh"), 
                rs.getBoolean("disponivel"), 
                rs.getInt("qtd_corridas_concluidas"), 
                rs.getFloat("avaliacao_media") 
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar motorista por nome: " + e.getMessage());
        }

        return null;
    }

    /**
     * Busca um motorista pelo seu endereço de e-mail.
     * A busca não diferencia maiúsculas de minúsculas.
     *
     * @param email O e-mail exato do motorista a ser buscado.
     * @return Um objeto Motorista se encontrado, caso contrário, retorna null.
     */
    public Motorista buscarPorEmail(String email) {
        String sql = "SELECT * FROM motorista WHERE email ILIKE  ?";


        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Motorista(
                rs.getInt("id_motorista"),
                rs.getString("nome"), 
                rs.getString("cpf"), 
                rs.getString("email"), 
                rs.getString("telefone"), 
                rs.getString("senha"), 
                rs.getString("genero"),      
                rs.getInt("idade"),
                rs.getLong("numero_cnh"), 
                rs.getBoolean("disponivel"), 
                rs.getInt("qtd_corridas_concluidas"), 
                rs.getFloat("avaliacao_media") 
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar motorista por e-mail: " + e.getMessage());
        }
        return null;
    }

    /**
     * Busca um motorista pelo seu ID único.
     *
     * @param id O ID do motorista a ser buscado.
     * @return Um objeto Motorista se encontrado, caso contrário, retorna null.
     */
    public Motorista buscarPorId(int id) {
        String sql = "SELECT * FROM motorista WHERE id_motorista = ?";

        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Motorista(
                    rs.getInt("id_motorista"),
                    rs.getString("nome"), 
                    rs.getString("cpf"), 
                    rs.getString("email"), 
                    rs.getString("telefone"), 
                    rs.getString("senha"), 
                    rs.getString("genero"),      
                    rs.getInt("idade"),
                    rs.getLong("numero_cnh"), 
                    rs.getBoolean("disponivel"), 
                    rs.getInt("qtd_corridas_concluidas"), 
                    rs.getFloat("avaliacao_media") 
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar motorista por ID: " + e.getMessage());
        }

        return null;
    }

    /**
     * Altera o nome de um motorista específico.
     *
     * @param novoNome O novo nome a ser atribuído.
     * @param id_motorista O ID do motorista a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarNome(String novoNome, int id_motorista){
        String sql = "UPDATE motorista SET nome = ? where id_motorista = ?";
         
        try(Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, novoNome);
                ps.setInt(2, id_motorista);
                ps.executeUpdate();
                return true;
        }catch (SQLException e) {
            System.out.println("Erro ao alterar nome" + e.getMessage());
            return false;
        }
    }
    /**
     * Altera o CPF de um motorista específico.
     *
     * @param novoCpf O novo CPF a ser atribuído.
     * @param id_motorista O ID do motorista a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarCpf(String novoCpf, int id_motorista) {
        String sql = "UPDATE motorista SET cpf = ? WHERE id_motorista = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoCpf);
            ps.setInt(2, id_motorista);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar CPF: " + e.getMessage());
            return false;
        }
    }

    /**
     * Altera o email de um motorista específico.
     *
     * @param novoEmail O novo email a ser atribuído.
     * @param id_motorista O ID do motorista a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarEmail(String novoEmail, int id_motorista) {
        String sql = "UPDATE motorista SET email = ? WHERE id_motorista = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoEmail);
            ps.setInt(2, id_motorista);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar e-mail: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Altera o telefone de um motorista específico.
     *
     * @param novoTelefone O novo telefone a ser atribuído.
     * @param id_motorista O ID do motorista a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarTelefone(String novoTelefone, int id_motorista) {
        String sql = "UPDATE motorista SET telefone = ? WHERE id_motorista = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novoTelefone);
            ps.setInt(2, id_motorista);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar telefone: " + e.getMessage());
            return false;
        }
    }

    /**
     * Altera a senha de um motorista específico.
     *
     * @param novaSenha A nova senha a ser atribuída.
     * @param id_motorista O ID do motorista a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarSenha(String novaSenha, int id_motorista) {
        String sql = "UPDATE motorista SET senha = ? WHERE id_motorista = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, novaSenha);
            ps.setInt(2, id_motorista);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar telefone: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Altera o número da CNH de um motorista específico.
     *
     * @param novoNumero O novo número da CNH a ser atribuído.
     * @param id_motorista O ID do motorista a ser atualizado.
     * @return true se a operação for bem-sucedida, false em caso de erro.
     */
    public boolean alterarNumeroCnh(long novoNumero, int id_motorista) {
        String sql = "UPDATE motorista SET numero_cnh = ? WHERE id_motorista = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, novoNumero);
            ps.setInt(2, id_motorista);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao alterar numero de cnh: " + e.getMessage());
            return false;
        }
    }

    /**
     * Atualiza a avaliação média e a quantidade de corridas de um motorista
     * após a conclusão de uma nova corrida.
     *
     * @param avaliacao A nota da nova corrida a ser adicionada.
     * @param id_motorista O ID do motorista que será avaliado.
     */
    public void updateAvaliacao(float avaliacao, int id_motorista){
        String sqlQtdCorridas = "SELECT qtd_corridas_concluidas, avaliacao_media FROM motorista WHERE id_motorista = ?";
        int qtdCorridas = 0;
        float avaliacaoMedia = 0;
        float novaAvalicaoMedia = 0;
        
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQtdCorridas)) {
            ps.setInt(1, id_motorista);
            ResultSet rs = ps.executeQuery();
            rs.next();
            qtdCorridas = rs.getInt("qtd_corridas_concluidas");
            avaliacaoMedia = rs.getFloat("avaliacao_media");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar qtd_corridas_concluidas e avaliacao_media: " + e.getMessage());
        }
        
        novaAvalicaoMedia = ((avaliacaoMedia * qtdCorridas) + avaliacao)/ (qtdCorridas+1);
    
        String sql = "UPDATE motorista SET avaliacao_media = ?, qtd_corridas_concluidas = qtd_corridas_concluidas+1 WHERE id_motorista = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setFloat(1, novaAvalicaoMedia);
            ps.setInt(2, id_motorista);
            ps.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("Erro ao alterar a avalição média: " + e.getMessage());
        }
    }

    /**
     * Altera o status de disponibilidade de um motorista.
     *
     * @param disponivel O novo status de disponibilidade (true para disponível, false para indisponível).
     * @param idMotorista O ID do motorista a ser atualizado.
     * @return true se a alteração foi bem-sucedida, false em caso de erro.
     */
    public boolean alterarDisponibilidade(boolean disponivel, int idMotorista) {
        String sql = "UPDATE motorista SET disponivel = ? WHERE id_motorista = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, disponivel);
            ps.setInt(2, idMotorista);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao alterar disponibilidade do motorista: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gera um relatório de faturamento geral para um motorista.
     * Contabiliza corridas finalizadas, canceladas e o faturamento total.
     *
     * @param idMotorista O ID do motorista para o qual o relatório será gerado.
     * @return Um Map contendo 'corridasFinalizadas', 'corridasCanceladas' e 'faturamentoTotal'. Retorna null em caso de erro.
     */
    public Map<String, Number> gerarRelatorioFaturamento(int idMotorista) {
        String sql = """
            SELECT
                COUNT(CASE WHEN status = 4 THEN 1 END) AS corridas_finalizadas,
                COUNT(CASE WHEN status = 5 THEN 1 END) AS corridas_canceladas,
                SUM(CASE WHEN status = 4 THEN preco ELSE 0 END) AS faturamento_total
            FROM corrida
            WHERE motorista_id = ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idMotorista);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Map<String, Number> relatorio = new HashMap<>();
                relatorio.put("corridasFinalizadas", rs.getInt("corridas_finalizadas"));
                relatorio.put("corridasCanceladas", rs.getInt("corridas_canceladas"));
                relatorio.put("faturamentoTotal", rs.getDouble("faturamento_total"));
                return relatorio;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório de faturamento: " + e.getMessage());
        }
        return null;
    }

    /**
     * Gera um relatório de faturamento para um motorista dentro de um período de tempo específico.
     *
     * @param idMotorista O ID do motorista.
     * @param dataInicio A data de início do período (formato 'AAAA-MM-DD').
     * @param dataFim A data de fim do período (formato 'AAAA-MM-DD').
     * @return Um Map contendo as estatísticas do período. Retorna null em caso de erro.
     */
    public Map<String, Number> gerarRelatorioFaturamentoPorPeriodo(int idMotorista, String dataInicio, String dataFim) {
        String sql = """
            SELECT
                COUNT(CASE WHEN status = 4 THEN 1 END) AS corridas_finalizadas,
                COUNT(CASE WHEN status = 5 THEN 1 END) AS corridas_canceladas,
                SUM(CASE WHEN status = 4 THEN preco ELSE 0 END) AS faturamento_total
            FROM corrida
            WHERE motorista_id = ? AND data_corrida >= ? AND data_corrida < ?
        """;

        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            LocalDate fim = LocalDate.parse(dataFim).plusDays(1);

            ps.setInt(1, idMotorista);
            ps.setString(2, dataInicio);
            ps.setString(3, fim.toString());
            
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Map<String, Number> relatorio = new HashMap<>();
                relatorio.put("corridasFinalizadas", rs.getInt("corridas_finalizadas"));
                relatorio.put("corridasCanceladas", rs.getInt("corridas_canceladas"));
                relatorio.put("faturamentoTotal", rs.getDouble("faturamento_total"));
                return relatorio;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao gerar relatório de faturamento por período: " + e.getMessage());
        }
        return null;
    }

}