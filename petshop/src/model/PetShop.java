package model;

public class PetShop {
	public String nome;
	public int distancia;
	public int preco;
	
	public PetShop(String nome, int distancia, int preco) {
		super();
		this.nome = nome;
		this.distancia = distancia;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}
	
	
}
