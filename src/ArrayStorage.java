import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Array based storage for Resumes
 * Test Branches
 */
public class ArrayStorage {
    public final int arrayLenght=10000;
    Resume[] storage = new Resume[arrayLenght];
    private int size=0;

    void clear() {
        storage=new Resume[10000];
        for(int i=0;i<size;i++)
            storage[i]=null;
        size=0;
    }

    void save(Resume r) {
        //проверка есть ли уже такой элемент в массиве
        if(get(r.uuid)==null)
        {
            //сравнение с переменной (на непревышение максимальной длины массива)
            if(size<arrayLenght) storage[size++]=r; else
            System.out.println("Error: Not enough space");
        } else
            System.out.println("Error: this resume is already in array" );

    }

    Resume get(String uuid) {

        for(int i=0;i<size;i++)
            if(storage[i].uuid.equals(uuid)) return storage[i];

        return null;

    }

    void delete(String uuid) {
        for(int i=0;i<size;i++){
                if (storage[i].uuid.equals(uuid)){
                    storage[i]=storage[size-1];
                    storage[size-1]=null;
                    size--;
                    return;
                }

        }
        System.out.println("Error: resume not found");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] r1=new Resume[size];
        for(int i=0;i<size;i++)
            r1[i]=storage[i];
        return r1;    }

    int size() {
        return size;
    }
}
