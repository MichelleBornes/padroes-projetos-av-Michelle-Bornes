
import leilao.Auditor;
import leilao.Lote;
import leilao.Participante;
import leilao.PainelLeiloeiro;
import comissao.ComissaoArte;
import comissao.ComissaoEletronicos;
import comissao.ComissaoVeiculos;
import fatura.CertificadoAutenticidade;
import fatura.EmbalagemEspecial;
import fatura.EntregaExpressa;
import fatura.Fatura;
import fatura.SeguroTransporte;
import fatura.Servico;
import fatura.Venda;

public class Main {
    public static void main(String[] args) {

        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");

        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        Lote quadro = new Lote(
            "Noite Estrelada", "Arte",
            1000.00, 1500.00,
            new ComissaoArte()
        );

        Lote carro = new Lote(
            "Carro antigo", "Veiculos",
            20000.00, 25000.00,
            new ComissaoVeiculos()
        );

        for (Lote lote : new Lote[]{quadro, carro}) {
            lote.adicionarInteressado(ana);
            lote.adicionarInteressado(bruno);
            lote.adicionarInteressado(carla);
            lote.adicionarInteressado(painel);
            lote.adicionarInteressado(auditor);
        }

        System.out.println("\n========== LEILAO: LOTE DE ARTE ==========");

        quadro.receberLance(ana, 1100.00);
        quadro.receberLance(bruno, 1120.00);

        quadro.removerInteressado(carla);
        System.out.println("Carla deixou de acompanhar o lote de arte.");

        quadro.receberLance(carla, 1300.00);

        quadro.trocarRegraComissao(new ComissaoEletronicos());

        quadro.receberLance(bruno, 1600.00);

        boolean quadroVendido = quadro.encerrar();

        System.out.println("\n========== LEILAO: LOTE DE VEICULO ==========");

        carro.receberLance(bruno, 21000.00);
        carro.receberLance(ana, 22000.00);

        boolean carroVendido = carro.encerrar();

        if (quadroVendido) {

            Servico venda = new Venda(
                quadro.getNome(),
                quadro.getLanceAtual()
            );

            venda = new SeguroTransporte(venda);
            venda = new CertificadoAutenticidade(venda);
            venda = new EmbalagemEspecial(venda);
            venda = new SeguroTransporte(venda);
            venda = new EntregaExpressa(venda);

            new Fatura(venda).imprimir();

            double comissao = quadro.calcularComissao();

            System.out.println("\n========== ACERTO DO VENDEDOR ==========");

            System.out.printf(
                "Valor de venda: R$ %.2f%n",
                quadro.getLanceAtual()
            );

            System.out.printf(
                "Comissao cobrada (Eletronicos): R$ %.2f%n",
                comissao
            );

            System.out.printf(
                "Valor liquido ao vendedor: R$ %.2f%n",
                quadro.getLanceAtual() - comissao
            );
        }

        if (carroVendido) {
            double comissao = carro.calcularComissao();

            System.out.printf(
                "Acerto do vendedor do carro: R$ %.2f%n",
                carro.getLanceAtual() - comissao
            );

        } else {
            System.out.println(
                "Nao ha fatura nem acerto: o lote de veiculo nao foi vendido."
            );
        }

        auditor.imprimirRegistros();
    }
}
