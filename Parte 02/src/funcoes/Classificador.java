package funcoes;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Classificador {

    private static final Scanner scanner = new Scanner(System.in);

    public static String ler(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static final Pattern DIZIMA = Pattern.compile("([+-]?)(\\d*)[.,](\\d*)\\((\\d+)\\)");
    private static final Pattern RAIZ = Pattern.compile("(?:√|sqrt)(?:(\\d+)|\\((\\d+)\\))");
    private static final Pattern CONSTANTE = Pattern.compile("[+-]?(pi|π|e)");

    public static void classificarNumero() {
        classificar(ler("Digite um número: ").trim());
    }

    public static void classificar(String entrada) {
        if (ehIrracionalSimbolico(entrada)) return;

        if (entrada.contains("/")) {
            classificarFracao(entrada);
        } else if (DIZIMA.matcher(entrada).matches()) {
            classificarDizima(entrada);
        } else if (ehInteiro(entrada)) {
            classificarInteiro(new BigInteger(entrada));
        } else if (ehDecimal(entrada)) {
            System.out.println("Racional (ℚ) e Real (ℝ).");
        } else {
            System.out.println("Isso não é um número válido.");
        }
    }

    private static boolean ehIrracionalSimbolico(String s) {
        String t = s.toLowerCase().replace(" ", "");

        if (CONSTANTE.matcher(t).matches()) {
            System.out.println("Irracional (𝕀) e Real (ℝ).");
            return true;
        }

        Matcher m = RAIZ.matcher(t);
        if (m.matches()) {
            BigInteger n = new BigInteger(m.group(1) != null ? m.group(1) : m.group(2));
            BigInteger raiz = n.sqrt();

            if (raiz.multiply(raiz).equals(n)) {
                classificarInteiro(raiz);
            } else {
                System.out.println("Irracional (𝕀) e Real (ℝ).");
            }
            return true;
        }
        return false;
    }

    private static void classificarDizima(String entrada) {
        Matcher m = DIZIMA.matcher(entrada);
        m.matches();

        String sinal = m.group(1);
        String inteira = m.group(2).isEmpty() ? "0" : m.group(2);
        String naoRep = m.group(3);
        String rep = m.group(4);

        BigInteger tudo = new BigInteger(inteira + naoRep + rep);
        BigInteger base = new BigInteger(inteira + naoRep);
        BigInteger num = tudo.subtract(base);
        BigInteger den = BigInteger.TEN.pow(rep.length()).subtract(BigInteger.ONE)
                .multiply(BigInteger.TEN.pow(naoRep.length()));

        if (sinal.equals("-")) num = num.negate();

        BigInteger mdc = num.gcd(den);
        if (mdc.signum() != 0) {
            num = num.divide(mdc);
            den = den.divide(mdc);
        }

        System.out.println("Dízima periódica = fração geratriz " + num + "/" + den);

        if (den.equals(BigInteger.ONE)) {
            classificarInteiro(num);
        } else {
            System.out.println("Racional (ℚ) e Real (ℝ).");
        }
    }

    private static void classificarFracao(String entrada) {
        String[] partes = entrada.split("/");

        if (partes.length != 2 || !ehInteiro(partes[0].trim()) || !ehInteiro(partes[1].trim())) {
            System.out.println("Fração inválida.");
            return;
        }

        BigInteger numerador = new BigInteger(partes[0].trim());
        BigInteger denominador = new BigInteger(partes[1].trim());

        if (denominador.signum() == 0) {
            System.out.println("Não existe divisão por zero.");
        } else if (numerador.mod(denominador.abs()).signum() == 0) {
            classificarInteiro(numerador.divide(denominador));
        } else {
            System.out.println("Racional (ℚ) e Real (ℝ).");
        }
    }

    private static void classificarInteiro(BigInteger n) {
        if (n.signum() >= 0) {
            System.out.println("Natural (ℕ), Inteiro (ℤ), Racional (ℚ) e Real (ℝ).");
        } else {
            System.out.println("Inteiro (ℤ), Racional (ℚ) e Real (ℝ).");
        }
    }

    private static boolean ehInteiro(String s) {
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean ehDecimal(String s) {
        return s.matches("[+-]?\\d*[.,]?\\d+");
    }
}
