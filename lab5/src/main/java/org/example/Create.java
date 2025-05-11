package org.example;

import org.custom.exceptions.CommandException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Create implements Command {

    //ex: create REPO REPO_NAME
    @Override
    public void execute(String[] args, List<Repository> repositoryList) throws CommandException {
        if(args.length != 2) {
            throw new CommandException("Wrong number of arguments");
        }
        repositoryList.add(new Repository());
        repositoryList.getLast().setName(args[1]);
        System.out.println("Repository created successfully");
    }
}
