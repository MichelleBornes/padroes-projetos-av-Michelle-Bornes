package fabrica;

import azure.ArmazenamentoAzure;
import recursos.Armazenamento;
import recursos.FilaMensagens;
import azure.FilaMensagensAzure;
import recursos.MaquinaVirtual;
import azure.MaquinaVirtualAzure;


public class AzureFactory implements ProvedorCloudFactory{

    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new MaquinaVirtualAzure();
    }

    @Override
    public Armazenamento criarArmazenamento() {
        return new ArmazenamentoAzure();
    }

    @Override
    public FilaMensagens criarFilaMensagens() {
        return new FilaMensagensAzure();
    }

    @Override
    public String getNomeProvedor() {
        return "Azure";
    }

    @Override
    public double getCustoHoraVM() {
        return 0.12;
    }

    @Override
    public double getCustoGB() {
        return 0.02;
    }

    @Override
    public double getCustoFixoFila() {
        return 0.50;
    }
    
}
