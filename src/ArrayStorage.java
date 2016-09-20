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
    private int size=0;

    void clear() {
        storage=new Resume[10000];
        size=0;
    }

    void save(Resume r) {

        if(size<storage.length) storage[size++]=r; else
            System.out.println("Not enough space");
    }

    Resume get(String uuid) {

        for(int i=0;i<size;i++)
            if(storage[i].uuid.equals(uuid)) return storage[i];

        return null;

    }

    void delete(String uuid) {
        boolean isFind=false;
        for(int i=0;i<size;i++){
            if(storage[i]!=null&&!isFind)
                if (storage[i].uuid.equals(uuid))
                    isFind=true;
            if(isFind){
                if(i<size-1)storage[i]=storage[i+1];else
                    storage[i]=null;
            }
        }
        if(isFind)size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {        return Arrays.copyOfRange(storage,0,size);    }

    int size() {
        return size;
    }
}
