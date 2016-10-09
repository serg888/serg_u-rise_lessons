package com.urise.webapp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.SynchronousQueue;

/**
 * com.urise.webapp.model.com.urise.webapp.model.Resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private Contacts contacts;
    private Sections sections;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
        contacts = new Contacts();
        sections = new Sections();
    }

    public void saveAllContact() {
        contacts.saveAllContents();
    }

    public void updateContact(String contact) {
        contacts.updateContent(contact);
    }

    public void printAllContacts() {
        contacts.printAllContents();
    }

    public void printContact(String contact) {
        contacts.printContent(contact);
    }

    public void saveAllSection() {
        sections.saveAllContents();
    }

    public void updateSection(String section) {
        sections.updateContent(section);
    }

    public void printAllSections() {
        sections.printAllContents();
    }

    public void printSection(String section) {
        sections.printContent(section);
    }


    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return uuid + " (" + fullName + ")";
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.getFullName());
        return (cmp == 0) ? uuid.compareTo(o.getUuid()) : cmp;
    }

    private abstract class AbstractContent {
        Map<SelectionType, ContentStorage> map = new HashMap<SelectionType, ContentStorage>();
        String title;

        AbstractContent(String title) {
            this.title = title;
        }

        void saveContent(SelectionType ec) {
            map.get(ec).saveInfo();
        }

        void saveAllContents() {
            for (Map.Entry<SelectionType, ContentStorage> m : map.entrySet()) {
                SelectionType ec = m.getKey();
                System.out.println("Введите " + ec.getTitle() + ":");
                saveContent(ec);
            }
        }

        void updateContent(String content) {
            SelectionType ec = SelectionType.valueOf(content);
            System.out.println("Прежнее значение поля " + ec.getTitle() + " в " + title);
            printContent(content);
            System.out.println("Задайте новое значение...:");
            saveContent(ec);
        }

        void printContent(String content) {
            SelectionType ec = SelectionType.valueOf(content);
            System.out.println(ec.getTitle() + ":");
            map.get(ec).printInfo();
        }

        void printAllContents() {
            for (Map.Entry<SelectionType, ContentStorage> m : map.entrySet()) {
                printContent(m.getKey().toString());
            }
        }

    }

    private class Contacts extends AbstractContent {

        Contacts() {
            super("КОНТАКТЫ");
            map.put(SelectionType.LIVING, new OneString());
            map.put(SelectionType.EMAIL, new OneString());
            map.put(SelectionType.SKYPE, new OneString());
            map.put(SelectionType.ACCOUNTS, new ListOfString());
        }
    }

    private class Sections extends AbstractContent {

        Sections() {
            super("СЕКЦИИ");
            map.put(SelectionType.PERSONAL, new OneString());
            map.put(SelectionType.OBJECTIVE, new OneString());
            map.put(SelectionType.ACHIEVEMENT, new ListOfString());
            map.put(SelectionType.QUALIFICATIONS, new ListOfString());
            map.put(SelectionType.EXPERIENCE, new OneString());
            map.put(SelectionType.EDUCATION, new OneString());
        }

    }

    private class OneString implements ContentStorage<String> {
        private String text;

        @Override
        public void saveInfo() {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                text = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getInfo() {
            return text;
        }

        @Override
        public void printInfo() {
            System.out.println(text);
        }
    }

    private class ListOfString implements ContentStorage<List<String>> {
        private List<String> list = new ArrayList<>();
        private String s;

        @Override
        public void saveInfo() {
            System.out.println("Вводите данные, exit-для выхода ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (!(s = br.readLine()).equals("exit")) {
                    list.add(s);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public List<String> getInfo() {
            return list;
        }

        @Override
        public void printInfo() {
            for (String s : list) {
                System.out.println(s);
            }
        }

    }

    private interface ContentStorage<T> {
        void saveInfo();

        T getInfo();

        void printInfo();
    }
}
