package java08;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapAndFlatmap {

	public static void main(String[] args) {
		
		List<List<Integer>> listOfListofInts = Arrays.asList(Arrays.asList(1, 2, 3), 
											  																 Arrays.asList(4, 5, 6),
																												 Arrays.asList(7, 8, 9));
		
		List<List<List<Integer>>> listOfListofInts2 = Arrays.asList(Arrays.asList(Arrays.asList(7, 8, 9)), 
																															  Arrays.asList(Arrays.asList(7, 8, 9)),
																															  Arrays.asList(Arrays.asList(7, 8, 9)));
		
		//flatMap para objetos internos aninhados (ex: lista dentro de listas). Achata para uma lista só
		System.out.println(listOfListofInts.stream().flatMap(List::stream).collect(Collectors.toList()));
		System.out.println(listOfListofInts2.stream().flatMap(List::stream).collect(Collectors.toList()));
		
		// não consegue percorrer os itens aninhados
		System.out.println(listOfListofInts.stream().map(List::stream).collect(Collectors.toList()));

	}
}
