package org.example;

import java.util.List;

public class Exit implements Command {

    @Override
    public void execute(String[] args, List<Repository> repositoryList) {

        System.exit(0);
    }
}
