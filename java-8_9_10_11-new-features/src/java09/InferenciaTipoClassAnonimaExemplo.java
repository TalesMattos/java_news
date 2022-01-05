package java09;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InferenciaTipoClassAnonimaExemplo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Classe interna anônima
		// no java 8 era obrigatório informar o tipo na instanciacao da classe anonima
		InferenciaTipoClasseAnonimaSomaTudoIFC<String> somaString = new InferenciaTipoClasseAnonimaSomaTudoIFC<>() {
			
			@Override
			public String soma(String a, String b) {
				// TODO Auto-generated method stub
				return a + b;
			}
		};
		
		System.out.println(somaString.soma("Olá", " mundo!"));
		
		List<? extends InferenciaTipoClasseAnonimaSomaTudoIFC<String>> lg0 = new ArrayList<>();
//		lg0.add(somaString); // ERRO: não é possivel garantir o tipo com extends.
		
		List<? super InferenciaTipoClasseAnonimaSomaTudoIFC<String>> lg = new ArrayList<>();
		lg.add(somaString); // com super é possível garantir. Number é super de Number

		Teste t = new Teste();
		List<? super Teste> lt = new ArrayList<>();
		lt.add(t);
		
		List<? super Number> ln = new ArrayList<>();
		ln.add(Long.valueOf(1));
		ln.add(Double.valueOf(1.1));
		for (Object n : ln) {
			System.out.println(n);
		}
		
		List<Integer> lni = new ArrayList<>();
		lni.add(Integer.valueOf(1));
		List<Double> lnd = new ArrayList<>();
		lnd.add(Double.valueOf(1.11));
		printGeneric(lni);
		printGeneric(lnd);

	}
	
	public static void printGeneric(List<? extends Number> list) {
		for (Number number : list) {
			System.out.println(number);
		}
	}

}

class Teste implements InferenciaTipoClasseAnonimaSomaTudoIFC<String> {
	@Override
	public String soma(String a, String b) {
		return a + b;
	}
}
