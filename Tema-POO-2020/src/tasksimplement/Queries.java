package tasksimplement;

import fileio.ActionInputData;
import fileio.ActorInputData;

import java.util.List;

public final class Queries {
    private Queries() { }

    /**
     *
     */
    public static void average(final List<ActorInputData> actors,
                        final ActionInputData action) {
        for (int i = 1; i <= action.getNumber(); i++) {
            if (action.getSortType() == "asc")  { //daca este crescator
            } else {   //daca este descrescators

            }
        }
    }
}
