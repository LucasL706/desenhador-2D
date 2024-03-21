import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import ponto.FiguraPontos;
import reta.FiguraRetas;
import retangulo.FiguraRetangulos;
import triangulo.FiguraTriangulos;
import circulo.FiguraCirculos;
import figura.FiguraFiguras;

import ponto.PontoGr;
import reta.RetaGr;
import triangulo.TrianguloGr;
import retangulo.RetanguloGr;
import circulo.CirculoGr;
import figura.FiguraGr;

import ed.ListaLigadaSimples;
import ed.No;
/**
 * Cria desenhos de acordo com o tipo e eventos do mouse
 * 
 * @author Julio Arakaki 
 * @version 20220815
 */
public class PainelDesenho extends JPanel implements MouseListener, MouseMotionListener {

    JLabel msg;           // Label para mensagens
    TipoPrimitivo tipo; // Tipo do primitivo
    Color corAtual,cor1,cor2, corBackgroung;       // Cor atual do primitivo
    int esp;              // Diametro do ponto
    TipoOperacoes ultimaAlteracao;

    // Para ponto
    int x, y, xR, yR,xT,yT,xE,yE;

    // Para reta, triangulo, circulo e retangulo
    int x1, y1, x2, y2, x3, y3;

    int xtR;
    int ytR;

    //para Rotaçao

    //coordenadas que vão rodar
    int x1r;
    int x2r;
    int x3r;
    int y1r;
    int y2r;
    int y3r;

    int x1e;
    int x2e;
    int x3e;
    int y1e;
    int y2e;
    int y3e;

    Object menorTriangulo;

    double grau;
    double escalaX,escalaY;
    //x's e y's novos após a rotação
    double xNovo,yNovo;

    // selecionar primeiro click do mouse
    boolean primeiraVez = true;
    boolean segundaVez = true;

    //armazenadores das figuras
    ListaLigadaSimples lPonto = new ListaLigadaSimples();
    ListaLigadaSimples lReta = new ListaLigadaSimples();
    ListaLigadaSimples lRetangulo = new ListaLigadaSimples();
    ListaLigadaSimples lCirculo = new ListaLigadaSimples();
    ListaLigadaSimples lTriangulo = new ListaLigadaSimples();
    ListaLigadaSimples lFigura = new ListaLigadaSimples();
    ListaLigadaSimples lTotal = new ListaLigadaSimples();
    ListaLigadaSimples lRemocoes = new ListaLigadaSimples();
    ListaLigadaSimples lAlteracoes = new ListaLigadaSimples();
    ListaLigadaSimples lRotacao  = new ListaLigadaSimples();
    /**
     * Constroi o painel de desenho
     *
     * @param msg mensagem a ser escrita no rodape do painel
     * @param tipo tipo atual do primitivo
     * @param corAtual cor atual do primitivo
     * @param esp espessura atual do primitivo
     */
    public PainelDesenho(JLabel msg, TipoPrimitivo tipo, Color corAtual, int esp){
        setTipo(tipo);
        setMsg(msg);
        setCorAtual(corAtual);
        setEsp(esp);
        // Adiciona "ouvidor" de eventos de mouse
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);

    }

    /**
     * Altera o tipo atual do primitivo
     *
     * @param tipo tipo do primitivo
     */
    public void setTipo(TipoPrimitivo tipo){
        this.tipo = tipo;
    }

    public void setGrau(double grau)
    {
        this.grau = grau;
    }

    public double getGrau()
    {
        return grau;
    }

    public void setEscalaX(double escalaX)
    {
        this.escalaX = escalaX; 
    }

    public double getEscalaX()
    {
        return escalaX;
    }

    public void setEscalaY(double escalaY)
    {
        this.escalaY = escalaY; 
    }

    public double getEscalaY()
    {
        return escalaY;
    }

    /**
     * Retorna o tipo do primitivo
     *
     * @return tipo do primitivo
     */
    public TipoPrimitivo getTipo(){
        return this.tipo;
    }

    /**
     * Altera a espessura do primitivo
     *
     * @param esp espessura do primitivo
     */
    public void setEsp(int esp){
        this.esp = esp;
    }

    /**
     * Retorna a espessura do primitivo
     *
     * @return espessura do primitivo
     */
    public int getEsp(){
        return this.esp;
    }

    /**
     * Altera a cor atual do primitivo
     *
     * @param corAtual cor atual do primitivo
     */
    public void setCorAtual(Color corAtual){
        this.corAtual = corAtual;
    }

    /**
     * Altera a cor da variavel background
     *
     * @param corAtual cor1 da variavel background
     */
    public void setCorBackground(Color backgroung){
        this.corBackgroung = backgroung;
    }

    /**
     * Altera a cor1 do primitivo figura
     *
     * @param corAtual cor1 do primitivo figura
     */
    public void setCor1(Color corAtual){
        this.cor1 = corAtual;
    }

    /**
     * Altera a cor2 do primitivo figura
     *
     * @param corAtual cor2 do primitivo figura
     */
    public void setCor2(Color corAtual){
        this.cor2 = corAtual;
    }

    /**
     * Atualiza a vari
     */
    public void setAlt(TipoOperacoes alteracao){
        this.ultimaAlteracao = alteracao;
    }

    public TipoOperacoes getAlt(){
        return this.ultimaAlteracao;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCorAtual(){
        return this.corAtual;
    }

    /**
     * retorna a cor atual do background
     *
     * @return cor atual do background
     */
    public Color getCorBackground(){
        return this.corBackgroung;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCor2(){
        return this.cor2;
    }

    /**
     * retorna a cor atual do primitivo
     *
     * @return cor atual do primitivo
     */
    public Color getCor1(){
        return this.cor1;
    }

    /**
     * Altera a msg a ser apresentada no rodape
     *
     * @param msg mensagem a ser apresentada
     */
    public void setMsg(JLabel msg){
        this.msg = msg;
    }

    /**
     * Retorna a mensagem
     *
     * @return mensagem as ser apresentada no rodape
     */
    public JLabel getMsg(){
        return this.msg;
    }

    /**
     * Metodo chamado quando o paint eh acionado
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void paintComponent(Graphics g) {   
        desenharPrimitivos(g);
    }

    public void setMenorTriangulo(Object menorTriangulo)
    {
        this.menorTriangulo = menorTriangulo;
    }

    public Object getMenorTriangulo()
    {
        return menorTriangulo;
    }

    /**
     * Evento: pressionar do mouse
     *
     * @param e dados do evento
     */
    public void mousePressed(MouseEvent e) { 
        Graphics g = getGraphics();  
        if (tipo == TipoPrimitivo.PONTO){
            x = e.getX();
            y = e.getY();
            paint(g);

        }  else if (tipo == TipoPrimitivo.REMOVER){
            xR = e.getX();
            yR = e.getY();
            remover();
        } else if (tipo == TipoPrimitivo.RETA){
            if (primeiraVez == true) {
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            } else {
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                primeiraVez = true;
                paint(g);
            }
        } else if (tipo == TipoPrimitivo.RETANGULO){
            if(primeiraVez == true){
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            } else {
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                primeiraVez = true;
                paint(g);
            }
        } else if (tipo == TipoPrimitivo.FIGURA){
            if(primeiraVez == true){
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            } else {
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                primeiraVez = true;
                paint(g);
            } 
        }else if (tipo == TipoPrimitivo.TRIANGULO){
            if (primeiraVez == true){
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            } else if (primeiraVez == false && segundaVez == true){
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                segundaVez = false;
            } else if (segundaVez == false){
                x3 = (int)e.getX();
                y3 = (int)e.getY();
                primeiraVez = true;
                segundaVez = true;
                paint(g);
            }
        } else if (tipo == TipoPrimitivo.CIRCULO){
            if(primeiraVez == true){
                x1 = (int)e.getX();
                y1 = (int)e.getY();
                primeiraVez = false;
            } else {
                x2 = (int)e.getX();
                y2 = (int)e.getY();
                primeiraVez = true;
                paint(g);
            }
        }else if(tipo == TipoPrimitivo.ROTACAO)
        {
            xR = (int)e.getX();
            yR = (int)e.getY();

            No menorTriangulo = menorTriangulo();
            TrianguloGr triangulo = null;
            if(menorTriangulo != null)
            {
                triangulo = (TrianguloGr)menorTriangulo.getConteudo(); 
            }
            if(primeiraVez == true && triangulo != null)
            {
                String entrada =JOptionPane.showInputDialog("Digite o angulo desejado:");
                double grau = Double.parseDouble(entrada);
                //pegar valor em graus
                setGrau(grau);

                //pegar o valor de cada um dos pontos do triangulo
                //pontos que vao rodar
                x1r = (int)triangulo.p1.getX();
                y1r = (int)triangulo.p1.getY();

                x2r = (int)triangulo.p2.getX();
                y2r = (int)triangulo.p2.getY();

                x3r = (int)triangulo.p3.getX();
                y3r = (int)triangulo.p3.getY();
                primeiraVez = false;
                lTriangulo.removerElem(menorTriangulo);
                lTotal.removerElem(menorTriangulo);
                lRemocoes.inserirFim(triangulo);
                lRotacao.inserirFim(triangulo);
                //lAlteracoes.inserirFim("REMOVEU TRIANGULO");
            }
            else if(primeiraVez == false)
            {
                //ocorre a rotação
                double radianos = Math.toRadians(getGrau());
                double cosseno = Math.cos(radianos);
                double seno = Math.sin(radianos);

                calcNovosPontos(x1r,y1r,xR,yR,cosseno,seno);

                int x1 = (int)getNovoPontoX();
                int y1 = (int)getNovoPontoY();

                calcNovosPontos(x2r,y2r,xR,yR,cosseno,seno);

                int x2 = (int)getNovoPontoX();
                int y2 = (int)getNovoPontoY();

                calcNovosPontos(x3r,y3r,xR,yR,cosseno,seno);

                int x3 = (int)getNovoPontoX();
                int y3 = (int)getNovoPontoY();

                FiguraTriangulos.desenharTriangulo(g,x1r, y1r, x2r, y2r, x3r, y3r, "", getEsp(), getBackground());

                FiguraTriangulos.desenharTriangulo(g,x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());
                TrianguloGr novoTriangulo = new TrianguloGr(x1,y1,x2,y2,x3,y3,getCorAtual(),"",getEsp());
                lTriangulo.inserirFim(novoTriangulo);
                lTotal.inserirFim(novoTriangulo);
                //lAlteracoes.inserirFim("DESENHOU");
                lAlteracoes.inserirFim("ROTACAO");

                primeiraVez = true;
            }
        }
        else if(tipo == TipoPrimitivo.ESCALA)
        {
            xR = (int)e.getX();
            yR = (int)e.getY();

            No menorTriangulo = menorTriangulo();
            TrianguloGr triangulo = null;

            if(primeiraVez == true)
            {
                setMenorTriangulo(menorTriangulo);
            }

            if(menorTriangulo != null && primeiraVez == true)
            {
                triangulo = (TrianguloGr)menorTriangulo.getConteudo(); 

                String entrada = JOptionPane.showInputDialog("Digite a escala em X desejada:");
                String entrada2 = JOptionPane.showInputDialog("Digite a escala em Y desejada:");

                escalaX = Double.parseDouble(entrada);
                escalaY =Double.parseDouble(entrada2);
                //pegar valor da escala
                setEscalaX(escalaX);
                setEscalaY(escalaY);

                //pegar o valor de cada um dos pontos do triangulo
                //pontos que vao rodar
                x1e = (int)triangulo.p1.getX();
                y1e = (int)triangulo.p1.getY();

                x2e = (int)triangulo.p2.getX();
                y2e = (int)triangulo.p2.getY();

                x3e = (int)triangulo.p3.getX();
                y3e = (int)triangulo.p3.getY();

                lTriangulo.removerElem(menorTriangulo);
                lTotal.removerElem(menorTriangulo);
                lRemocoes.inserirFim(triangulo);
                lRotacao.inserirFim(triangulo);
                //lAlteracoes.inserirFim("REMOVEU TRIANGULO");

                primeiraVez = false;

            }
            else if(primeiraVez == false)
            {

                No aux = (No)getMenorTriangulo();
                triangulo = (TrianguloGr)aux.getConteudo();

                calcNovosPontosEscala(xR,yR,x1e,y1e,getEscalaX(),getEscalaY());

                int x1 = (int)getNovoPontoX();
                int y1 = (int)getNovoPontoY();

                calcNovosPontosEscala(xR,yR,x2e,y2e,getEscalaX(),getEscalaY());
                int x2 = (int)getNovoPontoX();
                int y2 = (int)getNovoPontoY();

                calcNovosPontosEscala(xR,yR,x3e,y3e,getEscalaX(),getEscalaY());
                int x3 = (int)getNovoPontoX();
                int y3 = (int)getNovoPontoY();

                triangulo.p2.setX(x1);
                triangulo.p2.setY(y1);

                triangulo.p1.setX(x2);
                triangulo.p1.setY(y2);

                triangulo.p3.setX(x3);
                triangulo.p3.setY(y3);

                aux.setConteudo(triangulo);
                menorTriangulo = aux;

                FiguraTriangulos.desenharTriangulo(g,x1e,y1e, x2e, y2e,x3e, y3e, "",getEsp(), getBackground());

                FiguraTriangulos.desenharTriangulo(g,x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());
                primeiraVez = true;
            }
            redesenhar();
        }     

    }           

    public void mouseReleased(MouseEvent e) { 
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Evento mouseMoved: escreve mensagem no rodape (x, y) do mouse
     *
     * @param e dados do evento do mouse
     */
    public void mouseMoved(MouseEvent e) {
        this.msg.setText("("+e.getX() + ", " + e.getY() + ") - " + getTipo());
    }

    public void calcNovosPontos(int xr,int yr,int xo,int yo,double cosseno,double seno)
    {
        double xTr;
        double yTr;

        xTr = (xr - xo) * cosseno - (yr - yo) * seno + xo;
        yTr = (xr - xo) * seno + (yr - yo) * cosseno + yo;         

        setNovoPontoX(xTr);
        setNovoPontoY(yTr);
    }

    public void calcNovosPontosEscala(int xo,int yo,int x,int y,double escalaX,double escalaY)
    {
        int xu = (int)(xo + (x-xo) * escalaX);
        int yu = (int)(yo + (y-yo) * escalaY);

        setNovoPontoX(xu);
        setNovoPontoY(yu);
    }

    public void setNovoPontoX(double xNovo)
    {
        this.xNovo = xNovo;
    }

    public void setNovoPontoY(double yNovo)
    {
        this.yNovo = yNovo;
    }

    public double getNovoPontoX()
    {
        return xNovo;
    }

    public double getNovoPontoY()
    {
        return yNovo;
    }

    /**
     * Desenha os primitivos
     *
     * @param g biblioteca para desenhar em modo grafico
     */
    public void desenharPrimitivos(Graphics g){

        if (tipo == TipoPrimitivo.PONTO){
            FiguraPontos.desenharPonto(g, x, y, "", getEsp(), getCorAtual());
            //FiguraPontos.desenharPontos(g, 50, 20);
            PontoGr ponto = new PontoGr(x,y,getCorAtual(),"",getEsp());
            lPonto.inserirFim(ponto);
            lTotal.inserirFim(ponto);
            lAlteracoes.inserirFim("DESENHOU");
        }

        if (tipo == TipoPrimitivo.RETA){
            FiguraRetas.desenharReta(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
            //FiguraRetas.desenharRetas(g, 10, 3);
            RetaGr reta = new RetaGr(x1,y1,x2,y2,getCorAtual(),"",getEsp());
            lReta.inserirFim(reta);
            lTotal.inserirFim(reta);
            lAlteracoes.inserirFim("DESENHOU");
        }

        if (tipo == TipoPrimitivo.RETANGULO)
        {
            FiguraRetangulos.desenharRetangulo(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
            RetanguloGr retangulo = new RetanguloGr(x1,y1,x2,y2,getCorAtual(),"",getEsp());
            lRetangulo.inserirFim(retangulo);
            lTotal.inserirFim(retangulo);
            lAlteracoes.inserirFim("DESENHOU");
        }

        if (tipo == TipoPrimitivo.TRIANGULO)
        {
            FiguraTriangulos.desenharTriangulo(g,x1, y1, x2, y2, x3, y3, "", getEsp(), getCorAtual());
            TrianguloGr triangulo = new TrianguloGr(x1,y1,x2,y2,x3,y3,getCorAtual(),"",getEsp());
            lTriangulo.inserirFim(triangulo);
            lTotal.inserirFim(triangulo);
            lAlteracoes.inserirFim("DESENHOU");
        }

        if (tipo==TipoPrimitivo.CIRCULO){
            FiguraCirculos.desenharCirculo(g, x1, y1, x2, y2, "", getEsp(), getCorAtual());
            CirculoGr circulo = new CirculoGr(x1,y1,x2,y2,getCorAtual(),"",getEsp());
            lCirculo.inserirFim(circulo);
            lTotal.inserirFim(circulo);
            lAlteracoes.inserirFim("DESENHOU");
        }

        if (tipo == TipoPrimitivo.FIGURA){
            FiguraFiguras.desenharFigura(g, x1, y1, x2, y2, "", getEsp(), getCor1(), getCor2());
            FiguraGr figura = new FiguraGr(x1,y1,x2,y2, getCor1(), getCor2(),"",getEsp());
            lFigura.inserirFim(figura);
            lTotal.inserirFim(figura);
            lAlteracoes.inserirFim("DESENHOU");
        }
        setAlt(TipoOperacoes.DESENHOU);
    }

    /**
     * Método redesenhar - desenha todos os primitivos que estao armazenados na estrutura de dados
     *
     */
    public void redesenhar(){
        Graphics g = getGraphics();
        No atual = lPonto.getInicio();
        //teste se a lista eh vazia
        if(!lPonto.estaVazia()){
            while(atual != null){
                PontoGr ponto = (PontoGr)atual.getConteudo();
                FiguraPontos.desenharPonto(g, (int)ponto.getX(), (int)ponto.getY(), "", ponto.getDiametro(), ponto.getCorPto());

                atual = atual.getProximo();
            }
        }

        atual = lReta.getInicio();
        if(!lReta.estaVazia()){
            while(atual != null){
                RetaGr reta = (RetaGr)atual.getConteudo();
                FiguraRetas.desenharReta(g, (int)reta.p1.getX(), (int)reta.p1.getY(), (int)reta.p2.getX(), (int)reta.p2.getY(), "", reta.getEspReta(), reta.getCorReta());
                atual = atual.getProximo();
            }
        }

        atual = lRetangulo.getInicio();
        if(!lRetangulo.estaVazia()){
            while(atual != null){
                RetanguloGr retangulo = (RetanguloGr)atual.getConteudo();
                FiguraRetangulos.desenharRetangulo(g, (int)retangulo.p1.getX(), (int)retangulo.p1.getY(), (int)retangulo.p4.getX(), (int)retangulo.p4.getY(), "", retangulo.getEsp(), retangulo.getCorAtual());
                atual = atual.getProximo();
            }
        }

        atual = lTriangulo.getInicio();
        if(!lTriangulo.estaVazia()){
            while(atual != null){
                TrianguloGr triangulo = (TrianguloGr)atual.getConteudo();
                FiguraTriangulos.desenharTriangulo(g, (int)triangulo.p1.getX(), (int)triangulo.p1.getY(), (int)triangulo.p2.getX(), (int)triangulo.p2.getY(), 
                    (int)triangulo.p3.getX(), (int)triangulo.p3.getY(), "", triangulo.getEsp(), triangulo.getCorAtual());
                atual = atual.getProximo();
            }
        }

        atual = lCirculo.getInicio();
        if(!lCirculo.estaVazia()){
            while(atual != null){
                CirculoGr circulo = (CirculoGr)atual.getConteudo();
                FiguraPontos.desenharPonto(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), "", circulo.getEspCirculo(), getBackground());
                FiguraPontos.desenharPonto(g, (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), getBackground());
                FiguraCirculos.desenharCirculo(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), circulo.getCorCirculo());
                atual = atual.getProximo();
            }
        }

        atual = lFigura.getInicio();
        if(!lFigura.estaVazia()){
            while(atual != null){
                FiguraGr figura = (FiguraGr)atual.getConteudo();
                FiguraFiguras.desenharFigura(g, (int)figura.getP1().getX(), (int)figura.getP1().getY(), (int)figura.getP2().getX(), (int)figura.getP2().getY(), "", figura.getEsp(), figura.getCor1(), figura.getCor2());
                atual = atual.getProximo();
            }
        }
    }
    
    public void redesenharComCorDeFundo(){
        Graphics g = getGraphics();
        No atual = lPonto.getInicio();
        //teste se a lista eh vazia
        if(!lPonto.estaVazia()){
            while(atual != null){
                PontoGr ponto = (PontoGr)atual.getConteudo();
                FiguraPontos.desenharPonto(g, (int)ponto.getX(), (int)ponto.getY(), "", ponto.getDiametro(), getBackground());

                atual = atual.getProximo();
            }
        }

        atual = lReta.getInicio();
        if(!lReta.estaVazia()){
            while(atual != null){
                RetaGr reta = (RetaGr)atual.getConteudo();
                FiguraRetas.desenharReta(g, (int)reta.p1.getX(), (int)reta.p1.getY(), (int)reta.p2.getX(), (int)reta.p2.getY(), "", reta.getEspReta(), getBackground());
                atual = atual.getProximo();
            }
        }

        atual = lRetangulo.getInicio();
        if(!lRetangulo.estaVazia()){
            while(atual != null){
                RetanguloGr retangulo = (RetanguloGr)atual.getConteudo();
                FiguraRetangulos.desenharRetangulo(g, (int)retangulo.p1.getX(), (int)retangulo.p1.getY(), (int)retangulo.p4.getX(), (int)retangulo.p4.getY(), "", retangulo.getEsp(), getBackground());
                atual = atual.getProximo();
            }
        }

        atual = lTriangulo.getInicio();
        if(!lTriangulo.estaVazia()){
            while(atual != null){
                TrianguloGr triangulo = (TrianguloGr)atual.getConteudo();
                FiguraTriangulos.desenharTriangulo(g, (int)triangulo.p1.getX(), (int)triangulo.p1.getY(), (int)triangulo.p2.getX(), (int)triangulo.p2.getY(), 
                    (int)triangulo.p3.getX(), (int)triangulo.p3.getY(), "", triangulo.getEsp(), getBackground());
                atual = atual.getProximo();
            }
        }

        atual = lCirculo.getInicio();
        if(!lCirculo.estaVazia()){
            while(atual != null){
                CirculoGr circulo = (CirculoGr)atual.getConteudo();
                FiguraPontos.desenharPonto(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), "", circulo.getEspCirculo(), getBackground());
                FiguraPontos.desenharPonto(g, (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), getBackground());
                FiguraCirculos.desenharCirculo(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), getBackground());
                atual = atual.getProximo();
            }
        }

        atual = lFigura.getInicio();
        if(!lFigura.estaVazia()){
            while(atual != null){
                FiguraGr figura = (FiguraGr)atual.getConteudo();
                FiguraFiguras.desenharFigura(g, (int)figura.getP1().getX(), (int)figura.getP1().getY(), (int)figura.getP2().getX(), (int)figura.getP2().getY(), "", figura.getEsp(), getBackground(), getBackground());
                atual = atual.getProximo();
            }
        }
        removerED();
    }

    /**
     * Método removerED - limpa todas as listas ligadas setando o inicio e fim para null e quantidade de nos = 0.
     *
     */
    public void removerED(){
        lPonto.resetaLista();
        lReta.resetaLista();
        lRetangulo.resetaLista();
        lTriangulo.resetaLista();
        lCirculo.resetaLista();
        lFigura.resetaLista();
        lTotal.resetaLista();
        setAlt(TipoOperacoes.LIMPOU);
    }

    /**
     * Método voltar - desfaz a ultima alteracao do usuario.
     *
     */
    public void voltar(){
        if((!(lAlteracoes.estaVazia())) && (lAlteracoes.getFim().getConteudo().equals("DESENHOU"))){
            if(!lTotal.estaVazia()){
                Graphics g = getGraphics();
                if(!lPonto.estaVazia() && lTotal.getFim().getConteudo().equals(lPonto.getFim().getConteudo())){
                    PontoGr ponto = (PontoGr)lPonto.getFim().getConteudo();
                    FiguraPontos.desenharPonto(g, (int)ponto.getX(), (int)ponto.getY(), "", ponto.getDiametro(), getBackground());
                    lTotal.removerFim(); lPonto.removerFim(); lAlteracoes.removerFim();
                }
                else if(!lReta.estaVazia() && lTotal.getFim().getConteudo().equals(lReta.getFim().getConteudo())){
                    RetaGr reta = (RetaGr)lReta.getFim().getConteudo();
                    FiguraRetas.desenharReta(g, (int)reta.p1.getX(), (int)reta.p1.getY(), (int)reta.p2.getX(), (int)reta.p2.getY(), "", reta.getEspReta(), getBackground());
                    lTotal.removerFim(); lReta.removerFim(); lAlteracoes.removerFim();
                }
                else if(!lRetangulo.estaVazia() && lTotal.getFim().getConteudo().equals(lRetangulo.getFim().getConteudo())){     
                    RetanguloGr retangulo = (RetanguloGr)lRetangulo.getFim().getConteudo();
                    FiguraRetangulos.desenharRetangulo(g, (int)retangulo.p1.getX(), (int)retangulo.p1.getY(), (int)retangulo.p4.getX(), (int)retangulo.p4.getY(), "", retangulo.getEsp(), getBackground());
                    lTotal.removerFim(); lRetangulo.removerFim(); lAlteracoes.removerFim();
                }
                else if(!lTriangulo.estaVazia() && lTotal.getFim().getConteudo().equals(lTriangulo.getFim().getConteudo())){
                    TrianguloGr triangulo = (TrianguloGr)lTriangulo.getFim().getConteudo();
                    FiguraTriangulos.desenharTriangulo(g, (int)triangulo.p1.getX(), (int)triangulo.p1.getY(), (int)triangulo.p2.getX(), (int)triangulo.p2.getY(), 
                        (int)triangulo.p3.getX(), (int)triangulo.p3.getY(), "", triangulo.getEsp(), getBackground());
                    lTotal.removerFim(); lTriangulo.removerFim(); lAlteracoes.removerFim();
                }
                else if(!lCirculo.estaVazia() && lTotal.getFim().getConteudo().equals(lCirculo.getFim().getConteudo())){
                    CirculoGr circulo = (CirculoGr)lCirculo.getFim().getConteudo();
                    FiguraCirculos.desenharCirculo(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), getBackground());
                    lTotal.removerFim(); lCirculo.removerFim(); lAlteracoes.removerFim();
                }
                else if(!lFigura.estaVazia() && lTotal.getFim().getConteudo().equals(lFigura.getFim().getConteudo())){
                    FiguraGr figura = (FiguraGr)lFigura.getFim().getConteudo();
                    FiguraFiguras.desenharFigura(g, (int)figura.getP1().getX(), (int)figura.getP1().getY(), (int)figura.getP2().getX(), (int)figura.getP2().getY(), "", figura.getEsp(), getBackground(), getBackground());
                    lTotal.removerFim(); lFigura.removerFim(); lAlteracoes.removerFim();
                }
            }
        } else if ((!(lAlteracoes.estaVazia())) && (!(lAlteracoes.getFim().getConteudo().equals("DESENHOU"))) && (!(lRemocoes.estaVazia()))){
            Graphics g = getGraphics();
            if(lAlteracoes.getFim().getConteudo().equals("REMOVEU PONTO")){
                PontoGr ponto = (PontoGr)lRemocoes.getFim().getConteudo();
                FiguraPontos.desenharPonto(g, (int)ponto.getX(), (int)ponto.getY(), "", ponto.getDiametro(), ponto.getCorNomePto());
                lPonto.inserirFim(ponto); lRemocoes.removerFim(); lAlteracoes.removerFim();
            } else if(lAlteracoes.getFim().getConteudo().equals("REMOVEU RETA")){
                RetaGr reta = (RetaGr)lRemocoes.getFim().getConteudo();
                FiguraRetas.desenharReta(g, (int)reta.p1.getX(), (int)reta.p1.getY(), (int)reta.p2.getX(), (int)reta.p2.getY(), "", reta.getEspReta(), reta.getCorReta());
                lReta.inserirFim(reta); lRemocoes.removerFim(); lAlteracoes.removerFim();
            } else if(lAlteracoes.getFim().getConteudo().equals("REMOVEU RETANGULO")){
                RetanguloGr retangulo = (RetanguloGr)lRemocoes.getFim().getConteudo();
                FiguraRetangulos.desenharRetangulo(g, (int)retangulo.p1.getX(), (int)retangulo.p1.getY(), (int)retangulo.p4.getX(), (int)retangulo.p4.getY(), "", retangulo.getEsp(), retangulo.getCorAtual());
                lRetangulo.inserirFim(retangulo); lRemocoes.removerFim(); lAlteracoes.removerFim();
            } else if(lAlteracoes.getFim().getConteudo().equals("REMOVEU TRIANGULO")){
                TrianguloGr triangulo = (TrianguloGr)lRemocoes.getFim().getConteudo();
                FiguraTriangulos.desenharTriangulo(g, (int)triangulo.p1.getX(), (int)triangulo.p1.getY(), (int)triangulo.p2.getX(), (int)triangulo.p2.getY(), 
                    (int)triangulo.p3.getX(), (int)triangulo.p3.getY(), "", triangulo.getEsp(), triangulo.getCorAtual());
                lTriangulo.inserirFim(triangulo); lRemocoes.removerFim(); lAlteracoes.removerFim();
            } else if(lAlteracoes.getFim().getConteudo().equals("REMOVEU CIRCULO")){
                CirculoGr circulo = (CirculoGr)lRemocoes.getFim().getConteudo();
                FiguraCirculos.desenharCirculo(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), circulo.getCorCirculo());
                lCirculo.inserirFim(circulo); lRemocoes.removerFim(); lAlteracoes.removerFim();
            } else if(lAlteracoes.getFim().getConteudo().equals("REMOVEU FIGURA")){
                FiguraGr figura = (FiguraGr)lRemocoes.getFim().getConteudo();
                FiguraFiguras.desenharFigura(g, (int)figura.getP1().getX(), (int)figura.getP1().getY(), (int)figura.getP2().getX(), (int)figura.getP2().getY(), "", figura.getEsp(), figura.getCor1(), figura.getCor2());
                lFigura.inserirFim(figura); lRemocoes.removerFim(); lAlteracoes.removerFim();
            }
        }
        if(lAlteracoes.getFim().getConteudo() == "ROTACAO")
        {
            Graphics g = getGraphics();
            TrianguloGr triangulo = (TrianguloGr)lRotacao.getFim().getConteudo();
            FiguraTriangulos.desenharTriangulo(g, (int)triangulo.p1.getX(), (int)triangulo.p1.getY(), (int)triangulo.p2.getX(), (int)triangulo.p2.getY(), 
                (int)triangulo.p3.getX(), (int)triangulo.p3.getY(), "", triangulo.getEsp(), triangulo.getCorAtual());
            TrianguloGr triangulo2 =(TrianguloGr)lTriangulo.getFim().getConteudo();

            FiguraTriangulos.desenharTriangulo(g, (int)triangulo2.p1.getX(), (int)triangulo2.p1.getY(), (int)triangulo2.p2.getX(), (int)triangulo2.p2.getY(), 
                (int)triangulo2.p3.getX(), (int)triangulo2.p3.getY(), "", triangulo2.getEsp(), getBackground());
            lTriangulo.removerFim();
            lTriangulo.inserirFim(triangulo);
            lTotal.inserirFim(triangulo);
            lAlteracoes.removerFim();

        }
    }

    /**
     * Método remover - remove um tipo primitivo de acordo com o click do usuario.
     *
     */
    public void remover(){
        Graphics g = getGraphics();
        mostraPontosFiguraCirculo();

        No menorPonto = menorPonto();
        No menorReta = menorReta();
        No menorRetangulo = menorRetangulo();
        No menorTriangulo = menorTriangulo();
        No menorCirculo = menorCirculo();
        No menorFigura = menorFigura();
        double[] distancias = new double[7];
        distancias[1] = distanciaMenorPonto(menorPonto);
        distancias[2] = distanciaMenorReta(menorReta);
        distancias[3] = distanciaMenorRetangulo(menorRetangulo);
        distancias[4] = distanciaMenorTriangulo(menorTriangulo);
        distancias[5] = distanciaMenorCirculo(menorCirculo);
        distancias[6] = distanciaMenorFigura(menorFigura);
        distancias[0] = distancias[1];
        for(int i = 2; i <= 6; i++){
            if(distancias[i] < distancias[0]){
                distancias[0] = distancias[i];
            }
        }
        //ponto é menor
        if((distancias[0] == distancias[1]) && (distancias[0] != 99)){
            PontoGr ponto = (PontoGr)menorPonto.getConteudo();
            FiguraPontos.desenharPonto(g, (int)ponto.getX(), (int)ponto.getY(), "", ponto.getDiametro(), getBackground());
            lPonto.removerElem(menorPonto);
            lTotal.removerElem(menorPonto);
            redesenhar();
            lRemocoes.inserirFim(ponto);
            lAlteracoes.inserirFim("REMOVEU PONTO");
        } else if((distancias[0] == distancias[2]) && (distancias[0] != 99)){ //reta é menor
            RetaGr reta = (RetaGr)menorReta.getConteudo();
            FiguraRetas.desenharReta(g, (int)reta.p1.getX(), (int)reta.p1.getY(), (int)reta.p2.getX(), (int)reta.p2.getY(), "", reta.getEspReta(), getBackground());
            lReta.removerElem(menorReta);
            lTotal.removerElem(menorReta);
            redesenhar();
            lRemocoes.inserirFim(reta);
            lAlteracoes.inserirFim("REMOVEU RETA");
        } else if((distancias[0] == distancias[3]) && (distancias[0] != 99)){ //retangulo é menor
            RetanguloGr retangulo = (RetanguloGr)menorRetangulo().getConteudo();
            FiguraRetangulos.desenharRetangulo(g, (int)retangulo.p1.getX(), (int)retangulo.p1.getY(), (int)retangulo.p4.getX(), (int)retangulo.p4.getY(), "", retangulo.getEsp(), getBackground());
            lRetangulo.removerElem(menorRetangulo);
            lTotal.removerElem(menorRetangulo);
            redesenhar();
            lRemocoes.inserirFim(retangulo);
            lAlteracoes.inserirFim("REMOVEU RETANGULO");
        } else if ((distancias[0] == distancias[4]) && (distancias[0] != 99)){ //triangulo é menor
            TrianguloGr triangulo = (TrianguloGr)menorTriangulo.getConteudo();
            FiguraTriangulos.desenharTriangulo(g, (int)triangulo.p1.getX(), (int)triangulo.p1.getY(), (int)triangulo.p2.getX(), (int)triangulo.p2.getY(), 
                (int)triangulo.p3.getX(), (int)triangulo.p3.getY(), "", triangulo.getEsp(), getBackground());
            lTriangulo.removerElem(menorTriangulo);
            lTotal.removerElem(menorTriangulo);
            redesenhar();
            lRemocoes.inserirFim(triangulo);
            lAlteracoes.inserirFim("REMOVEU TRIANGULO");
        } else if ((distancias[0] == distancias[5]) && (distancias[0] != 99)){ //circulo é menor
            CirculoGr circulo = (CirculoGr)menorCirculo.getConteudo();
            FiguraPontos.desenharPonto(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), "", circulo.getEspCirculo(), getBackground());
            FiguraPontos.desenharPonto(g, (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), getBackground());
            FiguraCirculos.desenharCirculo(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), getBackground());
            lCirculo.removerElem(menorCirculo);
            lTotal.removerElem(menorCirculo);
            redesenhar();   
            lRemocoes.inserirFim(circulo);
            lAlteracoes.inserirFim("REMOVEU CIRCULO");
        } else if((distancias[0] == distancias[6]) && (distancias[0] != 99)){ //figura é menor 
            FiguraGr figura = (FiguraGr)menorFigura.getConteudo();
            FiguraFiguras.desenharFigura(g, (int)figura.getP1().getX(), (int)figura.getP1().getY(), (int)figura.getP2().getX(), (int)figura.getP2().getY(), "", figura.getEsp(), getBackground(), getBackground());
            lFigura.removerElem(menorFigura);
            lTotal.removerElem(menorFigura);
            redesenhar();
            lRemocoes.inserirFim(figura);
            lAlteracoes.inserirFim("REMOVEU FIGURA");
        }
    }

    /**
     * Método menorPonto
     *
     * @return null se não houver nenhum ponto que tenha distancia menor ou igual a 10, caso contrário retorna o endereço do menor ponto.
     */
    private No menorPonto(){
        double pontoMenorDistancia = 99;
        No p = null;
        No menorNo = null;
        if(!lPonto.estaVazia()){
            p = lPonto.getInicio();
            menorNo = p; //primeiro é o menor
            while(p != null){
                PontoGr atual = (PontoGr)p.getConteudo();
                double d = Math.sqrt(Math.pow(this.xR - atual.getX(),2) + Math.pow(this.yR - atual.getY(),2));
                if(pontoMenorDistancia > d){
                    pontoMenorDistancia = d;
                    menorNo = p;
                }
                p = p.getProximo();
            }
        }
        if(pontoMenorDistancia > 10){
            menorNo = null;
        }
        return menorNo;
    }

    /**
     * Método distanciaMenorPonto
     *
     * @param p Um parâmetro - nó que contém os dados do ponto
     * @return O valor de retorno - distancia entre o ponto mais proximo e o ponto(xR,yR)
     */
    private double distanciaMenorPonto(No p){
        if(p != null){
            PontoGr atual = (PontoGr)p.getConteudo();
            return Math.sqrt(Math.pow(this.xR - atual.getX(),2) + Math.pow(this.yR - atual.getY(),2));
        } else return 99;
    }

    /**
     * Método menorReta
     *
     * @return null se não houver nenhuma reta que tenha distancia menor ou igual a 10, caso contrário retorna o endereço da menor reta.
     */
    // float line_dist = dist_sq(lx1, ly1, lz1, lx2, ly2, lz2);
    // float t = ((px - lx1) * (lx2 - lx1) + (py - ly1) * (ly2 - ly1) + (pz - lz1) * (lz2 - lz1)) / line_dist;
    // t = constrain(t, 0, 1);
    // return dist_sq(px, py, pz, lx1 + t * (lx2 - lx1), ly1 + t * (ly2 - ly1), lz1 + t * (lz2 - lz1));
    
    public double pDistance(float x, float y, float x1, float y1, float x2, float y2) {

      double A = x - x1; // position of point rel one end of line
      double B = y - y1;
      double C = x2 - x1; // vector along line
      double D = y2 - y1;
      double E = -D; // orthogonal vector
      double F = C;

      double dot = A * E + B * F;
      double len_sq = E * E + F * F;

      return (double) Math.abs(dot) / Math.sqrt(len_sq);
    }
    
    public static double distanceToLineSegment(double x1, double y1, double x2, double y2, double Px, double Py) {
         // Find the nearest point on the line segment to the given point
        double Qx = x1 + (x2 - x1) * ((Px - x1) * (x2 - x1) + (Py - y1) * (y2 - y1)) / ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double Qy = y1 + (y2 - y1) * ((Px - x1) * (x2 - x1) + (Py - y1) * (y2 - y1)) / ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

         // Calculate the distance between Q and the given point
        return Math.sqrt((Qx - Px) * (Qx - Px) + (Qy - Py) * (Qy - Py));
    }
    
    private double shortestDistance(double x1,double y1,double x2,double y2,double x3,double y3)
    {
        double px=x2-x1;
        double py=y2-y1;
        double temp=(px*px)+(py*py);
        double u=((x3 - x1) * px + (y3 - y1) * py) / (temp);
        if(u>1){
            u=1;
        }
        else if(u<0){
            u=0;
        }
        double x = x1 + u * px;
        double y = y1 + u * py;

        double dx = x - x3;
        double dy = y - y3;
        double dist = Math.sqrt(dx*dx + dy*dy);
        return dist;

    }

    
    private No menorReta(){
        double retaMenorDistancia = 99;
        No p = null;
        No menorNo = null;
        if(!lReta.estaVazia()){
            p = lReta.getInicio();
            menorNo = p; //primeiro é o menor
            while(p != null){
                RetaGr atual = (RetaGr)p.getConteudo();
                double d = Math.abs(atual.calcularM() * xR - yR + atual.calcularB())/(Math.sqrt(Math.pow(atual.calcularM(),2) + 1));
                //d =  pDistance(x1, y1,  x2,  y2,  xR,  yR);
                d = shortestDistance( x1, y1,x2, y2,xR, yR);

                // float line_dist = dist_sq(x1, y1, x2, y2);
                // float t = ((xR - x1) * (x2 - x1) + (yR - y1) * (y2 - y1)) / line_dist;
                // t = constrain(t, 0, 1);
                // return dist_sq(xR, yR, x1 + t * (x2 - x1), y1 + t * (y2 - y1));

                // double numerator = Math.abs((x2 - x1) * (y1 - yR) - (x1 - xR) * (y2 - y1));
                // double denominator = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                // double d = numerator / denominator;
                if(retaMenorDistancia > d){
                    retaMenorDistancia = d;
                    menorNo = p;
                }
                p = p.getProximo();
            }
        }
        if(retaMenorDistancia > 10){
            menorNo = null;
        }
        return menorNo;
    }

    /**
     * Método distanciaMenorReta
     *
     * @param p Um parâmetro - nó que contém os dados do retangulo
     * @return O valor de retorno - distancia entre a reta mais proxima e o ponto(xR,yR)
     */
    private double distanciaMenorReta(No p){
        if(p != null){
            RetaGr atual = (RetaGr)p.getConteudo();
            return Math.abs(atual.calcularM() * xR - yR + atual.calcularB())/(Math.sqrt(Math.pow(atual.calcularM(),2) + 1));
        } else return 99;
    }

    /**
     * Método menorRetangulo
     *
     * @return null se não houver nenhum retangulo que tenha distancia menor ou igual a 10, caso contrário retorna o endereço do menor retangulo.
     */
    private No menorRetangulo(){
        double retanguloMenorDistancia = 99;
        No p = null;
        No menorNo = null;
        if(!lRetangulo.estaVazia()){
            p = lRetangulo.getInicio();
            menorNo = p; //primeiro é o menor
            while(p != null){
                RetanguloGr atual = (RetanguloGr)p.getConteudo();
                double d = 0;
                double menor1 = 0;
                double menor2 = 0;
                double d1 = Math.abs(atual.r1.calcularM() * xR - yR + atual.r1.calcularB())/(Math.sqrt(Math.pow(atual.r1.calcularM(),2) + 1));
                double d3 = Math.abs(atual.r3.calcularM() * xR - yR + atual.r3.calcularB())/(Math.sqrt(Math.pow(atual.r3.calcularM(),2) + 1));
                if((yR > (atual.p1.getY()+10) && yR < (atual.p2.getY()) + 10) || (yR > (atual.p2.getY()+10) && yR < (atual.p1.getY()+10))){
                    d1 = Math.abs(atual.p1.getX() - xR);
                    d3 = Math.abs(atual.p4.getX() - xR);
                }
                double d2 = Math.abs(atual.r2.calcularM() * xR - yR + atual.r2.calcularB())/(Math.sqrt(Math.pow(atual.r2.calcularM(),2) + 1));
                double d4 = Math.abs(atual.r4.calcularM() * xR - yR + atual.r4.calcularB())/(Math.sqrt(Math.pow(atual.r4.calcularM(),2) + 1));
                if(d1 < d2){
                    menor1 = d1;
                }
                else{
                    menor1 = d2;
                }

                if(d3 < d4){
                    menor2 = d3;
                }
                else{
                    menor2 = d4;
                }

                if(menor1 < menor2){
                    d = menor1;
                }
                else{
                    d = menor2;
                }

                if(retanguloMenorDistancia > d){
                    retanguloMenorDistancia = d;
                    menorNo = p;
                }
                p = p.getProximo();
            }
        }
        if(retanguloMenorDistancia > 10){
            menorNo = null;
        }
        return menorNo;
    }

    /**
     * Método distanciaMenorRetangulo
     *
     * @param p Um parâmetro - nó que contém os dados da reta
     * @return O valor de retorno - distancia entre o retangulo mais proximo e o ponto(xR,yR)
     */
    private double distanciaMenorRetangulo(No p){
        if(p != null){
            RetanguloGr atual = (RetanguloGr)p.getConteudo();
            double d = 0;
            double menor1 = 0;
            double menor2 = 0;
            double d1 = Math.abs(atual.r1.calcularM() * xR - yR + atual.r1.calcularB())/(Math.sqrt(Math.pow(atual.r1.calcularM(),2) + 1));
            double d2 = Math.abs(atual.r2.calcularM() * xR - yR + atual.r2.calcularB())/(Math.sqrt(Math.pow(atual.r2.calcularM(),2) + 1));
            double d3 = Math.abs(atual.r3.calcularM() * xR - yR + atual.r3.calcularB())/(Math.sqrt(Math.pow(atual.r3.calcularM(),2) + 1));
            double d4 = Math.abs(atual.r4.calcularM() * xR - yR + atual.r4.calcularB())/(Math.sqrt(Math.pow(atual.r4.calcularM(),2) + 1));
            if(d1 < d2){
                menor1 = d1;
            }
            else{
                menor1 = d2;
            }

            if(d3 < d4){
                menor2 = d3;
            }
            else{
                menor2 = d4;
            }

            if(menor1 < menor2){
                d = menor1;
            }
            else{
                d = menor2;
            }
            return d;
        } else return 99;
    }

    /**
     * Método menorTriangulo
     *
     * @return null se não houver nenhum triangulo que tenha distancia menor ou igual a 10, caso contrário retorna o endereço do menor triangulo.
     */
    private No menorTriangulo(){
        double trianguloMenorDistancia = 99;
        No p = null;
        No menorNo = null;
        if(!lTriangulo.estaVazia()){
            p = lTriangulo.getInicio();
            menorNo = p; //primeiro é o menor
            while(p != null){
                TrianguloGr atual = (TrianguloGr)p.getConteudo();
                double d = 0;
                double menor1 = 0;
                double d1 = Math.abs(atual.r1.calcularM() * xR - yR + atual.r1.calcularB())/(Math.sqrt(Math.pow(atual.r1.calcularM(),2) + 1));
                double d2 = Math.abs(atual.r2.calcularM() * xR - yR + atual.r2.calcularB())/(Math.sqrt(Math.pow(atual.r2.calcularM(),2) + 1));
                double d3 = Math.abs(atual.r3.calcularM() * xR - yR + atual.r3.calcularB())/(Math.sqrt(Math.pow(atual.r3.calcularM(),2) + 1));
                if(d1 < d2){
                    menor1 = d1;
                }
                else{
                    menor1 = d2;
                }

                if(menor1 < d3){
                    d = menor1;
                }
                else{
                    d = d3;
                }

                if(trianguloMenorDistancia > d){
                    trianguloMenorDistancia = d;
                    menorNo = p;
                }
                p = p.getProximo();
            }
        }
        if(trianguloMenorDistancia > 10){
            menorNo = null;
        }
        return menorNo;
    }

    /**
     * Método distanciaMenorTriangulo
     *
     * @param p Um parâmetro - nó que contém os dados do triangulo
     * @return O valor de retorno - distancia entre o triangulo mais proximo e o ponto(xR,yR)
     */
    private double distanciaMenorTriangulo(No p){
        if(p != null){
            TrianguloGr atual = (TrianguloGr)p.getConteudo();
            double d = 0;
            double menor1 = 0;
            double d1 = Math.abs(atual.r1.calcularM() * xR - yR + atual.r1.calcularB())/(Math.sqrt(Math.pow(atual.r1.calcularM(),2) + 1));
            double d2 = Math.abs(atual.r2.calcularM() * xR - yR + atual.r2.calcularB())/(Math.sqrt(Math.pow(atual.r2.calcularM(),2) + 1));
            double d3 = Math.abs(atual.r3.calcularM() * xR - yR + atual.r3.calcularB())/(Math.sqrt(Math.pow(atual.r3.calcularM(),2) + 1));
            if(d1 < d2){
                menor1 = d1;
            }
            else{
                menor1 = d2;
            }

            if(menor1 < d3){
                d = menor1;
            }
            else{
                d = d3;
            }
            return d;
        }else return 99;
    }

    /**
     * Método menorCirculo
     *
     * @return null se não houver nenhum circulo que tenha distancia menor ou igual a 10, caso contrário retorna o endereço do menor circulo.
     */
    private No menorCirculo(){
        double circuloMenorDistancia = 99;
        No p = null;
        No menorNo = null;
        if(!lCirculo.estaVazia()){
            p = lCirculo.getInicio();
            menorNo = p; //primeiro é o menor
            while(p != null){
                CirculoGr atual = (CirculoGr)p.getConteudo();
                double d = 0;
                double d1 = Math.sqrt(Math.pow(this.xR - atual.p1.getX(),2) + Math.pow(this.yR - atual.p1.getY(),2));
                double d2 = Math.sqrt(Math.pow(this.xR - atual.p2.getX(),2) + Math.pow(this.yR - atual.p2.getY(),2));
                if(d1 < d2){
                    d = d1;
                }
                else{
                    d = d2;
                }
                if(circuloMenorDistancia > d){
                    circuloMenorDistancia = d;
                    menorNo = p;
                }
                p = p.getProximo();
            }
        }
        if(circuloMenorDistancia > 10){
            menorNo = null;
        }
        return menorNo;
    }

    public void mostraPontosFiguraCirculo(){
        Graphics g = getGraphics();
        if(!lCirculo.estaVazia()){
            No p = lCirculo.getInicio();
            while(p != null){
                CirculoGr circulo = (CirculoGr)p.getConteudo();
                Color cor = inverterCor(circulo.getCorCirculo());
                if(cor.equals(Color.WHITE)){
                    cor = Color.RED;
                }
                FiguraPontos.desenharPonto(g, (int)circulo.p1.getX(), (int)circulo.p1.getY(), "", circulo.getEspCirculo(), cor);
                FiguraPontos.desenharPonto(g, (int)circulo.p2.getX(), (int)circulo.p2.getY(), "", circulo.getEspCirculo(), cor);
                p = p.getProximo();
            }
        }
        if(!lFigura.estaVazia()){
            No p = lFigura.getInicio();
            while(p != null){
                FiguraGr figura = (FiguraGr)p.getConteudo();
                Color cor1 = inverterCor(figura.getCor1());
                Color cor2 = inverterCor(figura.getCor2());
                if(cor1.equals(Color.WHITE)){
                    cor1 = Color.RED;
                }
                if(cor2.equals(Color.WHITE)){
                    cor2 = Color.RED;
                }
                FiguraPontos.desenharPonto(g, (int)figura.p1.getX(), (int)figura.p1.getY(), "", figura.getEsp(), cor1);
                FiguraPontos.desenharPonto(g, (int)figura.p2.getX(), (int)figura.p2.getY(), "", figura.getEsp(), cor2);
                p = p.getProximo();
            }
        }
    }

    /**
     * Método distanciaMenorCirculo
     *
     * @param p Um parâmetro - nó que contém os dados do circulo
     * @return O valor de retorno - distancia entre o ponto mais proximo e o ponto(xR,yR)
     */
    private double distanciaMenorCirculo(No p){
        if(p != null){
            CirculoGr atual = (CirculoGr)p.getConteudo();
            double d = 0;
            double d1 = Math.sqrt(Math.pow(this.xR - atual.p1.getX(),2) + Math.pow(this.yR - atual.p1.getY(),2));
            double d2 = Math.sqrt(Math.pow(this.xR - atual.p2.getX(),2) + Math.pow(this.yR - atual.p2.getY(),2));
            if(d1 < d2){
                d = d1;
            }
            else{
                d = d2;
            }
            return d;
        }else return 99;
    }

    /**
     * Método menorFigura
     *
     * @return null se não houver nenhuma figura que tenha distancia menor ou igual a 10, caso contrário retorna o endereço da menor figura.
     */
    private No menorFigura(){
        double figuraMenorDistancia = 99;
        No p = null;
        No menorNo = null;
        if(!lFigura.estaVazia()){
            p = lFigura.getInicio();
            menorNo = p; //primeiro é o menor
            while(p != null){
                FiguraGr atual = (FiguraGr)p.getConteudo();
                double d = 0;
                double d1 = Math.sqrt(Math.pow(this.xR - atual.p1.getX(),2) + Math.pow(this.yR - atual.p1.getY(),2));
                double d2 = Math.sqrt(Math.pow(this.xR - atual.p2.getX(),2) + Math.pow(this.yR - atual.p2.getY(),2));
                if(d1 < d2){
                    d = d1;
                }
                else{
                    d = d2;
                }
                if(figuraMenorDistancia > d){
                    figuraMenorDistancia = d;
                    menorNo = p;
                }
                p = p.getProximo();
            }
        }
        if(figuraMenorDistancia > 10){
            menorNo = null;
        }
        return menorNo;
    }

    /**
     * Método distanciaMenorFigura
     *
     * @param p Um parâmetro - nó que contém os dados da figura
     * @return O valor de retorno - distancia entre a figura mais proxima e o ponto(xR,yR)
     */
    private double distanciaMenorFigura(No p){
        if(p != null){
            FiguraGr atual = (FiguraGr)p.getConteudo();
            double d = 0;
            double d1 = Math.sqrt(Math.pow(this.xR - atual.p1.getX(),2) + Math.pow(this.yR - atual.p1.getY(),2));
            double d2 = Math.sqrt(Math.pow(this.xR - atual.p2.getX(),2) + Math.pow(this.yR - atual.p2.getY(),2));
            if(d1 < d2){
                d = d1;
            }
            else{
                d = d2;
            }
            return d;
        } else return 99;
    }

    /**
     * Método inverterCor - inverte uma cor enviada por parâmetro
     *
     * @param corAtual Um parâmetro - que contem a cor atual
     * @return O valor de retorno - retorna a cor inversa da cor enviada por parâmetro
     */
    private Color inverterCor(Color corAtual){
        int red = 255 - corAtual.getRed();
        int green = 255 - corAtual.getGreen();
        int blue = 255 - corAtual.getBlue();
        return new Color(red,green,blue);
    }
}
