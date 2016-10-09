package com.urise.webapp.model;

/**
 * Created by Сергей on 08.10.2016.
 */
public enum SelectionType {
    //блок для Контактов
    LIVING("Проживание"),
    EMAIL("Mail"),
    SKYPE("Skype"),
    ACCOUNTS("Аккаунты"),

    //блок для Секций
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;
    private SelectionType(String s){
        title=s;
    }

    public String getTitle() {
        return title;
    }
}
