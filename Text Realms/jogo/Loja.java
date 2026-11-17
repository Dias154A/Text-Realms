package jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loja {
    private List<Item> estoque;
    private List<Integer> precos;

    public Loja() {
        this.estoque = new ArrayList<>();
        this.precos = new ArrayList<>();
        inicializarEstoque();
    }

    private void inicializarEstoque() {
        // Itens à venda: Nome, Tipo, Força, Agilidade, Vitalidade
        adicionarItem(new Item("Espada Longa de Aço", "ARMA", 6, 0, 0), 40);
        adicionarItem(new Item("Escudo do Guarda", "ARMADURA", 0, -2, 8), 35);
        adicionarItem(new Item("Poção de Vida Extra", "POCAO_VIDA", 0, 0, 0), 10);
        adicionarItem(new Item("Poção de Mana Extra", "POCAO_MANA", 0, 0, 0), 10);
    }

    private void adicionarItem(Item item, int preco) {
        estoque.add(item);
        precos.add(preco);
    }

    public void abrirMenuLoja(Heroi heroi, Scanner scanner) {
        boolean naLoja = true;
        while (naLoja) {
            System.out.println("\n🛒 --- MERCADOR VIAJANTE ---");
            System.out.println("Seu Ouro: 🪙 " + heroi.getOuro() + " moedas");
            System.out.println("----------------------------");
            
            for (int i = 0; i < estoque.size(); i++) {
                System.out.println((i + 1) + ". " + estoque.get(i).getNome() + " - Preço: 🪙 " + precos.get(i) + " ouro");
            }
            System.out.println((estoque.size() + 1) + ". Sair da Loja");
            System.out.print("O que deseja comprar? ");
            
            int escolha = scanner.nextInt();
            
            if (escolha > 0 && escolha <= estoque.size()) {
                int indice = escolha - 1;
                int precoItem = precos.get(indice);
                
                if (heroi.getOuro() >= precoItem) {
                    heroi.adicionarOuro(-precoItem); // Deduz o valor
                    Item itemComprado = estoque.get(indice);
                    heroi.getInventario().add(itemComprado);
                    System.out.println("🛍️ Você comprou: " + itemComprado.getNome() + "!");
                } else {
                    System.out.println("❌ Ouro insuficiente para comprar este item.");
                }
            } else if (escolha == estoque.size() + 1) {
                System.out.println("👋 Mercador: 'Que a sorte guie suas lâminas, viajante!'");
                naLoja = false;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}
