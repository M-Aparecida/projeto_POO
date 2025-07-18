package entity;

public class Corrida {
    private String origem;
    private String destino;
    private Integer idcorrida;
    private Integer fatortransito;
    private String preco;
    private String data;
    private String passageiro;
    private String motorista;
    private String veiculo;
    private Integer passageiroId;
    private Integer motoristaId;
    private Integer veiculoId;
    private Integer status; // 0 = disponível, 1 = em andamento, 2 = finalizada, 3 = cancelada

    public Corrida() {}

    public Corrida(Integer idcorrida, String origem, String destino, Integer fatortransito, String preco, String data, String passageiro, String motorista, String veiculo, Integer passageiroId, Integer motoristaId, Integer veiculoId, Integer status) {
        this.idcorrida = idcorrida;
        this.origem = origem;
        this.destino = destino;
        this.fatortransito = fatortransito;
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

    public float calcularDistancia(String origem, String destino) {
        // Lógica fictícia de cálculo
        return origem.length() + destino.length();
    }

    public boolean modificarValoresCorrida(String origem, String destino, int fatorTransito, String preco) {
        if (origem != null && destino != null && preco != null) {
            this.origem = origem;
            this.destino = destino;
            this.fatortransito = fatorTransito;
            this.preco = preco;
            return true;
        }
        return false;
    }

    public boolean realizarCorrida(String origem, String destino) {
        if (origem != null && destino != null) {
            this.origem = origem;
            this.destino = destino;
            this.status = 1; // Corrida em andamento
            return true;
        }
        return false;
    }

    public boolean terminarCorrida(Integer idCorrida) {
        if (this.idcorrida.equals(idCorrida)) {
            this.status = 2; // Finalizada
            return true;
        }
        return false;
    }

    public boolean cancelarCorrida() {
        this.status = 3; // Cancelada
        return true;
    }

    public Corrida getDadosCorrida() {
        return this;
    }

    public void listarCorridas() {
        System.out.println(this.toString());
    }

    public void listarCorridas(String data1, String data2) {
        if (this.data != null && this.data.compareTo(data1) >= 0 && this.data.compareTo(data2) <= 0) {
            System.out.println(this.toString());
        }
    }

    public void listarCorridasDisponiveis() {
        if (this.status != null && this.status == 0) {
            System.out.println(this.toString());
        }
    }

    public void avaliarCorrida(Integer idCorrida) {
        if (this.idcorrida.equals(idCorrida)) {
            System.out.println("Corrida avaliada com sucesso.");
        }
    }

    public void historicoDeCorridas(int cnh) {
        if (this.motoristaId != null && this.motoristaId == cnh) {
            System.out.println(this.toString());
        }
    }

    public void historicoDeCorridas(String cpf) {
        if (this.passageiro != null && this.passageiro.equals(cpf)) {
            System.out.println(this.toString());
        }
    }

    public Corrida buscarCorrida(Integer idCorrida) {
        if (this.idcorrida.equals(idCorrida)) {
            return this;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Corrida{" +
                "idcorrida=" + idcorrida +
                ", origem='" + origem + '\'' +
                ", destino='" + destino + '\'' +
                ", data='" + data + '\'' +
                ", preco='" + preco + '\'' +
                ", status=" + status +
                '}';
    }

    // Getters e Setters omitidos por brevidade
}
