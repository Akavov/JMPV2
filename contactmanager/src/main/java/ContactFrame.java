import com.sun.jmx.remote.security.JMXPluggableAuthenticator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Ars on 08.10.2017.
 */
public class ContactFrame extends JFrame implements ActionListener {

    public static final String LOAD = "LOAD";
    public static final String ADD = "ADD";
    public static final String EDIT = "EDIT";
    public static final String DELETE = "DELETE";

    private final ContactManager contactManager=new ContactManager();
    private final JTable contactTable = new JTable();

    public ContactFrame() {
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridwidth=GridBagConstraints.REMAINDER;

        gridBagConstraints.fill=GridBagConstraints.BOTH;

        gridBagConstraints.insets = new Insets(5, 5, 0, 5);

        JPanel btnPanel = new JPanel();

        btnPanel.setLayout(gridBagLayout);

        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, "ОБНОВИТЬ", LOAD));
        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, "ДОБАВИТЬ", ADD));
        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, "ИСПРАВИТЬ", EDIT));
        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, "УДАЛИТЬ", DELETE));

        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(btnPanel, BorderLayout.NORTH);
        add(left, BorderLayout.WEST);

        add(new JScrollPane(contactTable), BorderLayout.CENTER);

        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadContact();
    }

    private JButton createButton(GridBagLayout gridBagLayout, GridBagConstraints gridBagConstraints, String title, String action) {
        JButton jButton = new JButton(title);
        jButton.setActionCommand(action);
        jButton.addActionListener(this);
        gridBagLayout.setConstraints(jButton, gridBagConstraints);
        return jButton;
    }

    public void actionPerformed(ActionEvent e) {
        String action=e.getActionCommand();
        switch (action) {
            case LOAD:
                loadContact();
                break;
            case ADD:
                addContact();
                break;
            case EDIT:
                editContact();
                break;
            case DELETE:
                deleteContact();
                break;
        }
    }

    private void loadContact() {
        java.util.List<Contact> contactList=contactManager.findContacts();
        ContactModel cm = new ContactModel(contactList);
        contactTable.setModel(cm);
    }

    private void addContact() {
        EditContactDialog ecd=new EditContactDialog();
        saveContact(ecd);
    }

    private void editContact() {
        int sr=contactTable.getSelectedRow();
        if (sr != -1) {
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            Contact cnt = contactManager.getContact(id);
            EditContactDialog ecd = new EditContactDialog(contactManager.getContact(id));
            saveContact(ecd);
        } else {
            JOptionPane.showMessageDialog(this, "You must choose row for edit");
        }
    }

    private void deleteContact() {
        int sr=contactTable.getSelectedRow();
        if (sr != -1) {
            Long id = Long.parseLong(contactTable.getModel().getValueAt(sr, 0).toString());
            contactManager.deleteContact(id);
            loadContact();
        } else {
            JOptionPane.showMessageDialog(this, "You must choose the row for delete");
        }


    }

    private void saveContact(EditContactDialog ecd) {
        if (ecd.isSave()) {
            Contact cnt=ecd.getContact();
            if (cnt.getContactId() != null) {
                contactManager.updateContact(cnt);
            } else {
                contactManager.addContact(cnt);
            }
            loadContact();
        }
    }
}
