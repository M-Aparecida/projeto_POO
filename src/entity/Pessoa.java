package entity;

// classe Pessoa e seus atributos
public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senha;
    private int idade;
    private String genero;
    private int quantidadeCorridas;
    private float avaliacaoMedia;

    public Pessoa(){
        this.quantidadeCorridas = 0;
        this.avaliacaoMedia = 0.0f;
    }

    // inicializa os atributos da classe
    public Pessoa(String nome, String cpf, String email, String telefone, int idade, String senha, String genero) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
         this.idade = idade;
        this.genero = genero;
        this.quantidadeCorridas = 0;
        this.avaliacaoMedia = 0.0f;
    }

    //valida email e senha
    public boolean login(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

    // recupera senha
    public void recuperarSenha(String email) {
        if (this.email.equals(email)) {
            System.out.println("Email confirmado. Prossiga com a redefinição da senha.");
        } else {
            System.out.println("Email não encontrado.");
        }
    }

    //exclui cadastro
    public boolean excluirCadastro(String cpf, String senha) {
        return this.cpf.equals(cpf) && this.senha.equals(senha);
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

    public void setCpf(String cpf) throws IllegalArgumentException {
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException();
        }
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException{
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException();
        }
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws IllegalArgumentException {
        if (!telefone.matches("\\(\\d{2}\\) \\d{5}\\-\\d{4}")) {
            throw new IllegalArgumentException();
        }
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade; }

    public void setIdade(int idade) throws IllegalArgumentException {
        if (idade < 1 || idade > 120) {
            throw new IllegalArgumentException();
        }
        this.idade = idade; 
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

    @Override
    public String toString() {
        return "nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", telefone=" + telefone + ", senha="
                + senha + ", idade=" + idade + ", genero=" + genero + ", quantidadeCorridas=" + quantidadeCorridas
                + ", avaliacaoMedia=" + avaliacaoMedia;
    }

    
}