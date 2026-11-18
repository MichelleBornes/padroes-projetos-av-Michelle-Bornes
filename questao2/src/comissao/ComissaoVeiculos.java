package comissao;

public class ComissaoVeiculos implements RegraComissao {

    @Override
    public double calcular(double valorVenda) {
        return Math.min(valorVenda * 0.08, 5000.00);
    }

    @Override
    public String getNome() {
        return "Veiculos (8%, limitada a R$ 5.000,00)";
    }
}
