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
    String sql = "INSERT INTO motorista(nome, cpf, email, telefone, senha, idade, genero, qtd_corridas, avaliacao_media, numero_cnh, disponivel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
    String sql = "SELECT * FROM motorista";
    List<Motorista> motoristas = new ArrayList<>();

    try (Connection conn = Conexao.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Motorista p = new Motorista(
                rs.getInt("id_motorista"),
                rs.getString("nome"), 
                rs.getString("cpf"), 
                rs.getString("email"), 
                rs.getString("telefone"), 
                rs.getString("senha"), 
                rs.getString("genero"),      
                rs.getInt("idade"),
                rs.getInt("numero_cnh"), 
                rs.getBoolean("disponivel"), 
                rs.getInt("qtd_corridas"), 
                rs.getFloat("avaliacao_media") 
            );
            motoristas.add(p);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar motoristas: " + e.getMessage());
    }

    return motoristas;
}


    
}





