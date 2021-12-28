package org.ustudio.lib.builder.dex;

import  com.android.dx.command.Main;
import java.util.logging.Logger;
import java.util.logging.Level;

public class DexBuilder {
    
    public static void dex(String outputDex, String jarFile){
        check(outputDex, jarFile);
        if(check(outputDex, jarFile) == false){
            Logger logger = Logger.getLogger(DexBuilder.class.getName());
            logger.log(Level.SEVERE, "Dex will not build!");
        }else{
            Main.main(new String[]{
                "--dex",
                String.format("--output=%1$s", outputDex),
                jarFile
            });
        }
    }
    
    public static boolean check(String outputDex, String jarFile){
        if(!outputDex.endsWith(".dex")){
            Logger logger = Logger.getLogger(DexBuilder.class.getName());
            logger.log(Level.WARNING, "Only can build into .dex");
            return false;
        }
        else if(!jarFile.endsWith(".jar")){
            Logger logger = Logger.getLogger(DexBuilder.class.getName());
            logger.log(Level.WARNING, "No jar file detected!");
            return false;
        }else{
            return true;
        }
    }
    
}
