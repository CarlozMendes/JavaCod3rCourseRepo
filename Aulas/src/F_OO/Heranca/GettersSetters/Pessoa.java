package F_OO.Heranca.GettersSetters;

public class Pessoa {

    private String nome;
    private int idade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa(int idade){
        setIdade(idade);
    }
    public Pessoa(String nome,int idade){
        setNome(nome);
        setIdade(idade);

    }

    // Getter
    public int getIdade(){
        return idade;
    }

    // Setter
    public void setIdade(int novaIdade){
        novaIdade = Math.abs(novaIdade);
        if (novaIdade >=0 && novaIdade <= 120){
            this.idade = novaIdade;
        }
    }

    @Override
    public String toString() {
        return "Olá eu sou o " +getNome() + " tenho "+ getIdade()+" anos." ;
    }
}
