
package leilao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Lance {

    private final Participante participante;
    private final double valor;
    private final LocalDateTime dataHora;

    public Lance(Participante participante, double valor) {
        this.participante = participante;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public Participante getParticipante() {
        return participante;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return participante.getNome()
            + " - R$ " + String.format("%.2f", valor)
            + " em "
            + dataHora.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            );
    }
}
