
package ambiente;

import config.Configuracao;
import config.Auditoria;
import fabrica.ProvedorCloudFactory;
import recursos.MaquinaVirtual;

public class MontadorAmbiente {

    private ProvedorCloudFactory fabrica;

    public MontadorAmbiente(ProvedorCloudFactory fabrica) {
        this.fabrica = fabrica;
    }

    public void montar(Ambiente ambiente) {

        double custo = (ambiente.getQtdVMs() * fabrica.getCustoHoraVM()) * 730
                + (ambiente.getQtdGB() * fabrica.getCustoGB())
                + fabrica.getCustoFixoFila();

        if (custo > Configuracao.getInstancia().getOrcamentoMaximo()) {

            Auditoria.getInstancia().registrar(
                    ambiente.getNome(),
                    fabrica.getNomeProvedor(),
                    "RECUSADO - Orçamento excedido"
            );

            System.out.println("\nAmbiente recusado: " + ambiente.getNome()
                    + " | Orçamento excedido.");

            return;
        }

        for (int i = 0; i < ambiente.getQtdVMs(); i++) {
            MaquinaVirtual maquinaVirtual = fabrica.criarMaquinaVirtual();
            ambiente.adicionarMaquinaVirtual(maquinaVirtual);
        }

        ambiente.adicionarArmazenamento(fabrica.criarArmazenamento());
        ambiente.adicionarFilaMensagens(fabrica.criarFilaMensagens());

        ambiente.calcularCustoMensal();

        Auditoria.getInstancia().registrar(
                ambiente.getNome(),
                fabrica.getNomeProvedor(),
                "ACEITO"
        );

        imprimirRelatorio(ambiente);
    }

    
    private void imprimirRelatorio(Ambiente ambiente) {

        synchronized (System.out) {

            System.out.println("\n========== AMBIENTE ==========");
            System.out.println("Ambiente: " + ambiente.getNome());
            System.out.println("Provedor: " + fabrica.getNomeProvedor());
            System.out.println("Região: " + ambiente.getRegiao());

            System.out.print("IDs das VMs: ");

            for (MaquinaVirtual vm : ambiente.getMaquinasVirtuais()) {
                System.out.print(vm.id() + " ");
            }

            System.out.println();

            System.out.println("Armazenamento: "
                    + ambiente.getArmazenamento().armazenamentoId());

            System.out.println("Fila: "
                    + ambiente.getFilaMensagens().filaId());

            System.out.printf("Custo mensal: R$ %.2f%n",
                    ambiente.getCustoMensal());

            System.out.println("==============================");
        }
    }

}
