
# Swagger USF API - OpenAPI

A USF (Unidade de Saúde da Família) nada mais é que uma unidade pública de saúde destinada a realizar atenção contínua nas especialidades básicas, com uma equipe multiprofissional habilitada para desenvolver as atividades de promoção, proteção e recuperação, características do nível primário de atenção.
Após conversa com funcionários dessas unidades notamos que há uma complexidade na gestão desse ambiente, pois precisam fazer a comunicação com outras USF’s, seja para realizar pedidos de recursos ou para gestão de profissionais, de maneira manual através de pranchetas e documentos por escrito. Fazendo o sistema desses ambientes vitais para o cuidado das comunidades brasileiras ficam propensos a erros e lentidão no atendimento.
Objetivando desenvolvimento para esse serviço, desenvolvemos uma API, para automatizar esses processos defasados.



## Funcionalidades

- Solicitation filtra por Status
- Doctor pode ser filtrado por nome
- Solicitation pode suspendar um pedido
- Um doutor pode verificar em qual USF ele está cadastrado
- Solicitation calcula a quantidade de recursos recebidos automaticamente

## Funcionalidades futuras

- Inventory calcular automaticamente a quantidade de recursos disponíveis
- Validar a Solicitation para evitar futos no estoque
- Endereço como Entidade
- Doutor não poder ver as informações de outros doutores

## Segurança 
### Perfis de usuário
- Administrador
- Operador da USF
- Médico

#### Requisitos funcionais

| ID   | Descrição       |       
| :---------- | :--------- |
| `RF001` | `A API permite ao Administrador cadastrar novos médicos ao banco de dados` | 
| `RF002` | `A API permite ao Administrador cadastrar novos times ao banco de dados` | 
| `RF003` | `A API permite ao Administrador cadastrar novas USF’s ao banco de dados` | 
| `RF004` | `A API permite ao Operador de USF cadastrar recursos da sua USF ao banco de dados` | 
| `RF005` | `A API permite ao Administrador deletar médicos do banco de dados` | 
| `RF006` | `A API permite ao Administrador deletar times do banco de dados` | 
| `RF007` | `A API permite ao Administrador deletar USF’s do banco de dados` |
| `RF008` | `A API permite ao Operador de USF deletar recursos da sua USF do banco de dados` |
| `RF009` | `A API permite ao Administrador atualizar as informações de um médico do banco de dados` |
| `RF010` | `A API permite ao Administrador atualizar as informações de um time do banco de dados` |
| `RF011` | `A API permite ao Administrador atualizar as informações de uma USF do banco de dados` |
| `RF012` | `A API permite ao Operador de USF atualizar as informações de um recurso da sua USF do banco de dados` |
| `RF013` | `A API permite ao Administrador listar os médicos com base em filtros pela área de atuação e ordenar por nomes` |
| `RF014` | `A API permite ao Administrador vincular um médico a um time` |
| `RF015` | `A API permite ao Administrador desvincular um médico de um time` |
| `RF016` | `A API permite ao Administrador vincular um time a uma USF` |
| `RF017` | `A API permite ao Administrador desvincular um time de uma USF` |
| `RF018` | `A API permite ao Operador de USF listar os médicos que compõe o time atualmente na sua USF` |
| `RF019` | `A API permite ao Operador de USF listar os recursos de sua USF` |
| `RF020` | `A API permite ao Operador de USF realizar um pedido de recursos` |
| `RF021` | `A API permite ao Operador de USF listar pedidos de recursos de outras USF’s filtrando com base na status do pedido de recurso` |
| `RF022` | `A API permite ao Operador de USF liberar recursos para outra USF` |
| `RF023` | `A API limita ao Operador de USF liberar recursos até alcançar o mínimo recomendado de tais recursos` |
| `RF024` | `A API permite ao Operador de USF atualizar o status do pedido de recursos` |
| `RF025` | `A API permite ao Operador de USF alterar a quantidade em estoque dos recursos` |
| `RF026` | `A API permite ao Médico verificar em qual USF ele está vinculado no momento. ` |
| `RF027` | `A API permite ao Administrador logar ao sistema com login e senha` |
| `RF028` | `A API permite ao Administrador trocar o login e a senha` |
| `RF029` | `A API permite ao Médico logar ao sistema com login e senha` |
| `RF030` | `A API permite ao Médico trocar o login e a senha dele` |
| `RF031` | `A API permite ao Operador de USF logar ao sistema com login e senha` |
| `RF032` | `A API permite ao Operador de USF trocar o login e senha dele` |


## Cobertura dos testes

Os testes estão com uma porcentagem de cobertura de: 94% em Classes, 80% em Métodos e 81% em Linhas
![image](https://user-images.githubusercontent.com/79091246/211013130-21d7096f-e902-4376-9c70-bc9856924677.png)


#### Pastas removidas dos testes: 
![image](https://user-images.githubusercontent.com/79091246/211013331-c3d6bfae-34a4-48a4-abce-4e787464c016.png)


## Documentação da API
### Doctor
#### Cria um doutor

```http
  POST /doctor
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna todos os doutores

```http
  GET /doctor
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna um doutor

```http
  GET /doctor/{idDoctor}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O idDoctor do doutor que você quer |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Doctor Not Found |

#### Atualiza um doutor

```http
  PUT /doctor/{idDoctor}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O idDoctor do doutor que você quer atualizar|
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Doctor Not Found |

#### Remove um doutor
```http
  DELETE /doctor/{idDoctor}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O idDoctor do doutor que você quer remover|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Doctor Not Found |

#### Vincula um doutor em um time
```http
  POST /doctor{idDoctor}/team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O ID do doutor que você quer vincular |
| `idTeam`      | `long` | **Obrigatório**. O ID do time que você quer vincular |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Doctor Not Found |

#### Desnvincula um doutor de um time
```http
  DELETE /doctor{idDoctor}/team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O ID do doutor que você quer desvincular |
| `idTeam`      | `long` | **Obrigatório**. O ID do time que você quer desvincular |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Doctor Not Found |

### Team

#### Cria um time

```http
  POST /team
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna todos os times

```http
  GET /team
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna um time

```http
  GET /team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Team Not Found |

#### Atualiza um time

```http
  PUT /team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer atualizar|
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Team Not Found |

#### Remove um time
```http
  DELETE /team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer remover|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Team Not Found |

#### Retorna todos doutores que pertencem a um time 
```http
  GET /team/{idTeam}/doctor
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer visualizar|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Team Not Found |

#### Vincula um time a uma USF 
```http
  POST /team{idTeam}/usf/{idUsf}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer vincular|
|`idUSF`|`long`|**Obrigatório**. O idUsf da USF que você quer vincular|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Team Not Found |

#### Desnvincula um time de uma usf
```http
  DELETE /team{idTeam}/usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer desvincular|
|`idUSF`|`long`|**Obrigatório**. O idUsf da USF que você quer desvincular|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Team Not Found |

### USF
#### Cria uma USF

```http
  POST /usf
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna todas as usf's

```http
  GET /usf
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna uma USF

```http
  GET /usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUsf`      | `long` | **Obrigatório**. O idUsf do time que você quer |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| USF Not Found |

#### Atualiza uma USF

```http
  PUT /usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUsf`      | `long` | **Obrigatório**. O idUsf da usf que você quer atualizar|
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| USF Not Found |

#### Remove uma usf
```http
  DELETE /usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUsf`      | `long` | **Obrigatório**. O idUsf da usf que você quer remover|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| USF Not Found |

### Solicitation
#### Cria uma solicitação

```http
  POST /solicitation
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna todas as solicitações

```http
  GET /solicitation
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna uma solicitação

```http
  GET /solicitation/{solicitationId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O solicitationId da solicitação que você quer |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Solicitation Not Found |

#### Atualiza uma solicitação

```http
  PUT /solicitation/{solicitationId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `solicitationId`      | `long` | **Obrigatório**. O solicitationId da solicitação que você quer atualizar|
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Solicitation Not Found |

#### Remove uma solicitação
```http
  DELETE /solicitation/{solicitationId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `solicitationId`      | `long` | **Obrigatório**. O solicitationId da solicitação que você quer remover|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Solicitation Not Found |

### Resource
#### Cria um recurso

```http
  POST /resource
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna todos os recursos

```http
  GET /resource
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna um recurso

```http
  GET /resource/{resourceId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O resourceId do recurso que você quer |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Resource Not Found |

#### Atualiza um recurso

```http
  PUT /resource/{resourceId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O resourceId do recurso que você quer atualizar|
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Resource Not Found |

#### Remove uma solicitação
```http
  DELETE /resource/{resourceId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O resourceId do recusro que você quer remover|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Resource Not Found |

### Inventory
#### Cria um inventário

```http
  POST /inventory
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna todos os inventários

```http
  GET /inventory
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Retorna um inventário

```http
  GET /inventory/{inventoryId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `inventoryId`      | `long` | **Obrigatório**. O inventoryId do inventário que você quer |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Inventory Not Found |

#### Atualiza um inventário

```http
  PUT /inventory/{inventoryId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O inventoryId do inventário que você quer atualizar|
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Inventory Not Found |

#### Remove um inventário
```http
  DELETE /inventory/{inventoryId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `inventoryId`      | `long` | **Obrigatório**. O inventoryId do inventário que você quer remover|
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| Inventory Not Found |

### Segurança
#### Criação de um usuário

```http
  POST /user/create
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `token` | `string` | **Obrigatório**. Token de Acesso |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Atualiza um usuário

```http
  POST /user/update/{id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Id`      | `long` | **Obrigatório**. O id do usuário que você quer atualizar|
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |
|`404`| User Not Found |

#### Define cargos aos usuários 
```http
POST /user/role
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
|`token`|`string`|**Obrigatório**. Token de Acesso|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid |
|`403`| Forbidden |

#### Logar no Sistema
```http
POST /login
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Id`      | `long` | **Obrigatório**. O id do usuário que você irá logar|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`404`| User Not Found |


## Autores

- [@luistabile](https://github.com/LuisTabile)
- [@rafinhaLQ](https://github.com/rafinhaLQ)
- [@solanokruger](https://github.com/solanokruger/)

