### 3.3.4 Processo 4 – Enviar avaliação

Esse processo detalha o sistema de avaliação realizado pelo usuário apos uso do salão. O processo se inicia com o usuário longando em sua conta e escrevendo a avaliação, logo após a avaliação é enviada para análise e se não conter palavras inapropriadas e número de caracteres abaixo que o mínimo permitido a avaliação é publicada.

![BPMN (2)](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1381100-festalink/assets/114035802/9477be47-e799-4cbe-a8ab-2c7381d90f52)




#### Detalhamento das atividades



**Logar na conta**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Login           | Caixa de Texto   | Formato de e-mail |                |
| Senha           | Caixa de Texto   | Mínimo de 8 caracteres |           |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| Entrar               | Gerar feedback            | default           |


**Gerar avaliação**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Gerar avaliação | Seleção Múltipla |Maximo 5 estrelas/ Usuario "Cliente" |                   |
|                 |                  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Enviar avaliação      | Publicar avaliação            | (default/cancel/  ) |
|                      |                                |                   |


| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Escrever avaliação | Área de Texto |Mínimo de 4 caracteres/ Usuario "Cliente" |                   |
|                 |                  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Enviar avaliação      | Publicar avaliação            | (default/cancel/  ) |
|                      |                                |                   |

**Publicar avaliação**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
|          |    |  |                |


| **Comandos**         |  **Destino**                   | **Tipo** |
| ----                  | ----                            | ----               |
| Publicar              | Fim do processo           | default           |

