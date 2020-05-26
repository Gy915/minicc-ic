package bit.minisys.minicc.icgen;
import java.util.LinkedList;

class Entry{
    String name;
    int D;
    int add;
}
public class Order_Table {
    LinkedList<Entry> entries = new LinkedList<>();

    // 填写未知项
    public void add_unsure(String name, int add){
        Entry e = new Entry();
        e.add = add;
        e.D = 0;
        e.name = name;
    }

    // 给出回填项的名以及地址，将其D改为1，其地址改为add
    public void Back_fill(String name, int add){
        for(Entry e: this.entries){
            if(e.name.equals(name)){
                e.D = 1;
                e.add = add;
            }
        }
    }

}
