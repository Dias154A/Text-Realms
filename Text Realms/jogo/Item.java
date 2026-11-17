package jogo;

public class Item {
    private String nome;
    private String tipo; // "ARMA", "ARMADURA", "POCAO_VIDA", "POCAO_MANA"
    private int modForca;
    private int modAgilidade;
    private int modVitalidade;

    public Item(String nome, String tipo, int modForca, int modAgilidade, int modVitalidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.modForca = modForca;
        this.modAgilidade = modAgilidade;
        this.modVitalidade = modVitalidade;
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getModForca() { return modForca; }
    public int getModAgilidade() { return modAgilidade; }
    public int getModVitalidade() { return modVitalidade; }
}
