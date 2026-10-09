#ifndef FUNCOES_H
#define FUNCOES_H

void informarElementosPopularConjuntos(int conjuntoA[], int *qtdA, int conjuntoB[], int *qtdB);
void exibirMenu();
void mostrarConjuntoA(int conjuntoA[], int qtdA);
void mostrarConjuntoB(int conjuntoB[], int qtdB);
void uniao(int conjuntoA[], int tamA, int conjuntoB[], int tamB);
void intersecao(int conjuntoA[], int tamA, int conjuntoB[], int tamB);
void diferencaAB(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB);
void diferencaBA(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB);
void diferencaSimetrica(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB);
void pertinencia(int conjuntoA[], int qtdA);
void contidoAB(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB);
void contidoBA(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB);
void propriamenteContidoAB(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB);
void propriamenteContidoBA(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB);
void complementoA(int conjuntoA[], int qtdA);
void cardinalidadeA(int qtdA);
void cardinalidadeB(int qtdB);
void complementoA(int conjuntoA[], int qtdA);

#endif