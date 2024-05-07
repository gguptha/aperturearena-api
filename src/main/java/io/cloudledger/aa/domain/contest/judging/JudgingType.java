package io.cloudledger.aa.domain.contest.judging;

public enum JudgingType {

    Offline("Offline"),
    Remote("Remote");

    private final String code;

    JudgingType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
