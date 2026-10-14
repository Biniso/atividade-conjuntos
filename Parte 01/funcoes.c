#include <stdio.h>
#include "funcoes.h"

void informarElementosPopularConjuntos(int conjuntoA[], int *qtdA, int conjuntoB[], int *qtdB) {
    int n, x, repetido;

    printf("\nInforme a quantidade de elementos do conjunto A: ");
    scanf("%d", &n);
    printf("\nInforme os elementos do conjunto A:\n");
    *qtdA = 0;
    for (int i = 0; i < n; i++) {
        scanf("%d", &x);

        repetido = 0;
        for (int j = 0; j < *qtdA; j++) {
            if (conjuntoA[j] == x) {
                repetido = 1;
                break;
            }
        }
        if (!repetido) {
            conjuntoA[(*qtdA)++] = x;
        }
    }

    printf("\nInforme a quantidade de elementos do conjunto B: ");
    scanf("%d", &n);
    printf("\nInforme os elementos do conjunto B:\n");
    *qtdB = 0;
    for (int i = 0; i < n; i++) {
        scanf("%d", &x);

        repetido = 0;
        for (int j = 0; j < *qtdB; j++) {
            if (conjuntoB[j] == x) {
                repetido = 1;
                break;
            }
        }
        if (!repetido) {
            conjuntoB[(*qtdB)++] = x;
        }
    }
}


void exibirMenu() {
    printf("\n\n-------------------------\n");
    printf("    MENU DE OPERAÇÕES   ");
    printf("\n-------------------------\n\n");
    printf("[1] Mostrar conjunto A\n");
    printf("[2] Mostrar conjunto B\n");
    printf("[3] União (A U B)\n");
    printf("[4] Interseção (A ∩ B)\n");
    printf("[5] Diferença (A - B)\n");
    printf("[6] Diferença (B - A)\n");
    printf("[7] Diferença simétrica (A U B) - (A ∩ B)\n");
    printf("[8] Pertinência (x ∈ A)\n");
    printf("[9] Inclusão (A C B)\n");
    printf("[10] Inclusão (B C A)\n");
    printf("[11] Inclusão própria (A C B e A ≠ B)\n");
    printf("[12] Inclusão própria (B C A e B ≠ A)\n");
    printf("[13] Cardinalidade (|A|)\n");
    printf("[14] Cardinalidade (|B|)\n");
    printf("[15] Complemento (Aᶜ)\n");
    printf("[0] Sair do programa\n");
    printf("\nEscolha uma opção: ");
}

void mostrarConjuntoA(int conjuntoA[], int qtdA) {
    printf("\nA = { ");
    for (int i = 0; i < qtdA; i++) {
        printf("%d ", conjuntoA[i]);
    }
    printf("}\n");
}

void mostrarConjuntoB(int conjuntoB[], int qtdB) {
    printf("\nB = { ");
    for (int i = 0; i < qtdB; i++) {
        printf("%d ", conjuntoB[i]);
    }
    printf("}\n");
}

void uniao(int conjuntoA[], int tamA,int conjuntoB[],int tamB){
    int achou;
    int i,j,k;

        printf("\nA U B = { ");
        for (i = 0; i < tamA; i++){
            printf("%d ",conjuntoA[i]);
        }
        for (j = 0; j < tamB; j++){
            achou = 0;
            for (k = 0; k < tamA; k++){
                if (conjuntoB[j] == conjuntoA[k]){
                    achou = 1;
                    break;
                }
            }
            if (!achou){
                printf("%d ",conjuntoB[j]);
            }
        }
        
        printf("}\n");
}

void intersecao(int conjuntoA[], int tamA,int conjuntoB[],int tamB){
            printf("\nIntersecao = { ");
        for (int i = 0; i < tamA; i++){
            for (int j = 0; j < tamB; j++){
                if (conjuntoA[i] == conjuntoB[j]){
                    printf("%d ", conjuntoA[i]);
                }
            }
        }
        printf("}\n");
}

void diferencaAB(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB) {
    int resultado[qtdA];
    int qtdR = 0;

    for (int i = 0; i < qtdA; i++) {
        int achou = 0;

        for (int j = 0; j < qtdB; j++) {
            if (conjuntoA[i] == conjuntoB[j]) {
                achou = 1;
                break;
            }
        }

        if (!achou) {
            resultado[qtdR++] = conjuntoA[i];
        }
    }

    printf("\nResultado da diferença (A - B):\n{ ");
    if (qtdR == 0) {
        printf("\nConjunto vazio\n");
        return;
    }
    for (int i = 0; i < qtdR; i++) {
        printf("%d ", resultado[i]);
    }
    printf("}\n");
}

void diferencaBA(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB) {
    int resultado[qtdB];
    int qtdR = 0;

    for (int i = 0; i < qtdB; i++) {
        int achou = 0;

        for (int j = 0; j < qtdA; j++) {
            if (conjuntoB[i] == conjuntoA[j]) {
                achou = 1;
                break;
            }
        }

        if (!achou) {
            resultado[qtdR++] = conjuntoB[i];
        }
    }

    printf("\nResultado da diferença (B - A):\n{ ");
    if (qtdR == 0) {
        printf("\nConjunto vazio\n");
        return;
    }
    for (int i = 0; i < qtdR; i++) {
        printf("%d ", resultado[i]);
    }
    printf("}\n");
}

void diferencaSimetrica(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB) {
    int achou;

    printf("\nDiferença simétrica = { ");

    for (int i = 0; i < qtdA; i++) {
        achou = 0;
        for (int j = 0; j < qtdB; j++) {
            if (conjuntoA[i] == conjuntoB[j]) {
                achou = 1;
                break;
            }
        }
        if (!achou) {
            printf("%d ", conjuntoA[i]);
        }
    }

    for (int i = 0; i < qtdB; i++) {
        achou = 0;
        for (int j = 0; j < qtdA; j++) {
            if (conjuntoB[i] == conjuntoA[j]) {
                achou = 1;
                break;
            }
        }
        if (!achou) {
            printf("%d ", conjuntoB[i]);
        }
    }

    printf("}\n");
}

void pertinencia(int conjuntoA[], int qtdA) {
    int elemento;
    int achou = 0;

    printf("\nInforme o elemento a ser verificado: ");
    scanf("%d", &elemento);

    for (int i = 0; i < qtdA; i++) {
        if (conjuntoA[i] == elemento) {
            achou = 1;
            break;
        }
    }
    printf("\nO elemento %d %s ao conjunto A\n", elemento, achou ? "pertence" : "não pertence");
}

void contidoAB(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB) {
    int achou = 0;

    for (int i = 0; i < qtdA; i++) {
        for (int j = 0; j < qtdB; j++) {
            if (conjuntoA[i] == conjuntoB[j]) {
                achou++;
                break;
            }
        }
    }

    if (achou == qtdA) {
        printf("\nO conjunto A está contido no conjunto B\n");
    } else {
        printf("\nO conjunto A não está contido no conjunto B\n");
    }
}

void contidoBA(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB) {
    int achou = 0;

    for (int i = 0; i < qtdB; i++) {
        for (int j = 0; j < qtdA; j++) {
            if (conjuntoB[i] == conjuntoA[j]) {
                achou++;
                break;
            }
        }
    }

    if (achou == qtdB) {
        printf("\nO conjunto B está contido no conjunto A\n");
    } else {
        printf("\nO conjunto B não está contido no conjunto A\n");
    }
}

void propriamenteContidoAB(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB) {
    int achou = 0;

    for (int i = 0; i < qtdA; i++) {
        for (int j = 0; j < qtdB; j++) {
            if (conjuntoA[i] == conjuntoB[j]) {
                achou++;
                break;
            }
        }
    }

    if (achou == qtdA && qtdA < qtdB) {
        printf("\nO conjunto A está propriamente contido no conjunto B\n");
    } else {
        printf("\nO conjunto A não está propriamente contido no conjunto B\n");
    }
}

void propriamenteContidoBA(int conjuntoA[], int qtdA, int conjuntoB[], int qtdB) {
    int achou = 0;

    for (int i = 0; i < qtdB; i++) {
        for (int j = 0; j < qtdA; j++) {
            if (conjuntoB[i] == conjuntoA[j]) {
                achou++;
                break;
            }
        }
    }

    if (achou == qtdB && qtdB < qtdA) {
        printf("\nO conjunto B está propriamente contido no conjunto A\n");
    } else {
        printf("\nO conjunto B não está propriamente contido no conjunto A\n");
    }
}

void cardinalidadeA(int qtdA) {
    printf("\nCardinalidade do conjunto A: |A| = %d\n", qtdA);
}

void cardinalidadeB(int qtdB) {
    printf("\nCardinalidade do conjunto B: |B| = %d\n", qtdB);
}

void complementoA(int conjuntoA[], int qtdA) {
            int elemento = 0;
            int qtdU = 0;

            printf("\nInforme o tamanho do conjunto Universo: ");
            scanf("%d", &qtdU);

            int conjuntoU[qtdU];

            printf("\nInforme os elementos do Conjunto Universo:");

            for(int j = 0; j < qtdU; j++){
                printf("Elemento %d: ", j + 1);
                scanf("%d", &elemento);

                conjuntoU[j] = elemento;
            }
            
            printf("\nAᶜ = { ");
            for (int i = 0; i < qtdU; i++){
                int achou = 0;
                for (int j = 0; j < qtdA; j++){
                    if(conjuntoU[i] == conjuntoA[j]){
                        achou = 1;
                        break;
                    }
                }
                if (achou == 0){
                    printf("%d ", conjuntoU[i]); 
                }
            }
            printf("}\n");
}


