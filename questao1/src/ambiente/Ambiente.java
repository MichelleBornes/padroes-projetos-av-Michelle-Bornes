package ambiente;

import java.util.List;

import config.Configuracao;

import java.util.ArrayList;

import recursos.Armazenamento;
import recursos.FilaMensagens;
import recursos.MaquinaVirtual;

public class Ambiente {
    private String nome;
    private String provedor;
    private String regiao;
    private int qtdVMs;
    private List<MaquinaVirtual> maquinasVirtuais = new ArrayList<>();
    private Armazenamento armazenamento;
    private FilaMensagens filaMensagens;
    private int qtdGB;
    private double custoMensal;


    public Ambiente(String nome, String provedor, int qtdVMs, int qtdGB){
        this.nome = nome;
        this.provedor = provedor;
        this.qtdVMs = qtdVMs;
        this.qtdGB = qtdGB;
        this.regiao = Configuracao.getInstancia().getRegiao();
    }

    public String getNome(){
        return nome;
    }

    public int getQtdVMs(){
        return qtdVMs;
    }

    public int getQtdGB(){
        return qtdGB;
    }

    public String getProvedor(){
        return provedor;
    }

    public String getRegiao(){
        return regiao;
    }

    public Armazenamento getArmazenamento(){
        return armazenamento;
    }

    public FilaMensagens getFilaMensagens(){
        return filaMensagens;
    }

    public double getCustoMensal(){
        return custoMensal;
    }

    public List<MaquinaVirtual> getMaquinasVirtuais(){
        return maquinasVirtuais;
    }

    public void adicionarMaquinaVirtual(MaquinaVirtual maquinaVirtual){
        maquinasVirtuais.add(maquinaVirtual);
    }

    public void adicionarArmazenamento(Armazenamento armazenamento){
        this.armazenamento = armazenamento;
    }

    public void adicionarFilaMensagens(FilaMensagens filaMensagens){
        this.filaMensagens = filaMensagens;
    }

    public void calcularCustoMensal() {
        double custo = 0;

        for (MaquinaVirtual maquina : maquinasVirtuais){
            custo += maquina.custoHora();
        }

        custo *= 730;

        custoMensal = custo + (qtdGB * armazenamento.custoGB()) + filaMensagens.custoFixo();
    }
}
