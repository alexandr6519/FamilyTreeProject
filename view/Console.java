package view;

import model.FamilyTreeIterator;
import model.FileHandler;
import model.Human;
import model.FamilyTree;
import presenter.Presenter;

import java.io.IOException;
import java.util.Scanner;

public class Console implements View {
    private Scanner scanner;
    private Presenter presenter;
    private boolean isWorking = true;
    private FamilyTree familyTree;
    private FileHandler fileHandler;

    public Console(FileHandler fileHandler, FamilyTree familyTree) {
        scanner = new Scanner(System.in);//, "cp866");
        this.fileHandler = fileHandler;
        this.familyTree = familyTree;
    }

    private void printInvitationForUser() {
        System.out.println();
        String str = "Выберите операцию и введите соответствующую цифру:\n" +
                " 1 - для создания генеалогического древа из заданного списка \n" +
                " 2 - для записи и сохранения списка родственников древа \n" +
                " 3 - для чтения и получения списка родственников из файла \n" +
                " 4 - для сортировки списка родственников по выбранному параметру \n" +
                " 5 - для добавления родственника в список древа \n" +
                " 0 - для выхода из меню \n";
        System.out.println(str);
    }

    @Override
    public void print(FamilyTree familyTree) {
        System.out.println("Список родственников древа состоит из:");
        familyTree.getInfoAboutFamilyTree();
    }

    @Override
    public void start() throws IOException {
        printInvitationForUser();
        while (isWorking) {
            int operationNumber = scanner.nextInt();
            switch (operationNumber) {
                case 0:
                    exit();
                    break;
                case 1:
                    familyTree.createInitialFamilyTree();
                    familyTree.getInfoAboutFamilyTree();
                    printInvitationForUser();
                    break;
                case 2:
                    fileHandler.writeTreeInFile(familyTree);
                    System.out.printf("Вы успешно сохранили список генеалогического древа в файл \"%s.%s\" !\n", fileHandler.getFileName(), fileHandler.getFileType());
                    familyTree.getInfoAboutFamilyTree();
                    printInvitationForUser();
                    break;
                case 3:
                    System.out.printf("Генеалогическое древо, прочитанное из файла \"%s.%s\":\n", fileHandler.getFileName(), fileHandler.getFileType());
                    familyTree.getInfoAboutFamilyTree();
                    printInvitationForUser();
                    break;
                case 4:
                    System.out.println("Для сортировки по имени введите цифру 1:\n" +
                            "для сортировки по году рождения введите цифру 2: \n" +
                            "для сортировки по id введите цифру 3:");
                    int sortNumber = scanner.nextInt();

                    switch (sortNumber) {
                        case 1:
                            System.out.println("Генеалогическое древо, отсортированное по имени:");
                            familyTree.sortByName();
                            break;
                        case 2:
                            System.out.println("Генеалогическое древо, отсортированное по году рождения:");
                            familyTree.sortByBirthday();
                            break;
                        case 3:
                            System.out.println("Генеалогическое древо, отсортированное по Id:");
                            familyTree.sortById();
                            break;
                        default:
                            System.out.println("Вы ввели некорректный номер сортировки!");
                    }
                    familyTree.getInfoAboutFamilyTree();
                    printInvitationForUser();
                    break;
                case 5:
                    System.out.println("Для добавления в список введите последовательно через запятую(без пробелов): " +
                            "фамилия, имя, отчество, пол (м или ж), id отца, id матери, год рождения" +
                            "(в случае отсутствия id введите -1");
                    String humanForAdding = scanner.next();
                    String[] arrayDataOfHuman = humanForAdding.split("[,]");

                    String nameFull = String.format("%s %s %s", arrayDataOfHuman[0], arrayDataOfHuman[1], arrayDataOfHuman[2]);
                    Human father = familyTree.getHumanById(Integer.parseInt(arrayDataOfHuman[4]));
                    Human mother = familyTree.getHumanById(Integer.parseInt(arrayDataOfHuman[5]));
                    int birthYear = Integer.parseInt(arrayDataOfHuman[6]);
                    int id = familyTree.getLastId() + 1;
                    Human humanNew = new Human(id, nameFull, familyTree.getGender(arrayDataOfHuman[3]), father, mother, birthYear);
                    familyTree.addHuman(humanNew);
                    familyTree.getInfoAboutFamilyTree();
                    familyTree.printChildren();
                    printInvitationForUser();
                    break;
                default:
                    System.out.println("Вы ввели некорректный номер операции!");
                    printInvitationForUser();
            }
        }
    }

   
    private void exit() {
        System.out.println("Работа завершена, всего доброго!");
        scanner.close();
        isWorking = false;
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}

