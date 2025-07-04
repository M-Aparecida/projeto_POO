// classe Pessoa e seus atributos
public class Pessoa {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private String genero;
    private int quantidadeCorridas;
    private float avaliacaoMedia;

    // inicializa os atributos da classe
    public Pessoa(String nome, String cpf, String email, String telefone, String senha, String genero) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.genero = genero;
        this.quantidadeCorridas = 0;
        this.avaliacaoMedia = 0.0f;
    }

    // exibe os dados cadastrados
    public void exibirCadastro() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Telefone: " + telefone);
        System.out.println("Gênero: " + genero);
        System.out.println("Quantidade de Corridas: " + quantidadeCorridas);
        System.out.println("Avaliação Média: " + avaliacaoMedia);
    }

    //valida email e senha
    public boolean loginEmailSenha(String emailInformado, String senhaInformada) {
        return this.email.equals(emailInformado) && this.senha.equals(senhaInformada);
    }

    // redefine a senha se o email for correspondente
    public boolean redefinirSenha(String emailInformado, String novaSenha) {
        if (this.email.equals(emailInformado)) {
            this.senha = novaSenha;
            return true;
        }
        return false;
    }

    // Getters e Setters para permitir acesso controlado aos atributos por outras classes
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQuantidadeCorridas() {
        return quantidadeCorridas;
    }

    public void setQuantidadeCorridas(int quantidadeCorridas) {
        this.quantidadeCorridas = quantidadeCorridas;
    }

    public float getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(float avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }
}