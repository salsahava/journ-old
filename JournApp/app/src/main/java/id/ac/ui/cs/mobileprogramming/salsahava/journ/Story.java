package id.ac.ui.cs.mobileprogramming.salsahava.journ;

public class Story {
    private String title;
    private String date;
    private String story;

    public Story(String title, String date, String story) {
        this.title = title;
        this.date = date;
        this.story = story;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getStory() {
        return story;
    }
}
