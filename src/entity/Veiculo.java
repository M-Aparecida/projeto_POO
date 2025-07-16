package entity;
import java.util.List;

import DAO.VeiculoDAO;

public class Veiculo{
    private int idVeiculo;
    private String placa;
    private String modelo;
    private int ano;
    private int capacidade;
    private boolean estaEmUso;
    private static int quantidadeVeiculos;


    public Veiculo(int idVeiculo, String placa, String modelo, int ano, int capacidade) throws IllegalArgumentException {
        if (placa == null || modelo == null || ano <= 0) {
            throw new IllegalArgumentException("Dados inválidos para criação do veículo.");
        }
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.estaEmUso = false;

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

}

  
    
