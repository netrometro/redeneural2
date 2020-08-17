# Rede Neural Simplificada vs.0.2
Uma rede neural de estrutura simples para aprendizado escrita em Java.
Este projeto foi baseado na explicação de Victor Dias que se encontra neste endereço:
https://www.youtube.com/watch?v=NZlIYr1slAk

## Arquitetura

- Neurônio: Unidade básica de processamento da rede;
- Camada: Grupo isolado de neurônios;
- Rede Neural: Uma quantidade determinada de camadas;
- Ecosistema: Um ambiente para treinar e testar a RN. Este ambiente entrega seu estado (outputs) para a RN e recebe decisões (inputs) para interagir no ambiente;
- Evolução: Sistema que cria os indivíduos (RN) de uma população, coloca em um ambiente, recebendo o(s) vencedor(es), gera uma nova população através de mutação, colocando em um novo ambiente e assim por diante até uma quantidade recebida de gerações, retornando, ao final, o DNA vencedor. Um algoritmo generativo hiper simplificado.

## Linguagem

- Foi utilizado Java puro sem nenhuma biblioteca externa.

## Ecosistema

- O ecosistema é um jogo de corrida onde cada piloto (RN) escolhe entre três marchar (1,2,3) cada uma delas impussiona o carro uma quantidade fixa de vezes;
- Quando o piloto chega em uma parte determinada da pista, a força das marchas 1, 2 e 3 são trocados entre si. Isto acontece mais uma vez ante do final da corrida;
- É esperado que a RN aprenda em cada seguimento da pista, qual as melhores marchar 1, 2 e 3 é melhor;
- Durante os testes a RN aprendeu na 16 geração a melhor maneira de se pilotar nesta pista.

