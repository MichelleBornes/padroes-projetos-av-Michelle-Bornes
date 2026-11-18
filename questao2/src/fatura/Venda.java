package fatura;

import java.util.Collections;
import java.util.List;

public class Venda implements Servico {

    private final String nomeLote;
    private final double valorVenda;

    public Venda(String nomeLote, double valorVenda) {
        this.nomeLote = nomeLote;
        this.valorVenda = valorVenda;
    }

    @Override
    public String getDescricao() {
        return "Lote: " + nomeLote;
    }

    @Override
    public double getCusto() {
        return valorVenda;
    }

    @Override
    public double getValorVendaBase() {
        return valorVenda;
    }

    @Override
    public List<ItemFatura> getItens() {
        return Collections.singletonList(
            new ItemFatura("Venda do lote: " + nomeLote, valorVenda)
        );
    }

    public String getNomeLote() {
        return nomeLote;
    }

    public double getValorVenda() {
        return valorVenda;
    }
}