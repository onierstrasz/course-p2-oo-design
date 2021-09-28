package compositePattern;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author greevy
 * 
 * Composite allows you to treat the single instance of an object the same way as a group of objects
 *
 */
public class Composite implements IComponent {
	private String id;
	private ArrayList<IComponent> list = new ArrayList<IComponent> ();
	
	public Composite(String id){
		this.id = id;
	}
	
	public String name(){
		StringBuilder buf = new StringBuilder();
		buf.append(String.format("(%s:",id));
		for (IComponent child: list){
			buf.append(" " + child.name());
		}
		buf.append(")");
		return buf.toString();
			
	}
	public boolean addComponent(IComponent c) {
		return list.add(c);
	}

	public Collection getChildren() {
		return list;
	}

	public boolean removeComponent(IComponent c) {
		return list.remove(c);
	}

}
