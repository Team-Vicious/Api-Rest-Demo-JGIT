package com.example.alumno.JGIT;

import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.URIish;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GitControl {

    private String localPath;
    private String httpUrl;

    public void createBranch(String nombreRama) throws IOException , GitAPIException{


        // prepare test-repository

        try (Git git = Git.open(new File(localPath))) {
            List<Ref> call = git.branchList().call();
            for (Ref ref : call) {
                System.out.println("Branch-Before: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
            }

            // make sure the branch is not there
            List<Ref> refs = git.branchList().call();
            for(Ref ref : refs) {
                System.out.println("Had branch: " + ref.getName());
                if(ref.getName().equals("refs/heads/testbranch")) {
                    System.out.println("Removing branch before");
                    git.branchDelete()
                            .setBranchNames(nombreRama)
                            .setForce(true)
                            .call();

                    break;
                }
            }

            // run the add-call
            git.branchCreate()
                    .setName(nombreRama)
                    .call();

            call = git.branchList().call();
            for (Ref ref : call) {
                System.out.println("Branch-Created: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
            }

            // run the delete-call
            //git.branchDelete()
            //.setBranchNames("testbranch")
            //.call();

            call = git.branchList().call();
            for (Ref ref : call) {
                System.out.println("Branch-After: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
            }

            // run the add-call with a given starting point
            //git.branchCreate()
            //       .setName("testbranch")
            //    .setStartPoint("d52a1031cd359a5941d0e047aa7ab82053f7f7c3")
            //     .call();

            call = git.branchList().call();
            for (Ref ref : call) {
                System.out.println("Branch-Created with starting point: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
            }
        }  catch (GitAPIException e) {
            e.printStackTrace();
        }
    }


    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public void commitAndPush(String mensaje, String user, String password){
        try{

            Repository localRepo = new FileRepository(localPath);
            try (Git git = Git.open(new File(localPath))) {


                AddCommand add = git.add();
                add.addFilepattern(".").call();

                git.commit().setMessage(mensaje).call();


                // agregar al repositorio remoto
                RemoteAddCommand remoteAddCommand = git.remoteAdd();
                remoteAddCommand.setName("origin");
                remoteAddCommand.setUri(new URIish(httpUrl));

                // Podemos agregar mas configuraciones si es necesario aca
                remoteAddCommand.call();

                // hacer push al remoto:
                PushCommand pushCommand = git.push();
                //pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(user, password));
                // podemos agregar mas configuraciones aca si es necesario
                pushCommand.call();
            }


        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
