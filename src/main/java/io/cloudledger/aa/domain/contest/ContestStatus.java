package io.cloudledger.aa.domain.contest;

public enum ContestStatus {

    Open("Open"),
    Closed("Closed"),
    ReviewInProgress("ReviewInProgress");

    private final String code;

    ContestStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
