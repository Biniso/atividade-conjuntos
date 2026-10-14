import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static int falhas = 0; 
    private static int conferencias = 0;

    public static void main(String[] args) {
        usarUtf8();
        if (args.length > 0 && args[0].equals("--interativo")) {
            interativo();
            return;
        }

        cenario1ExemploDaAtividade();
        cenario2CasosDeBorda();
        cenario3ExemploProprio();

        System.out.println("==================================================");
        System.out.println(conferencias + " conferencias feitas, " + falhas + " falha(s).");
        System.out.println(falhas == 0 ? "Todos os resultados batem com o calculo a mao."
                                       : "ATENCAO: ha divergencias.");
        if (falhas > 0) {
            System.exit(1);
        }
    }

    private static void cenario1ExemploDaAtividade() {
        titulo("CENARIO 1 - Exemplo da atividade (Ana, Bia e Caio)");

        SistemaRecomendacao sistema = new SistemaRecomendacao();
        Usuario ana  = new Usuario("Ana",  Arrays.asList("rock", "jazz", "cinema", "games"));
        Usuario bia  = new Usuario("Bia",  Arrays.asList("jazz", "cinema", "series", "viagens"));
        Usuario caio = new Usuario("Caio", Arrays.asList("games", "tecnologia", "cinema"));
        sistema.adicionar(ana);
        sistema.adicionar(bia);
        sistema.adicionar(caio);

        System.out.println(ana);
        System.out.println(bia);
        System.out.println(caio);
        System.out.println();

        // esperado a mao: (|inter|, |uniao|)
        conferir("Ana e Bia",  SistemaRecomendacao.comparar(ana, bia),  2, 6);
        conferir("Ana e Caio", SistemaRecomendacao.comparar(ana, caio), 2, 5);
        conferir("Bia e Caio", SistemaRecomendacao.comparar(bia, caio), 1, 6);

        System.out.println();
        mostrarRanking(sistema, "Ana");
        List<SistemaRecomendacao.Afinidade> ranking = sistema.recomendar("Ana");
        conferirTexto("Recomendacao mais forte para Ana", "Caio", ranking.get(0).getOutro().getNome());
        System.out.println();
    }

    private static void cenario2CasosDeBorda() {
        titulo("CENARIO 2 - Casos de borda");

        Usuario vazio    = new Usuario("Vazio",    Arrays.<String>asList());
        Usuario ze       = new Usuario("Ze",       Arrays.asList("rock", "jazz", "cinema"));
        Usuario zeClone  = new Usuario("ZeClone",  Arrays.asList("cinema", "rock", "jazz"));
        Usuario luiza    = new Usuario("Luiza",    Arrays.asList("culinaria", "yoga", "teatro", "pintura"));
        Usuario repetido = new Usuario("Repetido", Arrays.asList("Rock", "rock", " ROCK ", "jazz", "jazz"));

        System.out.println("a) Conjunto vazio:");
        conferir("Vazio e Ze (0 em comum, uniao = 3)",
                SistemaRecomendacao.comparar(vazio, ze), 0, 3);

        System.out.println("b) Dois conjuntos vazios (0/0 -> convencao J = 0):");
        Usuario vazio2 = new Usuario("Vazio2", Arrays.<String>asList());
        conferir("Vazio e Vazio2", SistemaRecomendacao.comparar(vazio, vazio2), 0, 0);

        System.out.println("c) Conjuntos iguais (mesmos elementos, ordem diferente):");
        conferir("Ze e ZeClone (J = 1)",
                SistemaRecomendacao.comparar(ze, zeClone), 3, 3);

        System.out.println("d) Conjuntos disjuntos:");
        conferir("Ze e Luiza (J = 0)",
                SistemaRecomendacao.comparar(ze, luiza), 0, 7);

        System.out.println("e) Elementos repetidos / maiusculas na entrada:");
        System.out.println("   Entrada: Rock, rock, ' ROCK ', jazz, jazz  ->  " + repetido.getInteresses());
        conferirTexto("   |interesses| de Repetido", "2", String.valueOf(repetido.getInteresses().size()));
        conferir("Repetido e Ze (rock, jazz em comum; uniao = 3)",
                SistemaRecomendacao.comparar(repetido, ze), 2, 3);
        System.out.println();
    }

    private static void cenario3ExemploProprio() {
        titulo("CENARIO 3 - Exemplo proprio: grupos de estudo da turma");

        SistemaRecomendacao sistema = new SistemaRecomendacao();
        Usuario dani = new Usuario("Dani", Arrays.asList("java", "python", "games", "musica"));
        Usuario edu  = new Usuario("Edu",  Arrays.asList("python", "games", "anime"));
        Usuario fabi = new Usuario("Fabi", Arrays.asList("java", "python", "redes", "musica"));
        Usuario gabi = new Usuario("Gabi", Arrays.asList("anime", "series", "viagens"));
        Usuario hugo = new Usuario("Hugo", Arrays.asList("cinema", "series"));
        for (Usuario u : Arrays.asList(dani, edu, fabi, gabi, hugo)) {
            sistema.adicionar(u);
        }
        for (Usuario u : Arrays.asList(dani, edu, fabi, gabi, hugo)) {
            System.out.println(u);
        }
        System.out.println();

        System.out.println("Para Dani (calculo a mao: |U| = |A| + |B| - |A ∩ B|):");
        conferir("Dani e Fabi (java, python, musica; uniao 4+4-3 = 5)",
                SistemaRecomendacao.comparar(dani, fabi), 3, 5);
        conferir("Dani e Edu  (python, games; uniao 4+3-2 = 5)",
                SistemaRecomendacao.comparar(dani, edu), 2, 5);
        conferir("Dani e Gabi (nada em comum; uniao 4+3 = 7)",
                SistemaRecomendacao.comparar(dani, gabi), 0, 7);
        conferir("Dani e Hugo (nada em comum; uniao 4+2 = 6)",
                SistemaRecomendacao.comparar(dani, hugo), 0, 6);
        System.out.println();
        mostrarRanking(sistema, "Dani");
        conferirTexto("Melhor recomendacao para Dani", "Fabi",
                sistema.recomendar("Dani").get(0).getOutro().getNome());

        System.out.println();
        System.out.println("Para Gabi (o tamanho da uniao muda a ordem):");
        conferir("Gabi e Hugo (series; uniao 3+2-1 = 4 -> 1/4)",
                SistemaRecomendacao.comparar(gabi, hugo), 1, 4);
        conferir("Gabi e Edu  (anime; uniao 3+3-1 = 5 -> 1/5)",
                SistemaRecomendacao.comparar(gabi, edu), 1, 5);
        System.out.println();
        mostrarRanking(sistema, "Gabi");
        conferirTexto("Melhor recomendacao para Gabi", "Hugo",
                sistema.recomendar("Gabi").get(0).getOutro().getNome());
        System.out.println("(Gabi tem 1 interesse em comum com Hugo e 1 com Edu, mas Hugo tem menos");
        System.out.println(" interesses no total, entao a uniao e menor e a afinidade e maior.)");
        System.out.println();
    }

    private static void interativo() {
        Scanner in = new Scanner(System.in);
        SistemaRecomendacao sistema = new SistemaRecomendacao();
        System.out.println("Modo interativo. Comandos: novo | recomendar | sair");
        while (true) {
            System.out.print("> ");
            if (!in.hasNextLine()) {
                break;
            }
            String cmd = in.nextLine().trim().toLowerCase();
            try {
                if (cmd.equals("novo")) {
                    System.out.print("Nome: ");
                    String nome = in.nextLine();
                    System.out.print("Interesses (separados por virgula): ");
                    String linha = in.nextLine();
                    sistema.adicionar(new Usuario(nome, Arrays.asList(linha.split(","))));
                    System.out.println("Usuario cadastrado: " + sistema.buscar(nome));
                } else if (cmd.equals("recomendar")) {
                    System.out.print("Para quem? ");
                    mostrarRanking(sistema, in.nextLine());
                } else if (cmd.equals("sair")) {
                    break;
                } else {
                    System.out.println("Comando desconhecido.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void mostrarRanking(SistemaRecomendacao sistema, String nome) {
        System.out.println("Ranking de afinidade para " + sistema.buscar(nome).getNome() + ":");
        int pos = 1;
        for (SistemaRecomendacao.Afinidade a : sistema.recomendar(nome)) {
            System.out.println(String.format(Locale.forLanguageTag("pt-BR"),
                    "  %d. %-6s J = %d/%d = %.2f   em comum: %s",
                    pos++, a.getOutro().getNome(), a.getTamIntersecao(), a.getTamUniao(),
                    a.getIndice(), a.getComuns()));
        }
    }

    /** Confere |A ∩ B| e |A ∪ B| calculados a mao contra o programa. */
    private static void conferir(String rotulo, SistemaRecomendacao.Afinidade real,
                                 int interEsperada, int uniaoEsperada) {
        boolean ok = real.getTamIntersecao() == interEsperada && real.getTamUniao() == uniaoEsperada;
        double esperado = uniaoEsperada == 0 ? 0.0 : (double) interEsperada / uniaoEsperada;
        conferencias++;
        if (!ok) {
            falhas++;
        }
        System.out.println(String.format(Locale.forLanguageTag("pt-BR"),
                "  [%s] %s%n        a mao: %d/%d = %.2f | programa: %d/%d = %.2f",
                ok ? "OK" : "FALHOU", rotulo,
                interEsperada, uniaoEsperada, esperado,
                real.getTamIntersecao(), real.getTamUniao(), real.getIndice()));
    }

    private static void conferirTexto(String rotulo, String esperado, String real) {
        boolean ok = esperado.equals(real);
        conferencias++;
        if (!ok) {
            falhas++;
        }
        System.out.println("  [" + (ok ? "OK" : "FALHOU") + "] " + rotulo
                + ": esperado " + esperado + " | programa " + real);
    }

    private static void titulo(String texto) {
        System.out.println("==================================================");
        System.out.println(texto);
        System.out.println("==================================================");
    }

    private static void usarUtf8() {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // segue com a codificacao padrao
        }
    }
}
