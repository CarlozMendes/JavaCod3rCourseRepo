package I_Tratamento_de_Erros.personalizadaB.personalizadaA;

import I_Tratamento_de_Erros.Aluno;

public class Validar {
    private Validar(){}

    public static void usuario(Aluno aluno) throws  StringVaziaException, NumeroForaIntervaloException{

        if(aluno == null){
            throw new IllegalArgumentException("O aluno está nulo");
        }

        if(aluno.nome == null || aluno.nome.trim().isEmpty()){
            throw new StringVaziaException("nome");
        }

        if(aluno.nota < 0 || aluno.nota >10){
            throw new NumeroForaIntervaloException("nota");
        }
    }
}
