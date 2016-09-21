import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Array based storage for Resumes
 * Test Branches
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    public final int arrayLenght=storage.length;
    private int size=0;


    void clear() {
        storage=new Resume[10000];
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
        boolean isFind=false;
        for(int i=0;i<size;i++){
                if (storage[i].uuid.equals(uuid)){
                    isFind=true;
                    storage[i]=storage[size-1];
                    storage[size-1]=null;
                    break;
                }

        }
        if(isFind)size--; else
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
