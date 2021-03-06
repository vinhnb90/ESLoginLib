package esolutions.com.esloginlib.lib;

import java.util.List;

/**
 * Created by VinhNB on 10/26/2017.
 */

public interface LoginInteface<T> {
    void openMainView();

    List<T> callServerDepart();

    boolean checkServerLogin(String depart, String user, String pass);

    boolean checkSessionLogin(boolean hasModeLoginOffline, String depart, String mUser, String mPass);

    void saveDBDepart(List<T> list) throws Exception;

    List<T> selectDBDepart();
}
