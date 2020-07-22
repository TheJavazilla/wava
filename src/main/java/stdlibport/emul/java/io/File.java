/**
 * This file is a part of Wava(TM)
 * 
 * Copyright (c) 2020 Fungus Software. All Rights Reserved.
 */
package java.io;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class File {

    public static String separator = "/";
    public static String pathSeparator = "/";
    public static char separatorChar = '/';
    private String name;

    public File(String string) {
        this.name = string;
        // TODO Auto-generated constructor stub
    }
    
    public File(File dirFile, String string) {
        this.name = string;
        // TODO
    }

    public File(URI uri) {
        // TODO Auto-generated constructor stub
    }

    public File[] listFiles() {
        return null;
    }
    
    public String getName() {
        return name;
    }

    public boolean exists() {
        // TODO Auto-generated method stub
        return false;
    }

    public File getParentFile() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getAbsolutePath() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object toPath() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getPath() {
        // TODO Auto-generated method stub
        return name;
    }

    public boolean mkdirs() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean canRead() {
        return false;
    }

    public long length() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean delete() {
        // TODO Auto-generated method stub
        return false;
    }

    public String getCanonicalPath() {
        // TODO Auto-generated method stub
        return null;
    }

    public File getCanonicalFile() throws IOException{
        // TODO Auto-generated method stub
        return null;
    }

    public String getParent() {
        // TODO Auto-generated method stub
        return null;
    }

    public String[] list(FilenameFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isAbsolute() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isFile() {
        // TODO Auto-generated method stub
        return false;
    }

    public URI toURI() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isHidden() {
        // TODO Auto-generated method stub
        return false;
    }

    public int compareTo(File file2) {
        // TODO Auto-generated method stub
        return 0;
    }

    public File getAbsoluteFile() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean canWrite() {
        // TODO Auto-generated method stub
        return false;
    }

    public long lastModified() {
        // TODO Auto-generated method stub
        return 0;
    }

    public boolean createNewFile() throws IOException {
        // TODO Auto-generated method stub
        return false;
    }

    public void deleteOnExit() {
        // TODO Auto-generated method stub
    }

    public boolean mkdir() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean renameTo(File dest) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean setLastModified(long time) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean setReadOnly() {
        // TODO Auto-generated method stub
        return false;
    }

    public static Object listRoots() {
        // TODO Auto-generated method stub
        return null;
    }

    public URL toURL() throws MalformedURLException {
        // TODO Auto-generated method stub
        return null;
    }

}