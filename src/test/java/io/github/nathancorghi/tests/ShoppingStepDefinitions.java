package io.github.nathancorghi.tests;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

import org.junit.Assert;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.github.nathancorghi.contantes.Cores;
import io.github.nathancorghi.contantes.Mensagem;
import io.github.nathancorghi.contantes.Url;
import io.github.nathancorghi.mysql.repository.MassasRepository;
import io.github.nathancorghi.pagina.CarrinhoTela;
import io.github.nathancorghi.pagina.CheckoutTela;
import io.github.nathancorghi.pagina.HomeTela;
import io.github.nathancorghi.pagina.ProdutoTela;
import io.github.nathancorghi.utils.EnumUtils;

public class ShoppingStepDefinitions {

    private final HomeTela home = new HomeTela();
    private final ProdutoTela produto = new ProdutoTela();
    private final CarrinhoTela carrinho = new CarrinhoTela();
    private final CheckoutTela checkout = new CheckoutTela();
    private final MassasRepository massas = new MassasRepository();
    private final Integer quantidade = new Random().nextInt(5);
    private String valorProduto;
    private String cor;

    @Before
    public void setUp() {

        webdriver().driver().clearCookies();
        open(Url.URL_BASE);
    }

    @After
    public void tearDown() {

        closeWebDriver();
    }

    @Dado("que estou na secao Special Offer")
    public void queEstouNaSecaoSpecialOffer() {

        home
            .aguardarCarregamentoTela()
            .tocarBotaoSpecialOffer();
    }

    @Dado("que tenho um produto pesquisado")
    public void queTenhoUmProdutoPesquisado() {

        home
            .aguardarCarregamentoTela()
            .tocarBotaoLupa()
            .aguardarCarregamentoCampoPesquisa()
            .preencherCampoPesquisa(massas.returnProducts().getNameProduct());
    }

    @Quando("eu acessar a oferta")
    public void euAcessarOferta() {

        home
            .tocarBotaoSeeOffer();
    }

    @Quando("eu alterar a cor do produto conforme cor informada no banco de dados")
    public void euAlterarCorDoProdutoConformeCorInformadaNoBancoDeDados() {

        produto
                .aguardarProdutoEstarVisivelEmTela()
                .selecionarCorProduto(massas.returnProducts().getColor());
    }

    @Quando("eu adicionar o produto no carrinho")
    public void euAdicionarProdutoNoCarrinho() {

        produto.tocarBotaoAddToCart();
    }

    @Quando("eu selecionar o produto pesquisado")
    public void euSelecionarProdutoPesquisado() {

        home
            .selecionarProduto(massas.returnProducts().getNameProduct());
    }

    @Quando("eu alterar a cor do produto diferente da cor informada no banco de dados")
    public void euAlterarCorDoProdutoDiferenteDaCorInformadaNoBancoDeDados() {

        cor = new EnumUtils<>(Cores.class).retornaEnumAleatorio().toString();
        while (massas.returnProducts().getNameProduct().equals(cor)) {
            cor = new EnumUtils<>(Cores.class).retornaEnumAleatorio().toString();
        }

        produto
            .aguardarProdutoEstarVisivelEmTela()
            .selecionarCorProduto(cor);
    }

    @Quando("eu alterar a quantidade de produtos")
    public void euAlterarQuantidadeDeProdutos() throws InterruptedException {

        valorProduto = produto.retornaValorProduto();
        produto
            .tocarBotaoAdicionarMaisProduto(quantidade);
    }

    @Quando("eu acessar a pagina de checkout")
    public void euAcessarPaginaDeCheckout() {

        home
            .tocarBotaoCarrinho();
        carrinho
            .aguardarCarregamentoTela()
            .tocarBotaoCheckout();
    }

    @Quando("eu remover o produto do carrinho")
    public void euRemoverProdutoDoCarrinho() {

        home
            .tocarBotaoCarrinho();
        carrinho
            .aguardarCarregamentoTela()
            .tocarBotaoRemove();
    }

    @Entao("as especificacoes do produto devem estar de acordo com o banco de dados")
    public void asEspecificacoesDoProdutoDevemEstarDeAcordoComBancoDeDados() {

        produto.aguardarProdutoEstarVisivelEmTela();

        Assert.assertEquals(massas.returnProducts().getNameProduct(), produto.retornaInformacoesProduto().get(0));
        Assert.assertEquals(massas.returnProducts().getCustomization(), produto.retornaInformacoesProduto().get(1));
        Assert.assertEquals(massas.returnProducts().getDisplay(), produto.retornaInformacoesProduto().get(2));
        Assert.assertEquals(massas.returnProducts().getDisplayResolution(), produto.retornaInformacoesProduto().get(3));
        Assert.assertEquals(massas.returnProducts().getDisplaySize(), produto.retornaInformacoesProduto().get(4));
        Assert.assertEquals(massas.returnProducts().getMemory(), produto.retornaInformacoesProduto().get(5));
        Assert.assertEquals(massas.returnProducts().getOperatingSystem(), produto.retornaInformacoesProduto().get(6));
        Assert.assertEquals(massas.returnProducts().getProcessor(), produto.retornaInformacoesProduto().get(7));
        Assert.assertEquals(massas.returnProducts().getTouchScreen(), produto.retornaInformacoesProduto().get(8));
        Assert.assertEquals(massas.returnProducts().getWeight(), produto.retornaInformacoesProduto().get(9));
    }

    @Entao("o produto sera adicionado no carrinho com a cor selecionada")
    public void oProdutoSeraAdicionadoNoCarrinhoComCorSelecionada() {

        home.tocarBotaoCarrinho();
        carrinho.aguardarCarregamentoTela();

        Assert.assertTrue(carrinho.retornaVisibilidadeProduto(massas.returnProducts().getNameProduct()));
    }

    @Entao("a soma dos precos devem corresponder ao total apresentado em tela")
    public void aSomaDosPrecosDevemCorresponderAoTotalApresentadoEmTela() {

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.UK);
        numberFormat.setMaximumFractionDigits(3);

        BigDecimal total = new BigDecimal(valorProduto.substring(1)).multiply(new BigDecimal(quantidade+1));

        Assert.assertEquals(checkout.retornaValorTotal().substring(1), numberFormat.format(total));
    }

    @Entao("a cor devera ser atualizada no banco de dados")
    public void aCorDeveraSerAtualizadaNoBancoDeDados() {

        massas.updateProductColor(massas.returnProducts().getNameProduct(), cor);

        Assert.assertEquals(cor, massas.returnProducts().getColor());
    }

    @Entao("o carrinho de compras devera estar vazio")
    public void oCarrinhoDeComprasDeveraEstarVazio() {

        Assert.assertEquals(Mensagem.MENSAGEM_CARRINHO_VAZIO, carrinho.retornaTextoCarrinhoVazio());
    }
}
