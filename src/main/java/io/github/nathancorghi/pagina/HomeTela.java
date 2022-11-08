package io.github.nathancorghi.pagina;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomeTela extends PaginaBase {

    public HomeTela tocarBotaoSpecialOffer() {

        tocarElemento($(byXpath("//*[@ng-click=\"gotoElement('special_offer_items')\"]")));
        return this;
    }

    public HomeTela tocarBotaoSeeOffer() {

        tocarElemento($(byId("see_offer_btn")));
        return this;
    }

    public HomeTela tocarBotaoCarrinho() {

        tocarElemento($(byId("menuCart")));
        return this;
    }

    public HomeTela tocarBotaoLupa() {

        tocarElemento($(byId("menuSearch")));
        return this;
    }

    public HomeTela aguardarCarregamentoCampoPesquisa() {

        aguardaElelentoEstarVisivel($(byId("autoComplete")), 5);
        return this;
    }

    public HomeTela preencherCampoPesquisa(String produto) {

        escreverTexto($(byId("autoComplete")), produto);
        return this;
    }

    public HomeTela selecionarProduto(String produto) {

        tocarElemento($(byXpath("//p[contains(text(), '"+produto+"')]")));
        return this;
    }

    public HomeTela aguardarCarregamentoTela() {

        aguardaElelentoEstarVisivel($(byId("tabletsImg")), 10);
        return this;
    }

    public HomeTela aguardarVisibilidadeBotaoSeeOffer() {

        aguardaElelentoEstarVisivel($(byId("see_offer_btn")), 10);
        return this;
    }
}
