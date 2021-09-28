package compositePattern;

import java.util.Collection;


public class Leaf implements IComponent {
	private String id;
	
	public Leaf(String id){
		this.id = id;
	}
	
	public String name(){
		return this.id;
	}

}
