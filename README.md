# Swagger USF API - OpenAPI

A USF (Unidade de Saúde da Família) nada mais é que uma unidade pública de saúde destinada
    a realizar atenção contínua nas especialidades básicas, com uma equipe multiprofissional
    habiitada para desenvolver as atividades de promoção, proteção e recuperação, características
    do nível primário de atenção. 
    
Desenvolvemos essa API para automatizar os processos defasados e arcaicos das USFs.

## Documentação da API
### Doctor
#### Cria um doutor

```http
  POST /doctor
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
| `401`| Access token is missing or invalid |

#### Retorna todos os doutores

```http
  GET /doctor
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
| `401`| Access token is missing or invalid |

#### Retorna um doutor

```http
  GET /doctor/{idDoctor}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O idDoctor do doutor que você quer |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Doctor Not Found |

#### Atualiza um doutor

```http
  PUT /doctor/{idDoctor}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O idDoctor do doutor que você quer atualizar|
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|
|`404`| Doctor Not Found |

#### Remove um doutor
```http
  DELETE /doctor/{idDoctor}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O idDoctor do doutor que você quer remover|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid|
|`404`| Doctor Not Found |

#### Vincula um doutor em um time
```http
  POST /doctor{idDoctor}/team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O ID do doutor que você quer vincular |
| `idTeam`      | `long` | **Obrigatório**. O ID do time que você quer vincular |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Doctor Not Found |

#### Desnvincula um doutor de um time
```http
  DELETE /doctor{idDoctor}/team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idDoctor`      | `long` | **Obrigatório**. O ID do doutor que você quer desvincular |
| `idTeam`      | `long` | **Obrigatório**. O ID do time que você quer desvincular |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Doctor Not Found |

### Team

#### Cria um time

```http
  POST /team
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
| `401`| Access token is missing or invalid |

#### Retorna todos os times

```http
  GET /team
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
| `401`| Access token is missing or invalid |

#### Retorna um time

```http
  GET /team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Team Not Found |

#### Atualiza um time

```http
  PUT /team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer atualizar|
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|
|`404`| Team Not Found |

#### Remove um time
```http
  DELETE /team/{idTeam}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer remover|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid|
|`404`| Team Not Found |

#### Retorna todos doutores que pertencem a um time 
```http
  GET /team/{idTeam}/doctor
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer visualizar|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Team Not Found |

#### Vincula um time a uma USF 
```http
  POST /team{idTeam}/usf/{idUsf}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer vincular|
|`idUSF`|`long`|**Obrigatório**. O idUsf da USF que você quer vincular|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Team Not Found |

#### Desnvincula um time de uma usf
```http
  DELETE /team{idTeam}/usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O idTeam do time que você quer desvincular|
|`idUSF`|`long`|**Obrigatório**. O idUsf da USF que você quer desvincular|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Team Not Found |

### USF
#### Cria uma USF

```http
  POST /usf
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
| `401`| Access token is missing or invalid |

#### Retorna todas as usf's

```http
  GET /usf
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
| `401`| Access token is missing or invalid |

#### Retorna uma USF

```http
  GET /usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUsf`      | `long` | **Obrigatório**. O idUsf do time que você quer |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| USF Not Found |

#### Atualiza uma USF

```http
  PUT /usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUsf`      | `long` | **Obrigatório**. O idUsf da usf que você quer atualizar|
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|
|`404`| USF Not Found |

#### Remove uma usf
```http
  DELETE /usf/{idUsf}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idUsf`      | `long` | **Obrigatório**. O idUsf da usf que você quer remover|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid|
|`404`| USF Not Found |

### Solicitation
#### Cria uma solicitação

```http
  POST /solicitation
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
| `401`| Access token is missing or invalid |

#### Retorna todas as solicitações

```http
  GET /solicitation
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
| `401`| Access token is missing or invalid |

#### Retorna uma solicitação

```http
  GET /solicitation/{solicitationId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `idTeam`      | `long` | **Obrigatório**. O solicitationId da solicitação que você quer |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Solicitation Not Found |

#### Atualiza uma solicitação

```http
  PUT /solicitation/{solicitationId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `solicitationId`      | `long` | **Obrigatório**. O solicitationId da solicitação que você quer atualizar|
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|
|`404`| Solicitation Not Found |

#### Remove uma solicitação
```http
  DELETE /solicitation/{solicitationId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `solicitationId`      | `long` | **Obrigatório**. O solicitationId da solicitação que você quer remover|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid|
|`404`| Solicitation Not Found |

### Resource
#### Cria um recurso

```http
  POST /resource
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
| `401`| Access token is missing or invalid |

#### Retorna todos os recursos

```http
  GET /resource
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
| `401`| Access token is missing or invalid |

#### Retorna um recurso

```http
  GET /resource/{resourceId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O resourceId do recurso que você quer |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Resource Not Found |

#### Atualiza um recurso

```http
  PUT /resource/{resourceId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O resourceId do recurso que você quer atualizar|
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|
|`404`| Resource Not Found |

#### Remove uma solicitação
```http
  DELETE /resource/{resourceId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O resourceId do recusro que você quer remover|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid|
|`404`| Resource Not Found |

### Inventory
#### Cria um inventário

```http
  POST /inventory
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
| `401`| Access token is missing or invalid |

#### Retorna todos os inventários

```http
  GET /inventory
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
| `401`| Access token is missing or invalid |

#### Retorna um inventário

```http
  GET /inventory/{inventoryId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `inventoryId`      | `long` | **Obrigatório**. O inventoryId do inventário que você quer |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`401`| Access token is missing or invalid|
|`404`| Inventory Not Found |

#### Atualiza um inventário

```http
  PUT /inventory/{inventoryId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `resourceId`      | `long` | **Obrigatório**. O inventoryId do inventário que você quer atualizar|
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|
|`404`| Inventory Not Found |

#### Remove um inventário
```http
  DELETE /inventory/{inventoryId}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `inventoryId`      | `long` | **Obrigatório**. O inventoryId do inventário que você quer remover|
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
|`401`| Access token is missing or invalid|
|`404`| Inventory Not Found |

### Segurança
#### Criação de um usuário

```http
  POST /user/create
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|

#### Atualiza um usuário

```http
  POST /user/update/{id}
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `Id`      | `long` | **Obrigatório**. O id do usuário que você quer atualizar|
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|
|`404`| User Not Found |

#### Define cargos aos usuários 
```http
POST /user/role
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
|`api_key`|`string`|**Obrigatório**. A chave da sua API|

| Response      | Descrição                           |
| :----------  | :---------------------------------- |
| `200`| OK |
|`400`| Bad Request|
|`401`| Access token is missing or invalid|

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
