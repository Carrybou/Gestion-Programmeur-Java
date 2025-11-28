package org.example.model.Employe;

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
}
