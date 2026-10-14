package funcoes;

import java.util.ArrayList;
import java.util.List;

public class Primos {

    public static boolean ehInteiroNaoNegativo(String s) {
        try {
            return Integer.parseInt(s) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<Integer> crivoDeEratostenes(int limite) {
        boolean[] ehComposto = new boolean[limite + 1];

        for (int primo = 2; primo * primo <= limite; primo++) {
            if (ehComposto[primo]) continue;

            for (int multiplo = primo * primo; multiplo <= limite; multiplo += primo) {
                ehComposto[multiplo] = true;
            }
        }

        List<Integer> primos = new ArrayList<>();
        for (int numero = 2; numero <= limite; numero++) {
            if (!ehComposto[numero]) {
                primos.add(numero);
            }
        }
        return primos;
    }
}
