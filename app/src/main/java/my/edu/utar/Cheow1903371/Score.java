package my.edu.utar.Cheow1903371;


public class Score implements Comparable<Score> {
    private String mName;
    private int mScore;

    public Score(String name, int score) {
        mName = name;
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public int getScore() {
        return mScore;
    }

    @Override
    public int compareTo(Score other) {
        return Integer.compare(other.mScore, this.mScore); // Sort in descending order
    }

    @Override
    public String toString() {
        return mName + ": " + mScore;
    }

}

