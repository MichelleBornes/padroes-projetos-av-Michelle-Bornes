package azure;
import java.util.concurrent.atomic.AtomicInteger;

import recursos.Armazenamento;

public class ArmazenamentoAzure implements Armazenamento{

    private static final AtomicInteger contadorId = new AtomicInteger(0);
    private final String id;

    public ArmazenamentoAzure(){
        this.id = "blob://" + contadorId.incrementAndGet();
    }
    
    @Override
    public String armazenamentoId() {
        return id;
    }

    @Override
    public double custoGB() {
       return 0.02;
    }
    
}
