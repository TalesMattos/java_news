package java08;

import java.util.ArrayList;
import java.util.List;

class ProdutoX {
	private String nome;
	private Double preco;
	
	public ProdutoX(String nome, Double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}

class ImprimeProdutosX {
	
	public static void imprime(List<ProdutoX> lista) {
		if(!lista.isEmpty()) {
			lista.forEach(p -> System.out.println(p.getNome()));
		}
		else {
			System.out.println("Lista vazia");
		}
	}
}

public class TypeInferenceExemplo {

	public static void main(String[] args) {
		
		List<ProdutoX> lista = new ArrayList<>();
		
		lista.add(new ProdutoX("TV 42'", 2000.00));
		lista.add(new ProdutoX("Geladeira 470L'", 3200.00));
		lista.add(new ProdutoX("Fogão 4 bocas", 900.00));
		lista.add(new ProdutoX("Videogame", 1999.00));
		lista.add(new ProdutoX("Microondas", 550.00));
		
		//Inferência de tipo na chamada ao método especializado imprime da classe ImprimeProdutoXs
		ImprimeProdutosX.imprime(new ArrayList<>());
		
	}

}
