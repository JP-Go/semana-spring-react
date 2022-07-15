# DSMeta backend

## Antes de tudo, legenda:

`VERBO_HTTP /rota/subrota?parametro=<tipo>&[parametro=<tipo>]`

Todas as rotas seguem o padrão acima, onde:

- `VERBO_HTTP`: verbo HTTP correspondente da requisição;
- `/rota/subrota`: Rota a partir da raiz do sistema;
- `?parametro=<tipo>`: Informa que a rota possui um parâmetro do formato `tipo`, 
que deve ser informado. Se o parâmetro ou grupo de parâmetros estiver envolvido 
em `[]`, isso indica que o(s) parametro(s) é(são) opicional(ais).

**Exemplo:** A rota abaixo é de uma requisição HTTP `GET` na rota `/artigos`
com parâmetro opicional `data` de formato de uma data 
[ISO8601](https://pt.wikipedia.org/wiki/ISO\_8601).

```
GET /artigos?[data=<data-ISO8601>]
```

Ps.: Exemplo meramente ilustrativo

## Rotas

`GET /sales?[minDate=<data-ISO8601date>&maxDate=<data-ISO8601>]`

Retorna todas as vendas entre data mínima `minDate` e data máxima `maxDate`. 

- Se `minDate` não for passada, assume-se a data mínima de _um ano antes da data atual do sistema_
- Se `maxDate` não for passada, assume-se data máxima _a data atual do sistema_


