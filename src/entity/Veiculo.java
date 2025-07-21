package entity;
import java.util.List;
import java.util.stream.Collectors;

import DAO.VeiculoDAO;

/**
 * Representa um Veículo no sistema.
 * Esta classe armazena os dados de um veículo, como placa, modelo e capacidade,
 * e também gerencia seu estado (se está em uso ou não).
 *
 */
public class Veiculo{
    private int idVeiculo;
    private String placa;
    private String modelo;
    private int ano;
    private int capacidade;
    private boolean estaEmUso;
    private static int quantidadeVeiculos;
    private int id_motorista;

    /**
     * Construtor para criar uma instância de Veiculo.
     * Realiza uma validação inicial dos dados e atualiza a contagem estática de veículos.
     *
     * @param idVeiculo    O ID único do veículo.
     * @param placa        A placa do veículo.
     * @param modelo       O modelo do veículo.
     * @param ano          O ano de fabricação do veículo.
     * @param capacidade   A capacidade de passageiros.
     * @param id_motorista O ID do motorista associado a este veículo.
     * @throws IllegalArgumentException se placa, modelo ou ano forem inválidos.
     */
    public Veiculo(int idVeiculo, String placa, String modelo, int ano, int capacidade, int id_motorista) throws IllegalArgumentException {
        if (placa == null || modelo == null || ano <= 0) {
            throw new IllegalArgumentException("Dados inválidos para criação do veículo.");
        }
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.estaEmUso = false;
        this.id_motorista = id_motorista;

        VeiculoDAO dao = new VeiculoDAO();
        quantidadeVeiculos = dao.qtd_veiculos();

    }

    /**
     * Modifica o modelo e a capacidade desta instância de veículo no banco de dados.
     * Se a operação no banco for bem-sucedida, os atributos do objeto também são atualizados.
     *
     * @param novoModelo      O novo modelo a ser atribuído.
     * @param novaCapacidade  A nova capacidade de passageiros.
     * @return true se a modificação for bem-sucedida, false caso contrário.
     */
    public boolean modificarValoresVeiculo(String novoModelo, int novaCapacidade) {
        VeiculoDAO dao = new VeiculoDAO();
        boolean sucesso = dao.modificarValoresVeiculo(this.placa, this.modelo, novoModelo, novaCapacidade);
        if (sucesso) {
            this.modelo = novoModelo;
            this.capacidade = novaCapacidade;
        }
        return sucesso;
    }

    /**
     * Deleta esta instância de veículo do banco de dados, usando sua placa como identificador.
     *
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    public boolean deletarVeiculo() {
        VeiculoDAO dao = new VeiculoDAO();
        return dao.deletarVeiculo(this.placa);
    }

    public Veiculo getDadosVeiculo() {
        return this;
    }

    /**
     * Lista todos os veículos disponíveis (que não estão em uso).
     *
     * @return Uma lista de objetos Veiculo que não estão marcados como "em uso".
     */
    public static List<Veiculo> listarVeiculos() {
        VeiculoDAO dao = new VeiculoDAO();
        return dao.listarVeiculos().stream()
            .filter(v -> !v.isEstaEmUso())
            .collect(Collectors.toList());
    }

    /**
     * Busca um veículo disponível pela placa.
     *
     * @param placa A placa do veículo a ser buscado.
     * @return O objeto Veiculo se for encontrado e não estiver em uso, caso contrário, retorna null.
     */
    public static Veiculo buscar(String placa) {
        VeiculoDAO dao = new VeiculoDAO();
        Veiculo v = dao.buscarPorPlaca(placa);
        return (v != null && !v.isEstaEmUso()) ? v : null;
    }

    /**
     * Busca um veículo disponível pelo modelo e ano.
     *
     * @param modelo O modelo do veículo.
     * @param ano    O ano de fabricação do veículo.
     * @return O objeto Veiculo se for encontrado e não estiver em uso, caso contrário, retorna null.
     */
    public static Veiculo buscar(String modelo, int ano) {
        VeiculoDAO dao = new VeiculoDAO();
        Veiculo v = dao.buscarPorModeloEAno(modelo, ano);
        return (v != null && !v.isEstaEmUso()) ? v : null;
    }

    // --- GETTERS E SETTERS ---
    public int getIdVeiculo() {
        return idVeiculo;
    }


    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }


    public String getPlaca() {
        return placa;
    }


    public void setPlaca(String placa) {
        this.placa = placa;
    }


    public String getModelo() {
        return modelo;
    }
    

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public int getAno() {
        return ano;
    }


    public void setAno(int ano) {
        this.ano = ano;
    }


    public int getCapacidade() {
        return capacidade;
    }


    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }


    public boolean isEstaEmUso() {
        return estaEmUso;
    }


    public void setEstaEmUso(boolean estaEmUso) {
        this.estaEmUso = estaEmUso;
    }


    public static int getQuantidadeVeiculos() {
        return quantidadeVeiculos;
    }


    public static void setQuantidadeVeiculos(int quantidadeVeiculos) {
        Veiculo.quantidadeVeiculos = quantidadeVeiculos;
    }

      public int getId_motorista() {
        return id_motorista;
    }


    public void setId_motorista(int id_motorista) {
        this.id_motorista = id_motorista;
    }
}
