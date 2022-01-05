package java09;

import java.util.ArrayList;
import java.util.List;

class Produto {
	
	private Integer codigo;
	private String nome;
	private Double preco;
	
	public Produto() {
		this.codigo = 0;
		this.nome = "";
		this.preco = 0.0;
	}

	public Produto(Integer codigo, String nome, Double preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.preco = preco;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

public class StreamTakeDropWhile {

	public static void main(String[] args) {
		
		List<Produto> listaProdutos = new ArrayList<Produto>(); 
		
		listaProdutos.add(new Produto(1000, "Geladeira 470L", 2999.00));
		listaProdutos.add(new Produto(1000, "Geladeira Turbo 470L", 2999.00));
		listaProdutos.add(new Produto(2000, "TV UHD 50''", 3500.00));
		listaProdutos.add(new Produto(1001, "TV UHD 65''", 5000.00));
		listaProdutos.add(new Produto(3000, "Microondas 20L", 399.00));
		listaProdutos.add(new Produto(1001, "Geladeira 120L", 900.00));
		listaProdutos.add(new Produto(4000, "Computador i5 2.9Ghz 4GB 1TB HD", 2429.00));
		listaProdutos.add(new Produto(1002, "Geladeira 500L", 3100.00));
		
		
		listaProdutos.stream()
			// para no item que nao satisfaz mais o predicado
			// o primeiro item tem q satisfazer pra pegar ao menos 1 item
			.takeWhile(p -> p.getNome().contains("Geladeira"))
			.map(p -> p.getNome())
			.forEach(System.out::println);

		System.out.println("----------------------");
		
		listaProdutos.stream()
			// ao contrario de takeWhile - continua a partir do item que nao mais satisfaz o predicado
			.dropWhile(p -> p.getNome().contains("Geladeira")) //oposto do .takeWhile
			.map(p -> p.getNome())
			.forEach(System.out::println);
	}

}
