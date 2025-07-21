package entity;

/**
 * Classe abstrata que representa uma Pessoa no sistema.
 * Serve como base para classes mais específicas como {@link Motorista} e {@link Passageiro},
 * provendo atributos e funcionalidades comuns a todos.
 * Não pode ser instanciada diretamente.
 *
 */
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

    /**
     * Construtor padrão.
     * Inicializa a quantidade de corridas e a avaliação média com zero.
     */
    public Pessoa(){
        this.quantidadeCorridas = 0;
        this.avaliacaoMedia = 0.0f;
    }

    /**
     * Construtor para inicializar os atributos de uma Pessoa.
     * Geralmente invocado por construtores de subclasses via {@code super()}.
     *
     * @param nome     O nome completo.
     * @param cpf      O CPF no formato "XXX.XXX.XXX-XX".
     * @param email    O endereço de e-mail.
     * @param telefone O número de telefone no formato "(XX) XXXXX-XXXX".
     * @param idade    A idade (deve ser maior ou igual a 18).
     * @param senha    A senha de acesso.
     * @param genero   O gênero.
     */
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

    /**
     * Valida as credenciais de login.
     *
     * @param email O e-mail fornecido para login.
     * @param senha A senha fornecida para login.
     * @return true se o e-mail e a senha corresponderem aos do objeto, false caso contrário.
     */
    public boolean login(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }

     /**
     * Simula um processo de recuperação de senha, validando o e-mail.
     *
     * @param email O e-mail fornecido para iniciar a recuperação.
     */
    public void recuperarSenha(String email) {
        if (this.email.equals(email)) {
            System.out.println("Email confirmado. Prossiga com a redefinição da senha.");
        } else {
            System.out.println("Email não encontrado.");
        }
    }

    /**
     * Simula a validação de credenciais para exclusão de cadastro.
     *
     * @param cpf   O CPF fornecido para confirmação.
     * @param senha A senha fornecida para confirmação.
     * @return true se o CPF e a senha corresponderem, false caso contrário.
     */
    public boolean excluirCadastro(String cpf, String senha) {
        return this.cpf.equals(cpf) && this.senha.equals(senha);
    }

    // --- GETTERS E SETTERS ---
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty() || nome.matches("\\s*")) {
                throw new IllegalArgumentException("O nome não pode ser nulo, vazio ou conter apenas espaços.");
            }
            
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
        if (idade < 18 || idade > 120) {
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
}