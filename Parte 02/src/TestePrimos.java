import funcoes.Primos;
import java.util.Scanner;

public class TestePrimos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite um número (ou 'sair'): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println("Até mais!");
                break;
            }

            if (!Primos.ehInteiroNaoNegativo(entrada)) {
                System.out.println("Isso não é um número válido.\n");
                continue;
            }

            int n = Integer.parseInt(entrada);
            System.out.println("Primos até " + n + ": " + Primos.crivoDeEratostenes(n) + "\n");
        }

        scanner.close();
    }
}