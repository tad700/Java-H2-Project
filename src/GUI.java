import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class GUI {

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;

    JTabbedPane tab = new JTabbedPane();
    int id;
    private final JFrame frame = new JFrame();
    //First Panel
    private final JPanel upPanel = new JPanel();
    private final JPanel secondUpPanel = new JPanel();
    private final JPanel midPanel = new JPanel();
    //Secondfinal  Panel
    private final JPanel secondMidPanel = new JPanel();
    private final JPanel bottomPanel = new JPanel();
    private final JPanel secondBottomPanel = new JPanel();
//Third Panel
    private final JPanel thirdUpPanel = new JPanel();
    private final JPanel thirdMidPanel = new JPanel();
    private final JPanel thirdBottomPanel = new JPanel();
    //Fourth Panel


   private final JTable tablePersons = new JTable();
   private final JTable tableClothes = new JTable();
   private final JTable tableOrders = new JTable();
   private final JPanel firstTab = new JPanel();
   private final JPanel secondTab = new JPanel();
   private final JPanel thirdTab = new JPanel();
    private JPanel fourthPanel = new JPanel();


    //Labels
    //First Panel
    private final JLabel fNameLabel = new JLabel("Име");
    private final JLabel lNameLabel = new JLabel("Фамилия");
    private final JLabel genderLabel = new JLabel("Пол");
    private final JLabel ageLabel = new JLabel("Възраст");
    private final JLabel salaryLabel = new JLabel("Заплата");
//Second Panel
   private final JLabel itemTypeLabel = new JLabel("Вид На Дреха");
   private final JLabel itemNameLabel = new JLabel("Име На Дреха");
   private final JLabel itemPriceLabel = new JLabel("Цена");
    //Third Panel
    //TODO change the names of the labels
   private final JLabel ordersPersonIDLabel = new JLabel("Име на Клиент/ID");
   private final JLabel ordersItemIDLabel = new JLabel("Продукт");
   private final JLabel ordersQuantityLabel = new JLabel("Количество");
   private final JLabel ordersOrderDateLabel = new JLabel("Дата на поръчката");

    //TextFields
// First Panel TextField
   private final JTextField fNameTextField = new JTextField();
   private final JTextField lNameTextField = new JTextField();
   private final JTextField ageTextField = new JTextField();
   private final JTextField salaryField = new JTextField();
   //Second Panel TextField
   private final JTextField itemTypeTextField = new JTextField();
   private final JTextField itemNameTextField = new JTextField();
   private final JTextField itemPriceTextField = new JTextField();
    //Third Panel TextField
    //TODO change the names of the textfields

   private final JTextField ordersFNameTextField = new JTextField();
   private final JTextField ordersItemTextField = new JTextField();
   private final JTextField ordersDateTextField = new JTextField();
   private final JTextField ordersQuantityTextField = new JTextField();

    //Buttons
    //First Panel
   private final JButton addButtonPeople = new JButton("Добави");
   private final JButton removeButtonPeople = new JButton("Премахни");
   private final JButton updateButtonPeople = new JButton("Промени");
   private final JButton searchButtonPeople = new JButton("Търсене");
   private final JButton refreshButtonPeople = new JButton("Обнови");
   //Second Panel
   private final JButton addButtonClothes = new JButton("Добави");
   private final JButton removeButtonClothes = new JButton("Премахни");
   private final JButton updateButtonClothes = new JButton("Промени");
   private final JButton searchButtonClothes = new JButton("Търсене");
   private final JButton refreshButtonClothes = new JButton("Обнови");
    //Third Panel

   private final JButton addButtonOrders = new JButton("Добави");
   private final JButton removeButtonOrders = new JButton("Премахни");
   private final JButton updateButtonOrders = new JButton("Промени");
   private final JButton searchButtonOrders = new JButton("Търсене");
   private final JButton refreshButtonOrders = new JButton("Обнови");

    //Table
   private final JScrollPane scrollPanePersons = new JScrollPane(tablePersons);
   private final JScrollPane scrollPaneClothes = new JScrollPane(tableClothes);
   private final JScrollPane scrollPaneOrders = new JScrollPane(tableOrders);

    String[] gender = {"M", "F"};
    JComboBox<String> genderCombo = new JComboBox(gender);


    GUI() {

        frame.setSize(400, 600);
        frame.setResizable(false);
        //First Panel
        //upPanel
        upPanel.setBackground(new Color(50, 200, 100, 100));
        upPanel.setLayout(new GridLayout(5, 2));
        upPanel.add(fNameLabel);
        upPanel.add(fNameTextField);
        upPanel.add(lNameLabel);
        upPanel.add(lNameTextField);
        upPanel.add(ageLabel);
        upPanel.add(ageTextField);
        upPanel.add(genderLabel);
        upPanel.add(genderCombo);
        upPanel.add(salaryLabel);
        upPanel.add(salaryField);
        midPanel.setBackground(new Color(0, 150, 250, 100));
        //Добавяне на данни в таблицата
        midPanel.add(addButtonPeople);
        tablePersons.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                {
                    int row = tablePersons.getSelectedRow();
                    id = Integer.parseInt(tablePersons.getValueAt(row, 0).toString());
                    fNameTextField.setText(tablePersons.getValueAt(row, 1).toString());
                    lNameTextField.setText(tablePersons.getValueAt(row, 2).toString());
                    ageTextField.setText(tablePersons.getValueAt(row, 4).toString());
                    salaryField.setText(tablePersons.getValueAt(row, 5).toString());

                    if (tablePersons.getValueAt(row, 3).toString().equals("M")) {
                        genderCombo.setSelectedIndex(0);
                    } else {
                        genderCombo.setSelectedIndex(1);
                    }


                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        addButtonPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = DbConnection.getConnection();
                String sql = "insert into persons(fName,lName,gender,age,salary) values(?,?,?,?,?)";
                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, fNameTextField.getText());
                    state.setString(2, lNameTextField.getText());
                    state.setString(3, genderCombo.getSelectedItem().toString());
                    state.setInt(4, Integer.parseInt(ageTextField.getText()));
                    state.setFloat(5, Float.parseFloat(salaryField.getText()));

                    state.execute();
                    state.close();
                    refreshTablePersons();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        //Изтриване от таблицата
        midPanel.add(removeButtonPeople);
        removeButtonPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conn = DbConnection.getConnection();


                conn = DbConnection.getConnection();
                String sql = "delete from persons where customerid=?";

                try {
                    state = conn.prepareStatement(sql);
                    state.setInt(1, id);
                    state.execute();
                    refreshTablePersons();
                    clearFormPersons();
                    state.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        //Промяна на таблциата
        midPanel.add(updateButtonPeople);
        updateButtonPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conn = DbConnection.getConnection();
                String updateSql = "UPDATE persons\n" +
                        "SET fname = ?, lname = ?, gender = ?, age = ?, salary = ?\n" +
                        "WHERE customerid = ?";

                try{
                    state=conn.prepareStatement(updateSql);
                    state.setString(1,fNameTextField.getText());
                    state.setString(2,lNameTextField.getText());
                    state.setString(3,genderCombo.getSelectedItem().toString());
                    state.setInt(4, Integer.parseInt(ageTextField.getText()));
                    state.setFloat(5,Float.parseFloat(salaryField.getText()));
                    state.setInt(6,id);
                    state.execute();
                    refreshTablePersons();
                    state.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        //Търсене по критерии
        midPanel.add(searchButtonPeople);
        searchButtonPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conn = DbConnection.getConnection();
                String sql = "select * from persons where customerid=?";
String input= JOptionPane.showInputDialog(null,"Въведете CustomerID");
                int inputid=Integer.parseInt(input);
                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, String.valueOf(inputid));
                    result = state.executeQuery();
                    tablePersons.setModel(new MyModel(result));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        //Обновяване на таблицата
        midPanel.add(refreshButtonPeople);
        refreshButtonPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshTablePersons();
            }
        });

        //bottomPanel
        scrollPanePersons.setPreferredSize(new Dimension(350, 150));
        bottomPanel.add(scrollPanePersons);
        refreshTablePersons();


        //Second Panel
        secondUpPanel.setBackground(new Color(50, 200, 100, 100));
        secondUpPanel.setLayout(new GridLayout(5, 2));
        secondUpPanel.add(itemTypeLabel);
        secondUpPanel.add(itemTypeTextField);
        secondUpPanel.add(itemNameLabel);
        secondUpPanel.add(itemNameTextField);
        secondUpPanel.add(itemPriceLabel);
        secondUpPanel.add(itemPriceTextField);
        secondMidPanel.setBackground(new Color(0, 150, 250, 100));
        //Добавяне на даннит в таблицата
        secondMidPanel.add(addButtonClothes);
        tableClothes.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                {
                    int row = tableClothes.getSelectedRow();
                    id = Integer.parseInt(tableClothes.getValueAt(row, 0).toString());
                    itemTypeTextField.setText(tableClothes.getValueAt(row, 1).toString());
                    itemNameTextField.setText(tableClothes.getValueAt(row, 2).toString());
                    itemPriceTextField.setText(tableClothes.getValueAt(row, 3).toString());


                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        addButtonClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = DbConnection.getConnection();
                String sql = "insert into items(item_type,item_name,Item_price) values(?,?,?)";
                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, itemTypeTextField.getText());
                    state.setString(2, itemNameTextField.getText());
                    state.setFloat(3, Float.parseFloat(itemPriceTextField.getText()));

                    state.execute();
                    state.close();
                    refreshTableClothes();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });
        //Изтриване от таблицата
        secondMidPanel.add(removeButtonClothes);
        removeButtonClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conn = DbConnection.getConnection();
                String sql = "delete from items where productid=?";

                try {
                    state = conn.prepareStatement(sql);
                    state.setInt(1, id);
                    state.execute();
                    refreshTableClothes();
                    clearFormClothes();
                    state.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        //Обновяване на таблциата
        secondMidPanel.add(updateButtonClothes);
        updateButtonClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String updateSql = "UPDATE items SET item_name = ?,item_type=?,item_price=? WHERE productid=?";
                conn = DbConnection.getConnection();

                try {
                    state = conn.prepareStatement(updateSql);
                    state.setString(1, itemNameTextField.getText());
                    state.setString(2, itemTypeTextField.getText());
                    state.setFloat(3, Float.parseFloat(itemPriceTextField.getText()));
                    state.setInt(4,id);
                    state.execute();
                    refreshTableClothes();
                    state.close();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        //Търсене по критерии
        secondMidPanel.add(searchButtonClothes);
        searchButtonClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conn = DbConnection.getConnection();
                String input= JOptionPane.showInputDialog(null,"Въведете ProductID");
                int inputid=Integer.parseInt(input);
                String sql = "select * from items where ProductID=?";

                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, String.valueOf(inputid));
                    result = state.executeQuery();
                    tableClothes.setModel(new MyModel(result));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        //Обновяване на таблицата
        secondMidPanel.add(refreshButtonClothes);
        refreshButtonClothes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshTableClothes();
            }
        });

        //bottomPanel
        scrollPaneClothes.setPreferredSize(new Dimension(350, 150));
        secondBottomPanel.add(scrollPaneClothes);
        refreshTableClothes();

        //Third Tab
        thirdUpPanel.setBackground(new Color(50, 200, 100, 100));
        thirdUpPanel.setLayout(new GridLayout(5, 2));
        thirdUpPanel.add(ordersPersonIDLabel);
        thirdUpPanel.add(ordersFNameTextField);
        thirdUpPanel.add(ordersItemIDLabel);
        thirdUpPanel.add(ordersItemTextField);
        thirdUpPanel.add(ordersQuantityLabel);
        thirdUpPanel.add(ordersQuantityTextField);

        thirdUpPanel.add(ordersOrderDateLabel);
        thirdUpPanel.add(ordersDateTextField);
        thirdMidPanel.setBackground(new Color(0, 150, 250, 100));
        //Добавяне на данни в таблицата
        thirdMidPanel.add(addButtonOrders);
        tableOrders.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                {
                    int row = tableOrders.getSelectedRow();
                    id = Integer.parseInt(tableOrders.getValueAt(row, 0).toString());
                    ordersFNameTextField.setText(tableOrders.getValueAt(row, 1).toString());
                    ordersItemTextField.setText(tableOrders.getValueAt(row, 2).toString());
                    ordersDateTextField.setText(tableOrders.getValueAt(row, 3).toString());
                    ordersQuantityTextField.setText(tableOrders.getValueAt(row,4).toString());



                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
ordersQuantityTextField.setBackground(Color.red);
ordersFNameTextField.setBackground(Color.cyan);
ordersItemTextField.setBackground(Color.green);
        addButtonOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo change the name to the correct fields
                conn = DbConnection.getConnection();
                String sql = "INSERT INTO orders (person_id, item_id, order_date, quantity)\n" +
                        "SELECT customerid, productid, NOW(), ?\n" +
                        "FROM persons, items\n" +
                        "WHERE persons.fname = ? AND persons.lname = ? AND items.productid = ?";
                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, ordersQuantityTextField.getText());
                    state.setString(2, ordersFNameTextField.getText());
                    state.setString(3, ordersItemTextField.getText());
                    state.setString(4, ordersDateTextField.getText());


                    state.execute();
                    state.close();
                    refreshTableOrders();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
        });

        //Изтриване от таблицата
        thirdMidPanel.add(removeButtonOrders);
        removeButtonOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conn = DbConnection.getConnection();


                conn = DbConnection.getConnection();
                String sql = "delete from orders where order_id=?";

                try {
                    state = conn.prepareStatement(sql);
                    state.setInt(1, id);
                    state.execute();
                    refreshTableOrders();
                    clearFormOrders();
                    state.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        //Промяна на таблциата
        thirdMidPanel.add(updateButtonOrders);
        updateButtonOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //todo fix the update clause 
                String updateSql = ("UPDATE orders SET person_id = ? ,Item_id = ?,Order_Date=NOW(), Quantity=? WHERE ORDER_ID = ?");

                conn = DbConnection.getConnection();

                try {

                    state = conn.prepareStatement(updateSql);
                    state.setString(1,ordersFNameTextField.getText());
                    state.setString(2, ordersItemTextField.getText());
                    state.setInt(3,Integer.parseInt(ordersQuantityTextField.getText()));
                    state.setInt(4,id);



                    state.execute();
                    refreshTableOrders();
                    state.close();


                } catch (SQLException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
            }
        });

        //Търсене по критерии
        thirdMidPanel.add(searchButtonOrders);
        searchButtonOrders.addActionListener(new ActionListener() {
            //todo change 'Integer.parseInt(ageTextField.getText()))'
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                conn = DbConnection.getConnection();
                String sql = "SELECT o.order_id, o.order_date, i.item_name, o.quantity \n" +
                        "FROM orders o \n" +
                        "JOIN items i ON o.item_id = i.productid \n" +
                        "JOIN persons p ON o.person_id = p.customerid \n" +
                        "WHERE p.fname = ? AND p.lname = ?;";
                Object[] message = {
                        "Enter first name:", new JTextField(),
                        "Enter last name:", new JTextField()
                };

                int option = JOptionPane.showConfirmDialog(null, message, "Въведете Имена по, които искате да търсите поръчки", JOptionPane.OK_CANCEL_OPTION);

                if (option == JOptionPane.OK_OPTION) {
                    String firstName = ((JTextField) message[1]).getText();
                    String lastName = ((JTextField) message[3]).getText();
                    // do something with the input values

// String input = JOptionPane.showInputDialog(null,"Въведете Име На Клиент");
                    try {
                        state = conn.prepareStatement(sql);
                        state.setString(1, firstName);
                        state.setString(2, lastName);
                        result = state.executeQuery();
                        tableOrders.setModel(new MyModel(result));
                    } catch (SQLException e1) {
                        System.out.println("Error");
                        e1.printStackTrace();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        //Обновяване на таблицата
        thirdMidPanel.add(refreshButtonOrders);
        refreshButtonOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refreshTableOrders();
            }
        });

        //bottomPanel
        scrollPaneOrders.setPreferredSize(new Dimension(350, 150));
        thirdBottomPanel.add(scrollPaneOrders);
        refreshTableOrders();

        firstTab.setBounds(0, 0, 400, 600);
        firstTab.setLayout(new GridLayout(3, 1));
        secondTab.setLayout(new GridLayout(3, 1));
        thirdTab.setLayout(new GridLayout(3, 1));


        firstTab.add(upPanel);
        firstTab.add(midPanel);
        firstTab.add(bottomPanel);

        secondTab.add(secondUpPanel);
        secondTab.add(secondMidPanel);
        secondTab.add(secondBottomPanel);

        thirdTab.add(thirdUpPanel);
        thirdTab.add(thirdMidPanel);
        thirdTab.add(thirdBottomPanel);

        tab.add(firstTab, "Хора");
        tab.add(secondTab, "Дрехи");
        tab.add(thirdTab, "Поръчки");
        tab.add(fourthPanel,"Справка");


        //Add to frame,Showframe
        frame.add(tab);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public void refreshTablePersons() {
        conn = DbConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from persons");
            result = state.executeQuery();
            tablePersons.setModel(new MyModel(result));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void refreshTableClothes() {
        conn = DbConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from items");
            result = state.executeQuery();
            tableClothes.setModel(new MyModel(result));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void clearFormPersons() {
        fNameTextField.setText("");
        lNameTextField.setText("");
        ageTextField.setText("");
        salaryField.setText("");
    }

    public void clearFormClothes() {
        itemNameTextField.setText("");
        itemTypeTextField.setText("");
        itemPriceTextField.setText("");
    }

    public void clearFormOrders() {
        ordersFNameTextField.setText("");
        ordersItemTextField.setText("");
        ordersDateTextField.setText("");
        ordersQuantityTextField.setText("");
    }
    public void refreshTableOrders() {
        conn = DbConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from orders");
            result = state.executeQuery();
            tableOrders.setModel(new MyModel(result));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}