package fatura;

import java.util.ArrayList;
import java.util.List;

public abstract class ServicoAdicional implements Servico {

    protected final Servico servico;

    protected ServicoAdicional(Servico servico) {

        if (servico == null) {
            throw new IllegalArgumentException(
                "Servico base nao pode ser nulo."
            );
        }

        this.servico = servico;
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao();
    }

    @Override
    public double getCusto() {
        return servico.getCusto();
    }

    @Override
    public double getValorVendaBase() {
        return servico.getValorVendaBase();
    }

    protected abstract String descricaoAdicional();

    protected abstract double custoAdicional();

    @Override
    public List<ItemFatura> getItens() {

        List<ItemFatura> itens =
            new ArrayList<>(servico.getItens());

        itens.add(
            new ItemFatura(
                descricaoAdicional(),
                custoAdicional()
            )
        );

        return itens;
    }
}