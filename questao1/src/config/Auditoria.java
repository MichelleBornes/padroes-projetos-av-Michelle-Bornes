
package config;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Auditoria {

    private static Auditoria auditoria;
    private List<String> registros = new ArrayList<>();

    private Auditoria() {

    }

    public static synchronized Auditoria getInstancia() {
        if (auditoria == null) {
            auditoria = new Auditoria();
        }

        return auditoria;
    }

    public synchronized void registrar(String nomeAmbiente, String provedor, String resultado) {

        String registro = LocalDateTime.now()
                + " | Ambiente: " + nomeAmbiente
                + " | Provedor: " + provedor
                + " | Resultado: " + resultado;

        registros.add(registro);
    }

    public synchronized void imprimirHistorico() {

        System.out.println("\n========== HISTÓRICO DE AUDITORIA ==========");

        for (String registro : registros) {
            System.out.println(registro);
        }

        System.out.println("============================================");
    }
}
