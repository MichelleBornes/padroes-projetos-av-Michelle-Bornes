package aws;
import java.util.concurrent.atomic.AtomicInteger;

import recursos.Armazenamento;

public class ArmazenamentoAWS implements Armazenamento {

    private static final AtomicInteger contador = new AtomicInteger(0);
    private final String id;

    public ArmazenamentoAWS(){
        this.id = "s3://" + contador.incrementAndGet();
    }
    
    @Override
    public String armazenamentoId() {
        return id;
    }

    @Override
    public double custoGB() {
        return 0.023;
    }
    
}
