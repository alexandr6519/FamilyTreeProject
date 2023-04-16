package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree<H extends Human> implements Serializable, FamilyTreeIterator<H> {
    private int id;
    private List<H> familyTree;

    public FamilyTree() {
        this.familyTree = new ArrayList<H>();
    }

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
