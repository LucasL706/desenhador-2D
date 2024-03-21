package ed;

/**
 * ListaLigadaSimples. Ed para Lista Ligada simples
 * 
 * @author Jo„o Pedro de Souza Oliveira,Beatriz Lopes Rizzo,Guilherme Diniz Leocadio,Lucas Lombardi de Brito
 * @version 20220905
 */
public class ListaLigadaSimples {
    No inicio, fim;
    int qtdNos;

    /**
     * ListaLigadaSimples Construtor
     *
     */
    public ListaLigadaSimples(){
        setInicio(null);
        setFim(null);
        setQtdNos(0);
    }

    /**
     * setInicio
     *
     * @param inicio No inicio da lista ligada
     */
    public void setInicio(No inicio){
        this.inicio = inicio;
    }

    /**
     * getInicio
     *
     * @return No de inicio da lista ligada
     */
    public No getInicio(){
        return(this.inicio);
    }

    /**
     * getFim
     *
     * @return No do final da lista ligada
     */
    public No getFim(){
        return(this.fim);
    }

    /**
     * setFim
     *
     * @param fim No fim da lista ligada
     */
    public void setFim(No fim){
        this.fim = fim;
    }

    /**
     * getQtdNos
     *
     * @return int, qtde de nos na lista ligada
     */
    public int getQtdNos(){
        return this.qtdNos;
    }

    /**
     * setQtdNos
     *
     * @param qtdNos Um par√¢metro
     */
    private void setQtdNos(int qtdNos){
        this.qtdNos = qtdNos;
    }

    /**
     * estaVazia
     *
     * @return boolean, true se estiver vazia e false caso contrario
     */
    public boolean estaVazia(){
        boolean vazia = false; 
        if (getQtdNos() == 0 && getInicio() == null && getFim() == null){
            vazia = true;
        }
        return vazia;
    }

    /**
     * inserirInicio
     *
     * @param elem Objeto a ser inserido no inicio da lista ligada
     */
    public void inserirInicio(Object elem) {
        No novo = new No(elem); //1

        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            novo.setProximo(inicio);
            setInicio(novo);
        }
        setQtdNos(getQtdNos() + 1);
    }

    /**
     * inserirFim
     *
     * @param elem Objeto a ser inserido no fim da lista ligada
     */
    public void inserirFim(Object elem){
        No novo = new No(elem);
        if (estaVazia()){
            setInicio(novo);
            setFim(novo);
        }
        else{
            getFim().setProximo(novo);
            setFim(novo);
        }
        setQtdNos(getQtdNos() + 1);
    }

    /**
     * removerInicio
     *
     * @return Objeto removido
     */
    public Object removerInicio(){
        No aux = null;
        Object obj = null; 
        if(!estaVazia()){
            if ((getInicio() == getFim())){
                aux = getInicio();
                setInicio(null);
                setFim(null);
            } else {
                aux = getInicio();
                setInicio(aux.getProximo());
                aux.setProximo(null);
            }
            setQtdNos(getQtdNos() - 1);
            obj = aux.getConteudo();
        }

        return(obj);
    }

    /**
     * removerFim
     *
     * @return Objeto removido
     */
    public Object removerFim(){
        No ant = getInicio();
        No aux = null;
        Object obj = null; 
        if (!estaVazia()){
            if ((getInicio() == getFim())){
                aux = getInicio();
                setInicio(null);
                setFim(null);
            } else {            
                // percorre ate achar o no antes do fim
                while(ant.getProximo() != fim){
                    ant = ant.getProximo();
                }
                ant.setProximo(null);
                aux = fim;
                setFim(ant);
            }
            setQtdNos(getQtdNos() - 1);
            obj = aux.getConteudo();           
        }
        return obj;
    }

    public void removerElem(Object elem){
        if(!estaVazia()){
            if(elem.equals(getInicio())){
                removerInicio();
            } else if(elem.equals(getFim())){
                removerFim();
            }else{
                No p = getInicio();
                No ant = getInicio();
                while((p != null) && (!(p.equals(elem)))){
                    ant = p;
                    p = p.getProximo();
                }
                if(p != null){
                    ant.setProximo(p.getProximo());
                    setQtdNos(getQtdNos()-1);
                }
            }
        }
    }

    public void resetaLista(){
        setInicio(null);
        setFim(null);
        setQtdNos(0);
    }
}