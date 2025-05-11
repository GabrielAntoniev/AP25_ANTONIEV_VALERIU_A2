package org.example;

import org.custom.exceptions.CommandException;
import org.custom.exceptions.ImageNotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Update implements Command {

    //ex: update REPO_NAME IMAG_NICK IMAG.name IMAG.date IMAG.path
    @Override
    public void execute(String[] args, List<Repository> repositoryList) throws CommandException, IOException, ImageNotFoundException {

        if(args.length != 6) {
            throw new CommandException("Wrong number of arguments");
        }

        Repository repository = repositoryList.stream().filter(repo -> repo.getName().equals(args[1])).findAny().orElseThrow();

        Image toModify = repository.getContent().stream().filter(img -> img.getName().equals(args[2])).findAny().orElse(null);
            if(toModify == null){
                throw new ImageNotFoundException();
            }
            toModify.setName(args[3]);
            toModify.setDate(LocalDate.parse(args[4]));
            toModify.setPath(args[5]);
    }
}
