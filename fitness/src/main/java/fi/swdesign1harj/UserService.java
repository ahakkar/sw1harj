package fi.swdesign1harj;

public class UserService {
    private UserData userData;
    private static UserService instance;

    public UserService() {
        this.userData = new UserData();
    }

    public static UserService getInstance(){

        synchronized (UserService.class){
            if (instance == null){
                instance = new UserService();
            }
            return instance;
        }
    }

    public void saveData(String steps, String burnedCalories, String time) {
        userData.setSteps(steps);
        userData.setBurnedCalories(burnedCalories);
        userData.setTime(time);
    }

    public UserData getData() {
        return userData;
    } 
}
