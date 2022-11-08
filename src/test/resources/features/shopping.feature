# language: pt
Funcionalidade: Shopping validation test

    Cenario: Validar especificacoes do produto
        Dado que estou na secao Special Offer
        Quando eu acessar a oferta
        Entao as especificacoes do produto devem estar de acordo com o banco de dados

    Cenario: Selecionar alteracao de cor do produto no carrinho
        Dado que estou na secao Special Offer
        Quando eu acessar a oferta
        E eu alterar a cor do produto conforme cor informada no banco de dados
        E eu adicionar o produto no carrinho
        Entao o produto sera adicionado no carrinho com a cor selecionada

    Cenario: Validar pagina de checkout
        Dado que tenho um produto pesquisado
        Quando eu selecionar o produto pesquisado
        E eu alterar a cor do produto diferente da cor informada no banco de dados
        E eu alterar a quantidade de produtos
        E eu adicionar o produto no carrinho
        E eu acessar a pagina de checkout
        Entao a soma dos precos devem corresponder ao total apresentado em tela
        E a cor devera ser atualizada no banco de dados

    Cenario: Remover produto do carrinho de compras
        Dado que estou na secao Special Offer
        Quando eu acessar a oferta
        E eu adicionar o produto no carrinho
        E eu remover o produto do carrinho
        Entao o carrinho de compras devera estar vazio