package week2.setDuplicates;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetDuplicates {
    @SafeVarargs
    public static Set<Integer> getDuplicatesFromSets(Set<Integer> ... arr) {
        Set<Integer> result = new HashSet<>();
        if (arr != null) {
            Set<Integer> setOne = arr[0];
            boolean flag = true;
            for (Integer elem : setOne) {
                flag = true;
                for (int i = 1; i < arr.length && flag; i++) {
                    if (!arr[i].contains((Integer) elem)) {
                        flag = false;
                    } if(flag && i == arr.length -1){
                        result.add(elem);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> b = new HashSet<>(Arrays.asList(4, 5, 6));
        Set<Integer> c = new HashSet<>(Arrays.asList(5, 6, 7, 8));

        System.out.println(SetDuplicates.getDuplicatesFromSets(a, b, c));
    }
}
