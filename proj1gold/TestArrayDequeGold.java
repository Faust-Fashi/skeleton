import static org.junit.Assert.*;
import org.junit.Test;

/*2022/11/19 11:35
 * by IDEA
 */
public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> stuDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solDeque = new ArrayDequeSolution<>();
        String log = "";
        Integer solNum = 1, stuNum = 1;
        for (int i = 0; i < 200; i++) {
            if (solDeque.size() == 0) { // Empty
                int addNum = StdRandom.uniform(1000);
                int firstOrLast = StdRandom.uniform(2);
                if (firstOrLast == 0) { // addFirst
                    log = log + "addFirst(" + addNum + ")\n";
                    solDeque.addFirst(addNum);
                    stuDeque.addFirst(addNum);
                } else {
                    log = log + "addLast(" + addNum + ")\n";
                    solDeque.addLast(addNum);
                    stuDeque.addLast(addNum);
                }
            } else {
                int condition = StdRandom.uniform(4);
                int addNum = StdRandom.uniform(1000);
                stuNum = solNum;
                switch (condition) {
                    case 0:
                        log = log + "addFirst(" + addNum + ")\n";
                        solDeque.addFirst(addNum);
                        stuDeque.addFirst(addNum);
                        break;
                    case 1:
                        log = log + "addLast(" + addNum + ")\n";
                        solDeque.addLast(addNum);
                        stuDeque.addLast(addNum);
                        break;
                    case 2:
                        log = log + "removeFirst()\n";
                        solNum = solDeque.removeFirst();
                        stuNum = stuDeque.removeFirst();
                        break;
                    case 3:
                        log = log + "removeLast()\n";
                        solNum = solDeque.removeLast();
                        stuNum = stuDeque.removeLast();
                        break;
                    default:
                        break;
                }
                assertEquals(log, solNum, stuNum);
                // sth like  expected:<707> but was:<774>
                // will be automatically added to the screen
            }
        }

    }
}
