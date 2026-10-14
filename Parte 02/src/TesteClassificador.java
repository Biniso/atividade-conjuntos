import funcoes.Classificador;
import java.util.Scanner;

public class TesteClassificador {

     private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] testes = {
            "0.(3)", "1.1(4)", "0.1(6)", "1.(45)", "-0.(3)", ".(3)",
            "0.(9)", "-0.(9)", "5", "-7", "3.14", "6/3", "1/3",
            "5/0", "pi", "√2", "sqrt(9)", "abc"
        };

        System.out.println("===== TESTES AUTOMÁTICOS =====");
        for (String t : testes) {
            System.out.println("\nEntrada: " + t);
            Classificador.classificar(t);
        }

        System.out.println("\n===== MODO INTERATIVO (digite 'sair' para encerrar) =====");
        while (true) {
            System.out.print("\nDigite um número: ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("sair")) break;
            Classificador.classificar(entrada);
        }
    }
}
