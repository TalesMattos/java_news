package java08;

import java.util.ArrayList;
import java.util.List;

class Produto1 {
	private String nome;
	private Double preco;
	
	public Produto1(String nome, Double preco) {
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

class Impressora {
	
	public static void imprime(Produto1 p) {
		System.out.println(p.getNome() + " = " + p.getPreco());
	}
}

public class MethodReferencesExemplo02 {
	
	public static void main(String[] args) {
		
		List<Produto1> lista = new ArrayList<>();
		
		lista.add(new Produto1("TV 42'", 2000.00));
		lista.add(new Produto1("Geladeira 470L'", 3200.00));
		lista.add(new Produto1("Fogão 4 bocas", 900.00));
		lista.add(new Produto1("Videogame", 1999.00));
		lista.add(new Produto1("Microondas", 550.00));
		
		/*Method reference 
		* Referência a um método estático (imprime)
		*/
		lista.forEach(Impressora::imprime);
		
	}
}
