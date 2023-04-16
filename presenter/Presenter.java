package presenter;

import model.Gender;
import model.Human;
import model.Service;
import view.View;

import java.io.IOException;

public class Presenter {
    private View view;
    private Service service;

    public Presenter(View view, Service service) {
        this.view = view;
        this.service = service;
        view.setPresenter(this);
    }

    public void addHuman(Human human) {
        service.addHuman(human);
        System.out.printf("Вы успешно добавили в древо следующего родственника:\n %s \n", human.toString());
    }

    public void print() {
        service.print();
    }

    public String getFileName() { return service.getFileName(); }

    public String getFileType() {
        return service.getFileType();
    }

    public Human getHumanByName(String name) {
        return service.getHumanByName(name);
    }

    public void createInitialTree() {
        service.createInitialTree();
    }

    public void printChildren(){
        service.printChildren();
    }
    public void writeTreeInFile()throws IOException {
        service.writeTreeInFile();
    }

    public void readFromFile()throws IOException, ClassNotFoundException{
        service.readFromFile();
    }

    public void sortByName(){
        service.sortByName();
    }

    public void sortById(){
        service.sortById();
    }

    public void sortByBirthYear(){
        service.sortByBirthYear();
    }

    public Human getHumanById(int id) {
        return service.getHumanById(id);
    }

    public int getLastId(){
        return service.getLastId();
    }

    public Gender getGender(String gender) {
        return service.getGender(gender);
    }
}
