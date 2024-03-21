import ed.ListaLigadaSimples;
import ed.No;
import java.awt.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONException;

import ponto.PontoGr;
import ponto.Ponto;
import reta.RetaGr;
import triangulo.TrianguloGr;
import retangulo.RetanguloGr;
import circulo.CirculoGr;
import figura.FiguraGr;

import java.io.FileReader;
import java.io.IOException;

/**
 * Escreva uma descrição da classe JsonReader aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class JsonReader
{
    // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
    ListaLigadaSimples lPonto;
    ListaLigadaSimples lReta;
    ListaLigadaSimples lRetangulo;
    ListaLigadaSimples lCirculo;
    ListaLigadaSimples lTriangulo;
    ListaLigadaSimples lFigura;

    /**
     * Construtor para objetos da classe JsonReader
     */
    public JsonReader(int larg,int alt,String nomeArq,ListaLigadaSimples lPonto,ListaLigadaSimples lReta,ListaLigadaSimples lRetangulo, ListaLigadaSimples lCirculo,ListaLigadaSimples lTriangulo,ListaLigadaSimples lFigura)
    {
        lerArquivo(larg,alt,nomeArq);
    }

    private void lerArquivo(int larg,int alt,String nomeArq)
    {
        try {
            FileReader reader = new FileReader(nomeArq);
            double esp;
            double x;
            double y;
            int r;
            int g;
            int b;
            Color color;
            Color color2;
            double p1x;
            double p1y; 
            double p2x;
            double p2y; 
            double p3x;
            double p3y; 
            JSONObject cor;
            JSONObject cor2;

            lPonto = new ListaLigadaSimples();
            lReta = new ListaLigadaSimples();
            lRetangulo = new ListaLigadaSimples();
            lCirculo = new ListaLigadaSimples();
            lTriangulo = new ListaLigadaSimples();
            lFigura = new ListaLigadaSimples();

            // Criar um objeto JSON a partir do arquivo
            JSONObject jsonObject = new JSONObject(new JSONTokener(reader));

            // Acessar a chave "desenho"
            JSONObject desenho = jsonObject.getJSONObject("desenho");

            if(!desenho.isNull("ponto")){
                JSONArray pontoArray = desenho.getJSONArray("ponto");
                JSONObject ponto;// = pontoArray.getJSONObject(0);
                //esp = ponto.getDouble("esp");
                //x = ponto.getDouble("x");
                //y = ponto.getDouble("y");

                //cor = ponto.getJSONObject("cor");
                //r = cor.getInt("r");
                // g = cor.getInt("g");
                // b = cor.getInt("b");
                //color = new Color(r,g,b);

                PontoGr pontoNovo;// = new PontoGr((int)x,(int)y,color,(int)esp);

                //lPonto.inserirInicio(pontoNovo);
                // Processar os PONTOS
                for (int i = 0; i  < pontoArray.length(); i++) {
                    ponto = pontoArray.getJSONObject(i);
                    esp = ponto.getDouble("esp");
                    x = larg * ponto.getDouble("x");
                    y = alt * ponto.getDouble("y");
                    // Acessar a chave "cor" para obter os valores de R, G e B
                    cor = ponto.getJSONObject("cor");
                    r = cor.getInt("r");
                    g = cor.getInt("g");
                    b = cor.getInt("b");

                    color = new Color(r,g,b);

                    pontoNovo = new PontoGr((int)x,(int)y,color,"",(int)esp);
                    lPonto.inserirFim(pontoNovo);
                    // Mostrar os valores lidos
                    //System.out.println("Ponto " + id + ": Espessura=" + esp + ", X=" + x + ", Y=" + y + ", Cor (R, G, B)=" + r + ", " + g + ", " + b);
                }

            }      

            if(!desenho.isNull("reta")){
                JSONArray retaArray = desenho.getJSONArray("reta");
                JSONObject reta ;// = retaArray.getJSONObject(1);
                // esp = reta.getDouble("esp");
                JSONObject p1; //= reta.getJSONObject("p1");
                JSONObject p2; //= reta.getJSONObject("p2");

                // p1x = p1.getDouble("x");
                // p1y = p1.getDouble("y");
                // p2x = p2.getDouble("x");
                // p2y = p2.getDouble("y");

                // cor = reta.getJSONObject("cor");
                // r = cor.getInt("r");
                // g = cor.getInt("g");
                // b = cor.getInt("b");
                // color = new Color(r,g,b);

                RetaGr retaNova; //= new RetaGr((int)p1x, (int)p1y, (int)p2x, (int)p2y, color, (int)esp);
                int testedada = retaArray.length();
                //lReta.inserirInicio(retaNova);
                for (int i = 0; i < retaArray.length(); i++) {
                    reta = retaArray.getJSONObject(i);
                    esp = reta.getDouble("esp");

                    // Acessar os pontos p1 e p2
                    p1 = reta.getJSONObject("p1");
                    p2 = reta.getJSONObject("p2");

                    p1x = larg * p1.getDouble("x");
                    p1y = alt * p1.getDouble("y");
                    p2x = larg* p2.getDouble("x");
                    p2y = alt * p2.getDouble("y");

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    cor = reta.getJSONObject("cor");
                    r = cor.getInt("r");
                    g = cor.getInt("g");
                    b = cor.getInt("b");
                    color = new Color(r,g,b);

                    retaNova = new RetaGr((int)p1x, (int)p1y, (int)p2x, (int)p2y, color, (int)esp);
                    lReta.inserirFim(retaNova);
                }
            }

            if(!desenho.isNull("retangulo")){
                JSONArray retanguloArray = desenho.getJSONArray("retangulo");
                JSONObject retangulo;// = retanguloArray.getJSONObject(1);
                //esp = retangulo.getDouble("esp");
                JSONObject p1;//= retangulo.getJSONObject("p1");
                JSONObject p2; // retangulo.getJSONObject("p2");

                //p1x = p1.getDouble("x");
                // p1y = p1.getDouble("y");
                //p2x = p2.getDouble("x");
                //p2y = p2.getDouble("y");

                //cor = retangulo.getJSONObject("cor");
                //r = cor.getInt("r");
                //g = cor.getInt("g");
                //b = cor.getInt("b");
                //color = new Color(r,g,b);

                RetanguloGr retanguloNovo; //= new RetanguloGr((int)p1x, (int)p1y, (int)p2x, (int)p2y, color,"", (int)esp);

                //lReta.inserirInicio(retanguloNovo);
                for (int i = 0; i  < retanguloArray.length(); i++) {
                    retangulo = retanguloArray.getJSONObject(i);

                    esp = retangulo.getDouble("esp");

                    // Acessar os pontos p1 e p2
                    p1 = retangulo.getJSONObject("p1");
                    p2 = retangulo.getJSONObject("p2");

                    p1x = larg * p1.getDouble("x");
                    p1y = alt * p1.getDouble("y");
                    p2x = larg * p2.getDouble("x");
                    p2y = alt * p2.getDouble("y");

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    cor = retangulo.getJSONObject("cor");
                    r = cor.getInt("r");
                    g = cor.getInt("g");
                    b = cor.getInt("b");
                    color = new Color(r,g,b);

                    retanguloNovo = new RetanguloGr((int)p1x, (int)p1y, (int)p2x, (int)p2y, color,"", (int)esp);
                    lRetangulo.inserirFim(retanguloNovo);
                }
            }

            if(!desenho.isNull("triangulo")){
                JSONArray trianguloArray = desenho.getJSONArray("triangulo");
                JSONObject triangulo; //= trianguloArray.getJSONObject(1);
                //esp = triangulo.getDouble("esp");
                JSONObject p1;// =// triangulo.getJSONObject("p1");
                JSONObject p2; //=// triangulo.getJSONObject("p2");
                JSONObject p3; //=// triangulo.getJSONObject("p3");

                //p1x = p1.getDouble("x");
                //p1y = p1.getDouble("y");
                //p2x = p2.getDouble("x");
                //p2y = p2.getDouble("y");
                //p3x = p3.getDouble("x");
                //p3y = p3.getDouble("y");

                //cor = triangulo.getJSONObject("cor");
                //r = cor.getInt("r");
                //g = cor.getInt("g");
                //b = cor.getInt("b");
                //color = new Color(r,g,b);

                TrianguloGr trianguloNovo; //= new TrianguloGr((int)p1x, (int)p1y, (int)p2x, (int)p2y,(int)p3x,(int)p3y, color,"", (int)esp);

                //lReta.inserirInicio(trianguloNovo);
                int teste = trianguloArray.length();
                for (int i = 0; i  < trianguloArray.length(); i++) {
                    triangulo = trianguloArray.getJSONObject(i);

                    esp = triangulo.getDouble("esp");

                    // Acessar os pontos p1 e p2
                    p1 = triangulo.getJSONObject("p1");
                    p2 = triangulo.getJSONObject("p2");
                    p3 = triangulo.getJSONObject("p3");

                    p1x = larg * p1.getDouble("x");
                    p1y = alt * p1.getDouble("y");
                    p2x = larg * p2.getDouble("x");
                    p2y = alt * p2.getDouble("y");
                    p3x = larg * p3.getDouble("x");
                    p3y = alt * p3.getDouble("y");

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    cor = triangulo.getJSONObject("cor");
                    r = cor.getInt("r");
                    g = cor.getInt("g");
                    b = cor.getInt("b");
                    color = new Color(r,g,b);

                    trianguloNovo = new TrianguloGr((int)p1x, (int)p1y, (int)p2x, (int)p2y,(int)p3x,(int)p3y, color,"", (int)esp);
                    lTriangulo.inserirFim(trianguloNovo);
                }
            }

            if(!desenho.isNull("mandala")){
                JSONArray mandalaArray = desenho.getJSONArray("mandala");
                JSONObject mandala;// = mandalaArray.getJSONObject(1);
                //esp = mandala.getDouble("esp");
                JSONObject p1;// = mandala.getJSONObject("p1");
                JSONObject p2;//= mandala.getJSONObject("p2");

                //p1x = p1.getDouble("x");
                //p1y = p1.getDouble("y");
                //p2x = p2.getDouble("x");
                //p2y = p2.getDouble("y");

                //cor = mandala.getJSONObject("cor1");
                //r = cor.getInt("r");
                //g = cor.getInt("g");
                //b = cor.getInt("b");
                //color = new Color(r,g,b);

                //cor2 = mandala.getJSONObject("cor2");
                //r = cor.getInt("r");
                //g = cor.getInt("g");
                //b = cor.getInt("b");
                //color2 = new Color(r,g,b);
                //public FiguraGr(int x1, int y1, int x2, int y2, Color cor1, Color cor2, String nome, int esp

                FiguraGr mandalaNovo; //= new FiguraGr((int)p1x, (int)p1y, (int)p2x, (int)p2y, color,color2,"", (int)esp);
                //int teste = mandalaArray.length();
                //lReta.inserirInicio(mandalaNovo);
                for (int i = 0; i  < mandalaArray.length(); i++) {
                    mandala = mandalaArray.getJSONObject(i);

                    esp = mandala.getDouble("esp");

                    // Acessar os pontos p1 e p2
                    p1 = mandala.getJSONObject("p1");
                    p2 = mandala.getJSONObject("p2");

                    p1x = larg * p1.getDouble("x");
                    p1y = alt * p1.getDouble("y");
                    p2x = larg * p2.getDouble("x");
                    p2y = alt * p2.getDouble("y");

                    // Acessar a chave "cor" para obter os valores de R, G e B
                    cor = mandala.getJSONObject("cor1");
                    r = cor.getInt("r");
                    g = cor.getInt("g");
                    b = cor.getInt("b");
                    color = new Color(r,g,b);

                    cor2 = mandala.getJSONObject("cor2");
                    r = cor2.getInt("r");
                    g = cor2.getInt("g");
                    b = cor2.getInt("b");
                    color2 = new Color(r,g,b);

                    mandalaNovo = new FiguraGr((int)p1x, (int)p1y, (int)p2x, (int)p2y, color,color2,"", (int)esp);
                    lFigura.inserirFim(mandalaNovo);
                }
            }

            if(!desenho.isNull("circulo")){
                JSONArray circuloArray = desenho.getJSONArray("circulo");
                JSONObject circulo; //= circuloArray.getJSONObject(1);

                // cor = circulo.getJSONObject("cor");
                //r = cor.getInt("r");
                //g = cor.getInt("g");
                //b = cor.getInt("b");
                //color = new Color(r,g,b);

                JSONObject centro ;//= circulo.getJSONObject("centro");
                //esp = circulo.getDouble("esp");
                //x = centro.getDouble("x");
                //y = centro.getDouble("y");
                double raio;// = circulo.getDouble("raio");
                int x1;//; =(int)(x + raio);

                //public CirculoGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){

                CirculoGr circuloNovo;// = new CirculoGr((int)x,(int)y,(int)x1,(int)y,color,"",(int)esp);
                //lCirculo.inserirInicio(circuloNovo);
                // Processar os PONTOS
                for (int i = 0; i  < circuloArray.length(); i++) {
                    circulo = circuloArray.getJSONObject(i);

                    cor = circulo.getJSONObject("cor");
                    r = cor.getInt("r");
                    g = cor.getInt("g");
                    b = cor.getInt("b");
                    color = new Color(r,g,b);

                    centro = circulo.getJSONObject("centro");
                    esp = circulo.getDouble("esp");
                    x = larg *centro.getDouble("x");
                    y = alt *  centro.getDouble("y");
                    raio = larg*circulo.getDouble("raio");
                    x1 =(int)(x + raio);
                    int y1 =(int)(y + raio);

                    //public CirculoGr(int x1, int y1, int x2, int y2, Color cor, String nome, int esp){

                    circuloNovo = new CirculoGr((int)x,(int)y,(int)x1,(int)y1,color,"",(int)esp);
                    lCirculo.inserirInicio(circuloNovo);

                }
            }      

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
