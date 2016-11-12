package com.design.pattern.Structural;

// Subject base
interface CommandExecutor{
	public void runCommand(String cmd) throws Exception;
}

//Real Subject
class CommandExecutorImpl implements CommandExecutor{
	public void runCommand(String cmd){
		System.out.println(cmd + " executed");
	}
}

// Proxy
class CommandExecutorProxy implements CommandExecutor{
	private CommandExecutor commandExecutor;
	private boolean isAdmin;
	CommandExecutorProxy(String user, String password){
		if ("bimal".equalsIgnoreCase(user) && "password".equalsIgnoreCase(password)){
			isAdmin = true;			
		}
		commandExecutor = new CommandExecutorImpl();
	}
	public void runCommand(String cmd) throws Exception {
		if (isAdmin){
			commandExecutor.runCommand(cmd);
		} else{
			if ("rm".equals(cmd)){
				throw new Exception("rm cannot be executed by non-admin");
			} else{
				commandExecutor.runCommand(cmd);
			}
		}
	}
}

public class ProxyDemo1 {
	public static void main(String [] args){
		CommandExecutor commandExecutor1 =  new CommandExecutorProxy("bimal", "password");		
		CommandExecutor commandExecutor2 = new CommandExecutorProxy("bharat", "password");		
		try {			
			commandExecutor1.runCommand("rm");
			commandExecutor2.runCommand("ls");
			commandExecutor2.runCommand("rm");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
