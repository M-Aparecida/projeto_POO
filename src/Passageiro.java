public class Passageiro extends Pessoa {
    private int idPassageiro;
    private String cartaoDados;

    public Passageiro(String nome, String cpf, String email, String telefone, String senha, String genero,
            int idPassageiro, String cartaoDados) {
        super(nome, cpf, email, telefone, senha, genero);
        this.idPassageiro = idPassageiro;
        this.cartaoDados = cartaoDados;
    }

    public boolean realizarPagamento(float valor, String metodoPagamento){
        if (valor > 0.0F) return true;
        return false;
    }
}
