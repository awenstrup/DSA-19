public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
        if(size == elems.length) {
            Cow[] temp = new Cow[elems.length * 2];
            for(int i = 0; i < size; i++)
                temp[i] = elems[i];
            elems = temp;
        }
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if(elems[index] == null)
            throw new IndexOutOfBoundsException();
        return elems[index];
    }

    // TODO: Runtime: O(N)
    public Cow remove(int index) {
        if(elems[index] == null)
            throw new IndexOutOfBoundsException();

        Cow[] temp;

        if(size < elems.length/4)
            temp = new Cow[elems.length/2];
        else
            temp = new Cow[elems.length];

        for(int i = 0; i < index; i++)
            temp[i] = elems[i];

        for(int i = index + 1; i < size; i++)
            temp[i - 1] = elems[i];

        Cow cow = elems[index];
        elems = temp;
        size--;

        return cow;
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if(index > size)
            throw new IndexOutOfBoundsException();

        Cow[] temp;

        if(size == elems.length)
            temp = new Cow[elems.length * 2];
        else
            temp = new Cow[elems.length];

        for(int i = 0; i < index; i++)
            temp[i] = elems[i];

        temp[index] = c;
        size++;

        for(int i = index + 1; i < size; i++)
            temp[i] = elems[i-1];

        elems = temp;
    }
}