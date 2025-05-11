package org.example;

import org.custom.exceptions.CommandException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Save implements Command {

    //ex: save REPO_NAME
    @Override
    public void execute(String[] args, List<Repository> repositoryList) throws IOException, CommandException {

        if(args.length != 2) {
            throw new CommandException("Wrong number of arguments");
        }

        if(!args[1].equalsIgnoreCase("ALL")){

            Repository repository = repositoryList.stream().filter(repo -> repo.getName().equals(args[1])).findFirst().orElseThrow();

            //System.out.println(repository);
            System.out.println(repository.getName() + repository.getContent().getFirst().getName() + repository.getContent().getFirst().getPath());

            FileOutputStream fos
                    = new FileOutputStream(repository.getName() + ".ser");
            ObjectOutputStream out
                    = new ObjectOutputStream(fos);
            out.writeObject(repository);
            //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(repository.getName()));
            out.writeObject(repository);
            out.close();
        }
        else{
            for(var repository : repositoryList){
                FileOutputStream fos
                        = new FileOutputStream(repository.getName() + ".ser");
                ObjectOutputStream out
                        = new ObjectOutputStream(fos);
                out.writeObject(repository);
                //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(repository.getName()));
                out.writeObject(repository);
                out.close();
            }
        }

    }
}
