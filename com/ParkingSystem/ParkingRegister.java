package com.ParkingSystem;
import java.io.*;
import java.util.ArrayList;

public class ParkingRegister {
    static void fileUpdate(File f,ArrayList<String> content){
        try{
            FileWriter fw=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            for(int i=0;i<content.size();i++){
                bw.write(content.get(i)+"\n");
            }
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
