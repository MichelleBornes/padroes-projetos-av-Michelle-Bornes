package fatura;

public class EntregaExpressa extends ServicoAdicional {

    public EntregaExpressa(Servico servico) {
        super(servico);
    }

    @Override
    protected String descricaoAdicional() {
        return "Entrega expressa";
    }

    @Override
    protected double custoAdicional() {
        return 450.00;
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao()
            + "\n  - Entrega expressa";
    }

    @Override
    public double getCusto() {
        return servico.getCusto() + custoAdicional();
    }
}
