package java08;

import java.util.ArrayList;
import java.util.List;

class Produto2 {
	private String nome;
	private Double preco;
	
	public Produto2(String nome, Double preco) {
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
	
	public void imprime() {
		System.out.println(nome + " = " + preco);
	}
}

public class MethodReferencesExemplo03 {
	
	public static void main(String[] args) {
		
		List<Produto2> lista = new ArrayList<>();
		
		lista.add(new Produto2("TV 42'", 2000.00));
		lista.add(new Produto2("Geladeira 470L'", 3200.00));
		lista.add(new Produto2("Fog�o 4 bocas", 900.00));
		lista.add(new Produto2("Videogame", 1999.00));
		lista.add(new Produto2("Microondas", 550.00));
		
		/*Method reference 
		* Refer�ncia a um m�todo de inst�ncia (imprime) de um objeto arbitr�rio (?) a partir de um tipo espec�fico (Produto)
		*/
		lista.forEach(Produto2::imprime);
		
	}
}
