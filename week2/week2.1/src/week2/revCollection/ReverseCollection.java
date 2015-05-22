package week2.revCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class ReverseCollection {

    static <T> void reverse(Collection<T> collection) {
        T[] array = (T[]) collection.toArray();
        
        Collections.reverse(Arrays.asList(array));
        collection.clear();
        
        for(int i = 0; i < array.length; i++){
            collection.add(array[i]);
        }
    }
    
    public static void main(String[] args) {
        ArrayList<Integer> set = new ArrayList<>();
        set.add(new Integer(5));
        set.add(new Integer(3));
        set.add(new Integer(2));
        set.add(new Integer(1));
        System.out.println(set);
        ReverseCollection.reverse(set);
        System.out.println(set);
    }
}
