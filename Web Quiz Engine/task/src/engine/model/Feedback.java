package engine.model;

public final class Feedback {
    private boolean success;
    private String feedback;

    public static final Feedback CORRECT = new Feedback(true, "Congratulations, you're right!");
    public static final Feedback INCORRECT = new Feedback(false, "Wrong answer! Please, try again.");


    public Feedback() {
    }

    public Feedback(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
