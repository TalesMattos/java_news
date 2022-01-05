package java08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TypeInferenceInLocalVariable {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		var lp = new ArrayList<ProdutoX>();
		lp.add(new ProdutoX("TV 50'", 2900.00));
		lp.add(new ProdutoX("TV 42'", 2000.00));
		lp.stream().map(ProdutoX::getPreco).forEach(System.out::println);
		
		List<String> minhasLetras = Arrays.asList("a", "b", "c");
		for (var letra : minhasLetras) {
			// infere letra como String
			System.out.println(letra);
		}  
		
		for (var contador = 0; contador < 10; contador++)  {
			// infere contador como int
			System.out.println(contador);
		}   
		
		List<String> minhaLista = Arrays.asList("Java", "Kotlin");
		String resultado = minhaLista.stream()
		  .map((/*@Nonnull*/ var x) -> x.toUpperCase())
		  .collect(Collectors.joining(", "));
		
		var meuContador = 5;  // Infere tipo inteiro
//		meuContador = "6"; // jogará: Error: incompatible types: java.lang.String cannot be converted to int
		
		var veiculo = new Circunferencia(); // instancia um Veiculo do tipo Carro
//		veiculo = new Retangulo();      // Um tempo depois, instanciar como Bicicleta não irá funcionar. Nessa situação utilizar a classe Veiculo na primeira vez seria a solução ideal.

		/*
			QUANDO NÃO PODE SER UTILIZADO
			Assinatura de métodos:
			public long process(var list) { }
			
			Declarar variável sem uma inicializações ou com null:
			var x;
			var x = null;
			
			Ao utilizar lambdas sem um tipo explícito:
			var x = () -> {}
		*/
	}

}
