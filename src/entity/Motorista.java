package entity;

import java.util.Scanner;

import DAO.MotoristaDAO;

public class Motorista extends Pessoa {
    private int numeroCnh;
    private int idMotorista;
    private boolean disponivel;

    //construtor de exibir
   public Motorista( int idMotorista,String nome, String cpf, String email, String telefone, String senha, String genero,
                 int idade, int numeroCnh, boolean disponivel, int quantidadeCorridas, float avaliacaoMedia) {
    super(nome, cpf, email, telefone, idade, senha, genero);
    this.idMotorista = idMotorista;
    this.numeroCnh = numeroCnh;
    this.disponivel = disponivel;
    this.setQuantidadeCorridas(quantidadeCorridas);
    this.setAvaliacaoMedia(avaliacaoMedia);
}


    //construtor de inserir
    public Motorista(String nome, String cpf, String email, String telefone, String senha, String genero, int idade,
            int numeroCnh, boolean disponivel, int quantidadeCorridas, float avaliacaoMedia) {
        super(nome, cpf, email, telefone, idade, senha, genero);
        this.numeroCnh = numeroCnh;
        this.disponivel = disponivel;
        super.setAvaliacaoMedia(avaliacaoMedia);
        super.setQuantidadeCorridas(quantidadeCorridas);
    }
    
    public int getNumeroCnh() {
        return numeroCnh;
    }

    public void setNumeroCnh(int numeroCnh) {
        this.numeroCnh = numeroCnh;
    }

    public int getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(int idMotorista) {
        this.idMotorista = idMotorista;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    

    private Motorista cadastroMotorista(){
       Motorista m = new Motorista();
       Scanner ent = new Scanner(System.in);

       System.out.print("Insira o seu nome: ");
        m.setNome(ent.nextLine());
        System.out.print("Insira o seu CPF: ");
        m.setCpf(ent.nextLine());
        System.out.print("Insira o seu email: ");
        m.setEmail(ent.nextLine());
        System.out.print("Insira o seu telefone: ");
        m.setTelefone(ent.nextLine());
        System.out.print("Insira a sua senha: ");
        m.setSenha(ent.nextLine());
        System.out.print("Insira o seu genero: ");
        m.setGenero(ent.nextLine());
        System.out.print("Insira a sua idade: ");
        m.setIdade(ent.nextInt());
        System.out.print("Insira o numero da sua cnh: ");
        m.setNumeroCnh(ent.nextInt());
        System.out.print("Insira se você está disponível ou não: ");
        m.setDisponivel(ent.nextBoolean());



    }


    public boolean aceitarCorrida(int corridaID) {
        if (!disponivel) {
            return false;
        }
        this.disponivel = false;
        return true;
    }

    public boolean modificarValoresMotorista(String nome, String email, String senha, int numeroCnh){
        return false;
    }

    
}
