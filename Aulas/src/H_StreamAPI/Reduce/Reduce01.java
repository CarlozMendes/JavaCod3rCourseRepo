package H_StreamAPI.Reduce;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

public class Reduce01 {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);
        BinaryOperator<Integer> soma = (ac,n) -> ac + n;

        int total = nums.stream().reduce(soma).get();
        System.out.println(total);

    }

}



/* Reduce #01
 * [Reduce.pdf]
 * reduce(acumulador,n) =>
 * O resultado do reduce não é outro stream e sim um único valor.
 * usamos .get() no final do pipeline.
 *
 *
 */