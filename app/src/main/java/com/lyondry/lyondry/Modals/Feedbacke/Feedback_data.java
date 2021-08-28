package com.lyondry.lyondry.Modals.Feedbacke;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feedback_data {
    @SerializedName("FeedbackId")
    @Expose
    private Integer feedbackId;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }
}
