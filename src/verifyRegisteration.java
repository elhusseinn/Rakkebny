public class verifyRegisteration {
    private Admin admin;
    public void verifyRegisteration(Driver driver) {
        if (admin.pendingDriver.isEmpty()) {
            System.out.println("there's no pending drivers");
        } else {
            for (int i = 0; i < admin.pendingDriver.size(); i++) {
                System.out.println(admin.pendingDriver.get(i).getUserName());
            }
        }
    }

}
