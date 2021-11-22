public class SuspendedUser {
    private  Admin admin;
    public void SuspendedUser(User user) {
        if (user instanceof Admin) {
        } else {
            admin.Suspended.add(user);
        }
    }
}
