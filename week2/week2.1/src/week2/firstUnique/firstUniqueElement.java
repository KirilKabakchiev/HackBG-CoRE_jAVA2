package week2.firstUnique;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class firstUniqueElement {
    private static Integer countOfElement(Integer elem, Collection<Integer> collection) {
        Iterator<Integer> iter = collection.iterator();
        int count = 0;
        while (iter.hasNext()) {
            if (iter.next().equals(elem)) {
                count++;
            }
        }
        return count;
    }

    public static Integer firstUnique(Collection<Integer> collection) {
        Integer a = collection.stream().filter(x -> countOfElement(x, collection) <= 1).findFirst().get();
        return a;
    }
    
    public static void main(String[] args) {
        Collection<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(5);
        list.add(6);
        System.out.println(firstUnique(list));
    }
}
