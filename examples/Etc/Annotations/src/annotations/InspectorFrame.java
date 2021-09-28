package annotations;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

import layout.SpringUtilities;

@SuppressWarnings("serial")
public class InspectorFrame extends JFrame 
		implements ActionListener  {
	
	private Map<String,JTextField> fieldMap;
	private Object object;
	
	public InspectorFrame(Object obj) {
		super("Inspector on " + obj);
		this.object = obj;
		this.fieldMap = new TreeMap<String,JTextField>();
		this.initialize();
	}
	
	public void actionPerformed(ActionEvent e) {
		saveAndClose();
	}

	private JButton createSaveButton() {
		JButton button = new JButton("Save");
		button.addActionListener(this);
		return button;
	}

	public Object getObject() {
		return object;
	}

	private String getValueOrNull(Object object, Method method) {
		try {
			return (String) method.invoke(object);
		} catch (Exception e) {
			// NOTE this should not happen
			e.printStackTrace();
			System.exit(-1);
		}
		return null;
	}
	
	private void initialize() {
        JPanel panel = new JPanel(new SpringLayout());
        this.populatePanel(panel);
        SpringUtilities.makeCompactGrid(panel,fieldMap.size(), 2, 6,6,6,6);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(this.createSaveButton(), BorderLayout.SOUTH);
        this.pack();
 	}
	
	// NOTE reads all properties from object, using the GetProperty annotation.
	private void populatePanel(JPanel panel) {
		for (Method method : object.getClass().getMethods()) {
			if (method.isAnnotationPresent(GetProperty.class)) {
				GetProperty getter = method.getAnnotation(GetProperty.class);
				panel.add(new JLabel(getter.value()));
				String value = this.getValueOrNull(object, method);
				JTextField field = new JTextField(value);
				panel.add(field);
				fieldMap.put(getter.value(), field);
			}
		}
	}

	// NOTE Writes all properties back to object, using SetProperty this time. 
	private void saveAndClose() {
		for (Method method : object.getClass().getMethods()) {
			if (method.isAnnotationPresent(SetProperty.class)) {
				SetProperty setter = method.getAnnotation(SetProperty.class);
				JTextField field = fieldMap.get(setter.value());
				String value = field.getText();
				this.setValueOrNull(object, method, value);
			}
		}
		this.dispose();
		Debug.print(object);
	}
	
	
	private void setValueOrNull(Object object, Method method, String value) {
		try {
			method.invoke(object, value);
		} catch (Exception e) {
			// NOTE this should not happen
			e.printStackTrace();
			System.exit(-1);
		}
	}		

}
