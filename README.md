


# Manual de Instruções para Executar o Chatbot

Este manual detalha como executar o chatbot de informações sobre o curso de Sistemas e Mídias Digitais. Abaixo estão listadas duas formas de executar o programa: via terminal ou Eclipse.

---

## Parte 1: Executando o Programa via Terminal

### 1. Certifique-se de ter o JDK instalado
O chatbot foi desenvolvido usando JDK 8 ou superior. Verifique se você possui uma versão compatível instalada no sistema.

### 2. Navegue até o Diretório do Projeto
Após baixar e descompactar o projeto completo, abra o terminal e navegue até o diretório raiz do projeto (onde se encontra a pasta `src/` com os arquivos `.java`).

### 3. Compile e Execute o Programa
Compile o código-fonte e execute o programa a partir da pasta raiz do projeto:

```bash
javac -d bin src/*.java
java -cp bin com.example.Main
```

Isso iniciará o chatbot no terminal.

---

## Parte 2: Executando o Programa via Eclipse

### 1. Importar o Projeto no Eclipse
No Eclipse, vá para **File > Import**.
Selecione **Existing Projects into Workspace** e clique em **Next**.
Na tela que aparecer, clique em **Browse** e selecione a pasta que contém o projeto completo.
Certifique-se de que o projeto está listado e marcado, então clique em **Finish**.

### 2. Configurar o JDK
Como o projeto foi desenvolvido com JDK 8 ou superior, o Eclipse pode solicitar a configuração do JDK caso não esteja configurado automaticamente.

### 3. Executar o Programa
No **Project Explorer**, navegue até a classe `Main.java` dentro do pacote `com.example`.
Clique com o botão direito em `Main.java` e selecione **Run As > Java Application**.
O chatbot será iniciado no console integrado do Eclipse.

---

## Como Interagir com o Chatbot

Após iniciar o chatbot, você pode interagir com ele digitando comandos no terminal ou no console do Eclipse. Aqui estão alguns exemplos de comandos:

- `curso`: Informações sobre o curso.
- `disciplina [nome]`: Informações sobre uma disciplina específica (por exemplo, "Programação II").
- `disciplinas do [X] período`: Lista as disciplinas de um período específico (por exemplo, "disciplinas do 1 período").
- `listar disciplinas`: Lista todas as disciplinas do curso.
- `professor [nome]`: Informações sobre um professor específico (por exemplo, "Professor Carlos").
- `sair`: Encerra a conversa com o chatbot.
- `help`: Exibe a lista de comandos disponíveis.

---

## Exemplo de Uso

### Terminal ou Eclipse:
```
Chatbot: Olá! Qual é o seu nome?
Usuário: João
Chatbot: Prazer em conhecê-lo, João! Como posso ajudar você hoje?
Usuário: Me fale sobre o curso.
Chatbot: O curso de Sistemas e Mídias Digitais forma bacharéis especializados em Sistemas Multimídia e Mídias Digitais...
Usuário: sair
Chatbot: Até logo, João!
```
---