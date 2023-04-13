import model.FamilyTree;
import model.FileHandler;
import model.Service;
import presenter.Presenter;
import view.Console;
import view.View;

import java.io.IOException;

public class MainMVP {
    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler("familyTree", "out");
        FamilyTree familyTree = new FamilyTree();
        View view = new Console(fileHandler, familyTree);
        Service service = new Service();
        Presenter presenter = new Presenter(view, service);

        view.start();
    }
}
