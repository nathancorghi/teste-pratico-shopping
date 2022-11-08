package io.github.nathancorghi.pagina;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CarrinhoTela extends PaginaBase {

    public boolean retornaVisibilidadeProduto(String produto) {

        return $(byXpath("//*[@class='roboto-regular productName ng-binding' and contains(text(), '"+produto+"')]")).isDisplayed();
    }

    public CarrinhoTela aguardarCarregamentoTela() {

        aguardaElelentoEstarVisivel($(byXpath("//*[@class='roboto-regular center sticky fixedImportant ng-binding']")), 20);
        return this;
    }

    public CarrinhoTela tocarBotaoCheckout() {

        tocarElemento($(byId("checkOutButton")));
        return this;
    }

    public CarrinhoTela tocarBotaoRemove() {

        tocarElemento($(byXpath("//*[@class='remove red ng-scope']")));
        return this;
    }

    public String retornaTextoCarrinhoVazio() {

        return retornaTextoElemento($(byXpath("//*[@class='roboto-bold ng-scope']")));
    }
}
