package cmdVersion.game.stats;

import cmdVersion.questionFactory.Question;

public class GameStats {

    Integer guessAmount;
    Integer guessCorrect;
    Integer guessCorrectStreak;
    double guessAccuracy;

    Question question;
    boolean latestQuestionAnsweredCorrect;

    // Getter START
    public Integer getGuessAmount() {
        return guessAmount;
    }

    public Integer getGuessCorrect() {
        return guessCorrect;
    }

    public Integer getGuessCorrectStreak() {
        return guessCorrectStreak;
    }

    public double getGuessAccuracy() {
        return guessAccuracy;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean isLatestQuestionAnsweredCorrectly() {
        return latestQuestionAnsweredCorrect;
    }
    // Getter END

    // Setter START
    public void setQuestion(Question question) {
        this.question = question;
    }

    // Setter END

    public void answeredCorrect(){
        this.latestQuestionAnsweredCorrect = true;
        this.guessAmount++;
        this.guessCorrect++;
        this.guessCorrectStreak++;
        this.guessAccuracy = ((double)this.guessCorrect)/this.guessAmount;
    }

    public void answeredWrong(){
        this.latestQuestionAnsweredCorrect = false;
        this.guessAmount++;
        this.guessCorrectStreak = 0;
        this.guessAccuracy = ((double)this.guessCorrect)/this.guessAmount;
    }

}
