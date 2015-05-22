package week2.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IntStatistics implements Statistics, Iterable<Integer> {
    private List<Integer> numbers;

    public IntStatistics(List<Integer> numbers) {
        this.numbers = numbers;

    }

    public IntStatistics() {
        numbers = new ArrayList<Integer>();
    }

    public void add(int number) {
        numbers.add(number);
    }

    @Override
    public Double getMean() {
        int sum = 0;
        for (Integer integer : numbers) {
            sum += integer;
        }
        return (double) sum / numbers.size();
    }

    @Override
    public Double getMedian() {
        Collections.sort(numbers);
        int size = numbers.size();
        if (size % 2 == 0) {
            return (double) (numbers.get(size / 2 - 1) + numbers.get(size / 2)) / 2;
        } else {
            return (double) numbers.get(size / 2);
        }
    }

    @Override
    public Double getMode() {
        Double maxValue = 0.0;
        int maxCount = 0;

        for (int i = 0; i < numbers.size(); ++i) {
            int count = 0;
            for (int j = 0; j < numbers.size(); ++j) {
                if (numbers.get(j).equals(numbers.get(i)))
                    ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = (double)numbers.get(i);
            }
        }

        return maxValue;
    }

    @Override
    public Double getRange() {
        return new Double(Collections.max(this.numbers) - Collections.min(this.numbers));
    }

    @Override
    public Iterator<Integer> iterator() {
        return numbers.iterator();
    }
}