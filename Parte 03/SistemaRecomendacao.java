import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SistemaRecomendacao {

    public static class Afinidade {
        private final Usuario outro;
        private final int tamIntersecao;
        private final int tamUniao;
        private final Set<String> comuns;

        Afinidade(Usuario outro, Set<String> comuns, int tamUniao) {
            this.outro = outro;
            this.comuns = comuns;
            this.tamIntersecao = comuns.size();
            this.tamUniao = tamUniao;
        }

        public Usuario getOutro()        { return outro; }
        public Set<String> getComuns()   { return comuns; }
        public int getTamIntersecao()    { return tamIntersecao; }
        public int getTamUniao()         { return tamUniao; }

        public double getIndice() {
            return tamUniao == 0 ? 0.0 : (double) tamIntersecao / tamUniao;
        }
    }

    private final Map<String, Usuario> usuarios = new LinkedHashMap<>();

    public void adicionar(Usuario usuario) {
        String chave = usuario.getNome().toLowerCase();
        if (usuarios.containsKey(chave)) {
            throw new IllegalArgumentException("Ja existe um usuario chamado " + usuario.getNome());
        }
        usuarios.put(chave, usuario);
    }

    public Usuario buscar(String nome) {
        Usuario u = usuarios.get(nome.trim().toLowerCase());
        if (u == null) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + nome);
        }
        return u;
    }

    public static Set<String> intersecao(Set<String> a, Set<String> b) {
        Set<String> resultado = new HashSet<>(a);
        resultado.retainAll(b);
        return resultado;
    }

    public static Set<String> uniao(Set<String> a, Set<String> b) {
        Set<String> resultado = new HashSet<>(a);
        resultado.addAll(b);
        return resultado;
    }

    public static double jaccard(Set<String> a, Set<String> b) {
        int uni = uniao(a, b).size();
        if (uni == 0) {
            return 0.0; // 0/0 -> convencao
        }
        return (double) intersecao(a, b).size() / uni;
    }

    public static Afinidade comparar(Usuario a, Usuario b) {
        Set<String> comuns = intersecao(a.getInteresses(), b.getInteresses());
        int tamUniao = uniao(a.getInteresses(), b.getInteresses()).size();
        return new Afinidade(b, comuns, tamUniao);
    }

    public List<Afinidade> recomendar(String nome) {
        Usuario alvo = buscar(nome);
        List<Afinidade> ranking = new ArrayList<>();

        for (Usuario outro : usuarios.values()) {
            if (outro != alvo) {
                ranking.add(comparar(alvo, outro));
            }
        }

        Collections.sort(ranking, new Comparator<Afinidade>() {
            @Override
            public int compare(Afinidade x, Afinidade y) {
                // compara as razoes inter/uniao sem erro de ponto flutuante
                // x.i/x.u  vs  y.i/y.u  <=>  x.i*y.u  vs  y.i*x.u  (denominadores >= 0)
                long esq = (long) x.getTamIntersecao() * y.getTamUniao();
                long dir = (long) y.getTamIntersecao() * x.getTamUniao();
                if (esq != dir) {
                    return esq > dir ? -1 : 1; // maior primeiro
                }
                return x.getOutro().getNome().compareToIgnoreCase(y.getOutro().getNome());
            }
        });
        return ranking;
    }
}