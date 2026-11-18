package fabrica;

import aws.ArmazenamentoAWS;
import recursos.Armazenamento;
import recursos.FilaMensagens;
import aws.FilaMensagensAWS;
import recursos.MaquinaVirtual;
import aws.MaquinaVirtualAWS;


public class AWSFactory implements ProvedorCloudFactory{


    @Override
    public MaquinaVirtual criarMaquinaVirtual() {
        return new MaquinaVirtualAWS();
    }

    @Override
    public Armazenamento criarArmazenamento() {
        return new ArmazenamentoAWS();
    }

    @Override
    public FilaMensagens criarFilaMensagens() {
        return new FilaMensagensAWS();
    }

    @Override
    public String getNomeProvedor() {
        return "AWS";
    }

    @Override
    public double getCustoHoraVM() {
        return 0.10;
    }

    @Override
    public double getCustoGB() {
       return 0.023;
    }

    @Override
    public double getCustoFixoFila() {
        return 0.40;
    }

}
