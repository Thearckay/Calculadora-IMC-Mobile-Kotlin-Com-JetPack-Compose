# Calculadora IMC com Jetpack Compose

Este é um projeto simples de uma Calculadora de Índice de Massa Corporal (IMC) desenvolvido para fins de aprendizado, utilizando as tecnologias mais modernas do ecossistema Android.

[//]: # (## Screenshots)

[//]: # ()
[//]: # (<div align="center">)

[//]: # (  <img src="screenshots/screen.png" width="300px"  alt="Print" height="100px"/>)

[//]: # (</div>)

[//]: # ()
[//]: # (![Texto Alternativo]&#40;screenshots/screen.png&#41;)

##  Tecnologias Utilizadas

- **Kotlin**: Linguagem de programação principal.
- **Jetpack Compose**: Toolkit moderno para construção de interfaces nativas.
- **Material Design 3**: Componentes de interface seguindo os padrões atuais do Google.

##  Detalhes Técnicos

Neste projeto, foram explorados diversos conceitos fundamentais do Compose:

- **State Management**: Uso de `remember` e `mutableStateOf` para controlar o estado dos campos de entrada e a seleção de sexo.
- **Layout Composables**:
    - `Column` e `Row` para estruturar os elementos de forma vertical e horizontal.
    - `Spacer` com `Modifier.weight(1f)` para empurrar o botão de calcular para a base da tela, garantindo que ele ocupe o espaço restante.
- **Componentes Material 3**:
    - `OutlinedTextField` para entradas de texto com labels flutuantes.
    - `RadioButton` para seleção de gênero.
    - `Button` e `Text` com tipografia personalizada (`FontWeight`, `sp`).
- **KeyboardOptions**: Configuração de teclados numéricos específicos para os campos de Peso e Altura.

##  Funcionalidades

- Seleção de sexo (Masculino/Feminino).
- Entrada de dados para Idade, Altura e Peso.
- Layout responsivo que se adapta a diferentes tamanhos de tela.
- Área de resultado destacada.

##  Como executar o projeto

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/IMCcomJetpackCompose.git
   ```
2. Abra o projeto no **Android Studio Hedgehog** ou superior.
3. Aguarde a sincronização do Gradle.
4. Execute o app em um emulador ou dispositivo físico.

---
Desenvolvido por **Kayck Arcanjo**
