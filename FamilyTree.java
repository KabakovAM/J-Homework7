package Homework7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FamilyTree {
    private String lastName;
    private String firstName;
    private String secondName;
    private String birthday;
    private String sex;
    private Integer personalId;
    private Integer fatherId;
    private Integer motherId;
    private static ArrayList<FamilyTree> family = new ArrayList<FamilyTree>();

    public FamilyTree(String lastName, String firstName, String secondName, String birthday, String sex,
            Integer personalId,
            Integer fatherId, Integer motherId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.sex = sex;
        this.personalId = personalId;
        this.fatherId = fatherId;
        this.motherId = motherId;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + secondName + ", " + birthday;
    }

    public FamilyTree addToList(ArrayList<FamilyTree> familyList) {
        familyList.add(this);
        return this;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public String getSexId() {
        return sex;
    }

    public static void printAll() {
        for (FamilyTree person : family) {
            System.out.println(person);
        }
    }

    public void printParent() {
        for (FamilyTree person : family) {
            if (person.getPersonalId() == fatherId) {
                System.out.println("Отец:");
                System.out.println(person);
            } else if (person.getPersonalId() == motherId) {
                System.out.println("Мать:");
                System.out.println(person);
            }
        }
    }

    public void printBrotherSister() {
        for (FamilyTree person : family) {
            if (person.getFatherId() == fatherId && person.getPersonalId() != personalId) {
                if (person.getSexId() == "м") {
                    System.out.println("Брат:");
                    System.out.println(person);
                } else {
                    System.out.println("Сестра:");
                    System.out.println(person);
                }
            }
        }
    }

    public void printChildrens() {
        for (FamilyTree person : family) {
            if (person.getFatherId() == personalId) {
                if (person.getSexId() == "м") {
                    System.out.println("Сын:");
                    System.out.println(person);
                } else {
                    System.out.println("Дочь:");
                    System.out.println(person);
                }
            }
        }
    }

    public void printGrandParent() {
        for (FamilyTree person : family) {
            if (person.getPersonalId() == fatherId || person.getPersonalId() == motherId) {
                for (FamilyTree person2 : family) {
                    if (person.getFatherId() == person2.getPersonalId()) {
                        System.out.println("Дедушка:");
                        System.out.println(person2);
                    } else if (person.getMotherId() == person2.getPersonalId()) {
                        System.out.println("Бабушка:");
                        System.out.println(person2);
                    }
                }
            }
        }
    }

    public static void familyTreeToFile() {
        try (FileWriter writer = new FileWriter("tree.txt", true)) {
            for (FamilyTree person : family) {
                writer.write(person.toString() + "\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {

        FamilyTree person1 = new FamilyTree("Иванов", "Алексей", "Иванович", "25.10.1995", "м", 1, 2, 3)
                .addToList(family);
        FamilyTree person2 = new FamilyTree("Иванов", "Антон", "Иванович",
                "02.09.1993", "м", 4, 2, 3).addToList(family);
        FamilyTree person3 = new FamilyTree("Иванов", "Иван", "Борисович", "09.04.1975", "м", 2, 5, 6)
                .addToList(family);
        FamilyTree person4 = new FamilyTree("Иванова", "Ольга", "Олеговна",
                "16.07.1977", "ж", 3, 7, 8).addToList(family);
        FamilyTree person5 = new FamilyTree("Иванов", "Борис", "Евгеньевич", "13.05.1944", "м", 5, 9, 10)
                .addToList(family);
        FamilyTree person6 = new FamilyTree("Иванова", "Ангелина", "Никитична",
                "05.01.1939", "ж", 6, 11, 12).addToList(family);
        FamilyTree person7 = new FamilyTree("Иванов", "Олег", "Витальевич", "09.04.1936", "м", 7, 13, 14)
                .addToList(family);
        FamilyTree person8 = new FamilyTree("Иванова", "Кристина", "Александровна",
                "14.07.1938", "ж", 8, 15, 16).addToList(family);
        person3.printParent();
        person1.printBrotherSister();
        person6.printChildrens();
        person2.printGrandParent();
        person4.printChildrens();
        person5.printChildrens();
        person7.printChildrens();
        person8.printChildrens();
        printAll();
        familyTreeToFile();
    }
}
