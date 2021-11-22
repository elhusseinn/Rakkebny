public class system extends DataBase{

    private static system instance = null;
    private system(){}
    public static system GetInstance(){
        if (instance == null){instance = new system();}
        return instance;

    }
  /*  public boolean Authenticate(User user){
       String type = user.getClass().getName();

       if(type.equalsIgnoreCase("customer")){
           for(int i = 0; i < customers.size();i++){
               if((customers.get(i).getUserName().equals(user.getUserName())) &&
                 (customers.get(i).getPassword().equals(user.getPassword()))){
                   return true;
               }
           }
       }
       else if (type.equalsIgnoreCase("driver")){

           for(int i = 0; i < drivers.size();i++){
               if((drivers.get(i).getUserName().equals(user.getUserName())) &&
                       (drivers.get(i).getPassword().equals(user.getPassword()))){
                   return true;
               }
           }

       }
       else if (type.equalsIgnoreCase("admin")){
           for(int i = 0; i < admins.size();i++){
               if((admins.get(i).getUserName().equals(user.getUserName())) &&
                       (admins.get(i).getPassword().equals(user.getPassword()))){
                   return true;
               }
           }

       }

        return false;
    }

   */

public boolean Authenticate(User user){

    for(int i = 0; i < users.size(); i++){
        if(users.get(i).getUserName().equalsIgnoreCase(user.getUserName()) &&
           users.get(i).getPassword().equals(user.getPassword())){
            return true;

        }
    }
return false;
}


}
