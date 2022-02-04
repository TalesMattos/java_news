package java11;

import java.util.function.Function;

public class ParametrosLambdaSemTipo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Function<String, String> concatena = (String s) -> s + ". ";
	
		//var em lambda expression
		Function<Object, String> concatena2 = (var s) -> s + ". ";
		
		System.out.println(concatena.apply("3") + concatena2.apply(2));
		
	}

}
