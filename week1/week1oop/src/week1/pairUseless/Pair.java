package week1.pairUseless;

public final class Pair {
    private final Object first;
    private final Object second;

    private Pair(Object o1, Object o2) {
        first = o1;
        second = o2;
    }

    public static Pair createPair(Object o1, Object o2) {
        return new Pair(o1, o2);
    }

    //ne trqbva da vru6tame par4eta ot na6iq obekt
    public Object getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }

    public String toString() {
        return String.format("<" + first + "," + second + ">");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        Pair other = (Pair) obj;
        if (first == null) {
            if (other.first != null)
                return false;
        } else if (!first.equals(other.first))
            return false;
        if (second == null) {
            if (other.second != null)
                return false;
        } else if (!second.equals(other.second))
            return false;
        return true;
    }
    
    

    public static void main(String[] args) {
        System.out.println(Pair.createPair(1, "sad"));
    }
}
