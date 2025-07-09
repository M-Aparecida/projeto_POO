package entity;

public class Veiculo extends Pessoa {
 private String placa;
    private String modelo;
    private int ano;
    private boolean emUso;

    public Veiculo(String placa,String modelo,int ano) {
        if (placa == null || modelo == null || ano <= 0) {
            throw new IllegalArgumentException("Dados inválidos para criação do veículo.");
        }
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.emUso = false;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public boolean isEmUso() {
        return emUso;
    }

    public void setEmUso(boolean emUso) {
        this.emUso = emUso;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Modelo: " + modelo + ", Ano: " + ano;
    }
}
    
