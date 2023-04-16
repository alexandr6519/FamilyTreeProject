package model;

import presenter.Presenter;

import java.util.Scanner;

public class Sorting {
    private Presenter presenter;
    private Scanner scanner;

    public Sorting(Presenter presenter, Scanner scanner) {
        this.presenter = presenter;
        this.scanner = scanner;
    }

    public void sortByParameter() {
        System.out.println("Для сортировки по имени введите цифру 1:\n" +
                "для сортировки по году рождения введите цифру 2: \n" +
                "для сортировки по id введите цифру 3:");
        try {
            int sortNumber = scanner.nextInt();
            switch (sortNumber) {
                case 1:
                    System.out.println("Генеалогическое древо, отсортированное по имени:");
                    this.presenter.sortByName();
                    break;
                case 2:
                    System.out.println("Генеалогическое древо, отсортированное по году рождения:");
                    this.presenter.sortByBirthYear();
                    break;
                case 3:
                    System.out.println("Генеалогическое древо, отсортированное по Id:");
                    this.presenter.sortById();
                    break;
                default:
                    System.out.println("Вы ввели некорректный номер сортировки!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода! " + e);
        }
    }
}
