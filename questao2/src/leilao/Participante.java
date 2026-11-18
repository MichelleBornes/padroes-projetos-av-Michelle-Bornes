package leilao;

public class Participante implements Interessado {

    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.printf(
            "[Notificacao para %s] Lote '%s': novo lance de R$ %.2f%n",
            nome,
            lote.getNome(),
            lance.getValor()
        );
    }
}
