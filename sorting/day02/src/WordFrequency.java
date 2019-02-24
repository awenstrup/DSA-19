public class WordFrequency implements Comparable{
    String word;
    int freq;

    public WordFrequency(String w, int f) {
        word = w;
        freq = f;
    }

    @Override
    public int compareTo(Object o) {
        return freq - ((WordFrequency)o).freq;
    }
}