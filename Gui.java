import java.awt.BorderLayout;
import java.awt.Color;
import ed.ListaLigadaSimples;
import ed.No;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Point;
@SuppressWarnings("serial")
/**
 * Cria a interface com o usuario (GUI)
 * 
 * @author João Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version 20220815
 */
class Gui extends JFrame {
    // Tipo Atual de primitivo
    private TipoPrimitivo tipoAtual = TipoPrimitivo.NENHUM;

    //testando 
    ListaLigadaSimples teste;

    // Cor atual
    private Color corAtual = Color.BLACK;

    // Espessura atual do primitivo
    private int espAtual = 2;

    // Componentes de GUI
    // barra de menu (inserir componente)
    private JToolBar barraComandos = new JToolBar();

    // mensagens
    private JLabel msg = new JLabel("Msg: ");

    // Painel de desenho
    private PainelDesenho areaDesenho = new PainelDesenho(msg, tipoAtual, corAtual, 10);

    //persistencia
    private JsonWriter arqJson;
    private JsonReader arqJsonLer;

    // Botoes
    private JButton jbPonto = new JButton("Ponto");
    private JButton jbReta = new JButton("Reta");
    private JButton jbRetangulo = new JButton("Retangulo");
    private JButton jbTriangulo = new JButton("Triangulo");
    private JButton jbCirculo = new JButton("Circulo");
    private JButton jbFigura = new JButton("Figura");
    private JButton jbRedesenhar = new JButton("Redesenhar");
    private JButton jbRemover = new JButton("Remover");
    private JButton jbRotacionar = new JButton("Rotação");
    private JButton jbEscala = new JButton("Escala");
    private JButton jbVoltar = new JButton("Ctrl + z");
    private JButton jbLimparTudo = new JButton("Limpar Tudo");
    private JButton jbLimpar = new JButton("Limpar");
    private JButton jbSalvar = new JButton("Salvar");
    private JButton jbLer = new JButton("Ler arq");

    private JButton jbCor = new JButton("Cor");
    private JButton jbSair = new JButton("Sair");

    // Entrada (slider) para definir espessura dos primitivos
    private JLabel jlEsp = new JLabel("   Espessura: 2" );
    private JSlider jsEsp = new JSlider(1, 50, 2);
    /**
     * Constroi a GUI
     *
     * @param larg largura da janela
     * @param alt altura da janela
     */
    public Gui(int larg, int alt) {
        /**
         * Definicoes de janela
         */
        super("Testa Primitivos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(larg, alt);
        setVisible(true);
        setResizable(false);

        // Adicionando os componentes
        barraComandos.add(jbPonto);
        barraComandos.add(jbReta);
        barraComandos.add(jbRetangulo);
        barraComandos.add(jbTriangulo);
        barraComandos.add(jbCirculo);
        barraComandos.add(jbFigura);
        barraComandos.add(jbLimpar); // Botao de Limpar
        barraComandos.add(jbLimparTudo); // Botao de Limpar ED
        barraComandos.add(jbRedesenhar);
        barraComandos.add(jbRemover);
        barraComandos.add(jbRotacionar);
        barraComandos.add(jbEscala);
        barraComandos.add(jbSalvar);
        barraComandos.add(jbLer);
        barraComandos.add(jbVoltar);
        barraComandos.add(jbCor); // Botao de Cores

        barraComandos.add(jlEsp); // Label para espessura
        barraComandos.add(jsEsp);    // Slider para espacamento
        areaDesenho.setEsp(espAtual); // define a espessura inicial
        barraComandos.add(jbSair); // Botao de Cores
        areaDesenho.setCorBackground(getContentPane().getBackground());
        // adiciona os componentes com os respectivos layouts
        add(barraComandos, BorderLayout.NORTH);                
        add(areaDesenho, BorderLayout.CENTER);                
        add(msg, BorderLayout.SOUTH);

        // Adiciona "tratador" ("ouvidor") de eventos para 
        // cada componente
        jbPonto.addActionListener(e -> {
                    resetToDefaultCursor();
                    tipoAtual = TipoPrimitivo.PONTO;
                    areaDesenho.setTipo(tipoAtual);
                    if(areaDesenho.getTipo() == TipoPrimitivo.REMOVER){
                        areaDesenho.redesenhar();
                    }
            });        
        jbReta.addActionListener(e -> {
                    resetToDefaultCursor();
                    tipoAtual = TipoPrimitivo.RETA;
                    areaDesenho.primeiraVez = true;
                    areaDesenho.segundaVez = true;
                    areaDesenho.setTipo(tipoAtual);
                    if(areaDesenho.getTipo() == TipoPrimitivo.REMOVER){
                        areaDesenho.redesenhar();
                    }
            });
        jbRetangulo.addActionListener(e -> {
                    resetToDefaultCursor();
                    tipoAtual = TipoPrimitivo.RETANGULO;
                    areaDesenho.primeiraVez = true;
                    areaDesenho.segundaVez = true;
                    areaDesenho.setTipo(tipoAtual);
                    if(areaDesenho.getTipo() == TipoPrimitivo.REMOVER){
                        areaDesenho.redesenhar();
                    }
            });
        jbRedesenhar.addActionListener(e -> {
                    resetToDefaultCursor();
                    areaDesenho.redesenhar();
            }); 
        jbTriangulo.addActionListener(e -> {
                    resetToDefaultCursor();
                    tipoAtual = TipoPrimitivo.TRIANGULO;
                    areaDesenho.primeiraVez = true;
                    areaDesenho.segundaVez = true;
                    areaDesenho.setTipo(tipoAtual);
                    if(areaDesenho.getTipo() == TipoPrimitivo.REMOVER){
                        areaDesenho.redesenhar();
                    }
            });
        jbCirculo.addActionListener(e -> {
                    resetToDefaultCursor();
                    tipoAtual = TipoPrimitivo.CIRCULO;
                    areaDesenho.primeiraVez = true;
                    areaDesenho.segundaVez = true;
                    areaDesenho.setTipo(tipoAtual);
                    if(areaDesenho.getTipo() == TipoPrimitivo.REMOVER){
                        areaDesenho.redesenhar();
                    }
            });        
        jbFigura.addActionListener(e -> {
                    resetToDefaultCursor();
                    tipoAtual = TipoPrimitivo.FIGURA;
                    areaDesenho.primeiraVez = true;
                    areaDesenho.segundaVez = true;
                    Color c = JColorChooser.showDialog(null, "Escolha a cor dos circulos", msg.getForeground()); 
                    if (c != null){ 
                        corAtual = c; // pega do chooserColor 
                    }
                    areaDesenho.setCor1(corAtual);

                    c = JColorChooser.showDialog(null, "Escolha a cor das retas", msg.getForeground()); 
                    if (c != null){ 
                        corAtual = c; // pega do chooserColor 
                    }
                    areaDesenho.setCor2(corAtual);
                    areaDesenho.setTipo(tipoAtual);
                    if(areaDesenho.getTipo() == TipoPrimitivo.REMOVER){
                        areaDesenho.redesenhar();
                    }
            });     
        jbLimpar.addActionListener(e -> {
                    resetToDefaultCursor();
                    areaDesenho.removeAll();
                    jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivoda tela) 
                    repaint();
                    areaDesenho.setAlt(TipoOperacoes.LIMPOU);
            });
        jbRemover.addActionListener(e -> {
                    customCursor();
                    areaDesenho.mostraPontosFiguraCirculo();
                    tipoAtual = TipoPrimitivo.REMOVER;
                    areaDesenho.setTipo(tipoAtual);
                    areaDesenho.setAlt(TipoOperacoes.REMOVEU);
            });
        jbRotacionar.addActionListener(e -> {
                    resetToDefaultCursor();
                    areaDesenho.primeiraVez = true;
                    areaDesenho.setTipo(TipoPrimitivo.ROTACAO);
                    areaDesenho.setAlt(TipoOperacoes.ROTACAO);
            });  
        jbEscala.addActionListener(e -> {
                    resetToDefaultCursor();
                    areaDesenho.primeiraVez = true;
                    areaDesenho.setTipo(TipoPrimitivo.ESCALA);
                    areaDesenho.setAlt(TipoOperacoes.ESCALA);
            });   
        jbLimparTudo.addActionListener(e -> {
                    resetToDefaultCursor();
                    areaDesenho.removeAll();
                    jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivoda tela) 
                    repaint();   
                    tipoAtual = TipoPrimitivo.REMOVER;
                    areaDesenho.setTipo(tipoAtual);
                    areaDesenho.setAlt(TipoOperacoes.REMOVEU);
                    areaDesenho.removerED();
                    areaDesenho.redesenhar();
            });
        jbVoltar.addActionListener(e -> {
                    resetToDefaultCursor();
                    areaDesenho.voltar();
                    areaDesenho.redesenhar();
            });
        jbCor.addActionListener(e -> {
                    resetToDefaultCursor();
                    Color c = JColorChooser.showDialog(null, "Escolha uma cor", msg.getForeground()); 
                    if (c != null){ 
                        corAtual = c; // pega do chooserColor 
                    }
                    areaDesenho.setCorAtual(corAtual); // cor atual
            });  
        jsEsp.addChangeListener(e -> {
                    resetToDefaultCursor();
                    espAtual = jsEsp.getValue();
                    jlEsp.setText("   Espessura: " + String.format("%-5s", espAtual));
                    areaDesenho.setEsp(espAtual);        
            });        

        jbSair.addActionListener(e -> {
                    System.exit(0);
            });//ListaLigadaSimples lPonto,ListaLigadaSimples lReta,ListaLigadaSimples lRetangulo, ListaLigadaSimples lCirculo,ListaLigadaSimples lTriangulo,ListaLigadaSimples lFigura)
        jbSalvar.addActionListener(e -> {
                    resetToDefaultCursor();
                    arqJson = new JsonWriter(larg,alt,areaDesenho.lPonto,areaDesenho.lReta,areaDesenho.lRetangulo,areaDesenho.lCirculo,areaDesenho.lTriangulo,areaDesenho.lFigura);
            });
        jbLer.addActionListener(e -> {
                    resetToDefaultCursor();
                    resetToDefaultCursor();
                    JFileChooser fc;
                    int resultado = 0;
                    do{
                        fc = new JFileChooser(".\\");
                        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        resultado = fc.showSaveDialog(this);
                        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                "Json", "json");
                        fc.setFileFilter(filter);
                        if(fc.getSelectedFile() == null && resultado != JFileChooser.CANCEL_OPTION){
                            JOptionPane.showMessageDialog(null, "Arquivo inválido, escolha outro");
                        }
                    }while(fc.getSelectedFile() == null && resultado != JFileChooser.CANCEL_OPTION);
                    if(resultado != JFileChooser.CANCEL_OPTION){
                        areaDesenho.redesenharComCorDeFundo();
                        areaDesenho.removeAll();
                        jsEsp.setValue(1); // inicia slider (necessario para limpar ultimo primitivoda tela) 
                        tipoAtual = TipoPrimitivo.REMOVER;
                        areaDesenho.setTipo(tipoAtual);
                        areaDesenho.setAlt(TipoOperacoes.REMOVEU);
                        areaDesenho.removerED();
                        areaDesenho.redesenhar();
                        arqJsonLer = new JsonReader(larg,alt,fc.getSelectedFile().getName(),areaDesenho.lPonto,areaDesenho.lReta,areaDesenho.lRetangulo,areaDesenho.lCirculo,areaDesenho.lTriangulo,areaDesenho.lFigura);
                        areaDesenho.lPonto = arqJsonLer.lPonto;
                        areaDesenho.lReta = arqJsonLer.lReta;
                        areaDesenho.lTriangulo = arqJsonLer.lTriangulo;
                        areaDesenho.lRetangulo = arqJsonLer.lRetangulo;
                        areaDesenho.lFigura = arqJsonLer.lFigura;
                        areaDesenho.lCirculo = arqJsonLer.lCirculo;
                        areaDesenho.redesenhar();
                    }
            });

    }

    public void customCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("teste1.png");

        Point hotspot = new Point(0,0);
        Cursor cursor = toolkit.createCustomCursor(image, hotspot, "CursorLixo");
        setCursor(cursor);

    }

    public void resetToDefaultCursor() {
        // Obtém o cursor padrão do sistema
        Cursor defaultCursor = Cursor.getDefaultCursor();

        // Define o cursor de volta para o padrão
        setCursor(defaultCursor);
    }
}
