package jogo;

import java.util.ArrayList;
import java.util.List;

public class Heroi extends Personagem {
    private int nivel;
    private int experiencia;
    private int manaAtual;
    private int ouro; // Sistema de moedas adicionado
    
    private List<Item> inventario;
    private Item armaEquipada;
    private Item armaduraEquipada;

    public Heroi(String nome, Atributos atributos) {
        super(nome, atributos);
        this.nivel = 1;
        this.experiencia = 0;
        this.manaAtual = atributos.getManaMaxima();
        this.ouro = 20; // Começa com 20 moedas de ouro para gastar na loja
        this.inventario = new ArrayList<>();
        
        // Itens iniciais básicos
        inventario.add(new Item("Poção de Vida", "POCAO_VIDA", 0, 0, 0));
        inventario.add(new Item("Poção de Mana", "POCAO_MANA", 0, 0, 0));
    }

    public void usarHabilidadeEspecial(Personagem alvo) {
        int custoMana = 15;
        if (this.manaAtual >= custoMana) {
            this.manaAtual -= custoMana;
            int danoMagico = getAtributos().getDanoMagico();
            System.out.println("⚡ " + getNome() + " conjurou um Raio Arcano consumindo " + custoMana + " de Mana!");
            alvo.receberDano(danoMagico);
        } else {
            System.out.println("❌ Mana insuficiente! Você acabou perdendo o foco do ataque.");
        }
    }

    public void equiparItem(Item item) {
        if (item.getTipo().equals("ARMA")) {
            this.armaEquipada = item;
            System.out.println("⚔️ Você equipou: " + item.getNome());
        } else if (item.getTipo().equals("ARMADURA")) {
            this.armaduraEquipada = item;
            System.out.println("🛡️ Você equipou: " + item.getNome());
        }
        atualizarStatusEquipamentos();
    }

    private void atualizarStatusEquipamentos() {
        getAtributos().limparBonus();
        if (armaEquipada != null) {
            getAtributos().aplicarBonus(armaEquipada.getModForca(), armaEquipada.getModAgilidade(), armaEquipada.getModVitalidade());
        }
        if (armaduraEquipada != null) {
            getAtributos().aplicarBonus(armaduraEquipada.getModForca(), armaduraEquipada.getModAgilidade(), armaduraEquipada.getModVitalidade());
        }
    }

    public void exibirInventario() {
        System.out.println("\n🎒 --- INVENTÁRIO ---");
        if (inventario.isEmpty()) {
            System.out.println("Seu inventário está vazio.");
            return;
        }
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println((i + 1) + ". " + inventario.get(i).getNome() + " [" + inventario.get(i).getTipo() + "]");
        }
    }

    public void usarItemDoInventario(int indice) {
        if (indice < 0 || indice >= inventario.size()) {
            System.out.println("Item inválido!");
            return;
        }
        Item item = inventario.get(indice);
        if (item.getTipo().startsWith("POCAO")) {
            if (item.getTipo().equals("POCAO_VIDA")) {
                int cura = getAtributos().getVitalidade() * 6;
                setVidaAtual(Math.min(getVidaAtual() + cura, getAtributos().getVidaMaxima()));
                System.out.println("🧪 Curou " + cura + " de Vida!");
            } else {
                int regenera = getAtributos().getInteligencia() * 5;
                this.manaAtual = Math.min(this.manaAtual + regenera, getAtributos().getManaMaxima());
                System.out.println("🔮 Regenerou " + regenera + " de Mana!");
            }
            inventario.remove(indice);
        } else {
            equiparItem(item);
        }
    }

    public void ganharExperiencia(int xp) {
        this.experiencia += xp;
        System.out.println("✨ +" + xp + " XP.");
        if (this.experiencia >= this.nivel * 100) {
            this.nivel++;
            this.experiencia = 0;
            getAtributos().setForca(getAtributos().getForca() + 2);
            getAtributos().setAgilidade(getAtributos().getAgilidade() + 1);
            getAtributos().setVitalidade(getAtributos().getVitalidade() + 2);
            getAtributos().setInteligencia(getAtributos().getInteligencia() + 2);
            setVidaAtual(getAtributos().getVidaMaxima());
            this.manaAtual = getAtributos().getManaMaxima();
            System.out.println("\n🎉 NÍVEL UP! Você agora está no Nível " + this.nivel + "!");
        }
    }

    // Getters e Setters
    public int getNivel() { return nivel; }
    public int getManaAtual() { return manaAtual; }
    public List<Item> getInventario() { return inventario; }
    public int getOuro() { return ouro; }
    public void adicionarOuro(int quantidade) { this.ouro += quantidade; }
}

