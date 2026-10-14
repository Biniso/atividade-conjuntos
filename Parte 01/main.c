#include <stdio.h>
#include "funcoes.h"
#include <stdbool.h>
#include <unistd.h>

int main() { 

    int conjuntoA[100], conjuntoB[100];
    int qtdA = 0, qtdB = 0, opcao = 0;
    bool sair = false;

    informarElementosPopularConjuntos(conjuntoA, &qtdA, conjuntoB, &qtdB);

    while(sair == false){
        sleep(2);
        exibirMenu();
        scanf("%d", &opcao);
        switch (opcao){
        case 0:
            printf("\nSaindo do programa...\n");
            sair = true;
            break;  
            
        case 1:  
            mostrarConjuntoA(conjuntoA, qtdA); 
            break;
            
        case 2:  
            mostrarConjuntoB(conjuntoB, qtdB); 
            break;
            
        case 3:  
            uniao(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 4:  
            intersecao(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 5:  
            diferencaAB(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 6:  
            diferencaBA(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 7:  
            diferencaSimetrica(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 8:  
            pertinencia(conjuntoA, qtdA); 
            break;
            
        case 9:  
            contidoAB(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 10: 
            contidoBA(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 11: 
            propriamenteContidoAB(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 12: 
            propriamenteContidoBA(conjuntoA, qtdA, conjuntoB, qtdB); 
            break;
            
        case 13: 
            cardinalidadeA(qtdA); 
            break;
            
        case 14: 
            cardinalidadeB(qtdB); 
            break;
            
        case 15: 
            complementoA(conjuntoA, qtdA); 
            break;
            
        default:
            printf("\nOpcao invalida! Tente novamente.\n");
            break;
        }
    }
    return 0;
}
