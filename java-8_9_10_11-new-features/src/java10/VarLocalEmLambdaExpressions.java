package java10;

import java.util.function.Function;

public class VarLocalEmLambdaExpressions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Function<String, String> concatena = (String s) -> s + ". ";
	
		//var em lambda expression
		Function<String, String> concatena2 = (var s) -> s + ". ";
		
	}

}
