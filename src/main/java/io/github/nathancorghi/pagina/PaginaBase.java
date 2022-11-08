package io.github.nathancorghi.pagina;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PaginaBase {

    protected void escreverTexto(SelenideElement elemento, String texto) {

        elemento.sendKeys(texto);
    }

    protected void tocarElemento(SelenideElement elemento) {

        elemento.click();
    }

    protected void tocarElemento(SelenideElement elemento, int quantidade) throws InterruptedException {

        for (int i=0; i< quantidade; i++) {
            elemento.click();
            Thread.sleep(1);
        }
    }

    protected String retornaTextoElemento(WebElement elemento) {

        return elemento.getText();
    }

    protected void rolarTelaAteElemento(SelenideElement elemento) {

        elemento.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
    }

    protected void aguardaElelentoEstarVisivel(SelenideElement elemento, int tempoSegundos) {

        elemento.shouldBe(Condition.visible, Duration.ofSeconds(tempoSegundos));
    }
}
