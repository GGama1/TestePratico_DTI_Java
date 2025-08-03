# Calculadora de PetShop - DTI Digital

## Instruções para executar o sistema

### Pré-requisitos
- Java 8 ou superior
- IDE Java (Eclipse, IntelliJ, NetBeans, etc.)

### Estrutura do Projeto
```
src/
├── controller/
│   └── PetShopController.java
├── model/
│   └── PetShop.java
└── view/
    └── TelaPetShop.java
```

### Como executar
1. Importe o projeto na sua IDE Java
2. Execute a classe `TelaPetShop.java`
3. A interface gráfica será aberta
4. Preencha os campos:
   - **Data**: formato DD/MM/AAAA (ex: 03/08/2018)
   - **Quantidade de cães pequenos e grandes**: selecione nos dropdowns
5. Clique em "Calcular" para ver o resultado
6. Use "Limpar" para resetar os campos

## Lista de premissas assumidas

- Formato de data obrigatório: DD/MM/AAAA
- Pelo menos 1 cão (pequeno ou grande) deve ser informado para realizar o cálculo
- Distâncias dos petshops são fixas: Meu Canino Feliz (2km), Vai Rex (1,7km), ChowChawgas (800m)
- Preços dos petshops seguem as regras definidas no problema
- Final de semana considerado: sábado e domingo
- Em caso de empate no preço total, o critério de desempate é a menor distância
- Valores monetários são tratados como inteiros (centavos) para evitar problemas de arredondamento

## Decisões de projeto

### Arquitetura
- **Padrão MVC**: Separação clara entre Model, View e Controller
- **Java Swing**: Escolhido para interface gráfica nativa e simplicidade
- **LocalDate**: Utilizado para manipulação e validação de datas

### Estrutura de Classes
- **PetShop (Model)**: Representa um petshop com nome, distância e preço calculado
- **PetShopController**: Contém toda a lógica de negócio e cálculos
- **TelaPetShop (View)**: Interface gráfica com validações de entrada

### Implementação
- Cálculo dinâmico dos preços baseado no dia da semana
- Validações de entrada com mensagens de erro amigáveis
- Resultado simples mostrando apenas o nome do petshop e preço total
- Tratamento de exceções para garantir estabilidade da aplicação
