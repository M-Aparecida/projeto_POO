package entity;

import java.util.List;

import DAO.CorridaDAO;

/**
 * Representa uma Corrida no sistema.
 * Esta classe atua como uma entidade, contendo os dados de uma corrida,
 * e também como uma classe de serviço, com métodos estáticos para interagir
 * com o DAO e exibir informações no console.
 * 
 * @see CorridaDAO
 */
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

    /**
     * Construtor completo para criar um objeto Corrida a partir de dados do banco.
     * Usado principalmente para exibir corridas que já existem.
     *
     * @param origem       Local de partida da corrida.
     * @param destino      Local de chegada da corrida.
     * @param idCorrida    ID único da corrida.
     * @param fatorTransito Fator de multiplicação do preço baseado no trânsito.
     * @param preco        Preço final da corrida.
     * @param data         Data em que a corrida ocorreu.
     * @param passageiro   Nome do passageiro.
     * @param motorista    Nome do motorista.
     * @param veiculo      Modelo do veículo.
     * @param passageiroId ID do passageiro.
     * @param motoristaId  ID do motorista.
     * @param veiculoId    ID do veículo.
     * @param status       Código de status da corrida (1: Solicitada, 2: Aceita, etc.).
     */
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

    /**
     * Construtor para criar uma nova instância de Corrida antes de persistir no banco de dados.
     *
     * @param origem       Local de partida.
     * @param destino      Local de chegada.
     * @param fatorTransito Fator de trânsito.
     * @param preco        Preço calculado.
     * @param data         Data da solicitação.
     * @param passageiroId ID do passageiro solicitante.
     * @param motoristaId  ID do motorista (pode ser 0 ou null inicialmente).
     * @param veiculoId    ID do veículo (pode ser 0 ou null inicialmente).
     * @param status       Status inicial da corrida (geralmente 1 para 'Solicitada').
     */
    public Corrida(String origem, String destino, int fatorTransito, float preco, String data, 
        int passageiroId, int motoristaId, int veiculoId, int status) {
        this.origem = origem;
        this.destino = destino;
        this.fatorTransito = fatorTransito;
        this.preco = preco;
        this.data = data;
        this.passageiroId = passageiroId;
        this.motoristaId = motoristaId;
        this.veiculoId = veiculoId;
        this.status = status;
    }

    /**
     * Busca todas as corridas no banco de dados e as exibe em uma tabela no console.
     */
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

    /**
     * Busca corridas dentro de um período específico e as exibe em uma tabela no console.
     *
     * @param data1 Data de início do período (formato 'AAAA-MM-DD').
     * @param data2 Data de fim do período (formato 'AAAA-MM-DD').
     */
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

    /**
     * Exibe o histórico de corridas de um motorista, identificado pela CNH.
     *
     * @param cnh O número da CNH do motorista.
     */
    public static void historicoDeCorridas(long cnh) {
    CorridaDAO dao = new CorridaDAO();
        List<Corrida> corridas = dao.listarCorridasPorCNH(cnh);

        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida encontrada para o motorista com CNH: " + cnh);
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

    /**
     * Exibe o histórico de corridas de um passageiro, identificado pelo CPF.
     *
     * @param cpf O CPF do passageiro.
     */
    public static void historicoDeCorridas(String cpf) {
        CorridaDAO dao = new CorridaDAO();
        List<Corrida> corridas = dao.listarCorridasPorCPF(cpf);

        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida encontrada para o passageiro com CPF: " + cpf);
            return;
        }

        System.out.printf("| %-5s | %-40s | %-41s | %-19s | %-27s | %-17s | %-18s | %-7s | %-6s |\n",
                "ID","Origem", "Destino", "Data", "Passageiro", "Motorista", "Veículo", "Preço", "Status");
        System.out.println("|------------------------------------------|-------------------------------------------|---------------------|-----------------------------|-------------------|--------------------|---------|--------|");

        for (Corrida c : corridas) {
            System.out.printf("| %-5s | %-40s | %-41s | %-19s | %-27s | %-17s | %-18s | %-7.2f | %-6d |\n",
                    c.getIdCorrida(),        
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

    /**
     * Busca uma corrida pelo seu ID e a retorna.
     *
     * @param idCorrida O ID da corrida a ser buscada.
     * @return O objeto Corrida se encontrado, caso contrário, null.
     */
    public static Corrida buscarCorrida(int idCorrida) {
        CorridaDAO dao = new CorridaDAO();
        Corrida corrida = dao.buscarCorrida(idCorrida);

        if (corrida == null) {
            System.out.println("Corrida com ID " + idCorrida + " não encontrada.");
        }

        return corrida;
    }

    /**
     * Busca e exibe todas as corridas disponíveis (status 1) em uma tabela no console.
     */
    public static void listarCorridasDisponiveis() {
        CorridaDAO dao = new CorridaDAO();
        List<Corrida> corridas = dao.listarCorridasDisponiveis();

        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida disponível no momento.");
            return;
        }

        System.out.printf("| %-3s | %-40s | %-41s | %-19s | %-27s | %-18s | %-7s |\n",
                "ID", "Origem", "Destino", "Data", "Passageiro", "Veículo", "Preço");
        System.out.println("|-----|------------------------------------------|-------------------------------------------|---------------------|-----------------------------|--------------------|---------|");

        for (Corrida c : corridas) {
            System.out.printf("| %-3d | %-40s | %-41s | %-19s | %-27s | %-18s | %-7.2f |\n",
                    c.getIdCorrida(),
                    c.getOrigem(),
                    c.getDestino(),
                    c.getData(),
                    c.getPassageiro(),
                    c.getVeiculo(),
                    c.getPreco()
            );
        }
    }

    /**
     * Modifica um campo específico desta instância da corrida no banco de dados.
     * A alteração só é permitida se o status da corrida for 1 (Solicitada).
     *
     * @param campo     O nome do campo a ser alterado (ex: "origem", "destino", "preco").
     * @param novoValor O novo valor para o campo, como uma String.
     * @return true se a modificação for bem-sucedida, false caso contrário.
     */
    public boolean modificarValoresCorrida(String campo, String novoValor) {
        CorridaDAO dao = new CorridaDAO();

        if (this.status != 1) {
            System.out.println("Você só pode alterar corridas com status 'Solicitada'.");
            return false;
        }

        switch (campo.toLowerCase()) {
            case "origem":
                return dao.alterarOrigem(novoValor, this.idCorrida);
            case "destino":
                return dao.alterarDestino(novoValor, this.idCorrida);
            case "fator":
                try {
                    int novoFator = Integer.parseInt(novoValor);
                    return dao.alterarFatorTransito(novoFator, this.idCorrida);
                } catch (NumberFormatException e) {
                    System.out.println("Fator de trânsito inválido.");
                    return false;
                }
            case "preco":
                try {
                    float novoPreco = Float.parseFloat(novoValor);
                    return dao.alterarPreco(novoPreco, this.idCorrida);
                } catch (NumberFormatException e) {
                    System.out.println("Preço inválido.");
                    return false;
                }
            case "data":
                return dao.alterarDataCorrida(novoValor, this.idCorrida);
             case "motorista_id":
                try {
                    int novoMotoristaId = Integer.parseInt(novoValor);
                    return dao.alterarMotoristaId(novoMotoristaId, this.idCorrida);
                } catch (NumberFormatException e) {
                    System.out.println("ID do motorista inválido.");
                    return false;
                }
            case "status":
                try {
                    int novoStatus = Integer.parseInt(novoValor);
                    return dao.alterarStatus(novoStatus, this.idCorrida);
                } catch (NumberFormatException e) {
                    System.out.println("Status inválido.");
                    return false;
                }
            default:
                System.out.println("Campo inválido.");
                return false;
        }
    }

    /**
     * Cria uma nova corrida com valores aleatórios para preço e fator de trânsito
     * e a insere no banco de dados.
     *
     * @param origem                 Local de partida.
     * @param destino                Local de chegada.
     * @param passageiroSolicitante O objeto Passageiro que está solicitando a corrida.
     * @return A instância da Corrida criada e persistida, com o ID preenchido.
     */
    public static Corrida realizarCorrida(String origem, String destino, Passageiro passageiroSolicitante) {
        float preco = (float) (20 + Math.random() * 30);
        int fatorTransito = (int) (1 + Math.random() * 5);

        Corrida novaCorrida = new Corrida(
            origem,
            destino,
            fatorTransito,
            preco,
            java.time.LocalDate.now().toString(),
            passageiroSolicitante.getIdPassageiro(),
            0,
            0,
            1 
        );

        CorridaDAO dao = new CorridaDAO();
        return dao.inserirCorrida(novaCorrida);
    }

    /**
     * Altera o status desta corrida para 4 (Finalizada) no banco de dados.
     *
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public boolean terminarCorrida() {
        CorridaDAO dao = new CorridaDAO();
        return dao.alterarStatus(4, this.idCorrida);
    }

    /**
     * Altera o status desta corrida para 5 (Cancelada) no banco de dados.
     *
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public boolean cancelarCorrida() {
        CorridaDAO dao = new CorridaDAO();
        return dao.alterarStatus(5, this.idCorrida);
    }
    
    // --- GETTERS E SETTERS ---

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