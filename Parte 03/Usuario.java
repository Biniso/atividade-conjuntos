import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Usuario {

    private final String nome;
    private final Set<String> interesses;

    public Usuario(String nome, Collection<String> interesses) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuario nao pode ser vazio.");
        }
        this.nome = nome.trim();

        Set<String> conjunto = new LinkedHashSet<>();
        if (interesses != null) {
            for (String interesse : interesses) {
                if (interesse != null && !interesse.trim().isEmpty()) {
                    conjunto.add(interesse.trim().toLowerCase());
                }
            }
        }
        this.interesses = Collections.unmodifiableSet(conjunto);
    }

    public String getNome() {
        return nome;
    }

    public Set<String> getInteresses() {
        return interesses;
    }

    @Override
    public String toString() {
        return nome + " = " + interesses;
    }
}