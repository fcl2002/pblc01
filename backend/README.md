# WealthWise - Site de Análise de Investimentos

# [https://github.com/fcl2002/wealthwise](GitHub)

# Language: Python

## Descrição
Uma aplicação de apoio ao investidor iniciante que oferece análises, simulações e monitoramente de investimentos em tempo real.

## Definições
- **_ticker_** - termo utilizado para identificar ações, fundos ou outros instrumentos financeiros em uma bolsa de valores.

## Instalação
    1. Clone o repositório:
        $ git clone https://github.com/fcl2002/wealthwise.git
    2. Instale as dependências:
        $ pip install -r requirements.txt
    3. Configure variáveis de ambiente:
        - DATABASE_URL=<url do banco de dados>
        - API_KEY=<sua API key>

## Como Usar
    1. Para rodar o backend:
        $ python manage.py runserver (Django)
    2. Acesse a aplicação:
        http://localhost:8000

## Funcionalidades

- [ ] O usuário deve poder criar uma conta.
- [ ] O usuário deve poder conectar-se em uma conta existente.
- [ ] O usuário deve poder analisar o retorno de até 3 ativos em diferentes janelas temporais (diária, semanal, mensal e anual), visualizando métricas como retorno acumulado, anualizado, volatilidade, além de comparativos gráficos.
- [ ] O usuário deve poder simular a performance passada de uma carteira de investimentos.
- [ ] O usuário deve poder planejar financeiramente uma aposentadoria através da calculadora, com a possibilidade de salvar e comparar diferentes cenários.
- [ ] O usuário deve poder calcular o risco atrelado a um ativo ou uma carteira de investimento através da análise de volatilidade.
- [ ] O usuário deve poder calcular de forma estática uma aplicação financeira de renda fixa, comparando diferentes produtos (CDBs, Tesouro Direto, etc) e visualizando gráficos de rendimento.
- [ ] O usuário deve poder visualizar estimativas de performance de uma ação para os próximos 12 meses.
- [ ] O usuário deve poder otimizar uma carteira através da relação risco-retorno, utilizando o modelo de otimização de carteiras de Markowitz.
- [ ] O usuário deve poder criar, editar e excluir múltiplas carteiras de investimentos.
- [ ] O usuário deve poder monitorar em tempo real as cotações dos ativos de sua carteira.
- [ ] O sistema deve ser capaz de buscar informações sobre os ativos selecionados, como balanços financeiros, relatórios de resultados trimestrais, e notícias relacionadas aos ativos.
- [ ] O usuário deve poder definir alertas personalizados para variações de preço ou eventos específicos dos ativos monitorados, podendo receber notificações via e-mail, SMS ou push notifications.


## Contribuição


## Licença