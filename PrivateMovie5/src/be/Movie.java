package be;

public class Movie {

    private int id;
    private String title;
    private double rating;
    private String fileLink;
    private double personalRating;

    public Movie(int id, String title, double rating, String fileLink, double personalRating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.fileLink = fileLink;
        this.personalRating = personalRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public double getPersonalRating() {
        return personalRating;
    }

    public void setPersonalRating(double personalRating) {
        this.personalRating = personalRating;
    }

    @Override
    public String toString() {
        return id + " " + title + " " + rating + " " + fileLink + " " + personalRating;
    }
}
