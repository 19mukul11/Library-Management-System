package librarymanagementsystem;

/**
 *
 * @author Mukul Mahajan
 * 
 * LIBRARY MANAGEMENT SYSTEM IS A APPLICATION SOFTWARE WHICH IS USED TO SIMULATE THE FUNCTIONING OF LIBRARY
 *  
 */

/* IMPORTING PACKAGES AND CLASSES FOR THIS HOME CLASS*/
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;    // IMPORTING EVENT FOR EVENT_HANDLING
import java.sql.*;          // IMPORTING SQL FOR IMPLEMENTING JDBC CONCEPT
import javax.swing.*;       // IMPORTING SWING FOR GUI DEVELOPMENT
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;


/* HOME CLASS : THIS IS THE MAIN WINDOW OF THE APPLICATION. ALL THE LIBRARY OPERATIONS CAN BE PERFORMED HERE 
HOME EXTENDS JFrame AND IMPLEMENTS ActionListener, MouseListener and KeyListener */

public class Home extends JFrame implements ActionListener, MouseListener, KeyListener {

    /* HOME CLASS ATTRIBUTES : GUI COMPONENTS DECLARATION */
    JPanel titlePanel, leftPanel, mainPanel, stuDBpanel, bookDbpanel, viewAllBookPanel, viewCategoryPanel, viewBookIDPanel;
    JButton home, addBook, editDelete, adminCorner, issue, returnBook, history, database, logout, submitAndAdd, generateID, searchEditDelete;
    JPanel addBookPanel, editBookPanel, adminPanel, addStudentPanel, issuePanel, returnPanel, historyPanel, databasePanel;
    JLabel picLogo, picAddBook, picDatabase, picAddStudent, bookID, editTitle, editAuthor, editPgs, editPrice, editPublisher, editIsbn, editCategory, ids;
    Border panelBorder = BorderFactory.createBevelBorder(1, Color.gray, Color.lightGray);
    Font generalFont = new Font("Verdana", Font.PLAIN, 20);
    Font generalFont2 = new Font("Verdana", Font.PLAIN, 17);
    JTextField bookTitle, author, publisher, pages, price, isbn, bookId, editTitleText, editAuthorText, editPublisherText, editPriceText, editPagesText, editIsbnText;
    JTextField studentName, studentEmail, studentMobile, address, studentID, bookIDReturn, bookIDDB, searchBookDb, viewStuID;
    JTextField bookIDIssue, titleIssue, authorIssue, statusIssue, studIDIssue, nameIssue, branchIssue, newBranch, newCat, delStu;
    JComboBox b1, editComBox, branch, categoryWiseBook, branchWiseStu;
    JButton editSave, delete, resetEditDelete, generateStuId, addStudent, resetStudent, resetBook, checkAvl, resetIssue, searchIssue, resetStuIssue,searchStuID;
    JButton confirm, confirmAndIssue, searchReturn, returnNow, returnReset, confirmReturn, about, stuDb, bookDb, addnewBranch, addnewCat, searchDelStu;
    JButton viewAllBook, viewCategory, viewBookID, viewAllStudent, viewBranch,viewStuById, searchBookDBCategory, searchBookID, searchStuBranch, delStudent;
    JCheckBox c1;
    JDateChooser issueDate, returnDate;
    JLabel n1, n2, n3, n4, n5, n6, n7, n8, delStuName, delStuBranch, delStuID, stuName,stuAdd,stuEmail,stuBr,stuMobile;
    JLabel returnBookTitle, returnStuName, returnStuID, returnBranch, returnIssueDate;
    JTable historyTable, viewAllBookTable, viewCategoryTable, viewAllStuTable, viewStuBranchTable, stuBooksTable;
    JPanel viewAllStuPanel, viewStuBranchPanel,viewStuIDPanel;

    
    /* MEMBER METHOD : fillBranches() ::: THIS IS THE METHOD TO POPULATE A COMBOBOX WITH BRANCHES FROM DATABASE*/
    private void fillBranches(JComboBox b1) {

        String sql = "select *from branch";
        try {
            connectionClass con = new connectionClass();
            PreparedStatement pt = con.c.prepareStatement(sql);
            ResultSet rt = pt.executeQuery();

            while (rt.next()) {
                b1.addItem(rt.getString("BRANCH"));
            }
            rt.close();
            pt.close();
        } catch (Exception e) {
                JOptionPane.showMessageDialog(this,e,"",0);
        }
    }

/* MEMBER METHOD : fillCategory() ::: THIS IS THE METHOD TO POPULATE A COMBOBOX WITH CATEGORIES FROM DATABASE*/
    private void fillCategory(JComboBox b1) {

        String sql = "select *from category";
        try {
            connectionClass con = new connectionClass();
            PreparedStatement pt = con.c.prepareStatement(sql);
            ResultSet rt = pt.executeQuery();

            while (rt.next()) {
                b1.addItem(rt.getString("CATEGORY"));
            }
            rt.close();
            pt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,e,"",0);
        }
    }

    /* CONSTRUCTOR */
        public Home() {
        
        /* BASIC PROPERTIES OF THE HOME FRAME*/
        setTitle("Library Management System @SISTec");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setLayout(null);

        /* CODE FOR WINDOW DIVISION PANELS */
        /* LEFT PANEL : CONSISTS OF MENU BUTTONS TO SURF WITHIN THE APPLICATION*/
        leftPanel = new JPanel();           
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(100, 100, 200));
        leftPanel.setBounds(0, 0, 300, 800);
        add(leftPanel);

        /* HEADER : TITLE PANEL : IT INCLUDES THE ORGANISATION LOGO*/
        titlePanel = new JPanel();        
        titlePanel.setLayout(null);
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(300, 0, 1100, 150);
        ImageIcon logo = new ImageIcon("img/logo.jpeg");
        Image img = logo.getImage().getScaledInstance(299, 95, NORMAL);
        logo = new ImageIcon(img);
        picLogo = new JLabel(logo);
        picLogo.setBounds(0, 20, 1000, 100);
        titlePanel.add(picLogo);
        add(titlePanel);

        /* MAIN PANEL : IT IS THE MAIN CONTENT PANE*/
        mainPanel = new JPanel();        
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(null);
        mainPanel.setBounds(300, 150, 1100, 650);

        /* HEADING : LIBRARY MANAGEMENT SYSTEM*/
        JLabel heading = new JLabel("Library Management System");
        heading.setBounds(150, 0, 900, 60);
        heading.setForeground(Color.black);
        heading.setFont(new Font("Verdana", Font.BOLD, 50));
        mainPanel.add(heading);
        
        ImageIcon icon = new ImageIcon("img/SISTec_Logo.png");
            setIconImage(icon.getImage());

        /* CONTENT OF MAIN PANEL */
        ImageIcon i1 = new ImageIcon("img/addBook.png");
        Image img1 = i1.getImage().getScaledInstance(256, 256, NORMAL);
        i1 = new ImageIcon(img1);
        picAddBook = new JLabel(i1);
        picAddBook.setBounds(100, 150, 256, 256);
        picAddBook.setCursor(new Cursor(12));
        picAddBook.addMouseListener(this);
        mainPanel.add(picAddBook);

        ImageIcon i2 = new ImageIcon("img/addStudent.png");
        Image img2 = i2.getImage().getScaledInstance(200, 200, NORMAL);
        i2 = new ImageIcon(img2);
        picAddStudent = new JLabel(i2);
        picAddStudent.setBounds(420, 150, 256, 256);
        picAddStudent.setCursor(new Cursor(12));
        picAddStudent.addMouseListener(this);
        mainPanel.add(picAddStudent);

        ImageIcon i3 = new ImageIcon("img/database1.png");
        Image img3 = i3.getImage().getScaledInstance(256, 256, NORMAL);
        i3 = new ImageIcon(img3);
        picDatabase = new JLabel(i3);
        picDatabase.setBounds(720, 150, 256, 256);
        picDatabase.setCursor(new Cursor(12));
        picDatabase.addMouseListener(this);
        mainPanel.add(picDatabase);

        about = new JButton("About");
        about.setBounds(960, 20, 80, 25);
        about.setCursor(new Cursor(12));
        about.setFocusPainted(false);
        about.setBorder(panelBorder);
        about.addActionListener(this);
        titlePanel.add(about);

        add(mainPanel);

 
        /* CODE FOR DIFFERENT WINDOW PANELS*/
        
        //*******************
        /* ADD BOOK PANEL */
        //*******************
        addBookPanel = new JPanel();
        addBookPanel.setBounds(300, 150, 1100, 650);
        addBookPanel.setLayout(null);
        addBookPanel.setBackground(Color.white);

        JLabel addBookTitle = new JLabel("Add Book");
        addBookTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        addBookTitle.setBounds(450, 5, 150, 25);
        addBookPanel.add(addBookTitle);

        JPanel p = new JPanel();
        p.setBounds(100, 40, 870, 480);
        p.setLayout(null);
        p.setBorder(panelBorder);
        p.setBackground(new Color(224, 224, 224));
        addBookPanel.add(p);

        JLabel bookTitleText, authorText, publisherText, pagesText, priceText, isbnText, categoryText, bookIdText;

        bookTitleText = new JLabel("Book Title :");
        bookTitleText.setBounds(50, 30, 150, 25);
        bookTitleText.setFont(generalFont);
        p.add(bookTitleText);
            authorText = new JLabel("Author :");
            authorText.setBounds(50, 65, 150, 25);
            authorText.setFont(generalFont);
            p.add(authorText);
        publisherText = new JLabel("Publisher :");
        publisherText.setBounds(50, 100, 150, 25);
        publisherText.setFont(generalFont);
        p.add(publisherText);
            pagesText = new JLabel("Pages :");
            pagesText.setFont(generalFont);
            pagesText.setBounds(50, 135, 150, 25);
            p.add(pagesText);
        priceText = new JLabel("Price :");
        priceText.setFont(generalFont);
        priceText.setBounds(50, 170, 150, 25);
        p.add(priceText);
            isbnText = new JLabel("ISBN no. :");
            isbnText.setFont(generalFont);
            isbnText.setBounds(50, 205, 150, 25);
            p.add(isbnText);
        categoryText = new JLabel("Category : ");
        categoryText.setFont(generalFont);
        categoryText.setBounds(50, 240, 150, 25);
        p.add(categoryText);
            bookIdText = new JLabel("Book ID :");
            bookIdText.setFont(generalFont);
            bookIdText.setBounds(50, 310, 150, 25);
            p.add(bookIdText);

        bookTitle = new JTextField();
        bookTitle.setBorder(panelBorder);
        bookTitle.setBounds(230, 30, 200, 25);
        p.add(bookTitle);
            author = new JTextField();
            author.setBorder(panelBorder);
             author.setBounds(230, 65, 200, 25);
            p.add(author);
        publisher = new JTextField();
        publisher.setBorder(panelBorder);
        publisher.setBounds(230, 100, 200, 25);
        p.add(publisher);
            pages = new JTextField();
            pages.setBorder(panelBorder);
            pages.addKeyListener(this);
            pages.setBounds(230, 135, 100, 25);
            p.add(pages);
        price = new JTextField();
        price.setBorder(panelBorder);
        price.addKeyListener(this);
        price.setBounds(230, 170, 100, 25);
        p.add(price);
            isbn = new JTextField();
            isbn.setBorder(panelBorder);
            isbn.addKeyListener(this);
            isbn.setBounds(230, 205, 200, 25);
            p.add(isbn);
        bookID = new JLabel();
        bookID.setBorder(panelBorder);
        bookID.setBounds(230, 310, 100, 25);
        bookID.setForeground(Color.red);
        bookID.setFont(new Font("Arial", Font.BOLD, 20));
        p.add(bookID);

        generateID = new JButton("Generate Book ID");
        generateID.setBounds(150, 350, 150, 25);
        generateID.setFocusPainted(false);
        generateID.addActionListener(this);
        p.add(generateID);
            submitAndAdd = new JButton("Submit & Add");
            submitAndAdd.setBounds(280, 420, 120, 30);
            submitAndAdd.setEnabled(false);
            submitAndAdd.setCursor(new Cursor(12));
            submitAndAdd.setFocusPainted(false);
            submitAndAdd.addActionListener(this);
            p.add(submitAndAdd);

        b1 = new JComboBox();
        fillCategory(b1);
        p.add(b1);
        b1.setBounds(230, 240, 200, 25);

        resetBook = new JButton("Reset");
        resetBook.setBorder(panelBorder);
        resetBook.setFocusPainted(false);
        resetBook.setBounds(500, 420, 100, 30);
        resetBook.addActionListener(this);
        p.add(resetBook);

        add(addBookPanel);
        addBookPanel.setVisible(false);

      
        //*********************
         /* EDIT BOOK PANEL */
        //*********************
        
        editBookPanel = new JPanel();
        editBookPanel.setBounds(300, 150, 1100, 600);
        editBookPanel.setLayout(null);
        editBookPanel.setBackground(Color.white);

        JLabel editTitle = new JLabel("Edit & Delete Book");
        editTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        editTitle.setBounds(380, 5, 280, 25);
        editBookPanel.add(editTitle);

        JPanel p1 = new JPanel();
        p1.setBounds(100, 40, 870, 480);
        p1.setLayout(null);
        p1.setBackground(new Color(224, 224, 224));
        p1.setBorder(panelBorder);
        editBookPanel.add(p1);

        JLabel l1;
        l1 = new JLabel("Enter Book ID : ");
        l1.setFont(generalFont);
        l1.setBounds(50, 30, 180, 25);
        p1.add(l1);

        bookId = new JTextField();
        bookId.setBounds(220, 30, 110, 25);
        p1.add(bookId);
        bookId.setBorder(panelBorder);

        searchEditDelete = new JButton("Search");
        searchEditDelete.setBounds(170, 65, 80, 25);
        searchEditDelete.setFocusPainted(false);
        searchEditDelete.addActionListener(this);
        p1.add(searchEditDelete);

        editTitle = new JLabel("Book Title :");
        editTitle.setBounds(50, 110, 150, 25);
        editTitle.setFont(generalFont);
        p1.add(editTitle);
            editAuthor = new JLabel("Author :");
            editAuthor.setBounds(50, 145, 150, 25);
            editAuthor.setFont(generalFont);
            p1.add(editAuthor);
        editPublisher = new JLabel("Publisher :");
        editPublisher.setBounds(50, 180, 150, 25);
        editPublisher.setFont(generalFont);
        p1.add(editPublisher);
            editPgs = new JLabel("Pages :");
            editPgs.setBounds(50, 215, 150, 25);
            editPgs.setFont(generalFont);
            p1.add(editPgs);
        editPrice = new JLabel("Price :");
        editPrice.setBounds(50, 250, 150, 25);
        editPrice.setFont(generalFont);
        p1.add(editPrice);
            editIsbn = new JLabel("ISBN no. :");
            editIsbn.setBounds(50, 285, 150, 25);
            editIsbn.setFont(generalFont);
            p1.add(editIsbn);
        editCategory = new JLabel("Category :");
        editCategory.setBounds(50, 315, 150, 25);
        editCategory.setFont(generalFont);
        p1.add(editCategory);

        editTitleText = new JTextField();
        editTitleText.setBounds(250, 110, 180, 25);
        editTitleText.setBorder(panelBorder);
        p1.add(editTitleText);
            editAuthorText = new JTextField();
            editAuthorText.setBounds(250, 145, 150, 25);
            editAuthorText.setBorder(panelBorder);
            p1.add(editAuthorText);
        editPublisherText = new JTextField();
        editPublisherText.setBounds(250, 180, 150, 25);
        editPublisherText.setBorder(panelBorder);
        p1.add(editPublisherText);
            editPagesText = new JTextField();
            editPagesText.setBounds(250, 215, 110, 25);
            editPagesText.addKeyListener(this);
            editPagesText.setBorder(panelBorder);
            p1.add(editPagesText);
        editPriceText = new JTextField();
        editPriceText.addKeyListener(this);
        editPriceText.setBounds(250, 250, 110, 25);
        editPriceText.setBorder(panelBorder);
        p1.add(editPriceText);
            editIsbnText = new JTextField();
            editIsbnText.addKeyListener(this);
            editIsbnText.setBounds(250, 285, 150, 25);
            editIsbnText.setBorder(panelBorder);
            p1.add(editIsbnText);
        editComBox = new JComboBox();
        fillCategory(editComBox);
        editComBox.setBounds(250, 320, 180, 25);
        p1.add(editComBox);

        editSave = new JButton("Edit & Save");
        editSave.setBounds(200, 400, 140, 25);
        editSave.setFocusPainted(false);
        editSave.setEnabled(false);
        editSave.setCursor(new Cursor(12));
        editSave.addActionListener(this);
        p1.add(editSave);
            delete = new JButton("Delete this Book");
            delete.setBounds(400, 400, 150, 25);
            delete.setEnabled(false);
            delete.setCursor(new Cursor(12));
            delete.setFocusPainted(false);
            delete.addActionListener(this);
            p1.add(delete);
        resetEditDelete = new JButton("Reset");
        resetEditDelete.setBounds(600, 400, 80, 25);
        resetEditDelete.setEnabled(true);
        resetEditDelete.setFocusPainted(false);
        resetEditDelete.addActionListener(this);
        p1.add(resetEditDelete);

        add(editBookPanel);
        editBookPanel.setVisible(false);

        
        //***********************************
            /* ADD STUDENT PANEL */
        //***********************************
        
        addStudentPanel = new JPanel();
        addStudentPanel.setBounds(300, 150, 1100, 650);
        addStudentPanel.setLayout(null);
        addStudentPanel.setBackground(Color.white);

        JLabel addS = new JLabel("Register Student");
        addS.setFont(new Font("Verdana", Font.BOLD, 25));
        addS.setBounds(430, 5, 240, 30);
        addStudentPanel.add(addS);

        JPanel pn = new JPanel();
        pn.setBounds(100, 40, 870, 480);
        pn.setLayout(null);
        pn.setBackground(new Color(224, 224, 224));
        pn.setBorder(panelBorder);
        addStudentPanel.add(pn);

        JLabel t1 = new JLabel("Student Name :");
        t1.setFont(generalFont);
        t1.setBounds(50, 30, 180, 25);
        pn.add(t1);
            JLabel t2 = new JLabel("Email :");
            t2.setFont(generalFont);
            t2.setBounds(50, 65, 180, 25);
            pn.add(t2);
        JLabel t3 = new JLabel("Address :");
        t3.setFont(generalFont);
        t3.setBounds(50, 100, 180, 25);
        pn.add(t3);
            JLabel t4 = new JLabel("Mobile :");
            t4.setFont(generalFont);
            t4.setBounds(50, 135, 180, 25);
            pn.add(t4);
        JLabel t5 = new JLabel("Branch :");
        t5.setFont(generalFont);
        t5.setBounds(50, 170, 180, 25);
        pn.add(t5);
            JLabel t6 = new JLabel("Student ID :");
            t6.setFont(generalFont);
            t6.setBounds(50, 205, 150, 25);
            pn.add(t6);

        studentName = new JTextField();
        studentName.setBorder(panelBorder);
        studentName.setBounds(250, 30, 190, 25);
        pn.add(studentName);
            studentEmail = new JTextField();
            studentEmail.setBorder(panelBorder);
            studentEmail.setBounds(250, 65, 190, 25);
            pn.add(studentEmail);
        address = new JTextField();
        address.setBorder(panelBorder);
        address.setBounds(250, 100, 210, 25);
        pn.add(address);
            studentMobile = new JTextField();
            studentMobile.setBorder(panelBorder);
            studentMobile.addKeyListener(this);
            studentMobile.setBounds(250, 135, 190, 25);
            pn.add(studentMobile);

        branch = new JComboBox();
        branch.setBounds(250, 170, 190, 25);
        fillBranches(branch);
        branch.setBorder(panelBorder);
        pn.add(branch);

        studentID = new JTextField();
        studentID.setEditable(false);
        studentID.setBorder(panelBorder);
        studentID.setForeground(Color.red);
        studentID.setFont(new Font("Arial", Font.BOLD, 20));
        studentID.setBounds(250, 205, 150, 25);
        pn.add(studentID);

        generateStuId = new JButton("Generate Student ID");
        generateStuId.setBounds(180, 245, 160, 25);
        generateStuId.setBorder(panelBorder);
        generateStuId.setFocusPainted(false);
        generateStuId.addActionListener(this);
        generateStuId.setCursor(new Cursor(12));
        pn.add(generateStuId);

        addStudent = new JButton("Submit");
        addStudent.setBounds(250, 400, 100, 25);
        addStudent.setBorder(panelBorder);
        addStudent.setFocusPainted(false);
        addStudent.setEnabled(false);
        addStudent.addActionListener(this);
        pn.add(addStudent);

        resetStudent = new JButton("Reset");
        resetStudent.setBounds(400, 400, 100, 25);
        resetStudent.setBorder(panelBorder);
        resetStudent.setFocusPainted(false);
        resetStudent.addActionListener(this);
        pn.add(resetStudent);

        add(addStudentPanel);
        addStudentPanel.setVisible(false);

        
        //****************************
          /* ISSUE BOOK PANEL */
        /****************************/
        
        issuePanel = new JPanel();
        issuePanel.setBounds(300, 150, 1100, 650);
        issuePanel.setLayout(null);
        issuePanel.setBackground(Color.white);

        JLabel issueTitle = new JLabel("Issue Book");
        issueTitle.setBounds(450, 5, 300, 30);
        issueTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        issuePanel.add(issueTitle);

        JPanel pi = new JPanel();
        pi.setBounds(100, 40, 870, 480);
        pi.setBackground(Color.white);
        pi.setLayout(null);
        issuePanel.add(pi);

        JPanel book = new JPanel();
        book.setBounds(20, 20, 400, 250);
        book.setLayout(null);
        book.setBackground(new Color(224, 224, 224));
        book.setBorder(panelBorder);
        pi.add(book);

        JLabel m1 = new JLabel("Enter Book ID : ");
        m1.setFont(generalFont);
        m1.setBounds(20, 20, 180, 25);
        book.add(m1);

        bookIDIssue = new JTextField();
        bookIDIssue.setBounds(230, 20, 130, 25);
        bookIDIssue.setBorder(panelBorder);
        book.add(bookIDIssue);

        checkAvl = new JButton("Check Availability");
        checkAvl.setBorder(panelBorder);
        checkAvl.setFocusPainted(false);
        checkAvl.setBounds(120, 60, 150, 25);
        checkAvl.setCursor(new Cursor(12));
        checkAvl.addActionListener(this);
        book.add(checkAvl);

        JLabel m2 = new JLabel("Book Title : ");
        m2.setFont(new Font("Verdana", Font.PLAIN, 17));
        m2.setBorder(null);
        m2.setBounds(20, 100, 110, 25);
        book.add(m2);
            JLabel m3 = new JLabel("Author : ");
            m3.setFont(new Font("Verdana", Font.PLAIN, 17));
            m3.setBounds(20, 130, 110, 25);
            m3.setBorder(null);
            book.add(m3);
        JLabel m4 = new JLabel("Current Status : ");
        m4.setFont(new Font("Verdana", Font.PLAIN, 17));
        m4.setBounds(20, 160, 160, 25);
        m4.setBorder(null);
        book.add(m4);

        titleIssue = new JTextField();
        titleIssue.setEditable(false);
        titleIssue.setBackground(new Color(224, 224, 224));
        titleIssue.setFont(new Font("Verdana", Font.PLAIN, 15));
        titleIssue.setBorder(null);
        titleIssue.setBounds(130, 100, 260, 25);
        book.add(titleIssue);
            authorIssue = new JTextField();
            authorIssue.setEditable(false);
            authorIssue.setBorder(null);
            authorIssue.setBackground(new Color(224, 224, 224));
            authorIssue.setFont(new Font("Verdana", Font.PLAIN, 15));
            authorIssue.setBounds(130, 130, 250, 25);
            book.add(authorIssue);
        statusIssue = new JTextField();
        statusIssue.setEditable(false);
        statusIssue.setBorder(null);
        statusIssue.setBackground(new Color(224, 224, 224));
        statusIssue.setForeground(Color.green);
        statusIssue.setFont(new Font("Verdana", Font.BOLD, 20));
        statusIssue.setBounds(170, 160, 100, 25);
        book.add(statusIssue);

        resetIssue = new JButton("Reset");
        resetIssue.setBorder(panelBorder);
        resetIssue.setBounds(250, 200, 80, 25);
        resetIssue.setFocusPainted(false);
        resetIssue.addActionListener(this);
        book.add(resetIssue);

        JPanel student = new JPanel();
        student.setBounds(450, 20, 400, 250);
        student.setLayout(null);
        student.setBackground(new Color(224, 224, 224));
        student.setBorder(panelBorder);
        pi.add(student);

        JLabel sid = new JLabel("Enter Student ID : ");
        sid.setFont(generalFont);
        sid.setBounds(20, 20, 200, 25);
        student.add(sid);

        studIDIssue = new JTextField();
        studIDIssue.setBounds(220, 20, 150, 25);
        studIDIssue.setBorder(panelBorder);
        if (statusIssue.getText().equals("AVL")) {
            studIDIssue.setEditable(true);
        } else {
            studIDIssue.setEditable(false);
        }
        student.add(studIDIssue);

        searchIssue = new JButton("Search");
        searchIssue.setBorder(panelBorder);
        searchIssue.setFocusPainted(false);
        searchIssue.setBounds(140, 60, 100, 25);
        searchIssue.setCursor(new Cursor(12));
        searchIssue.addActionListener(this);
        student.add(searchIssue);

        JLabel name = new JLabel("Student Name : ");
        name.setFont(new Font("Verdana", Font.PLAIN, 17));
        name.setBorder(null);
        name.setBounds(10, 100, 150, 25);
        student.add(name);
            JLabel stuBranch = new JLabel("Branch : ");
            stuBranch.setFont(new Font("Verdana", Font.PLAIN, 17));
            stuBranch.setBounds(10, 130, 90, 25);
            stuBranch.setBorder(null);
            student.add(stuBranch);
        JLabel id = new JLabel("Student ID : ");
        id.setFont(new Font("Verdana", Font.PLAIN, 17));
        id.setBounds(10, 160, 120, 25);
        id.setBorder(null);
        student.add(id);

        nameIssue = new JTextField();
        nameIssue.setBorder(null);
        nameIssue.setBackground(new Color(224, 224, 224));
        nameIssue.setEditable(false);
        nameIssue.setBounds(165, 100, 230, 25);
        nameIssue.setFont(new Font("Verdana", Font.PLAIN, 15));
        student.add(nameIssue);
            branchIssue = new JTextField();
            branchIssue.setBorder(null);
            branchIssue.setEditable(false);
            branchIssue.setBackground(new Color(224, 224, 224));
            branchIssue.setBounds(100, 130, 290, 25);
            branchIssue.setFont(new Font("Verdana", Font.PLAIN, 15));
            student.add(branchIssue);

        ids = new JLabel();
        ids.setBounds(140, 160, 150, 25);
        ids.setFont(new Font("Verdana", Font.BOLD, 20));
        ids.setBorder(null);
        student.add(ids);

        resetStuIssue = new JButton("Reset");
        resetStuIssue.setBorder(panelBorder);
        resetStuIssue.setBounds(250, 200, 80, 25);
        resetStuIssue.setFocusPainted(false);
        resetStuIssue.addActionListener(this);
        student.add(resetStuIssue);

        JLabel date = new JLabel("Date of Issue : ");
        date.setFont(new Font("Verdana", Font.BOLD, 20));
        date.setBounds(50, 300, 180, 25);
        pi.add(date);

        issueDate = new JDateChooser();
        issueDate.setBorder(panelBorder);
        issueDate.setBounds(250, 300, 200, 25);
        pi.add(issueDate);

        confirm = new JButton("Confirm Details");
        confirm.setFocusPainted(false);
        confirm.setBorder(panelBorder);
        confirm.addActionListener(this);
        confirm.setBounds(620, 350, 180, 30);
        pi.add(confirm);

        c1 = new JCheckBox("I have there by confirmed the details and Identity of Issuer ", false);

        c1.setFocusPainted(false);
        c1.setBackground(null);
        c1.setEnabled(false);
        c1.setBounds(80, 390, 400, 30);
        pi.add(c1);

        confirmAndIssue = new JButton("Confirm & Issue");
        confirmAndIssue.setBounds(330, 430, 170, 30);
        confirmAndIssue.setFocusPainted(false);
        confirmAndIssue.setEnabled(false);
        confirmAndIssue.setBorder(panelBorder);
        confirmAndIssue.addActionListener(this);
        pi.add(confirmAndIssue);

        add(issuePanel);
        issuePanel.setVisible(false);

        
        ///////////////////////////////
        /*   RETURN BOOK PANEL       */
        ///////////////////////////////
        
        returnPanel = new JPanel();
        returnPanel.setBounds(300, 150, 1100, 650);
        returnPanel.setLayout(null);
        returnPanel.setBackground(Color.white);

        JLabel ret = new JLabel("Return Book");
        ret.setFont(new Font("Verdana", Font.BOLD, 25));
        ret.setBounds(400, 5, 300, 30);
        returnPanel.add(ret);

        JPanel pt = new JPanel();
        pt.setBounds(100, 40, 870, 480);
        pt.setLayout(null);
        pt.setBackground(new Color(224, 224, 224));
        pt.setBorder(panelBorder);
        returnPanel.add(pt);

        JLabel bookt = new JLabel("Enter Book ID : ");
        bookt.setFont(generalFont);
        bookt.setBounds(50, 30, 180, 25);
        pt.add(bookt);

        bookIDReturn = new JTextField();
        bookIDReturn.setBorder(panelBorder);
        bookIDReturn.setBounds(240, 30, 160, 25);
        pt.add(bookIDReturn);

        searchReturn = new JButton("Search");
        searchReturn.setBorder(panelBorder);
        searchReturn.setCursor(new Cursor(12));
        searchReturn.setBounds(170, 65, 80, 25);
        searchReturn.setFocusPainted(false);
        searchReturn.addActionListener(this);
        pt.add(searchReturn);

        JLabel a1 = new JLabel("Book Title : ");
        a1.setFont(generalFont);
        a1.setBounds(50, 120, 150, 25);
        pt.add(a1);
            JLabel a2 = new JLabel("Student ID : ");
            a2.setFont(generalFont);
            a2.setBounds(50, 155, 150, 25);
            pt.add(a2);
        JLabel a3 = new JLabel("Student Name : ");
        a3.setFont(generalFont);
        a3.setBounds(50, 190, 180, 25);
        pt.add(a3);
            JLabel a4 = new JLabel("Branch : ");
            a4.setFont(generalFont);
            a4.setBounds(50, 225, 130, 25);
            pt.add(a4);
        JLabel a5 = new JLabel("Date of Issue : ");
        a5.setFont(generalFont);
        a5.setBounds(50, 260, 180, 25);
        pt.add(a5);

        returnBookTitle = new JLabel();
        returnBookTitle.setFont(new Font("Verdana", Font.PLAIN, 17));
        returnBookTitle.setBounds(240, 120, 500, 30);
        pt.add(returnBookTitle);
            returnStuID = new JLabel();
            returnStuID.setFont(new Font("Verdana", Font.PLAIN, 17));
            returnStuID.setBounds(240, 155, 200, 30);
            pt.add(returnStuID);
        returnStuName = new JLabel();
        returnStuName.setFont(new Font("Verdana", Font.PLAIN, 17));
        returnStuName.setBounds(240, 190, 500, 30);
        pt.add(returnStuName);
            returnBranch = new JLabel();
            returnBranch.setFont(new Font("Verdana", Font.PLAIN, 17));
            returnBranch.setBounds(240, 225, 400, 30);
            pt.add(returnBranch);
        returnIssueDate = new JLabel();
        returnIssueDate.setFont(new Font("Verdana", Font.PLAIN, 17));
        returnIssueDate.setBounds(240, 260, 200, 30);
        pt.add(returnIssueDate);

        returnNow = new JButton("Return Now");
        returnNow.setBounds(300, 300, 130, 25);
        returnNow.setBorder(panelBorder);
        returnNow.setCursor(new Cursor(12));
        returnNow.addActionListener(this);
        returnNow.setFocusPainted(false);
        returnNow.setEnabled(false);
        pt.add(returnNow);
            returnReset = new JButton("Reset");
            returnReset.setBounds(450, 300, 120, 25);
            returnReset.setBorder(panelBorder);
            returnReset.setCursor(new Cursor(12));
            returnReset.addActionListener(this);
            returnReset.setFocusPainted(false);
            pt.add(returnReset);

        JLabel rd = new JLabel("Return Date : ");
        rd.setFont(generalFont);
        rd.setBounds(50, 340, 180, 25);
        pt.add(rd);

        returnDate = new JDateChooser();
        returnDate.setBounds(240, 340, 200, 25);
        returnDate.setBorder(panelBorder);
        returnDate.setEnabled(false);
        pt.add(returnDate);

        confirmReturn = new JButton("Confirm Return");
        confirmReturn.setBounds(300, 400, 150, 25);
        confirmReturn.setFocusPainted(false);
        confirmReturn.setBorder(panelBorder);
        confirmReturn.setEnabled(false);
        confirmReturn.addActionListener(this);
        confirmReturn.setCursor(new Cursor(12));
        pt.add(confirmReturn);

        add(returnPanel);
        returnPanel.setVisible(false);

        
        //////////////////////////////
        //      HISTORY PANEL      ///
        //////////////////////////////
        
        historyPanel = new JPanel();
        historyPanel.setBounds(300, 150, 1100, 650);
        historyPanel.setLayout(null);
        historyPanel.setBackground(Color.white);

        JLabel his = new JLabel("History");
        his.setFont(generalFont);
        his.setFont(new Font("Verdana", Font.BOLD, 25));
        his.setBounds(400, 5, 200, 30);
        historyPanel.add(his);

        historyTable = new JTable();
        historyTable.setCellSelectionEnabled(false);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(90, 100, 900, 400);
        scrollPane.setBorder(panelBorder);
        scrollPane.add(historyTable);
        scrollPane.setViewportView(historyTable);
        historyPanel.add(scrollPane);

        add(historyPanel);
        historyPanel.setVisible(false);

        
        //////////////////////////////////////
        //////        DATABASE PANEL    //////
        //////////////////////////////////////
        
        databasePanel = new JPanel();
        databasePanel.setBounds(300, 150, 1100, 650);
        databasePanel.setLayout(null);
        databasePanel.setBackground(Color.white);

        JLabel dbt = new JLabel("View Database");
        dbt.setBounds(450, 5, 300, 30);
        dbt.setFont(new Font("Verdana", Font.BOLD, 25));
        databasePanel.add(dbt);

            /* STUDENT DATABASE SUB PANEL */
            //*****************************
        stuDBpanel = new JPanel();
        stuDBpanel.setBounds(50, 40, 970, 450);
        stuDBpanel.setBackground(new Color(224, 224, 224));
        stuDBpanel.setLayout(null);
        stuDBpanel.setBorder(panelBorder);
        stuDBpanel.setVisible(false);
        databasePanel.add(stuDBpanel);

        JLabel x = new JLabel("Student DB");
        x.setFont(new Font("Verdana", Font.BOLD, 20));
        x.setBounds(820, 150, 150, 25);
        stuDBpanel.add(x);

        viewAllStudent = new JButton("View All");
        viewAllStudent.setBorder(panelBorder);
        viewAllStudent.setBounds(800, 200, 150, 25);
        viewAllStudent.setFocusPainted(false);
        viewAllStudent.addActionListener(this);
        stuDBpanel.add(viewAllStudent);
            viewBranch = new JButton("View by Branch");
            viewBranch.setBorder(panelBorder);
            viewBranch.setBounds(800, 250, 150, 25);
            viewBranch.setFocusPainted(false);
            viewBranch.addActionListener(this);
            stuDBpanel.add(viewBranch);
        viewStuById = new JButton("By Student ID");
        viewStuById.setBorder(panelBorder);
        viewStuById.setBounds(800, 300, 150, 25);
        viewStuById.setFocusPainted(false);
        viewStuById.addActionListener(this);
        stuDBpanel.add(viewStuById);    

        viewAllStuPanel = new JPanel();
        viewAllStuPanel.setBounds(2, 2, 800, 446);
        viewAllStuPanel.setBackground(new Color(224, 224, 224));
        viewAllStuPanel.setLayout(null);
        viewAllStuPanel.setVisible(true);
        stuDBpanel.add(viewAllStuPanel);
            viewStuBranchPanel = new JPanel();
            viewStuBranchPanel.setBounds(2, 2, 800, 446);
            viewStuBranchPanel.setBackground(new Color(224, 224, 224));
            viewStuBranchPanel.setLayout(null);
            viewStuBranchPanel.setVisible(false);
            stuDBpanel.add(viewStuBranchPanel);
        viewStuIDPanel = new JPanel();
        viewStuIDPanel.setBounds(2,2,800,446);
        viewStuIDPanel.setBackground(new Color(224, 224, 224));
        viewStuIDPanel.setLayout(null);
        viewStuIDPanel.setVisible(false);
        stuDBpanel.add(viewStuIDPanel);
        
        
        viewAllStuTable = new JTable();
        viewAllStuTable.setRowSelectionAllowed(false);

        JScrollPane scn = new JScrollPane();
        scn.setBounds(20, 50, 750, 350);
        scn.setBorder(panelBorder);
        scn.setViewportView(viewAllStuTable);
        viewAllStuPanel.add(scn);
       

        JLabel m = new JLabel("Select Branch");
        m.setFont(new Font("Arial", Font.BOLD, 17));
        m.setBounds(20, 10, 150, 25);
        viewStuBranchPanel.add(m);

        branchWiseStu = new JComboBox();
        branchWiseStu.setBounds(190, 10, 200, 25);
        branchWiseStu.setBorder(panelBorder);
        fillBranches(branchWiseStu);
        viewStuBranchPanel.add(branchWiseStu);

        searchStuBranch = new JButton("Search");
        searchStuBranch.setBounds(150, 40, 100, 25);
        searchStuBranch.setFocusPainted(false);
        searchStuBranch.addActionListener(this);
        searchStuBranch.setBorder(panelBorder);
        viewStuBranchPanel.add(searchStuBranch);

        viewStuBranchTable = new JTable();
        viewStuBranchTable.setRowSelectionAllowed(false);

        JScrollPane spn = new JScrollPane();
        spn.setBorder(panelBorder);
        spn.setBounds(20, 100, 750, 310);
        spn.setViewportView(viewStuBranchTable);
        viewStuBranchPanel.add(spn);
        
        
        JLabel lw  = new JLabel("Enter Student ID : ");
        lw.setBounds(15,10,200,25);
        lw.setFont(generalFont);
        viewStuIDPanel.add(lw);
        
        viewStuID = new JTextField();
        viewStuID.setBorder(panelBorder);
        viewStuID.setBounds(220,10,120,25);
        viewStuIDPanel.add(viewStuID);
        
        searchStuID = new JButton("Search");
        searchStuID.setBounds(120,45,100,25);
        searchStuID.setBorder(panelBorder);
        searchStuID.addActionListener(this);
        searchStuID.setFocusPainted(false);
        searchStuID.setCursor(new Cursor(12));
        viewStuIDPanel.add(searchStuID);
        
        stuName = new JLabel();
        stuName.setFont(generalFont2);
        stuName.setBounds(20,80,500,22);
        viewStuIDPanel.add(stuName);
            stuBr = new JLabel();
            stuBr.setFont(generalFont2);
            stuBr.setBounds(20,102,500,22);
            viewStuIDPanel.add(stuBr);
        stuEmail = new JLabel();
        stuEmail.setFont(generalFont2);
        stuEmail.setBounds(20,124,400,22);
        viewStuIDPanel.add(stuEmail);
            stuMobile = new JLabel();
            stuMobile.setFont(generalFont2);
            stuMobile.setBounds(20,146,400,22);
            viewStuIDPanel.add(stuMobile);
        stuAdd = new JLabel();
        stuAdd.setFont(generalFont2);
        stuAdd.setBounds(20,168,700,22);
        viewStuIDPanel.add(stuAdd);
        
        stuBooksTable = new JTable();
        stuBooksTable.setRowSelectionAllowed(false);
        
        JScrollPane prt = new JScrollPane();
        prt.setBounds(80,210,700,200);
        prt.setBorder(panelBorder);
        prt.setViewportView(stuBooksTable);
        viewStuIDPanel.add(prt);
        
        
            /* BOOK DATABASE SUB PANEL */
            //*****************************

        bookDbpanel = new JPanel();
        bookDbpanel.setBounds(50, 40, 970, 450);
        bookDbpanel.setBackground(new Color(224, 224, 224));
        bookDbpanel.setLayout(null);
        bookDbpanel.setBorder(panelBorder);
        bookDbpanel.setVisible(true);
        databasePanel.add(bookDbpanel);

        JLabel xt = new JLabel("Book DB");
        xt.setFont(new Font("Verdana", Font.BOLD, 20));
        xt.setBounds(830, 80, 150, 25);
        bookDbpanel.add(xt);

        viewAllBook = new JButton("View All");
        viewAllBook.setBounds(800, 150, 150, 25);
        viewAllBook.setBorder(panelBorder);
        viewAllBook.setFocusPainted(false);
        viewAllBook.addActionListener(this);
        bookDbpanel.add(viewAllBook);
            viewCategory = new JButton("View by Category");
            viewCategory.setBounds(800, 210, 150, 25);
            viewCategory.setBorder(panelBorder);
            viewCategory.setFocusPainted(false);
            viewCategory.addActionListener(this);
            bookDbpanel.add(viewCategory);
        viewBookID = new JButton("View by Book ID");
        viewBookID.setBounds(800, 270, 150, 25);
        viewBookID.setBorder(panelBorder);
        viewBookID.setFocusPainted(false);
        viewBookID.addActionListener(this);
        bookDbpanel.add(viewBookID);

        
        viewAllBookPanel = new JPanel();
        viewAllBookPanel.setBounds(2, 2, 800, 446);
        viewAllBookPanel.setLayout(null);
        viewAllBookPanel.setBackground(new Color(224, 224, 224));
        viewAllBookPanel.setVisible(true);
        bookDbpanel.add(viewAllBookPanel);

        viewAllBookTable = new JTable();
        viewAllBookTable.setRowSelectionAllowed(false);

        JScrollPane sc = new JScrollPane();
        sc.setBounds(20, 50, 750, 350);
        sc.setViewportView(viewAllBookTable);
        sc.setBorder(panelBorder);
        viewAllBookPanel.add(sc);

           
        viewCategoryPanel = new JPanel();
        viewCategoryPanel.setBounds(2, 2, 800, 446);
        viewCategoryPanel.setBackground(new Color(224, 224, 224));
        viewCategoryPanel.setLayout(null);
        viewCategoryPanel.setVisible(false);
        bookDbpanel.add(viewCategoryPanel);

        JLabel l2 = new JLabel("Select Category");
        l2.setFont(new Font("Arial", Font.BOLD, 17));
        l2.setBounds(20, 10, 200, 20);
        viewCategoryPanel.add(l2);

        categoryWiseBook = new JComboBox();
        fillCategory(categoryWiseBook);
        categoryWiseBook.setBounds(200, 10, 190, 25);
        categoryWiseBook.setBorder(panelBorder);
        viewCategoryPanel.add(categoryWiseBook);

        searchBookDBCategory = new JButton("Search");
        searchBookDBCategory.setBounds(150, 40, 100, 25);
        searchBookDBCategory.setFocusPainted(false);
        searchBookDBCategory.setBorder(panelBorder);
        searchBookDBCategory.addActionListener(this);
        viewCategoryPanel.add(searchBookDBCategory);

        viewCategoryTable = new JTable();
        viewCategoryTable.setRowSelectionAllowed(false);

        JScrollPane sc1 = new JScrollPane();
        sc1.setBounds(20, 100, 750, 300);
        sc1.setViewportView(viewCategoryTable);
        sc1.setBorder(panelBorder);
        viewCategoryPanel.add(sc1);

        
        viewBookIDPanel = new JPanel();
        viewBookIDPanel.setBounds(2, 2, 800, 446);
        viewBookIDPanel.setLayout(null);
        viewBookIDPanel.setBackground(new Color(224, 224, 224));
        viewBookIDPanel.setVisible(false);
        bookDbpanel.add(viewBookIDPanel);

        JLabel n = new JLabel("Enter Book ID : ");
        n.setFont(new Font("Arial", Font.BOLD, 17));
        n.setBounds(20, 10, 150, 25);
        viewBookIDPanel.add(n);

        bookIDDB = new JTextField();
        bookIDDB.setBounds(200, 10, 130, 25);
        bookIDDB.setBorder(panelBorder);
        viewBookIDPanel.add(bookIDDB);

        searchBookID = new JButton("Search");
        searchBookID.setBounds(150, 40, 100, 25);
        searchBookID.addActionListener(this);
        searchBookID.setFocusPainted(false);
        searchBookID.setBorder(panelBorder);
        viewBookIDPanel.add(searchBookID);

        n1 = new JLabel("");
        n1.setFont(generalFont2);
        n1.setBounds(100, 100, 500, 20);
        viewBookIDPanel.add(n1);
            n2 = new JLabel("");
            n2.setFont(generalFont2);
            n2.setBounds(100, 130, 500, 20);
            viewBookIDPanel.add(n2);
        n3 = new JLabel("");
        n3.setFont(generalFont2);
        n3.setBounds(100, 160, 500, 20);
        viewBookIDPanel.add(n3);
            n4 = new JLabel("");
            n4.setFont(generalFont2);
            n4.setBounds(100, 190, 500, 20);
            viewBookIDPanel.add(n4);
        n5 = new JLabel("");
        n5.setFont(generalFont2);
        n5.setBounds(100, 220, 500, 20);
        viewBookIDPanel.add(n5);
            n6 = new JLabel("");
            n6.setFont(generalFont2);
            n6.setBounds(100, 250, 500, 20);
            viewBookIDPanel.add(n6);
        n7 = new JLabel("");
        n7.setFont(generalFont2);
        n7.setBounds(100, 280, 500, 20);
        viewBookIDPanel.add(n7);
            n8 = new JLabel("");
            n8.setFont(generalFont2);
            n8.setBounds(100, 310, 500, 20);
            viewBookIDPanel.add(n8);

        stuDb = new JButton("Student DB");
        stuDb.setBounds(350, 500, 150, 25);
        stuDb.setBorder(panelBorder);
        stuDb.setFocusPainted(false);
        stuDb.addActionListener(this);
        databasePanel.add(stuDb);

        bookDb = new JButton("Book DB");
        bookDb.setBounds(510, 500, 150, 25);
        bookDb.setBorder(panelBorder);
        bookDb.setFocusPainted(false);
        bookDb.addActionListener(this);
        databasePanel.add(bookDb);

        add(databasePanel);
        databasePanel.setVisible(false);

        
        /******************************
        /*  ADMIN CORNER PANEL  */
        //***************************
        
        adminPanel = new JPanel();
        adminPanel.setBounds(300, 150, 1100, 650);
        adminPanel.setLayout(null);
        adminPanel.setBackground(Color.white);
        add(adminPanel);

        JLabel mt = new JLabel("Admin Corner");
        mt.setFont(new Font("Verdana", Font.BOLD, 25));
        mt.setBounds(420, 0, 300, 30);
        adminPanel.add(mt);

        JPanel pa = new JPanel();
        pa.setBorder(panelBorder);
        pa.setLayout(null);
        pa.setBounds(50, 40, 450, 200);
        adminPanel.add(pa);

        JLabel l = new JLabel("Enter new Category : ");
        l.setFont(generalFont2);
        l.setBounds(10, 50, 200, 20);
        pa.add(l);

        newCat = new JTextField();
        newCat.setBounds(210, 50, 150, 25);
        newCat.setBorder(panelBorder);
        pa.add(newCat);

        addnewCat = new JButton(" Add Category");
        addnewCat.setBorder(panelBorder);
        addnewCat.setBounds(100, 100, 100, 25);
        addnewCat.addActionListener(this);
        addnewCat.setFocusPainted(false);
        addnewCat.setCursor(new Cursor(12));
        pa.add(addnewCat);

        JPanel pb = new JPanel();
        pb.setBorder(panelBorder);
        pb.setLayout(null);
        pb.setBounds(520, 40, 450, 200);
        adminPanel.add(pb);

        JLabel lm = new JLabel("Enter new Branch : ");
        lm.setFont(generalFont2);
        lm.setBounds(10, 50, 200, 20);
        pb.add(lm);

        newBranch = new JTextField();
        newBranch.setBounds(210, 50, 150, 25);
        newBranch.setBorder(panelBorder);
        pb.add(newBranch);

        addnewBranch = new JButton(" Add Branch");
        addnewBranch.setBorder(panelBorder);
        addnewBranch.setBounds(100, 100, 100, 25);
        addnewBranch.addActionListener(this);
        addnewBranch.setFocusPainted(false);
        addnewBranch.setCursor(new Cursor(12));
        pb.add(addnewBranch);

        JPanel pc = new JPanel();
        pc.setBorder(panelBorder);
        pc.setLayout(null);
        pc.setBounds(50, 260, 920, 250);
        adminPanel.add(pc);
            JLabel ds = new JLabel("Delete Student");
            ds.setFont(new Font("Verdana", Font.BOLD, 20));
            ds.setBounds(300, 5, 200, 25);
            pc.add(ds);

        JLabel xr = new JLabel("Enter Student ID : ");
        xr.setFont(generalFont2);
        xr.setBounds(10, 30, 170, 25);
        pc.add(xr);

        delStu = new JTextField();
        delStu.setBounds(180, 30, 110, 25);
        delStu.setBorder(panelBorder);
        delStu.addKeyListener(this);
        pc.add(delStu);

        searchDelStu = new JButton(" Search ");
        searchDelStu.setBounds(100, 65, 100, 20);
        searchDelStu.setFocusPainted(false);
        searchDelStu.setCursor(new Cursor(12));
        searchDelStu.addActionListener(this);
        pc.add(searchDelStu);

        delStuName = new JLabel("Name : ");
        delStuName.setFont(new Font("Verdana", Font.PLAIN, 18));
        delStuName.setForeground(Color.red);
        delStuName.setBounds(100, 90, 500, 25);
        pc.add(delStuName);
            delStuBranch = new JLabel("Branch : ");
            delStuBranch.setFont(new Font("Verdana", Font.PLAIN, 18));
            delStuBranch.setForeground(Color.red);
            delStuBranch.setBounds(100, 120, 400, 25);
            pc.add(delStuBranch);
        delStuID = new JLabel();

        delStudent = new JButton("Delete");
        delStudent.setBounds(400, 155, 100, 25);
        delStudent.setBorder(panelBorder);
        delStudent.setCursor(new Cursor(12));
        delStudent.setFocusPainted(false);
        delStudent.addActionListener(this);
        delStudent.setEnabled(false);
        pc.add(delStudent);

        adminPanel.setVisible(false);
       

        /* **********************************************************
         MENU BUTTONS OF THE LEFT PANEL TO SURF WITHIN THE APPLICATION 
         ***********************************************************/
        ImageIcon homeIcon = new ImageIcon("img/home.png");
        Image i = homeIcon.getImage().getScaledInstance(120, 120, NORMAL);
        homeIcon = new ImageIcon(i);
        JLabel homei = new JLabel(homeIcon);
        homei.setBounds(85, 10, 128, 128);
        leftPanel.add(homei);

        home = new JButton("Home", null);
        home.setFont(new Font("Verdana", Font.BOLD, 25));
        home.setForeground(Color.white);
        home.setBackground(new Color(100, 100, 200));
        home.setBounds(0, 150, 300, 30);
        home.setBorderPainted(false);
        home.setFocusPainted(false);
        home.setCursor(new Cursor(12));     // for finger cursor int == 12
        home.addActionListener(this);
        home.addMouseListener(this);
        leftPanel.add(home);

        addBook = new JButton("Add Book");
        addBook.setFont(new Font("Verdana", Font.BOLD, 25));
        addBook.setForeground(Color.white);
        addBook.setBackground(new Color(100, 100, 200));
        addBook.setBounds(0, 200, 300, 30);
        addBook.setCursor(new Cursor(12));
        addBook.setBorderPainted(false);
        addBook.setFocusPainted(false);
        addBook.addMouseListener(this);
        addBook.addActionListener(this);
        leftPanel.add(addBook);

        editDelete = new JButton("Edit/Delete Book");
        editDelete.setFont(new Font("Verdana", Font.BOLD, 25));
        editDelete.setForeground(Color.white);
        editDelete.setBackground(new Color(100, 100, 200));
        editDelete.setBounds(0, 250, 300, 30);
        editDelete.setCursor(new Cursor(12));
        editDelete.setBorderPainted(false);
        editDelete.setFocusPainted(false);
        editDelete.addActionListener(this);
        editDelete.addMouseListener(this);
        leftPanel.add(editDelete);

        issue = new JButton("Issue");
        issue.setFont(new Font("Verdana", Font.BOLD, 25));
        issue.setForeground(Color.white);
        issue.setBackground(new Color(100, 100, 200));
        issue.setBounds(0, 300, 300, 30);
        issue.setCursor(new Cursor(12));
        issue.setBorderPainted(false);
        issue.setFocusPainted(false);
        issue.addActionListener(this);
        issue.addMouseListener(this);
        leftPanel.add(issue);

        returnBook = new JButton("Return");
        returnBook.setFont(new Font("Verdana", Font.BOLD, 25));
        returnBook.setForeground(Color.white);
        returnBook.setBackground(new Color(100, 100, 200));
        returnBook.setBounds(0, 350, 300, 30);
        returnBook.setCursor(new Cursor(12));
        returnBook.setBorderPainted(false);
        returnBook.setFocusPainted(false);
        returnBook.addActionListener(this);
        returnBook.addMouseListener(this);
        leftPanel.add(returnBook);

        history = new JButton("History");
        history.setFont(new Font("Verdana", Font.BOLD, 25));
        history.setForeground(Color.white);
        history.setBackground(new Color(100, 100, 200));
        history.setBounds(0, 400, 300, 30);
        history.setBorderPainted(false);
        history.setFocusPainted(false);
        history.setCursor(new Cursor(12));     // for finger cursor int 12
        history.addActionListener(this);
        history.addMouseListener(this);
        leftPanel.add(history);

        database = new JButton("View Database");
        database.setFont(new Font("Verdana", Font.BOLD, 25));
        database.setForeground(Color.white);
        database.setBackground(new Color(100, 100, 200));
        database.setBounds(0, 450, 300, 30);
        database.setBorderPainted(false);
        database.setFocusPainted(false);
        database.setCursor(new Cursor(12));     // for finger cursor int 12
        database.addActionListener(this);
        database.addMouseListener(this);
        leftPanel.add(database);

        adminCorner = new JButton("Admin Corner");
        adminCorner.setFont(new Font("Verdana", Font.BOLD, 25));
        adminCorner.setForeground(Color.white);
        adminCorner.setBackground(new Color(100, 100, 200));
        adminCorner.setBounds(0, 500, 300, 30);
        adminCorner.setCursor(new Cursor(12));
        adminCorner.setBorderPainted(false);
        adminCorner.setFocusPainted(false);
        adminCorner.addActionListener(this);
        adminCorner.addMouseListener(this);
        leftPanel.add(adminCorner);

        logout = new JButton("Log Out");
        logout.setFont(new Font("Verdana", Font.BOLD, 25));
        logout.setForeground(Color.white);
        logout.setBackground(new Color(100, 100, 200));
        logout.setBounds(0, 550, 300, 30);
        logout.setBorderPainted(false);
        logout.setFocusPainted(false);
        logout.setCursor(new Cursor(12));     // for finger cursor int 12
        logout.addActionListener(this);
        logout.addMouseListener(this);
        leftPanel.add(logout);

    }

    
    /***********************************************************************************/
        /* ACTION HANDLER METHOD*/
        /* actionPerformed() IS THE METHOD FOR ADDING FUNCTIONALITY TO THE BUTTONS */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == about) {

            String about = " Library Management System @SISTec v1.0.0 \n Standalone Desktop Application for data management of Library\n Developed by - \n Mukul Mahajan";
            JOptionPane.showMessageDialog(this, about, "About", 1);
        }

        if (e.getSource() == home) {
            mainPanel.setVisible(true);
            addBookPanel.setVisible(false);
            editBookPanel.setVisible(false);
            addStudentPanel.setVisible(false);
            adminPanel.setVisible(false);
            returnPanel.setVisible(false);
            issuePanel.setVisible(false);
            historyPanel.setVisible(false);
            databasePanel.setVisible(false);

        }

        if (e.getSource() == addBook) {
            addBookPanel.setVisible(true);
            mainPanel.setVisible(false);
            editBookPanel.setVisible(false);
            addStudentPanel.setVisible(false);
            adminPanel.setVisible(false);
            returnPanel.setVisible(false);
            issuePanel.setVisible(false);
            historyPanel.setVisible(false);
            databasePanel.setVisible(false);

        }

        if (e.getSource() == editDelete) {
            editBookPanel.setVisible(true);
            mainPanel.setVisible(false);
            addStudentPanel.setVisible(false);
            addBookPanel.setVisible(false);
            adminPanel.setVisible(false);
            returnPanel.setVisible(false);
            issuePanel.setVisible(false);
            historyPanel.setVisible(false);
            databasePanel.setVisible(false);

        }

        if (e.getSource() == issue) {

            editBookPanel.setVisible(false);
            mainPanel.setVisible(false);
            addStudentPanel.setVisible(false);
            returnPanel.setVisible(false);
            addBookPanel.setVisible(false);
            adminPanel.setVisible(false);
            returnPanel.setVisible(false);
            issuePanel.setVisible(true);
            historyPanel.setVisible(false);
            databasePanel.setVisible(false);

        }

        if (e.getSource() == returnBook) {

            editBookPanel.setVisible(false);
            mainPanel.setVisible(false);
            addStudentPanel.setVisible(false);
            addBookPanel.setVisible(false);
            adminPanel.setVisible(false);
            returnPanel.setVisible(true);
            issuePanel.setVisible(false);
            historyPanel.setVisible(false);
            databasePanel.setVisible(false);

        }

        if (e.getSource() == history) {

            editBookPanel.setVisible(false);
            mainPanel.setVisible(false);
            addStudentPanel.setVisible(false);
            addBookPanel.setVisible(false);
            adminPanel.setVisible(false);
            returnPanel.setVisible(false);
            issuePanel.setVisible(false);
            returnPanel.setVisible(false);
            historyPanel.setVisible(true);
            databasePanel.setVisible(false);

            String sql = "select *from stats";
            try {
                connectionClass con = new connectionClass();
                PreparedStatement prt = con.c.prepareStatement(sql);

                ResultSet rt = prt.executeQuery();

                historyTable.setModel(DbUtils.resultSetToTableModel(rt));
                rt.close();
                prt.close();
            } catch (SQLException exp) {
             JOptionPane.showMessageDialog(this, exp,"" , 0);
            }
        }

        if (e.getSource() == database) {
            editBookPanel.setVisible(false);
            mainPanel.setVisible(false);
            addStudentPanel.setVisible(false);
            addBookPanel.setVisible(false);
            adminPanel.setVisible(false);
            returnPanel.setVisible(false);
            issuePanel.setVisible(false);
            historyPanel.setVisible(false);
            databasePanel.setVisible(true);
        }

        if (e.getSource() == adminCorner) {
            editBookPanel.setVisible(false);
            mainPanel.setVisible(false);
            issuePanel.setVisible(false);
            addStudentPanel.setVisible(false);
            returnPanel.setVisible(false);
            addBookPanel.setVisible(false);
            adminPanel.setVisible(true);
            historyPanel.setVisible(false);
            databasePanel.setVisible(false);
        }

        if (e.getSource() == logout) {
            if (JOptionPane.showConfirmDialog(this, "Are you sure to Logout ? ") == 0) {
                setVisible(false);
                new Login().setVisible(true);
            }
        }

        if (e.getSource() == resetBook) {

            bookTitle.setText(null);
            author.setText(null);
            publisher.setText(null);
            b1.setSelectedItem(0);
            pages.setText(null);
            price.setText(null);
            isbn.setText(null);
            bookID.setText(null);
            submitAndAdd.setEnabled(false);
            generateID.setEnabled(true);
        }

        if (e.getSource() == generateID) {
            if (bookTitle.getText().isEmpty() || author.getText().isEmpty() || publisher.getText().isEmpty() || pages.getText().isEmpty() || price.getText().isEmpty() || isbn.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Some fields are empty", "Alert", 1);
            } else {
                String bid = new RandomID().getID();
                bookID.setText(bid);
                generateID.setEnabled(false);
                submitAndAdd.setEnabled(true);
            }
        }

        if (e.getSource() == submitAndAdd) {
            String sql = "insert into book values(?,?,?,?,?,?,?,?,?)";

            try {
                connectionClass conn = new connectionClass();
                PreparedStatement pst = conn.c.prepareStatement(sql);

                pst.setString(1, bookID.getText());
                pst.setString(2, bookTitle.getText());
                pst.setString(3, author.getText());
                pst.setString(4, publisher.getText());
                pst.setString(5, b1.getSelectedItem().toString());
                pst.setString(6, pages.getText());
                pst.setString(7, price.getText());
                pst.setString(8, isbn.getText());
                pst.setString(9, "AVL");

                if (pst.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(this, "Book Title : " + bookTitle.getText() + "\nBookID : " + bookID.getText() + "\nAdded SuccessFully !!", "Message", 1);
                    bookTitle.setText(null);
                    author.setText(null);
                    publisher.setText(null);
                    b1.setSelectedItem(null);
                    pages.setText(null);
                    price.setText(null);
                    isbn.setText(null);
                    bookID.setText(null);
                    submitAndAdd.setEnabled(false);
                    generateID.setEnabled(true);
                    pst.close();
                }

            } catch (SQLException exp) {
                JOptionPane.showMessageDialog(this, "Primary Key Error Occurred !! \nPlease Try Again", "Alert", 0);
                generateID.setEnabled(true);
                bookID.setText(null);
                submitAndAdd.setEnabled(false);
            }
        }

        if (e.getSource() == searchEditDelete) {
            String sql = "select *from book where BOOK_ID=?";

            try {
                connectionClass con = new connectionClass();
                PreparedStatement pt = con.c.prepareStatement(sql);

                pt.setString(1, bookId.getText());

                ResultSet rt = pt.executeQuery();

                if (rt.next()) {
                    editTitleText.setText(rt.getString("TITLE"));
                    editAuthorText.setText(rt.getString("AUTHOR"));
                    editPublisherText.setText(rt.getString("PUBLISHER"));
                    editComBox.setSelectedItem(rt.getString("CATEGORY"));
                    editPagesText.setText(rt.getString("PAGES"));
                    editPriceText.setText(rt.getString("PRICE"));
                    editIsbnText.setText(rt.getString("ISBN"));

                    editSave.setEnabled(true);
                    bookId.setEditable(false);
                    delete.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No such Record found !!", "Alert", 0);
                    bookId.setText(null);
                    bookId.setEditable(true);
                }
                rt.close();
                pt.close();
            } catch (SQLException m) {
             JOptionPane.showMessageDialog(this, m, "Alert", 0);
            }
        }

        if (e.getSource() == resetEditDelete) {
            bookId.setText(null);
            editTitleText.setText(null);
            editAuthorText.setText(null);
            editPublisherText.setText(null);
            editPagesText.setText(null);
            editPriceText.setText(null);
            editIsbnText.setText(null);
            editComBox.setSelectedItem(null);
            editSave.setEnabled(false);
            delete.setEnabled(false);
            bookId.setEditable(true);
        }

        if (e.getSource() == delete) {
            
            String str = "select *from book where BOOK_ID=? AND STATUS=?";
            try{
                connectionClass con  = new connectionClass();
                PreparedStatement pat = con.c.prepareStatement(str);
                
                pat.setString(1,bookId.getText());
                pat.setString(2,"ISSUED");
                
                ResultSet rat = pat.executeQuery();
                             
                if(rat.next()){
                    JOptionPane.showMessageDialog(this,"This Book is currently issued by student\nStudent name : "+rat.getString("STUDENT_NAME")+"\nStudent ID : "+rat.getString("STUDENT_ID")+"\n Hence, its data cannot be erased","Alert",0);
                }
                else{
                    rat.close();
                    pat.close();
                    
                    if (JOptionPane.showConfirmDialog(this, "You are about to delete this book\n Book ID : " + bookId.getText() + "\nAre you sure ?", "Alert", 0) == JOptionPane.YES_OPTION) {
                        String sql = "delete from book where BOOK_ID=?";
                        pat = con.c.prepareStatement(sql);
                        
                        pat.setString(1, bookId.getText());

                        if (pat.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(this, "Book Deleted Successfully \n Book ID : " + bookId.getText(), "Message", 1);
                            bookId.setText(null);
                            editTitleText.setText(null);
                            editAuthorText.setText(null);
                            editPublisherText.setText(null);
                            editPagesText.setText(null);
                            editPriceText.setText(null);
                            editIsbnText.setText(null);
                            editComBox.setSelectedItem(null);
                            editSave.setEnabled(false);
                            delete.setEnabled(false);
                            bookId.setEditable(true);
                    }
                    pat.close();
                }
                }
            }catch(SQLException ep){
                JOptionPane.showMessageDialog(this,ep,"",0);
            }  
        }

        if (e.getSource() == editSave) {
            if (JOptionPane.showConfirmDialog(this, "You are about to edit Book Details. \nAre you sure ?", "Alert", 0) == 0) {
                String sq = "update book set TITLE=?,AUTHOR=?,PUBLISHER=?,CATEGORY=?,PAGES=?,PRICE=?,ISBN=? where BOOK_ID=?";

                try {
                    connectionClass conn = new connectionClass();
                    PreparedStatement p = conn.c.prepareStatement(sq);

                    p.setString(1, editTitleText.getText());
                    p.setString(2, editAuthorText.getText());
                    p.setString(3, editPublisherText.getText());
                    p.setString(4, editComBox.getSelectedItem().toString());
                    p.setString(5, editPagesText.getText());
                    p.setString(6, editPriceText.getText());
                    p.setString(7, editIsbnText.getText());
                    p.setString(8, bookId.getText());

                    if (p.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(this, "Book Details Editted Successfully !!", "Message", 1);
                        bookId.setText(null);
                        editTitleText.setText(null);
                        editAuthorText.setText(null);
                        editComBox.setSelectedItem(null);
                        editPriceText.setText(null);
                        editIsbnText.setText(null);
                        editPagesText.setText(null);
                        editPublisherText.setText(null);
                        editSave.setEnabled(false);
                        delete.setEnabled(false);
                        bookId.setEditable(true);
                        p.close();
                    }
                } catch (SQLException v) {
                   JOptionPane.showMessageDialog(this,v,"",0);
                }
            }
        }

        if (e.getSource() == generateStuId) {
            if (studentName.getText().isEmpty() || address.getText().isEmpty() || studentEmail.getText().isEmpty() || studentMobile.getText().isEmpty() || branch.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Some Field/s are empty !!", "Alert", 1);
            } else {
                String id = new RandomID().getID();
                studentID.setText(id);
                generateStuId.setEnabled(false);
                addStudent.setEnabled(true);
            }
        }

        if (e.getSource() == addStudent) {
        
            String sql = "insert into student values(?,?,?,?,?,?)";

            try {
                connectionClass conn = new connectionClass();
                PreparedStatement pat = conn.c.prepareStatement(sql);

                pat.setString(1, studentID.getText());
                pat.setString(2, studentName.getText());
                pat.setString(3, studentEmail.getText());
                pat.setString(4, address.getText());
                pat.setString(5, studentMobile.getText());
                pat.setString(6, branch.getSelectedItem().toString());

                if (pat.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(this, "Student_ID : " + studentID.getText() + "\nName : " + studentName.getText() + "\nRegistered Successsfully !!", "Message", 1);
                    studentID.setText(null);
                    studentEmail.setText(null);
                    studentMobile.setText((null));
                    studentName.setText(null);
                    address.setText(null);
                    branch.setSelectedItem(null);
                    generateStuId.setEnabled(true);
                    addStudent.setEnabled(false);
                    pat.close();
                }

            } catch (SQLException ep) {
                JOptionPane.showMessageDialog(this, "Primary Key Error , Please Try Again! \n" + ep + "", "Sorry", 2);
                studentID.setText(null);
                generateStuId.setEnabled(true);
                addStudent.setEnabled(false);
            }

        }
        if (e.getSource() == resetStudent) {

            studentName.setText(null);
            address.setText(null);
            studentEmail.setText(null);
            studentID.setText(null);
            studentMobile.setText(null);
            branch.setSelectedItem(null);
            addStudent.setEnabled(false);
            generateStuId.setEnabled(true);
        }

        if (e.getSource() == resetIssue) {

            bookIDIssue.setText(null);
            bookIDIssue.setEditable(true);
            titleIssue.setText(null);
            authorIssue.setText(null);
            statusIssue.setText(null);
            studIDIssue.setEditable(false);
        }

        if (e.getSource() == resetStuIssue) {
            studIDIssue.setText(null);
            nameIssue.setText(null);
            branchIssue.setText(null);
            ids.setText(null);

            if (statusIssue.getText().equals("AVL")) {
                studIDIssue.setEditable(true);
            } else {
                studIDIssue.setEditable(false);
            }
        }

        if (e.getSource() == checkAvl) {

            String sql = "select *from book where BOOK_ID=?";
            try {
                connectionClass conn = new connectionClass();
                PreparedStatement pt = conn.c.prepareStatement(sql);

                pt.setString(1, bookIDIssue.getText());
                ResultSet r = pt.executeQuery();

                if (r.next()) {
                    titleIssue.setText(r.getString("TITLE"));
                    authorIssue.setText(r.getString("AUTHOR"));
                    statusIssue.setText(r.getString("STATUS"));
                    bookIDIssue.setEditable(false);

                    if (statusIssue.getText().equals("AVL")) {
                        statusIssue.setForeground(Color.green);
                        studIDIssue.setEditable(true);
                    } else {
                        statusIssue.setForeground(Color.red);
                        studIDIssue.setEditable(false);
                    }
                    r.close();
                    pt.close();
                } else {
                    JOptionPane.showMessageDialog(this, "No such record found !!", "Alert", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ec) {
                JOptionPane.showMessageDialog(this,ec, "Alert", 0);
            }
        }

        if (e.getSource() == searchIssue) {

            String sql = "select *from student where STUDENT_ID=?";
            try {
                connectionClass con = new connectionClass();
                PreparedStatement put = con.c.prepareStatement(sql);

                put.setString(1, studIDIssue.getText());
                ResultSet rt = put.executeQuery();

                if (rt.next()) {
                    nameIssue.setText(rt.getString("NAME"));
                    branchIssue.setText(rt.getString("BRANCH"));
                    ids.setText(rt.getString("STUDENT_ID"));
                    studIDIssue.setEditable(false);
                } else {
                    JOptionPane.showMessageDialog(this, "NO Such record Found !!", "Alert", JOptionPane.ERROR_MESSAGE);
                }

                rt.close();
                put.close();
            } catch (SQLException em) {
              JOptionPane.showMessageDialog(this,em,"",0);
            }
        }

        if (e.getSource() == confirm) {

            try {

                if (titleIssue.getText().isEmpty() || nameIssue.getText().isEmpty() || issueDate.getDate().toString().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "(Issuer / Book) not selected", "Alert", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    bookIDIssue.setEditable(false);
                    studIDIssue.setEditable(false);
                    resetIssue.setEnabled(false);
                    resetStuIssue.setEnabled(false);
                    c1.setEnabled(true);
                    confirmAndIssue.setEnabled(true);
                    issueDate.setEnabled(false);
                }
            } catch (Exception er) {
                JOptionPane.showMessageDialog(this, "Issue Date not Selected or is in Incorrect Format", "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == confirmAndIssue) {

            if (c1.isSelected()) {

                String sql1 = "insert into stats values(?,?,?,?,?,?,?)";
                String sql2 = "update book set STATUS=? where BOOK_ID=?";
                String sql3 = "insert into issue values(?,?,?,?,?,?,?)";

                try {
                    connectionClass con = new connectionClass();
                    PreparedStatement prt = con.c.prepareStatement(sql1);

                    prt.setString(1, ((JTextField) issueDate.getDateEditor().getUiComponent()).getText());
                    prt.setString(2, bookIDIssue.getText());       
                    prt.setString(3, titleIssue.getText());       
                    prt.setString(4, studIDIssue.getText());      
                    prt.setString(5, nameIssue.getText());      
                    prt.setString(6, branchIssue.getText());         
                    prt.setString(7, "ISSUE");              

                    if (prt.executeUpdate() > 0) {
                        prt.close();

                        prt = con.c.prepareStatement(sql2);

                        prt.setString(1, "ISSUED");
                        prt.setString(2, bookIDIssue.getText());
                        prt.executeUpdate();
                        prt.close();

                        prt = con.c.prepareStatement(sql3);

                        prt.setString(1, ((JTextField) issueDate.getDateEditor().getUiComponent()).getText());
                        prt.setString(2, bookIDIssue.getText());
                        prt.setString(3, titleIssue.getText());
                        prt.setString(4, studIDIssue.getText());
                        prt.setString(5, nameIssue.getText());
                        prt.setString(6, branchIssue.getText());
                        prt.setString(7, "NO");

                        if (prt.executeUpdate() > 0) {
                            JOptionPane.showMessageDialog(this, "Book Successfully Issued !!\nBook ID : " + bookIDIssue.getText() + "\nBook Title : " + titleIssue.getText() + "\nStudent ID : " + studIDIssue.getText() + "\nStudent Name : " + nameIssue.getText(), "Issue Message", 1);
                            bookIDIssue.setText(null);
                            bookIDIssue.setEditable(true);
                            titleIssue.setText(null);
                            authorIssue.setText(null);
                            studIDIssue.setText(null);
                            studIDIssue.setEditable(false);
                            nameIssue.setText(null);
                            branchIssue.setText(null);
                            ids.setText(null);
                            issueDate.setEnabled(true);
                            issueDate.setDate(null);
                            c1.setSelected(false);
                            c1.setEnabled(false);
                            confirmAndIssue.setEnabled(false);
                            statusIssue.setText(null);
                            resetIssue.setEnabled(true);
                            prt.close();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Error", "alert", 0);
                    }

                } catch (SQLException ec) {
                    JOptionPane.showMessageDialog(this,ec,"",0);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please Select the CheckBox", "Alert", 0);
            }
        }

        if (e.getSource() == searchReturn) {
            String sql = "select *from issue where BOOK_ID=? AND RETURN_STATUS=?";

            try {
                connectionClass con = new connectionClass();
                PreparedStatement pt = con.c.prepareStatement(sql);

                pt.setString(1, bookIDReturn.getText());
                pt.setString(2, "NO");

                ResultSet rt = pt.executeQuery();

                if (rt.next()) {
                    returnBookTitle.setText(rt.getString("TITLE"));
                    returnStuID.setText(rt.getString("STUDENT_ID"));
                    returnStuName.setText(rt.getString("STUDENT_NAME"));
                    returnBranch.setText(rt.getString("BRANCH"));
                    returnIssueDate.setText(rt.getString("DATE"));
                    rt.close();
                    pt.close();

                    bookIDReturn.setEditable(false);
                    returnNow.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "No such Record found.\nThis Book is not Issued Yet !!", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception et) {
                JOptionPane.showMessageDialog(this, "Fatal Error", "Error", 0);
            }
        }

        if (e.getSource() == returnReset) {

            bookIDReturn.setText(null);
            bookIDReturn.setEditable(true);
            returnBookTitle.setText(null);
            returnStuName.setText(null);
            returnBranch.setText(null);
            returnIssueDate.setText(null);
            returnStuID.setText(null);
            returnNow.setEnabled(false);
        }

        if (e.getSource() == returnNow) {
            returnDate.setEnabled(true);
            returnReset.setEnabled(false);
            returnNow.setEnabled(false);
            confirmReturn.setEnabled(true);
        }

        if (e.getSource() == confirmReturn) {

            try {
                if (returnDate.getDate().toString().isEmpty()) {
                } else {
                    String sql1 = "insert into stats values(?,?,?,?,?,?,?)";
                    String sql2 = "update book set STATUS=? where BOOK_ID=?";
                    String sql3 = "update issue set RETURN_STATUS=? where BOOK_ID=? AND RETURN_STATUS=?";

                    try {
                        connectionClass con = new connectionClass();
                        PreparedStatement pt = con.c.prepareStatement(sql1);

                        pt.setString(1, ((JTextField) returnDate.getDateEditor().getUiComponent()).getText());
                        pt.setString(2, bookIDReturn.getText());
                        pt.setString(3, returnBookTitle.getText());
                        pt.setString(4, returnStuID.getText());
                        pt.setString(5, returnStuName.getText());
                        pt.setString(6, returnBranch.getText());
                        pt.setString(7, "RETURN");

                        if (pt.executeUpdate() > 0) {
                            pt.close();

                            pt = con.c.prepareStatement(sql2);

                            pt.setString(1, "AVL");
                            pt.setString(2, bookIDReturn.getText());

                            if (pt.executeUpdate() > 0) {
                                pt.close();

                                pt = con.c.prepareStatement(sql3);

                                pt.setString(1, "YES");
                                pt.setString(2, bookIDReturn.getText());
                                pt.setString(3, "NO");

                                if (pt.executeUpdate() > 0) {

                                    JOptionPane.showMessageDialog(this, "Book Successfully Returned", "Message", JOptionPane.INFORMATION_MESSAGE);
                                    bookIDReturn.setText(null);
                                    bookIDReturn.setEditable(true);
                                    returnBookTitle.setText(null);
                                    returnStuName.setText(null);
                                    returnBranch.setText(null);
                                    returnIssueDate.setText(null);
                                    returnStuID.setText(null);
                                    returnNow.setEnabled(false);
                                    returnReset.setEnabled(true);
                                    confirmReturn.setEnabled(false);
                                    returnDate.setDate(null);
                                    returnDate.setEnabled(false);
                                    pt.close();

                                }
                            }
                        }
                    } catch (Exception et) {
                        JOptionPane.showMessageDialog(this, "Fatal Error :"+et, "Alert", 1);
                    }
                }
            } catch (Exception ep) {
                JOptionPane.showMessageDialog(this, "Issue Date not Selected or is in Incorrect Format", "Alert", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == stuDb) {
            stuDBpanel.setVisible(true);
            bookDbpanel.setVisible(false);
        }

        if (e.getSource() == bookDb) {
            stuDBpanel.setVisible(false);
            bookDbpanel.setVisible(true);
        }

        if (e.getSource() == viewAllBook) {

            viewAllBookPanel.setVisible(true);
            viewCategoryPanel.setVisible(false);
            viewBookIDPanel.setVisible(false);

            String sql = "select BOOK_ID,TITLE,AUTHOR,PUBLISHER,CATEGORY,STATUS from book";
            try {
                connectionClass con = new connectionClass();
                PreparedStatement pt = con.c.prepareStatement(sql);

                ResultSet rt = pt.executeQuery();
                viewAllBookTable.setModel(DbUtils.resultSetToTableModel(rt));
                rt.close();
                pt.close();

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(this,ee,"",0);
            }
        }
        if (e.getSource() == viewBookID) {
            viewAllBookPanel.setVisible(false);
            viewCategoryPanel.setVisible(false);
            viewBookIDPanel.setVisible(true);
        }

        if (e.getSource() == viewCategory) {
            viewAllBookPanel.setVisible(false);
            viewCategoryPanel.setVisible(true);
            viewBookIDPanel.setVisible(false);
        }

        if (e.getSource() == searchBookDBCategory) {

            String sql = "select BOOK_ID,TITLE,AUTHOR,PUBLISHER,STATUS from book where CATEGORY=?";
            try {
                connectionClass con = new connectionClass();
                PreparedStatement pt = con.c.prepareStatement(sql);

                pt.setString(1, categoryWiseBook.getSelectedItem().toString());

                ResultSet rt = pt.executeQuery();
                viewCategoryTable.setModel(DbUtils.resultSetToTableModel(rt));
                rt.close();
                pt.close();

            } catch (Exception ee) {
                JOptionPane.showMessageDialog(this,ee,"",0);
            }
        }

        if (e.getSource() == searchBookID) {
            String sql = "select *from book where BOOK_ID=?";

            try {
                connectionClass con = new connectionClass();
                PreparedStatement pt = con.c.prepareStatement(sql);

                pt.setString(1, bookIDDB.getText());
                ResultSet rut = pt.executeQuery();

                n1.setText("Book Title : " + rut.getString("TITLE"));
                n2.setText("Author : " + rut.getString("AUTHOR"));
                n3.setText("Publisher : " + rut.getString("PUBLISHER"));
                n4.setText("Category : " + rut.getString("CATEGORY"));
                n5.setText("Pages : " + rut.getString("PAGES"));
                n6.setText("Price : " + rut.getString("PRICE"));
                n7.setText("ISBN : " + rut.getString("ISBN"));
                n8.setText("Status : " + rut.getString("STATUS"));
                rut.close();
                pt.close();
            } catch (Exception ert) {
                JOptionPane.showMessageDialog(this, "No such Book Record Found !!!", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == viewAllStudent) {
            viewAllStuPanel.setVisible(true);
            viewStuBranchPanel.setVisible(false);
            viewStuIDPanel.setVisible(false);

            String sql = "select *from student";
            try {
                connectionClass con = new connectionClass();
                PreparedStatement put = con.c.prepareStatement(sql);

                ResultSet rt = put.executeQuery();

                viewAllStuTable.setModel(DbUtils.resultSetToTableModel(rt));
               rt.close();
                put.close();
            } catch (Exception ert) {
                JOptionPane.showMessageDialog(this, ert, "alert", 0);
            }
        }

        if (e.getSource() == viewBranch) {
            viewAllStuPanel.setVisible(false);
            viewStuBranchPanel.setVisible(true);
            viewStuIDPanel.setVisible(false);
        }
        
        if(e.getSource() == viewStuById){
            viewAllStuPanel.setVisible(false);
            viewStuBranchPanel.setVisible(false);
            viewStuIDPanel.setVisible(true);
        }

        if (e.getSource() == searchStuBranch) {
            String sql = "select STUDENT_ID,NAME,EMAIL,ADDRESS,MOBILE from student where BRANCH=?";

            try {
                connectionClass con = new connectionClass();
                PreparedStatement put = con.c.prepareStatement(sql);

                put.setString(1, branchWiseStu.getSelectedItem().toString());
                ResultSet rt = put.executeQuery();

                viewStuBranchTable.setModel(DbUtils.resultSetToTableModel(rt));
                rt.close();
                put.close();
            } catch (Exception ert) {
                JOptionPane.showMessageDialog(this, ert, "alert", 0);
            }
        }
        
        if(e.getSource() == searchStuID){
            String sql = "select *from student where STUDENT_ID=?";
            try{
                connectionClass con = new connectionClass();
                PreparedStatement pt = con.c.prepareStatement(sql);
                
                pt.setString(1, viewStuID.getText());
                ResultSet rt = pt.executeQuery();
                
                if(rt.next()){
                    stuName.setText("Name : "+rt.getString("NAME"));
                    stuBr.setText("Branch : "+rt.getString("BRANCH"));
                    stuEmail.setText("Email : "+rt.getString("EMAIL"));
                    stuAdd.setText("Address : "+rt.getString("ADDRESS"));
                    stuMobile.setText("Mobile : "+rt.getString("MOBILE"));
                    rt.close();
                    pt.close();
                    
                    String sql2 = "select *from issue where STUDENT_ID=? and RETURN_STATUS=?";
                    pt = con.c.prepareStatement(sql2);
                    
                    pt.setString(1,viewStuID.getText());
                    pt.setString(2,"NO");
                    
                    rt = pt.executeQuery();
                            
                    stuBooksTable.setModel(DbUtils.resultSetToTableModel(rt));
                    rt.close();
                    pt.close();
                }
                else{
                    JOptionPane.showMessageDialog(this,"No such Record Found","",0);
                }
                                                 
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,"No such Record Found","",0);
            }
        }

        if (e.getSource() == addnewCat) {

            if (newCat.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter a Category", "Alert", JOptionPane.ERROR_MESSAGE);
            } else {
                String str = "insert into CATEGORY values(?)";
                try {
                    connectionClass con = new connectionClass();
                    PreparedStatement pt = con.c.prepareStatement(str);

                    pt.setString(1, newCat.getText());
                    if(pt.executeUpdate() >0)
                          JOptionPane.showMessageDialog(this, "Category Added : " + newCat.getText() + "", "Added Successfully", 1);
                   
                    pt.close();
                    newCat.setText(null);
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(this, "Runtime DB error, Please try again", "Alert", 0);
                }
            }
        }

        if (e.getSource() == addnewBranch) {

            if (newBranch.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter a Branch", "Alert", JOptionPane.ERROR_MESSAGE);
            } else {
                String str = "insert into BRANCH values(?)";
                try {
                    connectionClass con = new connectionClass();
                    PreparedStatement pt = con.c.prepareStatement(str);

                    pt.setString(1, newBranch.getText());
                   if( pt.executeUpdate() > 0)
                    JOptionPane.showMessageDialog(this, "Branch Added : " + newBranch.getText() + "", "Added Successfully", 1);
                    pt.close();
                    newBranch.setText(null);
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(this, "Runtime DB error, Please try again", "Alert", 0);
                }
            }
        }

        if (e.getSource() == searchDelStu) {

            if (delStu.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Student ID ", "Alert", 0);
            } else {
                String sql = "select *from student where STUDENT_ID = ?";
                try {
                    connectionClass con = new connectionClass();
                    PreparedStatement put = con.c.prepareStatement(sql);

                    put.setString(1, delStu.getText());
                    ResultSet rt = put.executeQuery();

                    delStuName.setText("Name : " + rt.getString("NAME"));
                    delStuBranch.setText("Branch : " + rt.getString("BRANCH"));
                    delStuID.setText(rt.getString("STUDENT_ID"));
                    delStudent.setEnabled(true);
                    rt.close();
                    put.close();
                } catch (Exception ert) {
                    JOptionPane.showMessageDialog(this, "No such Record Found !!", "Alert", 0);
                }
            }
        }

        if (e.getSource() == delStudent) {

            String sql = "select *from issue where STUDENT_ID=? AND RETURN_STATUS=?";
            
            try {
                connectionClass conn = new connectionClass();
                connectionClass conn2 = new connectionClass();
                PreparedStatement pt = conn.c.prepareStatement(sql);

                pt.setString(1, delStuID.getText());
                pt.setString(2,"NO");
                
                ResultSet rst = pt.executeQuery();

                    if (rst.next())
                    {
                    JOptionPane.showMessageDialog(this,"This Student currently holds dues, hence his/her data cannot be erased !\n"+rst.getString("STUDENT_NAME"),"Alert", 0);
                    } 
                    else {
                        rst.close();
                        pt.close();
                        String sql2 = "delete from student where STUDENT_ID=?";
                        pt = conn2.c.prepareStatement(sql2);

                        pt.setString(1, delStuID.getText());

                        if(pt.executeUpdate() > 0){
                            JOptionPane.showMessageDialog(this, "Deleted Student !!\nName : " + delStuName.getText(), "Deletion Success", 1);
                            delStu.setText(null);
                            delStuBranch.setText(null);
                            delStuName.setText(null);
                            delStuID.setText(null);
                            delStudent.setEnabled(false);}
                            pt.close();
                         }
            } catch (Exception er) {
                        JOptionPane.showMessageDialog(this,er,"Error",0);
            }
        }
    }
    
    
    /* MOUSE HANDLER FOR THE UI COMPONENTS */
    /* ABSTRACT METHODS OF MouseListener INTERFACE HAS BEEN IMPLEMENTED HERE*/

    public void mouseClicked(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet.");//To change body of generated methods, choose Tools | Templates.

        if (e.getSource() == picAddBook) {
            mainPanel.setVisible(false);
            addBookPanel.setVisible(true);
        }
        if (e.getSource() == picAddStudent) {
            mainPanel.setVisible(false);
            addStudentPanel.setVisible(true);
        }
        if (e.getSource() == picDatabase) {
            mainPanel.setVisible(false);
            databasePanel.setVisible(true);
        }
        if (e.getSource() == historyTable) {
            e.consume();
        }

    }

    public void mousePressed(MouseEvent e) {

        if (e.getSource() == historyTable) {
            e.consume();
        }
    }

    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == historyTable) {
            e.consume();
        }
    }

    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == picAddBook) {

            picAddBook.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.lightGray));

        }
        if (e.getSource() == picAddStudent) {

            picAddStudent.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.lightGray));

        }
        if (e.getSource() == picDatabase) {

            picDatabase.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.lightGray));
        }
        if (e.getSource() == historyTable) {
            e.consume();
            return;
        }

        e.getComponent().setBackground(Color.white);
        e.getComponent().setForeground(Color.black);
    }

    
    public void mouseExited(MouseEvent e) {

        if (e.getSource() == picAddBook) {
            picAddBook.setBorder(null);
        }
        if (e.getSource() == picAddStudent) {

            picAddStudent.setBorder(null);
        }
        if (e.getSource() == picDatabase) {

            picDatabase.setBorder(null);
        }
        if (e.getSource() == historyTable) {
            e.consume();
            return;
        }
        e.getComponent().setBackground(new Color(100, 100, 200));
        e.getComponent().setForeground(Color.white);
    }

    
    /***********************************************/
    /**************** MAIN METHOD *****************/
    
    public static void main(String args[]) {
        new Home().setVisible(true);
    }

   /* KET HANDLERS : ABSTRACT METHODS OF KeyListener INTERFACE HAS BEEN IMPLEMENTED HERE*/
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        char c = e.getKeyChar();

        if (e.getSource() == price || e.getSource() == editPriceText) {
            if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_PERIOD) {
            } else {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        } else {
            if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE) {
            } else {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }

    }

    public void keyPressed(KeyEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void keyReleased(KeyEvent e) {
        /// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/* ************************ END OF HOME CLASS ***************************/