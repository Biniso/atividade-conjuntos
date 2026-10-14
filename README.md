# Atividade Conjuntos

Projeto acadêmico desenvolvido para aplicar os conceitos da Teoria dos Conjuntos por meio de algoritmos implementados em C e Java.

## Objetivo

O objetivo do projeto é compreender como as operações e propriedades dos conjuntos podem ser representadas computacionalmente, desenvolvendo soluções para manipulação de conjuntos, classificação numérica e recomendação de usuários.

## Estrutura do projeto

```text
atividade-conjuntos/
├── .vscode/
├── Parte 01/
│   ├── main.c
│   ├── funcoes.c
│   ├── funcoes.h
│   └── programa          # Executável gerado na compilação
├── Parte 02/
│   ├── src/
│   │   ├── TesteClassificador.java
│   │   ├── TesteDivisores.java
│   │   ├── TestePrimos.java
│   │   └── funcoes/
│   │       ├── Classificador.java
│   │       ├── Divisores.java
│   │       └── Primos.java
│   └── out/              # Classes compiladas
├── Parte 03/
│   ├── Main.java
│   ├── SistemaRecomendacao.java
│   ├── Usuario.java
│   └── out/              # Classes compiladas
└── README.md
```

## Como executar o projeto

### Parte 1 — Conjuntos em C

Execute o programa:

```bash
"./Parte 01/programa"
```

### Parte 2 — Classificador, Divisores e Primos em Java

Execute o classificador:

```bash
java -cp "Parte 02/out" TesteClassificador
```

Execute o programa de divisores:

```bash
java -cp "Parte 02/out" TesteDivisores
```

Execute o programa de números primos:

```bash
java -cp "Parte 02/out" TestePrimos
```

### Parte 3 — Sistema de Recomendação em Java

Execute o programa principal:

```bash
java -cp "Parte 03/out" Main
```

Para iniciar o modo interativo, execute:

```bash
java -cp "Parte 03/out" Main --interativo
```

## Parte 1 — Operações com conjuntos em C

A primeira parte implementa operações entre conjuntos de números inteiros, utilizando arrays e funções.

As operações disponíveis incluem:

- União: \(A \cup B\).
- Interseção: \(A \cap B\).
- Diferença entre conjuntos: \(A-B\) e \(B-A\).
- Diferença simétrica.
- Verificação de pertinência.
- Relações de inclusão e inclusão própria.
- Cardinalidade dos conjuntos.
- Complemento.

O programa apresenta um menu interativo para selecionar a operação desejada.

### Decisões de implementação

Os conjuntos são representados por arrays de inteiros, acompanhados de variáveis que armazenam suas quantidades de elementos. As operações são organizadas em funções, separando a lógica de cada cálculo da interação com o usuário.

A verificação de elementos repetidos é importante para garantir que os resultados respeitem as propriedades dos conjuntos, nos quais cada elemento aparece uma única vez.

## Parte 2 — Classificação numérica em Java

A segunda parte reúne três programas organizados em classes Java.

### Classificador

A classe `Classificador` reconhece diferentes representações numéricas, incluindo inteiros, decimais, frações, dízimas periódicas e representações simbólicas de números irracionais.

O uso de expressões regulares permite analisar os formatos de entrada, enquanto `BigInteger` possibilita trabalhar com inteiros grandes.

### Divisores

A classe `Divisores` trabalha com a identificação dos divisores positivos de um número inteiro positivo.

O programa valida a entrada e apresenta os divisores encontrados.

### Números primos

A classe `Primos` utiliza o Crivo de Eratóstenes para encontrar números primos até determinado limite.

Esse algoritmo elimina os múltiplos dos números encontrados, permitindo identificar os números primos de maneira organizada.

### Decisões de implementação

Os programas foram separados em classes para dividir responsabilidades e facilitar a manutenção. As classes de teste permitem verificar os comportamentos implementados e executar os programas de forma independente.

## Parte 3 — Sistema de recomendação por conjuntos

A terceira parte implementa um sistema de recomendação que compara os interesses de diferentes usuários para identificar aqueles com maior afinidade.

Cada usuário possui um conjunto de interesses. O programa compara esses conjuntos utilizando a interseção, a união e o índice de Jaccard.

### Classes utilizadas

- **`Usuario`:** armazena o nome e os interesses de cada usuário. Os interesses são normalizados para letras minúsculas, espaços desnecessários são removidos e duplicatas são eliminadas.
- **`SistemaRecomendacao`:** cadastra e busca usuários, calcula interseções e uniões, determina o índice de Jaccard e organiza as recomendações por afinidade.
- **`Main`:** executa cenários de teste, compara os resultados do programa com os cálculos manuais e oferece um modo interativo.

### Índice de Jaccard

O índice de Jaccard mede a similaridade entre dois conjuntos:

\[
J(A,B)=\frac{|A\cap B|}{|A\cup B|}
\]

Em que:

- \(A\cap B\) representa os interesses em comum.
- \(A\cup B\) representa todos os interesses distintos dos dois usuários.
- Quanto maior o índice, maior a similaridade entre os conjuntos.

Quando os dois conjuntos são vazios, o programa adota a convenção de atribuir índice zero, evitando uma divisão por zero.

As recomendações são ordenadas do maior para o menor índice de afinidade. Em caso de empate, os nomes são ordenados alfabeticamente.

## Exemplos de execução e testes

A Parte 3 possui três cenários automatizados no arquivo `Main.java`. Os testes calculam os resultados esperados manualmente e os comparam com os valores obtidos pelo programa.

### Cenário 1 — Exemplo com Ana, Bia e Caio

Interesses:

- Ana: {rock, jazz, cinema, games}
- Bia: {jazz, cinema, series, viagens}
- Caio: {games, tecnologia, cinema}

**Ana e Bia**

Interseção: {jazz, cinema}, com 2 elementos.

União: {rock, jazz, cinema, games, series, viagens}, com 6 elementos.

\[
J(Ana,Bia)=\frac{2}{6}\approx0{,}33
\]

**Ana e Caio**

Interseção: {games, cinema}, com 2 elementos.

União: {rock, jazz, cinema, games, tecnologia}, com 5 elementos.

\[
J(Ana,Caio)=\frac{2}{5}=0{,}40
\]

**Bia e Caio**

Interseção: {cinema}, com 1 elemento.

União: {jazz, cinema, series, viagens, games, tecnologia}, com 6 elementos.

\[
J(Bia,Caio)=\frac{1}{6}\approx0{,}17
\]

Nesse cenário, Caio é a recomendação mais forte para Ana, pois apresenta o maior índice de Jaccard entre os dois usuários.

### Cenário 2 — Casos de borda

Esse cenário verifica situações importantes para a correção do programa.

| Caso de teste | Resultado esperado |
|---|---|
| Conjunto vazio e conjunto com 3 elementos | Interseção = 0; união = 3; J = 0 |
| Dois conjuntos vazios | Interseção = 0; união = 0; J = 0, conforme a convenção adotada |
| Conjuntos iguais, em ordens diferentes | Interseção = 3; união = 3; J = 1 |
| Conjuntos disjuntos com 3 e 4 elementos | Interseção = 0; união = 7; J = 0 |
| Elementos repetidos e diferenças entre maiúsculas e minúsculas | Duplicatas eliminadas após normalização |

O cenário também verifica se os resultados calculados manualmente correspondem aos resultados apresentados pelo programa.

### Cenário 3 — Grupos de estudo

São utilizados cinco usuários: Dani, Edu, Fabi, Gabi e Hugo.

**Dani e Fabi**

Interesses em comum: {java, python, musica}.

\[
J(Dani,Fabi)=\frac{3}{5}=0{,}60
\]

**Dani e Edu**

Interesses em comum: {python, games}.

\[
J(Dani,Edu)=\frac{2}{5}=0{,}40
\]

**Dani e Gabi**

Não possuem interesses em comum. A união contém 7 elementos.

\[
J(Dani,Gabi)=\frac{0}{7}=0
\]

**Dani e Hugo**

Não possuem interesses em comum. A união contém 6 elementos.

\[
J(Dani,Hugo)=\frac{0}{6}=0
\]

Portanto, Fabi é a recomendação mais forte para Dani.

O cenário também compara as afinidades de Gabi:

- Gabi e Hugo: 1 interesse em comum e união de 4 elementos, resultando em \(J=1/4=0{,}25\).
- Gabi e Edu: 1 interesse em comum e união de 5 elementos, resultando em \(J=1/5=0{,}20\).

Assim, Hugo é a recomendação mais forte para Gabi.

### Execução e conferência

O programa verifica automaticamente os valores calculados à mão e informa a quantidade de conferências realizadas e de falhas encontradas.

Uma execução sem divergências deve terminar com a mensagem:

```text
Todos os resultados batem com o calculo a mao.
```

Para comprovar os testes, devem ser incluídas capturas de tela ou registros reais do terminal.

## Casos de teste gerais

Além dos cenários apresentados, devem ser considerados os seguintes casos:

- Conjuntos vazios.
- Conjuntos iguais.
- Conjuntos disjuntos.
- Elementos repetidos na entrada.
- Entradas inválidas nos programas de classificação numérica e divisores.
- Limites pequenos no cálculo de números primos.
- Usuários com interesses idênticos.
- Usuários sem interesses em comum.
- Dois usuários sem interesses, para verificar a convenção adotada no índice de Jaccard.

## Tecnologias utilizadas

- C;
- Java;
- Git e GitHub;
- Visual Studio Code;
- GitHub Codespaces.

## Conclusão

O projeto demonstra como conceitos matemáticos podem ser transformados em soluções computacionais. A implementação das operações de conjuntos, a organização em funções e classes e a validação por meio de testes contribuem para o desenvolvimento do raciocínio lógico, da programação e da compreensão prática da Teoria dos Conjuntos.

Equipe: Guilherme Gonçalves e Vinicius Taraborelli

Repositório: https://github.com/Biniso/atividade-conjuntos.git
