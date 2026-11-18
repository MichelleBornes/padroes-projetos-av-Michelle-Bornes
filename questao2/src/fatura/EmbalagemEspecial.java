package fatura;

public class EmbalagemEspecial extends ServicoAdicional {

    public EmbalagemEspecial(Servico servico) {
        super(servico);
    }

    @Override
    protected String descricaoAdicional() {
        return "Embalagem especial";
    }

    @Override
    protected double custoAdicional() {
        return 180.00;
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao()
            + "\n  - Embalagem especial";
    }

    @Override
    public double getCusto() {
        return servico.getCusto() + custoAdicional();
    }
}
