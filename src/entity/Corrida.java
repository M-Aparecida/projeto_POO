package entity;

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
        status = status;
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
        status = status;
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
        status = status;
    }


    
    
}


