package week2.rotateElements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class rotateCollection {
    public static void rotate(Collection<Integer> collection, int rotateStep) {
        Integer[] arr = new Integer[collection.size()];
        collection.toArray(arr);
        if (rotateStep > 0) {

            reverse(arr, 0, arr.length - 1);
            reverse(arr, 0, rotateStep - 1);
            reverse(arr, rotateStep, arr.length - 1);
        } else if (rotateStep < 0) {
            rotateStep = - rotateStep;
            reverse(arr, 0, rotateStep - 1);
            reverse(arr, rotateStep, arr.length - 1);
            reverse(arr, 0, arr.length - 1);
        }

        collection.clear();
        collection.addAll(Arrays.asList(arr));
    }

    public static void reverse(Integer[] data, int left, int right) {

        while (left < right) {
            // swap the values at the left and right indices
            int temp = data[left];
            data[left] = data[right];
            data[right] = temp;

            // move the left and right index pointers in toward the center
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Collection<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        rotateCollection.rotate(list, 2);
        System.out.println(list.toString());
        rotateCollection.rotate(list, -2);
        System.out.println(list.toString());

    }
}
