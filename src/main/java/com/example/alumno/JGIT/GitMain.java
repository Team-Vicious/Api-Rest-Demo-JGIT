package com.example.alumno.JGIT;



import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;


public class GitMain {
    public static void main(String[] args) throws IOException, GitAPIException {

    GitControl git = new GitControl();


    //git.setHttpUrl("https://github.com/Team-Vicious/Api-Rest-Demo-JGIT");
    //httpUrl = "https://github.com/Team-Vicious/Api-Rest-Demo-JGIT";

    //git.setLocalPath("ruta en donde se encuentra el proyecto");
    //EJ:  "C:\\Users\\luciano\\Desktop\\alumnoJGIT\\"
    //localPath = "C:\\Users\\luciano\\Desktop\\alumnoJGIT\\";

    try {
        //crear ramas
        git.createBranch("Development");
        git.createBranch("Controladores");
        git.createBranch("Servicios");
        git.createBranch("Repositorios");
        git.createBranch("Entidades");

        //hacer commit y push
        //git.commitAndPush("commit base 1","user","password");

    }catch (Exception e){
        System.out.println(e.getMessage());
    }



    }


}



