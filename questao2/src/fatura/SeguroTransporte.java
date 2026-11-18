package fatura;

public class SeguroTransporte extends ServicoAdicional {

    public SeguroTransporte(Servico servico) {
        super(servico);
    }

    @Override
    protected String descricaoAdicional() {
        return "Seguro de transporte";
    }

    @Override
    protected double custoAdicional() {
        return getValorVendaBase() * 0.015;
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao()
            + "\n  - Seguro de transporte";
    }

    @Override
    public double getCusto() {
        return servico.getCusto() + custoAdicional();
    }
}
