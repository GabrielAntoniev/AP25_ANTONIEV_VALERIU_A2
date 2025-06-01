package org.example;

import org.custom.exceptions.CommandException;
import org.custom.exceptions.ImageNotFoundException;
import org.custom.exceptions.RepositoryNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws CommandException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, RepositoryNotFoundException, ImageNotFoundException {

        //compulsory();
//        try{
//            homework();
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
        homework();
    }

    public static void compulsory(){
        String path = "/home/gabi/Pictures/image.jpeg";
        var img = new Image("image",null, path);
        var repo = new Repository();
        repo.addImage(img);
        try {
            repo.viewImage("image");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void homework() {

        //Repository currentRepo = new Repository();
        List<Repository> repositoryList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if(input.isEmpty()){
                continue;
            }
            String[] args = input.split(" ");
            args[0] = "org.example."+args[0].substring(0,1).toUpperCase() + args[0].substring(1);
            try{
                Command command = (Command) Class.forName(args[0]).getDeclaredConstructor().newInstance();
                command.execute(args, repositoryList);
            }
            catch (RepositoryNotFoundException | ImageNotFoundException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (CommandException e) {
                System.out.println("Command failed: " + e.getMessage());

            } catch (ClassNotFoundException e) {
                System.out.println("Unknown command: " + args[0]);

            } catch (InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                System.out.println("Could not load command: " + e.getMessage());

            } catch (IOException e) {
                System.out.println("IO error: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
                e.printStackTrace();
            }



        }

    }
}