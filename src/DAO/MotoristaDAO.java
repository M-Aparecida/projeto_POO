package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;
import conexao.Conexao;
import entity.Motorista;
public class MotoristaDAO{
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

}




