package production;


import view.IObserver;

import java.util.ArrayList;

public interface IInfoObserver extends IObserver<String, Integer> {
    void update(ArrayList<Tuple<String, Integer>> information);
}
