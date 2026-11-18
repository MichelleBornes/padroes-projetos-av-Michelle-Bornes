package aws;
import java.util.concurrent.atomic.AtomicInteger;

import recursos.FilaMensagens;

public class FilaMensagensAWS implements FilaMensagens{

    private static final AtomicInteger contador = new AtomicInteger(0);

    private final String id;

    public FilaMensagensAWS(){
        this.id = "sqs-" + contador.incrementAndGet();
    }
    
    @Override
    public String filaId() {
        return id;
    }

    @Override
    public double custoFixo() {
        return 0.40;
    }
    
}
