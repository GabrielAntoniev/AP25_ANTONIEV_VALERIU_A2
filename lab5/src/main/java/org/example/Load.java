package org.example;

import org.custom.exceptions.CommandException;

import java.io.*;
import java.util.List;

public class Load implements Command, Serializable {

    //ex: load REPO_DE_PE_DISK
    @Override
    public void execute(String[] args, List<Repository> repositoryList) throws CommandException, FileNotFoundException {
        if (args.length != 2) {
            throw new CommandException("Wrong number of arguments");
        }

        if (args[1].equalsIgnoreCase("ALL")) {
            File currentDir = new File(".");
            File[] serFiles = currentDir.listFiles((dir, name) -> name.endsWith(".ser"));

            if (serFiles == null || serFiles.length == 0) {
                System.out.println("No .ser files found in the current directory.");
                return;
            }

            for (File file : serFiles) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    Repository repo = (Repository) in.readObject();
                    repositoryList.add(repo);
                    System.out.println("Loaded: " + repo.getName());
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Failed to load " + file.getName() + ": " + e.getMessage());
                }
            }
        } else {
            File file = new File(args[1]);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + args[1]);
            }

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                Repository repo = (Repository) in.readObject();
                repositoryList.add(repo);
                System.out.println("Loaded: " + repo.getName());
            } catch (IOException | ClassNotFoundException e) {
                throw new CommandException("Failed to load repository: " + e.getMessage());
            }
        }
    }
}
