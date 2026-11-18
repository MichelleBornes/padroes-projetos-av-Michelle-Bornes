package config;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class Configuracao {

    private static Configuracao config;
    private String regiao;
    private double orcamentoMaximo;

    private Configuracao(){
        carregarConfiguracoes();
    }

    public static synchronized Configuracao getInstancia(){
        if(config == null){
            config = new Configuracao(); 
        }

        return config;
    }

    private void carregarConfiguracoes(){
        Properties propriedades = new Properties();

        try (FileInputStream arquivo = new FileInputStream("config.properties")) {
            propriedades.load(arquivo);

            regiao = propriedades.getProperty("regiao");
            orcamentoMaximo = Double.parseDouble(propriedades.getProperty("orcamento.maximo"));
        } 
        catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
        
    }

    public String getRegiao(){
        return regiao;
    }

    public double getOrcamentoMaximo(){
        return orcamentoMaximo;
    }
}
