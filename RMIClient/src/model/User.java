package model;

public class User {
	private Integer id;
	private String name;
	private Group group;

	public User() {
		super();
	}
	
	public void joinGroup(Group _group) {
		if(group == null) {
			group = _group;
			System.out.println("You joined the group!");
		}
		else {
			System.out.println("You must leave the group: " + group.getName() + " first.");
		}
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}
