import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    static int i=0;

    void clear() {
        storage=new Resume[10000];
    }

    void save(Resume r) {
        storage[i++]=r;
    }

    Resume get(String uuid) {
        Resume[]r=getAll();
        for(Resume r1:r)
          if(r1.uuid.equals(uuid)) return r1;
        if(true)return new Resume();
        return null;

    }

    void delete(String uuid) {
        boolean isFind=false;
        for(int i=0;i<storage.length;i++){
            if(storage[i]!=null)
                if (storage[i].uuid.equals(uuid)) isFind=true;
            if(isFind){
                if(i<storage.length-2)storage[i]=storage[i+1];else
                    storage[i]=null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        List<Resume>r=new ArrayList<>();
        for(Resume r1:storage)
          if(r1!=null)r.add(r1);
        Resume[]rm=new Resume[r.size()];
        for(int i=0;i<r.size();i++)
            rm[i]=r.get(i);

        return rm;
    }

    int size() {
        return getAll().length;
    }
}
