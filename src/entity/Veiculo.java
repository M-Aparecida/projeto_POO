package entity;

public class Veiculo{
 private String placa;
    private String modelo;
    private int ano;
    private boolean estaEmUso;

    public Veiculo(String placa,String modelo,int ano) {
        if (placa == null || modelo == null || ano <= 0) {
            throw new IllegalArgumentException("Dados inválidos para criação do veículo.");
        }
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.estaEmUso = false;
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
        return estaEmUso;
    }

    public void setEmUso(boolean emUso) {
        this.estaEmUso = emUso;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Modelo: " + modelo + ", Ano: " + ano;
    }
}
    
