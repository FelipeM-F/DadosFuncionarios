# Gerenciamento de Funcionários em Java

Este projeto em **Java** realiza diversas operações sobre uma lista de funcionários, incluindo inserção, remoção, atualização de salários, agrupação por função, busca por aniversariantes, ordenação alfabética e cálculos financeiros.

## 🚀 Funcionalidades

- 📌 **Cadastro de Funcionários** com nome, data de nascimento, salário e função.
- ✂️ **Remoção de Funcionários** pelo nome.
- 📈 **Ajuste Salarial**: Aplica aumento de 10% nos salários.
- 📂 **Agrupamento por Função** em um `Map<String, List<Funcionario>>`.
- 🎂 **Listagem de Aniversariantes** de meses específicos.
- 🔍 **Busca pelo Funcionário Mais Velho** com cálculo de idade.
- 🔠 **Ordenação Alfabética** da lista de funcionários.
- 💰 **Cálculo do Total de Salários** da empresa.
- 📊 **Cálculo de Quantos Salários Mínimos Cada Funcionário Ganha**.

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Collections API (List, Map, Streams)**
- **BigDecimal** para cálculos financeiros precisos
- **LocalDate** para manipulação de datas

## 📦 Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/gerenciamento-funcionarios.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd gerenciamento-funcionarios
   ```
3. Compile e execute o projeto:
   ```bash
   javac Main.java && java Main
   ```

## 📌 Exemplo de Saída

```
-----------------------------------------------------------------------------
Nome        Data Nascimento  Salário        Função         
-----------------------------------------------------------------------------
Maria       18/10/2000       R$ 2.210,38     Operador       
João        12/05/1990       R$ 2.512,82     Operador       
Miguel      14/10/1988       R$ 21.231,87    Diretor        
...
```


