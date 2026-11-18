package fabrica;

import recursos.Armazenamento;
import recursos.FilaMensagens;
import recursos.MaquinaVirtual;

public interface ProvedorCloudFactory{

    MaquinaVirtual criarMaquinaVirtual();
    Armazenamento criarArmazenamento();
    FilaMensagens criarFilaMensagens();

    String getNomeProvedor();
    double getCustoHoraVM();
    double getCustoGB();
    double getCustoFixoFila();
}
