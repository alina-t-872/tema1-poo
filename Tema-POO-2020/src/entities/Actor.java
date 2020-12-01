package entities;

import actor.ActorsAwards;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.Map;

public final class Actor {
    /**
     * The class fields
     */
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;

    /**
     *Constructor for the class fields
     */
    public Actor(final ActorInputData actorInputData) {
        this.name = actorInputData.getName();
        this.careerDescription = actorInputData.getCareerDescription();
        this.filmography = actorInputData.getFilmography();
        this.awards = actorInputData.getAwards();
    }

    /**
     * Getters & Setters for the class fields because they are private
     */
    public String getName() {
        return name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    /**
     * Overriding the method toString to print the class fields
     */
    @Override
    public String toString() {
        return "Actor{"
                + "name='" + name + '\''
                + ", careerDescription='" + careerDescription + '\''
                + ", filmography=" + filmography
                + ", awards=" + awards
                + '}';
    }
}
