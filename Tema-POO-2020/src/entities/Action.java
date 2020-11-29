package entities;

import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Action {
    private final int actionId;
    private final String actionType;
    private final String type;
    private final String username;
    private final String objectType;
    private final String sortType;
    private final String criteria;
    private final String title;
    private final String genre;
    private final int number;
    private final double grade;
    private final int seasonNumber;
    private final List<List<String>> filters = new ArrayList<>();

    public Action(final ActionInputData action) {
        this.actionId = action.getActionId();
        this.actionType = action.getActionType();
        this.type = action.getType();
        this.username = action.getUsername();
        this.objectType = action.getObjectType();
        this.sortType = action.getSortType();
        this.criteria = action.getCriteria();
        this.title = action.getTitle();
        this.genre = action.getGenre();
        this.number = action.getNumber();
        this.grade = action.getGrade();
        this.seasonNumber = action.getSeasonNumber();
    }
    public Action(final ActionInputData actionData, final String year,
                      final List<String> words, final List<String> awards,
                      final String genre) {
        this.actionId = actionData.getActionId();
        this.actionType = actionData.getActionType();
        this.objectType = actionData.getObjectType();
        this.sortType = actionData.getSortType();
        this.criteria = actionData.getCriteria();
        this.number = actionData.getNumber();
        this.filters.add(new ArrayList<>(Collections.singleton(year)));
        this.filters.add(new ArrayList<>(Collections.singleton(genre)));
        this.filters.add(words);
        this.filters.add(awards);
        this.title = null;
        this.type = null;
        this.username = null;
        this.genre = null;
        this.grade = 0;
        this.seasonNumber = 0;
    }

    public int getActionId() {
        return actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getSortType() {
        return sortType;
    }

    public String getCriteria() {
        return criteria;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumber() {
        return number;
    }

    public double getGrade() {
        return grade;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public List<List<String>> getFilters() {
        return filters;
    }

    @Override
    public String toString() {
        return "Action{"
                + "actionId=" + actionId
                + ", actionType='" + actionType + '\''
                + ", type='" + type + '\''
                + ", username='" + username + '\''
                + ", objectType='" + objectType + '\''
                + ", sortType='" + sortType + '\''
                + ", criteria='" + criteria + '\''
                + ", title='" + title + '\''
                + ", genre='" + genre + '\''
                + ", number=" + number
                + ", grade=" + grade
                + ", seasonNumber=" + seasonNumber
                + ", filters=" + filters
                + '}';
    }
}
