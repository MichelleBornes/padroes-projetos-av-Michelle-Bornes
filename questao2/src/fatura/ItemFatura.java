package fatura;

public class ItemFatura {

    private final String descricao;
    private final double custo;

    public ItemFatura(String descricao, double custo) {
        this.descricao = descricao;
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getCusto() {
        return custo;
    }
}
