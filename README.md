Desafio Prático de Programação - Prothera Tecnologia
Este projeto é a solução para o Teste Prático de Programação da Prothera Tecnologia, desenvolvido em Java. O objetivo é gerenciar uma lista de funcionários de uma indústria, implementando os requisitos especificados, como manipulação de dados, cálculos financeiros e formatação de saída em formato tabular.
Estrutura do Projeto
O projeto é composto por três classes principais:

Pessoa.java: Classe base com os atributos nome (String) e dataNascimento (LocalDate), além de métodos para acessar e formatar a data no padrão dd/MM/yyyy.
Funcionario.java: Classe que estende Pessoa, adicionando os atributos salario (BigDecimal) e funcao (String), com métodos para formatar o salário com separador de milhar (ponto) e decimal (vírgula).
Main.java: Classe principal que implementa todas as funcionalidades exigidas no desafio, incluindo manipulação de dados e impressão formatada.

Requisitos Atendidos
O projeto cumpre todos os requisitos do desafio:

Classe Pessoa: Implementada com os atributos nome e dataNascimento.
Classe Funcionário: Estende Pessoa, com os atributos salario e funcao.
Classe Principal (Main):

3.1: Inserção de uma lista de 10 funcionários com dados predefinidos.
3.2: Remoção do funcionário "João" da lista.
3.3: Impressão de todos os funcionários em formato tabular, com data no formato dd/MM/yyyy e salário formatado (ex: 2.009,44).
3.4: Aplicação de aumento de 10% nos salários de todos os funcionários.
3.5 e 3.6: Agrupamento dos funcionários por função em um Map e impressão dos grupos, ordenados alfabeticamente, com contagem de funcionários por função e separadores visuais.
3.8: Impressão dos funcionários com aniversário em outubro (mês 10) e dezembro (mês 12) em formato tabular.
3.9: Identificação e impressão do funcionário mais velho, exibindo nome e idade.
3.10: Impressão da lista de funcionários em ordem alfabética, em formato tabular.
3.11: Cálculo e impressão do total dos salários, formatado com separador de milhar e decimal.
3.12: Cálculo e impressão de quantos salários mínimos (R$ 1.212,00) cada funcionário ganha, em formato tabular.



Formatação da Saída

Todas as listas de funcionários são exibidas em formato tabular, com colunas alinhadas (Nome, Nascimento, Salário, Função) e separadores (-) para melhor legibilidade.
Para o agrupamento por função (3.6), a saída foi aprimorada com:

Contagem de funcionários por função (ex: "Função: Operador (2 funcionários)").
Ordenação alfabética das funções.
Linha de separação após cada grupo.


Valores monetários são formatados com ponto como separador de milhar e vírgula como decimal (ex: 2.210,38).
Datas são exibidas no formato dd/MM/yyyy (ex: 18/10/2000).

Tecnologias Utilizadas

Java: Versão 8 ou superior (para suporte a LocalDate e BigDecimal).
Bibliotecas:

java.time (para manipulação de datas).
java.math.BigDecimal (para cálculos precisos de valores monetários).
java.text.NumberFormat (para formatação de números no padrão brasileiro).
java.util.stream (para manipulação de coleções com Streams API).


IDE: Desenvolvido e testado em [insira sua IDE, ex: Eclipse, NetBeans ou IntelliJ IDEA].

Como Executar

Pré-requisitos:

Java 8 ou superior instalado.
Uma IDE compatível (Eclipse, NetBeans, IntelliJ IDEA, etc.) ou compilador Java via linha de comando.


Passos:

Clone ou baixe este projeto.
Crie um projeto Java em sua IDE e adicione as classes Pessoa.java, Funcionario.java e Main.java.
Execute a classe Main.java, que contém o método main.


Saída:

O programa imprime no console todas as saídas solicitadas, em formato tabular, conforme os requisitos do desafio.
