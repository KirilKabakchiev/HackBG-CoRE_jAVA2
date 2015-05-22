package week1.stack;

import java.util.Arrays;

public class StackImpl implements Stackk {
    private int maxSize;
    private long[] stackArray;
    private int top;

    public StackImpl(int s) {
        maxSize = s;
        stackArray = new long[maxSize];
        top = -1;
    }

    @Override
    public void push(long j) {
        if (Arrays.stream(stackArray).filter(x -> x == j).count() != 0) {
            System.out.println("Element " + j + " cannot be pushed. It's already in the stack.");
        } else {
            stackArray[++top] = j;
        }
    }

    @Override
    public long pop() {
        return stackArray[top--];
    }

    @Override
    public long peek() {
        return stackArray[top];
    }

    @Override
    public boolean isEmpty() {
        return (top == -1);
    }

    @Override
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    @Override
    public int count() {
        return stackArray.length;
    }

    public static void main(String[] args) {
        StackImpl theStack = new StackImpl(10);
        theStack.push(10);
        theStack.push(20);
        theStack.push(30);
        theStack.push(40);
        theStack.push(40);
        theStack.push(50);
        while (!theStack.isEmpty()) {
            long value = theStack.pop();
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.println("");
    }
}
