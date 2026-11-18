package construtor;

public class BuilderHamburguer {
    String tipoPao = "Pão italiano";
    int quantidade_hamburgueres = 1;
    boolean temCebola = true;
    boolean condimentos = true;
    boolean ketchup = true;

    public BuilderHamburguer tipoPao(String tipoPao){
        this.tipoPao = tipoPao;
        return this;
    }
    public BuilderHamburguer quantidadeHamburguer(int quantidade){
        this.quantidade_hamburgueres = quantidade;
        return this;
    }
    public BuilderHamburguer temCebola(boolean confirmacao){
        this.temCebola = confirmacao;
        return this;
    }
    public BuilderHamburguer temCodimentos(boolean confirmacao){
        this.condimentos = confirmacao;
        return this;
    }
    public BuilderHamburguer temKetchup(boolean confirmacao){
        this.ketchup = confirmacao ;
        return this;
    }

    public String getTipoPao() {
        return tipoPao;
    }

    public int getQuantidade_hamburgueres() {
        return quantidade_hamburgueres;
    }

    public boolean isTemCebola() {
        return temCebola;
    }

    public boolean isCondimentos() {
        return condimentos;
    }

    public boolean isKetchup() {
        return ketchup;
    }
}
