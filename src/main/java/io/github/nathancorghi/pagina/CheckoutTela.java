package io.github.nathancorghi.pagina;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutTela extends PaginaBase {

    public String retornaValorTotal() {

        return retornaTextoElemento($(byXpath("//*[@class='roboto-medium totalValue ng-binding']")));
    }
}
