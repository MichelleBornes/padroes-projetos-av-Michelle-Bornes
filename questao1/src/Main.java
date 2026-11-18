import ambiente.Ambiente;
import ambiente.MontadorAmbiente;
import config.Auditoria;
import fabrica.AWSFactory;
import fabrica.AzureFactory;
import fabrica.ProvedorCloudFactory;

public class Main {

    public static void main(String[] args) {

        System.out.println("Orçamento máximo: R$ " + config.Configuracao.getInstancia().getOrcamentoMaximo());

        ProvedorCloudFactory fabricaAws = new AWSFactory();
        ProvedorCloudFactory fabricaAzure = new AzureFactory();

        MontadorAmbiente montadorAws = new MontadorAmbiente(fabricaAws);
        MontadorAmbiente montadorAzure = new MontadorAmbiente(fabricaAzure);

        Ambiente amb1 = new Ambiente("Desenvolvimento", "AWS", 2, 100);
        Ambiente amb2 = new Ambiente("Testes", "AWS", 1, 50);
        Ambiente amb3 = new Ambiente("Homologação", "Azure", 2, 200);
        Ambiente amb4 = new Ambiente("Produção", "AWS", 15, 100);

        Thread t1 = new Thread(() -> montadorAws.montar(amb1));
        Thread t2 = new Thread(() -> montadorAzure.montar(amb3));

        System.out.println("\nIniciando provisionamentos simultâneos...\n");

        t1.start();
        t2.start();

        montadorAws.montar(amb2);
        montadorAws.montar(amb4);

        try {
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Execução interrompida.");
            return;
        }

        System.out.println("\nTodos os provisionamentos foram finalizados.");

        Auditoria.getInstancia().imprimirHistorico();
    }
}
