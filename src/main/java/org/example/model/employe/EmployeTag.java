package org.example.model.employe;

public enum EmployeTag {
    PROGRAMMEUR("P"),
    CHEF_PROJET("CP");

    private final String code;

    EmployeTag(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EmployeTag fromCode(String code) {
        for (EmployeTag tag : values()) {
            if (tag.code.equalsIgnoreCase(code)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("Unknown code_metier: " + code);
    }
}

