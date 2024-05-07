package io.cloudledger.aa.domain.contest.section;

public enum SectionType {

    Digital("Digital"),
    Print("Print");

    private final String code;

    SectionType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
