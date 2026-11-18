package fatura;

import java.util.List;

public interface Servico {

    String getDescricao();

    double getCusto();

    double getValorVendaBase();

    List<ItemFatura> getItens();
}