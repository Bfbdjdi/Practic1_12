package org.oaosalty;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static boolean fileExistenceChecker(String enteredPath)
    {
        File fileExistenceChecker = new File(enteredPath);
        return fileExistenceChecker.isFile();
    }

    public static void main(String[] args) {
        System.out.println("\nБелин Георгий Алексеевич, РИБО-01-22, Вариант 1\n");

        Car car = new Car();

        boolean keepCycling = true; //Крутимся в главном цикле
        boolean variableIsFullyInitialized = false;

        String path = ""; //путь к файлу

        Scanner scan = new Scanner(System.in);

        while (keepCycling)
        {
            System.out.println("\nВыберите действие:\n" +
                    "(1) Добавить/Перезаписать объект,\n" +
                    "(2) Сохранить объект в файл,\n" +
                    "(3) Завершить работу программы.\n");

            String action = scan.nextLine();

            switch (action)
            {
                case ("1") /*Добавить новый объект*/->
                {
                    String licenceNumber, name, productionYear, color, ownersNumber;

                    while (true)
                    {
                        System.out.println("Введите:\n1. Регистрационный номер автомобиля:");
                        licenceNumber = scan.nextLine().toUpperCase();
                        if (!InputChecker.licenseNumberInputChecker(licenceNumber))
                        {
                            System.out.println("Регистрационный номер введён некорректно (не соответствует регламенту). Повторите ввод.\n ");
                            continue;
                        }

                        System.out.println("2. Полное название автомобиля:");
                        name = scan.nextLine();
                        if (!InputChecker.isThereData(name))
                        {
                            System.out.println("Это поле не может быть пустым. Введите информацию.\n ");
                            continue;
                        }
                        //Убирает из name лишние пробелы до первого символа
                        while (name.startsWith(" "))
                        {
                            name = name.replaceFirst(" ", "");
                        }

                        System.out.println("3. Год производства автомобиля:");
                        productionYear = scan.nextLine();
                        if (InputChecker.yearInputChecker(productionYear))
                        {
                            System.out.println("Год был введён неверно. Повторите ввод.\n");
                            continue;
                        }

                        System.out.println("4. Цвет автомобиля:");
                        color = scan.nextLine();
                        if (!InputChecker.isThereData(color))
                        {
                            System.out.println("Это поле не может быть пустым. Введите информацию.\n ");
                            continue;
                        }
                        //Убирает из color лишние пробелы до первого символа
                        while (name.startsWith(" "))
                        {
                            name = name.replaceFirst(" ", "");
                        }

                        System.out.println("5. Количество владельцев автомобиля:");
                        ownersNumber = scan.nextLine();
                        if (!InputChecker.isItNum(ownersNumber))
                        {
                            System.out.println("Число было введено неверно. Повторите ввод.\n");
                            continue;
                        }

                        break; //Завершение ввода
                    }

                    car.setName(name);
                    car.setLicenceNumber(licenceNumber);
                    car.setProductionYear(Integer.parseInt(productionYear));
                    car.setColor(color);
                    car.setOwnersNumber(Integer.parseInt(ownersNumber));

                    variableIsFullyInitialized = true;
                    //Никакое поле переменной car не пропущено, теперь можно её сохранить в файл

                    break; //Выход из case("1")
                }

                case ("2") /*Сериализация в файл*/ ->
                {
                    if (!variableIsFullyInitialized) //если не все требуемые данные были введены
                    {
                        System.out.println("Пока что нет данных для сохранения. Далее введите (1), чтобы ввести их.\n");
                        continue;
                    }

                    while (true)
                    {
                        System.out.println("Какое название должно быть у файла? (без расширения)");
                        String fileName = scan.nextLine().replaceAll(" ", "_");

                        if (InputChecker.fileNameChecker(fileName))
                        {
                            System.out.println("В введённом названии есть запрещённые символы. Повторите ввод.\n");
                            continue;
                        }

                        //C:\Users\googn\Desktop\
                        if (!fileName.isEmpty()) path = "C:\\" + fileName + ".txt"; else path = "C:\\carData_output.txt";
                        break;
                    }

                    Serialize sr = new Serialize(car, path);
                    Thread threadForSerialization = new Thread(sr);
                    threadForSerialization.start();
                    try {
                        threadForSerialization.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

                case ("3") /*Завершение работы программы*/ ->
                {
                    keepCycling = false;
                    if (fileExistenceChecker(path)) System.out.println("Файл сохранён по пути " + path);
                    else System.out.println("Никаких файлов не было сохранено.");
                    break; //Завершение работы
                }

                default ->
                {
                    System.out.println("Операция была выбрана неверно. Повторите ввод.\n");
                    continue;
                }
            }
        }
    }
}