package org.example;

import org.custom.exceptions.CommandException;
import org.custom.exceptions.ImageNotFoundException;
import org.custom.exceptions.RepositoryNotFoundException;

import java.io.IOException;
import java.util.List;

public interface Command {
    void execute(String[] args, List<Repository> repositoryList) throws CommandException, IOException, ImageNotFoundException, RepositoryNotFoundException;
}
