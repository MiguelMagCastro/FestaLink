### 3.3.3 Processo 3 – ALUGUEL

Este processo é dedicado a um sistema de aluguel de espaços para eventos, permitindo que os usuários pesquisem, visualizem e reservem espaços de acordo com suas necessidades. Ele oferece funcionalidades como pesquisa de espaços com base em critérios como localização, capacidade, tipo de evento e disponibilidade de datas. Além disso, os usuários podem visualizar detalhes dos espaços, incluindo imagens, descrições, avaliações de usuários anteriores e preços. O sistema também facilita a reserva de um espaço para um evento, selecionando as datas desejadas e completando o processo de pagamento. Por fim, os usuários podem gerenciar suas reservas, visualizando detalhes da reserva, realizando modificações e cancelando reservas se necessário.

PROCESSO DE ALUGUEL
![image](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1381100-festalink/assets/113795385/59579a8e-0378-4409-9877-67131a844ce8)






#### Detalhamento das atividades



**Logar na Conta**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Login | Caixa de Texto  |    Formato de e-mail            |                   |
|     Senha            |        Caixa de Texto          |   Mínimo 8 Caracteres             |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Entrar | Fim do processo 1 | default |




**Analisar opções através do filtro**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Filtro | Seleção Múltipla  | Anúncios Indisponíveis |    ---           |
| Nome             | Caixa de Texto              | Não pode ficar em branco            | ---               |
| Localização             | Caixa de Texto              | ---            | Localização atual               |
| Capacidade             | Numérico             | ---            | Qualquer               |
| Preço             | Numérico              | ---            | Qualquer               |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Filtrar              | Página com filtros aplicados     | Locais por perto     |



**Analisar feedback do espaço escolhido**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Feedbacks | Área de Texto  |     Anúncios Indisponíveis           |      ---             |


| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Link da página com feedbacks | Página de feedabacks  | --- |


**Selecionar data**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Data | Seleção Múltipla  |     Dias Indisponíveis           |      ---             |


| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Datas diponíveis | Aréa com as datas  | --- |



**Escolher a duração do evento**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Duração | Área de Texto  |     Horários Indisponíveis           |      ---             |


| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Horário | Área com Horários Disponíveis  | --- |



**Escolher Quantidade de convidados**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Quantidade de convidados | Númerico  |  Somente até o limite de capacidade do espaço           |      ---             |


| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Quantidade | Disponibilidade de Convidados  | --- |



**Reservar espaço**



| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Reservar espaço | Área de Texto e Imagem  |           Anúncios Indisponíveis                     |       ---            |
|                 |                  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Reservar | Fim do processo  | --- |
|                      |                                |                   |


