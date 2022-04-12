package java08;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * https://www.baeldung.com/java-8-functional-interfaces
 * https://www.baeldung.com/java-8-streams-introduction
 * https://www.baeldung.com/java-8-streams
 * 
 * 
 * 
 */
public class Streams {

	
	public static void main(String[] args) {
		
		List<String> listS = List.of("a", "b", "c", "d", "e", "a");
	
		Streams sObj = new Streams();
		
		System.out.println("Stream...");
		listS.stream().forEach(element -> sObj.doWork(element));

		System.out.println("Parallel Stream...");
		listS.parallelStream().forEach(element -> sObj.doWork(element));
		
		long count = listS.stream().distinct().count();
		System.out.println(count);
		
		boolean isExist = listS.stream().anyMatch(element -> element.contains("a"));
		System.out.println(isExist);
		
		Stream<String> streamFilter = listS.stream().filter(element -> element.contains("d"));
		
		List<String> uris = new ArrayList<>();
		uris.add("C:\\My.txt");
		Stream<Path> streamPath = uris.stream().map(uri -> Paths.get(uri));
		
		String[][] arr = new String[][] {{"a", "b", "c"}, {"1", "2", "3"}, {"%", "$", "#"}};
		Stream<String[]> streamArray1 = Arrays.stream(arr);
		Stream<String[]> streamArray2 = Arrays.stream(arr);
		Stream<String[]> streamArray3 = Arrays.stream(arr);
		Stream<String[]> streamArray4 = Arrays.stream(arr);
		streamArray1.map(e -> e[0]).forEach(System.out::print);
		System.out.println();
		streamArray2.flatMap(e -> Arrays.stream(e)).forEach(System.out::print);
		System.out.println();
		streamArray3.flatMap(Arrays::stream).forEach(System.out::print);
		System.out.println();
		Arrays.stream(new Integer[][]{{0,1,2},{3,4,5}}).flatMapToInt(e -> Arrays.stream(e).mapToInt(i ->  i) ).forEach(System.out::print);
		
		ArrayList<String> list = new ArrayList<>();
		list.add("One");
		list.add("OneAndOnly");
		list.add("Derek");
		list.add("Change");
		list.add("factory");
		list.add("justBefore");
		list.add("Italy");
		list.add("Italy");
		list.add("Thursday");
		list.add("");
		list.add("");
		boolean isValid = list.stream().anyMatch(element -> element.contains("h")); // true
		boolean isValidOne = list.stream().allMatch(element -> element.contains("h")); // false
		boolean isValidTwo = list.stream().noneMatch(element -> element.contains("h")); // false
		
		//For empty streams, the allMatch() method with any given predicate will return true:
		Stream.empty().allMatch(Objects::nonNull); // true
		//This is a sensible default, as we can't find any element that doesn't satisfy the predicate.
		//Similarly, the anyMatch() method always returns false for empty streams:
		Stream.empty().anyMatch(Objects::nonNull); // false
		
		System.out.println("");
		List<Integer> integers = Arrays.asList(1, 1, 1);
		Integer reduced = integers.stream().reduce(23, (a, b) -> a + b);
		System.out.println(reduced); 
		
		
		
		
		
		
		
		
		
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.stream();
		
		Stream<String> streamOfArray = Stream.of("a", "b", "c", "d", "e");
		
		String[] arr2 = new String[]{"a", "b", "c"};
		Stream<String> streamOfArrayFull = Arrays.stream(arr2);
		Stream<String> streamOfArrayPart = Arrays.stream(arr2, 1, 3);
		streamOfArrayPart.forEach(System.out::print);
		
		Stream<Object> streamBuilder = Stream.builder().add("a").add("b").add("c").build();
		Stream<String> streamBuilderStr = Stream.<String> builder().add("a").add("b").add("c").build();
		
		System.out.println();
		int[] a = {0};
		Stream<String> streamGenerated = Stream.generate(() -> "element" + (++a[0])).limit(10);
		streamGenerated.forEach(System.out::println);
		
		System.out.println();
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(20);
		streamIterated.forEach(v -> System.out.print(v + " "));
		
		IntStream intStream = IntStream.range(1, 3);
		LongStream longStream = LongStream.rangeClosed(1, 3);
		
		System.out.println();
		Random random = new Random();
		DoubleStream doubleStream = random.doubles(3);
		doubleStream.forEach(System.out::println);
		
		System.out.println();
		IntStream streamOfChars = "abc".chars();
		streamOfChars.forEach(v -> System.out.print(((char) v) + " "));
		
		System.out.println();
		Stream<String> streamOfString = Pattern.compile(", ").splitAsStream("a, b, c");
		streamOfString.forEach(System.out::println);
		
		
		try {
			Path path = Paths.get("hino-do-gremio.txt");
			Stream<String> streamOfStrings = Files.lines(path);
			Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
			streamOfStrings.forEach(System.out::println);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		
		Stream<String> stream = Stream.of("ba", "b", "bc", "b2", "eb").filter(element -> element.contains("b"));
		Optional<String> anyElement = stream.findAny();
		System.out.println(anyElement.get()); 
		//However, an attempt to reuse the same reference after calling the terminal operation will trigger the IllegalStateException:
		//Java 8 streams can't be reused.
		// FIXME Optional<String> firstElement = stream.findFirst(); //IllegalStateException. The stream was already used.
		
		List<String> elements = Stream.of("a", "b", "c").filter(element -> element.contains("b")).collect(Collectors.toList());
		Optional<String> anyElement2 = elements.stream().findAny();
		Optional<String> firstElement2 = elements.stream().findFirst();
			
		System.out.println();
		Stream<String> onceModifiedStream = Stream.of("abcd", "bbcd", "cbcd").skip(1);
		onceModifiedStream.forEach(System.out::println);
		System.out.println(IntStream.of(1,2,3,4,5).sum());
		System.out.println(IntStream.range(1, 4).reduce((x, y) -> x + y));
		
		int reducedParams = Stream.of(1, 2, 3)
			  .reduce(10, (x, y) -> x + y,
			  						(x, y) -> {
										     System.out.println("combiner was called");
										     return x + y;
										  });
		System.out.println(reducedParams);
		
		reducedParams = Stream.of(1, 2, 3).parallel()
			  .reduce(10, (x, y) -> x + y
//			  						,(x, y) -> {
//										     System.out.println("combiner was called");
//										     return x + y;
//										  }
			  					);
		System.out.println(reducedParams);
		
		
		List<Product> productList = Arrays.asList(sObj.new Product(23, "potatoes"),
				sObj.new Product(14, "orange"), sObj.new Product(13, "lemon"),
				sObj.new Product(23, "bread"), sObj.new Product(13, "sugar"));
		
		List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());
		System.out.println(collectorCollection);
		String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));
		System.out.println(listToString);
		
		double averagePrice = productList.stream().collect(Collectors.averagingInt(Product::getPrice));
		System.out.println(averagePrice);
		
		int summingPrice = productList.stream().collect(Collectors.summingInt(Product::getPrice));
		System.out.println(summingPrice);
		
		IntSummaryStatistics statistics  = productList.stream().collect(Collectors.summarizingInt(Product::getPrice));
		System.out.println(statistics.getMax());
		System.out.println(statistics);
		
		Map<Integer, List<Product>> collectorMapOfLists = productList.stream().collect(Collectors.groupingBy(Product::getPrice));
		System.out.println(collectorMapOfLists);
		
		Map<Boolean, List<Product>> mapPartioned = productList.stream().collect(Collectors.partitioningBy(element -> element.getPrice() > 15));
		System.out.println(mapPartioned);
		
		Collector<Product, ?, LinkedList<Product>> toLinkedList =
			  Collector.of(LinkedList::new, LinkedList::add /*igual a "(m,n) -> m.add(n)" */, 
			    (first, second) -> { 
			       first.addAll(second); 
			       return first; 
			    });
		LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList);
		System.out.println(linkedListOfPersons);
		
		
		
		Stream.of("abcd", "bbcd", "cbcd").skip(1).filter(i -> { System.out.println("filter " + i); return i.contains("b");}).close(); //.forEach(System.out::println);
		
	}
	
	
	public Stream<String> streamOf(List<String> list) {
    return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}
	
	
	public void doWork(String element) {
		System.out.println("doing work for element : " + element + "...");
	}
	
	
	class Product {
		private int price;
		private String name;
		
		public Product(int price, String name) {
			this.price = price;
			this.name = name;
		}
		
		public int getPrice() {
			return price;
		}
		
		public void setPrice(int price) {
			this.price = price;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return getName();
		}
	}
}
