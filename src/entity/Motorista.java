package entity;

public class Motorista extends Pessoa {
    private int numeroCnh;
    private int idMotorista;
    private boolean disponivel;

    public Motorista(String nome, String cpf, String email, String telefone,
                     String senha, String genero,int numeroCnh, int idMotorista, boolean disponivel){
        super(nome, cpf, email, telefone, senha, genero);
        this.numeroCnh = numeroCnh;
        this.idMotorista = idMotorista;
        this.disponivel = disponivel;
    }
    public boolean aceitarCorrida(int idCorrida) {
        if (!disponivel) {
            return false;
        }
        this.disponivel = false;
        return true;
    }
}
