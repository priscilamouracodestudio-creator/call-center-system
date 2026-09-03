# 📞 Call Center System

Sistema de atendimento de call center desenvolvido em **Java**, implementando estruturas de dados encadeadas (**Fila** e **Pilha**) do zero, com **Strategy Pattern** para decidir dinamicamente a ordem de atendimento dos clientes.

---

## 💡 O problema

Um balcão de atendimento precisa:

- 🎟️ Colocar clientes em uma **Fila de Espera** e atendê-los em ordem
- ↩️ Permitir que o atendente **desfaça** o encerramento de uma ficha, caso tenha clicado em "encerrar" por engano — usando uma **Pilha de Histórico**

A partir desse problema clássico de estrutura de dados, fui além do básico e construí um sistema com regras de negócio reais, arquitetura orientada a boas práticas e um algoritmo de priorização inteligente.

---

## ✨ Funcionalidades

- 🆕 **Criar ficha** de atendimento, com nome do cliente e nível de prioridade
- ▶️ **Chamar o próximo** cliente, seguindo a estratégia de atendimento ativa
- ✅ **Encerrar atendimento** em andamento
- ↩️ **Desfazer o último encerramento**, recuperando a ficha direto para o atendimento atual
- 🔍 **Consultar uma ficha por ID**, em qualquer estado (esperando, em atendimento ou já encerrada)
- 🔄 **Trocar a estratégia de atendimento** em tempo de execução (FIFO ou Prioridade + Tempo de Espera)

---

## 🧠 Decisões de arquitetura

### 📦 Estruturas de dados implementadas do zero
Em vez de usar `Queue`/`Deque` prontos do Java, a **Fila de Espera** e a **Pilha de Histórico** foram implementadas manualmente, com nós encadeados (`No<T>`), incluindo operações como inserção, remoção do meio da lista, e travessia — um bom exercício de fundamentos de estrutura de dados.

### 🎯 Strategy Pattern
A lógica de "quem é o próximo a ser atendido" é isolada em uma interface (`EstrategiaDeAtendimento`), com duas implementações:

| Estratégia | Como decide |
|---|---|
| 🟦 `EstrategiaFIFO` | Ordem pura de chegada |
| 🟨 `EstrategiaComPrioridade` | `score = peso da prioridade × 1000 + segundos de espera` |

A fórmula de prioridade garante que clientes urgentes sejam atendidos primeiro, mas **evita starvation** (inanição): mesmo um cliente de prioridade normal, esperando tempo suficiente, eventualmente supera um cliente urgente recém-chegado.

O `CallCenter` não sabe qual estratégia está usando — ele apenas delega a decisão, o que permite trocar a regra de atendimento **sem alterar nenhuma outra parte do sistema**.

### 🧱 Princípios SOLID
- **S**RP: cada classe tem uma única responsabilidade (`Ficha` só guarda dados, `FilaDeEspera` só organiza a estrutura, `EstrategiaDeAtendimento` só decide a ordem, `CallCenter` só orquestra)
- **O**CP: novas estratégias de atendimento podem ser adicionadas sem modificar o código existente
- **D**IP: o `CallCenter` depende da abstração `EstrategiaDeAtendimento`, não de uma implementação concreta

---

## 🗂️ Estrutura do projeto

```
com.callcenter
├── model/
│   ├── Ficha.java
│   └── NivelPrioridade.java
├── structure/
│   ├── No.java
│   ├── FilaDeEspera.java
│   └── PilhaDeHistorico.java
├── strategy/
│   ├── EstrategiaDeAtendimento.java
│   ├── EstrategiaFIFO.java
│   └── EstrategiaComPrioridade.java
├── service/
│   └── CallCenter.java
└── Main.java

test/com.callcenter/structure/
├── FilaDeEsperaTest.java
└── PilhaDeHistoricoTest.java
```

---

## 🧪 Testes automatizados

O projeto conta com testes unitários (JUnit 5) cobrindo o comportamento das duas estruturas de dados construídas do zero:

- ✅ `FilaDeEsperaTest` — fila começa vazia, enfileirar adiciona corretamente, `chamarProximo()` respeita a ordem FIFO
- ✅ `PilhaDeHistoricoTest` — pilha começa vazia, empilhar adiciona corretamente, `desempilhar()` respeita a ordem LIFO

Para rodar os testes:

```bash
mvn test
```

---

## 🛠️ Tecnologias

- ☕ Java
- 📦 Maven
- 🧪 JUnit 5

---

## ▶️ Como executar

```bash
git clone https://github.com/priscilamouracodestudio-creator/call-center-system.git
cd call-center-system
mvn compile
mvn exec:java -Dexec.mainClass="com.callcenter.Main"
```

Ou, se estiver usando o IntelliJ IDEA: abra o projeto e execute a classe `Main.java` diretamente.

---

## 🚀 Possíveis melhorias futuras

- 💾 Persistência dos dados em arquivo
- 📊 Painel de métricas (tempo médio de espera, tempo médio de atendimento)
- 🖥️ Interface gráfica

---

## 👩‍💻 Autora

Feito com bastante café e algumas exceções de `NullPointerException` no caminho, por **Priscila Moura**.