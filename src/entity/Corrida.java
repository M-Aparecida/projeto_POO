package entity;

import java.util.List;

import DAO.CorridaDAO;

public class Corrida {
    private String origem;
    private String destino;
    private int idCorrida;
    private int fatorTransito;
    private float preco;
    private String data;
    private String passageiro;
    private String motorista;
    private String veiculo;
    private int passageiroId;
    private int motoristaId;
    private int veiculoId;
    private int status;

    // exibir
    public Corrida(String origem, String destino, int idCorrida, int fatorTransito, float preco, String data,
            String passageiro, String motorista, String veiculo, int passageiroId, int motoristaId, int veiculoId,
            int status) {
        this.origem = origem;
        this.destino = destino;
        this.idCorrida = idCorrida;
        this.fatorTransito = fatorTransito;
        this.preco = preco;
        this.data = data;
        this.passageiro = passageiro;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.passageiroId = passageiroId;
        this.motoristaId = motoristaId;
        this.veiculoId = veiculoId;
        this.status = status;
    }

    //inserir
    public Corrida(String origem, String destino, int fatorTransito, float preco, String data, String passageiro,
            String motorista, String veiculo, int passageiroId, int motoristaId, int veiculoId, int status) {
        this.origem = origem;
        this.destino = destino;
        this.fatorTransito = fatorTransito;
        this.preco = preco;
        this.data = data;
        this.passageiro = passageiro;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.passageiroId = passageiroId;
        this.motoristaId = motoristaId;
        this.veiculoId = veiculoId;
        this. status = status;
    }

    public static void listarCorridas() {
        CorridaDAO dao = new CorridaDAO();
        List<Corrida> corridas = dao.listarTodasCorridas();

        System.out.printf("| %-40s | %-41s | %-19s | %-27s | %-17s | %-18s | %-7s | %-6s |\n",
                 "Origem", "Destino", "Data", "Passageiro", "Motorista", "Veículo", "Preço", "Status");
        System.out.println("|------------------------------------------|-------------------------------------------|---------------------|-----------------------------|-------------------|--------------------|---------|--------|");

        for (Corrida c : corridas) {
            System.out.printf("| %-40s | %-41s | %-19s | %-27s | %-17s | %-18s | %-7.2f | %-6d |\n",
                    c.getOrigem(),
                    c.getDestino(),
                    c.getData(),
                    c.getPassageiro(),
                    c.getMotorista(),
                    c.getVeiculo(),
                    c.getPreco(),
                    c.getStatus()
            );
        }
    }

    public static void listarCorridas(String data1, String data2) {
        CorridaDAO dao = new CorridaDAO();
        List<Corrida> corridas = dao.listarCorridasPorPeriodo(data1, data2);

        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida encontrada no período de " + data1 + " a " + data2 + ".");
            return;
        }

        System.out.printf("| %-40s | %-41s | %-19s | %-27s | %-17s | %-18s | %-7s | %-6s |\n",
                 "Origem", "Destino", "Data", "Passageiro", "Motorista", "Veículo", "Preço", "Status");
        System.out.println("|------------------------------------------|-------------------------------------------|---------------------|-----------------------------|-------------------|--------------------|---------|--------|");

        for (Corrida c : corridas) {
            System.out.printf("| %-40s | %-41s | %-19s | %-27s | %-17s | %-18s | %-7.2f | %-6d |\n",
                    c.getOrigem(),
                    c.getDestino(),
                    c.getData(),
                    c.getPassageiro(),
                    c.getMotorista(),
                    c.getVeiculo(),
                    c.getPreco(),
                    c.getStatus()
            );
        }
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getIdCorrida() {
        return idCorrida;
    }

    public void setIdCorrida(int idCorrida) {
        this.idCorrida = idCorrida;
    }

    public int getFatorTransito() {
        return fatorTransito;
    }

    public void setFatorTransito(int fatorTransito) {
        this.fatorTransito = fatorTransito;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public int getPassageiroId() {
        return passageiroId;
    }

    public void setPassageiroId(int passageiroId) {
        this.passageiroId = passageiroId;
    }

    public int getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(int motoristaId) {
        this.motoristaId = motoristaId;
    }

    public int getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(int veiculoId) {
        this.veiculoId = veiculoId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    
    
}


