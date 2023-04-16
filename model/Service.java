package model;

import model.comparator.HumanComparatorByBirthday;
import model.comparator.HumanComparatorById;
import model.comparator.HumanComparatorByName;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {
    private FamilyTree<Human> familyTree;
    private FileHandler fileHandler;

    public Service(FamilyTree<Human> familyTree, FileHandler fileHandler) {
        this.familyTree = familyTree;
        this.fileHandler = fileHandler;
    }

    public String getFileName() {
        return fileHandler.getFileName();
    }

    public String getFileType() {
        return fileHandler.getFileType();
    }

    public void addHuman(Human human) {
        familyTree.getFamilyTree().add(human);
        if (human.getMother() != null) {
            human.getMother().addChild(human);
        }
        if (human.getFather() != null) {
            human.getFather().addChild(human);
        }
    }

    public Human getHumanById(int id) {
        for (Human human : familyTree.getFamilyTree()) {
            if (human.getId() == id) {
                return human;
            }
        }
        return null;
    }

    public Human getHumanByName(String name) {
        String nameFull = name.replace(",", " ");
        for (Human human : familyTree.getFamilyTree()) {
            if (human.getFullName().equals(nameFull)) {
                return human;
            }
        }
        return null;
    }

    public void printChildren() {
        System.out.println("Список генеалогического древа с указанием наличия (отсутствия) детей:");
        int index = 1;
        for (Human humanTemp : familyTree.getFamilyTree()) {
            if (humanTemp.getChildren().isEmpty()) {
                System.out.printf("%d)%s (%d) не имеет детей!\n ", index++, humanTemp.getFullName(), humanTemp.getBirthYear());
                System.out.println();
            } else {
                System.out.printf("%d)%s (%d) имеет следующих детей:\n %s\n", index++, humanTemp.getFullName(), humanTemp.getBirthYear(), humanTemp.getChildren());
                System.out.println();
            }
        }
    }

    public void print() {
        System.out.println("Список родственников древа состоит из:");
        while (familyTree.hasNext()) {
            System.out.println(familyTree.next());
        }
    }

    public int getLastId() {
        if (familyTree.getFamilyTree() == null){
            return -1;
        }
        return familyTree.getFamilyTree().size() - 1;
    }

    public Gender getGender(String g) {
        if (g.equals("м")) {
            return Gender.male;
        } else if (g.equals("ж")){
            return Gender.female;
        }
        return null;
    }

    public void createInitialTree() {
        int id = 0;
        Human h_1 = new Human(id++, "Кузнецов Иван Иванович", Gender.male, 1874);
        Human h_2 = new Human(id++, "Кузнецова Евдокия Петровна", Gender.female, 1881);
        Human h_3 = new Human(id++, "Грищенко Мария Ивановна", Gender.female, h_1, h_2, 1910);
        Human h_4 = new Human(id++, "Кузнецов Петр Иванович", Gender.male, h_1, h_2, 1924);
        Human h_5 = new Human(id++, "Грищенко Афонасий Ермолаевич", Gender.male, 1915);
        Human h_6 = new Human(id++, "Богданова Зоя Афонасьевна", Gender.female, h_5, h_3, 1937);
        Human h_7 = new Human(id++, "Грищенко Александр Афонасьевич", Gender.male, h_5, h_3, 1940);
        Human h_8 = new Human(id, "Кузнецов Андрей Иванович", Gender.male, h_1, h_2, 1918);
        List<Human> humans = new ArrayList<>(Arrays.asList(h_1, h_2, h_3, h_4, h_5, h_6, h_7, h_8 ));

        for (Human human : humans) {
            familyTree.getFamilyTree().add(human);
            if (human.getMother() != null) {
                human.getMother().addChild(human);
            }
            if (human.getFather() != null) {
                human.getFather().addChild(human);
            }
        }
    }

    public void writeTreeInFile() throws IOException {
        fileHandler.writeTreeInFile(familyTree);
    }

    public void readFromFile() throws IOException, ClassNotFoundException {
        this.familyTree = fileHandler.readFromFile();
    }

    public void sortByName() {
        familyTree.getFamilyTree().sort(new HumanComparatorByName());
    }

    public void sortByBirthYear() {
        familyTree.getFamilyTree().sort(new HumanComparatorByBirthday());
    }

    public void sortById() {
        familyTree.getFamilyTree().sort(new HumanComparatorById());
    }
}
