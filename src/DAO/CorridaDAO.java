package DAO;

import entity.Corrida;
import java.util.ArrayList;
import java.util.List;

public class CorridaDAO {
    private List<Corrida> corridas = new ArrayList<>();

    public boolean adicionarCorrida(Corrida corrida) {
        if (corrida != null) {
            corridas.add(corrida);
            return true;
        }
        return false;
    }

    public boolean atualizarCorrida(Corrida novaCorrida) {
        for (int i = 0; i < corridas.size(); i++) {
            if (corridas.get(i).getIdcorrida().equals(novaCorrida.getIdcorrida())) {
                corridas.set(i, novaCorrida);
                return true;
            }
        }
        return false;
    }

    public boolean removerCorrida(Integer idCorrida) {
        return corridas.removeIf(c -> c.getIdcorrida().equals(idCorrida));
    }

    public Corrida buscarCorrida(Integer idCorrida) {
        for (Corrida c : corridas) {
            if (c.getIdcorrida().equals(idCorrida)) {
                return c;
            }
        }
        return null;
    }

    public List<Corrida> listarTodasCorridas() {
        return new ArrayList<>(corridas);
    }

    public List<Corrida> listarCorridasDisponiveis() {
        List<Corrida> disponiveis = new ArrayList<>();
        for (Corrida c : corridas) {
            if (c.getStatus() != null && c.getStatus() == 0) {
                disponiveis.add(c);
            }
        }
        return disponiveis;
    }

    public List<Corrida> listarCorridasPorData(String data1, String data2) {
        List<Corrida> resultado = new ArrayList<>();
        for (Corrida c : corridas) {
            if (c.getData() != null && c.getData().compareTo(data1) >= 0 && c.getData().compareTo(data2) <= 0) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Corrida> historicoDeCorridasPorPassageiro(String cpf) {
        List<Corrida> historico = new ArrayList<>();
        for (Corrida c : corridas) {
            if (c.getPassageiro() != null && c.getPassageiro().equals(cpf)) {
                historico.add(c);
            }
        }
        return historico;
    }

    public List<Corrida> historicoDeCorridasPorMotorista(int cnh) {
        List<Corrida> historico = new ArrayList<>();
        for (Corrida c : corridas) {
            if (c.getMotoristaId() != null && c.getMotoristaId() == cnh) {
                historico.add(c);
            }
        }
        return historico;
    }
}
