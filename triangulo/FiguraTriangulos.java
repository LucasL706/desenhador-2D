package triangulo;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Escreva uma descri��o da classe FiguraTriangulos aqui.
 * 
 * @author Jo�o Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito 
 * @version (um n�mero da vers�o ou uma data)
 */
public class FiguraTriangulos
{
    // vari�veis de inst�ncia - substitua o exemplo abaixo pelo seu pr�prio
    private int x;

    /**
     * Desenha uma reta de acordo com os pontos p1, p2 e p3
     *
     * @param g biblioteca para desenhar o primitivo grafico
     * @param x1 coordenada x de p1
     * @param y1 coordenada y de p1
     * @param x2 coordenada x de p2
     * @param y2 coordenada y de p2
     * @param x3 coordenada x de p3
     * @param y3 coordenada y de p3
     * @param nome nome da reta
     * @param esp espessura da reta
     * @param cor cor da reta
     */
    public static void desenharTriangulo(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, String nome, int esp, Color cor){       
        TrianguloGr r = new TrianguloGr(x1, y1, x2, y2, x3, y3, cor, nome, esp);
        r.desenharTriangulo(g);
    }

}
