package fatura;

public class CertificadoAutenticidade extends ServicoAdicional {

    public CertificadoAutenticidade(Servico servico) {
        super(servico);
    }

    @Override
    protected String descricaoAdicional() {
        return "Certificado de autenticidade";
    }

    @Override
    protected double custoAdicional() {
        return 300.00;
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao()
            + "\n  - Certificado de autenticidade";
    }

    @Override
    public double getCusto() {
        return servico.getCusto() + custoAdicional();
    }
}
