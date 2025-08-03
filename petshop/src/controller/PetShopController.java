package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import model.PetShop;

public class PetShopController {
    
    private List<PetShop> petshops;
    
    public PetShopController() {
        this.petshops = new ArrayList<>();
        inicializarPetShops();
    }
    
    private void inicializarPetShops() {
        // Os petshops serão criados dinamicamente no cálculo
        // pois o preço varia conforme o dia da semana
    }
    
    public PetShop calcularMelhorPetShop(String data, int qtdCaesPequenos, int qtdCaesGrandes) {
        boolean ehFinalDeSemana = verificarFinalDeSemana(data);
        
        // Criar petshops com preços baseados no dia da semana
        List<PetShop> opcoes = criarPetShopsComPrecos(qtdCaesPequenos, qtdCaesGrandes, ehFinalDeSemana);
        
        // Encontrar o melhor petshop (menor preço, em caso de empate o mais próximo)
        PetShop melhorOpcao = opcoes.get(0);
        
        for (PetShop petshop : opcoes) {
            if (petshop.getPreco() < melhorOpcao.getPreco() || 
                (petshop.getPreco() == melhorOpcao.getPreco() && petshop.getDistancia() < melhorOpcao.getDistancia())) {
                melhorOpcao = petshop;
            }
        }
        
        return melhorOpcao;
    }
    
    private List<PetShop> criarPetShopsComPrecos(int qtdCaesPequenos, int qtdCaesGrandes, boolean ehFinalDeSemana) {
        List<PetShop> opcoes = new ArrayList<>();
        
        // Meu Canino Feliz - 2km (2000m)
        int precoMeuCanino;
        if (ehFinalDeSemana) {
            // 20% de aumento nos finais de semana
            precoMeuCanino = (int)((20 * qtdCaesPequenos + 40 * qtdCaesGrandes) * 1.2);
        } else {
            precoMeuCanino = 20 * qtdCaesPequenos + 40 * qtdCaesGrandes;
        }
        opcoes.add(new PetShop("Meu Canino Feliz", 2000, precoMeuCanino));
        
        // Vai Rex - 1.7km (1700m)
        int precoVaiRex;
        if (ehFinalDeSemana) {
            precoVaiRex = 20 * qtdCaesPequenos + 55 * qtdCaesGrandes;
        } else {
            precoVaiRex = 15 * qtdCaesPequenos + 50 * qtdCaesGrandes;
        }
        opcoes.add(new PetShop("Vai Rex", 1700, precoVaiRex));
        
        // ChowChawgas - 800m
        // Mesmo preço todos os dias
        int precoChowChawgas = 30 * qtdCaesPequenos + 45 * qtdCaesGrandes;
        opcoes.add(new PetShop("ChowChawgas", 800, precoChowChawgas));
        
        return opcoes;
    }
    
    private boolean verificarFinalDeSemana(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(data, formatter);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de data inválido. Use dd/MM/yyyy");
        }
    }
    
    public boolean validarData(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(data, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

