package java08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Produto_1 {
	
	private Integer codigo;
	private String nome;
	private Double preco;
	
	public Produto_1() {
		this.codigo = 0;
		this.nome = "";
		this.preco = 0.0;
	}

	public Produto_1(Integer codigo, String nome, Double preco) {
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

class OrdenaProduto_1PorPreco implements Comparator<Produto_1> 
{ 
    public int compare(Produto_1 p1, Produto_1 p2) 
    { 
        return p1.getPreco().compareTo(p2.getPreco()); 
    } 
} 

public class JavaStreamsExemplo {

	public static void main(String[] args) {
		
		List<Produto_1> listaProduto_1s = new ArrayList<Produto_1>(); 
		
		listaProduto_1s.add(new Produto_1(1000, "Geladeira 470L", 2999.00));
		listaProduto_1s.add(new Produto_1(2000, "TV UHD 50''", 3500.00));
		listaProduto_1s.add(new Produto_1(1001, "TV UHD 65''", 5000.00));
		listaProduto_1s.add(new Produto_1(3000, "Microondas 20L", 399.00));
		listaProduto_1s.add(new Produto_1(1001, "Geladeira 120L", 900.00));
		listaProduto_1s.add(new Produto_1(4000, "Computador i5 2.9Ghz 4GB 1TB HD", 2429.00));
		listaProduto_1s.add(new Produto_1(1002, "Geladeira 500L", 3100.00));
		
		System.out.println("Usando a forma 'antiga'...");
		
		//Cria lista só de geladeiras
		List<Produto_1> listaGeladeiras = new ArrayList<Produto_1>();
		for(Produto_1 p : listaProduto_1s) {
			if(p.getNome().contains("Geladeira")) {
				listaGeladeiras.add(p);
			}
		}
		
		//Ordena a lista de geladeiras por preço crescente
		Collections.sort(listaGeladeiras, new OrdenaProduto_1PorPreco());
		
		//Cria a lista só de nomes em letras maiúsculas das geladeiras
		List<String> listaNomesGeladeiras = new ArrayList<String>();
		for(Produto_1 p : listaGeladeiras) {
			listaNomesGeladeiras.add(p.getNome().toUpperCase());
		}
		
		for(String nome : listaNomesGeladeiras) {
			System.out.println(nome);
		}
		
		/*************************************************************/
		
		System.out.println("\nUsando Java Streams...");
		
		//Obtém um fluxo de dados (stream)
		listaProduto_1s.stream()
			//Interface Predicate - filtrar os nomes dos Produto_1s que contém 'Geladeira'
			.filter(p -> p.getNome().contains("Geladeira"))
			//Interface Comparator - ordenar o preço em ordem crescente de acordo com a implemetação da classe OrdenaProduto_1PorPreco
			.sorted((p1, p2) -> p1.getPreco().compareTo(p2.getPreco()))
			//Interface Function - aplicar uma dada função (toUpperCase) aos elementos dessa stream
			.map(p -> p.getNome().toUpperCase())
			//Interface Consumer - percorrer cada elemento da stream e exibi-los no console 
			.forEach(System.out::println);
		
	}

}
