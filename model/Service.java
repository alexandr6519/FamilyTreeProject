package model;

public class Service {
    private FamilyTree<Human> familyTree;

    public Service() {
        familyTree = new FamilyTree();
    }

    public void addHuman(Human human) {
        this.familyTree.getFamilyTree().add(human);
        if (human.getMother() != null) {
            human.getMother().addChild(human);
        }
        if (human.getFather() != null) {
            human.getFather().addChild(human);
        }
    }

}

