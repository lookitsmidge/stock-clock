import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
//focusListener

//hashing   - base 64  (encoding)

// sha 256 hashing to check passwords
// salt - add a set of charachters to the end of the passsword
//	do not save passwords
// private salt = "HYFTeieift^6766779";
public class MainGUI extends JFrame implements ActionListener, KeyListener{
	JFrame MainFrame = new JFrame();
	Random r = new Random();
	StaffList StaffList = new StaffList();
	WindowListener exitListener = new WindowAdapter(){
		@Override
		public void windowClosing(WindowEvent e){
			int confirm = JOptionPane.showOptionDialog(null, "are you sure you want to close the Application>","Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if(confirm == 0){
				System.out.println("[GUI] Saving...");
				StaffList.writeToFile();
				System.out.println("[GUI] Saved");
				System.exit(0);
			}
			/*if((r.nextInt(100)+1)< 50){
				System.exit(0);
			} else{
				JOptionPane.showMessageDialog(null, "AI has prevented you from closing window");
			}*/
		}
	};
// main panel
	JPanel MainMenu = new JPanel();
	JLabel lblHello = new JLabel();
	JTextField txtTest = new JTextField();
	//different headings -- one for each: product, customer etc
	String[] headingsBlank = {"None","None","None","None"};
	String[] headingsProduct = {"ID","Type","manufacurer","price","quantity","supplierID"};
	String[] headingsCustomer = {"ID","firseName","lastName","Gender","postcode"};
	String[][] tableData = new String[0][0];
	DefaultTableModel SearchTableModel = new DefaultTableModel(tableData, headingsBlank);
	JTable SearchTable = new JTable(SearchTableModel);
	JScrollPane SearchTableScroll = new JScrollPane(SearchTable);
// end main Panel 
// search Panel

	JButton btnLogInOff = new JButton();	//to configure log in log off function  - toggle
	JLabel lblWhoLoggedIn = new JLabel();	// to enable the user to see who has logged on
	boolean loggedIn;

	//combobox model.
	JPanel searchPanel = new JPanel();
	JTextField txtSearchTop = new JTextField();

	JButton btnStaffMenu = new JButton();
	JButton btnClockMenu = new JButton();
	JButton btnCustomerMenu = new JButton();
	JButton btnReturnMenu = new JButton();
	String[] comboSearch_data = {"Staff","Product","Customer"};
	JComboBox comboSearch = new JComboBox(comboSearch_data);

	JButton btnHome = new JButton();
// 
// staff menu
	JTabbedPane staffMenu = new JTabbedPane();
	JPanel staffHomePanel = new JPanel();
	JPanel blankPanel = new JPanel();
	JButton btnGoToAddStaff = new JButton();
	JButton btnGoToViewStaff = new JButton();
//addStaff
	JLabel lblAccessLevel = new JLabel();
	String[] comboAccessLevel_data={"Admin","Staff"};
	JComboBox comboAccessLevel = new JComboBox(comboAccessLevel_data);
	JPanel addPanel = new JPanel();
	JButton btnGoHome = new JButton();
	JLabel lblFirstName = new JLabel();
	JTextField txtFirstName = new JTextField();
	JLabel lblLastName = new JLabel();
	JTextField txtLastName = new JTextField();
	JLabel lblDob = new JLabel();
	JTextField txtDobDd = new JTextField();
	JTextField txtDobMm = new JTextField();
	JTextField txtDobYy = new JTextField();
	JLabel lblPassword = new JLabel();
	JPasswordField txtPassword1 = new JPasswordField();
	JPasswordField txtPassword2 = new JPasswordField();
	JButton btnStaffConfirm = new JButton();

	JLabel lblGender = new JLabel();
	String[] comboGender_data = {"Male","Female","Other"};
	JComboBox comboGender = new JComboBox(comboGender_data);
	JLabel lblHouseNum = new JLabel();
	JTextField txtHouseNum = new JTextField();
	JLabel lblAddressLn1 = new JLabel();
	JTextField txtAddressLn1 = new JTextField();
	JLabel lblAddressLn2 = new JLabel();
	JTextField txtAddressLn2 = new JTextField();
	JLabel lblTownCity = new JLabel();
	JTextField txtTownCity = new JTextField();
	JLabel lblPostcode = new JLabel();
	JTextField txtPostcode = new JTextField();
//
//viewStaff
	JPanel viewStaff = new JPanel();
	JButton btnGoStaffHome1 = new JButton();

	String[][] staffTableData = new String[0][0];
	String[] headingsStaff = {"ID","firstName","lastName","Gender","dailyHours","Total hours","postCode"};
	DefaultTableModel staffTableModel = new DefaultTableModel(staffTableData, headingsStaff);
	JTable staffTable = new JTable(staffTableModel);
	JScrollPane staffTableScroll = new JScrollPane(staffTable);
//
//searchStaff

//endsearchStaff
//end staff menu
//Clock Menu
	JPanel clockPanel = new JPanel();
	JButton btnClockInOut = new JButton();
	clockList CL = new clockList();
//
//Log In Panel
	
	//JPanel logInPanel = new JPanel();   - just use homepage (main screen with a table of buttons of active staff)

//
// Staff sign in
	//int currentStaffID = -1;

	public static void main(String[] args){
		MainGUI MG =  new MainGUI();
		MG.startGUI();
	}
	public void startGUI(){
		//read from file
		System.out.println("[GUI] Starting read");
		StaffList.readFromFile();
		System.out.println("[GUI] initialising GUI");

		CL.fillArray();
		this.addWindowListener(exitListener);
		this.setLayout(new GridBagLayout());
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		buildAllPanels();
		this.setTitle("Stock'n'Clock");
		this.setSize(1600, 900);
		this.setForeground( new Color(-16777216) );
		this.setBackground( new Color(-2696737) );
		this.setVisible(true);
		this.setResizable(false);//make sure to figure aspect ratios into this later on

		//StaffList.readFromFile();
	}
	public void buildAllPanels(){
		buildSearchBox();
		buildMainPanel();
		buildStaffMenu();
		buildClockPanel();
	}
	public void buildBlankPanel(){
		blankPanel.setLayout(null);
	}
	public void buildSearchBox(){
		System.out.println("[gui] creating search box");
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.05;
		c.gridheight = 1;

		searchPanel.setLayout(null);
		searchPanel.setBackground(Color.white);

		txtSearchTop.setLocation(0,0);
		txtSearchTop.setSize(500, 40);
		txtSearchTop.addKeyListener(this);
		txtSearchTop.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent fe) {
    			System.out.println("Focus gained in JPanel");
    			txtSearchTop.setText("");
			}

			public void focusLost(FocusEvent fe){
    			System.out.println("Focus lost in JPanel");
    			txtSearchTop.setText("       						Search      ");
			}
		});
		searchPanel.add(txtSearchTop);

		comboSearch.setLocation(500, 0);
		comboSearch.setSize(100, 40);
		comboSearch.setEditable(false);
		comboSearch.setOpaque(true);
		comboSearch.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String dataSearch = (String) comboSearch.getSelectedItem();
				if(dataSearch.equals("Staff") == true){
					System.out.println("staff selected");
					String[][] tableData = new String[0][0];
					SearchTableModel = new DefaultTableModel(tableData, headingsStaff);
					SearchTable = new JTable(SearchTableModel);
					SearchTableScroll = new JScrollPane(SearchTable);


				} else if(dataSearch.equals("Product") == true){
					System.out.println("Product selected");
					String[][] tableData = new String[0][0];
					SearchTableModel = new DefaultTableModel(tableData, headingsProduct);
					SearchTable = new JTable(SearchTableModel);
					SearchTableScroll = new JScrollPane(SearchTable);


				} else if(dataSearch.equals("Customer") == true){
					System.out.println("Customer");
					String[][] tableData = new String[0][0];
					SearchTableModel = new DefaultTableModel(tableData, headingsCustomer);
					SearchTable = new JTable(SearchTableModel);
					SearchTableScroll = new JScrollPane(SearchTable);
				}
				SearchTableModel.fireTableDataChanged();
				MainMenu.repaint();
				MainMenu.revalidate();
			}
		});
		searchPanel.add(comboSearch);

		btnStaffMenu.setLocation(700,0);
		btnStaffMenu.setSize(100,40);
		btnStaffMenu.addActionListener(this);
		btnStaffMenu.setOpaque(true);
		btnStaffMenu.setText("Staff Menu");
		searchPanel.add(btnStaffMenu);

		btnClockMenu.setLocation(800, 0);
		btnClockMenu.setSize(100,40);
		btnClockMenu.addActionListener(this);
		btnClockMenu.setOpaque(true);
		btnClockMenu.setText("Clock Menu");
		searchPanel.add(btnClockMenu);

		btnHome.setLocation(600,0);
		btnHome.setSize(100,40);
		btnHome.addActionListener(this);
		btnHome.setOpaque(true);
		btnHome.setText("Home");
		searchPanel.add(btnHome);

		btnLogInOff.setLocation(1490,0);
		btnLogInOff.setSize(100,40);
		btnLogInOff.addActionListener(this);
		btnLogInOff.setOpaque(true);
		btnLogInOff.setText("Log In");
		searchPanel.add(btnLogInOff);
		loggedIn = false;

		lblWhoLoggedIn.setLocation(1349,0);
		lblWhoLoggedIn.setSize(150, 40);
		lblWhoLoggedIn.setOpaque(true);
		lblWhoLoggedIn.setText("Not logged In");
		searchPanel.add(lblWhoLoggedIn);



		this.add(searchPanel, c);
	}
	public void buildClockPanel(){
		clockPanel.setLayout(null);
		System.out.println("[GUI] building clock manager");

		btnClockInOut.setLocation(400, 50);
		btnClockInOut.setSize(200, 70);
		btnClockInOut.addActionListener(this);
		btnClockInOut.setOpaque(true);
		btnClockInOut.setText("CLOCK");
		clockPanel.add(btnClockInOut);

		this.add(clockPanel, getGridBagMain());
	}
//staff Code
	public void buildStaffMenu(){
		System.out.println("[gui] creating staff menu");

		//staffMenu.setLayout(null);
		staffMenu.setLocation(0,0);
		buildStaffHomePanel();
		staffMenu.addTab("Home",staffHomePanel);
		staffMenu.setEnabledAt(0, false);
		buildAddStaff();
		staffMenu.addTab("Add",addPanel);
		staffMenu.setEnabledAt(1, false);
		staffMenu.setVisible(true);
		buildViewStaff();
		staffMenu.addTab("View",viewStaff);
		staffMenu.setEnabledAt(2, false);
		staffMenu.setVisible(true);
		buildBlankPanel();
		staffMenu.addTab("BLANK", blankPanel);
		staffMenu.setEnabledAt(3, false);
		staffMenu.setVisible(true);
		staffMenu.setSelectedIndex(3);

		this.add(staffMenu, getGridBagMain());
		//this.remove(staffMenu);
	}
	public void buildViewStaff(){
		System.out.println("[GUI] creating view staff");
		viewStaff.setLayout(null);

		btnGoStaffHome1.setLocation(0,0);
		btnGoStaffHome1.setSize(190,50);
		btnGoStaffHome1.addActionListener(this);
		btnGoStaffHome1.setOpaque(true);
		btnGoStaffHome1.setText("StaffHome");
		viewStaff.add(btnGoStaffHome1);

		staffTableScroll.setSize(720,350);
		staffTableScroll.setLocation(0,100);
		viewStaff.add(staffTableScroll);
	}
	public void clearStaffTable(){
		System.out.println("\n[GUI] clearing table");

		try{
			int numberOfRows = staffTableModel.getRowCount();

			for(int i=0; i<numberOfRows;i++){
				System.out.println("	[GUI] Removing Row Num: " + i );
				staffTableModel.removeRow(i);
				System.out.println("	[GUI] Removed");
			}
			System.out.println("[GUI] Completed clearing rows");
		} catch(Exception e){
			System.out.println("[GUI][ERROR] an error occured when performing table clear");
		}
	}
	public void populateStaffTable(){
		System.out.println("[GUI] Populating table");

		staffTableModel.setRowCount(0);
		for(int i=0;i<StaffList.nextStaffLocation;i++){
			System.out.println("	[GUI] Adding row num:" + i);
			String[] dataToAdd = {StaffList.staffArray[i].staffID+"", StaffList.staffArray[i].firstName, StaffList.staffArray[i].lastName, StaffList.staffArray[i].gender+"", StaffList.staffArray[i].dailyHours+"", StaffList.staffArray[i].totalHours+"", StaffList.staffArray[i].postCode+""};//need to use table model headings to add them to the table
			staffTableModel.addRow(dataToAdd);
			System.out.println("[GUI] Row has been added");
		}
		System.out.println("[GUI] finished adding rows");
	}
	public void buildStaffHomePanel(){
		System.out.println("[GUI] building staff home panel");
		staffHomePanel.setLayout(null);

		btnGoToAddStaff.setLocation(100, 50);
		btnGoToAddStaff.setSize(200, 50);
		btnGoToAddStaff.addActionListener(this);
		btnGoToAddStaff.setOpaque(true);
		btnGoToAddStaff.setVisible(true);
		btnGoToAddStaff.setText("Add Staff");
		staffHomePanel.add(btnGoToAddStaff);

		btnGoToViewStaff.setLocation(400, 50);
		btnGoToViewStaff.setSize(200, 50);
		btnGoToViewStaff.addActionListener(this);
		btnGoToViewStaff.setOpaque(true);
		btnGoToViewStaff.setVisible(true);
		btnGoToViewStaff.setText("View Staff");
		staffHomePanel.add(btnGoToViewStaff);

		System.out.println("[GUI] finished making add staff panel");
	}
	public void buildAddStaff(){
		System.out.println("[gui] creating staff menu");
		addPanel.setLayout(null);
	//buttons
		btnGoHome.setLocation(0, 0);
		btnGoHome.setSize(190,50);
		btnGoHome.addActionListener(this);
		btnGoHome.setOpaque(true);
		btnGoHome.setText("StaffHome");
		addPanel.add(btnGoHome);

		btnStaffConfirm.setLocation(190,0);
		btnStaffConfirm.setSize(190, 50);
		btnStaffConfirm.addActionListener(this);
		btnStaffConfirm.setOpaque(true);
		btnStaffConfirm.setText("Confirm");
		addPanel.add(btnStaffConfirm);
	//
	//accessLevel
		lblAccessLevel.setLocation(190, 50);
		lblAccessLevel.setSize(150, 50);
		lblAccessLevel.setOpaque(true);
		lblAccessLevel.setText("Type:");
		addPanel.add(lblAccessLevel);

		comboAccessLevel.setLocation(340, 50);
		comboAccessLevel.setSize(150,50);
		comboAccessLevel.setEditable(false);
		comboAccessLevel.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event){
				if(event.getStateChange() == ItemEvent.SELECTED){
					int tempLevel = comboAccessLevel.getSelectedIndex();// as 0 is SU
					if(tempLevel == 0){//admin
						System.out.println("admin selected");

					} else if(tempLevel == 1){
						System.out.println("staff selected");
					} else if(tempLevel == 2){
						System.out.println("customer selected");
					}
				}
			}
		});
		comboAccessLevel.setOpaque(true);
		addPanel.add(comboAccessLevel);
	//
	//firstName
		lblFirstName.setLocation(190,150);
		lblFirstName.setSize(100,50);
		lblFirstName.setOpaque(true);
		lblFirstName.setText("First Name");
		addPanel.add(lblFirstName);

		txtFirstName.setLocation(290,150);
		txtFirstName.setSize(200, 50);
		addPanel.add(txtFirstName);
	//
	//lastName
		lblLastName.setLocation(190,200);
		lblLastName.setSize(100,50);
		lblLastName.setOpaque(true);
		lblLastName.setText("Last name");
		addPanel.add(lblLastName);

		txtLastName.setLocation(290,200);
		txtLastName.setSize(200, 50);
		addPanel.add(txtLastName);
	//
	//dob
		lblDob.setLocation(190,250);
		lblDob.setSize(100,50);
		lblDob.setOpaque(true);
		lblDob.setText("Dob");
		addPanel.add(lblDob);

		txtDobDd.setLocation(290,250);
		txtDobDd.setSize((200/3)+1, 50);
		addPanel.add(txtDobDd);

		txtDobMm.setLocation(290+(200/3),250);
		txtDobMm.setSize((200/3)+1, 50);
		addPanel.add(txtDobMm);

		txtDobYy.setLocation(290+(2*(200/3))+1,250);
		txtDobYy.setSize((200/3)+1, 50);
		addPanel.add(txtDobYy);
	//
	//Password
		lblPassword.setLocation(190,300);
		lblPassword.setSize(100,50);
		lblPassword.setOpaque(true);
		lblPassword.setText("Password");
		addPanel.add(lblPassword);

		txtPassword1.setLocation(290,300);
		txtPassword1.setSize(200, 50);
		addPanel.add(txtPassword1);

		txtPassword2.setLocation(290, 350);
		txtPassword2.setSize(200,50);
		addPanel.add(txtPassword2);
	//
	//Gender
		lblGender.setLocation(600,150);
		lblGender.setSize(100,50);
		lblGender.setOpaque(true);
		lblGender.setText("Gender:");
		addPanel.add(lblGender);

		comboGender.setLocation(700, 150);
		comboGender.setSize(150,50);
		comboGender.setEditable(false);
		comboGender.setOpaque(true);
		addPanel.add(comboGender);
	//
	//houseNum
		lblHouseNum.setLocation(600,190);
		lblHouseNum.setSize(150, 50);
		lblHouseNum.setOpaque(true);
		lblHouseNum.setText("House Num/Name:");
		addPanel.add(lblHouseNum);

		txtHouseNum.setLocation (710, 200);
		txtHouseNum.setSize(200, 50);
		addPanel.add(txtHouseNum);
	//
	//addressLn1
		lblAddressLn1.setLocation(600, 250);
		lblAddressLn1.setSize(100, 50);
		lblAddressLn1.setOpaque(true);
		lblAddressLn1.setText("Address Ln1:");
		addPanel.add(lblAddressLn1);

		txtAddressLn1.setLocation(700, 250);
		txtAddressLn1.setSize(200,50);
		addPanel.add(txtAddressLn1);
	//
	//addressLn2
		lblAddressLn2.setLocation(600, 300);
		lblAddressLn2.setSize(100, 50);
		lblAddressLn2.setOpaque(true);
		lblAddressLn2.setText("Address Ln2:");
		addPanel.add(lblAddressLn2);

		txtAddressLn2.setLocation(700, 300);
		txtAddressLn2.setSize(200,50);
		addPanel.add(txtAddressLn2);
	//
	//Town/City
		lblTownCity.setLocation(600, 350);
		lblTownCity.setSize(100, 50);
		lblTownCity.setOpaque(true);
		lblTownCity.setText("Town/City:");
		addPanel.add(lblTownCity);

		txtTownCity.setLocation(700, 350);
		txtTownCity.setSize(150,50);
		addPanel.add(txtTownCity);
	//
	//Postcode
		lblPostcode.setLocation(600, 400);
		lblPostcode.setSize(100, 50);
		lblPostcode.setOpaque(true);
		lblPostcode.setText("PostCode:");
		addPanel.add(lblPostcode);

		txtPostcode.setLocation(700, 400);
		txtPostcode.setSize(100,50);
		addPanel.add(txtPostcode);		
	//

		System.out.println("[GUI] finished making add staff panel");
	}
//endOfStaffCode
	public void buildMainPanel(){
		System.out.println("[gui] creating main menu");

		MainMenu.setLayout(null);

		this.add(MainMenu, getGridBagMain());
	}
	public void actionPerformed(ActionEvent e){
		/*
		MainFrame/this.remove(aPanel);
		MainFrame/this.add(newPanel);
		MainFrame/this.repaint();
		*/
		if(e.getSource()==btnStaffMenu){
			System.out.println("button Pressed - staff menu");
			this.remove(MainMenu);
			this.remove(clockPanel);
			this.add(staffMenu, getGridBagMain());
			staffMenu.setSelectedIndex(0);
			this.repaint();
		}
		if(e.getSource()==btnHome){
			System.out.println("button Pressed - home");
			this.remove(staffMenu);
			this.remove(clockPanel);
			this.add(MainMenu, getGridBagMain());
			this.repaint();
		}
		if(e.getSource()==btnGoHome){
			System.out.println("btnGoHome has been pressed");
			staffMenu.setSelectedIndex(0);
		}
		if(e.getSource()==btnGoToAddStaff){
			System.out.println("btnGoToAddStaff has been pressed");
			/*this.remove(staffMenu);
			this.add(staffMenu);
			this.repaint();*/
			staffMenu.setSelectedIndex(1);
		}
		if(e.getSource()==btnStaffConfirm){
			System.out.println("btnStaffConfirm has been pressed");
			if(txtFirstName.getText().isEmpty()==true || txtLastName.getText().isEmpty()==true || txtDobDd.getText().isEmpty()==true || txtDobMm.getText().isEmpty()==true || txtDobYy.getText().isEmpty()==true || txtPassword1.getText().isEmpty()==true || txtPassword2.getText().isEmpty()==true){
				System.out.println("found blank field");
				JOptionPane.showMessageDialog(null, "One or more fields empty");
			} else{
				Staff tempStaff = new Staff();
				tempStaff.firstName = txtFirstName.getText();
				tempStaff.lastName = txtLastName.getText();
				tempStaff.dob = txtDobDd.getText() + "/" + txtDobMm.getText() + "/" + txtDobYy.getText();

				if(((String) comboAccessLevel.getSelectedItem()).equals("Staff")==true){
					tempStaff.accessLevel = 2;
				} else{
					tempStaff.accessLevel = 3;
				}

				tempStaff.gender = ((String) comboGender.getSelectedItem()).charAt(0);
				tempStaff.dailyHours = 0;
				tempStaff.totalHours = 0;
				//need to check if both passwords are the same  - if not then not make....
				tempStaff.password = txtPassword1.getText();
				tempStaff.houseNameNumber = txtHouseNum.getText();
				tempStaff.addressLn1 = txtAddressLn1.getText();
				tempStaff.addressLn2 = txtAddressLn2.getText();
				tempStaff.townCity = txtTownCity.getText();
				tempStaff.postCode = txtPostcode.getText();

				tempStaff.staffID = StaffList.nextStaffLocation;
			//need to check if the id is unique
				
				StaffList.addToList(tempStaff);
				JOptionPane.showMessageDialog(null, "Staff added");
			}
		}
		if(e.getSource()==btnGoStaffHome1){
			System.out.println("btnGoStaffHome has been pressed");
			staffMenu.setSelectedIndex(0);
		}
		if(e.getSource()==btnGoToViewStaff){
			System.out.println("btnGoToViewStaff");
			clearStaffTable();
			populateStaffTable();
			staffMenu.setSelectedIndex(2);
		}
		if(e.getSource()==btnClockMenu){
			System.out.println("button Pressed - Clock Menu");
			this.remove(MainMenu);
			this.remove(staffMenu);
			this.add(clockPanel, getGridBagMain());
			this.repaint();
		}
		if(e.getSource()==btnClockInOut){
			//check which membet of staff is signed in at the current time.
			//if no staff signed in, then prompt sign in before clock in or out  - soft dev
			//if(currentStaffID == -1){ //blah blah blah}
			//gets staff id from current sign in.
			System.out.println("clock button pressed");
			CL.recordTime(StaffList.currentUserLocation);

		}
		if(e.getSource()==btnLogInOff){
			if (loggedIn == true) { //logged in
				System.out.println("[GUI] btnLogInOff Pressed");
				btnLogInOff.setText("log In");
				System.out.println("Logged Out");
				loggedIn = false;
				lblWhoLoggedIn.setText("Not Logged In");
				StaffList.currentUserLocation = -1;

			} else if (loggedIn == false) { // not logged in - logging in
				System.out.println("[GUI] btnLogInOff Pressed");
				btnLogInOff.setText("log Out");
				System.out.println("Logged In");
				loggedIn = true;
				lblWhoLoggedIn.setText("Logged In as: 0");
				StaffList.currentUserLocation = 1;
			}
			//searchPanel.repaint();
			System.out.println(loggedIn);
		}
	}
	public void keyPressed(KeyEvent args0){
		int key = args0.getKeyCode();
		System.out.println((char)key+"");
		if(key == 13){
			System.out.println("Enter pressed");
		}
	}
	public void keyReleased(KeyEvent args0){
		int key = args0.getKeyCode();
	}
	public void keyTyped(KeyEvent args0){}
	public GridBagConstraints getGridBagMain(){
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1.0;
		c.weighty = 0.95;
		c.gridheight = 3;
		return c;
	}
}