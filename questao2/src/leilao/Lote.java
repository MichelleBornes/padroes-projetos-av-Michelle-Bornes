package leilao;

import comissao.RegraComissao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lote {

    private final String nome;
    private final String categoria;
    private final double precoInicial;
    private final double precoReserva;

    private double lanceAtual;

    private final List<Lance> lances = new ArrayList<>();
    private final List<Interessado> interessados = new ArrayList<>();

    private boolean aberto = true;
    private boolean vendido;

    private RegraComissao regraComissao;

    public Lote(String nome, String categoria,
                double precoInicial, double precoReserva,
                RegraComissao regraComissao) {

        if (precoInicial < 0 || precoReserva < 0) {
            throw new IllegalArgumentException(
                "Precos nao podem ser negativos."
            );
        }

        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.lanceAtual = precoInicial;
        this.regraComissao = regraComissao;
    }

    public void adicionarInteressado(Interessado interessado) {

        if (interessado != null &&
            !interessados.contains(interessado)) {

            interessados.add(interessado);
        }
    }

    public void removerInteressado(Interessado interessado) {
        interessados.remove(interessado);
    }

    public boolean receberLance(Participante participante, double valor) {

        if (!aberto) {
            System.out.println(
                "Lance recusado: o lote '" + nome + "' ja foi encerrado."
            );
            return false;
        }

        double minimo = lanceAtual * 1.05;

        if (valor < minimo) {

            System.out.printf(
                "Lance recusado para '%s': o minimo e R$ %.2f "
                + "(5%% acima do lance atual).%n",
                nome,
                minimo
            );

            return false;
        }

        Lance lance = new Lance(participante, valor);

        lances.add(lance);
        lanceAtual = valor;

        notificarInteressados(lance, participante);

        return true;
    }

    private void notificarInteressados(Lance lance, Participante autor) {

        for (Interessado interessado :
             new ArrayList<>(interessados)) {

            if (interessado != autor) {
                interessado.atualizar(this, lance);
            }
        }
    }

    public boolean encerrar() {

        if (!aberto) {
            System.out.println(
                "O lote '" + nome + "' ja estava encerrado."
            );
            return vendido;
        }

        aberto = false;

        vendido = !lances.isEmpty()
            && lanceAtual >= precoReserva;

        System.out.println(
            vendido
                ? String.format(
                    "Lote '%s' VENDIDO por R$ %.2f.",
                    nome, lanceAtual
                )
                : "Lote '" + nome
                    + "' NAO VENDIDO (preco de reserva nao atingido)."
        );

        return vendido;
    }

    public double calcularComissao() {

        if (!vendido) {
            return 0;
        }

        return regraComissao.calcular(lanceAtual);
    }

    public void trocarRegraComissao(RegraComissao novaRegra) {

        if (!aberto) {
            throw new IllegalStateException(
                "Nao e possivel trocar a comissao de um lote encerrado."
            );
        }

        if (novaRegra == null) {
            throw new IllegalArgumentException(
                "A regra de comissao nao pode ser nula."
            );
        }

        this.regraComissao = novaRegra;

        System.out.println(
            "Regra de comissao do lote '" + nome
            + "' alterada para " + novaRegra.getNome() + "."
        );
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public double getPrecoReserva() {
        return precoReserva;
    }

    public double getLanceAtual() {
        return lanceAtual;
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isVendido() {
        return vendido;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }
}
