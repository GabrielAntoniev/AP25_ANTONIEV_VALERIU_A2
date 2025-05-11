package org.example;

import org.custom.exceptions.CommandException;
import org.custom.exceptions.ImageNotFoundException;
import org.custom.exceptions.RepositoryNotFoundException;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class Add implements Command, Serializable {

    //ex: add REPO_NAME IMAG_NICK IMAG_PATH
    @Override
    public void execute(String[] args, List<Repository> repositoryList) throws CommandException, IOException, ImageNotFoundException, RepositoryNotFoundException {
        if(args.length != 4) {
            throw new CommandException("Wrong number of arguments");
        }


        Repository repository = repositoryList.stream().filter(repo -> repo.getName().equals(args[1])).findAny().orElseThrow();
        if(new File(args[3]).exists()){
            repository.addImage(new Image(args[2],LocalDate.now(),args[3]));
            System.out.println("Image added successfully");
        }
        else{
            throw new ImageNotFoundException();
        }

    }
}
