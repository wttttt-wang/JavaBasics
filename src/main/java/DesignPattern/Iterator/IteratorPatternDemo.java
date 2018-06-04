package DesignPattern.Iterator;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-04
 * Time: 17:40
 */
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();

        for (IteratorBase itr = nameRepository.getIterator(); itr.hasNext();) {
            System.out.println("Name : " + itr.next());
        }
    }
}


interface IteratorBase {
    public boolean hasNext();
    public Object next();
}

interface Container {
    public IteratorBase getIterator();
}


// concrete container: 该类含有实现了Iterator的NameIterator。
class NameRepository implements Container {
    public String[] names = {"Robert" , "John" ,"Julie" , "Lora"};

    @Override
    public IteratorBase getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements IteratorBase{
        int index;

        @Override
        public Object next() {
            if (this.hasNext()) return names[index++];
            return null;
        }

        @Override
        public boolean hasNext() {
            return index < names.length;
        }
    }
}
