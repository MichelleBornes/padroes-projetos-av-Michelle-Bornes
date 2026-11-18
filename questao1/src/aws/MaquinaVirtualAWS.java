package aws;
import java.util.concurrent.atomic.AtomicInteger;

import recursos.MaquinaVirtual;

public class MaquinaVirtualAWS implements MaquinaVirtual{

    private static final AtomicInteger contador = new AtomicInteger(0);

    private final String id;

    public MaquinaVirtualAWS(){
        this.id =  "i-" + contador.incrementAndGet();
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public double custoHora() {
        return 0.10;
    }
    
}
