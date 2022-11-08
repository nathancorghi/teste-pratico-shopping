package io.github.nathancorghi.pagina;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;

public class ProdutoTela extends PaginaBase {

    public List<String> retornaInformacoesProduto() {

        List<String> informacoes = new ArrayList<>();

        rolarTelaAteElemento($(byXpath("//*[@class='roboto-regular product_specifications ng-scope']")));

        List<WebElement> informacoesProduto = $(byXpath("//*[@class='roboto-regular product_specifications ng-scope']"))
                .findElements(byXpath("//*[@class='value ng-binding']"));

        informacoes.add(retornaTextoElemento($(byXpath("//*[@class='roboto-regular screen768 ng-binding']"))));

        for (WebElement element : informacoesProduto) {
            informacoes.add(retornaTextoElemento(element));
        }

        return informacoes;
    }

    public ProdutoTela aguardarProdutoEstarVisivelEmTela() {

        aguardaElelentoEstarVisivel($(byXpath("//*[@class='roboto-regular screen768 ng-binding']")), 20);
        return this;
    }

    public ProdutoTela selecionarCorProduto(String cor) {

        tocarElemento($(byXpath("//*[@id='bunny' and contains(@title,'"+cor+"')]")));
        return this;
    }

    public ProdutoTela tocarBotaoAddToCart() {

        tocarElemento($(byXpath("//*[@name='save_to_cart']")));
        return this;
    }

    public ProdutoTela tocarBotaoAdicionarMaisProduto(int quantidade) throws InterruptedException {

        tocarElemento($(byXpath("//*[@class='plus']")), quantidade);
        return this;
    }

    public String retornaValorProduto() {
        return retornaTextoElemento($(byXpath("//*[@id='Description']/child::*[@class='roboto-thin screen768 ng-binding' and contains(text(),'$')]")));
    }
}
