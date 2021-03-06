/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Customer;
import Entities.Employee;
import Entities.Product;
import Enums.OperationType;
import JDOM.JDOMProcedures;
import SQL.SQLProcedures;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Edgar
 */
public class ViewOrder extends javax.swing.JFrame {

    /**
     * Creates new form ViewOrder
     */
    private static final String[] columnNames = {" ", "Id", "Articulo", "Precio unitario", "Cantidad"};

    public OperationType operation;

    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    List<Object[]> listItems = new ArrayList<>();
    List<Object> listDetails = new ArrayList<>();

    SQLProcedures sqlp = new SQLProcedures();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    List<Employee> employees = sqlp.getEmployees();
    List<Product> products = sqlp.getProducts();
    List<Customer> customers = sqlp.getCustomers();

    JDOMProcedures jDOMProcedures = new JDOMProcedures();

    public void addToListDetails() {
        listDetails.clear();
        listDetails.add(txtOrderId.getText());
        String fecha = dateFormat.format(dchFecha.getDate());
        listDetails.add(fecha);
        listDetails.add(txtModo.getText());
        listDetails.add(customers.get(cmbCliente.getSelectedIndex() - 1).getCustomerId());
        listDetails.add(customers.get(cmbCliente.getSelectedIndex() - 1).getCustFirstName());
        listDetails.add(txtTotal.getText());
        listDetails.add(employees.get(cmbEmployee.getSelectedIndex() - 1).getEmployeeId().toString());
        listDetails.add(employees.get(cmbEmployee.getSelectedIndex() - 1).getFirstName());
    }

    public Object[] createItem() {
        Object[] item = new Object[5];
        if (listItems.isEmpty()) {
            item[0] = 1;
        } else {
            Integer index = listItems.size();
            item[0] = index + 1;
        }
        item[1] = products.get(cmbProducts.getSelectedIndex() - 1).getProductId();
        item[2] = products.get(cmbProducts.getSelectedIndex() - 1).getProductName();
        item[3] = products.get(cmbProducts.getSelectedIndex() - 1).getMinPrice().toString();
        item[4] = txtCantidad.getText();
        return item;
    }

    public void cargarDatos() {
        for (Customer customer : customers) {
            String customerItem = String.format("%s %s (%s)",
                    customer.getCustFirstName(),
                    customer.getCustLastName(),
                    customer.getCustomerId());
            cmbCliente.addItem(customerItem);
        }
        for (Employee employee : employees) {
            String employeeItem = String.format("%s %s (%s)",
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmployeeId());
            cmbEmployee.addItem(employeeItem);
        }
        for (Product product : products) {
            String employeeItem = String.format("%s (%s)",
                    product.getProductName(),
                    product.getProductId());
            cmbProducts.addItem(employeeItem);
        }
    }

    public Integer returnCustomerIndex(Integer id) {
        for (Integer x = 0; x < customers.size(); x++) {
            if (id == customers.get(x).getCustomerId()) {
                return x + 1;
            }
        }
        return -1;
    }

    public Integer returnSalesRepIndex(Integer id) {
        for (Integer x = 0; x < employees.size(); x++) {
            if (Objects.equals(id, employees.get(x).getEmployeeId())) {
                return x + 1;
            }
        }
        return -1;
    }

    public ViewOrder(OperationType operation) throws SQLException {
        initComponents();
        this.operation = operation;
        setLocationRelativeTo(null);
        cargarDatos();
        tblItems.setModel(tableModel);
        switch (operation) {
            case CREATE:
                txtOrderId.setText(sqlp.getLastIndex().toString());
                btnBuscar.setEnabled(false);
                this.setTitle("Crear orden");
                break;
            case UPDATE:
                txtOrderId.setEditable(true);
                this.setTitle("Actualizar orden");
                break;
            case DELETE:
                btnEliminarItem.setEnabled(false);
                txtOrderId.setEditable(true);
                bntAgnadir.setEnabled(false);
                cmbProducts.setEditable(false);
                this.setTitle("Eliminar orden");
                break;
        }
    }

    public ViewOrder() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public void refreshTable(List<Object[]> rows) {
        tableModel.getDataVector().removeAllElements();
        revalidate();
        for (Object[] row : rows) {
            tableModel.addRow(row);
        }
    }

    public Float calculateTotal(List<Object[]> rows) {
        float total = 0;
        float x, y;
        for (Object[] row : rows) {
            x = Float.parseFloat(row[3].toString());
            y = Float.parseFloat(row[4].toString());
            total = (total + (x * y));
        }
        return total;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        dchFecha = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtOrderId = new javax.swing.JTextField();
        txtModo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        cmbEmployee = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        bntAgnadir = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        cmbProducts = new javax.swing.JComboBox<>();
        btnOk = new javax.swing.JButton();
        btnEliminarItem = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tblItems.setAutoCreateRowSorter(true);
        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblItems);

        jLabel1.setText("Orden No: ");

        jLabel2.setText("Fecha:");

        jLabel3.setText("Modo:");

        txtOrderId.setEditable(false);
        txtOrderId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOrderIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOrderId))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dchFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .addComponent(txtModo))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOrderId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dchFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)))
        );

        jLabel4.setText("Nombre cliente:");

        jLabel5.setText("Nombre vendedor:");

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-" }));

        cmbEmployee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(60, 60, 60)
                        .addComponent(cmbEmployee, 0, 184, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(cmbCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bntAgnadir.setText("Añadir Item");
        bntAgnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAgnadirActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel6.setText("Total:");

        jLabel7.setText("Articulo:");

        jLabel8.setText("Cantidad:");

        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidadFocusGained(evt);
            }
        });

        cmbProducts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-" }));

        btnOk.setText("Confirmar Operacion");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnEliminarItem.setText("Eliminar Item");
        btnEliminarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bntAgnadir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOk, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbProducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(bntAgnadir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnEliminarItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOk))
        );

        btnBuscar.setText("Buscar");
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarMouseClicked(evt);
            }
        });
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnBuscar)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntAgnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAgnadirActionPerformed
        Integer selectedRow = tblItems.getSelectedRow();
        listItems.add(createItem());
        refreshTable(listItems);
        txtTotal.setText(calculateTotal(listItems).toString());
    }//GEN-LAST:event_bntAgnadirActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO lost focus for jtable:
    }//GEN-LAST:event_formMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            listDetails = sqlp.getOrderDetails(Integer.parseInt(txtOrderId.getText()));
            listItems = sqlp.getOrderItems(Integer.parseInt(txtOrderId.getText()));
            if (listDetails.isEmpty() || listItems.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Orden no encontrada");
            } else {
                Date date = dateFormat.parse(String.valueOf(listDetails.get(1)));
                dchFecha.setDate(date);

                cmbCliente.setSelectedIndex(returnCustomerIndex(Integer.parseInt(listDetails.get(3).toString())));
                cmbEmployee.setSelectedIndex(returnSalesRepIndex(Integer.parseInt(listDetails.get(6).toString())));
                txtModo.setText(listDetails.get(2).toString());
                refreshTable(listItems);
                txtTotal.setText(calculateTotal(listItems).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ViewOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtOrderIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOrderIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOrderIdActionPerformed

    private void btnBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseClicked

    }//GEN-LAST:event_btnBuscarMouseClicked

    private void btnEliminarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarItemActionPerformed
        int selectedRow = tblItems.getSelectedRow();
        if (selectedRow >= 0) {
            Integer rows = tblItems.getRowCount();
            System.out.println(rows);
            if (rows >= 1) {
                listItems.remove(selectedRow);
                refreshTable(listItems);
                txtTotal.setText(calculateTotal(listItems).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Debe mantenerse al menos un articulo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se selecciono ningun registro");
        }
    }//GEN-LAST:event_btnEliminarItemActionPerformed

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadFocusGained

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        switch (this.operation) {
            case CREATE:
                addToListDetails();
                sqlp.createOrder(jDOMProcedures.xmlOrder(listDetails, listItems));
                JOptionPane.showMessageDialog(null, "Orden creada");
                this.dispose();
                break;
            case UPDATE:
                addToListDetails();
                String temp = jDOMProcedures.xmlOrder(listDetails, listItems);
                System.out.println(temp);
                sqlp.deleteOrder(Integer.parseInt(txtOrderId.getText()));
                sqlp.createOrder(temp);
                JOptionPane.showMessageDialog(null, "Orden actualizada");
                this.dispose();
                break;
            case DELETE:
                sqlp.deleteOrder(Integer.parseInt(txtOrderId.getText()));
                JOptionPane.showMessageDialog(null, "Orden eliminada");
                this.dispose();
                break;
        }
    }//GEN-LAST:event_btnOkActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntAgnadir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminarItem;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbEmployee;
    private javax.swing.JComboBox<String> cmbProducts;
    private com.toedter.calendar.JDateChooser dchFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblItems;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtModo;
    private javax.swing.JTextField txtOrderId;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
