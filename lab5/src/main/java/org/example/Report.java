package org.example;

import freemarker.template.*;
import org.custom.exceptions.CommandException;
import org.custom.exceptions.ImageNotFoundException;
import org.custom.exceptions.RepositoryNotFoundException;

import java.awt.Desktop;
import java.io.*;
import java.util.*;

public class Report implements Command {

    @Override
    public void execute(String[] args, List<Repository> repositoryList)
            throws CommandException, IOException, ImageNotFoundException, RepositoryNotFoundException {

        if (args.length < 1) {
            throw new CommandException("Usage: report <repositoryName>");
        }

        String targetRepoName = args[1];

        Repository repository = repositoryList.stream()
                .filter(r -> r.getName().equalsIgnoreCase(targetRepoName))
                .findFirst()
                .orElseThrow(() -> new RepositoryNotFoundException("Repository not found: " + targetRepoName));

        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+ File.separator + "src" +  File.separator + "main" + File.separator + "resources"));
            cfg.setDefaultEncoding("UTF-8");

            System.out.println(cfg.getTemplate("report-template.html"));

            Template template = cfg.getTemplate("report-template.html");

            Map<String, Object> data = new HashMap<>();
            data.put("repositoryName", repository.getName());
            data.put("images", repository.getContent()); // List<Image>

            File outputFile = new File(System.getProperty("user.dir")+ File.separator + "src" +  File.separator + "main" + File.separator + "resources"+File.separator + "report.html");
            try (Writer writer = new FileWriter(outputFile)) {
                template.process(data, writer);
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(outputFile.toURI());
            }

        } catch (TemplateException e) {
            throw new CommandException("Failed to process FreeMarker template: " + e.getMessage());
        }
    }
}
