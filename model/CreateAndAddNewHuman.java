package model;

import presenter.Presenter;

import java.util.Scanner;

public class CreateAndAddNewHuman {
    private Presenter presenter;
    private Scanner scanner;

    public CreateAndAddNewHuman(Presenter presenter, Scanner scanner) {
        this.presenter = presenter;
        this.scanner = scanner;
    }

    public void readAndAddHuman() {
        try {
            System.out.println("Для добавления в список введите последовательно через запятую(без пробелов): " +
                    "фамилия, имя, отчество, пол (м или ж), id отца, id матери, год рождения" +
                    "(в случае отсутствия id введите -1");

            String humanForAdding = scanner.next();
            String[] arrayDataOfHuman = humanForAdding.split("[,]");

            String nameFull = String.format("%s %s %s", arrayDataOfHuman[0], arrayDataOfHuman[1], arrayDataOfHuman[2]);
            Human father = presenter.getHumanById(Integer.parseInt(arrayDataOfHuman[4]));
            Human mother = presenter.getHumanById(Integer.parseInt(arrayDataOfHuman[5]));
            int birthYear = Integer.parseInt(arrayDataOfHuman[6]);
            int id = presenter.getLastId() + 1;
            Human humanNew = new Human(id, nameFull, presenter.getGender(arrayDataOfHuman[3]), father, mother, birthYear);
            presenter.addHuman(humanNew);
        } catch (Exception e) {
            System.out.println("Ошибка ввода! " + e);
        }
    }
}
