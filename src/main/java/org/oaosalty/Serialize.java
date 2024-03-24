package org.oaosalty;
import java.io.*;

public class Serialize implements Externalizable, Runnable{

    private Car car;
    private String path;

    public Serialize(Car car, String path)
    {
        this.path = path;
        this.car = car;
    }

    public Serialize() {
    }

    public Car getCar() {
        return car;
    }

    @Override
    public void run()
    {

        final long serialVersionUID = 1L;
        try {
            //Выводит номер потока, чтобы точно убедиться в поточности сериализации
            //System.out.println(Thread.currentThread().getName());

            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(car);
            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("Сохранено.\n");
        }
        catch (IOException e)
        {
            System.out.println("Отказано в доступе для сохранения файла. IDE/Программа запущена от имени администратора?\n");
        }
    }

    @Override
    synchronized public void writeExternal(ObjectOutput car) throws IOException {
        car.writeObject(this.getCar());
    }
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        car = (Car) in.readObject();
    }

}
