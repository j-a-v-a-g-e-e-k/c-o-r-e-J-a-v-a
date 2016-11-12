package com.design.pattern.behavioral;

interface FileSystemReciever{
	public void openFile();
	public void closeFile();
}

class WindowsFileSystemReciever implements FileSystemReciever{
	public void openFile(){
		System.out.println("Opening file in Windows OS");
	}
	public void closeFile(){
		System.out.println("Closing file in Windows OS");
	}
}

class UnixFileSystemReciever implements FileSystemReciever{
	public void openFile(){
		System.out.println("Opening file in Unix OS");
	}
	public void closeFile(){
		System.out.println("Closing file in Unix OS");
	}
}

interface Command{
	public void execute();
}

class OpenFileCommand implements Command{
	FileSystemReciever reciever;
	OpenFileCommand(FileSystemReciever reciever){
		this.reciever = reciever;
	}
	public void execute(){
		reciever.openFile();
	}
}

class CloseFileCommand implements Command{
	FileSystemReciever reciever;
	CloseFileCommand(FileSystemReciever reciever){
		this.reciever = reciever;
	}
	public void execute(){
		reciever.closeFile();
	}
}

class Invoker{
	Command command;
	Invoker(Command command){
		this.command = command;
	}
	public void execute(){
		command.execute();
	}
}
public class CommandDemo {
	public static void main(String [] args){
		FileSystemReciever fileSystemReciever = null;
		String osName = System.getProperty("os.name");
		if ("Windows 7".equalsIgnoreCase(osName)){
			fileSystemReciever = new WindowsFileSystemReciever();
		} else {
			fileSystemReciever = new UnixFileSystemReciever();
		}
		Command openCommand = new OpenFileCommand(fileSystemReciever);
		Invoker invoker = new Invoker(openCommand);
		invoker.execute();
		Command closeCommand = new CloseFileCommand(fileSystemReciever);
		invoker = new Invoker(closeCommand);
		invoker.execute();
	}
}
