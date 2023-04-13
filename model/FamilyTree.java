package model;

import model.comparator.HumanComparatorByBirthday;
import model.comparator.HumanComparatorById;
import model.comparator.HumanComparatorByName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FamilyTree<H extends Human> implements Serializable, FamilyTreeIterator<H> {
    private int id;
    private List<H> familyTree;

    public int getLastId(){
        return this.familyTree.size() - 1;
    }

    public FamilyTree() {
        this.familyTree = new ArrayList<H>();
    }

    public void createInitialFamilyTree() {
        Human h_1 = new Human(this.id++, "Кузнецов Иван Иванович", Gender.male, 1874);
        Human h_2 = new Human(this.id++, "Кузнецова Евдокия Петровна", Gender.female, 1881);
        Human h_3 = new Human(this.id++, "Грищенко Мария Ивановна", Gender.female, h_1, h_2, 1910);
        Human h_4 = new Human(this.id++, "Кузнецов Петр Иванович", Gender.male, h_1, h_2, 1924);
        Human h_5 = new Human(this.id++, "Грищенко Афонасий Ермолаевич", Gender.male, 1915);
        Human h_6 = new Human(this.id++, "Богданова Зоя Афонасьевна", Gender.female, h_5, h_3, 1937);
        Human h_7 = new Human(this.id++, "Грищенко Александр Афонасьевич", Gender.male, h_5, h_3, 1940);
        List<Human> humans = new ArrayList<>(Arrays.asList(h_1, h_2, h_3, h_4, h_5, h_6, h_7));

        FamilyTree familyTree_1 = new FamilyTree();
        for (Human human : humans) {
            familyTree_1.addHuman(human);
        }
        this.familyTree = familyTree_1.getFamilyTree();
    }

    public void addHuman(H human) {
        this.familyTree.add(human);
        if (human.getMother() != null) {
            human.getMother().addChild(human);
        }
        if (human.getFather() != null) {
            human.getFather().addChild(human);
        }
    }

    public void getInfoAboutFamilyTree() {
        System.out.println("Список родственников древа состоит из:");
        for (Human human: familyTree) {
            System.out.println(human);
        }
    }

    public Gender getGender(String g) {
        if (g.equals("м")){
            return Gender.male;
        }
        return Gender.female;
    }
    public H getHumanById(int id){
        for (H human: this.familyTree) {
            if (human.getId() == id){
                return human;
            }
        }
        return null;
    }

    public void printChildren() {
        System.out.println("Список генеалогического древа с указанием наличия (отсутствия) детей:");
        int index = 1;
        for (H humanTemp : familyTree) {
            if (humanTemp.getChildren().isEmpty()) {
                System.out.printf("%d)%s (%d) не имеет детей!\n ", index++, humanTemp.getFullName(), humanTemp.getBirthYear());
                System.out.println();
            } else {
                System.out.printf("%d)%s (%d) имеет следующих детей:\n %s\n", index++, humanTemp.getFullName(), humanTemp.getBirthYear(), humanTemp.getChildren());
                System.out.println();
            }
        }
    }

    public void sortByName() {
        this.familyTree.sort(new HumanComparatorByName());
    }

    public void sortByBirthday() {
        this.familyTree.sort(new HumanComparatorByBirthday());
    }

    public void sortById() {        this.familyTree.sort(new HumanComparatorById());    }

    public List<H> getFamilyTree() {
        return familyTree;
    }

    @Override
    public boolean hasNext() {
        return this.id < familyTree.size();
    }

    @Override
    public H next() {
        return this.familyTree.get(id++);
    }
}
