package entity;
import java.util.List;
import java.util.stream.Collectors;

import DAO.VeiculoDAO;

public class Veiculo{
    private int idVeiculo;
    private String placa;
    private String modelo;
    private int ano;
    private int capacidade;
    private boolean estaEmUso;
    private static int quantidadeVeiculos;
    private int id_motorista;


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

    

    public boolean modificarValoresVeiculo(String novoModelo, int novaCapacidade) {
        VeiculoDAO dao = new VeiculoDAO();
        boolean sucesso = dao.modificarValoresVeiculo(this.placa, this.modelo, novoModelo, novaCapacidade);
        if (sucesso) {
            this.modelo = novoModelo;
            this.capacidade = novaCapacidade;
        }
        return sucesso;
    }

     public boolean deletarVeiculo() {
        VeiculoDAO dao = new VeiculoDAO();
        return dao.deletarVeiculo(this.placa);
    }

    public Veiculo getDadosVeiculo() {
        return this;
    }

    public static List<Veiculo> listarVeiculos() {
        VeiculoDAO dao = new VeiculoDAO();
        return dao.listarVeiculos().stream()
            .filter(v -> !v.isEstaEmUso())
            .collect(Collectors.toList());
    }

    public static Veiculo buscar(String placa) {
        VeiculoDAO dao = new VeiculoDAO();
        Veiculo v = dao.buscarPorPlaca(placa);
        return (v != null && !v.isEstaEmUso()) ? v : null;
    }

    public static Veiculo buscar(String modelo, int ano) {
        VeiculoDAO dao = new VeiculoDAO();
        Veiculo v = dao.buscarPorModeloEAno(modelo, ano);
        return (v != null && !v.isEstaEmUso()) ? v : null;
    }


}




  
    
