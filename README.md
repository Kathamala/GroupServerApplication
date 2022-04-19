# GroupServerApplication
![image](https://user-images.githubusercontent.com/30270495/164106548-dd6888f7-9f35-4e15-b315-10e0491ff041.png)
https://lucid.app/lucidchart/7a490dc1-8ff8-44d7-bdaa-4305a446c311/edit?invitationId=inv_afaaf127-6ea6-4a34-a468-52c1a7651ed6
### Server:
- Attributes:
	- Group[] groups;
- Methods:
	- getters and setters...
	- create_group(Group group);
    		
		- The server adds a group to its group list, if the name isn't already used by another group. The client calls this method on the server in order to create a new group.
	- send_message(Message message);
    		
		- The client calls this method on the server to send a message to another client. If the group exists, then the message is sent.
	- find_group(String name);
    		
		- Returns the group with the name provided, if there is one. This method is called by the clients.
	- add_user_to_group(User user, Group group);
    		
		- This gets called by "join_group()" to actually add the user to the group by the servers perspective.
	- remove_user_from_group(User user);
    		
		- This gets called by "leave_group()" to actually remove the user from the group.

### User (Client):
- Attributes:
	- Integer id;
	- String name;
	- Group group;
- Methods:
	- getters and setters...
	- The bellow methods are only used to call methods from the server, to provide an extra layer of transparency.
	- join_group(Group group);
		
		- The user sends a request to the server to join a group. If the group exists, and the client isn't part of another group, then the server updates the group to include the new user.
		- The client must use the "find_group()" function to find the group it wants to join.
		- Must call "add_user_to_group()".

	- leave_group();

		- The user sends a request to the server to leave the group he's in. If the user is part of a group, then the server updates the group to remove the user. The user's reference to the group must be set to null.
		- Must call "remove_user_from_group()".

### Group:
- Attributes
	- Integer id;
	- String name;
    	- User[] users;
- Methods:
	- getters and setters...
	- add_user(User user);

		- If the user isn't on another group, it gets added to this group.

	- delete_group();

		- Only the server can call this method. It should set every user's group to null.

### Message:
- Attributes:
	- Group senderGroup, destinyGroup;
	- User senderUser, destinyUser;
	- String content;
- Methods:
	- getters and setters...

# Extra:
- The server has the "true" state of the group list. If the player changes its own attributes without the use of the methods, the server will still see them as before.
- The methods regarding the server connection were not listed.
