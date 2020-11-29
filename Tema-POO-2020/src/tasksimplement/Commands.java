package tasksimplement;

import entities.Action;
import entities.Serial;
import entities.User;
import fileio.ActionInputData;
import fileio.ShowInput;
import fileio.UserInputData;

public final class Commands {
    private Commands() { }


    /**
     *
     */
    public static void view(final ShowInput video, final UserInputData user) {
            if (user.getHistory().containsKey(video.getTitle())) {
                user.getHistory().put(video.getTitle(),
                                  user.getHistory().get(video.getTitle() + 1));
            } else {
                user.getHistory().put(video.getTitle(), 1);
            }
    }

    /**
     *
     */
    public static double setSeasonRating(final Serial serial,
                                      final Action action) {
        int size = serial.getNumberOfSeasons();
        for (int i = 1; i <= size; i++) {
            if(i == action.getSeasonNumber()) {
                serial.getSeasons().get(i-1).getRatings().add(action.getGrade());
            }
        }
        return action.getGrade();
    }

    public static double calcRating(final Serial serial) {
        double sumSerial = 0;

        for (int i = 0; i < serial.getNumberOfSeasons(); i++) {
            int sizeSeason = serial.getSeasons().get(i).getRatings().size();
            double sumSeason = 0;
            for (int j = 0; j < sizeSeason; j++) {
                double seasonRating = serial.getSeasons().get(i).getRatings().get(j);
                sumSeason = sumSeason + seasonRating;
            }
            sumSeason = sumSeason / sizeSeason;
            sumSerial = sumSerial + sumSeason;
        }

        sumSerial = sumSerial / serial.getNumberOfSeasons();
        serial.setGeneralRating(sumSerial);
        return sumSerial;
    }

}
