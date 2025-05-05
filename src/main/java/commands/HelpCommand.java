package commands;

import commands.Command;

import java.util.Scanner;


public class HelpCommand implements Command {

    @Override
    public void execute(String... args) {
        if (args.length != 0) {
            System.err.println("This command does not take any arguments");
            return;
        }
        System.out.println("""
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                add {element} : добавить новый элемент в коллекцию
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_by_id id : удалить элемент из коллекции по его id
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
                add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                shuffle : перемешать элементы коллекции в случайном порядке
                average_of_number_of_participants : вывести среднее значение поля numberOfParticipants для всех элементов коллекции
                max_by_genre : вывести любой объект из коллекции, значение поля genre которого является максимальным
                group_counting_by_name : сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе
                """);
    }


    @Override
    public String getName() {
        return "help";
    }
}