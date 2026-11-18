package leilao;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Auditor implements Interessado {

    private final List<String> registros = new ArrayList<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {

        String registro =
            lance.getDataHora().format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            )
            + " | Lote: " + lote.getNome()
            + " | Participante: "
            + lance.getParticipante().getNome()
            + " | Valor: R$ "
            + String.format("%.2f", lance.getValor());

        registros.add(registro);
    }

    public void imprimirRegistros() {

        System.out.println("\n========== REGISTROS DO AUDITOR ==========");

        if (registros.isEmpty()) {
            System.out.println("Nenhum lance registrado.");
        }

        for (String registro : registros) {
            System.out.println(registro);
        }
    }
}
