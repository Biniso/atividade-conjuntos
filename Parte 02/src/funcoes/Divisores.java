package funcoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Divisores {

    public static void listarDivisores() {
        Scanner read = new Scanner(System.in);
        System.out.print("Digite um número: ");
        String entrada = read.nextLine().trim();

        if (!ehInteiroPositivo(entrada)) {
            System.out.println("Isso não é um número válido.");
            return;
        }

        int numero = Integer.parseInt(entrada);
        System.out.println("Divisores de " + numero + ": " + divisores(numero));
    }

    public static boolean ehInteiroPositivo(String s) {
        try {
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<Integer> divisores(int numero) {
        List<Integer> divisores = new ArrayList<>();

        for (int i = 1; i * i <= numero; i++) {
            if (numero % i != 0) continue;
            divisores.add(i);
            if (i != numero / i) divisores.add(numero / i);
        }

        Collections.sort(divisores);
        return divisores;
    }
}