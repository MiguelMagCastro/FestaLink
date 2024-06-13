### 3.3.1 Processo 1 – Cadastro de salão

Este processo demonstra o caminho que é feito pelo usuário dentro da plataforma para fazer o cadastro de um novo salão de festas para aluguel na nossa aplicaçao web. 



![image](https://github.com/ICEI-PUC-Minas-PPLES-TI/plf-es-2024-1-ti2-1381100-festalink/assets/129908336/5df2f033-83ec-4006-9be4-eb57a67f42f7)




#### Detalhamento das atividades


**Criar conta do salão**

## Tabela de Criação de Conta

| **Campo**       | **Tipo**         | **Restrições**         | **Valor default** |
|-----------------|------------------|------------------------|-------------------|
| Nome Completo   | Caixa de Texto   | 255 caracteres                   | ---               |
| Email           | Caixa de Texto   | formato de e-mail      |dominio@xxx.com              |
| senha           | Caixa de Texto   | mínimo de 8 caracteres | ---               |
| Confirmar senha           | Caixa de Texto   | mínimo de 8 caracteres | ---               |
| Telefone        | Caixa de Texto   | 15 números, formato telefone | +55 (xx) 12345-1234|
| Tipo de Usuário | Seleçao única    | Selecionar um          |                   |

| **Comandos**    | **Destino**                     | **Tipo**       |
|-----------------|---------------------------------|----------------|
| Resgistrar      | Login                           |   |




**Cadastrar informações do salão**

## Tabela de Salões

| Campo | Tipo | Restrições | Valor Default |
|---|---|---|---|
| Nome do Salão | Caixa de Texto | 255 caracteres | |
| Localização | Caixa de Texto | 255 caracteres | rua xxxx |
| Descrição | Caixa de Texto | | |
| Capacidade de Pessoas | Numérico | Maior que 0 | null |
| Preço Base | float | | |


| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| Cadastrar salão       | Fim do processo               |   |
| Meus Salões          |  Fim do processo   |    |






