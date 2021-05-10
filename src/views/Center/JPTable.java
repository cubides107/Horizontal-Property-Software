package views.Center;

import persistence.Persistence;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JPTable extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	private DefaultTableModel dtmElements;
	private JTable jTable;
	private JScrollPane jScrollPane;
	
	public JPTable() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//		setBackground(ColorsFonts.WHITE);
		loadComponents();
		setVisible(true);
	}
	
	private void loadComponents() {
		dtmElements = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		generateTable();
		configureScroll();
		dtmElements.setColumnIdentifiers(new Object[]{"Nombre","Inmueble","Dueno"});
		this.add(jScrollPane, BorderLayout.PAGE_END);
		
	}
	
	private void generateTable() {
		jTable = new JTable();
		jTable.getTableHeader().setReorderingAllowed(false);
//		jTable.getTableHeader().setBackground(ColorsFonts.LIGHT_BLUE);
		jTable.getTableHeader().setPreferredSize( new Dimension(0, 30));
//		jTable.setBackground(ColorsFonts.WHITE);
//		jTable.getTableHeader().setForeground(ColorsFonts.WHITE);
		jTable.setFillsViewportHeight(true);
		jTable.setRowHeight( 35 );
		jTable.setBorder(null);
		
		jTable.setIntercellSpacing(new Dimension(0, 0));
		jTable.getTableHeader().setResizingAllowed(false);
		
//		dtmElements.setColumnIdentifiers(ConstanstUI.JT_HEADERS);
//		jTable.setModel(dtmElements);
	}
	
	private void configureScroll() {
		jScrollPane = new JScrollPane(jTable);
//		jScrollPane.setForeground(ColorsFonts.WHITE);
		jScrollPane.setBorder(null);
		jScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	private void alingCenterText() {
		DefaultTableCellRenderer centerModel = new DefaultTableCellRenderer(); 
		centerModel.setHorizontalAlignment(SwingConstants.CENTER); 
		for (int i = 0; i < dtmElements.getColumnCount(); i++) {
			jTable.getColumnModel().getColumn(i).setCellRenderer(centerModel);  
		}
	}
	
	public void refreshTable(Object[][] vector) {
		cleanRowsTable();
//		dtmElements.setColumnIdentifiers(ConstanstUI.JT_HEADERS);
		jTable.setModel(dtmElements);
		alingCenterText();
		for (Object[] objects : vector) {
			addElementToTable(objects);
		}
	}
	
	public void showCities(ArrayList<Object[]> vector) {
		cleanRowsTable();
//		dtmElements.setColumnIdentifiers(UtilitiesViews.getConstants(ConstanstUI.JT_HEADERS_CITIES));
		jTable.setModel(dtmElements);
		alingCenterText();
		for (Object[] objects : vector) {
			addElementToTable(objects);
		}
	}
	
	public void showHospitals(ArrayList<Object[]> vector) {
		cleanRowsTable();
//		dtmElements.setColumnIdentifiers(UtilitiesViews.getConstants(ConstanstUI.JT_HEADERS_HOSPITALS));
		jTable.setModel(dtmElements);
		alingCenterText();
		for (Object[] objects : vector) {
			addElementToTable(objects);
		}
	}
	
	public void showPatients(ArrayList<Object[]> vector) {
		cleanRowsTable();
//		dtmElements.setColumnIdentifiers(UtilitiesViews.getConstants(ConstanstUI.JT_HEADERS_PATIENTS));
		jTable.setModel(dtmElements);
		alingCenterText();
		for (Object[] objects : vector) {
			addElementToTable(objects);
		}
	}

	private void cleanRowsTable() {
		dtmElements.setNumRows(0);
	}
	
	public void addElementToTable(Object[] vector) {
		for (int i = 7; i < vector.length; i++) {
//			vector[i] = Utilities.toDecimalFormat((double) vector[i]);
		}
		dtmElements.addRow(vector);
		alingCenterText();
	}

	public void setModelTable() {
		jTable.setModel(Persistence.readUserForTable());
	}
}
