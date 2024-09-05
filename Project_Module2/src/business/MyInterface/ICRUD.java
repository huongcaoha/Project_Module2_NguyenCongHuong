package business.MyInterface;

import java.util.List;

public interface ICRUD<T> {
    void displayList(List<T> list);
    boolean add();
    boolean update();
    boolean delete();
}
