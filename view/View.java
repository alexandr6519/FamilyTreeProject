package view;

import model.FamilyTree;
import presenter.Presenter;

import java.io.IOException;

public interface View {

    void print(FamilyTree familyTree);
    void start() throws IOException;

    void setPresenter(Presenter presenter);
}
