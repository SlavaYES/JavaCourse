import java.util.ArrayList;
import java.util.Collection;

public class Statistic<T extends User> {
    private static Integer min, max;
    private static Double middle;
    private static Integer sum = 0;

    public Statistic(Collection<T> arr) {
        min = 101; max = -1;
        for (T element : arr) {
            if (min > element.kpi) {
                min = element.kpi;
            }
            if (max < element.kpi) {
                max = element.kpi;
            }
            sum += element.kpi;
        }
        middle = (double)sum / arr.size();
    }
    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    public Double getMiddle() {
        return middle;
    }
}
