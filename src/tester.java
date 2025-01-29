public class tester {
    public static void main(String[] args) {
        DatabaseHelper dbHelper = new DatabaseHelper();
        try {
            dbHelper.connect();
            System.out.println("Database connection successful!");
            dbHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
