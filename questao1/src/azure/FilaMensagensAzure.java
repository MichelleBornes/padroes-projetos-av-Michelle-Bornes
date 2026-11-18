package azure;

import recursos.FilaMensagens;
import java.util.concurrent.atomic.AtomicInteger;

public class FilaMensagensAzure implements FilaMensagens{

    private static final AtomicInteger contador = new AtomicInteger(0);
    private final String id;

    public FilaMensagensAzure(){
        this.id = "sb-" + contador.incrementAndGet();
    }

    @Override
    public String filaId() {
        return id;
    }

    @Override
    public double custoFixo() {
        return 0.50;
    }
    
}
