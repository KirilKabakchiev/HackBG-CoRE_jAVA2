package spliterator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

public class SlidingWindowSpliterator implements Spliterator<String>{

	int shiftValue;
	Collection<String> collection;
	
	public SlidingWindowSpliterator(Collection<String> c, int sV) {
		shiftValue = sV;
		collection = c;
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super String> action) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Spliterator<String> trySplit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long estimateSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int characteristics() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) {
		//Collection<String> testCollection = Arrays.asList("This","is","a","lame","example");
		//Spliterator<String> yourSpliterator = new SlidingWindowSpliterator(testCollection, 2) //2 is the sliding window length
		//StreamSupport.stream(null).forEach(System.out::println);
		//yourSpliteratorObject
	}

	
}
