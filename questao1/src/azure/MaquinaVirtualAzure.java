package azure;
import java.util.concurrent.atomic.AtomicInteger;

import recursos.MaquinaVirtual;

public class MaquinaVirtualAzure implements MaquinaVirtual{

    private static final AtomicInteger contador = new AtomicInteger(0);
    private final String id;

    public MaquinaVirtualAzure(){
        this.id = "vm-" + contador.incrementAndGet();
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public double custoHora() {
        return 0.12;
    }

}
