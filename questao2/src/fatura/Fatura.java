package fatura;

public class Fatura {

    private final Servico venda;

    public Fatura(Servico venda) {
        this.venda = venda;
    }

    public void imprimir() {

        System.out.println(
            "\n========== FATURA DO COMPRADOR =========="
        );

        for (ItemFatura item : venda.getItens()) {

            System.out.printf(
                "%-35s R$ %10.2f%n",
                item.getDescricao(),
                item.getCusto()
            );
        }

        System.out.printf(
            "%-35s R$ %10.2f%n",
            "TOTAL A PAGAR",
            venda.getCusto()
        );
    }
}
