/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBCODBCProject;

/**
 *
 * @author Galib
 */
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
public class table extends JFrame{
    
    JTable table;
    
    public table(Object[][] data){
        setLayout(new FlowLayout());
        
        String[] columnNames = {"NAME","STD_ID","STD_DEPT","STD_NUMBER"};
        
        table = new JTable(data,columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,100));
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
    
    public static void main(String[] args){
        Statement ps;
        ResultSet rs;
        Object[][] data = new Object[5][5];
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
                try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","GALIB","dipto10105")) {
                    String sql = "select * from STUDENT";
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery(sql);
                    int i = 0;
                    while(rs.next()){
                        //System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
                        for(int j=0;j<4;j++){
                            data[i][j] = rs.getString(j+1);
                        }
                        i++;
                    }
                }
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        table gui = new table(data);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(600, 200);
        gui.setVisible(true);
        gui.setTitle("Student Table");
    }
}

