import ed.ListaLigadaSimples;
import ed.No;
import org.json.JSONArray;
import org.json.JSONObject;
import ponto.PontoGr;
import ponto.Ponto;
import reta.RetaGr;
import triangulo.TrianguloGr;
import retangulo.RetanguloGr;
import circulo.CirculoGr;
import figura.FiguraGr;
import java.awt.Color;
import java.text.DecimalFormat;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Escreva uma descrição da classe JsonWriter aqui.
 * classe escreve um arquivo Json
 * 
 * @author João Pedro de Souza Oliveira
 * @version (um número da versão ou uma data)
 */
public class JsonWriter
{
    /**
     * Construtor para objetos da classe JsonWriter
     */
    ListaLigadaSimples lPonto;
    ListaLigadaSimples lReta;
    ListaLigadaSimples lRetangulo;
    ListaLigadaSimples lCirculo;
    ListaLigadaSimples lTriangulo;
    ListaLigadaSimples lFigura;
    int larg, alt;

    JSONObject desenho = new JSONObject();
    JSONObject root = new JSONObject();
    JSONArray pontoArray = new JSONArray();
    JSONArray retaArray = new JSONArray();
    JSONArray trianguloArray = new JSONArray();
    JSONArray retanguloArray = new JSONArray();
    JSONArray circuloArray = new JSONArray();
    JSONArray mandalaArray = new JSONArray();

    public JsonWriter(int larg,int alt,ListaLigadaSimples lPonto,ListaLigadaSimples lReta,ListaLigadaSimples lRetangulo, ListaLigadaSimples lCirculo,ListaLigadaSimples lTriangulo,ListaLigadaSimples lFigura)
    {
        setLarg(larg);
        setAlt(alt);
        setLPonto(lPonto);
        setLReta(lReta);
        setLRetangulo(lRetangulo);
        setLTriangulo(lTriangulo);
        setLCirculo(lCirculo);
        setLFigura(lFigura);

        salvarPonto(getLPonto());
        salvarReta(getLReta());
        salvarTriangulo(getLTriangulo());
        salvarRetangulo(getLRetangulo());
        salvarCirculo(getLCirculo());
        salvarFigura(getLFigura());

        desenho.put("ponto", pontoArray);
        desenho.put("reta", retaArray);
        desenho.put("triangulo", trianguloArray);
        desenho.put("retangulo", retanguloArray);

        desenho.put("circulo", circuloArray);
        desenho.put("mandala", mandalaArray);

        root.put("desenho", desenho);
        String nomeArqAux = JOptionPane.showInputDialog("Nome do arquivo:");
        String nomeArq = nomeArqAux + ".json";

        try (FileWriter file = new FileWriter(nomeArq)) {
            file.write(root.toString(4)); // O argumento 4 indica a quantidade de espaços de recuo para a formatação
            JOptionPane.showMessageDialog(null,"Arquivo JSON criado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAlt(int alt)
    {
        this.alt = alt;
    }

    public int getAlt()
    {
        return alt;
    }

    public void setLarg(int larg)
    {
        this.larg = larg;
    }

    public int getLarg()
    {
        return larg;
    }

    public void setLPonto(ListaLigadaSimples lPonto)
    {
        this.lPonto = lPonto;
    }

    public void setLReta(ListaLigadaSimples lReta)
    {
        this.lReta = lReta;
    }

    public void setLRetangulo(ListaLigadaSimples lRetangulo)
    {
        this.lRetangulo = lRetangulo;
    }

    public void setLTriangulo(ListaLigadaSimples lTriangulo)
    {
        this.lTriangulo = lTriangulo;
    }

    public void setLCirculo(ListaLigadaSimples lCirculo)
    {
        this.lCirculo = lCirculo;
    }

    public void setLFigura(ListaLigadaSimples lFigura)
    {
        this.lFigura = lFigura;
    }

    public ListaLigadaSimples getLPonto()
    {
        return lPonto;
    }

    public ListaLigadaSimples getLReta()
    {
        return lReta;
    }

    public ListaLigadaSimples getLRetangulo()
    {
        return this.lRetangulo;
    }

    public ListaLigadaSimples getLTriangulo()
    {
        return this.lTriangulo;
    }

    public ListaLigadaSimples getLCirculo()
    {
        return this.lCirculo;
    }

    public ListaLigadaSimples getLFigura()
    {
        return this.lFigura;
    }

    public void salvarPonto(ListaLigadaSimples lPonto)
    {
        //variavel que percorre a lista ligada
        No atual;
        double diametro;
        int id = 1;
        double x,y;
        int r,g,b;

        DecimalFormat df = new DecimalFormat("#.00000");
        

        //varrer a lista dos pontos e jogar no seu array correspondente 

        //verifica se a lista é vazia
        if(lPonto.getInicio() != null)
        {
            //ponteiro que anda pela lista
            atual = lPonto.getInicio();
            do
            {
                JSONObject ponto = new JSONObject();
                JSONObject cor = new JSONObject();

                PontoGr aux =(PontoGr)atual.getConteudo();
                diametro = aux.getDiametro();

                x = aux.getX();
                y = aux.getY();

                r = aux.getCorPto().getRed();
                g = aux.getCorPto().getGreen();
                b = aux.getCorPto().getBlue();
                //int larg2 = Double.parseDouble(getLarg());
                double x5 = getLarg();
                double conta = x/x5;
                ponto.put("id","ponto_" + id);
                ponto.put("esp",diametro);
                ponto.put("x",x/getLarg());
                ponto.put("y",y/getAlt());

                cor.put("r", r);
                cor.put("g", g);
                cor.put("b", b);

                ponto.put("cor", cor);

                pontoArray.put(ponto);
                atual = atual.getProximo();
                id++;
            }while(atual != null);
        }
    }

    public void salvarReta(ListaLigadaSimples lReta)
    {
        //varrer cada uma das listas e jogar no seu array
        No atual;
        int id = 1;

        double esp;
        double x1,y1,x2,y2;
        int r,g,b;

        if(lReta.getInicio() != null)
        {
            //ponteiro que anda pela lista

            atual = lReta.getInicio();
            do
            {
                JSONObject reta = new JSONObject();
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();
                JSONObject cor = new JSONObject();

                RetaGr aux =(RetaGr)atual.getConteudo();

                esp = aux.getEspReta();
                reta.put("esp",esp);
                reta.put("id", "reta_" + id);

                x1 = aux.p1.getX();
                y1 = aux.p1.getY();
                p1.put("x", x1/getLarg());
                p1.put("y", y1/getAlt());

                x2 = aux.p2.getX();
                y2 = aux.p2.getY();
                p2.put("x", x2/getLarg());
                p2.put("y", y2/getAlt());

                reta.put("p1", p1);
                reta.put("p2", p2);

                r = aux.getCorReta().getRed();
                g = aux.getCorReta().getGreen();
                b = aux.getCorReta().getBlue();

                cor.put("r", r);
                cor.put("g", g);
                cor.put("b", b);

                reta.put("cor", cor);
                retaArray.put(reta);
                atual = atual.getProximo();
                id++;
            }while(atual != null);
        }
    }

    public void salvarRetangulo(ListaLigadaSimples lRetangulo)
    {
        //varrer cada uma das listas e jogar no seu array
        No atual;
        int id = 1;
        int esp;

        double diametro;
        double x1,y1,x2,y2;
        int r,g,b;

        if(lRetangulo.getInicio() != null)
        {
            //ponteiro que anda pela lista
            atual = lRetangulo.getInicio();
            do
            {
                JSONObject retangulo = new JSONObject();
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();
                JSONObject cor = new JSONObject();

                RetanguloGr aux =(RetanguloGr)atual.getConteudo();

                esp = aux.getEsp();
                retangulo.put("esp",esp);
                retangulo.put("id","retangulo_" + id);

                x1 = aux.p1.getX();
                y1 = aux.p1.getY();
                p1.put("x", x1/getLarg());
                p1.put("y", y1/getAlt());

                x2 = aux.p4.getX();
                y2 = aux.p4.getY();
                p2.put("x", x2/getLarg());
                p2.put("y", y2/getAlt());

                retangulo.put("p1", p1);
                retangulo.put("p2", p2);

                r = aux.getCorAtual().getRed();
                g = aux.getCorAtual().getGreen();
                b = aux.getCorAtual().getBlue();

                cor.put("r", r);
                cor.put("g", g);
                cor.put("b", b);

                retangulo.put("cor", cor);
                retanguloArray.put(retangulo);
                atual = atual.getProximo();
                id++;
            }while(atual != null);
        }
    }

    public void salvarTriangulo(ListaLigadaSimples lTriangulo)
    {
        //varrer cada uma das listas e jogar no seu array
        No atual;
        int esp;
        int id = 1;

        double diametro;
        double x1,y1,x2,y2,x3,y3;
        int r,g,b;

        if(lTriangulo.getInicio() != null)
        {
            //ponteiro que anda pela lista
            atual = lTriangulo.getInicio();
            do
            {
                JSONObject triangulo = new JSONObject();
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();
                JSONObject p3 = new JSONObject();
                JSONObject cor = new JSONObject();

                TrianguloGr aux =(TrianguloGr)atual.getConteudo();
                esp = aux.getEsp();
                triangulo.put("esp",esp);
                triangulo.put("id","triangulo_"+ id);

                x1 = aux.p1.getX();
                y1 = aux.p1.getY();
                p1.put("x", x1/getLarg());
                p1.put("y", y1/getAlt());

                x2 = aux.p2.getX();
                y2 = aux.p2.getY();
                p2.put("x", x2/getLarg());
                p2.put("y", y2/getAlt());

                x3 = aux.p3.getX();
                y3 = aux.p3.getY();
                p3.put("x", x3/getLarg());
                p3.put("y", y3/getAlt());

                triangulo.put("p1", p1);
                triangulo.put("p2", p2);
                triangulo.put("p3", p3);

                r = aux.getCorAtual().getRed();
                g = aux.getCorAtual().getGreen();
                b = aux.getCorAtual().getBlue();

                cor.put("r", r);
                cor.put("g", g);
                cor.put("b", b);

                triangulo.put("cor", cor);
                trianguloArray.put(triangulo);
                atual = atual.getProximo();
                id++;
            }while(atual != null);
        }
    }

    public void salvarCirculo(ListaLigadaSimples lCirculo)
    {
        //varrer cada uma das listas e jogar no seu array
        No atual;
        int esp;
        int id = 1;

        double diametro;
        double x1,y1;
        double raio;
        int r,g,b;

        if(lCirculo.getInicio() != null)
        {
            //ponteiro que anda pela lista
            atual = lCirculo.getInicio();
            do
            {
                JSONObject circulo = new JSONObject();
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();
                JSONObject cor = new JSONObject();

                CirculoGr aux =(CirculoGr)atual.getConteudo();
                esp = aux.getEspCirculo();
                circulo.put("esp",esp);
                circulo.put("id","circulo_"+id);

                x1 = aux.p1.getX();
                y1 = aux.p1.getY();
                p1.put("x", x1/getLarg());
                p1.put("y", y1/getAlt());
                circulo.put("centro", p1);

                raio = aux.getRaio()/getLarg();
                circulo.put("raio",raio);

                r = aux.getCorCirculo().getRed();
                g = aux.getCorCirculo().getGreen();
                b = aux.getCorCirculo().getBlue();

                cor.put("r", r);
                cor.put("g", g);
                cor.put("b", b);

                circulo.put("cor", cor);
                circuloArray.put(circulo);
                atual = atual.getProximo();
            }while(atual != null);
        }
    }

    public void salvarFigura(ListaLigadaSimples lFigura)
    {
        //varrer cada uma das listas e jogar no seu array

        //varrer cada uma das listas e jogar no seu array
        No atual;
        int esp;
        int id = 1;

        double diametro;
        double x1,y1,x2,y2;
        double raio;
        int r,g,b,r2,g2,b2;

        if(lFigura.getInicio() != null)
        {
            //ponteiro que anda pela lista
            atual = lFigura.getInicio();
            do
            {
                JSONObject mandala = new JSONObject();
                JSONObject p1 = new JSONObject();
                JSONObject p2 = new JSONObject();
                JSONObject cor = new JSONObject();
                JSONObject cor2 = new JSONObject();

                FiguraGr aux =(FiguraGr)atual.getConteudo();
                esp = aux.getEsp();
                mandala.put("esp",esp);
                mandala.put("id","mandala_"+id);

                x1 = aux.p1.getX();
                y1 = aux.p1.getY();
                p1.put("x", x1/getLarg());
                p1.put("y", y1/getAlt());
                mandala.put("p1", p1);

                x2 = aux.p2.getX();
                y2 = aux.p2.getY();
                p2.put("x", x2/getLarg());
                p2.put("y", y2/getAlt());
                mandala.put("p2", p2);

                r = aux.getCor1().getRed();
                g = aux.getCor1().getGreen();
                b = aux.getCor1().getBlue();

                r2 = aux.getCor2().getRed();
                g2 = aux.getCor2().getGreen();
                b2 = aux.getCor2().getBlue();

                cor.put("r", r);
                cor.put("g", g);
                cor.put("b", b);

                cor2.put("r", r2);
                cor2.put("g", g2);
                cor2.put("b", b2);

                mandala.put("cor1", cor);
                mandala.put("cor2", cor2);
                mandalaArray.put(mandala);
                atual = atual.getProximo();
            }while(atual != null);
        }

    }
}
