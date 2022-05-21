package thiJava_2;

import java.sql.*;
import java.util.Scanner;

public class Student {
        public static void main(String[] args) {
            try(
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");

                    Statement stmt = conn.createStatement();
            ){
                System.out.println(" Chon 1 de insert!\n Chon 2 de update!\n Chon 3 de delete!\n Chon 4 de Search! \n Chon 5 hien thi bang! \n");
                Scanner inputChoice = new Scanner(System.in);
                int choice = inputChoice.nextInt();

                switch (choice) {
                    case 1: // insert
                        System.out.println("Ban da chon muc insert!");
                        Scanner inputInsert = new Scanner(System.in);
                        System.out.println("Nhap ID hoc sinh: ");
                        String insertid = inputInsert.nextLine();
                        inputInsert.nextLine();
                        System.out.println("Nhap ten hoc sinh: ");
                        String insertName = inputInsert.nextLine();
                        System.out.println("Nhap dia chi: ");
                        String insertAddress = inputInsert.nextLine();
                        System.out.println("Nhap so dien thoai lien he: ");
                        Integer insertPhone = inputInsert.nextInt();

                        String insertStm = "insert into student(studentId, studentName , address, phone) values (?, ?, ?, ?)";
                        PreparedStatement prpstmInsert = conn.prepareStatement(insertStm);

                        prpstmInsert.setString(1, insertid);
                        prpstmInsert.setString(2, insertName);
                        prpstmInsert.setString(3, insertAddress);
                        prpstmInsert.setInt(4, insertPhone);

                        prpstmInsert.executeUpdate();
                        break;
                    case 2: // update
                        System.out.println("Ban da lua chon muc update!");
                        System.out.println("Nhap ID hoc sinh can update: ");
                        Scanner inputUpdate = new Scanner(System.in);
                        String updateid = inputUpdate.nextLine();
                        inputUpdate.nextLine();
                        System.out.println("Nhap ID moi: ");
                        String newid = inputUpdate.nextLine();
                        inputUpdate.nextLine();
                        System.out.println("Nhap ten moi: ");
                        String newName = inputUpdate.nextLine();
                        System.out.println("Nhap ten dia chi moi: ");
                        String newAddress = inputUpdate.nextLine();
                        System.out.println("Nhap so dien thoai lien he moi: ");
                        Integer newPhone = inputUpdate.nextInt();

                        String updateStm = "update student set studentId = ?, studentName = ?, address = ?, phone = ? where studentId = ?";
                        PreparedStatement prpstmUpdate = conn.prepareStatement(updateStm);
                        prpstmUpdate.setString(1, newid);
                        prpstmUpdate.setString(2, newName);
                        prpstmUpdate.setString(3, newAddress);
                        prpstmUpdate.setInt(4, newPhone);
                        prpstmUpdate.setString(5, updateid);
                        prpstmUpdate.executeUpdate();
                        break;
                    case 3: // delete 
                        System.out.println("Ban da chon muc delete!");
                        Scanner inputDelete = new Scanner(System.in);
                        System.out.println("Nhap id hoc sinh can xoa: ");
                        String deleteName = inputDelete.next();

                        String deleteStm = "delete from student where studentId = ?";
                        PreparedStatement prpstmDelete = conn.prepareStatement(deleteStm);
                        prpstmDelete.setString(1, deleteName);
                        prpstmDelete.executeUpdate();
                        break;
                    case 4: // Search
                        System.out.println("Ban da lua chon muc tim kiem!");
                        Scanner inputSearch = new Scanner(System.in);
                        System.out.println("Nhap ten hoc sinh ban muon tim");
                        String searchName = inputSearch.nextLine();

                        String searchStm = "select * from student where studentName = ?";
                        PreparedStatement prpstmSearch = conn.prepareStatement(searchStm);
                        prpstmSearch.setString(1, searchName);
                        ResultSet rset = prpstmSearch.executeQuery();
                        ResultSetMetaData rsetMD = rset.getMetaData();
                        int numColumns = rsetMD.getColumnCount();
                        for (int i = 1; i <= numColumns; ++i) {
                            System.out.printf("%-30s", rsetMD.getColumnName(i));
                        }
                        System.out.println();
                        for (int i = 1; i <= numColumns; ++i) {
                            System.out.printf("%-30s",
                                    "(" + rsetMD.getColumnClassName(i) + ")");
                        }
                        System.out.println();
                        while (rset.next()) {
                            for (int i = 1; i <= numColumns; ++i) {
                                // getString() can be used for all column types
                                System.out.printf("%-30s", rset.getString(i));
                            }
                            System.out.println();
                        }
                        break;
                    case 5: // display
                        String select = "select*from student";
                        System.out.println("Student: " + select);
                        ResultSet rsert = stmt.executeQuery(select);
                        ResultSetMetaData rsertMD = rsert.getMetaData();
                        int numColumn = rsertMD.getColumnCount();
                        for (int i = 1; i <= numColumn; ++i) {
                            System.out.printf("%-30s", rsertMD.getColumnName(i));
                        }
                        System.out.println();
                        for (int i = 1; i <= numColumn; ++i) {
                            System.out.printf("%-30s",
                                    "(" + rsertMD.getColumnClassName(i) + ")");
                        }
                        System.out.println();
                        while (rsert.next()) {
                            for (int i = 1; i <= numColumn; ++i) {
                                // getString() can be used for all column types
                                System.out.printf("%-30s", rsert.getString(i));
                            }
                            System.out.println();
                        }
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
    }
}
