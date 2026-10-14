import funcoes.Divisores;
import java.util.Scanner;

public class TesteDivisores {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite um número (ou 'sair'): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println("Até mais!");
                break;
            }

            if (!Divisores.ehInteiroPositivo(entrada)) {
                System.out.println("Isso não é um número válido.\n");
                continue;
            }

            int n = Integer.parseInt(entrada);
            System.out.println("Divisores de " + n + ": " + Divisores.divisores(n) + "\n");
        }

        scanner.close();
    }
}